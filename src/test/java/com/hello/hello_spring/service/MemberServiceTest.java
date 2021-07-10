package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    // 일반적인 경우에서는 afterEach에서 클리어가 되지 않는다.
    // new를 실행하게 되면 memberService에서 사용하는 memoryMemberRepo와 다른 객체가 생성된다.
    // 이번 경우 memberService에 가보면 static으로 memoryrepo를 만들어서 가능하다.
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    // 각 테스트가 각자의 memory를 사용하도록 처리
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // Dependency injection
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
//    테스트 코드는 한글로 해도 된다.
//    test의 머리 가슴 배
//        //given
//
//        //when
//
//        //then

    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(member.getName());
    }

    @Test
    public void 중복_회원_조회() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

//        // try catch 문이 애매하면?
//        // when
//        // then
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        }
//        catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}