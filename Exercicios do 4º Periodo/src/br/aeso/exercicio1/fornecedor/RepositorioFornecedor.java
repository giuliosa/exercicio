package br.aeso.exercicio1.fornecedor;

import java.util.ArrayList;
import java.util.Iterator;

public class RepositorioFornecedor implements IRepositorioFornecedor {

	private ArrayList<Fornecedor> fornecedores;
	public int acumulador;

	public RepositorioFornecedor() {
		fornecedores = new ArrayList<Fornecedor>();
		acumulador = 1;
	}

	@Override
	public void cadastrar(Fornecedor fornecedor)
			throws FornecedorJaCadastradoException {
		// TODO Auto-generated method stub
		if (this.existe(fornecedor.getCPF()))
			throw new FornecedorJaCadastradoException();
		fornecedor.setCodigo(acumulador);
		fornecedores.add(fornecedor);
		acumulador++;
	}

	@Override
	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		Fornecedor fornecedorProcurado = this.procurar(fornecedor.getCPF());
		fornecedor.setCodigo(fornecedorProcurado.getCodigo());
		// fornecedor.setEndereco(fornecedorProcurado.getEndereco());
		fornecedores.set(fornecedores.indexOf(fornecedorProcurado), fornecedor);
	}

	@Override
	public void remover(String cpf) throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		if (!this.existe(cpf))
			throw new FornecedorNaoEncontradoException();
		for (Iterator<Fornecedor> iter = fornecedores.iterator(); iter
				.hasNext();) {
			Fornecedor f = iter.next();
			if (f.getCPF().equals(cpf)) {
				iter.remove();
			}
		}
	}

	@Override
	public Fornecedor procurar(String cpf)
			throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		Fornecedor fornecedorProcurado = null;
		if (!this.existe(cpf))
			throw new FornecedorNaoEncontradoException();

		for (Fornecedor fornecedor : fornecedores) {
			if (fornecedor.getCPF().equals(cpf))
				fornecedorProcurado = fornecedor;
		}
		return fornecedorProcurado;
	}

	@Override
	public boolean existe(String cpf) {
		// TODO Auto-generated method stub
		boolean resposta = false;
		for (Fornecedor fornecedor : fornecedores) {
			if (fornecedor.getCPF().equals(cpf))
				resposta = true;
		}
		return resposta;
	}

	@Override
	public ArrayList<Fornecedor> listar() {
		// TODO Auto-generated method stub
		return fornecedores;
	}

}
