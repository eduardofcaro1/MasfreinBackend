package br.com.ifsp.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.model.Notificacao;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.repository.NotificacaoRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;

@RestController
@RequestMapping("/notificacao")
@CrossOrigin(origins = "*")
public class NotificacaoController {

	@Autowired
	private NotificacaoRepository notificacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/naoLidas/{usuarioId}")
	public ResponseEntity<List<Notificacao>> getNotificacoesNaoLidas(@PathVariable Long usuarioId) {
		UsuarioDashboard usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Notificacao> notificacoes = notificacaoRepository.findByUsuarioAndVisualizado(usuario, false);
		return ResponseEntity.ok(notificacoes);
	}

	@PutMapping("/marcarComoLida/{notificacaoId}")
	public ResponseEntity<?> marcarComoLida(@PathVariable Long notificacaoId) {
		return notificacaoRepository.findById(notificacaoId).map(notificacao -> {
			notificacao.setVisualizado(true);
			notificacaoRepository.save(notificacao);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/marcarTodasComoLidas/{usuarioId}")
	public ResponseEntity<?> marcarTodasComoLidas(@PathVariable Long usuarioId) {
		UsuarioDashboard usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Notificacao> notificacoes = notificacaoRepository.findByUsuarioAndVisualizado(usuario, false);
		for (Notificacao notificacao : notificacoes) {
			notificacao.setVisualizado(true);
		}
		notificacaoRepository.saveAll(notificacoes);
		return ResponseEntity.ok().build();
	}
}