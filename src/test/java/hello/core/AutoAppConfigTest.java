package hello.core;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


class AutoAppConfigTest {

    @Test
    void basicScan(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);

        // - 컴포넌트 스캔
        // - @ComponentScan은 @Component가 붙은 모든 클래스를 스프링빈으로 등록한다.
        // - 스프링빈의 기본 이름은 클래스명을 사용하되 맨 앞글자를 소문자로 사용한다. (빈 이름 지정도 가능)

        // - @Autowired
        // - 생성자에 @Autowired를 지정하면 스프링컨테이너가 자동으로 해당 스프링빈을 찾아서 주입함
        // - 타입이 같은 빈을 등록해서 사용한다.
        // - 생성자의 파라미터가 많아도 찾아서 등록해준다.

    }
}