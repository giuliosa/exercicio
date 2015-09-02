package br.aeso.exercicio1.fornecedor;

public class FornecedorJaCadastradoException extends Exception {
	public FornecedorJaCadastradoException(){
		super("Fornecedor já cadastrado!");
	}
}
