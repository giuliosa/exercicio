package br.aeso.exercicio1.fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioFornecedor {

	public void cadastrar(Fornecedor fornecedor) throws FornecedorJaCadastradoException;

	public void atualizar(Fornecedor fornecedor) throws FornecedorNaoEncontradoException;

	public void remover(String cpf) throws FornecedorNaoEncontradoException;

	public Fornecedor procurar(String cpf) throws FornecedorNaoEncontradoException;

	public boolean existe(String cpf);

	public ArrayList<Fornecedor> listar();
}
