package com.example.jpabasic.jpql;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
