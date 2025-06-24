package or.umc.prehaoume.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generate(@RequestParam String prompt) {
        byte[] image = imageService.createImage(prompt);

        if (image == null) {
            throw new IllegalStateException("이미지 생성 실패");
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

}
