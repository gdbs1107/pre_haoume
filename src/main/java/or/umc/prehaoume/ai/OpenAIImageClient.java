
package or.umc.prehaoume.ai;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openaiImageClient", url = "https://api.openai.com/v1/images")
public interface OpenAIImageClient {

    @PostMapping(value = "/generations", consumes = "application/json")
    ImageResponse generateImage(@RequestHeader("Authorization") String authHeader,
                                @RequestBody ImageRequest request);
}
