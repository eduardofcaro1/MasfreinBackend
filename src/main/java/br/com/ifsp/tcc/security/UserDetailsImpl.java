package br.com.ifsp.tcc.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ifsp.tcc.model.UsuarioDashboard;
import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails {

	private UsuarioDashboard usuario;

	public UserDetailsImpl(UsuarioDashboard usuario) {
		this.usuario = usuario;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return usuario.getFuncoes().stream().map(role -> new SimpleGrantedAuthority(role.getNomeFuncao().name()))
//				.collect(Collectors.toList());
//	}

	@Override
	public String getPassword() {
		return usuario.getSenhaUsuario();
	}

	@Override
	public String getUsername() {
		return usuario.getDscUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
