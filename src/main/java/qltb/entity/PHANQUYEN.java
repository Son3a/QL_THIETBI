/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import qltb.entity.*;

@Entity
@Table(name = "PHANQUYEN")
/**
 *
 * @author HOME
 */
public class PHANQUYEN implements Serializable{

    @Id
    @Column(name = "MAPQ")
    private String mapq;

    @Column(name = "TENPQ")
    private String tenpq;

    @OneToMany(mappedBy = "phanQuyen", fetch = FetchType.EAGER)
    private Collection<ACCOUNT> accounts;

    public PHANQUYEN() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PHANQUYEN(String mapq, String tenpq) {
        super();
        this.mapq = mapq;
        this.tenpq = tenpq;
    }

    public String getMapq() {
        return mapq;
    }

    public void setMapq(String mapq) {
        this.mapq = mapq;
    }

    public String getTenpq() {
        return tenpq;
    }

    public void setTenpq(String tenpq) {
        this.tenpq = tenpq;
    }

    public Collection<ACCOUNT> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<ACCOUNT> accounts) {
        this.accounts = accounts;
    }

}
