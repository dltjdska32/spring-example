package com.lsn.project01.service;

import com.lsn.project01.dto.MemberDTO;
import com.lsn.project01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public boolean login(MemberDTO memberDTO) {

        MemberDTO loginMember = memberRepository.login(memberDTO);

        // 멤버 리파지토리에서 로그인 쿼리를 수행후 결과를 DTO객쳉에 반환
        // 만약 반환받은 객체가 있다면 true 반환 , 객체가 없다면 false;
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }


    public boolean update(MemberDTO memberDTO) {

        int result = memberRepository.update(memberDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String emailCheck(String memberEmail) {
       MemberDTO memberDTO =  memberRepository.findByMemberEmail(memberEmail);

        // 중복 이메일이 없으면 ok, 있으면 no 반환
       if (memberDTO == null) {
           return "ok";
       } else {
           return "no";
       }
    }
}
