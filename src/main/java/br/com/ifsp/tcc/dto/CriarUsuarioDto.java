package br.com.ifsp.tcc.dto;

public record CriarUsuarioDto(String dscUsuario, String senhaUsuario, String flgAtivo, String key, String celular,
		String nomeUsuario, Integer flgProfessor, Integer flgMobile) {
}
