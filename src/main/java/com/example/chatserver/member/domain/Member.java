package com.example.chatserver.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //db 와 sync 를 맞춘다.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 값 증가
  private Long id;

  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  private Role role = Role.USER; //디폴트 값 설정, db에 숫자값으로 들어가게되므로 STRING 설정




}
