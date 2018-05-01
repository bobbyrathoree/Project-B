package controllers;

import models.Donor;
import models.Login;
import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Scope;

import java.util.List;

/**
 * Created by Aazad on 3/28/2017.
 */
public class DonApp extends Controller {
    public static void loginindex() {

        render();

    }

    public static void login(@Required @Email String d_email, @Required String d_pass){
        if(validation.hasErrors()){
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            loginindex();
        }else{
            List<Login> l=new Login().find("d_email",d_email).fetch();
            System.out.println(l);
            System.out.println("here..");
            if(l.isEmpty()==true){
                flash.error("Email not found...!");
                params.flash();
                System.out.println("null");
                validation.keep();
                loginindex();
            }
            else{
                for (Login login1:l){
                    System.out.println("for");
                    System.out.println(login1.getD_pass());
                    System.out.println(d_pass);
                    if(login1.getD_pass().equals(d_pass)){
                        System.out.println("succees");
                        System.out.println(login1.getDonor().getD_id());
                        session.put("id",login1.getDonor().getD_id());
                        List<Donor> dnr=login1.getDonor().find("d_email",d_email).fetch();
                        if(dnr!=null){
                            for(Donor d:dnr){
                                System.out.println(d.getD_id());
                            }
                        }
                        //System.out.println(dnr.toString());
                        home(dnr);
                    }
                    else {
                        flash.error("Password not matching...!");
                        //flash.keep("abc");
                        System.out.println("false");
                        //params.get("abc");
                        params.flash();
                        validation.keep();
                        loginindex();
                    }
                    //home(login1.getDonor(),login1.getD_email());
                }
            }
        }
        //render();
    }

    public static void home(List<Donor> donor){
        boolean user=false;
        if(params.get("d_id")!=null){
            user=true;
        }

        render(donor,user);

    }

    public static void perform(String donor){
        System.out.println(donor);
        /*for(Donor dnr:donor){
            System.out.println(dnr.getD_id());
        }*/
        renderTemplate("DonApp/donorprofile.html",donor);

    }


    public static void donorprofile(){
        render();
    }

    public static void logout(){
        Scope.Session.current().remove("id");
        loginindex();
    }

}
