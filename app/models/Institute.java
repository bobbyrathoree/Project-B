package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Aazad on 3/25/2017.
 */
@Entity
@Table(name = "Inst_TB")
public class Institute extends GenericModel {

    @GeneratedValue(generator = "seq_ins_id")
    @GenericGenerator(name = "seq_ins_id",strategy = "IDGenerator.InsIDGenerator")
    @Id
    private String in_id;
    @Required
    private String in_name;
    @Required
    private String in_ad_l1;
    @Required
    private String in_ad_l2;
    @Required
    private String in_ad_l3;
    @Required
    private String in_city;
    @Required
    private String in_incharge;
    @Required
    private String in_contact1;
    @Required
    private String in_contact2;


    public String getIn_id() {
        return in_id;
    }

    public void setIn_id(String in_id) {
        this.in_id = in_id;
    }
    public String getIn_name() {
        return in_name;
    }

    public void setIn_name(String in_name) {
        this.in_name = in_name;
    }

    public String getIn_ad_l1() {
        return in_ad_l1;
    }

    public void setIn_ad_l1(String in_ad_l1) {
        this.in_ad_l1 = in_ad_l1;
    }

    public String getIn_ad_l2() {
        return in_ad_l2;
    }

    public void setIn_ad_l2(String in_ad_l2) {
        this.in_ad_l2 = in_ad_l2;
    }

    public String getIn_ad_l3() {
        return in_ad_l3;
    }

    public void setIn_ad_l3(String in_ad_l3) {
        this.in_ad_l3 = in_ad_l3;
    }

    public String getIn_city() {
        return in_city;
    }

    public void setIn_city(String in_city) {
        this.in_city = in_city;
    }

    public String getIn_incharge() {
        return in_incharge;
    }

    public void setIn_incharge(String in_incharge) {
        this.in_incharge = in_incharge;
    }

    public String getIn_contact1() {
        return in_contact1;
    }

    public void setIn_contact1(String in_contact1) {
        this.in_contact1 = in_contact1;
    }

    public String getIn_contact2() {
        return in_contact2;
    }

    public void setIn_contact2(String in_contact2) {
        this.in_contact2 = in_contact2;
    }

    public Institute() {
    }

    public Institute(String in_name, String in_ad_l1, String in_ad_l2, String in_ad_l3, String in_city, String in_incharge, String in_contact1, String in_contact2) {
        this.in_name = in_name;
        this.in_ad_l1 = in_ad_l1;
        this.in_ad_l2 = in_ad_l2;
        this.in_ad_l3 = in_ad_l3;
        this.in_city = in_city;
        this.in_incharge = in_incharge;
        this.in_contact1 = in_contact1;
        this.in_contact2 = in_contact2;
    }


}
