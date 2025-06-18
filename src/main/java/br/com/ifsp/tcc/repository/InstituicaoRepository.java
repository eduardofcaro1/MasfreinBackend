package br.com.ifsp.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
	Instituicao findInstituicaoById(Long id);
	Instituicao findInstituicaoByKey(String key);
}