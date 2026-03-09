package br.com.fiap.study_apir;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("raiz")
public class PingController {


    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }


    @GetMapping("/rota1")
    public String rota1(){
        return "rota1";
    }

    @GetMapping("/rota2")
    public String rota2(){
        return "rota2";
    }


}
