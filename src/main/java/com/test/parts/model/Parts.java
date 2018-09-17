package com.test.parts.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by oper on 13.09.2018.
 */
@Entity
@Table(name = "parts")
public class Parts implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "partname")
    private String partname;

    @Column(name = "partbase", columnDefinition = "TINYINT(0)")
    private boolean partbase;

    @Column(name = "partqty")
    private int partqty;

    @Column(name = "parttype")
    private int parttype;


    public void setParttype(int parttype) {
        this.parttype = parttype;
    }

    public int getParttype() {
        return parttype;
    }

    public Parts() {
    }

    public int getId() {
        return id;
    }

    public String getPartname() {
        return partname;
    }

    public boolean getPartbase() {
        return partbase;
    }

    public int getPartqty() {
        return partqty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public void setPartbase(boolean partbase) {
        this.partbase = partbase;
    }

    public void setPartqty(int partqty) {
        this.partqty = partqty;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", partname='" + partname + '\'' +
                ", partbase=" + partbase +
                ", partqty=" + partqty +
                ", parttype=" + parttype +
                '}';
    }
}
