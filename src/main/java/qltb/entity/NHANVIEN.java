package qltb.entity;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat;
import qltb.entity.*;

@Entity
@Table(name = "NHANVIEN")
public class NHANVIEN implements Serializable {

    @Id
    @Column(name = "MANV")
    private String manv;

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

    @Column(name = "HINH")
    private String hinh;
    
    @ManyToOne
    @JoinColumn(name = "MAQL")
    private QUANLI quanli;

    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private ACCOUNT acc;

    @OneToMany(mappedBy = "nhanvien", fetch = FetchType.LAZY)
    private List<PHIEUNHAP> phieunhaps;

    @OneToMany(mappedBy = "nhanvien_thanhly", fetch = FetchType.LAZY)
    private List<PHIEUTHANHLY> phieuthanhlys;

    @OneToMany(mappedBy = "nhanvien_pm", fetch = FetchType.EAGER)
    private List<PHIEUMUON> phieumuons;

    public NHANVIEN() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
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

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public ACCOUNT getAcc() {
        return acc;
    }

    public void setAcc(ACCOUNT acc) {
        this.acc = acc;
    }

    public List<PHIEUNHAP> getPhieunhaps() {
        return phieunhaps;
    }

    public void setPhieunhaps(List<PHIEUNHAP> phieunhaps) {
        this.phieunhaps = phieunhaps;
    }

    public List<PHIEUTHANHLY> getPhieuthanhlys() {
        return phieuthanhlys;
    }

    public void setPhieuthanhlys(List<PHIEUTHANHLY> phieuthanhlys) {
        this.phieuthanhlys = phieuthanhlys;
    }

    public List<PHIEUMUON> getPhieumuons() {
        return phieumuons;
    }

    public void setPhieumuons(List<PHIEUMUON> phieumuons) {
        this.phieumuons = phieumuons;
    }

    public QUANLI getQuanli() {
 		return quanli;
 	}

 	public void setQuanli(QUANLI quanli) {
 		this.quanli = quanli;
 	}
}
