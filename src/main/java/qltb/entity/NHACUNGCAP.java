/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.lang.String;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "NHACUNGCAP")
public class NHACUNGCAP implements Serializable {

    @Id
    @Column(name = "MANCC")
    private String mancc;

    @Column(name = "TENNCC")
    private String ten;

    @OneToMany(mappedBy = "ncc", fetch = FetchType.EAGER)
    private List<PHIEUNHAP> phieunhaps;

    public NHACUNGCAP(String mancc, String ten, List<PHIEUNHAP> phieunhaps) {
        super();
        this.mancc = mancc;
        this.ten = ten;
        this.phieunhaps = phieunhaps;
    }

    public NHACUNGCAP() {
        super();
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<PHIEUNHAP> getPhieunhaps() {
        return phieunhaps;
    }

    public void setPhieunhaps(List<PHIEUNHAP> phieunhaps) {
        this.phieunhaps = phieunhaps;
    }

}
