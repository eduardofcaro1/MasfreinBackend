package br.com.ifsp.tcc.repository;

import br.com.ifsp.tcc.model.VwMateriasSemestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VwMateriasSemestreRepository extends JpaRepository<VwMateriasSemestre, Long> {

    @Query("SELECT v FROM VwMateriasSemestre v WHERE v.usuarioId = :usuarioId AND v.semestreId = :semestreId")
    List<VwMateriasSemestre> findByUsuarioIdAndSemestreId(@Param("usuarioId") Long usuarioId, @Param("semestreId") Long semestreId);
}
