package example.springexample.controller;

import example.springexample.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.MemberService;

import java.util.List;

//스프링이 실행되면 스프링컨테이너에 controller(스프링빈)를생성 /// Controller는 사용자에게 요청을 받으면 실행
@Controller
public class MemberController {

    private final MemberService memberService;


    //스프링 컨테이너에있는 멤버서비스를 연결시켜준다. (의존성주입)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //home.html 에서 회원가입 링크를 클릭시 매핑해서 createForm으로 이동
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //멤버폼 객체에있는 이름으로
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //다시 홈 화면으로 이동
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        //사용자가 members를 요청하면 members(리스트)를 return값에 반환
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
