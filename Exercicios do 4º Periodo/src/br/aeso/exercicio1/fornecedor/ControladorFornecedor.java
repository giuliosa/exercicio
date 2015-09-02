package br.aeso.exercicio1.fornecedor;

import java.util.ArrayList;

import br.aeso.exercicio1.endereco.ControladorEndereco;
import br.aeso.exercicio1.endereco.Endereco;
import br.aeso.exercicio1.endereco.EnderecoJaCadastradoException;
import br.aeso.exercicio1.endereco.EnderecoNaoEncontradoException;
import br.aeso.exercicio1.util.CPFInvalidoException;
import br.aeso.exercicio1.util.CampoNuloException;
import br.aeso.exercicio1.util.CamposNulos;
import br.aeso.exercicio1.util.ValidarCPF;

public class ControladorFornecedor {
	private IRepositorioFornecedor repositorioFornecedor;
	private ControladorEndereco controladorEndereco;
	private CamposNulos camposNulos;

	public ControladorFornecedor() {
		// this.repositorioFornecedor = new RepositorioFornecedor();
		this.repositorioFornecedor = new RepositorioFornecedorDAO();
		this.controladorEndereco = new ControladorEndereco();
		this.camposNulos = new CamposNulos();
	}

	public void cadastrar(Fornecedor fornecedor)
			throws EnderecoJaCadastradoException, CPFInvalidoException,
			FornecedorJaCadastradoException, CampoNuloException {

		if (fornecedor == null)
			throw new IllegalArgumentException("Fornecedor Inválido.");

		if (camposNulos.estaVazio(fornecedor))
			throw new CampoNuloException();

		if (!ValidarCPF.validaCPF(fornecedor.getCPF()))
			throw new CPFInvalidoException(fornecedor.getCPF());
		
		this.repositorioFornecedor.cadastrar(fornecedor);
		controladorEndereco.cadastrar(fornecedor.getEndereco());
	}

	public void atualizar(Fornecedor fornecedor)
			throws EnderecoNaoEncontradoException,
			FornecedorNaoEncontradoException, CPFInvalidoException {
		if (!ValidarCPF.validaCPF(fornecedor.getCPF()))
			throw new CPFInvalidoException(fornecedor.getCPF());

		this.repositorioFornecedor.atualizar(fornecedor);
		controladorEndereco.atualizar(fornecedor.getEndereco());
	}

	public void remover(String cpf) throws CPFInvalidoException,
			EnderecoNaoEncontradoException, FornecedorNaoEncontradoException {
		Fornecedor fornecedor = null;
		cpf = cpf.replaceAll("\\.|\\-|\\ ", "");
		if (!ValidarCPF.validaCPF(cpf))
			throw new CPFInvalidoException(cpf);

		fornecedor = this.procurar(cpf);
		controladorEndereco.remover(fornecedor.getCodigo());
		this.repositorioFornecedor.remover(cpf);

	}

	public Fornecedor procurar(String cpf) throws CPFInvalidoException,
			FornecedorNaoEncontradoException {
		Fornecedor fornecedor = null;
		// Endereco endereco = null;
		cpf = cpf.replaceAll("\\.|\\-|\\ ", "");

		if (!ValidarCPF.validaCPF(cpf))
			throw new CPFInvalidoException(cpf);

		fornecedor = this.repositorioFornecedor.procurar(cpf);

		Endereco endereco = controladorEndereco
				.procurarPorFornecedor(fornecedor.getCodigo());

		endereco.setFornecedor(fornecedor);
		fornecedor.setEndereco(endereco);

		return fornecedor;
	}

	public ArrayList<Fornecedor> listar() {
		ArrayList<Fornecedor> fornecedores = null;
		Endereco endereco = null;

		fornecedores = this.repositorioFornecedor.listar();

		for (Fornecedor fornecedor : fornecedores) {

			endereco = controladorEndereco.procurarPorFornecedor(fornecedor
					.getCodigo());

			endereco.setFornecedor(fornecedor);
			fornecedor.setEndereco(endereco);
		}
		return fornecedores;
	}
}
