package kr.kiomn2.kiomnd2splearn.domain;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
