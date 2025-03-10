package project.com.Recruitment.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;

import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.exceptions.IllegalRegistrationException;

@ControllerAdvice
public class ErrorHandling{
    public static final String ERROR_TYPE = "errorType";
    public static final String GENERIC_ERROR = "generic";
    public static final String ERROR_MSG = "errorMsg";
    public static final String USERNAME_ERROR = "usernameError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String PSN_ERROR = "psnError";
    public static final String REGISTER_DTO = "registerDTO";

    public static final String ERROR_URL = "error";
    public static final String REGISTER_URL = "register";

    /*
     * Method to handle IllegalRegistrationException
     * @param e the exception
     * @param model the model to add attributes to
     * @return the register view with the error message
     */
    @ExceptionHandler(IllegalRegistrationException.class)
    public String exceptionHandler(IllegalRegistrationException e, Model model) {
        if (e.getMessage().toLowerCase().contains("username")) {
            model.addAttribute(ERROR_TYPE, USERNAME_ERROR);
            
        } else if (e.getMessage().toLowerCase().contains("email")) {
            model.addAttribute(ERROR_TYPE, EMAIL_ERROR);
            
        } else if (e.getMessage().toLowerCase().contains("personal")) {
            model.addAttribute(ERROR_TYPE, PSN_ERROR);

        } else {
            model.addAttribute(ERROR_TYPE, GENERIC_ERROR);
        }
        model.addAttribute(REGISTER_DTO, new RegisterDTO());
        model.addAttribute(ERROR_MSG, e.getMessage());
        return REGISTER_URL;
    }
    /*
     * Method to handle all other exceptions
     * @param e the exception
     * @param model the model to add attributes to
     * @return the error view with the error message
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute(ERROR_TYPE, GENERIC_ERROR);
        model.addAttribute(ERROR_MSG, e.getMessage());
        return ERROR_URL;
    }
}
