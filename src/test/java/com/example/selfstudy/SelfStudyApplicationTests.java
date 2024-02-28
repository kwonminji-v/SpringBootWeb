package com.example.selfstudy;

import com.example.selfstudy.entity.Memo;
import com.example.selfstudy.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class SelfStudyApplicationTests {


    @Autowired
    MemoRepository memoRepository;

    //1페이지 10개씩
    @Test
    public void testPageDefault() {

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("========================================================");
        System.out.println("Total page(총 몇개) = " + result.getTotalPages());
        System.out.println("Total Count");
    }

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Transactional
    @Test
    public void testSelect2() {

        Long mno = 50L;

        Memo memo = memoRepository.getReferenceById(mno);

        System.out.println("=====================");

        System.out.println(memo);
    }

    @Test
    public void testDelete() {

        Long mno = 50L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testSelect() {

        //DB에 존재하는 MNO
        Long mno = 50L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("=================================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }


    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(50L).memoText("샘플 데이터 ...50").build();

        System.out.println(memoRepository.save(memo));
    }

//    @Test
//    public void testInsertFakeData() {
//        IntStream.rangeClosed(1,50).forEach(i -> {
//            Memo memo = Memo.builder().memoText("샘플 데이터 ..." + i).build();
//            memoRepository.save(memo);
//        });
//    }

}
