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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ktrout.Main;
import ktrout.controller.GameController;
import ktrout.model.Game;
import ktrout.util.MapPoints;

public class GameGuiView extends JPanel implements GameView {

	private String[] directions = { "NORTH", "SOUTH", "EAST", "WEST" };
	private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
	private JButton moveButton = new JButton("MOVE");
	private JButton quitButton = new JButton("Quit Game");
	// private JButton switchButton = new JButton("SWITCH to console");

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
		infoPane.setPreferredSize(new Dimension(220, 250));
		infoPane.setMinimumSize(new Dimension(200, 250));
		this.add(infoPane, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);
		
		mapPane.setEditable(false);
		mapPane.setText("Map");
		JScrollPane mapScroll = new JScrollPane(mapPane);
		mapScroll.setPreferredSize(new Dimension(300, 300));
		mapScroll.setMinimumSize(new Dimension(200, 200));
		
		directionsComboBox.setSelectedIndex(0);
		// this.add(mapPane, gbc);
		this.add(directionsComboBox, gbc);
		this.add(moveButton, gbc);
		this.add(quitButton, gbc);

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
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onQuit();
			}
		});
		// switchButton.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		controller.onSwitch();
		// 	}
		// });
	}

	@Override
	public void printMap(boolean[][] map, MapPoints heroCoords) {
	    StringBuilder stringBuilder = new StringBuilder();
	    stringBuilder.append(String.format("MAP %dx%d\n", map.length, map.length));
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[i].length; j++) {
	            if (heroCoords.getX() == j && heroCoords.getY() == i)
	                stringBuilder.append("H ");
	            else if (map[i][j])
	                stringBuilder.append("* ");
	            else
	                stringBuilder.append(". ");
            }
	        stringBuilder.append("\n");
        }
	    mapPane.setText(stringBuilder.toString());
    }

    @Override
    public void update(Game game) {
	    infoPane.setText(game.getHero().toString() +
            "Position: (" + game.getHeroCoords().getX() +
            "," + game.getHeroCoords().getY() + ")");
	    printMap(game.getMap(), game.getHeroCoords());
    }
    
    @Override
    public void gameDone() {
    	Main.hideFrame();
    	Main.getFrame().dispose();
    	Main.closeConnections();
    }
    
    @Override
    public void showMsg(String msg) {
    	JOptionPane.showMessageDialog(Main.getFrame(), msg);
    }
    
    @Override
    public void getCombatInput() {
    	Object options[] = {"FIGHT", "RUN"};
    	
    	int res = JOptionPane.showOptionDialog(Main.getFrame(), 
    			"You ran into an enemy.", 
				"Fight or run?", 
    			JOptionPane.YES_NO_OPTION, 
    			JOptionPane.QUESTION_MESSAGE, null, 
    			options, options[0]);
    	if (res == JOptionPane.YES_OPTION)
    		controller.onFight();
    	else
    		controller.onRun();
    }
    
    @Override
    public boolean replaceArtifact(String replaceMsg) {
    	Object options[] = {"Replace", "Drop"};
    	
    	int res = JOptionPane.showOptionDialog(Main.getFrame(), 
    			"Would you like to replace " + replaceMsg + "?", 
    			"Replace or drop?", 
    			JOptionPane.YES_NO_OPTION, 
    			JOptionPane.QUESTION_MESSAGE, null, 
    			options, options[0]);
    	return res == JOptionPane.YES_OPTION;
    }
    
    @Override
    public void switchView() {
    	Main.hideFrame();
    	new GameConsoleView().start();
	}
	
	@Override
    public void quitGame() {
    	Main.hideFrame();
    	Main.getFrame().dispose();
    	Main.closeConnections();
    }
}
