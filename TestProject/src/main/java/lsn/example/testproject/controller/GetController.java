package lsn.example.testproject.controller;

import org.springframework.web.bind.annotation.*;
import lsn.example.testproject.data.dto.MemberDTO;

import java.util.Map;

@RestController

//공통된 url값을 넣어줌
@RequestMapping("/api/v1/get-api")
public class GetController {

    //http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
        return "hello world";
    }

    //http://localhost:8080/api/v1/get-api/name
    @GetMapping(value = "/name")
    public String getName(){
        return "lee";
    }

        //http://localhost:8080/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    //http://localhost:8080/api/v1/get-api/variable2/{String 값}
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    //http://localhost:8080/api/v1/get-api/request1?
    // name=lee&email=dltjdska32@naver.com&organization=knu
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization)
    {
        return name + " " + email + " " + organization;
    }

    //http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }


    //http://localhost:8080/api/v1/get-api/request3?name=lee&email=dltjdska32@naver.com&organization=knu
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDTO memberDTO){
        // return memberDTO.getName() + " " + memberDTO.getEmail() + " " + memberDTO.getOrganization
        return memberDTO.toString();
    }





}
