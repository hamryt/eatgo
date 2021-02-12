package kr.co.fastcampus.eatgo.application;

public class PasswordWrongExepcion extends RuntimeException{
    PasswordWrongExepcion(){
        super("Password is wrong");
    }
}
