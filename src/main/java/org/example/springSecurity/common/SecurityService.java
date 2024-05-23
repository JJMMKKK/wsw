package org.example.springSecurity.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.beforeLogin.MemberDTO;
import org.example.beforeLogin.MemberVO;
import org.example.member.user.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberVO memberVO = memberRepository.findByUsername(username);
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberVO, memberDTO);
        log.info("로그인 유저 정보 {}", memberDTO);

        return new SecurityUser(memberDTO);
    }
}