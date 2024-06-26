package org.example.member.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.beforeLogin.BeforeLoginRepository;
import org.example.beforeLogin.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BeforeLoginRepository beforeLoginRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void withdrawUser(String username) {
        boolean logined = beforeLoginRepository.existsByUsername(username);
        if(logined){
            memberRepository.deleteByUsername(username);
        }
    }

    public boolean updatePassword(String username, String password) {
        MemberVO memberVO = memberRepository.findByUsername(username);
        if (memberVO != null) {
            memberVO.setPassword(passwordEncoder.encode(password));
            memberRepository.save(memberVO);
            return true;
        }
        return false;
    }
}
