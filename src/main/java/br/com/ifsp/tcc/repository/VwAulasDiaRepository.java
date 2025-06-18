package br.com.ifsp.tcc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.VwAulasDia;

@Repository
public interface VwAulasDiaRepository extends JpaRepository<VwAulasDia, Long> {
	List<VwAulasDia> findByDiaAndInstituicaoId(LocalDate dia, Long instituicaoId);
	List<VwAulasDia> findByInstituicaoId(Long instituicaoId);
	List<VwAulasDia> findByInstituicaoIdAndSemestreId(Long instituicaoId, Long idSemestre);
	List<VwAulasDia> findByInstituicaoIdAndSemestreIdAndProfessorId(Long instituicaoId, Long idSemestre,Long professorId);
	List<VwAulasDia> findByMateriaSemestreId(Long materiaSemestreId);
	List<VwAulasDia> findByProfessorId(Long professorId);
	List<VwAulasDia> findByProfessorIdAndDiaBetween(Long professorId, LocalDate inicioSemana, LocalDate fimSemana);
	List<VwAulasDia> findByMateriaIdAndDiaBetween(Long materiaId, LocalDate inicio, LocalDate fim);
}
