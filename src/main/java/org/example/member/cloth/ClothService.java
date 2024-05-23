package org.example.member.cloth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.beforeLogin.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClothService {

    private final ClothRepository clothRepository;
    private final ClothMemberRepository clothMemberRepository;

    public List<ClothDTO> findAllByUserId(String username) {

        MemberVO memberVO = checkLogined(username);

        List<ClothVO> clothVOList = clothRepository.findAllByMemberVO(memberVO);
        if (clothVOList == null || clothVOList.isEmpty()) {
            return null;
        }

        List<ClothDTO> clothDTOList = new ArrayList<>();
        ClothDTO clothDTO = new ClothDTO();
        for (ClothVO clothVO : clothVOList) {
            BeanUtils.copyProperties(clothVO, clothDTO);
            clothDTOList.add(clothDTO);
        }
        return clothDTOList;
    }


    public void deleteById(String username, int clothId) {

        MemberVO memberVO = checkLogined(username);
        clothRepository.deleteByIdAndMemberVO(clothId, memberVO);
    }


    public void createNewCloth(String username, ClothDTO clothDTO) {
        MemberVO memberVO = checkLogined(username);
        ClothVO clothVO = new ClothVO();
            BeanUtils.copyProperties(clothDTO, clothVO);
            clothVO.setMemberVO(memberVO);
        clothRepository.save(clothVO);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //GPT 질문용 전체 옷 출력
    public List<CQ> forGPTClothes(String username) {

        MemberVO memberVO = checkLogined(username);

        List<ClothVO> clothVOList = clothRepository.findAllByMemberVO(memberVO);
        if (clothVOList == null || clothVOList.isEmpty()) {
            return null;
        }

        List<CQ> cqs = new ArrayList<>();
        CQ cq = new CQ();
        for (ClothVO clothVO : clothVOList) {
            BeanUtils.copyProperties(clothVO, cq);
            cqs.add(cq);
        }

        return cqs;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Principal 확인
    public MemberVO checkLogined(String username){
        MemberVO memberVO = clothMemberRepository.findByUsername(username);
        if (memberVO == null) {
            return null;                                                            // 커스텀 에러 페이지========================
        }
        return memberVO;
    }

}
