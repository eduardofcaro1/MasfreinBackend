package br.com.ifsp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.tcc.model.AulaRegistrada;
import br.com.ifsp.tcc.model.Notificacao;
import br.com.ifsp.tcc.model.UsuarioDashboard;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
	List<Notificacao> findByUsuarioAndVisualizado(UsuarioDashboard usuario, boolean visualizado);

	List<Notificacao> findByAula(AulaRegistrada aula);
}