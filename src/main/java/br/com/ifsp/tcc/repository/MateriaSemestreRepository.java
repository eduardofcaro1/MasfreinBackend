package br.com.ifsp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.MateriaSemestre;

@Repository
public interface MateriaSemestreRepository
		extends JpaRepository<MateriaSemestre, Long>, MateriaSemestreRepositoryCustom {
	List<MateriaSemestre> findBySemestreIdAndMateriaCursoIdAndNumeroModulo(Long semestreId, Long cursoId, Integer numeroModulo);
}