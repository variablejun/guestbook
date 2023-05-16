package kr.ac.kopo.guestbook.service;

import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.dto.PageResultDTO;
import kr.ac.kopo.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService service;

    @Test
    public  void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("userSample")
                .build();

        System.out.println(service.register(guestbookDTO));

    }


    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> requestDTO = service.getList(pageRequestDTO);

        System.out.println("prev : " + requestDTO.isPrev());
        System.out.println("next : " + requestDTO.isNext());
        System.out.println("total : " + requestDTO.getTotalPage());
        System.out.println("=================================");

         for (GuestbookDTO guestbookDTO : requestDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        requestDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
