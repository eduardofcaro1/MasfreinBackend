package br.com.ifsp.tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ifsp.tcc.dto.MateriaDTO;
import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Materia;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.LaboratorioRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.MateriaSemestreRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Autowired
	private UsuarioRepository professorRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private MateriaSemestreRepository materiaSemestreRepository;

	@Autowired
	private SemestreRepository semestreRepository;

	@GetMapping("/retornaCursos/{id}")
	public ResponseEntity<List<Curso>> retornaCursos(@PathVariable Integer id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			List<Curso> cursos = cursoRepository.findByInstituicao(instituicao.get());
			return new ResponseEntity<>(cursos, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaMateriasDoCurso/{id}")
	public ResponseEntity<List<Materia>> retornaMateriasCurso(@PathVariable Integer id) {
		Optional<Curso> curso = cursoRepository.findById(id.longValue());
		if (!curso.isPresent()) {
			return null;
		} else {
			List<Materia> materias = materiaRepository.findByCurso(curso.get());
			return new ResponseEntity<>(materias, HttpStatus.OK);
		}
	}

	@PostMapping("/cadastrarMateria")
	public ResponseEntity<?> cadastrarMateriaSemestre(@RequestBody MateriaDTO materiaDTO) {
		Materia materia = new Materia();
		materia.setNome(materiaDTO.getNome());
		materia.setSigla(materiaDTO.getSigla());
		materia.setQtdAulas(materiaDTO.getQtdAulas());
		materia.setCurso(cursoRepository.findById(materiaDTO.getCursoId().longValue()).get());

		try {
			Materia materiaSalva = materiaRepository.save(materia);
			return new ResponseEntity<>(materiaSalva, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao cadastrar matéria", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/atualizaMateria")
	public ResponseEntity<?> atualizaMateria(@RequestBody MateriaDTO materiaDTO) {
		Optional<Materia> materia = materiaRepository.findById(materiaDTO.getId());
		if (materia.isPresent()) {
			Materia materiaAntiga = materia.get();
			materiaAntiga.setNome(materiaDTO.getNome());
			materiaAntiga.setQtdAulas(materiaDTO.getQtdAulas());
			materiaAntiga.setSigla(materiaDTO.getSigla());
			try {
				Materia materiaSalva = materiaRepository.save(materiaAntiga);
				return new ResponseEntity<>(materiaSalva, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Erro ao editar matéria", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>("Erro ao editar matéria", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
