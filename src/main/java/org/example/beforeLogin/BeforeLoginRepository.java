package org.example.beforeLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeforeLoginRepository extends JpaRepository<MemberVO, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    MemberVO findByEmail(String email);
    MemberVO findByUsernameAndEmail(String username, String email);
}
