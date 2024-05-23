package org.example.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GptService {

    @Value("${openai.secret-key}")
    private String secretKey;

    private final GptRepository gptRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String getGptResponse(String question) throws JsonProcessingException {
        GptQuestionDto gptQuestionDto = new GptQuestionDto(question);

        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(secretKey);
            headers.add("Content-Type", "application/json");
        Map<String, Object> map = new HashMap<>();
            map.put("model", gptQuestionDto.getModel());
            map.put("messages", Collections.singletonList(Map.of("role", "user", "content", gptQuestionDto.getPrompt())));
            map.put("temperature", gptQuestionDto.getTemperature());
            map.put("max_tokens", gptQuestionDto.getMax_tokens());

        String requestJson = objectMapper.writeValueAsString(map);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        //ChatGPT에서 받은 json 데이터 중 답변만 출력
        JsonNode node = objectMapper.readTree(response.getBody());
        String content = node.path("choices").get(0).path("message").path("content").asText();
        log.info("Gpt response: {}", content);
        return content;
    }

    public void saveAnswer(GptanswervoDto gptanswervoDto) {
        Gptanswervo gptanswervo = new Gptanswervo();
        BeanUtils.copyProperties(gptanswervoDto, gptanswervo);
        gptRepository.save(gptanswervo);
    }
}
