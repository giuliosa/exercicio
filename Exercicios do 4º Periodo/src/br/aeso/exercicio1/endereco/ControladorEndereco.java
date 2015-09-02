package br.aeso.exercicio1.endereco;

import java.util.ArrayList;

public class ControladorEndereco {
	private IRepositorioEndereco repositorioEndereco;

	public ControladorEndereco() {
		//this.repositorioEndereco = new RepositorioEndereco();
		this.repositorioEndereco = new RepositorioEnderecoDAO();
	}

	public void cadastrar(Endereco endereco) throws EnderecoJaCadastradoException {
		if (endereco == null)
			throw new IllegalArgumentException("Endereço Inválido.");
		this.repositorioEndereco.cadastrar(endereco);
	}

	public void atualizar(Endereco endereco) throws EnderecoNaoEncontradoException {
		if (endereco == null)
			throw new IllegalArgumentException("Endereço Inválido.");
		this.repositorioEndereco.atualizar(endereco);
	}

	public void remover(Integer id) throws EnderecoNaoEncontradoException {
		this.repositorioEndereco.remover(id);
	}

	public Endereco procurar(Integer id) throws EnderecoNaoEncontradoException {
		return this.repositorioEndereco.procurar(id);
	}

	public ArrayList<Endereco> listar() {
		return this.repositorioEndereco.listar();
	}

	public Endereco procurarPorFornecedor(Integer id) {
		// TODO Auto-generated method stub
		return this.repositorioEndereco.procurarPorFornecedor(id);
	}
}
