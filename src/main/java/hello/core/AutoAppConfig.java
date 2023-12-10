package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @ComponentScan 사용시 @Configuration이 붙은 설정 정보도 자동으로 등록하기 때문에 이전에 생성한 설정정보들이 실행될 수 있어서 이전정보들을 제외시켜줌
// @Bean으로 등록한 클래스가 하나도 없다.
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 자동빈과 수동빈이 출돌날 경우 수동등록빈이 우선원을 가진다.
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
