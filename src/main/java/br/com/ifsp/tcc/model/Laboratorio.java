package br.com.ifsp.tcc.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", foreignKey = @ForeignKey(name = "usuario_dashboard_ifk_1"), nullable = true)
    private Instituicao instituicao;

    @Column(name = "qtd_computadores")
    private int qtdComputadores;

    @Column(name = "qtd_lugares")
    private int qtdLugares;

    public Laboratorio() {
        super();
    }

    public Laboratorio(Long id, String nome, Instituicao instituicao, int qtdComputadores, int qtdLugares) {
        super();
        this.id = id;
        this.nome = nome;
        this.instituicao = instituicao;
        this.qtdComputadores = qtdComputadores;
        this.qtdLugares = qtdLugares;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public int getQtdComputadores() {
        return qtdComputadores;
    }

    public void setQtdComputadores(int qtdComputadores) {
        this.qtdComputadores = qtdComputadores;
    }

    public int getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(int qtdLugares) {
        this.qtdLugares = qtdLugares;
    }

    public static LaboratorioBuilder builder() {
        return new LaboratorioBuilder();
    }

    public static class LaboratorioBuilder {
        private Long id;
        private String nome;
        private Instituicao instituicao;
        private int qtdLugares;
        private int qtdComputadores;

        private LaboratorioBuilder() {
        }

        public LaboratorioBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LaboratorioBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public LaboratorioBuilder instituicao(Instituicao instituicao) {
            this.instituicao = instituicao;
            return this;
        }

        public Laboratorio build() {
            return new Laboratorio(id, nome, instituicao, qtdComputadores, qtdLugares);
        }
    }

}
