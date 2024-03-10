package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {


    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }


    //@PathVariable style
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        // 변수명을 맞추면 @PathVariable("userId")이런식으로 안써도 된다고 함
        log.info("mappingpath userId={}",userId);

        return "ok";
    }

    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPathOrders(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingpath userId={}, orderId={}",userId,orderId);
        return "ok";
    }

    //특정header만 해서도 가능함, headers = "mode=debug" 매개변수가 지원됨
    // key:mode, value:debug
    //mediaType 조건매핑
    // consumes = "application/json" -> 내가 받아들이는 contentType
    // produces -> 내가 반환할때 지정하는 contentType
    @GetMapping(value = "/mapping-param",params = "mode=debug")
    public String mappingParamModeDebug() {
        //query 에서 mode=debug가 없으면 아예 404가 떠버림
        log.info("mappingParam mode=debug");
        return "ok";
    }
}
