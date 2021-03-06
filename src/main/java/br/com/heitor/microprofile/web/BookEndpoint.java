package br.com.heitor.microprofile.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.heitor.microprofile.model.Book;
import br.com.heitor.microprofile.repo.BookManager;

@Path("books")
@RequestScoped
public class BookEndpoint {

	@Inject
	private BookManager bookManager;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") String id) {
		Book book = bookManager.get(id);
		return Response.ok(book).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() {
		return Response.ok(bookManager.getAll()).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		bookManager.delete(bookManager.get(id));
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Book book) {
		String bookId = bookManager.add(book);
		return Response.created(UriBuilder.fromResource(this.getClass()).path(bookId).build()).build();
	}

}
