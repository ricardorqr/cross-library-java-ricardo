/**
 * 
 */
package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.exceptions.Forbidden403Exception;
import com.crossover.techtrial.exceptions.NotFound404Exception;
import com.crossover.techtrial.model.Book;
import com.crossover.techtrial.model.Member;
import com.crossover.techtrial.model.Transaction;
import com.crossover.techtrial.service.BookService;
import com.crossover.techtrial.service.MemberService;
import com.crossover.techtrial.service.TransactionService;

/**
 * @author kshah
 *
 */
@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BookService bookService;

	/*
	 * PLEASE DO NOT CHANGE SIGNATURE OR METHOD TYPE OF END POINTS Example Post
	 * Request : { "bookId":1,"memberId":33 }
	 */
	@PostMapping(path = "/api/transaction")
	public ResponseEntity<Transaction> issueBookToMember(@RequestBody Map<String, Long> params) {
		Long bookId = params.get("bookId");
		Long memberId = params.get("memberId");

		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new NotFound404Exception("Boot not found");
		}

		Member member = memberService.findById(memberId);
		if (member == null) {
			throw new NotFound404Exception("Member not found");
		}

		Transaction tran = transactionService.findBookByBookAndMember(bookId, memberId);
		if (tran == null) {
			throw new NotFound404Exception("Book has issued to a member");
		}

		List<Transaction> tranList = transactionService.findAllTransaction(bookId, memberId);
		if (!tranList.isEmpty() && tranList.size() > 5) {
			throw new Forbidden403Exception("Member has 5 issues already");
		}

		Transaction transaction = new Transaction();
		transaction.setBook(book);
		transaction.setMember(member);
		transaction.setDateOfIssue(LocalDateTime.now());
		return ResponseEntity.ok().body(transactionService.save(transaction));
	}

	/*
	 * PLEASE DO NOT CHANGE SIGNATURE OR METHOD TYPE OF END POINTS
	 */
	@PatchMapping(path = "/api/transaction/{transaction-id}/return")
	public ResponseEntity<Transaction> returnBookTransaction(
			@PathVariable(name = "transaction-id") Long transactionId) {
		Transaction transaction = transactionService.findById(transactionId);
		if (transaction == null) {
			throw new Forbidden403Exception("Transaction not found");
		}

		if (transaction.getDateOfReturn() != null) {
			throw new Forbidden403Exception("Transaction not found");
		}

		transaction.setDateOfReturn(LocalDateTime.now());
		return ResponseEntity.ok().body(transactionService.save(transaction));
	}

}
