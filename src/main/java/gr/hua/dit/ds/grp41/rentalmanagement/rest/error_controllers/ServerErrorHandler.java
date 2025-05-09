package gr.hua.dit.ds.grp41.rentalmanagement.rest.error_controllers;

public class ServerErrorHandler implements BaseHandler {
    @Override
    public String handleError() {
        return "Server Error";
    }
}