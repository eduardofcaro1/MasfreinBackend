package br.com.ifsp.tcc.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class NovaAulaDTO {
	private Long laboratorioId;
	private Long materiaSemestreId;
	private String descricao;
	private LocalDate dia;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String flg_satatus;

	public Long getLaboratorioId() {
		return laboratorioId;
	}

	public void setLaboratorioId(Long laboratorioId) {
		this.laboratorioId = laboratorioId;
	}

	public Long getMateriaSemestreId() {
		return materiaSemestreId;
	}

	public void setMateriaSemestreId(Long materiaSemestreId) {
		this.materiaSemestreId = materiaSemestreId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getFlgStatus(){
		return this.flg_satatus;
	}

	public void setFlgStatus(String flg_status){
		this.flg_satatus = flg_status;
	}


}
