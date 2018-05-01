package controllers;

import play.mvc.*;

public class Application extends Controller {

    public static void index(){
        render();
    }

    public static void logindex(String login){
        if(login.equals("DLogin")){
            DonApp.loginindex();
        }else {
            InsLogin.instituteindex();
        }
    }
}