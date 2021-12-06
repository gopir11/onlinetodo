package com.todo.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})
@Table(name = "TODO")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_DONE")
	private boolean done;

	@Column(name = "TARGET_DATE")
	private LocalDateTime targetDate;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false, referencedColumnName = "id")
	@JsonBackReference
	private User user;
}
