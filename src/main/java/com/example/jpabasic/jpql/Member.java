package com.example.jpabasic.jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}

