package com.estsoft.springprojectblogexam;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @Test
    public void test() {
        // given - test에 필요한 기본 값을 설정
        int a =1;
        int b =2;

        // when - 검증하고 싶은 메소드(코드) 호출
        int sum = a + b;

        // then - when절의 실행 결과를 검증 Assertions 클래스 사용.

        // JUnit - 기대값과 실제값이 잘 구분되지 않는 단점이 있다.
        //Assertions.assertEquals(3,sum); //(기대값, 실제값)

        // AssertJ 사용.가독성이 좋다.
        Assertions.assertThat(sum).isEqualTo(3);
    }
}
