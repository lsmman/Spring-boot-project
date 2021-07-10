package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 네이밍도 join 처럼 서비스스럽게 한다.
// repo 같은 클래스는 add 같이 개발스러운 용어를 쓴다.
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /*
     Dependency injection (DI)

     - MemberService에서 직접 만들지 않고 외부에서 memoryRepo 객체를 받아서 넣는 것

     의존성 주입의 장점
        코드의 재활용성을 높여준다.
        객체 간의 의존성(종속성)을 줄이거나 없엘 수 있다.
        객체 간의 결합도이 낮추면서 유연한 코드를 작성할 수 있다.

     참고 : https://velog.io/@wlsdud2194/what-is-di
     */

    // memberService가 bean으로 생성될 때 memberRepo가 필요하기 때문에 autowired를 넣어준다.
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /*
    회원가입
     */

    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 x

//        // 옵셔널을 result로 객체로 만드는 것도, get으로 직접 꺼내는 것도 권장하지 않고 아래 와 같은 문법을 쓴다.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.")
//        });

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById((memberId));
    }

}
