package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);

        PrintWriter writer = response.getWriter();
        writer.write("ok");
    }

    @ResponseBody //원래 지금 class는 @Controller어노테이션 땜에 return값이 string이면
    //논리이름으로 viewResolver가 view를 찾을려고 하는데 @ResponseBody어노테이션을 적어주면
    // 그냥 httpMethod body에다가 박아넣고 끝내버림
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("memberName={}, memberAge={}",memberName,memberAge);
        return "ok";
    }



    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age) {
        //Stringm int, integer같은 단순 타입이면 @RequestParam 도 생략 가능,
        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        //age를 안보낼때는 internal 500에러가 남
        //왜냐하면 자바의 int 형은 null을 받을수가 없어서 그럼
        //따라서 null을 받게 하려면 integer를 써줘야함
        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true,defaultValue = "guest") String username,
            @RequestParam(required = false,defaultValue = "-1") int age) {
        //defaultValue가 있으면 required의 존재가 무의미해짐 어차피 다됨
        //빈문자의 경우에도 defaultValue로 매핑을 해줌
        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap) {
        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }




}
