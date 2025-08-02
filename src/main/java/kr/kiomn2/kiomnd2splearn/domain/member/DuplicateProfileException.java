package kr.kiomn2.kiomnd2splearn.domain.member;

public class DuplicateProfileException extends RuntimeException {
    public DuplicateProfileException(String message) {
        super(message);
    }
}
