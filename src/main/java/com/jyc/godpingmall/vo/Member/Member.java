package com.jyc.godpingmall.vo.Member;

public class Member extends UserDetailsImpl {

    public String getEmail() {
        return super.email;
    }

    public String getPassword() {
        return super.password;
    }
}
