package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
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

        // componentScan basePackage
        // - 따로 설정된 위차가 없다면 @ComponentScan이 붙은 위치부터 탐색한다.
        // - basePackage로 시작위치를 지정해 줄 수 있음
        // - 프로젝트 시작 루트에 메인 설정정보응 지정하는 곳 ex) com.hello 에서 @ComponentScan을 붙여 basePackage를 따로 지정하진 않음

        // ** annotaion에는 상속관계가 없다.
        // annotaion이 특정 annotation을 들고 있는 것을 인식할 수 있는 것은 스프링에서 지원해주는 기능임

        // + 컴포넌트 스캔과 더불어 특정 애노테이션의 부가 기능을 수행함
        // @Component, @Controller, @Service, @Repository, @Configration

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

    }
}