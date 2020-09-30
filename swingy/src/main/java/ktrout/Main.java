package ktrout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ktrout.util.Database;
import ktrout.view.StartConsoleView;
import ktrout.view.StartGuiView;

public class Main {
    
	private static Scanner scanner;
	private static JFrame frame;
	
    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))) {
        	System.out.println("Please enter console or gui view");
        	System.out.println("java -jar programName console");
        	System.out.println("OR");
        	System.out.println("java -jar programName gui");
        }
        
        Database.connect();
        
        if (args[0].equals("console"))
        	new StartConsoleView().start();
        else if (args[0].equals("gui"))
        	new StartGuiView().start();
    }
    
    public static Scanner getScanner() {
    	if (scanner == null)
    		scanner = new Scanner(System.in);
    	return scanner;
    }
    
    public static JFrame getFrame() {
    	if (frame == null) {
    		frame = new JFrame();
    		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		frame.setSize(500, 400);
    		frameListener();
    	}
    	return frame;
    }
    
    public static void showFrame() {
    	if (frame != null)
    		frame.setVisible(true);
    }
    
    public static void hideFrame() {
    	if (frame != null)
    		frame.setVisible(false);
    }
    
    public static void closeConnections() {
		// if (scanner != null)
		// 	scanner.close();
    	Database.close();
    }
    
    private static void frameListener() {
    	getFrame().addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			closeConnections();
    			super.windowClosing(e);
    		}
    	});
    }
}