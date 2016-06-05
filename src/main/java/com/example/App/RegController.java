package com.example.App;

import com.example.Services.PersonService;
import com.example.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/index")
    public String redirect(Model model) {
        model.addAttribute("user", new User());
        return "index";
    };

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

            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            user.setConfirmed(false);
            PersonService personService = new PersonService();
            System.out.println("Adding user into database" +  personService.addUser(user));
        }

        return "/index";
    }

}
