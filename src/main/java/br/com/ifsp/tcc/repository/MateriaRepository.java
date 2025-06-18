package br.com.ifsp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
	List<Materia> findByCurso(Curso curso);
}
