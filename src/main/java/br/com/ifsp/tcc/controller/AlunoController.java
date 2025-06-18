package br.com.ifsp.tcc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.dto.AlunoDTO;
import br.com.ifsp.tcc.dto.InstituicaoDTO;
import br.com.ifsp.tcc.dto.MatriculaCursoDTO;
import br.com.ifsp.tcc.model.AlunoMateriaSemestre;
import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.MatriculaCurso;
import br.com.ifsp.tcc.model.Semestre;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.model.VwAulasDia;
import br.com.ifsp.tcc.model.VwMateriasAluno;
import br.com.ifsp.tcc.repository.AlunoMateriaSemestreRepository;
import br.com.ifsp.tcc.repository.AulaRegistradaRepository;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.MatriculaCursoRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;
import br.com.ifsp.tcc.repository.VwAulasDiaRepository;
import br.com.ifsp.tcc.repository.VwMateriasAlunoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/aluno")
public class AlunoController {
	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private AulaRegistradaRepository aulaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private AulaRegistradaRepository aulaRegistradaRepository;

	@Autowired
	private VwMateriasAlunoRepository vwMateriasAlunoRepository;

	@Autowired
	private MatriculaCursoRepository matriculaCursoRepository;

	@Autowired
	private UsuarioRepository usuarioDashboardRepository;

	@Autowired
	private VwAulasDiaRepository vwAulasDiaRepository;

	@Autowired
	private AlunoMateriaSemestreRepository alunoMateriaSemestreRepository;

	@PostMapping("/alterarStatusMatricula")
	public ResponseEntity<?> alterarStatusMatricula(@RequestBody Map<String, Object> payload) {
	    Long usuarioId = Long.valueOf(payload.get("usuarioId").toString());
	    Long materiaSemestreId = Long.valueOf(payload.get("materiaSemestreId").toString());
	    String flgAceito = payload.get("flgAceito").toString();

	    Optional<AlunoMateriaSemestre> amsOpt = alunoMateriaSemestreRepository.findByUsuarioIdAndMateriaSemestreId(usuarioId, materiaSemestreId);
	    if (amsOpt.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    AlunoMateriaSemestre ams = amsOpt.get();
	    ams.setFlgAceito(flgAceito);
	    alunoMateriaSemestreRepository.save(ams);

	    return ResponseEntity.ok().build();
	}
	
	@PostMapping("/atribuirMateriasAoAluno")
	public ResponseEntity<?> atribuirMateriasAoAluno(@RequestBody List<Map<String, Long>> materiasAlunos) {
		List<AlunoMateriaSemestre> novasMaterias = new ArrayList<>();

		for (Map<String, Long> materiaAluno : materiasAlunos) {
			Long alunoId = materiaAluno.get("alunoId");
			Long materiaSemestreId = materiaAluno.get("materiaSemestreId");

			boolean existe = alunoMateriaSemestreRepository.existsByUsuarioIdAndMateriaSemestreId(alunoId,
					materiaSemestreId);

			if (!existe) {
				AlunoMateriaSemestre novaMateria = new AlunoMateriaSemestre();
				novaMateria.setUsuarioId(alunoId);
				novaMateria.setMateriaSemestreId(materiaSemestreId);
				novaMateria.setFlgAceito("S");
				novasMaterias.add(novaMateria);
			}
		}

		if (!novasMaterias.isEmpty()) {
			alunoMateriaSemestreRepository.saveAll(novasMaterias);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Materias atribuídas com sucesso.");
	}

	@PostMapping("/matricularAlunoNoCurso")
	public ResponseEntity<?> matricularAluno(@RequestParam Long usuarioId, @RequestParam Long cursoId) {
		Optional<MatriculaCurso> matriculaExistente = matriculaCursoRepository.findByUsuarioIdAndCursoId(usuarioId,
				cursoId);

		if (matriculaExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O aluno já está matriculado neste curso.");
		}

		Optional<Curso> curso = cursoRepository.findById(cursoId);
		Optional<UsuarioDashboard> usuario = usuarioDashboardRepository.findById(usuarioId);

		if (curso.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado.");
		}

		if (usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
		}

		MatriculaCurso novaMatricula = new MatriculaCurso();
		novaMatricula.setUsuario(usuario.get());
		novaMatricula.setCurso(curso.get());
		novaMatricula.setStatusMatricula('A');
		novaMatricula.setDataMatricula(LocalDate.now());

		try {
			matriculaCursoRepository.save(novaMatricula);
			return ResponseEntity.status(HttpStatus.CREATED).body("Aluno matriculado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao matricular o aluno.");
		}
	}

	@GetMapping("/retornaAlunosMatriculados/{cursoId}")
	public ResponseEntity<List<MatriculaCursoDTO>> retornaAlunosMatriculados(@PathVariable Long cursoId) {
		List<MatriculaCurso> alunosMatriculados = matriculaCursoRepository.findByCursoId(cursoId);
		List<MatriculaCursoDTO> alunosMatriculadosDTO = new ArrayList<>();
		for (MatriculaCurso aluno : alunosMatriculados) {
			MatriculaCursoDTO alunoDTO = new MatriculaCursoDTO();
			alunoDTO.setId(aluno.getId());
			alunoDTO.setUsuarioId(aluno.getUsuario().getId().intValue());
			alunoDTO.setNomeUsuario(aluno.getUsuario().getNomeUsuario());
			alunoDTO.setDscUsuario(aluno.getUsuario().getDscUsuario());
			alunoDTO.setCelular(aluno.getUsuario().getCelular());
			alunoDTO.setCurso(aluno.getCurso());
			alunoDTO.setDataMatricula(aluno.getDataMatricula());
			alunoDTO.setDataFimMatricula(aluno.getDataFimMatricula());
			alunoDTO.setStatusMatricula(aluno.getStatusMatricula());
			alunosMatriculadosDTO.add(alunoDTO);
		}

		return ResponseEntity.ok(alunosMatriculadosDTO);
	}

	@GetMapping("/retornaAlunosMatriculados")
	public ResponseEntity<List<MatriculaCursoDTO>> retornaAlunosMatriculados() {
		List<MatriculaCurso> alunosMatriculados = matriculaCursoRepository.findAll();
		List<MatriculaCursoDTO> alunosMatriculadosDTO = new ArrayList<>();
		for (MatriculaCurso aluno : alunosMatriculados) {
			MatriculaCursoDTO alunoDTO = new MatriculaCursoDTO();
			alunoDTO.setId(aluno.getId());
			alunoDTO.setUsuarioId(aluno.getUsuario().getId().intValue());
			alunoDTO.setNomeUsuario(aluno.getUsuario().getNomeUsuario());
			alunoDTO.setDscUsuario(aluno.getUsuario().getDscUsuario());
			alunoDTO.setCelular(aluno.getUsuario().getCelular());
			alunoDTO.setCurso(aluno.getCurso());
			alunoDTO.setDataMatricula(aluno.getDataMatricula());
			alunoDTO.setDataFimMatricula(aluno.getDataFimMatricula());
			alunoDTO.setStatusMatricula(aluno.getStatusMatricula());
			alunosMatriculadosDTO.add(alunoDTO);
		}
		if (alunosMatriculados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(alunosMatriculadosDTO);
	}

	@GetMapping("/retornaAlunos")
	public ResponseEntity<List<AlunoDTO>> retornaAlunos() {
		List<UsuarioDashboard> usuarios = usuarioDashboardRepository.findByFlgMobile(1);

		if (usuarios.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<AlunoDTO> alunosDTO = usuarios.stream().map(usuario -> {
			AlunoDTO alunoDTO = new AlunoDTO();
			alunoDTO.setId(usuario.getId());
			alunoDTO.setNomeUsuario(usuario.getNomeUsuario());
			alunoDTO.setCelular(usuario.getCelular());
			alunoDTO.setDscUsuario(usuario.getDscUsuario());

			if (usuario.getInstituicao() != null) {
				InstituicaoDTO instituicaoDTO = new InstituicaoDTO();
				instituicaoDTO.setId(usuario.getInstituicao().getId());
				instituicaoDTO.setNome(usuario.getInstituicao().getNome());
				alunoDTO.setInstituicao(instituicaoDTO);
			}

			return alunoDTO;
		}).toList();

		return ResponseEntity.ok(alunosDTO);
	}

	@GetMapping("/semestreAtual")
	public ResponseEntity<Semestre> getSemestreAtual() {
		LocalDate dataAtual = LocalDate.now();
		Semestre semestreAtual = semestreRepository.findSemestreAtual(dataAtual);

		if (semestreAtual != null) {
			return ResponseEntity.ok(semestreAtual);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/materias/{usuarioId}")
	public ResponseEntity<List<Map<String, Object>>> getMateriasByUsuario(@PathVariable Long usuarioId) {
		List<VwMateriasAluno> materias = vwMateriasAlunoRepository.findByUsuarioId(usuarioId);

		if (materias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<Map<String, Object>> response = new ArrayList<>();

		for (VwMateriasAluno materia : materias) {
			Map<String, Object> materiaComSemestre = new HashMap<>();
			materiaComSemestre.put("materia", materia);

			if (materia.getSemestreId() != null) {
				Optional<Semestre> semestre = semestreRepository.findById(materia.getSemestreId());
				semestre.ifPresent(value -> materiaComSemestre.put("semestre", value));
			}

			response.add(materiaComSemestre);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/materiasTodosAlunos")
	public ResponseEntity<List<Map<String, Object>>> getAllMaterias() {
		List<VwMateriasAluno> materias = vwMateriasAlunoRepository.findAll();

		if (materias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<Map<String, Object>> response = new ArrayList<>();

		for (VwMateriasAluno materia : materias) {
			Map<String, Object> materiaComSemestre = new HashMap<>();
			materiaComSemestre.put("materia", materia);

			if (materia.getSemestreId() != null) {
				Optional<Semestre> semestre = semestreRepository.findById(materia.getSemestreId());
				semestre.ifPresent(value -> materiaComSemestre.put("semestre", value));
			}

			response.add(materiaComSemestre);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/materias/{semestreId}/{usuarioId}")
	public ResponseEntity<List<VwMateriasAluno>> getMateriasBySemestreAndUsuario(@PathVariable Long semestreId,
			@PathVariable Long usuarioId) {
		List<VwMateriasAluno> materias = vwMateriasAlunoRepository.findBySemestreIdAndUsuarioId(semestreId, usuarioId);

		List<VwMateriasAluno> materiasAceitas = materias.stream().filter(m -> "S".equalsIgnoreCase(m.getFlgAceito()))
				.toList();

		if (materiasAceitas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.ok(materiasAceitas);
		}
	}

	@GetMapping("/curso/{usuarioId}")
	public ResponseEntity<List<MatriculaCursoDTO>> curso(@PathVariable Long usuarioId) {
		List<MatriculaCurso> cursosMatriculados = matriculaCursoRepository.findByUsuarioId(usuarioId);

		if (cursosMatriculados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			List<MatriculaCursoDTO> cursosDTO = cursosMatriculados.stream()
					.map(curso -> new MatriculaCursoDTO(curso.getId(), curso.getUsuario().getId().intValue(),
							curso.getCurso(), curso.getDataMatricula(), curso.getDataFimMatricula(),
							curso.getStatusMatricula()))
					.toList();
			return ResponseEntity.ok(cursosDTO);
		}
	}

	@GetMapping("/aulasSemana/{idAluno}/{dtaInicialSemana}/{dtaFinalSemana}/{idSemestre}")
	public ResponseEntity<List<VwAulasDia>> retornaAulasSemanaAluno(@PathVariable Long idAluno,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtaInicialSemana,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtaFinalSemana,
			@PathVariable Long idSemestre) {

		List<VwMateriasAluno> materias = vwMateriasAlunoRepository.findBySemestreIdAndUsuarioId(idSemestre, idAluno)
				.stream().filter(m -> "S".equalsIgnoreCase(m.getFlgAceito())).toList();

		if (materias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<VwAulasDia> aulasSemana = new ArrayList<>();

		for (VwMateriasAluno materia : materias) {
			List<VwAulasDia> aulas = vwAulasDiaRepository.findByMateriaIdAndDiaBetween(materia.getMateriaId(),
					dtaInicialSemana, dtaFinalSemana);
			aulas.stream().filter(aula -> aula.getflgStatus() != null && aula.getflgStatus().equalsIgnoreCase("A"))
					.forEach(aulasSemana::add);
		}

		if (aulasSemana.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(aulasSemana);
	}

	@GetMapping("/retornaAulunosPorMateria/{id}")
	public ResponseEntity<List<AlunoMateriaSemestre>> retornaAluanosPorMateria(@PathVariable Long id) {
		return new ResponseEntity<>(alunoMateriaSemestreRepository.findByMateriaSemestreId(id), HttpStatus.OK);
	}
}
