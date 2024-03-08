package example.springexample;


import example.springexample.repository.MemberRepository;
import example.springexample.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MemberService;


//스프링빈을 직접 자바코드로 등록 (컴포넌트 스캔없이 @Service, @Repository @Autowiered 등 없이사용하는법)
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memoryMemberRepository());
    }


    @Bean
    public MemoryMemberRepository memoryMemberRepository(){
        return new MemoryMemberRepository();
    }
}
