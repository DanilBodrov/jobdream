package ru.jobdream.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.jobdream.model.Candidate;
import ru.jobdream.model.City;
import ru.jobdream.model.Post;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmlParseService {

    public Map<Integer, List> parserXml(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        Document document = builder.parse(convFile);

        Map<Integer, List> map = new HashMap<>();

        List<Candidate> candidates = parseCandidates(document);
        List<Post> posts = parsePosts(document);

        map.put(1, candidates);
        map.put(2, posts);
        return map;
    }

    public List<Candidate> parseCandidates(Document document) {
        List<Candidate> candidates = new ArrayList<>();
        NodeList candidatesElements = document.getDocumentElement().getElementsByTagName("candidate");
        for (int i = 0; i < candidatesElements.getLength(); i++) {
            Node item = candidatesElements.item(i);
            NamedNodeMap attributes = item.getAttributes();
            Candidate candidate = new Candidate(
                    0,
                    attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("description").getNodeValue(),
                    LocalDateTime.now(),
                    new byte[0]
            );
            candidates.add(candidate);
        }
        return candidates;
    }

    public List<Post> parsePosts(Document document) {
        List<Post> posts = new ArrayList<>();
        NodeList postsElements = document.getDocumentElement().getElementsByTagName("post");
        for (int i = 0; i < postsElements.getLength(); i++) {
            Node item = postsElements.item(i);
            NamedNodeMap attributes = item.getAttributes();
            Post post = new Post(
                    0,
                    attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("description").getNodeValue(),
                    new City(Integer.parseInt(attributes.getNamedItem("city_id").getNodeValue()))
            );
            posts.add(post);
        }
        return posts;
    }
}
