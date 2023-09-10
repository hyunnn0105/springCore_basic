package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 의존 관계가 구현까지 의존함
        // 추상화와 구체화 모두 의존하는 단점이 있음
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = new MemberServiceImpl();
        // MemberService memberService = appConfig.memberService();

        // appconfig 설정정보를 가지고 객체생성을 등록해서 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.joinMember(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName()) ;
        System.out.println("findMember = " + findMember.getName());

    }
}
