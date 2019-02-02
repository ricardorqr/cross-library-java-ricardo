/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.model.Transaction;
import com.crossover.techtrial.repositories.TransactionRepository;

/**
 * BookService interface for Books.
 * 
 * @author cossover
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction findById(Long transactionId) {
		Optional<Transaction> transaction = transactionRepository.findById(transactionId);
		return transaction.orElse(null);
	}

	@Override
	public Transaction findBookByBookAndMember(Long bookId, Long memberId) {
		Optional<Transaction> transaction = transactionRepository.findByBookAndMember(bookId, memberId);
		if (!transaction.isPresent() && transaction.get().getDateOfIssue() == null) {
			return null;
		} else {
			return transaction.get();
		}
	}

	@Override
	public List<Transaction> findAllTransaction(Long bookId, Long memberId) {
		return transactionRepository.findAllByBookAndMember(bookId, memberId);
	}

}
