package br.aeso.exercicio1.fornecedor;

public class FornecedorNaoEncontradoException extends Exception {
	public FornecedorNaoEncontradoException(){
		super("Fornecedor não encontrado!");
	}
}
