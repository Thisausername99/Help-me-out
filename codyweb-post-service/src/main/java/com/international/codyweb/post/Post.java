package com.international.codyweb.post;


import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.international.codyweb.member.Member;

import lombok.*;

@Entity
@Table(name = "posts")

@Getter @Setter @NoArgsConstructor
public class Post extends AuditModel{

	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String title;

	@NotNull
	@Size(max = 250)
	private String category;


	@NotNull
	@Lob
	private String content;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mem_id", nullable = false)
	private Member member;




	public Post(String title, String category, String content) {
		this.title = title;
		this.category = category;
		this.content = content;
	}






	@Override
	public int hashCode() {
		return Objects.hash(id, member);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id) && Objects.equals(member, other.member);
	}



	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + member + "]";
	}


}
