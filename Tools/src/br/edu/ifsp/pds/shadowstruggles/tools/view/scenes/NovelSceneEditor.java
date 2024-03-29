package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NovelSceneEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovelSceneEditor frame = new NovelSceneEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NovelSceneEditor() {
		setTitle("Novel Scene Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(24, 11, 46, 14);
		contentPane.add(lblText);
		
		JLabel lblBackground = new JLabel("Background:");
		lblBackground.setBounds(24, 98, 76, 14);
		contentPane.add(lblBackground);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 11, 241, 75);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBounds(93, 95, 241, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddNovelScene = new JButton("Add Novel Scene");
		btnAddNovelScene.setBounds(24, 150, 126, 40);
		contentPane.add(btnAddNovelScene);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(227, 150, 107, 40);
		contentPane.add(btnCancel);
	}
}
