package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
//    요게 그냥 실행되면 에러가 뜬다
//    왜냐하면 test func 끼리는 순서가 보장되지 않아서 spring1이라는 데이터가 중복으로 들어가 findbyname이 에러가 뜬다.
//    그래서 repo를 clear해주는 func이 필요
//    afterEach를 사용하여 clear 메소드를 넣어주자

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 한 테스트 func이 끝나면 afterall을 실행한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // optional에서 바로 get으로 꺼내는 건 좋은 방법이 아니다 왜? 직접 optional을 풀어주는 애네
        Member result = repository.findById(member.getId()).get();

        // // Junit 버전
        // import org.junit.jupiter.api.Assertions;
        // Assertions.assertEquals(member, result);

        // 요즘 많이 쓰는 assertj, 좀 더 편함
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName(member1.getName()).get();

        assertThat(result1).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // repository.findAll() ctrl+alt+V 누르면 자동으로 variable으로 만들어준다.
        
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
