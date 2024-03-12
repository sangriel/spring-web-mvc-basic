package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data","hello");

        return mv;
    }


    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","hello2");

        return "response/hello"; //@Controller에 string 반환이면 논리 뷰 이름으로 알아서 viewResolver가 찾아줌
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data","hello2");
        return;
    }//매우 비 추천 하는 방식
    //@Controller를 사용하고, HttpServletResponse, OutputStream 같은 Http메시지 바디를 처리하는 파라미터가
    //없으면 요청 URL을 논리뷰 이름으로 사용
}
