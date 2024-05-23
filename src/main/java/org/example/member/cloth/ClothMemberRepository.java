package org.example.member.cloth;

import org.example.beforeLogin.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothMemberRepository extends JpaRepository<MemberVO, Long> {
    MemberVO findByUsername(String username);
}
