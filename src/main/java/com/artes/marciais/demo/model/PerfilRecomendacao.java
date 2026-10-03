package com.artes.marciais.demo.model;

public class PerfilRecomendacao {

	private String genero = "PREFIRO_NAO_INFORMAR";
	private Integer idade;
	private Integer alturaCm;
	private Double pesoKg;
	private String objetivo = "CONDICIONAMENTO";
	private String preferencia = "SEM_PREFERENCIA";
	private String experiencia = "INICIANTE";
	private String contato = "MODERADO";
	private boolean possuiRestricao;

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Integer getAlturaCm() {
		return alturaCm;
	}

	public void setAlturaCm(Integer alturaCm) {
		this.alturaCm = alturaCm;
	}

	public Double getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public boolean isPossuiRestricao() {
		return possuiRestricao;
	}

	public void setPossuiRestricao(boolean possuiRestricao) {
		this.possuiRestricao = possuiRestricao;
	}
}
