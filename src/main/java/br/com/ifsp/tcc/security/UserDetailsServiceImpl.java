package br.com.ifsp.tcc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioDashboard user = userRepository.findByDscUsuario(username)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return new UserDetailsImpl(user);
	}

}