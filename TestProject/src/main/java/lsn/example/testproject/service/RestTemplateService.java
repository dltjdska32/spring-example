package lsn.example.testproject.service;


import lsn.example.testproject.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface RestTemplateService {
    public String getLeeExample();
    public String getName();
    public String getName2();
    public ResponseEntity<MemberDTO> postDto();
    public ResponseEntity<MemberDTO> addHeader();
}
