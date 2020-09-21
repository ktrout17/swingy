import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ktrout.util.Database;

public class Main {
    
	private static Scanner scanner;
	private static JFrame frame;
	
    public static void main(String[] args) {
        
        Database.connect();
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
    	Database.close();
    	if (scanner != null)
    		scanner.close();
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