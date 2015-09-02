package br.aeso.exercicio1.util;

public class CampoNuloException extends Exception{
	public CampoNuloException(){
		super("Algum campo obrigatório está vazio!");
	}
}
