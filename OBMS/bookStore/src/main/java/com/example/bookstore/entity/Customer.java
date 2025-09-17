package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;
@Column(unique = true)
private String email;
private String password; // store encoded password
private String fullName;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
}