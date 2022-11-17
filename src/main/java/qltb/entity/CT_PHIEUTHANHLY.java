/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.lang.String;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "CT_PTL")
public class CT_PHIEUTHANHLY implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "MATB")
    private THIETBI thietbi_thanhly;

    @Id
    @ManyToOne
    @JoinColumn(name = "MAPTL")
    private PHIEUTHANHLY phieuthanhly;

    @Column(name = "DONGIA")
    private Double dongia;

    public CT_PHIEUTHANHLY() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CT_PHIEUTHANHLY(Integer id, THIETBI thietbi_thanhly, PHIEUTHANHLY phieuthanhly, Integer soluong, Double dongia) {
        super();
        this.thietbi_thanhly = thietbi_thanhly;
        this.phieuthanhly = phieuthanhly;
        this.dongia = dongia;
    }

    public THIETBI getThietbi_thanhly() {
        return thietbi_thanhly;
    }

    public void setThietbi_thanhly(THIETBI thietbi_thanhly) {
        this.thietbi_thanhly = thietbi_thanhly;
    }

    public PHIEUTHANHLY getPhieuthanhly() {
        return phieuthanhly;
    }

    public void setPhieuthanhly(PHIEUTHANHLY phieuthanhly) {
        this.phieuthanhly = phieuthanhly;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

}
