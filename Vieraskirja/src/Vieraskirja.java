import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Vieraskirja extends JApplet{

	
	private static final long serialVersionUID = 1L;

	//Declare the entries -file.
	
	File entry = new File("http://koti.mbnet.fi/kiiso/Vieraskirja/bin/entry.txt");
	File privateentry = new File("http://koti.mbnet.fi/kiiso/Vieraskirja/bin/privateentry.txt");
	
	//Declare the variables
	public JPanel base, namebase, datebase, messagebase, buttonbase, privatebase, thankbase, choosebase;
	public JTextField name, date, dname, ddate, dmessage, length, info, thank;
	public JTextArea message;
	public JCheckBox visible;
	public JSlider nameslider, messageslider;
	public JButton write, read, send;
	public Color color = new Color(249, 209, 119);
	public int namelength, messagelength;
	private Formatter writer, privatewriter;
	
	
	//The code comes below
	public void init(){
		//Setting layout
		
		
		//Check if entry -file exists.
		
		checkFile();	
		checkprivateFields();
		
		//Define the variables
		
		base = new JPanel();
		base.setBackground(color);
		base.setLayout(null);
		
		
		namebase = new JPanel();
		namebase.setBackground(color);
		namebase.setLayout(new BorderLayout());
		namebase.setBounds(10,30,262,67);		
		
		datebase = new JPanel();
		datebase.setBackground(color);
		datebase.setLayout(new BorderLayout());
		datebase.setBounds(300, 30, 150, 40);
		
		messagebase = new JPanel();
		messagebase.setBackground(color);
		messagebase.setLayout(new BorderLayout());
		messagebase.setBounds(10, 150, 437, 300);
		
		buttonbase = new JPanel();
		buttonbase.setBackground(color);
		buttonbase.setLayout(new BorderLayout());
		buttonbase.setBounds(10, 105, 100, 40);
		
		privatebase = new JPanel();
		privatebase.setBackground(color);
		privatebase.setLayout(new BorderLayout());
		privatebase.setBounds(120, 115, 337, 20);
		
		thankbase = new JPanel();
		thankbase.setBackground(color);
		thankbase.setLayout(new BorderLayout());
		thankbase.setBounds(130, 100, 220, 20);
		
		choosebase = new JPanel();
		choosebase.setBackground(color);
		choosebase.setLayout(new BorderLayout());
		choosebase.setBounds(10,100, 410, 100);
		
		//User chooses wether he wants to write or read entries

		write = new JButton("Kirjoita merkint‰");
		write.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent tap){
						namebase.setVisible(true);
						datebase.setVisible(true);
						messagebase.setVisible(true);
						buttonbase.setVisible(true);
						privatebase.setVisible(true);
						choosebase.setVisible(false);
					}
				}
		);
		
		read = new JButton("Lue merkintˆj‰");
		read.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent tap){
						
					}
				}
		);
		
		
		dname = new JTextField("Nimi: ", 6);
		dname.setEditable(false);
		
		name = new JTextField("", 10);		
		
		//length = new JTextField("", 2);
		
		//Define the nameslider.
		nameslider = new JSlider(SwingConstants.HORIZONTAL, 0, 148, 0);
		nameslider.setMajorTickSpacing(10);
		nameslider.setPaintTicks(true);
		nameslider.addChangeListener(
				new ChangeListener(){
					public void stateChanged(ChangeEvent tap){
						namelength = nameslider.getValue();
						name.setSize(namelength + 114, 20);
						//length.setText(String.format("%s", namelength));
						repaint();
					}

				}
		);

		//More variable definition.
		ddate = new JTextField("P‰iv‰m‰‰r‰: ", 8);
		ddate.setEditable(false);
		
		date = new JTextField("", 8);
		
		dmessage = new JTextField("Viestisi: ", 6);
		dmessage.setEditable(false);
		
		message = new JTextArea(16, 37);
		
		//Define the messageslider.
		messageslider = new JSlider(SwingConstants.VERTICAL, 0, 125, 0);
		messageslider.setMajorTickSpacing(10);
		messageslider.setPaintTicks(true);
		
		messageslider.addChangeListener(
				new ChangeListener(){
					public void stateChanged(ChangeEvent tap){
						messagelength = messageslider.getValue();
						message.setSize(407, messagelength + 300);
						messagebase.setSize(437, 300 + messagelength);
						repaint();
					}
				}
		);
		
		//The checkbox definement.
		visible = new JCheckBox();
		visible.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent tap){
						
					}
				}
		);
		
		//Define the send -button.
		send = new JButton("L‰het‰");
		send.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent tap){
						if(visible.isSelected()){
							try{
								writeprivateFields();
								closeprivateFile();
								JOptionPane.showMessageDialog(null, "Viestin kirjaus onnistui!");
								resetMe();
							}catch(Exception e){
								
							}
						}
						else{
							try{
								writeFields();
								closeFile();
								JOptionPane.showMessageDialog(null, "Viestin kirjaus onnistui!");
								resetMe();
							}
							catch(Exception e){
								JOptionPane.showMessageDialog(null, e);
							}
						}
					}
				}
		);
		
		
		info = new JTextField("Viesti‰ni ei saa julkaista vieraskirjassa.");
		info.setEditable(false);
		
		thank = new JTextField("Kiitos merkinn‰st‰, p‰ivitt‰k‰‰ nyt sivu.");
		thank.setEditable(false);
		
		
		//Add all objects
		namebase.add(dname, BorderLayout.NORTH);
		namebase.add(name, BorderLayout.WEST);
		namebase.add(nameslider, BorderLayout.SOUTH);
		datebase.add(ddate, BorderLayout.NORTH);
		datebase.add(date, BorderLayout.CENTER);
		messagebase.add(dmessage, BorderLayout.NORTH);
		messagebase.add(message, BorderLayout.WEST);
		messagebase.add(messageslider, BorderLayout.EAST);
		base.add(namebase);
		base.add(datebase);
		base.add(messagebase);
		base.add(buttonbase);
		base.add(privatebase);
		base.add(choosebase);
		buttonbase.add(send, BorderLayout.CENTER);
		privatebase.add(visible, BorderLayout.WEST);
		privatebase.add(info, BorderLayout.CENTER);
		thankbase.add(thank, BorderLayout.CENTER);
		choosebase.add(write, BorderLayout.NORTH);
		choosebase.add(read, BorderLayout.SOUTH);
		//base.add(length);
		setContentPane(base);
		namebase.setVisible(false);
		datebase.setVisible(false);
		messagebase.setVisible(false);
		buttonbase.setVisible(false);
		privatebase.setVisible(false);
		thankbase.setVisible(false);
		choosebase.setVisible(true);
	}	
	//End of init().
	
	//The method that checks the files existance.
	public void checkFile() {
		if(entry.exists()){

		}


		try{
			writer = new Formatter(new FileWriter(entry, true));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	//The file writes.
	public void writeFields(){
		if(name.getText().isEmpty()){
			name.setText("Anonyymi");
		}
		if(date.getText().isEmpty()){
			date.setText("Ei tiedossa");
		}
		if(message.getText().isEmpty()){
			message.setText("Ei viesti‰.");
		}

		writer.format("%s\t%s\r\n%s%s\r\n%s\t%s\r\n\r\n", "Nimi:", name.getText(), "P‰iv‰m‰‰r‰:      ", date.getText(), "Viesti:", message.getText());
	}

	//If the user wants to hide his/her entry.
	public void checkprivateFields(){
		if(privateentry.exists()){
			
		}

		try{
			privatewriter = new Formatter(new FileWriter(privateentry, true));
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Same as before.
	public void writeprivateFields(){
		if(name.getText().isEmpty()){
			name.setText("Anonyymi");
		}
		if(date.getText().isEmpty()){
			date.setText("Ei tiedossa");
		}
		if(message.getText().isEmpty()){
			message.setText("Ei viesti‰.");
		}

		privatewriter.format("%s\t%s\r\n%s%s\r\n%s\t%s\r\n\r\n", "Nimi:", name.getText(), "P‰iv‰m‰‰r‰:      ", date.getText(), "Viesti:", message.getText());

	}

	//And the very important close method.
	public void closeFile(){
		writer.close();
	}

	public void closeprivateFile(){
		privatewriter.close();
	}
	
	//And the reset
	public void resetMe(){
		name.setText("");
		date.setText("");
		message.setText("");
		visible.setSelected(false);
		nameslider.setValue(0);
		messageslider.setValue(0);
		namebase.setVisible(false);
		datebase.setVisible(false);
		messagebase.setVisible(false);
		buttonbase.setVisible(false);
		privatebase.setVisible(false);
		base.add(thankbase);
		thankbase.setVisible(true);
		choosebase.setVisible(true);
		choosebase.remove(write);
		
	}
}
