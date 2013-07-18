package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JLabel;

public class ResourceEditor extends JFrame {

	private JPanel contentPane;
	private ResourceEditor frame;
	private Controller controller;
	private JList list;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResourceEditor frame = new ResourceEditor(null);
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
	public ResourceEditor(Controller controller) {
		this.frame=this;
		this.controller=controller;
		setTitle("Resource Edition");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 34, 437, 23);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Images", null, panel, null);
		
		JTree tree = new JTree();
		tree.setBounds(31, 80, 187, 100);
		tree.setVisible(false);
		contentPane.add(tree);
		
		SimpleTree folderTree = new SimpleTree();
		folderTree.setBounds(31, 80, 220, 200);
		contentPane.add(folderTree);
		
		list = new JList();
		list.setModel(new DefaultListModel<>());
		list.setBounds(264, 80, 182, 200);
		contentPane.add(list);
		
		JButton btnAddFile = new JButton("Add File");
		btnAddFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");  		
				chooser.addChoosableFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(Paths.get("").toFile());		
				int returnValue = chooser.showOpenDialog(frame);
				if(returnValue==JFileChooser.APPROVE_OPTION){
					File file = chooser.getSelectedFile();
					String log = "Opening "+file.getName()+"\n";		
					((DefaultListModel<String>)list.getModel()).addElement(file.getName());
					list.setSelectedIndex(list.getModel().getSize()-1);
					updateImage(file);
				}
					
				
			}
		});
		btnAddFile.setBounds(261, 291, 89, 23);
		contentPane.add(btnAddFile);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(31, 321, 17, 14);
		contentPane.add(lblNewLabel);
		
		
	}
	
	private void updateImage(File file){
		BufferedImage myPicture;
		try {
			myPicture =(BufferedImage) ImageIO.read(file).getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			
			Icon icon = new ImageIcon(myPicture);
			
			lblNewLabel.setIcon(icon);	
			lblNewLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}