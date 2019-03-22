package br.com.heitor.microprofile.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import br.com.heitor.microprofile.model.Book;
import br.com.heitor.microprofile.util.BookMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class BookListMessageBodyWriter implements MessageBodyWriter<List<Book>> {

	public boolean isWriteable(Class<?> clazz, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	public long getSize(List<Book> books, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return 0;
	}

	public void writeTo(List<Book> books, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		JsonWriter jsonWriter = Json.createWriter(entityStream);
		JsonArray jsonArray = BookMapper.map(books);
		jsonWriter.writeArray(jsonArray);
		jsonWriter.close();
	}

}
