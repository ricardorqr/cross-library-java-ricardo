/**
 * 
 */
package com.crossover.techtrial.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author kshah
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8951221480021840448L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	Book book;

	@OneToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	Member member;
	// Date and time of issuance of this book
	@Column(name = "date_of_issue")
	LocalDateTime dateOfIssue;

	// Date and time of return of this book
	@Column(name = "date_of_return")
	LocalDateTime dateOfReturn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDateTime getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDateTime dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public LocalDateTime getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(LocalDateTime dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", book=" + book + ", member=" + member + ", dateOfIssue=" + dateOfIssue
				+ ", dateOfReturn=" + dateOfReturn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
		result = prime * result + ((dateOfReturn == null) ? 0 : dateOfReturn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (dateOfIssue == null) {
			if (other.dateOfIssue != null)
				return false;
		} else if (!dateOfIssue.equals(other.dateOfIssue))
			return false;
		if (dateOfReturn == null) {
			if (other.dateOfReturn != null)
				return false;
		} else if (!dateOfReturn.equals(other.dateOfReturn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}

}
