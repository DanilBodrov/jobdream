package ru.jobdream.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.jobdream.service.CandidateService;
import ru.jobdream.service.PostService;
import ru.jobdream.service.XmlParseService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ThreadSafe
@Controller
public class ParseController {

    private final CandidateService candidateService;
    private final PostService postService;
    private final XmlParseService xmlParseService = new XmlParseService();

    public ParseController(CandidateService candidateService, PostService postService) {
        this.candidateService = candidateService;
        this.postService = postService;
    }

    @GetMapping("/formParseXML")
    public String parseXml() {
        return "/parseXML";
    }

    @PostMapping("/parseAndAddXml")
    public String parseAndAddXml(@RequestParam("file") MultipartFile file) throws IOException, SAXException, ParserConfigurationException {
        Map<Integer, List> dataFromXml = xmlParseService.parserXml(file);
        candidateService.create(dataFromXml.get(1));
        postService.create(dataFromXml.get(2));
        return "redirect:/index";
    }
}
