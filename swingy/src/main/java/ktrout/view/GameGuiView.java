package ktrout.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ktrout.Main;
import ktrout.controller.GameController;

public class GameGuiView extends JPanel implements GameView {
	
	private String[] directions = { "NORTH", "SOUTH", "EAST", "WEST" };
	private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
	private JButton moveButton = new JButton("MOVE");
	private JButton switchButton = new JButton("SWITCH to console");
	
	private JEditorPane infoPane = new JEditorPane();
	private JEditorPane mapPane = new JEditorPane();
	
	private GameController controller;
	
	@Override
	public void start() {
		controller = new GameController(this);
		
		buildUI();
		controller.onStart();
	}
	
	private void buildUI() {
		Main.getFrame().setTitle("SWINGY");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		infoPane.setEditable(false);
		infoPane.setText("Select a hero to see their info");
		infoPane.setPreferredSize(new Dimension(220, 190));
		infoPane.setMinimumSize(new Dimension(200, 200));
		this.add(infoPane, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);
		
		mapPane.setEditable(false);
		mapPane.setText("Map");
		JScrollPane mapScroll = new JScrollPane(mapPane);
		mapScroll.setPreferredSize(new Dimension(300, 300));
		mapScroll.setMinimumSize(new Dimension(200, 200));
		
		directionsComboBox.setSelectedIndex(0);
		this.add(directionsComboBox, gbc);
		this.add(moveButton, gbc);
		this.add(switchButton, gbc);
		
		this.setVisible(true);
		Main.getFrame().setContentPane(this);
		Main.getFrame().revalidate();
		Main.showFrame();
		
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onMove((String) directionsComboBox.getSelectedItem());
			}
		});
		switchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onSwitch();
			}
		});
	}
}
