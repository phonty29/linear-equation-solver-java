import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {
	int dimension;
	JFrame calcFrame;
	JPanel matrixPanel, constPanel, labelPanel;
	JScrollPane matrixPane;
	JScrollPane constPane;
	JTextField matrixCell;
	JLabel equalSign;
	JTextField[][] matrixGrid;
	JTextField[] constColumn;
	JButton clearButton,fillButton,calculateButton;
	JLabel[] vars;
	double matrix[][], constVal[];
	
	
	Calculator(int n) {
		dimension = n;
		int cellSize = (n>10) ? (30) : (40);
		int fontSize = 15;
		calcFrame = new JFrame();
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setLayout(null);
		
		matrixPanel = new JPanel();
		matrixPanel.setLayout(new GridLayout(n,n,10,10));

		constPanel = new JPanel();
		constPanel.setLayout(new GridLayout(n,1,10,10));
		
		matrixGrid = new JTextField[n][n];
		matrix = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrixGrid[i][j] = new JTextField();
				matrixGrid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				matrixGrid[i][j].setPreferredSize(new Dimension(cellSize,cellSize));
				matrixGrid[i][j].setFont(new Font("Calibri", Font.PLAIN,fontSize));
				matrixPanel.add(matrixGrid[i][j]);
			}
		}
		
		constColumn = new JTextField[n];
		constVal = new double[n];
		for (int i = 0; i < n; i++) {
			constColumn[i] = new JTextField();
			constColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
			constColumn[i].setPreferredSize(new Dimension(cellSize,cellSize));
			constColumn[i].setFont(new Font("Calibri", Font.PLAIN,fontSize));
			constPanel.add(constColumn[i]);
		}
		
		int mpWidth = matrixPanel.getPreferredSize().width;
		int extraWidth = n<5 ? 80 : 0;
		int mHeight = matrixPanel.getPreferredSize().height;
		
		int cpWidth =  constPanel.getPreferredSize().width;		
		
		mHeight = mHeight > 557 ? 557 : mHeight;
		
		matrixPane = new JScrollPane(matrixPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		constPane = new JScrollPane(constPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		matrixPane.setBounds(cellSize+extraWidth/2, 75, mpWidth+18, mHeight+18);
		constPane.setBounds(cellSize+mpWidth+78+extraWidth/2, 75, cpWidth+18, mHeight+18);
		
		equalSign = new JLabel("=");
		equalSign.setHorizontalTextPosition(JLabel.CENTER); 
		equalSign.setHorizontalAlignment(JLabel.CENTER);
		equalSign.setFont(new Font(null, Font.PLAIN, 30));
		equalSign.setBounds(cellSize+mpWidth+18+extraWidth/2, 50, 60, mHeight+18);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		clearButton.setFocusable(false);
		fillButton = new JButton("Fill empty cells with 0");
		fillButton.addActionListener(this);
		fillButton.setFocusable(false);
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(this);
		calculateButton.setFocusable(false);
		
		calcFrame.setSize(2*cellSize+mpWidth+cpWidth+extraWidth+96, mHeight+200);
		int mid = calcFrame.getWidth()/2;
		
		clearButton.setBounds(mid-clearButton.getPreferredSize().width-40, 10, clearButton.getPreferredSize().width, clearButton.getPreferredSize().height);
		fillButton.setBounds(mid-10, 10, fillButton.getPreferredSize().width, fillButton.getPreferredSize().height);
		calculateButton.setBounds(mid-calculateButton.getPreferredSize().width/2, mHeight+18+100, calculateButton.getPreferredSize().width, clearButton.getPreferredSize().height);
		
		vars = new JLabel[n];
		labelPanel = new JPanel(new GridLayout(1,n,10,10));
		char subScripts[] = {'\u2080', '\u2081', '\u2082', '\u2083', '\u2084', '\u2085', '\u2086', '\u2087', '\u2088', '\u2088'};
		
		for (int i = 0; i < n; i++) {
    		String subScript = "";
    		if ((i+1)/10 != 0) {
    			subScript += subScripts[(i+1)/10];
    			subScript += subScripts[(i+1)%10];
    		}
    		else subScript += subScripts[i+1];
    		vars[i] = new JLabel("X" + subScript);
    		vars[i].setFont(new Font(null, Font.PLAIN, fontSize));
    		vars[i].setPreferredSize(new Dimension(cellSize, cellSize));
    		vars[i].setHorizontalTextPosition(JLabel.CENTER); 
    		vars[i].setHorizontalAlignment(JLabel.CENTER);
    		labelPanel.add(vars[i]);
    	}
		labelPanel.setBounds(cellSize+extraWidth/2, 40, mpWidth, labelPanel.getPreferredSize().height);
		
		calcFrame.add(matrixPane);
		calcFrame.add(equalSign);
		calcFrame.add(constPane);
		calcFrame.add(clearButton);
		calcFrame.add(fillButton);
		calcFrame.add(labelPanel);
		calcFrame.add(calculateButton);
		
		calcFrame.setVisible(true);	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fillButton) {
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					if (matrixGrid[i][j].getText().trim().contentEquals("")) {
						matrixGrid[i][j].setText("0");
					}
				}
				if (constColumn[i].getText().trim().contentEquals("")) {
					constColumn[i].setText("0");
				}
			}
		}
		
		if(e.getSource() == clearButton) {
			new Ensure(this, dimension);
		}
		if(e.getSource() == calculateButton) {
			boolean itHasEmptyCells = false;
			
			first : for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					if (matrixGrid[i][j].getText().trim().contentEquals("")) {
						itHasEmptyCells = true;
						new Error(this, "There are empty cells! Please, fill all of them");
						break first;
					}
					matrix[i][j] = Double.valueOf(matrixGrid[i][j].getText().trim());
				}
				if (constColumn[i].getText().trim().contentEquals("")) {
					itHasEmptyCells = true;
					new Error(this, "There are empty cells! Please, fill all of them");
					break;
				}
				constVal[i] = Double.valueOf(constColumn[i].getText().trim());
			}
			
			if (itHasEmptyCells == false) 
				new Answer(this, dimension, matrix, constVal);
		}	
	}
}
