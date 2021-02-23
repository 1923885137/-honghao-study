package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/view")
public class Views {
    @ResponseBody
    @RequestMapping(path = "/backstage",method = RequestMethod.GET)
    public ModelAndView backstage(){
        return new ModelAndView("backstage");
    }

    @ResponseBody
    @RequestMapping(path = "/register")
    public ModelAndView register(){
        return new ModelAndView("load_register");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage_password")
    public ModelAndView backstage_password(){
        return new ModelAndView("backstage-password");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage_resource")
    public ModelAndView backstage_resource(){
        return new ModelAndView("backstage-resource");
    }
}
