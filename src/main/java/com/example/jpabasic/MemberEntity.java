package com.example.jpabasic;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Member")
public class MemberEntity {

    @Id // PK 매핑
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // JAVA ENUM TYPE 사용 시 (무조건 STRING 사용을 해야함)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 TYPE 사용 시
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // DATETIME 최신 버전은 LocalDate, LocalDateTime 사용하면 됨.

    @Lob // CLOB, BLOB 등 varchar 를 넘어선 큰 컨텐츠를 넣고 싶을 경우에 사용함.
    private String description;

    @Transient // Memory에서만 사용하는 의미
    private int temp;

    public MemberEntity() {
    }

    public MemberEntity(Long id, String name) {
        this.id = id;
        this.username = name;
    }
}