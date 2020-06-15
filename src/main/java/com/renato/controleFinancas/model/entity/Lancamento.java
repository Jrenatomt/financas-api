package com.renato.controleFinancas.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.renato.controleFinancas.model.enums.StatusLancamento;
import com.renato.controleFinancas.model.enums.TipoLancamento;

import lombok.Builder;

@Entity
@Table(schema = "financas")
@Builder
public class Lancamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "mes")
	private Integer mes;

	@Column(name = "valor")
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipo;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento status;

	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	
	
	public Lancamento () {	
	}


	public Lancamento(Long id, String descricao, Integer ano, Integer mes, BigDecimal valor, Usuario usuario,
			TipoLancamento tipo, StatusLancamento status, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ano = ano;
		this.mes = mes;
		this.valor = valor;
		this.usuario = usuario;
		this.tipo = tipo;
		this.status = status;
		this.dataCadastro = dataCadastro;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Integer getMes() {
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public TipoLancamento getTipo() {
		return tipo;
	}


	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}


	public StatusLancamento getStatus() {
		return status;
	}


	public void setStatus(StatusLancamento status) {
		this.status = status;
	}


	public LocalDate getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

	