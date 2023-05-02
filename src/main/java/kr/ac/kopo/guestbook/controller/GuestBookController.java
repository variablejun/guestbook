package kr.ac.kopo.guestbook.controller;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@Log4j2
public class GuestBookController {


    @GetMapping({"/","/list"})
    public String list(){
        log.info("방명록 목록 화면");
        return "/guestbook/list";
    }
}
