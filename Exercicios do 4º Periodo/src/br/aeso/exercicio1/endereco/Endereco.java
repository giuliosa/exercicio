package br.aeso.exercicio1.endereco;

import br.aeso.exercicio1.fornecedor.Fornecedor;

public class Endereco {
	private Integer codigoDoFornecedor;
	private Integer id;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String CEP;
	private Fornecedor fornecedor;
	

	public Endereco(String rua, String numero, String complemento,
			String bairro, String cidade, String estado, String CEP, Fornecedor fornecedor) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.setCEP(CEP);
		this.fornecedor = fornecedor;
	}
	
	public Endereco(String rua, String numero, String complemento,
			String bairro, String cidade, String estado, String CEP) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.CEP = CEP;		
	}
	
	public Endereco(){}

	public Integer getCodigoDoFornecedor() {
		return codigoDoFornecedor;
	}

	public void setCodigoDoFornecedor(Integer codigoDoFornecedor) {
		this.codigoDoFornecedor = codigoDoFornecedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP.replaceAll("\\.|\\-|\\ ", "");
	}
	
	public String getCepFormatado() {
		return CEP.substring(0,2) + "." + CEP.substring(2, 5) + "-" + CEP.substring(5, 8);
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return rua + ", nº " + numero + ", " + complemento + ", "
				+ bairro + ", " + cidade + "-" + estado + "-" + this.getCepFormatado();
	}
}
