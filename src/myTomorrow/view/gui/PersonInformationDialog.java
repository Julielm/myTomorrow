package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import myTomorrow.model.Person;

public class PersonInformationDialog extends JDialog implements ActionListener
{
	private JTextField nom, prenom;
	private Person personInput;
	private volatile boolean isInput;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonInformationDialog(){
		this.setTitle("Saisie");
		this.setSize(350, 140);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Informations de la personne"));
		pan.setLayout(new GridLayout(2,2));
		this.nom = new JTextField();
		JLabel nomLabel = new JLabel("Saisir un nom :");
		pan.add(nomLabel);
		pan.add(this.nom);
		this.prenom = new JTextField();
		JLabel prenomLabel = new JLabel("Saisir un prenom :");
		pan.add(prenomLabel);
		pan.add(this.prenom);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		okBouton.setPreferredSize(new Dimension(70, 30));
		control.add(okBouton);
		okBouton.addActionListener(this);
		
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(pan);
		split.setBottomComponent(control);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.add(split);
		this.isInput = false;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.personInput = new Person(this.nom.getText(), this.prenom.getText());
		this.isInput = true;
		this.dispose();
	}
	
	public boolean getIsInput(){
		return this.isInput;
	}
	
	public Person getPersonInput(){
		return this.personInput;
	}

}
