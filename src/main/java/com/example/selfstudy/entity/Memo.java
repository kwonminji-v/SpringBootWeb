package com.example.selfstudy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
