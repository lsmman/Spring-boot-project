package com.hello.hello_spring.controller;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
controller를 설명해놓으면 벌어지는 일
    스프링이 빌드되서 스프링 컨테이너가 뜰 때 이 controller라는 annotation이 있으면
    스프링에서 MemberController로 객체를 생성해서 controller로 들고있는다.
    그걸 스프링 컨테이너에서 bean으로써 (스프링땅콩) 관리된다고 말한다.
 */
@Controller
public class MemberController {
    // 요렇게 생성하면 종속될 뿐만 아니라 주문 등의 다른 컨트롤러에서 memberservice를 하나로 같이 쓸수가 없다.
    //    private final MemberService memberService = new MemberService();
    // 따라서 DI가 필요하다

    /*
        DI의 세가지 형태
            필드 인젝션 :
                @Autowired private final MemberService memberService;
                단점 객체로 bean에서 관리하기가 힘듬
            setter 인젝션 :
                public void setMemberService(MemberService memberService) {this.memberService = memberService;}
                단점 접근이 어려워야할 싱글톤 객체가 public으로 노출된다. 위험
            Constructor : 권장
     */

    private MemberService memberService;
    // Autowired가 있으면 스프링이 Controller를 생성할 때 memberService 객체를 스프링이
    // 컴포넌트 스킨으로 생성해서 스프링 컨테이너가 가지고 있게 된다.
    // 그치만 그냥 실행하면 에러가 난다. 왜?
    // 스프링이 bean에 memberService를 등록할 일이 없었기 때문에 없다고 나온다.
    // 그래서 @Service와 @Repository로 bean에게 알려줘야한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // createMemberForm의 form action에서 post method로 /members/new로 보내면 요 메소드 실행
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());


        memberService.join(member);

        // redirect를 넣어주면 이동한다.
        return "redirect:/";
    }
    
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
