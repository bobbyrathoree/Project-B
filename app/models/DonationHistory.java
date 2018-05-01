package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Aazad on 3/25/2017.
*/
@Entity
@Table(name="Don_Hist_TB")
public class DonationHistory extends Model{

    private String d_id;
    private String in_id;
    private Date date;

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getIn_id() {
        return in_id;
    }

    public void setIn_id(String in_id) {
        this.in_id = in_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DonationHistory() {
    }

    public DonationHistory(String d_id, String in_id, Date date) {
        this.d_id = d_id;
        this.in_id = in_id;
        this.date = date;
    }
}
