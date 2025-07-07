package kr.kiomn2.kiomnd2splearn.domain;

import java.util.regex.Pattern;

public record Email(String address) {
    public Email {
        Pattern EMAIL_PATTERN =
                Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("Invalid email" + address);
        }
    }
}
