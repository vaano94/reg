package com.example.Controller;

import com.example.Entity.User;
import com.example.Service.UserService;
import com.example.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by ivan on 03/06/16.
 */
@Controller
public class RegController {
    private Validator validator;

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String validateFields(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Set<ConstraintViolation<User >> constraintViolations =
                validator.validate( user );
        System.out.println(constraintViolations.size());
        if (constraintViolations.size()>0) {
            System.out.println(constraintViolations.iterator().next().getMessage());
        }
        else {

                userService.publishMessage(user);
                System.out.println(user.getEmail());
                System.out.println(user.getPassword());
                user.setConfirmed(false);
                return "/ok";
        }

        return "/index";
    }

    @RequestMapping(
            value = "/confirm/{link}",
            method = RequestMethod.GET
    )
    public String confirm(@PathVariable String link) {

        System.out.println(link);
        boolean isConfirmed = userService.confirmEmail(link);
        if (isConfirmed) {
            return "/success";
        }
        else return "/index";
    }


}
