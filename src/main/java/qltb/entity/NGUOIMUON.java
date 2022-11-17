/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import qltb.entity.*;

@Entity
@Table(name = "NGUOIMUON")
public class NGUOIMUON implements Serializable {

    @Id
    @Column(name = "MANM")
    private String manm;

    @Column(name = "HO")
    private String ho;

    @Column(name = "TEN")
    private String ten;

    @Column(name = "GIOITINH")
    private String gioitinh;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NGAYSINH")
    private Date ngaysinh;

    @Column(name = "DIACHI")
    private String diachi;

    @Column(name = "CMND")
    private String cmnd;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "GHICHU")
    private String ghichu;

    @OneToMany(mappedBy = "nm", fetch = FetchType.EAGER)
    private Collection<PHIEUMUON> phieumuons;

    public NGUOIMUON() {
        super();
    }

    public NGUOIMUON(String manm, String ho, String ten, String gioitinh, Date ngaysinh, String diachi, String cmnd,
            String loai, String ghichu, Collection<PHIEUMUON> phieumuons) {
        super();
        this.manm = manm;
        this.ho = ho;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.cmnd = cmnd;
        this.ghichu = ghichu;
        this.phieumuons = phieumuons;
    }

    public String getManm() {
        return manm;
    }

    public void setManm(String manm) {
        this.manm = manm;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Collection<PHIEUMUON> getPhieumuons() {
        return phieumuons;
    }

    public void setPhieumuons(Collection<PHIEUMUON> phieumuons) {
        this.phieumuons = phieumuons;
    }

}
