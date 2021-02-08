package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor // 변수가 하나도 없으면 전부가 하나도 없는거라서 @NoArgsContructor 이랑 같은 생성자를 만든게 된다. 그래서 변수 하나도 없을때는 오류뜸
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer score;

    @NotEmpty
    private String description;

    @Setter
    private Long restaurantId;

}
