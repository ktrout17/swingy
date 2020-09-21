package ktrout.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ktrout.Main;
import ktrout.controller.StartController;

public class StartGuiView extends JPanel implements StartView {
		
	private JButton createHeroButton = new JButton("Create Hero");
	private JButton selectHeroButton = new JButton("Select Hero");
	private JButton switchViewButton = new JButton("Switch to console view");
	
	private StartController controller;
	
	@Override 
	public void start() {
		controller = new StartController(this);
		
		buildUI();
	}
	
	private void buildUI() {
		Main.getFrame().setTitle("Start");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		this.add(createHeroButton, gbc);
		this.add(selectHeroButton, gbc);
		this.add(switchViewButton, gbc);
		
		this.setVisible(true);
		Main.getFrame().setContentPane(this);
		Main.getFrame().revalidate();
		Main.showFrame();
		
		createHeroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCreate();
			}
		});
		selectHeroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onSelect();
			}
		});
		switchViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onSwitch();
			}
		});
	}
	
	@Override
	public void openCreateHero() {
		this.setVisible(false);
		new CreateHeroGuiView().start();
	}
	
	@Override
	public void switchView() {
		Main.hideFrame();
		new StartConsoleView().start();
	}
	
	@Override
	public void openSelectHero() {
		this.setVisible(false);
		new SelectHeroGuiView().start();
	}
}
