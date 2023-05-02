package kr.ac.kopo.guestbook.repository;

import kr.ac.kopo.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
        @Autowired
        private GuestBookRepository guestBookRepository;
        @Test
        public void insertDummies(){
            IntStream.rangeClosed(1, 300).forEach(i ->{
                Guestbook guestBook = Guestbook.builder()
                        .title("Title..." + i)
                        .content("Content..." + i)
                        .writer("user" + (i % 10))
                        .build();
                System.out.println(guestBookRepository.save(guestBook));
            });
        }
}
