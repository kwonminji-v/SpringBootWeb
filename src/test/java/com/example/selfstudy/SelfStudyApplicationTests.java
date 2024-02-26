package com.example.selfstudy;

import com.example.selfstudy.entity.Memo;
import com.example.selfstudy.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class SelfStudyApplicationTests {


    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertFakeData() {
        IntStream.rangeClosed(1,50).forEach(i -> {
            Memo memo = Memo.builder().memoText("샘플 데이터 ..." + i).build();
            memoRepository.save(memo);
        });
    }

}
