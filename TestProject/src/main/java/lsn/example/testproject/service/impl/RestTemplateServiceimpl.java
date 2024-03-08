package lsn.example.testproject.service.impl;


import lsn.example.testproject.data.dto.MemberDTO;
import lsn.example.testproject.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@Service
public class RestTemplateServiceimpl implements RestTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceimpl.class);


    @Override
    public String getLeeExample() {

        URI uri = UriComponentsBuilder
                //경로 설정
                .fromUriString("http://localhost:8081")
                .path("/api/server/lee-example")
                // utf - 8 인코딩
                .encode()
                //build() 를 사용하면 uriComponant로 반환 따라서 toUri를 통해서 uri값을 반환받는다.
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/name")
                //키값 name 밸류값 seongnam
                .queryParam("name", "seongnam")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("seongnam")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/member")
                .queryParam("name", "seongnam")
                .queryParam("email", "llll@nnn.com")
                .queryParam("organization", "knu")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("Lee!!!!!!!!");
        memberDTO.setEmail("llleee@knl.com");
        memberDTO.setOrganization("KNU!@!@!@!@!@!@");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO
                ,MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("SEONGNAM");
        memberDTO.setEmail("lllll@naver.com");
        memberDTO.setOrganization("KNUUNIV");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("lee-header", "lee Example")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity
                , MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}
