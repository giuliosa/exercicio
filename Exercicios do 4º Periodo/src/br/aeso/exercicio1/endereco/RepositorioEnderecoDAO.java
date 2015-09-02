package br.aeso.exercicio1.endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aesoexercicio1.jdbc.ConnectionFactory;

public class RepositorioEnderecoDAO implements IRepositorioEndereco {

	// conexão com o banco de dados;
	private Connection connection;

	public RepositorioEnderecoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Endereco endereco) {
		// TODO Auto-generated method stub

		String sql = "insert into endereco "
				+ "(rua, numero, bairro, complemento, cidade, estado, CEP, codigoFornecedor)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());		
			stmt.setString(4, endereco.getComplemento());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getCEP());
			stmt.setInt(8, endereco.getFornecedor().getCodigo());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}

	@Override
	public void atualizar(Endereco endereco)
			throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		
		String sql = "update endereco set "
				+ "rua=?, numero=?,complemento=?, bairro=?, cidade=?, estado=?, cep=? where codigo = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getBairro());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getCEP());
			stmt.setInt(8, endereco.getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(int id) throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = connection.prepareStatement("delete "
					+ "from endereco where codigoFornecedor=?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Endereco procurar(int id) throws EnderecoNaoEncontradoException {
		// TODO Auto-generated method stub
		Endereco enderecoProcurado;
		String sql = "select * from endereco where codigo = " + id;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			enderecoProcurado = new Endereco(rs.getString("rua"),
					rs.getString("numero"), rs.getString("complemento"),
					rs.getString("bairro"), rs.getString("cidade"),
					rs.getString("estado"), rs.getString("CEP"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return enderecoProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Endereco> listar() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from endereco");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto contato
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("codigo"));
				endereco.setRua(rs.getString("rua"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCEP(rs.getString("CEP"));

				// adicionando o objeto à lista
				enderecos.add(endereco);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Endereco procurarPorFornecedor(Integer codigoDoFornecedor) {
		// TODO Auto-generated method stub
		Endereco enderecoProcurado = null;		
		String sql = "select * from endereco where codigoFornecedor = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, codigoDoFornecedor);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				enderecoProcurado = new Endereco(rs.getString("rua"),
						rs.getString("numero"), rs.getString("complemento"),
						rs.getString("bairro"), rs.getString("cidade"),
						rs.getString("estado"), rs.getString("CEP"));
				enderecoProcurado.setId(rs.getInt("codigo"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return enderecoProcurado;	
	}
	
}
