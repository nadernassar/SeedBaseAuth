package edu.pace.csis.phd.sbauth.login.gui;
import java.applet.*;
import java.awt.*;

import javax.accessibility.Accessible;
import javax.swing.WindowConstants;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.RootPaneContainer;
import javax.swing.filechooser.FileNameExtensionFilter;



	/**
	 * @param args
	 */


	public class XXXlogin extends Applet implements WindowConstants, Accessible , RootPaneContainer{
		
		public static void main(String args[]){
			
		   
			  
			FrameFactory();
			
		}
		
		
	public static void FrameFactory(){
		
		  
		

		JFrame mainFrame = new JFrame("This is my frame");
		mainFrame.setTitle("Log in uisng SBAuth");
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setSize(300,400);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

		 
		JLabel  headerLabel = new JLabel("Welcome to SBAuth Demo", JLabel.CENTER);        
		JLabel   statusLabel = new JLabel("",JLabel.CENTER);    

	      statusLabel.setSize(350,100);

	      JLabel  msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial."
	         , JLabel.CENTER);
	      
	     JPanel  controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	      
	      FileChooser();
	}

	
		
	   public void paint(Graphics g){
	      g.drawString(" Welcome in Java Applet.",40,20);
	      
	   //   FileChooser();
	     
	   }
	   public static void FileChooser(){
		 
		   JFileChooser chooser = new JFileChooser();
		      FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		      chooser.setFileFilter(filter);
		    //  int returnVal = chooser.showOpenDialog();
		    // if(returnVal == JFileChooser.APPROVE_OPTION) {
		         System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName()+ "Path is "+ chooser.getSelectedFile().getPath());
		     // }
	   }


	@Override
	public Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Component getGlassPane() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JLayeredPane getLayeredPane() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JRootPane getRootPane() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setContentPane(Container arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setGlassPane(Component arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setLayeredPane(JLayeredPane arg0) {
		// TODO Auto-generated method stub
		
	}
	   
	}

	
	