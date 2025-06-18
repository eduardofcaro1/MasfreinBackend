package br.com.ifsp.tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.tcc.dto.ProfessorDTO;
import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.repository.CursoRepository;
import br.com.ifsp.tcc.repository.InstituicaoRepository;
import br.com.ifsp.tcc.repository.LaboratorioRepository;
import br.com.ifsp.tcc.repository.MateriaRepository;
import br.com.ifsp.tcc.repository.SemestreRepository;
import br.com.ifsp.tcc.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/professor")
public class ProfessorController {
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
	private SemestreRepository semestreRepository;

	@GetMapping("/retornaProfessores/{id}/{mobile}")
	public ResponseEntity<List<UsuarioDashboard>> retornaProfessores(@PathVariable Integer id,
			@PathVariable Integer mobile) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			List<UsuarioDashboard> professores = professorRepository.findByInstituicaoAndFlgMobile(instituicao.get(),
					mobile);
			return new ResponseEntity<>(professores, HttpStatus.OK);
		}
	}

	@GetMapping("/retornaUsuariosPendentes/{id}")
	public ResponseEntity<List<UsuarioDashboard>> retornaUsuariosPendentes(@PathVariable Integer id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
		if (!instituicao.isPresent()) {
			return null;
		} else {
			List<UsuarioDashboard> professores = professorRepository
					.findByInstituicaoAndFlgMobileAndFlgAtivoAndFlgProfessor(instituicao.get(), 0, "N", 0);
			return new ResponseEntity<>(professores, HttpStatus.OK);
		}
	}

	@PutMapping("/atualizarProfessor/{id}")
	public ResponseEntity<?> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
		Optional<UsuarioDashboard> professorOpt = professorRepository.findById(id);

		if (!professorOpt.isPresent()) {
			return new ResponseEntity<>("Professor não encontrado", HttpStatus.NOT_FOUND);
		}

		UsuarioDashboard professor = professorOpt.get();
		professor.setNomeUsuario(professorDTO.getNomeUsuario());
		professor.setFlgAtivo(professorDTO.getFlgAtivo());
		professor.setCelular(professorDTO.getCelular());
		professor.setIsAdmin(professorDTO.isAdmin() ? 1 : 0);
		professor.setFlgProfessor(professorDTO.isFlgProfessor() ? 1 : 0);

		try {
			UsuarioDashboard professorAtualizado = professorRepository.save(professor);
			return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao atualizar professor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
