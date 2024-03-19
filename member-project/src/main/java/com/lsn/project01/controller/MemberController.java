package com.lsn.project01.controller;

import com.lsn.project01.dto.MemberDTO;
import com.lsn.project01.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor // 의존성 주입을 위한 어노테이션 -> final로 선언한 객체만 생성자를 만듦
@RequestMapping("/member")
public class MemberController
{
    //서비스 클래스를 이용하기위해 의존성을 주입
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    //@modelAttribute 어노테이션은 requestParam과 같은기능을한다
    //DTO 객체와 연결
    public String save(@ModelAttribute MemberDTO memberDTO) {
        //저장한 정보를 정수값으로 반환받는다.
        //가입 성공시 login페이지로 , 실패시 save페이지로
        int saveResult = memberService.save(memberDTO);
        if(saveResult > 0) {
            return "login";
        } else {
            return "save";
        }
    }

    //로그인 페이지를 띄우는 메소드
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    //로그인 처리를 하는 메소드 -> 사용자가 입력한 이메일 비밀번호를 받기 위해 modelAttribute사용
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session) {

        boolean loginResult = memberService.login(memberDTO);

        // 로그인 성공시 세션에 로그인 멤버 정보를 넘긴다. 그리고 메인페이지로 넘어가도록함
        // 실패시 로그인 페이지에 계속 남아있도록함
        if (loginResult) {
            //loginEmail 이라는 이름으로 세션에 회원의 이메일을 담아두었다.
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "main";
        } else {
            return "login";
        }
    }

    // 데이터를 가져가는 변수이기때문에 모델 객채를 활용
    // 모델객체는 controller에서 생성된 데이터를 담아 view로 전달할때 사용하는 객체
    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        //.addAttribute함수를 사용해서 키값과 밸류값을 통해 view에 키,벨류값으로 전달.
        model.addAttribute("memberList", memberDTOList);
        //list.jsp로 이동
        return "list";
    }

    // 상세 조회 (ex -> /member?id=1)
    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        //삭제를 수행하고 리스트를 재요청 -> redirect - location을 재지정해서 보내줌
        return "redirect:/member/";
    }

    //수정화면 요청
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        //세션에 저장된 나의 이메일 가져오기
        //session.getAttribute는 object를 반환 하지만 반환은 String으로 받기때문에 string으로 형변환 해준다.
        String loginEmail = (String) session.getAttribute("loginEmail");
        //이메일로 조회하여 회원의 전체 정보를 가져옴
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "update";
    }

    // 수정처리
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
       boolean result = memberService.update(memberDTO);
        if(result) {
            //상세조회를 위한 주소( findById()) 로 rediredt
           return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }

    @PostMapping("/email-check")
    //ajax로 응답처리시 반드시 Responsebody나 ReponseEntity등으로 어노테이션을 붙혀줘야한다.
    // 스트링 타입의 응답을 준다.
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail );
        //DB에서 체크한 결과를 String 값으로 가져와서 반환함. (ajax의 res로 반환)
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

}
