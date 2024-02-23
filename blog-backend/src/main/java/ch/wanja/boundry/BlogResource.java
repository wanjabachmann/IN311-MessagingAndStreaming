package ch.wanja.boundry;

import ch.wanja.control.BlogDto;
import ch.wanja.control.BlogRepository;
import ch.wanja.entity.Blog;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void createBlog(BlogDto blogDto) {
        Blog newBlog = new Blog();
        newBlog.setTitle(blogDto.getTitle());
        newBlog.setContent(blogDto.getContent());

        blogRepository.persist(newBlog);
    }
    /*
     * @POST
     * public void createBlog(Blog blog) {
     * blogRepository.persist(blog);
     * }
     */
}