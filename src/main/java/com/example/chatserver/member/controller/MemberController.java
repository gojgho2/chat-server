package com.example.chatserver.member.controller;

import com.example.chatserver.common.auth.JwtTokenProvider;
import com.example.chatserver.member.domain.Member;
import com.example.chatserver.member.dto.MemberLoginReqDto;
import com.example.chatserver.member.dto.MemberSaveReqDto;
import com.example.chatserver.member.service.MemberService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/create")
  public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto dto) {
    Member member =  memberService.create(dto);
    return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> doLogin(@RequestBody MemberLoginReqDto dto) {
    // email, password validation
    // is ok
    Member member = memberService.login(dto);
    // 일치할 경우 accessToken 발급
    String token = jwtTokenProvider.createToken(member.getEmail(), member.getRole().name());

    Map<String, Object> loginInfo = new HashMap<>();
    loginInfo.put("id", member.getId());
    loginInfo.put("token", token);

    return new ResponseEntity<>(loginInfo, HttpStatus.OK);


  }
}
