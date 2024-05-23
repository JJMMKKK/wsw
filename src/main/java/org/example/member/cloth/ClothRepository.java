package org.example.member.cloth;

import org.example.beforeLogin.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<ClothVO, Long>{

    List<ClothVO> findAllByMemberVO(MemberVO memberVO);

    void deleteByIdAndMemberVO(int clothId, MemberVO memberVO);
}
