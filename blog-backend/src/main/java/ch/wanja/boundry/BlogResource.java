package ch.wanja.boundry;

import ch.wanja.control.BlogDto;
import ch.wanja.control.BlogRepository;
import ch.wanja.entity.Blog;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    BlogRepository blogRepository;

    @Inject
    @Channel("blogs-out")
    Emitter<String> blogPostEmitter;

    public BlogResource(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GET
    public List<Blog> getAllBlogs() {
        return blogRepository.listAll();
    }

    @POST
    @Transactional
    public void createBlog(BlogDto blogDto) {
        Blog newBlog = new Blog();
        newBlog.setTitle(blogDto.getTitle());
        newBlog.setContent(blogDto.getContent());

        blogRepository.persist(newBlog);

        blogPostEmitter.send(newBlog.getContent());
    }

    @Incoming("validation-result-out")
    public void updateValidationStatus(String validationResult) {
        System.out.println("Update is: ==> " + validationResult);
    }

}