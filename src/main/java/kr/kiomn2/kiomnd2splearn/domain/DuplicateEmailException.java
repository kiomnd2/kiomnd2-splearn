package kr.kiomn2.kiomnd2splearn.domain;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
