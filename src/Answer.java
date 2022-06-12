import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Answer implements ActionListener {
	JFrame answerFrame;
	JPanel answerPanel;
	JLabel answerLabels[];
	JButton okButton;
	Calculator data;

static double [][] createMinorMatrix(double majorMatrix[][], int n, int col) {
	double minorMatrix[][] = new double[n-1][n-1];
	
	int i_minor=0, j_minor = 0;
	
	for (int i = 1; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (j == col)
				continue;
			minorMatrix[i_minor][j_minor] = majorMatrix[i][j];
			j_minor++;
			if (j_minor == n-1) {i_minor++; j_minor = 0;}
			}
		}
	return minorMatrix;
}

static double calculateDeterminant(double matrix[][], int n) {
		double determinant = 0;
		
		if (n == 1) {
			return matrix[0][0];
		} 
		
		if (n == 2) {
			return matrix[0][0] * matrix[1][1] - matrix[0][1]*matrix[1][0];
		}
		
		if (n == 3) {
			return (matrix[0][0]*matrix[1][1]*matrix[2][2] + matrix[0][1]*matrix[1][2]*matrix[2][0] + matrix[1][0]*matrix[2][1]*matrix[0][2] -
					matrix[2][0]*matrix[1][1]*matrix[0][2]-matrix[1][0]*matrix[0][1]*matrix[2][2]-matrix[2][1]*matrix[1][2]*matrix[0][0]);
		}
		
		double minorMatrix[][] = new double[n-1][n-1];
		int sign = 1;
		
		for (int j = 0; j < n; j++) {
			minorMatrix = createMinorMatrix(matrix, n, j);
			
			determinant += sign * matrix[0][j] * calculateDeterminant(minorMatrix, n-1);
			
			sign = -1 * sign;
		}
		
		return determinant;
	}
     
     Answer(Calculator calc, int n, double matrix[][], double constCol[]) {
    	 data = calc;
    	 double determinant = calculateDeterminant(matrix, n);
    	 
    	 if (determinant == 0) {
    		 new Error(data, "Determinant equals to zero! Can't calculate it");
    		 return;
    	 }
    	 
    	 data.calcFrame.setVisible(false);
    	 double[] extraDeterminants = new double[n];
    	 double[][] extraMatrix;
    	 for (int m = 0; m < n; m++) {
    		 extraMatrix = new double[n][n];
    		 for (int i = 0; i < n; i++) {
    			 for (int j = 0; j < n; j++) {
    				 if (j==m) extraMatrix[i][j] = constCol[i];
    				 else extraMatrix[i][j] = matrix[i][j];
    			 }
    		 }
    		 extraDeterminants[m] = calculateDeterminant(extraMatrix, n);
    	 }
    	 
    	 double getAnswers[] = new double[n];
    	 
    	 for (int i = 0; i < n; i++) {
    		 getAnswers[i] = (extraDeterminants[i]*1.0)/(determinant*1.0);
    	 }
    	 
    	answerFrame = new JFrame();
    	answerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	answerFrame.setLayout(new FlowLayout());
    	answerFrame.setVisible(true);	
    	answerFrame.setLocationRelativeTo(null); 
    	
    	answerPanel = new JPanel();
    	answerPanel.setLayout(new GridLayout(1, n, 20, 0));
    	
    	char subScripts[] = {'\u2080', '\u2081', '\u2082', '\u2083', '\u2084', '\u2085', '\u2086', '\u2087', '\u2088', '\u2088'};
    	
    	answerLabels = new JLabel[n];
    	for (int i = 0; i < n; i++) {
    		String subScript = "";
    		if ((i+1)/10 != 0) {
    			subScript += subScripts[(i+1)/10];
    			subScript += subScripts[(i+1)%10];
    		}
    		else subScript += subScripts[i+1];
    		answerLabels[i] = new JLabel("X" + subScript+" = " + String.format(Locale.US, "%.2f", getAnswers[i]));
    		answerLabels[i].setFont(new Font(null, Font.PLAIN, 25));
    		answerLabels[i].setBorder(BorderFactory.createEtchedBorder());
    		answerPanel.add(answerLabels[i]);
    	}
    	
    	answerPanel.setBounds(0, 0, answerPanel.getPreferredSize().width, answerPanel.getPreferredSize().height);
    	
    	okButton = new JButton("OK");
		okButton.setFocusable(false);
		okButton.addActionListener(this);
    	 
    	answerFrame.add(answerPanel);
    	answerFrame.add(okButton);
    	answerFrame.setSize(answerPanel.getPreferredSize().width, answerPanel.getPreferredSize().height+75);
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			data.calcFrame.setVisible(true);
			answerFrame.dispose();
		}
	}

}

