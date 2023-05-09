package kr.ac.kopo.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.guestbook.entity.Guestbook;
import kr.ac.kopo.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
        @Autowired
        private GuestBookRepository guestBookRepository;
        // Querydsl을 사용해서 특정 검색어를 갖는 행들만 선택

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

        @Test
        public void updateTest(){
            Optional<Guestbook> result = guestBookRepository.findById(300L);

            if(result.isPresent()){
                Guestbook guestbook = result.get();
                guestbook.changeTitle("Change Title...");
                guestbook.changeContent("Change Content...");
                guestBookRepository.save(guestbook);
            }

        }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "7";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = guestBookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook ->{
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0, 10,Sort.by("gno").ascending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "7";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expTitle = qGuestbook.title.contains(keyword);
        BooleanExpression expWriter = qGuestbook.writer.contains(keyword);
        BooleanExpression expAll =  expTitle.or(expWriter);
        builder.and(expAll);
        builder.and(qGuestbook.gno.gt(50L));

        Page<Guestbook> result = guestBookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook ->{
            System.out.println(guestbook);
        });
    }
}
