package kr.kiomn2.kiomnd2splearn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class Kiomnd2SplearnApplicationTest {

    @Test
    void run() {
        MockedStatic<SpringApplication> mocked = Mockito.mockStatic(SpringApplication.class);

        Kiomnd2SplearnApplication.main(new String[0]);

        mocked.verify(() -> SpringApplication.run(Kiomnd2SplearnApplication.class, new String[0]));
    }
}