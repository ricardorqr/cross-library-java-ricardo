package com.crossover.techtrial.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.crossover.techtrial.exceptions.Forbidden403Exception;
import com.crossover.techtrial.exceptions.NotFound404Exception;
import com.crossover.techtrial.model.Book;
import com.crossover.techtrial.model.Member;
import com.crossover.techtrial.model.MembershipStatus;
import com.crossover.techtrial.model.Transaction;
import com.crossover.techtrial.service.BookService;
import com.crossover.techtrial.service.MemberService;
import com.crossover.techtrial.service.TransactionService;

public class TransactionControllerTest {

	@InjectMocks
	private TransactionController transactionController;

	@Mock
	private TransactionService transactionService;

	@Mock
	private BookService bookService;

	@Mock
	private MemberService memberService;

	private Map<String, Long> params;
	private Book book1, book2, book3, book4, book5, book6;
	private Member member1, member2;
	private Transaction transaction1, transaction2, transaction3, transaction4, transaction5, transaction6;
	private List<Transaction> transactionListSameBook, transactionList6Books;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		params = new HashMap<String, Long>();
		params.put("bookId", 1L);
		params.put("memberId", 1L);

		book1 = new Book();
		book1.setId(1L);
		book1.setTitle("Java 11");
		
		book2 = new Book();
		book2.setId(2L);
		book2.setTitle("Java 22");
		
		book3 = new Book();
		book3.setId(3L);
		book3.setTitle("Java 33");
		
		book4 = new Book();
		book4.setId(4L);
		book4.setTitle("Java 44");
		
		book5 = new Book();
		book5.setId(5L);
		book5.setTitle("Java 55");
		
		book6 = new Book();
		book6.setId(6L);
		book6.setTitle("Java 66");

		member1 = new Member();
		member1.setId(1L);
		member1.setName("Ricardo");
		member1.setEmail("ricardorqr@gmail.com");
		member1.setMembershipStartDate(LocalDateTime.now());
		member1.setMembershipStatus(MembershipStatus.ACTIVE);

		member2 = new Member();
		member2.setId(2L);
		member2.setName("Sophie");
		member2.setEmail("sophie@gmail.com");
		member2.setMembershipStartDate(LocalDateTime.now());
		member2.setMembershipStatus(MembershipStatus.ACTIVE);
		
		transaction1 = new Transaction();
		transaction1.setId(1L);
		transaction1.setBook(book1);
		transaction1.setMember(member1);
		transaction1.setDateOfIssue(LocalDateTime.now());
		transaction1.setDateOfReturn(null);
		
		transaction2 = new Transaction();
		transaction2.setId(2L);
		transaction2.setBook(book2);
		transaction2.setMember(member1);
		transaction2.setDateOfIssue(LocalDateTime.now());
		transaction2.setDateOfReturn(null);
		
		transaction3 = new Transaction();
		transaction3.setId(3L);
		transaction3.setBook(book3);
		transaction3.setMember(member1);
		transaction3.setDateOfIssue(LocalDateTime.now());
		transaction3.setDateOfReturn(null);
		
		transaction4 = new Transaction();
		transaction4.setId(1L);
		transaction4.setBook(book4);
		transaction4.setMember(member1);
		transaction4.setDateOfIssue(LocalDateTime.now());
		transaction4.setDateOfReturn(null);
		
		transaction5 = new Transaction();
		transaction5.setId(1L);
		transaction5.setBook(book5);
		transaction5.setMember(member1);
		transaction5.setDateOfIssue(LocalDateTime.now());
		transaction5.setDateOfReturn(null);
		
		transaction6 = new Transaction();
		transaction6.setId(1L);
		transaction6.setBook(book6);
		transaction6.setMember(member1);
		transaction6.setDateOfIssue(LocalDateTime.now());
		transaction6.setDateOfReturn(null);
		
		transactionListSameBook = new ArrayList<Transaction>();
		transactionListSameBook.add(transaction1);
		
		transactionList6Books = new ArrayList<Transaction>();
		transactionList6Books.add(transaction1);
		transactionList6Books.add(transaction2);
		transactionList6Books.add(transaction3);
		transactionList6Books.add(transaction4);
		transactionList6Books.add(transaction5);
		transactionList6Books.add(transaction6);
	}

	@Test
	public void issueBookToMemberTest() {
		when(bookService.findById(anyLong())).thenReturn(book1);
		when(memberService.findById(anyLong())).thenReturn(member1);
		when(transactionService.findAllTransactionByBook(anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.findAllTransactionByBookAndMember(anyLong(), anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);

		ResponseEntity<Transaction> transaction = transactionController.issueBookToMember(params);

		assertNotNull(transaction);
		assertEquals(transaction.getBody(), this.transaction1);
		assertEquals(transaction.getBody().getId().longValue(), transaction.getBody().getId().longValue());
		assertEquals(transaction.getBody().getMember().getId().longValue(), transaction.getBody().getMember().getId().longValue());
		assertEquals(transaction.getBody().getBook().getId().longValue(), transaction.getBody().getBook().getId().longValue());
	}

	@Test(expected = NotFound404Exception.class)
	public void issueBookToMemberWithNoMember() {
		when(bookService.findById(anyLong())).thenReturn(book1);
		when(memberService.findById(anyLong())).thenReturn(null);
		when(transactionService.findAllTransactionByBook(anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.findAllTransactionByBookAndMember(anyLong(), anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.issueBookToMember(params);
	}
	
	@Test(expected = NotFound404Exception.class)
	public void issueBookToMemberWithNoBook() {
		when(bookService.findById(anyLong())).thenReturn(null);
		when(memberService.findById(anyLong())).thenReturn(member1);
		when(transactionService.findAllTransactionByBook(anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.findAllTransactionByBookAndMember(anyLong(), anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.issueBookToMember(params);
	}
	
	@Test(expected = Forbidden403Exception.class)
	public void issueBookToMemberWithTheSameBook() {
		when(bookService.findById(anyLong())).thenReturn(book1);
		when(memberService.findById(anyLong())).thenReturn(member1);
		when(transactionService.findAllTransactionByBook(anyLong())).thenReturn(transactionListSameBook);
		when(transactionService.findAllTransactionByBookAndMember(anyLong(), anyLong())).thenReturn(null);
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.issueBookToMember(params);
	}
	
	@Test(expected = Forbidden403Exception.class)
	public void issueBookToMemberWith6Issues() {
		when(bookService.findById(anyLong())).thenReturn(book1);
		when(memberService.findById(anyLong())).thenReturn(member1);
		when(transactionService.findAllTransactionByBook(anyLong())).thenReturn(new ArrayList<Transaction>());
		when(transactionService.findAllTransactionByBookAndMember(anyLong(), anyLong())).thenReturn(transactionList6Books);
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.issueBookToMember(params);
	}
	
	@Test
	public void returnBookTransaction() {
		when(transactionService.findTransactionById(anyLong())).thenReturn(transaction1);
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		ResponseEntity<Transaction> transaction = transactionController.returnBookTransaction(1L);
		
		assertNotNull(transaction);
		assertEquals(transaction.getBody(), this.transaction1);
		assertEquals(transaction.getBody().getId().longValue(), this.transaction1.getId().longValue());
		assertEquals(transaction.getBody().getMember().getId().longValue(), this.transaction1.getMember().getId().longValue());
		assertEquals(transaction.getBody().getBook().getId().longValue(), this.transaction1.getBook().getId().longValue());
		assertEquals(transaction.getBody().getDateOfReturn(), this.transaction1.getDateOfReturn());
	}
	
	@Test(expected = Forbidden403Exception.class)
	public void returnBookTransactionWithNoTransaction() {
		when(transactionService.findTransactionById(anyLong())).thenReturn(null);
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.returnBookTransaction(2L);
	}
	
	@Test(expected = Forbidden403Exception.class)
	public void returnBookTransactionReturnedAlready() {
		transaction1.setDateOfReturn(LocalDateTime.now());
		when(transactionService.findTransactionById(anyLong())).thenReturn(transaction1);
		when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
		
		transactionController.returnBookTransaction(2L);
	}
	
}
