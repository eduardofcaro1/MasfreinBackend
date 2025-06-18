package br.com.ifsp.tcc.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.dto.CursoSemestreDTO;
import br.com.ifsp.tcc.dto.MateriaSemestreDTO;
import br.com.ifsp.tcc.dto.MateriaSemestreRetorno;
import br.com.ifsp.tcc.dto.RetornaMateriasProfessoresDisponiveisDTO;
import br.com.ifsp.tcc.dto.SemestreDTO;
import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.MateriaSemestre;
import br.com.ifsp.tcc.model.Semestre;
import br.com.ifsp.tcc.model.VwMateriasAluno;
import br.com.ifsp.tcc.model.VwMateriasSemestre;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.LaboratorioRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.MateriaSemestreRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;
import br.com.ifsp.tcc.repository.VwMateriasAlunoRepository;
import br.com.ifsp.tcc.repository.VwMateriasSemestreRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/materiaSemestre")
public class MateriasSemestreController {
	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Autowired
	private UsuarioRepository usuarioDashboardRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private MateriaSemestreRepository materiaSemestreRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private VwMateriasAlunoRepository vwMateriasAlunoRepository;

	@Autowired
	private VwMateriasSemestreRepository vwMateriasSemestreRepository;

	@GetMapping("/usuario/{usuarioId}/semestre/{semestreId}")
	public List<VwMateriasSemestre> getMateriasByUsuarioAndSemestre(
			@PathVariable Long usuarioId,
			@PathVariable Long semestreId) {
		return vwMateriasSemestreRepository.findByUsuarioIdAndSemestreId(usuarioId, semestreId);
	}

	@GetMapping("/retornaCursosSemestres/{id}")
	public ResponseEntity<CursoSemestreDTO> retornaCursosSemestre(@PathVariable Integer id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			CursoSemestreDTO cursoSemestreDTO = new CursoSemestreDTO();
			cursoSemestreDTO.setCursos(cursoRepository.findByInstituicao(instituicao.get()));
			cursoSemestreDTO.setSemestres(semestreRepository.findByInstituicao(instituicao.get()));
			return new ResponseEntity<>(cursoSemestreDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaMateriasProfessoresDisponiveis/{idCurso}/{idInstituicao}/{idSemestre}/{numeroModulo}")
	public ResponseEntity<RetornaMateriasProfessoresDisponiveisDTO> retornaMateriasProfessoresDisponiveis(
			@PathVariable Integer idCurso, @PathVariable Integer idInstituicao, @PathVariable Integer idSemestre,
			@PathVariable Integer numeroModulo) {

		Optional<Instituicao> instituicao = instituicaoRepository.findById(idInstituicao.longValue());
		Optional<Curso> curso = cursoRepository.findById(idCurso.longValue());

		if (!curso.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			RetornaMateriasProfessoresDisponiveisDTO retorno = new RetornaMateriasProfessoresDisponiveisDTO();

			List<MateriaSemestre> materiasSemestre = materiaSemestreRepository
					.findBySemestreIdAndMateriaCursoIdAndNumeroModulo(idSemestre.longValue(), idCurso.longValue(),
							numeroModulo);

			retorno.setMaterias(materiaRepository.findByCurso(curso.get()));
			retorno.setProfessores(usuarioDashboardRepository.findByInstituicaoAndFlgProfessor(instituicao.get(), 1));
			retorno.setMateriasSemestre(materiasSemestre);

			return new ResponseEntity<>(retorno, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaMateriasSemestres/{idCurso}/{idSemestre}")
	public List<MateriaSemestreRetorno> getMateriasSemestreByCurso(@PathVariable Integer idCurso,
			@PathVariable Integer idSemestre) {
		return materiaSemestreRepository.findBySemestreIdAndCursoId(idSemestre.longValue(), idCurso.longValue());
	}

	@PostMapping("/cadastrarMateriaSemestre")
	public ResponseEntity<?> cadastrarMateriaSemestre(@RequestBody MateriaSemestreDTO materiaSemestreDTO) {
		MateriaSemestre materiaSemestre = new MateriaSemestre();
		materiaSemestre.setMateria(materiaRepository.findById(materiaSemestreDTO.getMateria()).get());
		materiaSemestre.setSemestre(semestreRepository.findById(materiaSemestreDTO.getSemestre()).get());
		materiaSemestre.setUsuario(usuarioDashboardRepository.findById(materiaSemestreDTO.getProfessor()).get());
		materiaSemestre.setNumeroModulo(materiaSemestreDTO.getNumeroModulo());
		try {
			MateriaSemestre materiaSemestreSalva = materiaSemestreRepository.save(materiaSemestre);
			return new ResponseEntity<>(materiaSemestreSalva, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao cadastrar matéria no semestre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/retornaAlunosCursando/{id}")
	public List<VwMateriasAluno> getMateriasSemestreByCurso(@PathVariable Integer id) {
		return vwMateriasAlunoRepository.findByMateriaSemestreId(id.longValue());
	}

	@PostMapping("/editarSemestre")
	public ResponseEntity<?> editarSemestre(@RequestBody SemestreDTO semestreDTO) {

		Semestre semestre = semestreRepository.findById(semestreDTO.getId()).get();
		semestre.setDescricao(semestreDTO.getDescricao());
		semestre.setDataInicio(LocalDate.parse(semestreDTO.getDataInicio()));
		semestre.setDataFim(LocalDate.parse(semestreDTO.getDataFim()));
		semestre.setFlgAtivo(semestreDTO.getFlgAtivo());

		try {
			Semestre semestreAtualizado = semestreRepository.save(semestre);
			return new ResponseEntity<>(semestreAtualizado, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao atualizar semestre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
