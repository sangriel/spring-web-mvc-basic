package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message={}",message);
        HelloData helloData = objectMapper.readValue(message, HelloData.class);
        log.info("helloData={}",helloData);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String message) throws IOException {
        log.info("message={}",message);
        HelloData helloData = objectMapper.readValue(message, HelloData.class);
        log.info("helloData={}",helloData);
        return "ok";
    }


    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        //@RequestBody를 생략하면 @ModelAttribute로 자동으로 매핑됨
        //따라서 http method body가 아니라 requestparameter에서 정보를 뽑아내려고 시도함
        //HttpMessageConverter가 문자뿐만 아니라 JSON도 객체로 변환을 해줘서 가능함
        //HttpMessageConverter 사용 -> MappingJackson2HttpMessageconverter(content-type: application/json)
        log.info("helloData={}",helloData);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData={}",helloData);
        //HttpMessageConverter가 객체를 알아서 JSON으로 해서 내보냄
        return helloData;
    }


}
