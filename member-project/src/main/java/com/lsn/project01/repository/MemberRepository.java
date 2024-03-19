package com.lsn.project01.repository;

import com.lsn.project01.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


//DB쪽과 mybatis를 통해 연결
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // mybatis에서 제공하는 클래스인 SqlSessionTemplate 의존성 주입
    private final SqlSessionTemplate sql;

    public int save(MemberDTO memberDTO) {
        System.out.printf("memberDTO" + memberDTO);
        //Mapper 패키지의 memberMapper.xml (sql문 정의) 파일을 활용하여 meberDTO값을 통해 쿼리를 날림
        return sql.insert("Member.save", memberDTO);
    }

    public List<MemberDTO> findAll() {
        //데이터베이스에서 여러 멤버 정보를 가져옴
        return sql.selectList("Member.findAll");
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // selectOne 조회결과가 하나 조회결과가 여러개일경우 selectList
        return sql.selectOne("Member.login", memberDTO);
    }

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById", id);
    }

    public void delete(Long id) {
        //return type은 int형식으로준다 삭제가 되면 1이상 안되면 0
        sql.delete("Member.delete", id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail);
    }

    public int update(MemberDTO memberDTO) {
        //update, delete, insert 인트타입으로 반환 -> 된것 만큼의 갯수를 리턴 만약 0이반환된경우 업데이트나 딜리트 인서트가 수행되지 않은것
        return sql.update("Member.update", memberDTO);
    }
}
