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

import br.com.ifsp.tcc.dto.CursoDTO;
import br.com.ifsp.tcc.dto.SemestreDTO;
import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Semestre;
import br.com.ifsp.tcc.repository.AulaRegistradaRepository;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/curso")
public class CursoController {
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

	@PostMapping("/cadastrarCurso")
	public ResponseEntity<?> cadastrarCurso(@RequestBody CursoDTO cursoDTO) {
		Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(cursoDTO.getInstituicaoId());

		if (!instituicaoOpt.isPresent()) {
			return new ResponseEntity<>("Instituição não encontrada", HttpStatus.NOT_FOUND);
		}

		Instituicao instituicao = instituicaoOpt.get();
		Curso curso = new Curso();
		curso.setInstituicao(instituicao);
		curso.setNome(cursoDTO.getNome());
		curso.setDuracaoSemestres(cursoDTO.getDuracaoSemestres());

		try {
			Curso cursoSalvo = cursoRepository.save(curso);
			return new ResponseEntity<>(cursoSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao cadastrar curso", HttpStatus.INTERNAL_SERVER_ERROR);
		}
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

	@PostMapping("/editarCurso")
	public ResponseEntity<?> editaCurso(@RequestBody CursoDTO cursoDTO) {

		Curso curso = cursoRepository.findById(cursoDTO.getId()).get();
		curso.setNome(cursoDTO.getNome());
		curso.setDuracaoSemestres(cursoDTO.getDuracaoSemestres());
		try {
			Curso cursoAtualizado = cursoRepository.save(curso);
			return new ResponseEntity<>(cursoAtualizado, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao atualizar curso", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
