package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        // 의존 관계가 구현까지 의존함
        // 추상화와 구체화 모두 의존하는 단점이 있음

        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.joinMember(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName()) ;
        System.out.println("findMember = " + findMember.getName());

    }
}
