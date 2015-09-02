package br.aeso.exercicio1.fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import br.aeso.exercicio1.endereco.Endereco;
import br.aesoexercicio1.jdbc.ConnectionFactory;

public class RepositorioFornecedorDAO implements IRepositorioFornecedor {

	private Connection connection;

	public RepositorioFornecedorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Fornecedor fornecedor)
			throws FornecedorJaCadastradoException {
		int codigo = 0;
		// TODO Auto-generated method stub
		if (this.existe(fornecedor.getCPF()))
			throw new FornecedorJaCadastradoException();
		String sql = "insert into fornecedor " + "(nome,cpf,banco)"
				+ "values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCPF());
			stmt.setString(3, fornecedor.getBanco());

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				codigo = rs.getInt(1);
			}

			fornecedor.setCodigo(codigo);
			System.out.println();

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FornecedorJaCadastradoException();
		}
	}

	@Override
	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		String sql = "update fornecedor set " + "nome=?,banco=? where cpf=?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getBanco());
			stmt.setString(3, fornecedor.getCPF());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}

	@Override
	public void remover(String cpf) throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		Fornecedor fornecedor = this.procurar(cpf);
		try {
			PreparedStatement stmt = connection.prepareStatement("delete "
					+ "from fornecedor where codigo =?");
			stmt.setInt(1, fornecedor.getCodigo());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Fornecedor procurar(String cpf)
			throws FornecedorNaoEncontradoException {
		// TODO Auto-generated method stub
		Fornecedor fornecedorProcurado = null;
		String sql = "select * from fornecedor where cpf = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fornecedorProcurado = new Fornecedor(rs.getString("nome"),
						rs.getString("cpf"), rs.getString("banco"));
				fornecedorProcurado.setCodigo(rs.getInt("codigo"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return fornecedorProcurado;
	}

	@Override
	public boolean existe(String cpf) {
		// TODO Auto-generated method stub
		boolean resposta = false;
		ArrayList<String> listaCpf = new ArrayList<String>();
		String sql = "select cpf from fornecedor";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String cpfs;
				cpfs = rs.getString("cpf");
				listaCpf.add(cpfs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

		for (String string : listaCpf) {
			if (string == sql)
				resposta = true;
		}
		return resposta;
	}

	@Override
	public ArrayList<Fornecedor> listar() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from  fornecedor");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto contato
				Fornecedor fornecedor = new Fornecedor();

				// Pegar o resto dos dados para setar no endereço
				fornecedor.setCodigo(rs.getInt("codigo"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCPF(rs.getString("cpf"));
				fornecedor.setBanco(rs.getString("banco"));				

				// adicionando o objeto à lista
				fornecedores.add(fornecedor);
			}
			rs.close();
			stmt.close();
			return fornecedores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
