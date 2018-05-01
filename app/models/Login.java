package models;

import org.hibernate.annotations.*;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Aazad on 3/19/2017.
 */
@Entity
@Table(name="Login_TB")

public class Login extends Model {


    @OneToOne(cascade = CascadeType.ALL)
    @ForeignKey(name = "dfk")
    private Donor donor;
    @Required@Email
    private String d_email;
    @Required
    private String d_pass;

    public Login(Donor donor, String d_email, String d_pass) {
        this.donor = donor;
        this.d_email = d_email;
        this.d_pass = d_pass;
    }

    public Login() {
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public String getD_email() {
        return d_email;
    }

    public void setD_email(String d_email) {
        this.d_email = d_email;
    }

    public String getD_pass() {
        return d_pass;
    }

    public void setD_pass(String d_pass) {
        this.d_pass = d_pass;
    }

    public String validate(String mail, String pass) {
        Login login = new Login();
        try{
        List<Login> log = login.find("d_email", mail).fetch();
        for (Login l : log) {
            if (l.getD_pass().equals(pass)) {
                return l.getDonor().getD_id();
            } else
                return "false";
        }
        }catch (Exception e){
            return "false";
        }

        return null;
    }
}




