package com.sarvatra.jassert;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JAssertsTests {

    int addTwoNumber(int a, int b) {
        return a + b;
    }

    @Test
    @DisplayName(value = "useAssertWithNumbers")
    void testWithNumbers() {
        assertThat(9)
                .isEqualTo(addTwoNumber(4, 5))
                .isGreaterThan(3);
    }

    @Test
    void testWithStrings() {
        assertThat("hello")
                .startsWith("he")
                .endsWith("lo")
                .contains("ell");
    }

    @Test
    void testWithBooleans() {
        assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    void testWithLists() {
        assertThat(List.of("apple", "banana"))
                .contains("apple")
                .doesNotContain("orange")
                .hasSize(2);
    }

    int getDivision() {
        try {
            return 5 / 0;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("/ by 0");
        }
    }

    @Test
    void testWithExceptions() {
        assertThatThrownBy(() -> {
            getDivision();
        }).isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by 0");
    }

}




