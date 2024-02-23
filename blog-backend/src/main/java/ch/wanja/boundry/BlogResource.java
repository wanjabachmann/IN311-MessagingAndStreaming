package ch.wanja.boundry;

import ch.wanja.control.BlogRepository;
import ch.wanja.entity.Blog;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    BlogRepository blogRepository;

    public BlogResource(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GET
    public List<Blog> getAllBlogs() {
        return blogRepository.listAll();
    }

    @POST
    public void createBlog(Blog blog) {
        blogRepository.persist(blog);
    }
}