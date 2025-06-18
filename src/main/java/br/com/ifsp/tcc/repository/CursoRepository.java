package br.com.ifsp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Instituicao;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	List<Curso> findByInstituicao(Instituicao instituicao);
}
