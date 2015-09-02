package br.aeso.exercicio1.util;

import br.aeso.exercicio1.fornecedor.Fornecedor;

public class CamposNulos {

	public boolean estaVazio(Fornecedor fornecedor) {
		Boolean flag = false;
		if (fornecedor.getNome().trim().isEmpty()) {
			flag = true;
		} else if (fornecedor.getEndereco().getRua().trim().isEmpty()) {
			flag = true;
		} else if (fornecedor.getEndereco().getBairro().trim().isEmpty()) {
			flag = true;
		} else if (fornecedor.getEndereco().getCEP().trim().isEmpty()) {
			flag = true;
		} else if (fornecedor.getEndereco().getCidade().trim().isEmpty()) {
			flag = true;
		} else if (fornecedor.getEndereco().getEstado().trim().isEmpty()) {
			flag = true;
		} 
		return flag;
	}
}
