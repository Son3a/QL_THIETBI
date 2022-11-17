/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "THIETBI")
public class THIETBI implements Serializable {

    @Id
    @Column(name = "MATB")
    private String matb;

    @Column(name = "TEN")
    private String ten;

    @Column(name = "LOAITB")
    private String loaitb;

    @Column(name = "TINHTRANG")
    private String tinhtrang;

    @Column(name = "HINH")
    private String hinh;

    @Column(name = "PHONG")
    private String phong;			

    @OneToMany(mappedBy = "thietbi_muon", fetch = FetchType.LAZY)
    private Collection<CT_PHIEUMUON> ct_phieumuons;

    @OneToMany(mappedBy = "thietbi", fetch = FetchType.LAZY)
    private Collection<CT_PHIEUNHAP> ct_phieunhaps;

    @OneToMany(mappedBy = "thietbi_thanhly", fetch = FetchType.LAZY)
    private Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys;

    public THIETBI() {
        super();
    }

    public String getMatb() {
        return matb;
    }

    public void setMatb(String matb) {
        this.matb = matb;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    
    
    public String getLoaitb() {
		return loaitb;
	}

	public void setLoaitb(String loaitb) {
		this.loaitb = loaitb;
	}

	

    public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    

    public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	public Collection<CT_PHIEUMUON> getCt_phieumuons() {
        return ct_phieumuons;
    }

    public void setCt_phieumuons(Collection<CT_PHIEUMUON> ct_phieumuons) {
        this.ct_phieumuons = ct_phieumuons;
    }

    public Collection<CT_PHIEUNHAP> getCt_phieunhaps() {
        return ct_phieunhaps;
    }

    public void setCt_phieunhaps(Collection<CT_PHIEUNHAP> ct_phieunhaps) {
        this.ct_phieunhaps = ct_phieunhaps;
    }

    public Collection<CT_PHIEUTHANHLY> getCt_phieuthanhlys() {
        return ct_phieuthanhlys;
    }

    public void setCt_phieuthanhlys(Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys) {
        this.ct_phieuthanhlys = ct_phieuthanhlys;
    }

}
