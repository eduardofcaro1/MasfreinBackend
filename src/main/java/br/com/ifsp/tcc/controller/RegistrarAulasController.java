package br.com.ifsp.tcc.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.com.ifsp.tcc.dto.CursoSemestreDTO;
import br.com.ifsp.tcc.dto.NovaAulaDTO;
import br.com.ifsp.tcc.dto.RetornaAulasDiaDTO;
import br.com.ifsp.tcc.model.AulaRegistrada;
import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Laboratorio;
import br.com.ifsp.tcc.model.MateriaSemestre;
import br.com.ifsp.tcc.model.Notificacao;
import br.com.ifsp.tcc.model.Semestre;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.model.VwAulasDia;
import br.com.ifsp.tcc.repository.AulaRegistradaRepository;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.LaboratorioRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.MateriaSemestreRepository;
import br.com.ifsp.tcc.repository.NotificacaoRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;
import br.com.ifsp.tcc.repository.VwAulasDiaRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/registrarAula")
public class RegistrarAulasController {
	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private AulaRegistradaRepository aulaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private MateriaSemestreRepository materiaSemestreRepository;

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private VwAulasDiaRepository vwAulasDiaRepository;

	@Autowired
	private AulaRegistradaRepository aulaRegistradaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private NotificacaoRepository notificacaoRepository;

	@GetMapping("/retornaAulasProfessor/{id}")
	public List<VwAulasDia> retornaAulasProfessor(@PathVariable("id") Long id) {
	    LocalDate hoje = LocalDate.now();
	    LocalDate inicioSemana = hoje.with(DayOfWeek.MONDAY);
	    LocalDate fimSemana = hoje.with(DayOfWeek.SUNDAY);
		System.out.println("Inicio: "+inicioSemana+" Fim: "+fimSemana);
	    List<VwAulasDia> aulasDia = vwAulasDiaRepository.findByProfessorIdAndDiaBetween(id, inicioSemana, fimSemana);
	    return aulasDia.stream()
	        .filter(aula -> aula.getflgStatus() == null || !aula.getflgStatus().equalsIgnoreCase("N"))
	        .toList();
	}
	
	@PutMapping("/realocarAula/{aulaId}/{laboratorioId}")
	public ResponseEntity<?> realocarAula(
	        @PathVariable("aulaId") Long aulaId,
	        @PathVariable("laboratorioId") Long laboratorioId) {
	    Optional<AulaRegistrada> aulaOpt = aulaRegistradaRepository.findById(aulaId);
	    Optional<Laboratorio> labOpt = laboratorioRepository.findById(laboratorioId);

	    if (!aulaOpt.isPresent()) {
	        return new ResponseEntity<>("Aula não encontrada", HttpStatus.NOT_FOUND);
	    }
	    if (!labOpt.isPresent()) {
	        return new ResponseEntity<>("Laboratório não encontrado", HttpStatus.NOT_FOUND);
	    }

	    AulaRegistrada aula = aulaOpt.get();
	    aula.setLaboratorio(labOpt.get());
	    aulaRegistradaRepository.save(aula);

	    UsuarioDashboard professor = aula.getMateriaSemestre().getUsuario();
	    if (professor != null) {
	        Notificacao notificacao = new Notificacao();
	        notificacao.setMensagem("Sua aula '" + aula.getDescricao() + "' foi realocada para o laboratório '" + labOpt.get().getNome() + "'.");
	        notificacao.setDataCriacao(LocalDateTime.now());
	        notificacao.setAula(aula);
	        notificacao.setUsuario(professor);
	        notificacao.setVisualizado(false);
	        notificacaoRepository.save(notificacao);
	    }

	    return new ResponseEntity<>(aula, HttpStatus.OK);
	}
	@GetMapping("/retornaInfoAulas/{id}")
	public ResponseEntity<CursoSemestreDTO> retornaSemestre(@PathVariable Integer id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			CursoSemestreDTO cursoSemestreDTO = new CursoSemestreDTO();
			List<Curso> cursos = cursoRepository.findByInstituicao(instituicao.get());
			List<Semestre> semestres = semestreRepository.findByInstituicao(instituicao.get());
			cursoSemestreDTO.setCursos(cursos);
			cursoSemestreDTO.setSemestres(semestres);
			return new ResponseEntity<>(cursoSemestreDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaAulasDia/{dia}/{instituicaoId}")
	public List<AulaRegistrada> retornaAulasDia(
			@PathVariable("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia,
			@PathVariable("instituicaoId") Long instituicaoId) {
		return aulaRegistradaRepository.findByDiaAndInstituicaoId(dia, instituicaoId);
	}

	@GetMapping("/retornaAulasMateriaSemestre/{id}")
	public List<VwAulasDia> retornaAulasMateriaSemestre(@PathVariable("id") Long id) {
		List<VwAulasDia> aulasDia = vwAulasDiaRepository.findByMateriaSemestreId(id);
		return aulasDia;
	}

	@GetMapping("/retornaMateriaSemestre/{id}")
	public MateriaSemestre retornaMateriaSemestre(@PathVariable("id") Long id) {
		Optional<MateriaSemestre> materia = materiaSemestreRepository.findById(id);
		
		return materia.get();
	}
	@PostMapping("/excluirAula/{id}")
	public ResponseEntity<?> excluirAula(@PathVariable("id") Long id) {
	    Optional<AulaRegistrada> aula = aulaRegistradaRepository.findById(id);

	    if (!aula.isPresent()) {
	        return new ResponseEntity<>("Aula não encontrada", HttpStatus.NOT_FOUND);
	    }
	    try {
	        List<Notificacao> notificacoes = notificacaoRepository.findByAula(aula.get());
	        notificacaoRepository.deleteAll(notificacoes);

	        aulaRegistradaRepository.delete(aula.get());
	        return new ResponseEntity<>(aula, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Erro ao excluir aula", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PostMapping("/novaAula")
	public ResponseEntity<?> criaNovaAula(@RequestBody NovaAulaDTO novaAulaDTO) {
		AulaRegistrada aula = new AulaRegistrada();
		aula.setDescricao(novaAulaDTO.getDescricao());
		aula.setDia(novaAulaDTO.getDia());
		aula.setHoraInicio(novaAulaDTO.getHoraInicio());
		aula.setHoraFim(novaAulaDTO.getHoraFim());
		MateriaSemestre materiaSemestre = materiaSemestreRepository.findById(novaAulaDTO.getMateriaSemestreId()).get();
		aula.setMateriaSemestre(materiaSemestre);
		Laboratorio laboratorio = laboratorioRepository.findById(novaAulaDTO.getLaboratorioId()).get();
		aula.setLaboratorio(laboratorio);
		aula.setFlgStatus(novaAulaDTO.getFlgStatus());
		try {
			AulaRegistrada aulaRegistrada = aulaRegistradaRepository.save(aula);

			List<UsuarioDashboard> admins = usuarioRepository
					.findByInstituicaoAndIsAdmin(aulaRegistrada.getMateriaSemestre().getSemestre().getInstituicao(), 1);
			for (UsuarioDashboard admin : admins) {
				Notificacao notificacao = new Notificacao();
				notificacao.setMensagem("Nova aula pendente de aprovação: " + aulaRegistrada.getDescricao());
				notificacao.setDataCriacao(LocalDateTime.now());
				notificacao.setAula(aulaRegistrada);
				notificacao.setUsuario(admin);
				notificacao.setVisualizado(false);
				notificacaoRepository.save(notificacao);
			}
			return new ResponseEntity<>(aulaRegistrada, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao criar aula", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/retornaAulasDoDia/{dia}/{instituicaoId}")
	public ResponseEntity<?> retornaAulasDoDia(@PathVariable("dia") LocalDate dia,
			@PathVariable("instituicaoId") Long instituicaoId) {
		return new ResponseEntity<>(vwAulasDiaRepository.findByDiaAndInstituicaoId(dia, instituicaoId), HttpStatus.OK);
	}

	@GetMapping("/retornaTodasAulas/{instituicaoId}")
	public ResponseEntity<?> retornaTodasAulas(@PathVariable("instituicaoId") Long instituicaoId) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId).get();
		RetornaAulasDiaDTO aulasDia = new RetornaAulasDiaDTO();
		aulasDia.setAulasDia(vwAulasDiaRepository.findByInstituicaoId(instituicaoId));
		aulasDia.setProfessores(usuarioRepository.findByInstituicaoAndFlgProfessor(instituicao, 1));
		aulasDia.setCursos(cursoRepository.findByInstituicao(instituicao));
		return new ResponseEntity<>(aulasDia, HttpStatus.OK);
	}

	@GetMapping("/retornaTodasAulasSemestre/{instituicaoId}/{semestreId}")
	public ResponseEntity<?> retornaTodasAulasSemestre(@PathVariable("instituicaoId") Long instituicaoId,
			@PathVariable("semestreId") Long semestreId) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId).get();
		RetornaAulasDiaDTO aulasDia = new RetornaAulasDiaDTO();
		aulasDia.setAulasDia(vwAulasDiaRepository.findByInstituicaoIdAndSemestreId(instituicaoId, semestreId));
		aulasDia.setProfessores(usuarioRepository.findByInstituicaoAndFlgProfessor(instituicao, 1));
		aulasDia.setCursos(cursoRepository.findByInstituicao(instituicao));
		return new ResponseEntity<>(aulasDia, HttpStatus.OK);
	}

	@GetMapping("/retornaTodasAulasSemestre/{instituicaoId}/{semestreId}/{professorId}")
	public ResponseEntity<?> retornaAulaPorInstituticaoSemestreProfessor(
			@PathVariable("instituicaoId") Long instituicaoId, @PathVariable("semestreId") Long semestreId,
			@PathVariable("professorId") Long professorId) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId).get();
		RetornaAulasDiaDTO aulasDia = new RetornaAulasDiaDTO();
		aulasDia.setAulasDia(vwAulasDiaRepository.findByInstituicaoIdAndSemestreIdAndProfessorId(instituicaoId,
				semestreId, professorId));
		aulasDia.setProfessores(usuarioRepository.findByInstituicaoAndFlgProfessor(instituicao, 1));
		aulasDia.setCursos(cursoRepository.findByInstituicao(instituicao));
		return new ResponseEntity<>(aulasDia, HttpStatus.OK);
	}

	@PutMapping("/atualizaSatus/{id}/{status}")
	public ResponseEntity<?> atualizaStatus(@PathVariable Long id, @PathVariable String status) {
	    Optional<AulaRegistrada> aulaSalva = aulaRegistradaRepository.findById(id);
	    if (aulaSalva.isPresent()) {
	        AulaRegistrada aula = aulaSalva.get();
	        aula.setFlgStatus(status);
	        aulaRepository.save(aula);

	        UsuarioDashboard professor = aula.getMateriaSemestre().getUsuario();
	        if (professor != null) {
	            Notificacao notificacao = new Notificacao();
	            if ("A".equalsIgnoreCase(status)) {
	                notificacao.setMensagem("Sua aula '" + aula.getDescricao() + "' foi aprovada.");
	            } else if ("N".equalsIgnoreCase(status)) {
	                notificacao.setMensagem("Sua aula '" + aula.getDescricao() + "' foi reprovada.");
	            } else {
	                notificacao.setMensagem("O status da sua aula '" + aula.getDescricao() + "' foi atualizado para: " + status);
	            }
	            notificacao.setDataCriacao(LocalDateTime.now());
	            notificacao.setAula(aula);
	            notificacao.setUsuario(professor);
	            notificacao.setVisualizado(false);
	            notificacaoRepository.save(notificacao);
	        }

	        return new ResponseEntity<>(aula, HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Erro ao atualiazar aula", HttpStatus.NOT_FOUND);
	}
	@GetMapping("/retornaAulasPendentes")
	public ResponseEntity<List<AulaRegistrada>> retornaAulasPendentes() {
	    List<AulaRegistrada> aulasPendentes = aulaRegistradaRepository.findAulasByStatus("P");
	    return ResponseEntity.ok(aulasPendentes);
	}
}
