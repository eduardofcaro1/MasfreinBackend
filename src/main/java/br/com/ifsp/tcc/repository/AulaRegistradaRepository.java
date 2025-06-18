package br.com.ifsp.tcc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.AulaRegistrada;
import br.com.ifsp.tcc.model.MateriaSemestre;

@Repository
public interface AulaRegistradaRepository extends JpaRepository<AulaRegistrada, Long> {

	@Query("SELECT a FROM AulaRegistrada a WHERE a.dia = :dia AND (a.materiaSemestre.materia.curso.instituicao.id = :instituicaoId OR a.laboratorio.instituicao.id = :instituicaoId)")
	List<AulaRegistrada> findByDiaAndInstituicaoId(@Param("dia") LocalDate dia,
			@Param("instituicaoId") Long instituicaoId);

	List<AulaRegistrada> findByMateriaSemestre(MateriaSemestre materiaSemestre);
	@Query(value = "SELECT * FROM aula_registrada WHERE flg_status = :flgStatus", nativeQuery = true)
	List<AulaRegistrada> findAulasByStatus(@Param("flgStatus") String flgStatus);
}
