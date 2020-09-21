package ktrout.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ktrout.Main;
import ktrout.controller.SelectHeroController;

public class SelectHeroGuiView extends JPanel implements SelectHeroView {

	private JEditorPane infoPane = new JEditorPane();
	private JButton selectButton = new JButton("Select");
	private JButton createButton = new JButton("Create");
	
	private SelectHeroController controller;
	private int lastSelIndex;
	
	@Override
	public void start() {
		controller = new SelectHeroController(this);
		
		buildUI();
	}
	
	private void buildUI() {
		Main.getFrame().setTitle("Select Hero");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		String[] data = controller.getListData();
		final JList list = new JList(data);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);;
		JScrollPane listScroll = new JScrollPane(list);
		listScroll.setPreferredSize(new Dimension(200, 200));
		listScroll.setMinimumSize(new Dimension(150, 150));
		this.add(listScroll);
		
		infoPane.setEditable(false);
		infoPane.setText("Select a hero to see more info");
		if (data.length == 0) 
			infoPane.setText("No previously saved heroes.");
		JScrollPane infoScroll = new JScrollPane(infoPane);
		infoScroll.setPreferredSize(new Dimension(200, 200));
		infoScroll.setMinimumSize(new Dimension(150, 150));
		this.add(infoScroll, gbc);
		
		this.add(selectButton, gbc);
		this.add(createButton, gbc);
		selectButton.setEnabled(false);
		
		this.setVisible(true);
		
		Main.getFrame().setContentPane(this);
		Main.getFrame().revalidate();
		Main.showFrame();
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (list.getSelectedIndex() != -1) {
						controller.onElementSelected(list.getSelectedIndex());
						selectButton.setEnabled(true);
						lastSelIndex = list.getSelectedIndex()
					} else {
						selectButton.setEnabled(false);
					}
						
				}
			}
		});
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCreate();
			}
		});
	}
	
	@Override
	public void updateInfo(String info) {
		infoPane.setText(info);
	}
	
	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog(Main.getFrame(), msg);
	}
	
	@Override
	public void openGame() {
		this.setVisible(false);
		new GameGuiView().start();
	}
	
	@Override
	public void openCreateHero() {
		this.setVisible(false);
		new CreateHeroGuiView().start();
	}
}
