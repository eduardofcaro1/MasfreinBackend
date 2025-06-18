package br.com.ifsp.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.UsuarioDashboard;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDashboard, Long> {
	Optional<UsuarioDashboard> findByDscUsuario(String dscUsuario);
	List<UsuarioDashboard> findByInstituicaoAndFlgProfessor(Instituicao instituicao, Integer flgProfessor);
	List<UsuarioDashboard> findByInstituicaoAndFlgMobile(Instituicao instituicao, Integer flgMobile);
	List<UsuarioDashboard> findByInstituicaoAndFlgMobileAndFlgAtivoAndFlgProfessor(Instituicao instituicao, Integer flgMobile,String flgAtivo, Integer flgProfessor);
	List<UsuarioDashboard> findByInstituicao(Instituicao instituicao);
	long countByIsAdmin(Integer isAdmin);
	List<UsuarioDashboard> findByFlgMobile(int flgMobile);
	List<UsuarioDashboard> findByInstituicaoAndIsAdmin(Instituicao instituicao, Integer isAdmin);
}