package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // beanDefinitionNames = appConfigbeanDefinition = Generic bean: class [hello.core.AppConfig$$EnhancerBySpringCGLIB$$a0235ce1];
    // scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true;
    // primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null

    // java에 spring bean을 생성하는 방법
    // 1. 직접 증록하는 방법
    // 2. java code를 통해 등록하는 방법 - 팩토리빈 통해서 등록해주는 방법
    @Test
    @DisplayName("빈 메타정보 확인")
    void findApplicationBean(){
        String[] beanDetfinitonNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDetfinitonNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == beanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionNames = " + beanDefinitionName
                    + "beanDefinition = " + beanDefinition);
            }
        }
    }
}
