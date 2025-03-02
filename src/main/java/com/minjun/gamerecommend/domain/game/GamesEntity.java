package com.minjun.gamerecommend.domain.game;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Table(schema = "game")
@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GamesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String goodsSeq;

    @Getter
    String goodsName;

    @Getter
    String goodsType;


}
