package backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "master_kelas")
public class MasterKelas {

    @Id
    @Column(name = "kode_kelas", length = 100, unique = true)
    private String kode_kelas;

    @NotNull(message = "Nama Materi harus diisi.")
    @Column(name = "nama_kelas")
    private String nama_kelas;

    @Column(name = "created_by")
    private Integer created_by;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;

    @Column(name = "updated_by")
    private Integer updated_by;

    @Column(name = "updated_date", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_date;

    public String getKode_kelas() {
        return kode_kelas;
    }

    public void setKode_kelas(String kode_kelas) {
        this.kode_kelas = kode_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

}