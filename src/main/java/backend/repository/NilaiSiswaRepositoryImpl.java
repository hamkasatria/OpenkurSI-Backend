package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

import backend.model.NilaiSiswa;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class NilaiSiswaRepositoryImpl implements NilaiSiswaRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public NilaiSiswaRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public NilaiSiswa save(NilaiSiswa nilaiSiswa) {

        entityManager.persist(nilaiSiswa);
        return nilaiSiswa;    
    }

    @Override
    @Transactional
    public NilaiSiswa update(Long id, NilaiSiswa nilaiSiswa) {
        
        entityManager.merge(nilaiSiswa);
        return nilaiSiswa;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NilaiSiswa> findAll() {
        String qlString = "SELECT k FROM NilaiSiswa k";
        TypedQuery<NilaiSiswa> query = entityManager.createQuery(qlString, NilaiSiswa.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NilaiSiswa> findByIdKelas(Long id_kelas) {
        String qlString = "select a from NilaiSiswa a " + 
            "inner join KelasPeserta b on a.id_kelas_peserta = b.id " +
            "inner join Kelas c on b.id_kelas = c.id " +
            "where c.id = " + id_kelas;
        TypedQuery<NilaiSiswa> query = entityManager.createQuery(qlString, NilaiSiswa.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public NilaiSiswa findById(@NotNull Long id) {
        return entityManager.find(NilaiSiswa.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        NilaiSiswa NilaiSiswa = findById(id);

        if(NilaiSiswa != null) {
            entityManager.remove(NilaiSiswa);
        }
    }

    @Override
    @Transactional
    public List<NilaiSiswa> findByIdPeserta(Long id_peserta) {
        String qlString = "select a from NilaiSiswa a " +
            "inner join KelasPeserta b on a.id_kelas_peserta = b.id " +
            "inner join UserDetail c on b.id_user = c.id " +
            "where c.id = :id_peserta";
        TypedQuery<NilaiSiswa> query = entityManager.createQuery(
            qlString, 
            NilaiSiswa.class
        ).setParameter("id_peserta", id_peserta);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Boolean existByIdKelasPesertaAndIdKategoriNilaiMateri(
        Long id_kelas_peserta,
        Long id_kategori_nilai_materi
    ) {
        String qlString = "select a from NilaiSiswa a " + 
            "where a.id_kelas_peserta = :id_kelas_peserta " +
            "and a.id_kategori_nilai_materi = :id_kategori_nilai_materi";
        TypedQuery<NilaiSiswa> query = entityManager.createQuery(
            qlString,
            NilaiSiswa.class
            ).setParameter("id_kelas_peserta", id_kelas_peserta)
            .setParameter("id_kategori_nilai_materi", id_kategori_nilai_materi)
            .setMaxResults(1);
        return query.getResultList().isEmpty();
    }

}
