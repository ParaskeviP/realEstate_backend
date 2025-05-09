package gr.hua.dit.ds.grp41.rentalmanagement.rest.error_controllers;

public class ForbidenHandler implements BaseHandler{
    @Override
    public String handleError() {
        return "error/error-403";
    }
}