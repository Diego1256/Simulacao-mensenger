package br.java.aplicacao.menseger_teste;

import static javax.swing.JOptionPane.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cliente {

	public static void main(String[] args) {
		String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "", PLAIN_MESSAGE);
		Chat chat = new Chat(nome);
		chat.setVisible(true);
		
		JLabel jl = new JLabel();
		jl.setText(nome);
	}

}
