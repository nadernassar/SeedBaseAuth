package edu.pace.csis.phd.sbauth.login.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.pace.csis.phd.sbauth.generator.SeedGenerator;
import edu.pace.csis.phd.sbauth.generator.TokenGenerator;
import edu.pace.csis.phd.sbauth.login.*;

public class LoginGui extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8843546537957508843L;
	File selectedFile = null;
	 public static Long getUrlSeed() {
		return urlSeed;
	}

	public static void setUrlSeed(Long urlSeed) {
		LoginGui.urlSeed = urlSeed;
	}

	public static Long getUnmSeed() {
		return unmSeed;
	}

	public static void setUnmSeed(Long unmSeed) {
		LoginGui.unmSeed = unmSeed;
	}

	public static Long getUrlToken() {
		return urlToken;
	}

	public static void setUrlToken(Long urlToken) {
		LoginGui.urlToken = urlToken;
	}

	public static Long getUnmToken() {
		return unmToken;
	}

	public static void setUnmToken(Long unmToken) {
		LoginGui.unmToken = unmToken;
	}

	public static int getSeqNum() {
		return seqNum;
	}

	public static void setSeqNum(int seqNum) {
		LoginGui.seqNum = seqNum;
	}

	public static   Long urlSeed = null;
	 public static   Long unmSeed= null;
	 public static   Long urlToken= null;
	 public static   Long unmToken= null ;
	 public static   int seqNum = 0;
	
	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public LoginGui() {
		    super("Welcome to SBAuth Demo");
		    setSize(350, 300);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		    
		    Container c = getContentPane();
		    c.setLayout(new FlowLayout());
		
		    final JLabel  headerLabel = new JLabel("Welcome to SBAuth Demo, please select your root file ", JLabel.CENTER);
			final JLabel  SPACE = new JLabel(" \n ", JLabel.CENTER);  
		    final JLabel statusbar =   new JLabel("Output of your selection will go here", JLabel.CENTER);
		    final JLabel urlSeedStatusbar =   new JLabel("", JLabel.CENTER);
		    final JLabel unmSeedStatusbar =   new JLabel("", JLabel.CENTER);
		    final JLabel urlTokenStatusbar =   new JLabel("", JLabel.CENTER);
		    final JLabel unmTokenStatusbar =   new JLabel("", JLabel.CENTER);
		    final JLabel seqNumStatusbar  =   new JLabel("", JLabel.CENTER);
		    final JLabel icon  =   new JLabel();
		    final  JButton openButton = new JButton("Open");
		   
		    
		    // Create a file chooser that opens up as an Open dialog
		    openButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
			     chooser.setFileFilter(filter);
		        chooser.setMultiSelectionEnabled(false);
		        int option = chooser.showOpenDialog(LoginGui.this);
		        if (option == JFileChooser.APPROVE_OPTION) {
		        	setSelectedFile(chooser.getSelectedFile());
		        	 statusbar.setText("You chose file " + getSelectedFile().getName());
			         
		        	 	SeedGenerator.readPassFile(getSelectedFile());
		        	 
		        	  	 setSeqNum( TokenGenerator.getSquenceNum());
		        	 	 setUnmSeed(SeedGenerator.generateUNSeed());
						 setUrlSeed(SeedGenerator.generateURLSeed());						
						 setUrlToken(TokenGenerator.generateToken(urlSeed)); 
						 setUnmToken(TokenGenerator.generateToken(unmSeed)); 
						 
						//icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(getSelectedFile().getPath()))); // NOI18N
						 
						// DisplayImage(getSelectedFile());
						 urlSeedStatusbar.setText("urlSeed: "+urlSeed +" ");
						 unmSeedStatusbar.setText("unmSeed: "+unmSeed+"  ");
						 urlTokenStatusbar.setText("urlToken: "+urlToken+"  ");
						 unmTokenStatusbar.setText("unmToken: "+unmToken+"  ");												 
						 seqNumStatusbar.setText("Seq Num: " +seqNum);
						 
						// Image img = new ImageIcon(getSelectedFile().getPath()).getImage();
						 Image img = new ImageIcon(getSelectedFile().getPath()).getImage().getScaledInstance(20, 20,0);
						icon.setIcon(new ImageIcon(getSelectedFile().getPath()));
						
						 icon.setBounds(0, 0, 20, 20);
						 icon.setSize(20, 20);
						 icon.setLocation(0, 0);
						 icon.updateUI();
						 Authenticate.setUrlSeed(urlSeed);
						 Authenticate.setUnmSeed(unmSeed);
												
						 try {
							Login.Identify(getUrlToken(), getUnmToken(), getSeqNum());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						 TokenGenerator.updateSequenceNumber();
		        }else{
		        	statusbar.setText("Operatoin Cancelled ");
		        }
		        }
		    });
		    			 
		    c.add(headerLabel);
		    c.add(openButton);			    
		    c.add(statusbar);
		    c.add(urlSeedStatusbar);
		    c.add(unmSeedStatusbar);
		    c.add(urlTokenStatusbar);
		    c.add(unmTokenStatusbar);
		    c.add(seqNumStatusbar);
		    c.add(icon);

		    
		   
		  }
	 public void DisplayImage(File f) {    
		            JFrame frame = new JFrame("Display Image");    
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		        
		            JPanel panel = (JPanel)frame.getContentPane();    
		        
		           JLabel label = new JLabel();    
		           label.setIcon(new ImageIcon(f.getPath()));//  image here    
		           panel.add(label);    
		       
		           frame.setLocationRelativeTo(null);    
		           frame.pack();    
		           frame.setVisible(true);    
		       }    

		  public static void main(String args[]) {
		    LoginGui sfc = new LoginGui();
		    sfc.setVisible(true);
		    
		    
		    
		  }

}
