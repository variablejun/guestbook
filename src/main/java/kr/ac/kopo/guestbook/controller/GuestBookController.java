package kr.ac.kopo.guestbook.controller;
import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestbookService service;
    @GetMapping("/")
    public String index(){
        return  "redirect:/guestbook/list";
    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("페이지 요청 정보: " + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
    // 등록화면을 보여줌 (get)
    @GetMapping("/register")
    public void register(){
        log.info("show register");
    }

    // 등록 처리 후에 목록페이지로 이동
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("등록처리 후 목록페이지 리다이렉트");
        Long gno = service.register(dto);

        return "redirect:/guestbook/list";
    }

}
