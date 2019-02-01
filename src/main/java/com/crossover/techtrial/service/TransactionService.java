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
	
	public Transaction findById(Long transactionId);
	
	public Transaction findBookByBookAndMember(Long bookId, Long memberId);

	public List<Transaction> findAllTransaction(Long bookId, Long memberId);

}
