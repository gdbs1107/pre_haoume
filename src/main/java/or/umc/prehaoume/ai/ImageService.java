package or.umc.prehaoume.ai;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final OpenAIImageClient openAIImageClient;
    @Value("${openai.api-key}")
    private String apiKey;

    public byte[] createImage(String prompt) {
        ImageRequest request = new ImageRequest();
        request.setPrompt(prompt);
        request.setModel("gpt-image-1");

        try {
            ImageResponse response = openAIImageClient.generateImage("Bearer " + apiKey, request);

            if (response.getData() == null || response.getData().isEmpty()) {
                log.error("OpenAI 응답 데이터가 비어 있음");
                return null;
            }

            String b64 = response.getData().get(0).getB64_json();
            byte[] imageBytes = Base64.getDecoder().decode(b64);

            // 저장 경로 설정
            String directoryPath = "/Users/jeonjaeyeon/Desktop/sopt/houme_ai_prac";
            String fileName = UUID.randomUUID() + ".png"; // 고유한 파일 이름
            File file = new File(directoryPath, fileName);

            // 디렉터리 없으면 생성
            file.getParentFile().mkdirs();

            // 파일 저장
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageBytes);
                log.info("이미지 파일 저장 성공");
            } catch (IOException e) {
                log.error("이미지 파일 저장 실패", e);
            }

            return imageBytes;
        } catch (FeignException e) {
            log.error("OpenAI 호출 실패: status={}, content={}", e.status(), e.contentUTF8());
            return null;
        }
    }






}
