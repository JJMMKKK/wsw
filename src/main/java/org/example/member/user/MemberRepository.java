package org.example.member.user;

import org.example.beforeLogin.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, Long> {
    MemberVO findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
}
