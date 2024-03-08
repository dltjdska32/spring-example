package service;

import example.springexample.domain.Member;
import example.springexample.repository.MemberRepository;
import example.springexample.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//비즈니스로직 사용자에게 요청(controller를통해)받은 행동을 해당 클래스에서 실행 (스프링빈등록)
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //의존성주입 멤버리포지토리와 연결
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원가입
    public Long join(Member member){
        //중복회원 있으면 안됨
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
               .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }

}
