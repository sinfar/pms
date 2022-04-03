package jxf.pms.controller;

import jxf.pms.service.CustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private CustomerServiceI customerService;

    @GetMapping(value = "/index")
    public String index(Model model){

        return "index";
    }
}
