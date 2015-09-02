package br.aeso.exercicio1.fornecedor;

import br.aeso.exercicio1.endereco.Endereco;

public class Fornecedor {
	private Integer codigo;
	private String nome;
	private String CPF;
	private String banco;
	private Endereco endereco;
	//private static int acumulador;

	public Fornecedor(String nome, String CPF, String banco) {
		setNome(nome);
		setCPF(CPF);
		setBanco(banco);
		
	}
	
	public Fornecedor(){}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF.replaceAll("\\.|\\-|\\ ", "");
	}

	public String getCpfFormatado() {
		return CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
				+ CPF.substring(6, 9) + "-" + CPF.substring(9, 11);
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Fornecedor cod: " + codigo + "\n" + nome + "\nCPF: " + this.getCpfFormatado()
				+ "\n" + banco + "\n" + endereco + "\n+++++++++++++++++++++++++++++++++++++++++++";
	}

}
