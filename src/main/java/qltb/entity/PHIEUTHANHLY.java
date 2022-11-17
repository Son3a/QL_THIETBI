/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.util.Collection;
import java.util.Date;
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
@Table(name = "PHIEUTHANHLY")
public class PHIEUTHANHLY implements Serializable {

    @Id
    @Column(name = "MAPTL")
    private String maptl;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "TGTHANHLY")
    private Date tgthanhly;

    @Column(name = "GHICHU")
    private String ghichu;

    @ManyToOne
    @JoinColumn(name = "MANV")
    private NHANVIEN nhanvien_thanhly;

    @OneToMany(mappedBy = "phieuthanhly", fetch = FetchType.LAZY)
    private Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys;

    public PHIEUTHANHLY() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getMaptl() {
        return maptl;
    }

    public void setMaptl(String maptl) {
        this.maptl = maptl;
    }


    public Date getTgthanhly() {
		return tgthanhly;
	}

	public void setTgthanhly(Date tgthanhly) {
		this.tgthanhly = tgthanhly;
	}

	public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public NHANVIEN getNhanvien_thanhly() {
        return nhanvien_thanhly;
    }

    public void setNhanvien_thanhly(NHANVIEN nhanvien_thanhly) {
        this.nhanvien_thanhly = nhanvien_thanhly;
    }

    public Collection<CT_PHIEUTHANHLY> getCt_phieuthanhlys() {
        return ct_phieuthanhlys;
    }

    public void setCt_phieuthanhlys(Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys) {
        this.ct_phieuthanhlys = ct_phieuthanhlys;
    }

}
