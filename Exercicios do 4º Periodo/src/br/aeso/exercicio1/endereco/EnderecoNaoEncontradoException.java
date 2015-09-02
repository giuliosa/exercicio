package br.aeso.exercicio1.endereco;

public class EnderecoNaoEncontradoException extends Exception {
	public EnderecoNaoEncontradoException(){
		super("Endereço não encontrado!");
	}
}
