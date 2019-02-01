/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.model.Book;
import com.crossover.techtrial.repositories.BookRepository;

/**
 * @author crossover
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAll() {
		List<Book> personList = new ArrayList<>();
		bookRepository.findAll().forEach(personList::add);
		return personList;
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book findById(Long bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		return book.orElse(null);
	}

}
