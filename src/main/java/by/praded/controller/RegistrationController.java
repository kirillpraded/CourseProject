package by.praded.controller;

import by.praded.entity.User;
import by.praded.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Kiryl Praded
 * 25.11.2020
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String confirmRegistration(@RequestParam("password2") String passwordConfirm,
                                      @Valid User user,
                                      BindingResult bindingResult,
                                      Model model) {

        boolean isPasswordConfirmationEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isPasswordConfirmationEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            System.out.println(user.getPassword());
            System.out.println(passwordConfirm);
            model.addAttribute("passwordError", "Passwords are not equals.");
            return "registration";
        }
        System.out.println(user.getPassword());
        System.out.println(passwordConfirm);
        if (isPasswordConfirmationEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User with that username already exists");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
