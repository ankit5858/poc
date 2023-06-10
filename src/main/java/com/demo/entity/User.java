package com.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Set;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name", unique = true, nullable = false, length = 50, updatable = false)
	private String userName;
	@Column(unique = true, nullable = false, length = 100, updatable = false)
	private String email;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "account_non_expired", nullable = false)
	private Boolean accountNonExpired;
	@Column(name = "account_non_locked", nullable = false)
	private Boolean accountNonLocked;
	@Column(name = "credentials_non_expired", nullable = false)
	private Boolean credentialsNonExpired;
	@Column(name = "email_verification", nullable = false)
	private Boolean emailVerification;
	@Column(nullable = false)
	private Boolean enable;
	@Column(name = "service_access")
	private Boolean serviceAccess;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;
	@Column(length = 80)
	@JsonIgnore
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<ImgData> imgData;
	
}
