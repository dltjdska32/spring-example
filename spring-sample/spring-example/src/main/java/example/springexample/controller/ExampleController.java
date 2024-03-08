package example.springexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController {

        //http 프로토콜중 get, post의 get
        @GetMapping("hello")
        public String hello(Model model){
            //사용자가 /hello를 요청하면
            //data에 hello!!!!!!!를 넣어 hello.html에 반환
            model.addAttribute("data", "hello!!!!!!");
            return "hello";
        }

        @GetMapping("example-mvc")
        public String exampleMvc(@RequestParam("name") String name, Model model){
            model.addAttribute("name", name);
            return "hello-template";
        }


        //api를 통해서 데이터를 바로줌  / html (x)
        @GetMapping("example-string")
        //http 응답 바디에 리턴값을 바로넣어줌
        @ResponseBody
        public String exampleString(@RequestParam("name") String name){

            return "hello" + name;
        }


        //문자가 아닌 데이터를 가져온다
        @GetMapping("example-api")
        @ResponseBody
        public Example exampleApi(@RequestParam("name") String name){
            Example example = new Example();

            example.setName(name);

            return example;

        }



    //아래에 데이터를 가져오는 api를 만들기위해 객체를만듦

    public class Example{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}

