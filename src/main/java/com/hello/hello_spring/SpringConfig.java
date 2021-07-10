package com.hello.hello_spring;

import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /*
    멤버서비스와 멤버레포지터리가 config로 빈에 등록이 되게 된다.
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    /*
    요렇게 bean으로 지정해놓으면 DB가 교체되더라도
    다른거 바꿀 필요 없이 new MemoryMemberRepo만 DBMemberRepo로 바꿔주면 된다.
     */
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
