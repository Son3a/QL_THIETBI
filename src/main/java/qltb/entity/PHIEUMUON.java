/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.format.annotation.DateTimeFormat;
import qltb.entity.*;

@Entity
@Table(name = "PHIEUMUON")
public class PHIEUMUON implements Serializable{

    @Id
    @Column(name = "MAPM")
    private String mapm;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "TGMUON")
    private Date tgmuon;

    @Column(name = "PHONG")
    private String phong;

    @Column(name = "TRANGTHAI")
    private int trangthai;
    
    @Column(name = "GHICHU")
    private String ghichu;

    @ManyToOne
    @JoinColumn(name = "MANM")
    private NGUOIMUON nm;

    @ManyToOne
    @JoinColumn(name = "MANV")
    private NHANVIEN nhanvien_pm;

    @OneToMany(mappedBy = "phieumuon", fetch = FetchType.EAGER)
    private Collection<CT_PHIEUMUON> ct_phieumuons;

    public PHIEUMUON() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    



	public int getTrangthai() {
		return trangthai;
	}






	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}






	public String getMapm() {
        return mapm;
    }

    public void setMapm(String mapm) {
        this.mapm = mapm;
    }

    public Date getTgmuon() {
		return tgmuon;
	}

	public void setTgmuon(Date tgmuon) {
		this.tgmuon = tgmuon;
	}

	public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public NGUOIMUON getNm() {
        return nm;
    }

    public void setNm(NGUOIMUON nm) {
        this.nm = nm;
    }

    public NHANVIEN getNhanvien_pm() {
        return nhanvien_pm;
    }

    public void setNhanvien_pm(NHANVIEN nhanvien_pm) {
        this.nhanvien_pm = nhanvien_pm;
    }

    public Collection<CT_PHIEUMUON> getCt_phieumuons() {
        return ct_phieumuons;
    }

    public void setCt_phieumuons(Collection<CT_PHIEUMUON> ct_phieumuons) {
        this.ct_phieumuons = ct_phieumuons;
    }

    public Date getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(strDate);
        return date1;
    }

    public double tinhKhoangCachHaiNgay_Date(Date d1, Date d2) {
        double result = 0;
        result = (double) (d2.getTime() - d1.getTime());
        result = result / (24 * 60 * 60 * 1000) + 1;
        return result;
    }

    public double laySoNgay_Date(Date d1) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        double result = 0;
        result = (double) (date1.getTime() - d1.getTime());
        result = result / (24 * 60 * 60 * 1000) + 1;
        return result;
    }

    public double laySoNgay(String t1) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Date date2 = null;
        try {
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(t1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        double result = 0;
        result = (double) (date1.getTime() - date2.getTime());
        result = result / (24 * 60 * 60 * 1000) + 1;
        return result;
    }

}
