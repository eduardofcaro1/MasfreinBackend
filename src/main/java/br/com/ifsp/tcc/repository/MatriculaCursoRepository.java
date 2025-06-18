package br.com.ifsp.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.tcc.model.MatriculaCurso;

public interface MatriculaCursoRepository extends JpaRepository<MatriculaCurso, Long> {
	List<MatriculaCurso> findByUsuarioId(Long usuarioId);
	Optional<MatriculaCurso> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
	List<MatriculaCurso> findByCursoId(Long cursoId);
}
