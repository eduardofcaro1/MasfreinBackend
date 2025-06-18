package br.com.ifsp.tcc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UsuarioAutenticacaoFiltro userAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeHttpRequests().requestMatchers(ENDPOINTS_SEM_AUTENTICACAO).permitAll()
				.requestMatchers(ENDPOINTS_COM_AUTENTICACAO).authenticated().requestMatchers(ENDPOINTS_ADMIN)
				.hasRole("ADMIN").requestMatchers(ENDPOINTS_CLIENTE).hasRole("CLIENTE").anyRequest().denyAll().and()
				.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static final String[] ENDPOINTS_SEM_AUTENTICACAO = { "/users/login", "/users", "/usermobile",
			"/usermobile/login", "/users/mobile" };

	// Endpoints que requerem autenticação para serem acessados
	public static final String[] ENDPOINTS_COM_AUTENTICACAO = { "/aula/test/{id}", "/aula/retornaSemestres/{id}",
			"/aula/cadastrarSemestre", "/aula/editarSemestre", "/curso/retornaCursos/{id}", "/curso/cadastrarCurso",
			"/curso/editarCurso", "/lab/retornaLaboratorios/{id}", "/lab/retornaLaboratorio/{id}",
			"/lab/cadastrarLaboratorio", "/lab/editarLaboratorio", "/lab/disponiveis",
			"/registrarAula/retornaAulasProfessor/{id}", "/professor/retornaProfessores/{id}/{mobile}",
			"/professor/cadastrarProfessor", "/professor/atualizarProfessor/{id}",
			"/professor/retornaUsuariosPendentes/{id}", "/materiaSemestre/retornaCursosSemestres/{id}",
			"/materiaSemestre/retornaMateriasSemestres/{idCurso}/{idSemestre}",
			"/materiaSemestre/retornaMateriasProfessoresDisponiveis/{idCurso}/{idInstituicao}/{idSemestre}/{numeroModulo}",
			"/materiaSemestre/cadastrarMateriaSemestre", "/materia/retornaCursos/{id}", "/materia/cadastrarMateria",
			"/materia/retornaMateriasDoCurso/{id}", "/materia/atualizaMateria",
			"/aula/retornaAulasDia/{dia}/{instituicaoId}", "/registrarAula/retornaInfoAulas/{id}",
			"/registrarAula/retornaAulasDia/{dia}/{instituicaoId}", "/registrarAula/retornaAulasMateriaSemestre/{id}",
			"/registrarAula/atualizaSatus/{id}/{status}", "/registrarAula/excluirAula/{id}", "/registrarAula/novaAula",
			"/registrarAula/retornaAulasDoDia/{dia}/{instituicaoId}",
			"/registrarAula/retornaTodasAulas/{instituicaoId}",
			"/registrarAula/retornaTodasAulasSemestre/{instituicaoId}/{semestreId}",
			"/registrarAula/retornaTodasAulasSemestre/{instituicaoId}/{semestreId}/{professorId}",
			"/materiaSemestre/usuario/{usuarioId}/semestre/{semestreId}", "/users/editarUsuario/{id}",
			"/users/alterarSenha/{id}", "/aplicativos", "/aplicativos/{id}", "/aluno/semestreAtual", "/users/autentica",
			"/aluno/materias/{semestreId}/{usuarioId}", "/aluno/curso/{usuarioId}",
			"/aluno/aulasSemana/{idAluno}/{dtaInicialSemana}/{dtaFinalSemana}/{idSemestre}",
			"/aluno/retornaAulunosPorMateria/{id}", "/aluno/materias/{usuarioId}", "/aluno/retornaAlunos",
			"/aluno/retornaAlunosMatriculados", "/aluno/materiasTodosAlunos", "/aluno/matricularAlunoNoCurso",
			"/aluno/retornaAlunosMatriculados/{cursoId}", "/aluno/atribuirMateriasAoAluno",
			"/notificacao/naoLidas/{usuarioId}", "/notificacao/marcarComoLida/{notificacaoId}",
			"/notificacao/marcarTodasComoLidas/{usuarioId}", "/registrarAula/retornaAulasPendentes",
			"/registrarAula/realocarAula/{aulaId}/{laboratorioId}",
			"/registrarAula/retornaMateriaSemestre/{materiaSemestreId}", "/materiaSemestre/retornaAlunosCursando/{id}",
			"/aluno/alterarStatusMatricula"};

	// Endpoints que só podem ser acessador por usuários com permissão de cliente
	public static final String[] ENDPOINTS_CLIENTE = { "/api/estoque/getProdutos", "/api/estoque/getProduto/{id}",
			"/api/estoque/registrarMovimentacao", "/api/estoque/getMovimentacoes",
			"/api/estoque/getMovimentacaoProduto/{idProduto}", "/api/estoque/getMovimentacaoFornecedor/{idFornecedor}",

	};

	// Endpoints que só podem ser acessador por usuários com permissão de
	// administrador
	public static final String[] ENDPOINTS_ADMIN = { "/api/estoque/getProdutos", "/api/estoque/getProduto/{id}",
			"/api/estoque/cadastrarProduto", "/api/estoque/cadastrarFornecedor", "/api/estoque/alterarProduto/{id}",
			"/api/estoque/excluirProduto/{id}", "/api/estoque/registrarMovimentacao", "/api/estoque/getMovimentacoes",
			"/api/estoque/getMovimentacaoProduto/{idProduto}",
			"/api/estoque/getMovimentacaoFornecedor/{idFornecedor}", };

}