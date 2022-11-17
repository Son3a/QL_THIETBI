/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "CT_PHIEUNHAP")
public class CT_PHIEUNHAP implements Serializable {

	@Id
    @ManyToOne
    @JoinColumn(name = "MAPN")
    public PHIEUNHAP phieunhap;

	@Id
    @ManyToOne
    @JoinColumn(name = "MATB")
    public THIETBI thietbi;
    
    @Column(name = "DONGIA")
    public Double dongia;

    public CT_PHIEUNHAP() {
        super();
    }

    public CT_PHIEUNHAP(Integer id, THIETBI thietbi, Integer soluongnhap, Double dongia) {
        super();
        this.thietbi = thietbi;
        this.dongia = dongia;
    }

    public PHIEUNHAP getPhieunhap() {
        return phieunhap;
    }

    public void setPhieunhap(PHIEUNHAP phieunhap) {
        this.phieunhap = phieunhap;
    }

    public THIETBI getThietbi() {
        return thietbi;
    }

    public void setThietbi(THIETBI thietbi) {
        this.thietbi = thietbi;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

}
