package br.aeso.exercicio1.endereco;

import java.util.ArrayList;

public interface IRepositorioEndereco {
	public void cadastrar(Endereco endereco);

	public void atualizar(Endereco endereco) throws EnderecoNaoEncontradoException;

	public void remover(int id) throws EnderecoNaoEncontradoException;

	public Endereco procurar(int id) throws EnderecoNaoEncontradoException;

	public boolean existe(int id);

	public ArrayList<Endereco> listar();

	public Endereco procurarPorFornecedor(Integer codigoDoFornecedor);

	
}
