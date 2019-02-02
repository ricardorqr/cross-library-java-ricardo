/**
 * 
 */
package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.dto.TopMemberDTO;
import com.crossover.techtrial.exceptions.Unauthorized401Exception;
import com.crossover.techtrial.model.Member;
import com.crossover.techtrial.service.MemberService;

/**
 * 
 * @author crossover
 */

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	/*
	 * PLEASE DO NOT CHANGE SIGNATURE OR METHOD TYPE OF END POINTS
	 */
	@PostMapping(path = "/api/member")
	public ResponseEntity<Member> register(@RequestBody Member p) {
		/**
		 * Each member should have a valid unique email address. No two members can have
		 * the same email address.
		 */
		Member member = memberService.findEmail(p.getEmail());
		if (member == null) {
			throw new Unauthorized401Exception("Email used already");
		}

		/**
		 * Implement validation on name field in member table to allow names with the
		 * length of 2 to 100 and should always start with an alphabet. Please do not
		 * add validations on all other fields.
		 */
		if (p.getName().matches("^[A-I].*$")) {
			throw new Unauthorized401Exception("Name must start with an alphabet");
		}

		if (p.getName().length() < 2 || p.getName().length() < 100) {
			throw new Unauthorized401Exception("Name size must be between 2 and 100");
		}

		return ResponseEntity.ok(memberService.save(p));
	}

	/*
	 * PLEASE DO NOT CHANGE API SIGNATURE OR METHOD TYPE OF END POINTS
	 */
	@GetMapping(path = "/api/member")
	public ResponseEntity<List<Member>> getAll() {
		return ResponseEntity.ok(memberService.findAll());
	}

	/*
	 * PLEASE DO NOT CHANGE API SIGNATURE OR METHOD TYPE OF END POINTS
	 */
	@GetMapping(path = "/api/member/{member-id}")
	public ResponseEntity<Member> getMemberById(@PathVariable(name = "member-id", required = true) Long memberId) {
		Member member = memberService.findById(memberId);

		if (member != null) {
			return ResponseEntity.ok(member);
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * This API returns the top 5 members who issued the most books within the
	 * search duration. Only books that have dateOfIssue and dateOfReturn within the
	 * mentioned duration should be counted. Any issued book where dateOfIssue or
	 * dateOfReturn is outside the search, should not be considered.
	 * 
	 * DONT CHANGE METHOD SIGNATURE AND RETURN TYPES
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/member/top-member")
	public ResponseEntity<List<TopMemberDTO>> getTopMembers(
			@RequestParam(value = "startTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
			@RequestParam(value = "endTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime) {
		return ResponseEntity.ok(memberService.getTop5Members(startTime, endTime));

	}

}
