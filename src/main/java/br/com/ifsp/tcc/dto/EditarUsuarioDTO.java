package br.com.ifsp.tcc.dto;

public record EditarUsuarioDTO(String nomeUsuario, String flgAtivo, String celular, Integer flgProfessor,
		Integer isAdmin) {
}
