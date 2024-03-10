package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j //아래서 Logger를 따로 선언해줄 필요가 없음
//@Controller 는 return을 view로 받아들임
@RestController // return값을 http method body에 그냥 강제 주입해버림
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //trace debug를 보려면 application.properties에서 설정하면 됨
        //아래로 내려갈수록 등급이 더 높아짐
        //운영은 info이상으로 depth를 설정함
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);


//        log.info("info log=" + name);
//        이렇게 할경우 자바언어의 특성상 info log=Spring이라흔 문자열 더하기 연산이 한번 일어나버림
        // 따라서 실제로는 로그가 찍히지 않는데도 리소스가 쓰이는 상황이 발생
        //반면 ("",name) -> 이건 그냥 매개변수로 넘기는거라 안에 예외처리 발생하면서 리소스 낭비를 안함
        return "ok";
    }
}
