package br.aeso.exercicio1.endereco;

public class EnderecoJaCadastradoException extends Exception {
	public EnderecoJaCadastradoException () {
		super("Endereço já cadastrado!");
	}
}
