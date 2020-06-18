package com.example.demo5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;
// comments made to test Git pull request
@Controller
public class HomeController
{

    List<User> users = new ArrayList<>();
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession httpSession, Model model){
        Object isLoggedIn = httpSession.getAttribute("isLoggedIn");
        if(isLoggedIn!=null && isLoggedIn.equals("yes")){
            model.addAttribute("secret", "Live life happily!");
        }else{
            model.addAttribute("secret", "Not available. Try again.");
        }
        return "index";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model, HttpSession httpSession){
        model.addAttribute("users", users);
        if(user.getLname().length() > 3)
        {
            users.add(user);
            httpSession.setAttribute("isLoggedIn", "yes");
        }else{
            httpSession.setAttribute("isLoggedIn", "no");
            return "unknownuser";
        }
        System.out.println("Session info:" + httpSession.getId());
        // lav alt muligt
        //user.setFname(user.getFname()+" redigeret");
        //model.addAttribute("user1", user);

        return "adduser";
    }

}
