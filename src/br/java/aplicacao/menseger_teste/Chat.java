package br.java.aplicacao.menseger_teste;

import java.net.*;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Chat extends JFrame {

	private JPanel contentPane;
	private Socket s;
	private String nome;

	private BufferedReader br;
	private InputStreamReader isr;
	private boolean rodar;

	public Chat(String nome) {
		setBackground(new Color(0, 153, 204));
		rodar = true;
		this.nome = nome;
		try {
			s = new Socket("127.0.0.1", 5000);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "não se conectou ao servidor");
			System.exit(0);
		}
		Thread();
	}

	private void Thread() {
		JTextArea mensagemRecebida = new JTextArea();
		mensagemRecebida.setBounds(10, 44, 414, 190);
		contentPane.add(mensagemRecebida);
		
		JLabel usuarioNome = new JLabel();
		usuarioNome.setHorizontalAlignment(SwingConstants.CENTER);
		usuarioNome.setBounds(182, 0, 70, 21);
		contentPane.add(usuarioNome);
		usuarioNome.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					isr = new InputStreamReader(s.getInputStream());
					br = new BufferedReader(isr);
					String msg;
					usuarioNome.setText(nome);
					usuarioNome.setForeground(Color.white);
					

					while ((msg = br.readLine()) != null) {
						mensagemRecebida.setText(mensagemRecebida.getText() + msg + "\n");
						if(!rodar) {
							break;
						}
					}
				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, "Erro na conexão com o servidor");

				}
			}
		});
		t.start();

	}

	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLabel usuarioNom = new JLabel();
		//usuarioNom.setBounds(184, 0, 72, 21);
		//contentPane.add(usuarioNom);
		//usuarioNom.setText("nome");
		
		JTextArea mensagemRecebida = new JTextArea();
		mensagemRecebida.setBounds(10, 23, 414, 224);
		contentPane.add(mensagemRecebida);

		JTextArea mensagemEnviada = new JTextArea();
		mensagemEnviada.setBounds(10, 258, 316, 105);

		mensagemEnviada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					String mensagem = nome + " disse: ";

					try {
					
						PrintStream ps = new PrintStream(s.getOutputStream());
						mensagem += mensagemEnviada.getText();
						ps.println(mensagem);
						ps.flush();
						mensagemEnviada.setText("");
						

					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "não se conectou ao servidor", "", ERROR);
					}

				}

			}

		});
		contentPane.add(mensagemEnviada);
		//@SuppressWarnings("unchecked")
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Open Sans SemiBold", Font.PLAIN, 12));
		btnEnviar.setBackground(new Color(240, 255, 240));
		btnEnviar.setBounds(336, 259, 89, 52);
	

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String mensagem = nome + " disse: ";

				try {

					PrintStream ps = new PrintStream(s.getOutputStream());
					mensagem += mensagemEnviada.getText();
					ps.println(mensagem);
					ps.flush();
					mensagemEnviada.setText("");

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "não se conectou ao servidor", "", ERROR);
				}
			}
		});
		contentPane.add(btnEnviar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Open Sans SemiBold", Font.PLAIN, 12));
		btnSair.setBackground(new Color(240, 255, 240));
		btnSair.setBounds(336, 322, 88, 41);
		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					s.close();
					System.exit(0);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			

		});
		contentPane.add(btnSair);
	}
}
