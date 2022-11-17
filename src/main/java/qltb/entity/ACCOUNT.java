/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ACCOUNT")
public class ACCOUNT implements Serializable {

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "GMAIL")
    private String gmail;

    @Column(name = "SDT")
    private String sdt;
    
    @ManyToOne
    @JoinColumn(name = "MAPQ")
    private PHANQUYEN phanQuyen;

    @OneToMany(mappedBy = "acc", fetch = FetchType.EAGER)
    private List<NHANVIEN> listNhanVien;

    public ACCOUNT() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public PHANQUYEN getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PHANQUYEN phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public List<NHANVIEN> getListNhanVien() {
        return listNhanVien;
    }

    public void setListNhanVien(List<NHANVIEN> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }

}
