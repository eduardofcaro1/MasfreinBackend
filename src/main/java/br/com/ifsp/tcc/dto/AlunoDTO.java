package br.com.ifsp.tcc.dto;

public class AlunoDTO {

    private Long id;
    private String dscUsuario;
    private String nomeUsuario;
    private String celular;
    private InstituicaoDTO instituicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public InstituicaoDTO getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoDTO instituicao) {
        this.instituicao = instituicao;
    }

    public String getDscUsuario() {
        return dscUsuario;
    }

    public void setDscUsuario(String dscUsuario) {
        this.dscUsuario = dscUsuario;
    }
}