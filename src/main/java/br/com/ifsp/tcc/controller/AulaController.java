package br.com.ifsp.tcc.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.dto.SemestreDTO;
import br.com.ifsp.tcc.model.AulaRegistrada;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Semestre;
import br.com.ifsp.tcc.repository.AulaRegistradaRepository;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/aula")
public class AulaController {
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

	@GetMapping("/retornaSemestres/{id}")
	public ResponseEntity<List<Semestre>> retornaSemestre(@PathVariable Integer id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			List<Semestre> semestres = semestreRepository.findByInstituicao(instituicao.get());
			return new ResponseEntity<>(semestres, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaAulasDia/{dia}/{instituicaoId}")
	public List<AulaRegistrada> retornaAulasDia(
			@PathVariable("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia,
			@PathVariable("instituicaoId") Long instituicaoId) {
		return aulaRegistradaRepository.findByDiaAndInstituicaoId(dia, instituicaoId);
	}

	@PostMapping("/cadastrarSemestre")
	public ResponseEntity<?> cadastrarSemestre(@RequestBody SemestreDTO semestreDTO) {
		Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(semestreDTO.getInstituicaoId());

		if (!instituicaoOpt.isPresent()) {
			return new ResponseEntity<>("Instituição não encontrada", HttpStatus.NOT_FOUND);
		}
		Optional<Semestre> descSemestre = semestreRepository.findByDescricao(semestreDTO.getDescricao());
		if (descSemestre.isPresent()) {
			return new ResponseEntity<>(descSemestre.get(), HttpStatus.OK);
		}

		Instituicao instituicao = instituicaoOpt.get();
		Semestre semestre = new Semestre();
		semestre.setInstituicao(instituicao);
		semestre.setDescricao(semestreDTO.getDescricao());
		semestre.setDataInicio(LocalDate.parse(semestreDTO.getDataInicio()));
		semestre.setDataFim(LocalDate.parse(semestreDTO.getDataFim()));
		semestre.setFlgAtivo(semestreDTO.getFlgAtivo());

		try {
			Semestre semestreSalvo = semestreRepository.save(semestre);
			return new ResponseEntity<>(semestreSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao cadastrar semestre", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
