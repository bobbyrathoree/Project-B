package models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Email;
import play.data.validation.Phone;
import play.data.validation.Range;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by Aazad on 3/19/2017.
 */
@Entity
@Table(name="Donor_TB")
public class Donor extends GenericModel{



    @GeneratedValue(generator = "seq_don_id")
    @GenericGenerator(name = "seq_don_id",strategy = "IDGenerator.UserIDGenerator")
    //@OneToOne(cascade = CascadeType.ALL)
    @Id
    private String d_id;
    @Required
    private String d_fname;
    @Required
    private String d_lname;
    @Required
    private String d_bgrp;
    @Required
    private String d_city;
    @Required@Range(min =18 ,max = 50,message = "Age Must has to be beetween 18 to 50 yeras")
    private int d_age;
    @Required
    private String d_gender;
    @Required
    @Phone
    private String d_mob_no;
    @Required
    @Email
    private String d_email;
    @Required
    private int d_units_donated;
    private int d_units_received;
    @Required
    private Date d_last_donate;

    private Date d_next_donate;


    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_fname() {
        return d_fname;
    }

    public void setD_fname(String d_fname) {
        this.d_fname = d_fname;
    }

    public String getD_lname() {
        return d_lname;
    }

    public void setD_lname(String d_lname) {
        this.d_lname = d_lname;
    }

    public String getD_bgrp() {
        return d_bgrp;
    }

    public void setD_bgrp(String d_bgrp) {
        this.d_bgrp = d_bgrp;
    }

    public String getD_city() {
        return d_city;
    }

    public void setD_city(String d_city) {
        this.d_city = d_city;
    }

    public int getD_age() {
        return d_age;
    }

    public void setD_age(int d_age) {
        this.d_age = d_age;
    }

    public String getD_gender() {
        return d_gender;
    }

    public void setD_gender(String d_gender) {
        this.d_gender = d_gender;
    }

    public String getD_mob_no() {
        return d_mob_no;
    }

    public void setD_mob_no(String d_mob_no) {
        this.d_mob_no = d_mob_no;
    }

    public String getD_email() {
        return d_email;
    }

    public void setD_email(String d_email) {
        this.d_email = d_email;
    }

    public int getD_units_donated() {
        return d_units_donated;
    }

    public void setD_units_donated(int d_units_donated) {
        this.d_units_donated = d_units_donated;
    }

    public int getD_units_received() {
        return d_units_received;
    }

    public void setD_units_received(int d_units_received) {
        this.d_units_received = d_units_received;
    }

    public Date getD_last_donate() {
        return d_last_donate;
    }

    public void setD_last_donate(Date d_last_donate) {
        this.d_last_donate = d_last_donate;
    }

    public Date getD_next_donate() {
        return d_next_donate;
    }

    public void setD_next_donate(Date d_next_donate) {
        this.d_next_donate = d_next_donate;
    }

    public Donor(){

    }

    @ValidateOnExecution
    public  Donor(String d_fname, String d_lname, String d_bgrp, String d_city, int d_age, String d_gender, String d_mob_no, String d_email, int d_units_donated, int d_units_received, Date d_last_donate, Date d_next_donate) {
        Boolean response=validate(d_email);
        if(response==true) {
            this.d_fname = d_fname;
            this.d_lname = d_lname;
            this.d_bgrp = d_bgrp;
            this.d_city = d_city;
            this.d_age = d_age;
            this.d_gender = d_gender;
            this.d_mob_no = d_mob_no;
            this.d_email = d_email;
            this.d_units_donated = d_units_donated;
            this.d_units_received = d_units_received;
            this.d_last_donate = d_last_donate;
            this.d_next_donate = d_next_donate;
        }

    }

    public Boolean validate(String email){
        Donor donor= new Donor();
        List<Donor> l=donor.find("d_email",email).fetch();
        if(l.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

}

