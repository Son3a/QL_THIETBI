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
@Table(name = "CT_PHIEUMUON")
public class CT_PHIEUMUON implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "MATB")
    private THIETBI thietbi_muon;

    @Id
    @ManyToOne
    @JoinColumn(name = "MAPM")
    private PHIEUMUON phieumuon;

    @Column(name = "TGTRA")
    private String tgtra;

    
    
    public CT_PHIEUMUON() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_PHIEUMUON(THIETBI thietbi_muon, PHIEUMUON phieumuon) {
		super();
		this.thietbi_muon = thietbi_muon;
		this.phieumuon = phieumuon;
	}

	public String getTgtra() {
		return tgtra;
	}

	public void setTgtra(String tgtra) {
		this.tgtra = tgtra;
	}

	public THIETBI getThietbi_muon() {
        return thietbi_muon;
    }

    public void setThietbi_muon(THIETBI thietbi_muon) {
        this.thietbi_muon = thietbi_muon;
    }

    public PHIEUMUON getPhieumuon() {
        return phieumuon;
    }

    public void setPhieumuon(PHIEUMUON phieumuon) {
        this.phieumuon = phieumuon;
    }

}
