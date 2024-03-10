package hello.springmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    //요청 파라미터와 다르게 Http body를 통해서 들어오는 데이터는 @RequestParam이나 @ModelAttribute를
    //쓸수가 없다.

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",message);

        response.getWriter().write("ok");
    }

    //HttpServletRequest -> InputStream
    //HttpServletResponse -> Writer
    //servlet에 관한 코드가 아니기 때문에 다음과 같이 가능함 굳이 통으로 servlet에 관한 정보가 필요가 없음
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",message);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        //httpmessageconverter라는 놈이 위에 streamutils가 하던 역할을 자동으로 해준다
        String message = httpEntity.getBody();
        log.info("messageBody={}",message);
//        return new HttpEntity<>("ok");
        //상태코드 추가 가능
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }


    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String message) {
        log.info("messageBody={}",message);
        return "ok";
    }


}
