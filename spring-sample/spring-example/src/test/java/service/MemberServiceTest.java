package service;

import example.springexample.domain.Member;
import example.springexample.repository.MemberRepository;
import example.springexample.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    //실제 메인 클래스의 MemberService 클래스에 있는 실제 값을 쓰기위해서 di 주입(의존성주입)
    //테스트 동작하기전 실행 각각의 함수를 실행하기 전 실행
    @BeforeEach
    public void beforeEach(){
        //실제 메인 클래스의 memberservice의 memoryMemberRepository 사용
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given(주어진 상황)
        Member member = new Member();
        member.setName("hello");
        //when (실행시)
        Long saveId = memberService.join(member);

        //then (결과)
        Member findMember = memberService.findOne(saveId).get();
        //alt + enter 스태틱임포트 (단축키)
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복확인(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //람다함수를 실행해서 해당 예외가 발생하는지 테스트
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //함수에서 반환한 "이미 존재하는 회원입니다" 가 정상 출력되는지 확인하기위한 테스트
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /*try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }


    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}