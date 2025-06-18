package br.com.ifsp.tcc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Semestre;

public interface SemestreRepository extends JpaRepository<Semestre, Long>{
	List<Semestre> findByInstituicao(Instituicao instituicao);
	@Query("SELECT s FROM Semestre s WHERE :dataAtual BETWEEN s.dataInicio AND s.dataFim")
    Semestre findSemestreAtual(@Param("dataAtual") LocalDate dataAtual);
	Optional<Semestre> findByDescricao(String descricao);
}
