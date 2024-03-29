package com.example.selfstudy.controller;


import com.example.selfstudy.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
    @GetMapping("/ex1")
    public void ex1() {
        log.info("ex1 ============== ");
    }

    @GetMapping({"/ex2"})
    public void exModel1(Model model) {
        List<SampleDTO> list = IntStream.rangeClosed(1,20).asLongStream().
                mapToObj(i-> {
                            SampleDTO Dto = SampleDTO.builder()
                                    .sno(i)
                                    .first("First = " + i)
                                    .last("last = " + i)
                                    .regTime(LocalDateTime.now())
                                    .build();

                            return Dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes) {

        log.info("exInline =========");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First..100")
                .last("Last..100")
                .regTime(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto",dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3() {
        log.info("ex3");
    }

    @GetMapping({"/ex2", "exLink"})
    public void exModel2(Model model) {
        List<SampleDTO> sampleDTOList = IntStream.rangeClosed(1,10).asLongStream()
                .mapToObj( i -> {
                    SampleDTO dto = SampleDTO.builder()
                            .sno(i)
                            .first("처음 .." + i)
                            .last("마지막.." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return dto;
                }).collect(Collectors.toList());

        model.addAttribute("sampleDTOList", sampleDTOList);
    }
}
