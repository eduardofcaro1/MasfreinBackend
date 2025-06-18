package br.com.ifsp.tcc.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.dto.AlterarSenhaDTO;
import br.com.ifsp.tcc.dto.CriarUsuarioDto;
import br.com.ifsp.tcc.dto.EditarUsuarioDTO;
import br.com.ifsp.tcc.dto.LoginUsuarioDto;
import br.com.ifsp.tcc.dto.UsuarioLogadoDto;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.repository.UsuarioRepository;
import br.com.ifsp.tcc.security.RecuperaJwtTokenDto;
import br.com.ifsp.tcc.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private UsuarioRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginUsuarioDto loginUserDto) {
		try {
			RecuperaJwtTokenDto token = userService.authenticateUser(loginUserDto);
			UsuarioLogadoDto usuario = new UsuarioLogadoDto();
			if (token != null) {
				Optional<UsuarioDashboard> usuarioLogou = userRepository.findByDscUsuario(loginUserDto.dscUsuario());
				if (usuarioLogou.isPresent()) {
					if ("N".equals(usuarioLogou.get().getFlgAtivo())) {
						return ResponseEntity.status(HttpStatus.FORBIDDEN)
								.body("Usuário aguardando aprovação pelo departamento administrativo.");
					}
					usuario.setId(usuarioLogou.get().getId());
					usuario.setDscUsuario(usuarioLogou.get().getDscUsuario());
					usuario.setInstituicao(usuarioLogou.get().getInstituicao());
					usuario.setToken(token.token());
					usuario.setFlgAtivo(usuarioLogou.get().getFlgAtivo());
					usuario.setCelular(usuarioLogou.get().getCelular());
					usuario.setIsAdmin(usuarioLogou.get().getIsAdmin());
					usuario.setNomeUsuario(usuarioLogou.get().getNomeUsuario());
					usuario.setFlgProfessor(usuarioLogou.get().getFlgProfessor());
					usuario.setFlgMobile(usuarioLogou.get().getFlgMobile());
				}
			}
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			String mensagemErro = "Falha na autenticação: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensagemErro);
		}
	}

	@PutMapping("/editarUsuario/{id}")
	public ResponseEntity<?> editarUsuario(@PathVariable Long id, @RequestBody EditarUsuarioDTO editarUsuarioDto) {
		Optional<UsuarioDashboard> usuarioOpt = userRepository.findById(id);

		if (!usuarioOpt.isPresent()) {
			return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
		}

		UsuarioDashboard usuario = usuarioOpt.get();

		if (usuario.getIsAdmin() == 1 && editarUsuarioDto.isAdmin() == 0) {
			long adminCount = userRepository.countByIsAdmin(1);
			if (adminCount <= 1) {
				return new ResponseEntity<>("Não é possível remover o último administrador.", HttpStatus.FORBIDDEN);
			}
		}

		usuario.setNomeUsuario(editarUsuarioDto.nomeUsuario());
		usuario.setFlgAtivo(editarUsuarioDto.flgAtivo());
		usuario.setCelular(editarUsuarioDto.celular());
		usuario.setIsAdmin(editarUsuarioDto.isAdmin());
		usuario.setFlgProfessor(editarUsuarioDto.flgProfessor());

		try {
			UsuarioDashboard usuarioAtualizado = userRepository.save(usuario);
			return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao atualizar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/alterarSenha/{id}")
	public ResponseEntity<?> alterarSenha(@PathVariable Long id, @RequestBody AlterarSenhaDTO alterarSenhaDto) {
		return userService.alterarSenha(id, alterarSenhaDto);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody CriarUsuarioDto createUserDto) {
		try {
			Optional<UsuarioDashboard> existingUser = userRepository.findByDscUsuario(createUserDto.dscUsuario());
			if (existingUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
			}
			UsuarioDashboard usuarioCriado = userService.createUser(createUserDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário");
		}
	}

	@PostMapping("/mobile")
	public ResponseEntity<UsuarioDashboard> createUserMobile(@RequestBody CriarUsuarioDto createUserDto) {
		try {
			Optional<UsuarioDashboard> existingUser = userRepository.findByDscUsuario(createUserDto.dscUsuario());
			if (existingUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}

			String dscUsuario = createUserDto.dscUsuario();
			if (createUserDto.flgMobile() == 1) {
				do {
					dscUsuario = "ct" + (int) (Math.random() * 1_000_0000);
				} while (userRepository.findByDscUsuario(dscUsuario).isPresent());
			}

			CriarUsuarioDto novoUsuarioDto = new CriarUsuarioDto(
					dscUsuario,
					createUserDto.senhaUsuario(),
					createUserDto.flgAtivo(),
					createUserDto.key(),
					createUserDto.celular(),
					createUserDto.nomeUsuario(),
					createUserDto.flgProfessor(),
					createUserDto.flgMobile());

			UsuarioDashboard usuarioCriado = userService.createUser(novoUsuarioDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/autentica")
	public ResponseEntity<Map<String, String>> getAuthenticationTest() {
		Map<String, String> response = new HashMap<>();
		response.put("mensagem", "Autenticado com sucesso");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/test/customer")
	public ResponseEntity<String> getCustomerAuthenticationTest() {
		return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
	}

	@GetMapping("/test/administrator")
	public ResponseEntity<String> getAdminAuthenticationTest() {
		return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
	}

}