package br.aeso.exercicio1.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.ComponentOrientation;

import br.aeso.exercicio1.endereco.Endereco;
import br.aeso.exercicio1.endereco.EnderecoJaCadastradoException;
import br.aeso.exercicio1.endereco.EnderecoNaoEncontradoException;
import br.aeso.exercicio1.fachada.Fachada;
import br.aeso.exercicio1.fornecedor.Fornecedor;
import br.aeso.exercicio1.fornecedor.FornecedorJaCadastradoException;
import br.aeso.exercicio1.fornecedor.FornecedorNaoEncontradoException;
import br.aeso.exercicio1.util.CPFInvalidoException;
import br.aeso.exercicio1.util.CampoNuloException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JScrollPane;

public class TesteDeTela extends JFrame {

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoBanco;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoComplemento;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JFormattedTextField campoCPF;
	private JFormattedTextField campoCEP;
	Fachada fachada = Fachada.getInstance();
	private DefaultTableModel defaultTableModel;
	private JTable tabelaFornecedor;
	private JTable tabelaEndereco;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteDeTela frame = new TesteDeTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TesteDeTela() throws IOException {
		inicializa();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public void inicializa() throws IOException {
		// Fachada fachada = Fachada.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBounds(12, 12, 576, 49);
		contentPane.add(painelBotoes);
		painelBotoes.setLayout(null);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cadastrar();
				} catch (CampoNuloException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botaoCadastrar.setBounds(8, 12, 105, 25);
		painelBotoes.add(botaoCadastrar);

		JButton botaoRemover = new JButton("Remover");
		botaoRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		botaoRemover.setBounds(121, 12, 105, 25);
		painelBotoes.add(botaoRemover);

		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		botaoAtualizar.setBounds(234, 12, 105, 25);
		painelBotoes.add(botaoAtualizar);

		JButton botaoProcurar = new JButton("Procurar");
		botaoProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurar();
			}
		});
		botaoProcurar.setBounds(347, 12, 105, 25);
		painelBotoes.add(botaoProcurar);

		JButton botaoListar = new JButton("Listar");
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar();
			}
		});
		botaoListar.setBounds(460, 12, 105, 25);
		painelBotoes.add(botaoListar);

		JPanel painelFornecedor = new JPanel();
		painelFornecedor.setBounds(12, 62, 576, 71);
		contentPane.add(painelFornecedor);
		painelFornecedor.setLayout(null);

		JLabel labelNome = new JLabel("Nome*");
		labelNome.setBounds(12, 12, 46, 15);
		painelFornecedor.add(labelNome);

		JLabel labelCPF = new JLabel("CPF*");
		labelCPF.setBounds(12, 39, 70, 15);
		painelFornecedor.add(labelCPF);

		campoNome = new JTextField();
		campoNome.setBounds(61, 10, 503, 23);
		painelFornecedor.add(campoNome);
		campoNome.setColumns(10);

		try {
			campoCPF = new JFormattedTextField(new MaskFormatter(
					"###.###.###-##"));
			campoCPF.setBounds(61, 37, 111, 23);
			painelFornecedor.add(campoCPF);

			JLabel labelBanco = new JLabel("Banco");
			labelBanco.setBounds(184, 39, 60, 15);
			painelFornecedor.add(labelBanco);

			campoBanco = new JTextField();
			campoBanco.setBounds(239, 37, 325, 23);
			painelFornecedor.add(campoBanco);
			campoBanco.setColumns(10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel painelEndereco = new JPanel();
		painelEndereco.setBounds(12, 136, 576, 94);
		contentPane.add(painelEndereco);
		painelEndereco.setLayout(null);

		JLabel labelRua = new JLabel("Rua*");
		labelRua.setBounds(8, 7, 33, 15);
		painelEndereco.add(labelRua);

		campoRua = new JTextField();
		campoRua.setBounds(52, 5, 442, 19);
		painelEndereco.add(campoRua);
		campoRua.setColumns(10);

		JLabel labelNumero = new JLabel("Nº");
		labelNumero.setBounds(498, 7, 17, 15);
		painelEndereco.add(labelNumero);

		campoNumero = new JTextField();
		campoNumero.setBounds(516, 5, 48, 19);
		painelEndereco.add(campoNumero);
		campoNumero.setColumns(10);

		JLabel labelComplemento = new JLabel("Complemento");
		labelComplemento.setBounds(8, 34, 97, 15);
		painelEndereco.add(labelComplemento);

		campoComplemento = new JTextField();
		campoComplemento.setBounds(117, 36, 208, 19);
		painelEndereco.add(campoComplemento);
		campoComplemento.setColumns(10);

		JLabel labelCep = new JLabel("CEP*");
		labelCep.setBounds(343, 38, 70, 15);
		painelEndereco.add(labelCep);

		try {
			campoCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
			campoCEP.setBounds(384, 36, 131, 19);
			painelEndereco.add(campoCEP);

			JLabel labelBairro = new JLabel("Bairro*");
			labelBairro.setBounds(8, 61, 49, 15);
			painelEndereco.add(labelBairro);

			campoBairro = new JTextField();
			campoBairro.setBounds(69, 59, 131, 19);
			painelEndereco.add(campoBairro);
			campoBairro.setColumns(10);

			JLabel labelCidade = new JLabel("Cidade*");
			labelCidade.setBounds(206, 61, 55, 15);
			painelEndereco.add(labelCidade);

			campoCidade = new JTextField();
			campoCidade.setBounds(258, 59, 165, 19);
			painelEndereco.add(campoCidade);
			campoCidade.setColumns(10);

			JLabel labelEstado = new JLabel("Estado*");
			labelEstado.setBounds(428, 61, 55, 15);
			painelEndereco.add(labelEstado);

			campoEstado = new JTextField();
			campoEstado.setBounds(494, 59, 70, 19);
			painelEndereco.add(campoEstado);
			campoEstado.setColumns(10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel painelLista = new JPanel();
		painelLista.setBounds(12, 242, 576, 218);
		contentPane.add(painelLista);
		painelLista.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 552, 109);
		painelLista.add(scrollPane);

		tabelaFornecedor = new JTable();
		scrollPane.setViewportView(tabelaFornecedor);
		String colunaTabelaCliente[] = new String[] { "ID", "Nome", "CPF",
				"Banco", "Endereço" };
		defaultTableModel = new DefaultTableModel(new Object[][] {},
				colunaTabelaCliente);
		tabelaFornecedor.setModel(defaultTableModel);

	}

	public void cadastrar() throws CampoNuloException {
		String nome = campoNome.getText();
		String CPF = campoCPF.getText();
		String banco = campoBanco.getText();
		String rua = campoRua.getText();
		String numero = campoNumero.getText();
		String complemento = campoComplemento.getText();
		String bairro = campoBairro.getText();
		String cidade = campoCidade.getText();
		String estado = campoEstado.getText();
		String CEP = campoCEP.getText();
		Fornecedor fornecedor = new Fornecedor(nome, CPF, banco);
		Endereco endereco = new Endereco(rua, numero, complemento, bairro,
				cidade, estado, CEP, fornecedor);
		fornecedor.setEndereco(endereco);
		// JOptionPane.showMessageDialog(this, fornecedor);
		try {
			fachada.cadastrarFornecedor(fornecedor);
			JOptionPane.showMessageDialog(this,
					"Usuário cadastrado com Sucesso!");
			this.limparCampos();
		} catch (EnderecoJaCadastradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (FornecedorJaCadastradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void remover() {
		String CPF = campoCPF.getText();
		try {
			fachada.removerFornecedor(CPF);
			JOptionPane.showMessageDialog(this, "Fornecedor: " + CPF
					+ " removido com sucesso!");
			this.limparCampos();
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (EnderecoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizar() {
		String nome = campoNome.getText();
		String CPF = campoCPF.getText();
		String banco = campoBanco.getText();
		String rua = campoRua.getText();
		String numero = campoNumero.getText();
		String complemento = campoComplemento.getText();
		String bairro = campoBairro.getText();
		String cidade = campoCidade.getText();
		String estado = campoEstado.getText();
		String CEP = campoCEP.getText();
		/*
		 * Fornecedor fornecedor= new Fornecedor(nome, CPF, banco); Endereco
		 * endereco = new Endereco(rua, numero, complemento, bairro, cidade,
		 * estado, CEP, fornecedor); fornecedor.setEndereco(endereco);
		 */

		try {
			Fornecedor fornecedor = fachada.procurarFornecedor(CPF);
			fornecedor.setNome(nome);
			fornecedor.setBanco(banco);
			fornecedor.getEndereco().setRua(rua);
			fornecedor.getEndereco().setNumero(numero);
			fornecedor.getEndereco().setComplemento(complemento);
			fornecedor.getEndereco().setBairro(bairro);
			fornecedor.getEndereco().setCidade(cidade);
			fornecedor.getEndereco().setEstado(estado);
			fornecedor.getEndereco().setCEP(CEP);
			fachada.atualizarFornecedor(fornecedor);
			JOptionPane.showMessageDialog(this, "Fornecedor atualizado!");
			this.limparCampos();
		} catch (EnderecoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void procurar() {
		String CPF = campoCPF.getText();
		try {
			Fornecedor fornecedor = fachada.procurarFornecedor(CPF);
			campoNome.setText(fornecedor.getNome());
			campoBanco.setText(fornecedor.getBanco());
			campoRua.setText(fornecedor.getEndereco().getRua());
			campoNumero.setText(fornecedor.getEndereco().getNumero());
			campoComplemento.setText(fornecedor.getEndereco().getComplemento());
			campoCidade.setText(fornecedor.getEndereco().getCidade());
			campoBairro.setText(fornecedor.getEndereco().getBairro());
			campoEstado.setText(fornecedor.getEndereco().getEstado());
			campoCEP.setText(fornecedor.getEndereco().getCEP());
			// JOptionPane.showMessageDialog(this,
			// fachada.procurarFornecedor(CPF));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (CPFInvalidoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void listar() {
		defaultTableModel.setNumRows(0);
		ArrayList<Fornecedor> lista = fachada.listarFornecedor();
		for (Fornecedor fornecedor : lista) {
			Vector vector = new Vector();
			vector.add(fornecedor.getCodigo());
			vector.add(fornecedor.getNome());
			vector.add(fornecedor.getCpfFormatado());
			vector.add(fornecedor.getBanco());
			vector.add(fornecedor.getEndereco());
			defaultTableModel.addRow(vector);			
		}
	}

	public void limparCampos() {
		campoNome.setText("");
		campoCPF.setText("");
		campoBanco.setText("");
		campoRua.setText("");
		campoNumero.setText("");
		campoComplemento.setText("");
		campoCidade.setText("");
		campoBairro.setText("");
		campoEstado.setText("");
		campoCEP.setText("");
	}
}
