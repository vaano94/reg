package com.example.Controller;

import com.example.Entity.User;
import com.example.Service.UserService;
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
 * Main controller of application.
 * Responsible for showing index page, success page and error page.
 */
@Controller
public class RegController {
    /**
     * Validator object.
     */
    private Validator validator;
    /**
     * Autowired istance of userService.
     */
    @Autowired
    private UserService userService;

    /**
     * Simple controller to redirect to starter index page.
     * @param model Defines User model which is created after user enters the page
     * @return path to index
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    /**
     * Accepts user data from "index" page in POST format.
     * Checks whether it is valid or not
     * if yes, redirects to "ok" page
     * @param user User previously created in model
     * @return pages "ok" or "index" defined by user input
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String validateFields(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);
        System.out.println(constraintViolations.size());
        if (constraintViolations.size() > 0) {
            System.out.println(constraintViolations.iterator().next().getMessage());
        } else {
                userService.publishMessage(user);
                user.setConfirmed(false);
                return "/ok";
        }

        return "/index";
    }

    /**
     * Confirmation method which accepts user requests received from email.
     * @param link String representation of confirmation link
     * @return returns Success page or Index page depending on user results.
     */
    @RequestMapping(
            value = "/confirm/{link}",
            method = RequestMethod.GET
    )
    public String confirm(@PathVariable String link) {

        System.out.println(link);
        boolean isConfirmed = userService.confirmEmail(link);
        if (isConfirmed) {
            return "/success";
        } else {
            return "/index";
        }
    }


}
