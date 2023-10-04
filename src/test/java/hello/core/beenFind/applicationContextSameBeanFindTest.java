package hello.core.beenFind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class applicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findByBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findByBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    // static member class https://siyoon210.tistory.com/141
    // 내부 static class로 객체를 두 번 생성했디면 두개의 객체는 서로 다른 참조이다.
    // 클래스의 역할은 인스턴스를 만드는 '설계도'의 역할일뿐 , 그 자체가 인스턴스처럼 존재할 수 없다.
    // static이라는 키워드가 클래스에 붙게 된다면 인스턴스를 생성하는 방식이 달라지는 것이지 클래스가 갑자기 인스턴스의 역할을 하지 못하는건 아니다.
    //  static 선언이 없을 시 inner Class를 선언하는 경우 외부참조가 존재하기 때문에 내부클래스가 외부 클래스에 접근이 가능해진다.
    // '외부 참조'는 2가지 단점
    // 참조값을 담아야 하기 때문에, 인스턴스 생성시 시간적, 공간적으로 성능이 낮아진다.
    // 외부 인스턴스에 대한 참조가 존재하기 때문에, 가비지 컬렉션이 인스턴스 수거를 하지 못하여 메모리 누수가 생길 수 있다.
    // ==> 외부참조로 인한 단점으로 내부 클래스를 선언시에는 static 키워드를 붙여준다.
    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
