package ui.SearchDealer;

import dto.Dealer;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ListOfDealersUI extends JFrame{
	
	public JFrame frame;
	private Container c;
	private JScrollPane jsp;
	private ArrayList<Dealer> ad = new ArrayList<>();

	public ListOfDealersUI(ArrayList<Dealer> dealers) {
		
	  	ad = dealers;

			
		createComponents();
		setLayout();
		addComponents();
		display();
	}
	
	private void display() {
		
		frame.setSize(800, 700);
		frame.setVisible(true);

	}

	private void addComponents() {
		frame.setTitle("List Of Dealers");
		for (int i = 0; i < ad.size(); i++) {
	         c.add(new ui.SearchDealer.SingleDealerPanelUI(ad.get(i)));
	    }
		
	}

	private void setLayout() {
		
		GridLayout gl = new GridLayout(4,1);
		c = frame.getContentPane();
		c.setLayout(gl);
		
	}

	private void createComponents() {
		
		frame = new JFrame();
		c = new Container();
		
		jsp = new JScrollPane(c);
		
		
	}
}
