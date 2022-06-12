import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Error implements ActionListener {
	JFrame errorFrame ;
	JLabel errorLabel;
	JButton okButton;
	Calculator goBack;
	
	Error(Calculator calc, String str) {
		goBack = calc;
		
		goBack.calcFrame.setEnabled(false);
		
		errorLabel = new JLabel(str);
		errorLabel.setBounds(0, 10, 300, 20);
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setFont(new Font(null, Font.PLAIN, 15));
		
		okButton = new JButton("OK");
		okButton.setFocusable(false);
		okButton.addActionListener(this);
		
		errorFrame = new JFrame();
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		errorFrame.setLayout(new FlowLayout());
		errorFrame.setSize(300,100);
		errorFrame.setVisible(true);	
		errorFrame.setLocationRelativeTo(null); 
		errorFrame.add(errorLabel);
		errorFrame.add(okButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			goBack.calcFrame.setEnabled(true);
			errorFrame.dispose();
		}
		
	}

}
