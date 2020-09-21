package ktrout.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ktrout.Main;
import ktrout.controller.CreateHeroController;

public class CreateHeroGuiView extends JPanel implements CreateHeroView {
	
	private JLabel heroNameLabel = new JLabel("Hero name:");
	private JTextField heroNameField = new JTextField(10);
	private JButton heroCreateButton = new JButton("Create Hero");
	private String[] heroClasses = {"FIGHTER", "NINJA", "WIZARD", "ARCHER", "BESERKER", "SHADOWKNIGHT"};
	private JLabel heroClassLabel = new JLabel("Class:");
	private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
	private JEditorPane infoPane = new JEditorPane();
	
	private CreateHeroController controller;
	
	@Override
	public void start() {
		controller = new CreateHeroController(this);
		
		buildUI();
	}
	
	private void buildUI() {
		Main.getFrame().setTitle("Create Hero");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		JPanel createHeroPanel = new JPanel();
		createHeroPanel.add(heroNameLabel);
		createHeroPanel.add(heroNameField);
		createHeroPanel.setVisible(true);
		this.add(createHeroPanel, gbc);
		
		JPanel classPanel = new JPanel();
		classPanel.add(heroClassLabel);
		classesComboBox.setSelectedIndex(0);
		classPanel.add(classesComboBox);
		classPanel.setVisible(true);
		this.add(classPanel, gbc);
		
		infoPane.setEditable(false);
		infoPane.setFont(new Font("Angelique Rose", Font.PLAIN, 12));
		infoPane.setText(" 				ATTACK	DEFENSE	HP\n" +
				"FIGHTER		50		50		150\n" + 
				"NINJA			40		20		100\n" +
				"WIZARD			35		20		80\n" + 
				"ARCHER			45		40		80\n" +
				"BESERKER		50		30		120\n" +
				"SHADOWKNIGHT	30		20		100\n");
		infoPane.setPreferredSize(new Dimension(200, 120));
		infoPane.setMinimumSize(new Dimension(200, 120));
		this.add(infoPane, gbc);
		
		this.add(heroCreateButton, gbc);
		this.setVisible(true);
		
		Main.getFrame().setContentPane(this);
		Main.getFrame().revalidate();
		Main.showFrame();
		
		heroCreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCreate(heroNameField.getText(),
						(String) classesComboBox.getSelectedItem());
			}
		});
	}
	
	@Override
	public void getUserInput() {}
	
	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog(Main.getFrame(), msg);
	}
	
	@Override
	public void openGame() {
		this.setVisible(false);
		new GameGuiView().start();
	}
}
