package gr.hua.dit.ds.grp41.rentalmanagement.rest.error_controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class MainErrorController implements ErrorController {
    private static ForbidenHandler fh;
    private static NotFoundHandler nfh;
    private static ServerErrorHandler seh;
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest req) {
        Object status=req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        System.out.println(RequestDispatcher.ERROR_STATUS_CODE);

        if(status!=null) {
           Integer statusCode=Integer.valueOf(status.toString());

           if(statusCode==HttpStatus.NOT_FOUND.value()) {
               return nfh.handleError();
           }
           else if(statusCode==HttpStatus.FORBIDDEN.value()) {
               return fh.handleError();
           }
           else if(statusCode==HttpStatus.INTERNAL_SERVER_ERROR.value()) {
               return seh.handleError();
           }
       }

       return "error/error";        
    }
}