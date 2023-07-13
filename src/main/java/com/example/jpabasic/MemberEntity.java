package com.example.jpabasic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Member")
public class MemberEntity {

    @Id
    private Long id;

    private String name;

    public MemberEntity() {
    }

    public MemberEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
