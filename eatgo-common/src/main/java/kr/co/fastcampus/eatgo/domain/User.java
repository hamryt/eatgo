package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Setter
    private String email;

    @NotEmpty
    @Setter
    private String name;

    @NotEmpty
    private String password;

    @NotNull
    @Setter
    private Long level;

    public boolean isAdmin() {
        return level>=3;
    }

    public boolean isActive() {
        return level>0;
    }

    public void deactivate() {
        level =0L;
    }

}
