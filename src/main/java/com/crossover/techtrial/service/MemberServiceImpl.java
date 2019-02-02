/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopMemberDTO;
import com.crossover.techtrial.model.Member;
import com.crossover.techtrial.repositories.MemberRepository;

/**
 * @author crossover
 *
 */
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	public Member save(Member member) {
		return memberRepository.save(member);
	}

	public Member findById(Long memberId) {
		Optional<Member> member = memberRepository.findById(memberId);
		return member.orElse(null);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Member findEmail(String email) {
		Optional<Member> member = memberRepository.findByEmail(email);
		return member.orElse(null);
	}

	@Override
	public List<TopMemberDTO> getTop5Members(LocalDateTime startTime, LocalDateTime endTime) {
		return memberRepository.findAllTop5Members(startTime, endTime);
	}

}
