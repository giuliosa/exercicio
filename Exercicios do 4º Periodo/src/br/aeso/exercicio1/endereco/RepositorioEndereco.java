package br.aeso.exercicio1.endereco;

import java.util.ArrayList;
import java.util.Iterator;

public class RepositorioEndereco implements IRepositorioEndereco {

	private ArrayList<Endereco> enderecos;
	private int indice;

	public RepositorioEndereco() {
		enderecos = new ArrayList<Endereco>();
		indice = 1;
	}

	@Override
	public void cadastrar(Endereco endereco) {
		// TODO Auto-generated method stub
		endereco.setId(indice);
		// endereco.getFornecedor().getCodigo();
		enderecos.add(endereco);
		indice++;
	}

	@Override
	public void atualizar(Endereco endereco)
			throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		Endereco enderecoProcurado = this.procurarPorFornecedor(endereco
				.getFornecedor().getCodigo());
		endereco.setId(enderecoProcurado.getId());
		enderecos.set(enderecos.indexOf(enderecoProcurado), endereco);
	}

	@Override
	public void remover(int id) throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		if (!this.existe(id))
			throw new EnderecoNaoEncontradoException();
		for (Iterator<Endereco> iter = enderecos.iterator(); iter.hasNext();) {
			Endereco endereco = iter.next();
			if (endereco.getId().equals(id)) {
				iter.remove();
			}
		}
	}

	@Override
	public Endereco procurar(int id) throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		Endereco enderecoProcurado = null;
		if (!this.existe(id))
			throw new EnderecoNaoEncontradoException();
		for (Endereco endereco : enderecos) {
			if (endereco.getId().equals(id))
				enderecoProcurado = endereco;
		}
		return enderecoProcurado;
	}

	@Override
	public Endereco procurarPorFornecedor(Integer codigoDoFornecedor) {
		// TODO Auto-generated method stub
		Endereco enderecoProcurado = null;
		for (Endereco endereco : enderecos) {
			if (endereco.getFornecedor().getCodigo().equals(codigoDoFornecedor)) {
				enderecoProcurado = endereco;
				break;
			}
		}
		return enderecoProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		boolean resposta = false;
		for (Endereco endereco : enderecos) {
			if (endereco.getId().equals(id))
				resposta = true;
		}
		return resposta;
	}

	@Override
	public ArrayList<Endereco> listar() {
		// TODO Auto-generated method stub
		return enderecos;
	}

}
