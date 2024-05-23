package org.example.beforeLogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.util.CustomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeforeLoginService {

    private final BeforeLoginRepository beforeLoginRepository;
    private final CustomUtil customUtil;

    public boolean existByUsername(String username) {
        return beforeLoginRepository.existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return beforeLoginRepository.existsByEmail(email);
    }

    public void registerUser(MemberDTO memberDTO) {
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(memberDTO, memberVO);
        beforeLoginRepository.save(memberVO);
    }

    public MemberDTO findByEmail(String email) {
        MemberVO memberVO = beforeLoginRepository.findByEmail(email);
        if (memberVO == null) {
            return null;
        }
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberVO, memberDTO);
        return memberDTO;
    }

    public boolean findByUsernameAndEmail(String username, String email) {
        MemberVO memberVO = beforeLoginRepository.findByUsernameAndEmail(username, email);
        if (memberVO == null) {
            return false;
        }
        String tempPassword = customUtil.createTemporaryPassword(15);
        memberVO.setPassword(tempPassword);
        beforeLoginRepository.save(memberVO);
        return true;
    }
}
