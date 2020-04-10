package ui.SearchDealer;

import dto.Dealer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SingleDealerPanelUI extends JPanel{
	
	private JButton clickForDetails;
	private JLabel name, url, location, zipcode, address;

	Dealer d = new Dealer();
	
	public SingleDealerPanelUI(Dealer dealer) {
		
		d = dealer;
		createComponents();
 		addComponents();
 		addListener();
	}
		
		
	private void addListener() {
		
			ButtonClickedListener bcl = new ButtonClickedListener();
			clickForDetails.addActionListener(bcl);
		
	}

	private void addComponents() {
			
			JPanel p = new JPanel();
			p.add(clickForDetails);
			
			this.setLayout(new GridLayout(6, 0));
			this.add(name);
			this.add(url);
			this.add(location);
			this.add(zipcode);
			this.add(address);
			this.add(p);
			this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.RED));
		
		
	}

	private void createComponents() {
		
			name = new JLabel(d.getName());
			url = new JLabel(d.getUrl());
			location = new JLabel(d.getLocation());
			zipcode = new JLabel(d.getZipcode());
			address = new JLabel(d.getAddress());
			clickForDetails = new JButton("Click For Details");
		
	}
		
class ButtonClickedListener implements ActionListener {

			@Override
	public void actionPerformed(ActionEvent e) {
				
			
	}

}
}
