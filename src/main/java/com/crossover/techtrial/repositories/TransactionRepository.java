/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Transaction;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	public List<Transaction> findAllTransactionByBookAndDateOfIssueIsNotNull(Long bookId);

	public List<Transaction> findfindAllTransactionAllByBookAndMember(Long bookId, Long memberId);

}
