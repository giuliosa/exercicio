package br.aeso.exercicio1.fachada;

import java.io.IOException;
import java.util.ArrayList;

import br.aeso.exercicio1.endereco.EnderecoJaCadastradoException;
import br.aeso.exercicio1.endereco.EnderecoNaoEncontradoException;
import br.aeso.exercicio1.fornecedor.ControladorFornecedor;
import br.aeso.exercicio1.fornecedor.Fornecedor;
import br.aeso.exercicio1.fornecedor.FornecedorJaCadastradoException;
import br.aeso.exercicio1.fornecedor.FornecedorNaoEncontradoException;
import br.aeso.exercicio1.util.CPFInvalidoException;
import br.aeso.exercicio1.util.CampoNuloException;

public class Fachada {
	private static Fachada instance;
	private ControladorFornecedor controladorFornecedor;

	private Fachada() throws IOException {
		this.controladorFornecedor = new ControladorFornecedor();

	}

	public static Fachada getInstance() throws IOException {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	public void cadastrarFornecedor(Fornecedor fornecedor)
			throws EnderecoJaCadastradoException, CPFInvalidoException,
			FornecedorJaCadastradoException, CampoNuloException {
		this.controladorFornecedor.cadastrar(fornecedor);
	}

	public void atualizarFornecedor(Fornecedor fornecedor)
			throws EnderecoNaoEncontradoException,
			FornecedorNaoEncontradoException, CPFInvalidoException {
		this.controladorFornecedor.atualizar(fornecedor);
	}

	public void removerFornecedor(String cpf) throws CPFInvalidoException,
			EnderecoNaoEncontradoException, FornecedorNaoEncontradoException {
		this.controladorFornecedor.remover(cpf);
	}

	public Fornecedor procurarFornecedor(String cpf)
			throws CPFInvalidoException, FornecedorNaoEncontradoException {
		return this.controladorFornecedor.procurar(cpf);
	}

	public ArrayList<Fornecedor> listarFornecedor() {
		return this.controladorFornecedor.listar();
	}
}
