package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;
import javax.sql.DataSource;

import models.Donor;
import models.InsInventory;
import models.Institute;
import models.Login;
import play.db.DB;
import play.db.jpa.GenericModel;
import play.db.jpa.JPA;
import play.mvc.Controller;

/**
 * Created by Aazad on 4/19/2017.
 */
public class AppController extends Controller {

    public static String loginCheck(String d_email,String d_pass) throws IOException {

        HashMap<String,String> successerror=new HashMap<String, String>(2);
        Gson gson=new Gson();
        List<Login> l = new Login().find("d_email", d_email).fetch();
        if (l.isEmpty() == true) {              //Invalid Donor ID
           successerror.put("sucess","false");
            successerror.put("errorcode","1");
            return gson.toJson(successerror);
        } else {
            for (Login login1 : l) {
                if (login1.getD_pass().equals(d_pass)) {    //Successfull Authentication
                    String json = "{\"pass\":\"" + login1.getD_pass() + "\"}";
                    successerror.put("sucess","true");
                    return gson.toJson(successerror);
                }
                else{                           //Invalid Password
                    successerror.put("sucess","false");
                    successerror.put("errorcode","2");
                    return gson.toJson(successerror);
                }
            }
            }
        return null;
        }

    public static String ProfileFinder(String d_email){
        List<Donor> donor=  new Donor().find("d_email",d_email).fetch();
        for (Donor dnr:donor){
            return(new Gson().toJson(dnr)) ;
        }

        return null;
    }

    public static String DonorInfo(String d_city, String d_bgrp){
        System.out.println(d_bgrp);
       List<Donor> donors=new Donor().find("d_city=? and d_bgrp=?",d_city,d_bgrp).fetch();
        Gson gson=new Gson();
        String response="{";
        int i=0;
        int size=donors.size();
        while(i<size){
            if(i==size-1)
                response=response+"donor"+i+":"+new Gson().toJson(donors.get(i));
            else
                response=response+"donor"+i+":"+new Gson().toJson(donors.get(i))+",";

            i++;
        }

        return response+"}";
        //return new Gson().toJson(donor);
        //return null;
    }

    public static String InstInfo(String in_city,String in_bgrp) throws SQLException {
        Connection conn = null;
        Statement stmt=null;
        DataSource DS;
        String resposne = "{";
        try {
            DS = DB.getDataSource();
            conn = DS.getConnection();
            stmt = conn.createStatement();

            String type = "";

            if (in_bgrp.equals("A+")) {
                type = "a_plus";

            } else if (in_bgrp.equals("A-")) {
                type = "a_minus";

            } else if (in_bgrp.equals("B+")) {
                type = "b_plus";

            } else if (in_bgrp.equals("B-")) {
                type = "b_minus";

            } else if (in_bgrp.equals("AB+")) {
                type = "ab_plus";

            } else if (in_bgrp.equals("AB-")) {
                type = "ab_minus";

            } else if (in_bgrp.equals("O+")) {
                type = "o_plus";

            } else if (in_bgrp.equals("O-")) {
                type = "o_minus";
            }

            String sql = "select * from inst_tb where in_city='" + in_city + "' and in_id in(select institute_in_id from inventory_tb where " + type + " >0)";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                if (rs.isLast())
                    resposne = resposne + "inst" + i + ":{\"in_id\":\"" + rs.getString("in_id") + "\",\"in_name\":\"" + rs.getString("in_name") + "\",\"in_city\":\"" + rs.getString("in_city") + "\"}";
                else
                    resposne = resposne + "inst" + i + ":{\"in_id\":\"" + rs.getString("in_id") + "\",\"in_name\":\"" + rs.getString("in_name") + "\",\"in_city\":\"" + rs.getString("in_city") + "\"},";
                i++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
            stmt.close();
        }
        return resposne+"}";

    }

    public static String InstDetail(String in_id,String in_bgrp){
        List<Institute> InstList=new Institute().find("in_id",in_id).fetch();
        String response = "{";
        List<InsInventory> InventoryList=new InsInventory().find("institute_in_id",in_id).fetch();
        int units=0;
        for(InsInventory inv:InventoryList) {
            if (in_bgrp.equals("A+")) {
                units=inv.getA_Plus();

            } else if (in_bgrp.equals("A-")) {
               units=inv.getA_Minus();

            } else if (in_bgrp.equals("B+")) {
                units=inv.getB_Plus();

            } else if (in_bgrp.equals("B-")) {
                units=inv.getB_Minus();

            } else if (in_bgrp.equals("AB+")) {
                units=inv.getAB_Plus();

            } else if (in_bgrp.equals("AB-")) {
                units=inv.getAB_Minus();

            } else if (in_bgrp.equals("O+")) {
                units=inv.getO_Plus();

            } else if (in_bgrp.equals("O-")) {
                units=inv.getO_Minus();
            }
        }

        for(Institute ins:InstList) {
            response = response + "\"InstInfo\":" + new Gson().toJson(ins)+",\"units\":\""+units+"\"";
        }

        return response+"}";
    }


}
