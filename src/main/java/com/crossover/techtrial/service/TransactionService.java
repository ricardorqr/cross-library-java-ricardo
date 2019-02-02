/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.model.Transaction;

/**
 * BookService interface for Books.
 * 
 * @author cossover
 *
 */
public interface TransactionService {

	public Transaction save(Transaction transaction);

	public Transaction findTransactionById(Long transactionId);

	public List<Transaction> findAllTransactionByBook(Long bookId);

	public List<Transaction> findAllTransactionByBookAndMember(Long bookId, Long memberId);

}
