package com.example.selfstudy;

import com.example.selfstudy.entity.Memo;
import com.example.selfstudy.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class SelfStudyApplicationTests {


    @Autowired
    MemoRepository memoRepository;

    @Test
    public void getselectQuery() {


        List<Memo> memo = memoRepository.getListdesc();

        System.out.println(memo);

    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemosByMnoLessThan(10L );
    }

    @Test
    public void testQueryMethodWithPageable() {

        Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L,50L,pageable);
        result.get().forEach(memo -> System.out.println(memo));
    }

    @Test
    public void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {

        Sort sort1 = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.forEach(memo -> {
            System.out.println(memo);
        });
    }

    //1페이지 10개씩
    @Test
    public void testPageDefault() {

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println();
        System.out.println("========================================================");
        System.out.println("Total page(총 몇 페이지) = " + result.getTotalPages());
        System.out.println("Total Count(총 몇개) = " + result.getTotalElements());
        System.out.println("Page Number (현재 페이지 번호) = " + result.getNumber());
        System.out.println("Page Size (페이지당 데이터 갯수) = " + result.getSize());
        System.out.println("Has Next Page (다음페이지 존재 여부)" + result.hasNext());
        System.out.println("First page (시작페이지 0 여부) = " + result.isFirst());
        System.out.println();
        System.out.println("============================");

        System.out.println();

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }

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

    @Test
    public void testInsertFakeData() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("샘플 데이터..." + i).build();
            memoRepository.save(memo);
        });
    }

}
