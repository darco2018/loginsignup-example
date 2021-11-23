package com.ust.logging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SignupLoginController {

    Map<String, String> users = new HashMap<>();

    @GetMapping("")
    public String showIndex() {
        return "index";
    }

    @GetMapping("signup")
    public String showSignupForm() {
        return "signupForm";
    }

    @PostMapping("signup")
    public String processSignup(@RequestParam("login") String log, @RequestParam String password, Model model) {

        if (isValidSignup(log, password)) {
            users.put(log, password);
            model.addAttribute("success_message", "You have signed up successfully.");
            model.addAttribute("log", log);
            return "index";
        } else {
            model.addAttribute("error_message", "Found errors. Try to sign up again.");
            return "signupForm";
        }

    }

    private boolean isValidSignup(String log, String password) {
        return true;
    }

    @GetMapping("login")
    public String showLoginForm() {
        return "loginForm";
    }

    @PostMapping("login")
    public String processLogin(@RequestParam("login") String log, @RequestParam String password, Model model) {

        if (isValidLogin(log, password)) {
            users.put(log, password);
            model.addAttribute("success_message", "You have logged in successfully.");
            model.addAttribute("log", log);
            return "index";
        } else {
            model.addAttribute("error_message", "Found errors. Try to log in again.");
            return "loginForm";
        }

    }

    private boolean isValidLogin(String log, String password) {
        return false;
    }

    @GetMapping("test")
    @ResponseBody
    public String testJSONResponse() {
        return "Here is a test page";
    }

    //////////////////// HOW TO ADD TO MODEL ///////////////////////////////////////////
    @GetMapping("listinmodel")
    public String messages(Model model) {
        // model.addAttribute("messages", messageRepository.findAll());
        return "message/list"; // view is returned with String as returned value
    }

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public ModelAndView messages() {
        ModelAndView mav = new ModelAndView("message/list");
        //mav.addObject("messages", messageRepository.findAll());
        return mav;
    }
/*
    But how do we know the view name here?!
    The @ModelAttribute is an annotation that binds a method parameter or method return value
    to a named model attribute and then exposes it to a web view.
    When the annotation is used at the method level it indicates the purpose of that method is
    to add one or more model attributes.

    Spring-MVC will always make a call first to that method, before it calls any request handler methods.
    That is, @ModelAttribute methods are invoked before the controller methods annotated with
    @RequestMapping are invoked. The logic behind the sequence is that, the model object has to be created
    before any processing starts inside the controller methods.

    It is also important that you annotate the respective class as @ControllerAdvice. Thus, you can add values
    in Model which will be identified as global. This actually means that for every request a default value exists,
     for every method in the response part.

    @ModelAttribute("messages")
    public List<Message> messages() {
        return messageRepository.findAll();
    }
*/

}
