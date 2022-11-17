/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.String;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "PHIEUNHAP")
public class PHIEUNHAP implements Serializable {

    @Id
    @Column(name = "MAPN")
    private String mapn;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "TGNHAP")
    private Date tgnhap;

    @ManyToOne
    @JoinColumn(name = "MANV")
    private NHANVIEN nhanvien;

    @OneToMany(mappedBy = "phieunhap", fetch = FetchType.LAZY)
    private List<CT_PHIEUNHAP> ct_phieunhaps;

    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NHACUNGCAP ncc;

    public PHIEUNHAP() {
        super();
    }

    public PHIEUNHAP(String mapn, Date thoigiannhap, NHANVIEN nhanvien, String ghichu, List<CT_PHIEUNHAP> ct_phieunhaps,
            NHACUNGCAP ncc) {
        super();
        this.mapn = mapn;
        this.tgnhap = thoigiannhap;
        this.nhanvien = nhanvien;
        this.ct_phieunhaps = ct_phieunhaps;
        this.ncc = ncc;
    }

    public String getMapn() {
        return mapn;
    }

    public void setMapn(String mapn) {
        this.mapn = mapn;
    }

    public NHANVIEN getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NHANVIEN nhanvien) {
        this.nhanvien = nhanvien;
    }

    public List<CT_PHIEUNHAP> getCt_phieunhaps() {
        return ct_phieunhaps;
    }

    public void setCt_phieunhaps(List<CT_PHIEUNHAP> ct_phieunhaps) {
        this.ct_phieunhaps = ct_phieunhaps;
    }

    public NHACUNGCAP getNcc() {
        return ncc;
    }

    public void setNcc(NHACUNGCAP ncc) {
        this.ncc = ncc;
    }

	public Date getTgnhap() {
		return tgnhap;
	}

	public void setTgnhap(Date tgnhap) {
		this.tgnhap = tgnhap;
	}

    
}
