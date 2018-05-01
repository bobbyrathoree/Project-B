package controllers;

import controllers.InsApp;
import models.Institute;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.util.List;

import static controllers.InsApp.InsHome;

/**
 * Created by Aazad on 4/12/2017.
 */
public class InsLogin extends Controller
{
    public static void login(@Required String in_id, @Required String in_incharge){
        if (Validation.hasErrors()){
            params.flash();
            validation.keep();
            instituteindex();
        }
        else{
            List<Institute> ins=new Institute().find("in_id",in_id).fetch();
            if(ins.isEmpty()){
                flash.error("Invalid Institute ID...");
                instituteindex();
            }else{
                for (Institute in:ins){
                    if(in.getIn_incharge().equalsIgnoreCase(in_incharge)) {
                        session.put("in_id",in.getIn_id());
                        InsApp.InsHome(in_id, in_incharge, in.getIn_name(), in.getIn_city());
                    }
                    else {
                        flash.error("Unauthorized incharge name found...");
                        instituteindex();
                    }
                }
            }
        }
    }

    public static void instituteindex(){
        render();
    }

}
