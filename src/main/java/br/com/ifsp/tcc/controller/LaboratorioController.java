package br.com.ifsp.tcc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.ifsp.tcc.model.*;
import br.com.ifsp.tcc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifsp.tcc.dto.LaboratorioDTO;
import br.com.ifsp.tcc.dto.SemestreDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lab")
public class LaboratorioController {
    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Autowired
    private  LaboratorioAplicativosRepository laboratorioAplicativosRepository;

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LaboratorioDTO>> getLaboratoriosDisponiveis(
            @RequestParam("dia") LocalDate dia,
            @RequestParam("horaInicio") LocalTime horaInicio,
            @RequestParam("horaFim") LocalTime horaFim) {

        List<Laboratorio> laboratoriosDisponiveis = laboratorioRepository.findLaboratoriosDisponiveis(dia, horaInicio, horaFim);
        List<LaboratorioDTO> labsDTO = new ArrayList<>();
        for(Laboratorio lab : laboratoriosDisponiveis){
            LaboratorioDTO labDTO = new LaboratorioDTO();
            labDTO.setId(lab.getId());
            labDTO.setNome(lab.getNome());
            labDTO.setInstituicaoId(lab.getInstituicao().getId());
            labDTO.setQtdComputadores(lab.getQtdComputadores());
            labDTO.setQtdLugares(lab.getQtdLugares());
            labDTO.setInstituicao(lab.getInstituicao());
            for (LaboratorioAplicativos labApps : laboratorioAplicativosRepository.findByLaboratorioId(lab.getId())) {
                labDTO.getAplicativos().add(aplicativoRepository.findById(labApps.getAplicativo_id()).get());
            }
            labsDTO.add(labDTO);
        }
        return new ResponseEntity(labsDTO, HttpStatus.OK);
    }

    @GetMapping("/retornaLaboratorios/{id}")
    public ResponseEntity<List<LaboratorioDTO>> retornaLaboratorios(@PathVariable Integer id) {
        Optional<Instituicao> instituicao = instituicaoRepository.findById(id.longValue());
        if (!instituicao.isPresent()) {
            return null;
        } else {
            List<Laboratorio> labs = laboratorioRepository.findByInstituicao(instituicao.get());
            List<LaboratorioDTO> labsDTO = new ArrayList<>();
            for(Laboratorio lab : labs){
                LaboratorioDTO labDTO = new LaboratorioDTO();
                labDTO.setId(lab.getId());
                labDTO.setNome(lab.getNome());
                labDTO.setInstituicaoId(lab.getInstituicao().getId());
                labDTO.setQtdComputadores(lab.getQtdComputadores());
                labDTO.setQtdLugares(lab.getQtdLugares());
                labDTO.setInstituicao(lab.getInstituicao());
                for (LaboratorioAplicativos labApps : laboratorioAplicativosRepository.findByLaboratorioId(lab.getId())) {
                    labDTO.getAplicativos().add(aplicativoRepository.findById(labApps.getAplicativo_id()).get());
                }
                labsDTO.add(labDTO);
            }
            return new ResponseEntity<>(labsDTO, HttpStatus.OK);

        }
    }

    @GetMapping("/retornaLaboratorio/{id}")
    public ResponseEntity<LaboratorioDTO> retornaLaboratorio(@PathVariable Long id) {
        Optional<Laboratorio> lab = laboratorioRepository.findById(id);
        LaboratorioDTO labDTO = new LaboratorioDTO();
        if(lab.isPresent()) {
            labDTO.setNome(lab.get().getNome());
            labDTO.setInstituicaoId(lab.get().getInstituicao().getId());
            labDTO.setQtdComputadores(lab.get().getQtdComputadores());
            labDTO.setQtdLugares(lab.get().getQtdLugares());

            for (LaboratorioAplicativos labApps : laboratorioAplicativosRepository.findByLaboratorioId(lab.get().getId())) {
                labDTO.getAplicativos().add(aplicativoRepository.findById(labApps.getAplicativo_id()).get());
            }
            return new ResponseEntity<>(labDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/cadastrarLaboratorio")
    public ResponseEntity<?> cadastrarCurso(@RequestBody LaboratorioDTO laboratorioDTO) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(laboratorioDTO.getInstituicaoId());

        if (!instituicaoOpt.isPresent()) {
            return new ResponseEntity<>("Instituição não encontrada", HttpStatus.NOT_FOUND);
        }

        Instituicao instituicao = instituicaoOpt.get();
        Laboratorio lab = new Laboratorio();
        lab.setInstituicao(instituicao);
        lab.setNome(laboratorioDTO.getNome());
        lab.setQtdComputadores(laboratorioDTO.getQtdComputadores());
        lab.setQtdLugares(laboratorioDTO.getQtdLugares());


        try {
            Laboratorio labSalvo = laboratorioRepository.save(lab);
            for(Aplicativo app : laboratorioDTO.getAplicativos()){
                LaboratorioAplicativos labApps = new LaboratorioAplicativos();
                labApps.setlaboratorioId(labSalvo.getId());
                labApps.setAplicativo_id(app.getId());
                laboratorioAplicativosRepository.save(labApps);
            }
            return new ResponseEntity<>(labSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao cadastrar laboratório", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editarLaboratorio")
    public ResponseEntity<?> editaLaboratorio(@RequestBody LaboratorioDTO laboratorioDTO) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(laboratorioDTO.getInstituicaoId());

        if (!instituicaoOpt.isPresent()) {
            return new ResponseEntity<>("Instituição não encontrada", HttpStatus.NOT_FOUND);
        }

        Instituicao instituicao = instituicaoOpt.get();
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(laboratorioDTO.getId());
        if(laboratorio.isPresent()){
            Laboratorio lab = laboratorio.get();
            lab.setInstituicao(instituicao);
            lab.setNome(laboratorioDTO.getNome());
            lab.setQtdComputadores(laboratorioDTO.getQtdComputadores());
            lab.setQtdLugares(laboratorioDTO.getQtdLugares());

            try {
                laboratorioAplicativosRepository.deleteAllByLaboratorioId(lab.getId());
                Laboratorio labSalvo = laboratorioRepository.save(lab);
                for(Aplicativo app : laboratorioDTO.getAplicativos()){
                    LaboratorioAplicativos labApps = new LaboratorioAplicativos();
                    labApps.setlaboratorioId(labSalvo.getId());
                    labApps.setAplicativo_id(app.getId());
                    laboratorioAplicativosRepository.save(labApps);
                }
                return new ResponseEntity<>(labSalvo, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Erro ao editar laboratório", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Laboratorio não encontrado", HttpStatus.NOT_FOUND);
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
