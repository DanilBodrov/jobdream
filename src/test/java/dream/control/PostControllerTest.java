package dream.control;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.jobdream.controller.PostController;
import ru.jobdream.model.City;
import ru.jobdream.model.Post;
import ru.jobdream.service.CityService;
import ru.jobdream.service.PostService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Test
    public void whenCreatedPost() {
        List<Post> posts = Arrays.asList(
                new Post(1, "name1", "description1", new City(1, "Msc")),
                new Post(2, "name2", "description2", new City(2, "Sam"))
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        String page = postController.posts(model);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post post =  new Post(1, "name1", "description1", new City(1, "Msc"));
        Post newPost =  new Post(1, "newName", "newDescription1", new City(1, "Msc"));
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findById(post.getId())).thenReturn(newPost);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.formUpdatePost(model, post.getId());
        verify(model).addAttribute("post", newPost);
        assertThat(page, is("updatePost"));
    }
}