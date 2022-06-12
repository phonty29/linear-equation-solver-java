import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ensure implements ActionListener {
	JFrame ensureWindow;
	JLabel question;
	JPanel buttonPanel;
	JButton yesButton, noButton;
	Calculator demand;
	int dimension;
	
	Ensure(Calculator isAsking, int n) {
		demand = isAsking;
		dimension = n;
		
		demand.calcFrame.setEnabled(false);
		
		question = new JLabel("Are you sure to clear all the cells?");
		question.setBounds(0, 10, 300, 20);
		question.setHorizontalAlignment(JLabel.CENTER);
		question.setFont(new Font(null, Font.PLAIN, 15));
		
		yesButton = new JButton("YES");
		yesButton.setFocusable(false);
		yesButton.addActionListener(this);
		
		noButton = new JButton("NO");
		noButton.setFocusable(false);
		noButton.addActionListener(this);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBounds(0,40,300,40);
		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);
		
		ensureWindow = new JFrame();
		ensureWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ensureWindow.setLayout(null);
		ensureWindow.setSize(300,130);
		ensureWindow.setLayout(null);
		ensureWindow.setVisible(true);	
		ensureWindow.setLocationRelativeTo(null); 
		ensureWindow.add(question);
		ensureWindow.add(buttonPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == yesButton) {
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					demand.matrixGrid[i][j].setText("");
				}
				demand.constColumn[i].setText("");
			}
			demand.calcFrame.setEnabled(true);
			ensureWindow.dispose();
		}
	   if (e.getSource() == noButton) {
		   demand.calcFrame.setEnabled(true);
		   ensureWindow.dispose();
	   }
	}
}
