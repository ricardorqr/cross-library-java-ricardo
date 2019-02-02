/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.dto.TopMemberDTO;
import com.crossover.techtrial.model.Member;

/**
 * Person repository for basic operations on Person entity.
 * 
 * @author crossover
 */
@RestResource(exported = false)
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

	Optional<Member> findById(Long id);

	Optional<Member> findByEmail(String email);

	List<Member> findAll();

	@Query(value = " select new com.crossover.techtrial.dto.TopMemberDTO(t.member.id, "
			+ " t.member.name, t.member.email, count(t.id)) "
			+ " from Transaction t "
			+ " where t.dateOfIssue = :startTime and t.dateOfReturn = :endTime "
			+ " group by t.member.id, t.member.name, t.member.email ", nativeQuery = false)
	List<TopMemberDTO> findAllTop5Members(@Param("startTime") LocalDateTime dateOfIssue, @Param("endTime") LocalDateTime dateOfReturn);

}
