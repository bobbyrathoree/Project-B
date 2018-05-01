package controllers;

import models.*;
import play.data.validation.Error;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Aazad on 3/28/2017.
 */
public class InsApp extends Controller {
    @Before
    public static void loginSession(){
        if(session.get("in_id")==null){
            flash.error("Please Login First...");
            InsLogin.instituteindex();
        }
    }

    public static void InsHome(String in_id, String in_incharge, String in_name, String in_city) {
        render(in_id,in_incharge,in_name,in_city);
    }

    public static void perform(String choice,String in_id,String in_incharge,String in_name,String in_city){
        System.out.println(choice);
        if(choice.equals("View Blood Bank Inventory")){
            showInventory(in_id,in_name,in_city,in_incharge);
        }else if(choice.equals("Register New Donor")){
            donorRegister(in_id,in_incharge,in_name,in_city);
        }else if(choice.equals("Enter Donation Details")){
            newDonation(in_id,in_incharge,in_name,in_city);
        }else if(choice.equals("View Donor Infomation")) {
            viewDonor(in_id, in_incharge, in_name, in_city);
        }
    }

    private static void usageDetails(String in_id) {
        render();
    }

    public static void viewDonor(String in_id, String in_incharge, String in_name, String in_city) {
        String d_id=params.get("d_id");
        System.out.println(d_id);

        List<Donor> list=new Donor().find("d_id",d_id).fetch();
        System.out.println("here");
        if(list.isEmpty()){
            System.out.println("unsuccess");
            flash.error("No donor found...");
            renderTemplate("InsApp/viewDonor.html", in_id, in_incharge, in_name, in_city);
        }
        else {
            System.out.println("here");
            renderTemplate("InsApp/viewDonor.html", list, in_id, in_incharge, in_name, in_city,d_id);
        }
    }

    private static void newDonation(String in_id, String in_incharge, String in_name, String in_city) {

            renderTemplate("InsApp/newDonation.html",in_id,in_incharge,in_name,in_city);



    }

    public static void donationProcess(String in_id, String in_incharge, String in_name, String in_city){
        String d_id=params.get("d_id");

        Donor donor=new Donor();
        List<Donor> donorList=donor.find("d_id",d_id).fetch();


        if(donorList.isEmpty()){
            flash.error("Invalid Donor ID...");
            renderTemplate("InsApp/newDonation.html",in_id,in_incharge,in_name,in_city);

        }
        else{

            Donor dnr=new Donor();
            List<Donor> list=dnr.find("d_id",d_id).fetch();
            for(Donor d:list){
                System.out.println(d.getD_id());
                d.setD_units_donated(d.getD_units_donated()+1);
                d.setD_last_donate(new Date());
                d.save();
                List<InsInventory> list1=new InsInventory().find("institute_in_id",in_id).fetch();
                for(InsInventory ins:list1) {
                    String bgrp = d.getD_bgrp();
                    if (bgrp.equals("A+")) {
                        ins.setA_Plus(ins.getA_Plus()+1);
                        ins.save();
                    } else if (bgrp.equals("A-")) {
                        ins.setA_Minus(ins.getA_Minus()+1);
                        ins.save();

                    } else if (bgrp.equals("B+")) {
                        ins.setB_Plus(ins.getB_Plus()+1);
                        ins.save();

                    } else if (bgrp.equals("B-")) {
                        ins.setA_Minus(ins.getB_Minus()+1);
                        ins.save();

                    } else if (bgrp.equals("AB+")) {
                        ins.setAB_Plus(ins.getAB_Plus()+1);
                        ins.save();

                    } else if (bgrp.equals("AB-")) {
                        ins.setAB_Minus(ins.getAB_Minus()+1);
                        ins.save();

                    } else if (bgrp.equals("O+")) {
                        ins.setO_Plus(ins.getO_Plus()+1);
                        ins.save();

                    } else if (bgrp.equals("O-")) {
                        ins.setO_Minus(ins.getO_Minus()+1);
                        ins.save();

                    }
                }
            }

            new DonationHistory(d_id,in_id,new Date()).save();
            Boolean success=true;

            renderTemplate("InsApp/newDonation.html",in_id,in_incharge,in_name,in_city,success);

        }
    }

    private static void donorRegister(String in_id, String in_incharge, String in_name, String in_city) {

        renderTemplate("InsApp/donorRegister.html",in_id,in_incharge,in_name,in_city);
    }

    public static void registration(@Valid Donor donor,String in_id,String in_incharge,String in_name,String in_city) {
        if (validation.hasErrors()) {
            for (Error error : validation.errors()) {
                System.out.println(error.message());
            }
            params.flash();
            validation.keep();
            donorRegister(in_id,in_incharge, in_name, in_city);
        } else {
            System.out.println("Success");
            List<Donor> donors = new Donor().find("d_email", donor.getD_email()).fetch();
            if (donors.isEmpty()) {
                donor.save();
                new Login(donor, donor.getD_email(), "password").save();
                String success = "Donor Registered ";
                renderTemplate("InsApp/donorRegister.html",success,in_id,in_name,in_incharge,in_city);
            } else {
                flash.error("Donor already exists....", "donor.d_email");
                System.out.println("unsucess");
                params.flash();
                validation.keep();
                donorRegister(in_id,in_incharge, in_name, in_city);
            }

        }

    }

    private static void showInventory(String in_id, String in_name, String in_city, String in_incharge) {
        List<InsInventory> insInfo=new InsInventory().find("institute_in_id",in_id).fetch();
        renderTemplate("InsApp/showInventory.html",insInfo,in_name,in_city,in_incharge,in_id);
    }

    public static void ilogout(){
        session.remove("in_id");
        InsLogin.instituteindex();
    }


}
