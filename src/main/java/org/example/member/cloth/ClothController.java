package org.example.member.cloth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClothController {

    private final ClothService clothService;

    @PostMapping("/viewAllClothes")
    public List<ClothDTO> viewAllClothes(Principal principal) {
        String username = principal.getName();
        return clothService.findAllByUserId(username);
    }

    @PostMapping("/deleteCloth")
    public void deleteCloth(Principal principal, int clothId) {
        String username = principal.getName();
        clothService.deleteById(username, clothId);
    }

    @PostMapping("/createNewCloth")
    public void createNewCloth(Principal principal, ClothDTO clothDTO) {
        String username = principal.getName();
        clothService.createNewCloth(username, clothDTO);
    }

    //GPT 질문용 전체 옷 출력
    @PostMapping("/forGPTClothes")
    public List<CQ> forGPTClothes(Principal principal) {
        String username = principal.getName();
        return clothService.forGPTClothes(username);
    }



}
