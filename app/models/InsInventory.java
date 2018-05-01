package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by Aazad on 3/25/2017.
 */
@Entity
@Table(name = "Inventory_TB")
public class InsInventory extends Model {
    @OneToOne
    private Institute institute;
    private int A_Plus;
    private int A_Minus;
    private int B_Plus;
    private int B_Minus;
    private int AB_Plus;
    private int AB_Minus;
    private int O_Plus;
    private int O_Minus;

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public int getA_Plus() {
        return A_Plus;
    }

    public void setA_Plus(int a_Plus) {
        A_Plus = a_Plus;
    }

    public int getA_Minus() {
        return A_Minus;
    }

    public void setA_Minus(int a_Minus) {
        A_Minus = a_Minus;
    }

    public int getB_Plus() {
        return B_Plus;
    }

    public void setB_Plus(int b_Plus) {
        B_Plus = b_Plus;
    }

    public int getB_Minus() {
        return B_Minus;
    }

    public void setB_Minus(int b_Minus) {
        B_Minus = b_Minus;
    }

    public int getAB_Plus() {
        return AB_Plus;
    }

    public void setAB_Plus(int AB_Plus) {
        this.AB_Plus = AB_Plus;
    }

    public int getAB_Minus() {
        return AB_Minus;
    }

    public void setAB_Minus(int AB_Minus) {
        this.AB_Minus = AB_Minus;
    }

    public int getO_Plus() {
        return O_Plus;
    }

    public void setO_Plus(int o_Plus) {
        O_Plus = o_Plus;
    }

    public int getO_Minus() {
        return O_Minus;
    }

    public void setO_Minus(int o_Minus) {
        O_Minus = o_Minus;
    }

    public InsInventory() {
    }

    public InsInventory(Institute institute, int a_Plus, int a_Minus, int b_Plus, int b_Minus, int AB_Plus, int AB_Minus, int o_Plus, int o_Minus) {
        this.institute = institute;
        A_Plus = a_Plus;
        A_Minus = a_Minus;
        B_Plus = b_Plus;
        B_Minus = b_Minus;
        this.AB_Plus = AB_Plus;
        this.AB_Minus = AB_Minus;
        O_Plus = o_Plus;
        O_Minus = o_Minus;
    }
}
