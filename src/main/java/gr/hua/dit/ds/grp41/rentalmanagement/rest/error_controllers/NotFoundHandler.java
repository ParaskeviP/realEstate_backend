package gr.hua.dit.ds.grp41.rentalmanagement.rest.error_controllers;

public class NotFoundHandler implements BaseHandler {
    @Override
    public String handleError() {
        return "Not Found.";
    }
}