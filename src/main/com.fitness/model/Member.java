package com.fitness.model;

public class Member {
    private String memberId;
    private String name;
    private String contact;
    private String email;
    private String gender;
    private int age;
    private String address;

    public Member() {}

    public Member(String memberId, String name, String contact, String email, String gender, int age, String address) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return memberId + "," + name + "," + contact + "," + email + "," + gender + "," + age + "," + address;
    }
}