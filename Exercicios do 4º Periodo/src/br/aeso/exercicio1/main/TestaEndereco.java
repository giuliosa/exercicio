package br.aeso.exercicio1.main;

import java.io.IOException;
import java.util.ArrayList;
import br.aeso.exercicio1.endereco.Endereco;
import br.aeso.exercicio1.endereco.EnderecoJaCadastradoException;
import br.aeso.exercicio1.endereco.EnderecoNaoEncontradoException;
import br.aeso.exercicio1.fachada.Fachada;
import br.aeso.exercicio1.fornecedor.Fornecedor;
import br.aeso.exercicio1.fornecedor.FornecedorJaCadastradoException;
import br.aeso.exercicio1.fornecedor.FornecedorNaoEncontradoException;
import br.aeso.exercicio1.util.CPFInvalidoException;
import br.aeso.exercicio1.util.CampoNuloException;
import br.aeso.exercicio1.util.ValidarCPF;

public class TestaEndereco {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FornecedorNaoEncontradoException
	 * @throws EnderecoNaoEncontradoException
	 * @throws CPFInvalidoException
	 */
	public static void main(String[] args) throws IOException,
			CPFInvalidoException, EnderecoNaoEncontradoException,
			FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		Fachada fachada = Fachada.getInstance();
		Fornecedor f1 = new Fornecedor("Giulio Caetano Pires de Sá",
				"097.509.064-03", "Caixa");
		
		Endereco e1 = new Endereco("Rua Rutilo", "18", " ", "Jardim Atlântico",
				"Olinda", "PE", "53.060-360",f1);
		
		f1.setEndereco(e1);
		
		Fornecedor f2 = new Fornecedor("Um Dois Tres de Oliveira Quatro",
				"345.135.354-74", "Itau");		

		Endereco e2 = new Endereco("Rua dos bobos", "0", "Casa engraçada ",
				"Jardim Atlântico", "Olinda", "PE", "53.060-362", f2);
		
		f2.setEndereco(e2);
				
		Fornecedor f3 = new Fornecedor("Mucambu Canfundó",
				"111.111.111-11", "Bradesco");		

		Endereco e3 = new Endereco("Avenida Cesar Romero", "45", "Palhaço",
				"Rio Doce", "Olinda", "PE", "53.063-365", f3);
		
		f3.setEndereco(e3);
		System.out.println(ValidarCPF.validaCPF(f1.getCPF()));
		
		try {			
			fachada.cadastrarFornecedor(f1);			
			fachada.cadastrarFornecedor(f2);
			fachada.cadastrarFornecedor(f3);
		} catch (EnderecoJaCadastradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (FornecedorJaCadastradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (CampoNuloException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		
		ArrayList<Fornecedor> lista = fachada.listarFornecedor();
		for (Fornecedor fornecedor : lista) {
			System.out.println(fornecedor);
		}
		
		System.out.println("\nAtualizando\n");	
		
		/*
		try {
			Fornecedor f4 = fachada.procurarFornecedor("09750906403");
			f4.getEndereco().setComplemento("Perto da eskina da coxinha");
			fachada.atualizarFornecedor(f4);
		} catch (EnderecoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} 
		
		
		
		lista = fachada.listarFornecedor();
		for (Fornecedor fornecedor : lista) {
			System.out.println(fornecedor);
		}*/
		/*
		System.out.println("\nRemovendo 097.509.064-03\n");		
		
		try {
			fachada.removerFornecedor("097.509.064-03");
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (EnderecoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}*/
		/*
		lista = fachada.listarFornecedor();
		for (Fornecedor fornecedor : lista) {
			System.out.println(fornecedor);
		}
		
		System.out.println("\nProcurando o fornecedor 345.135.354-74\n"+fachada.procurarFornecedor("345.135.354-74"));
		 */
	}

}
