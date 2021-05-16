package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

@Embeddable     //값 타입을 정의하는 곳에 사용
public class Address {
    private String city;
    private String street;
    private String zipcode;
    // 기본 생성자가 있어야 한다.
    public Address() {
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }
}
