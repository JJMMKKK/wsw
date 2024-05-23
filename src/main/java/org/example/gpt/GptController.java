package org.example.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GptController {

    private final GptService gptService;

    @PostMapping("/askToGpt")
    public String askToGpt(List<Object> questionData) throws JsonProcessingException {

        String dustData = questionData.get(0).toString();
        String weatherData = questionData.get(1).toString();
        String clothData = questionData.get(2).toString();
        String username = questionData.get(3).toString();

        String question =
            "1.오늘의 날씨를 보고 입을 옷을 옷 데이터 중에서 골라 " +
            "모자, 겉옷, 상의, 하의, 양말, 신발이 서로 조화가 잘 되도록 골라 " +
            "모자, 겉옷은 제외 가능 " +
            "상의, 하의, 양말, 신발는 필수 포함 " +
            "2.미세먼지 상태를 보고 마스크 착용 여부 출력 " +
            "3.답변은 List<map>형태로 출력 " +
            "4.답변은 어떤 설명도 없이 데이터만 출력 " +
            "5.답변은 어떤 공백, 줄바꿈도 없어야 함" +
            "ex: [" +
            "{\"모자\": \"다채로운 블랙 볼캡\", \"상의\": \"화이트 심플한 캐주얼한 티셔츠\", \"하의\": \"진청 데일리한 청바지\", \"양말\": \"화이트 베이직한 스니커즈 양말\", \"신발\": \"화이트 심플한 캔버스 스니커즈\"}," +
            "{\"미세먼지(PM10)\": \"보통 (48)\", \"초미세먼지(PM2.5)\": \"좋음 (15)\", \"마스크 착용 여부\": \"없음\"}\n" +
            "]" +
            "오늘의 날씨: " + weatherData +
            " 오늘의 미세먼지: " + dustData +
            " 옷 데이터: " + clothData;
        log.info("question {}", question);

        //JSON 형태의 예시 데이터
        //String answer = "[{\"상의\":\"화이트 심플한 캐주얼한 티셔츠\",\"하의\":\"진청 데일리한 청바지\",\"양말\":\"화이트 베이직한 스니커즈 양말\",\"신발\":\"화이트 심플한 캔버스 스니커즈\"},{\"미세먼지(PM10)\":\"보통 (48)\",\"초미세먼지(PM2.5)\":\"좋음 (15)\",\"마스크 착용 여부\":\"필수\"}]";

        //GPT 답변
        String answer = gptService.getGptResponse(question);

        //GPT 질문 및 답변 저장
        GptanswervoDto gptanswervoDto = new GptanswervoDto(username, question, answer);
        gptService.saveAnswer(gptanswervoDto);

        return answer;
    }





    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

}
