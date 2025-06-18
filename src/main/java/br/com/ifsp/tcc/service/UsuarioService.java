package br.com.ifsp.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.ifsp.tcc.dto.AlterarSenhaDTO;
import br.com.ifsp.tcc.dto.CriarUsuarioDto;
import br.com.ifsp.tcc.dto.LoginUsuarioDto;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;
import br.com.ifsp.tcc.security.JwtTokenService;
import br.com.ifsp.tcc.security.RecuperaJwtTokenDto;
import br.com.ifsp.tcc.security.SecurityConfiguration;
import br.com.ifsp.tcc.security.UserDetailsImpl;

@Service
public class UsuarioService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private SecurityConfiguration securityConfiguration;

	public RecuperaJwtTokenDto authenticateUser(LoginUsuarioDto loginUserDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginUserDto.dscUsuario(), loginUserDto.senhaUsuario());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return new RecuperaJwtTokenDto(jwtTokenService.generateToken(userDetails));
	}

	public UsuarioDashboard createUser(CriarUsuarioDto createUserDto) {
		var instituicao = instituicaoRepository.findInstituicaoByKey(createUserDto.key());
		if (instituicao == null) {
			throw new IllegalArgumentException("Instituição não encontrada");
		}

		UsuarioDashboard newUser = UsuarioDashboard.builder().dscUsuario(createUserDto.dscUsuario())
				.flgAtivo(createUserDto.flgAtivo()).celular(createUserDto.celular()).isAdmin(0)
				.nomeUsuario(createUserDto.nomeUsuario()).instituicao(instituicao)
				.flgProfessor(createUserDto.flgProfessor())
				.flgMobile(createUserDto.flgMobile())
				.senhaUsuario(securityConfiguration.passwordEncoder().encode(createUserDto.senhaUsuario())).build();

		return userRepository.save(newUser);
	}
	
	public ResponseEntity<?> alterarSenha(Long id, AlterarSenhaDTO alterarSenhaDto) {
        Optional<UsuarioDashboard> usuarioOpt = userRepository.findById(id);

        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        UsuarioDashboard usuario = usuarioOpt.get();

        if (!securityConfiguration.passwordEncoder().matches(alterarSenhaDto.getSenhaAntiga(), usuario.getSenhaUsuario())) {
            return new ResponseEntity<>("Senha antiga incorreta", HttpStatus.FORBIDDEN);
        }

        usuario.setSenhaUsuario(securityConfiguration.passwordEncoder().encode(alterarSenhaDto.getNovaSenha()));

        try {
            UsuarioDashboard usuarioAtualizado = userRepository.save(usuario);
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao alterar senha", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
