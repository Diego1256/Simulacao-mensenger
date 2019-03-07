package br.java.aplicacao.menseger_teste;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLogin {

	private JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin window = new ViewLogin();
					window.frame.setVisible(true);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 455, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(74, 40, 46, 14);
		frame.getContentPane().add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(74, 65, 248, 28);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(74, 104, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(74, 130, 248, 28);
		frame.getContentPane().add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckLogin(txtLogin.getText(), new String(txtSenha.getPassword()))) {
					
					JOptionPane.showMessageDialog(null, "Seja bem vindo ao virtual red machine.");
					Cliente cl = new Cliente();
					cl.main(null);
					txtLogin.setText("");
					txtSenha.setText("");
				}else {
				
					JOptionPane.showMessageDialog(null, "Deu ruim, alguma coisa está errado, verifique e tente novamente!","ERRO",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		btnEntrar.setBounds(74, 190, 89, 28);
		frame.getContentPane().add(btnEntrar);
	}
	
	public boolean CheckLogin(String Login, String Senha) {
		
		return Login.equals("olga") || Login.equals("felipe") && Senha.equals("123") || Senha.equals("222");
		
	}
}
