package com.crossover.techtrial.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crossover.techtrial.model.Book;
import com.crossover.techtrial.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;
	
	String bookJSON = "{\"id\": \"2\",\"title\": \"Java in 22 days\"}";
	
	@Test
	public void saveABook() throws Exception {
		Book book = new Book();
		book.setId(new Long(3));
		book.setTitle("Java in 22 days");
		
		Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/book")
				.accept(MediaType.APPLICATION_JSON)
				.content(bookJSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		assertEquals("http://localhost/api/book/3", response.getHeader(HttpHeaders.LOCATION));
	}
	
	@Test
	public void getABook() throws Exception {
		Book book = new Book();
		book.setId(new Long(2));
		book.setTitle("Java in 22 days");

		Mockito.when(bookService.findById((anyLong()))).thenReturn(book);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/book/2")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\": 2,\"title\": \"Java in 22 days\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
