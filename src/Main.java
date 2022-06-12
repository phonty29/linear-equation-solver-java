import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener {
	JFrame frame;
	JPanel panel;
	JComboBox comboBox;
	JButton button;
	JLabel label;
	
	JPanel openingPanel;
	JLabel courseWork, topic, performedBy, group, checkedBy;

	public static void main(String[] args) {
		Main main = new Main();
	}
	
	Main() {
		label = new JLabel("Solve the SOLE using Cramer's rule");
		label.setBounds(0, 10, 420, 25);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font(null, Font.PLAIN, 20));
		
		Integer[] dimensions = new Integer[20]; 
		for (int d = 0; d < 20; d++) {
			dimensions[d] = d+1;
		}
		comboBox = new JComboBox(dimensions);
		
		button = new JButton("Set Dimension");
		button.setFocusable(false);
		button.addActionListener(this);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBounds(0,50,420,35);
		panel.add(comboBox);
		panel.add(button);
		
		courseWork = new JLabel("Курсовая работа по Технологии программирования");
		topic = new JLabel("По теме: Решение СЛАУ методом Крамера");
		performedBy = new JLabel("Выполнил: Амедов Бекмухамет");
		group = new JLabel("Группа: ИП-20-6Р");
		checkedBy = new JLabel("Проверила: Тарасова Р.Н.");
		courseWork.setFont(new Font(null, Font.PLAIN, 14));
		topic.setFont(new Font(null, Font.PLAIN, 14));
		performedBy.setFont(new Font(null, Font.PLAIN, 14));
		group.setFont(new Font(null, Font.PLAIN, 14));
		checkedBy.setFont(new Font(null, Font.PLAIN, 14));
		
		openingPanel = new JPanel(new GridLayout(5,1));
		openingPanel.setBounds(50,150,420,100);
		openingPanel.add(courseWork);
		openingPanel.add(topic);
		openingPanel.add(performedBy);
		openingPanel.add(group);
		openingPanel.add(checkedBy);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,300);
		frame.setLayout(null);
		frame.setVisible(true);	
		frame.setLocationRelativeTo(null); 
		frame.add(label);
		frame.add(panel);
		frame.add(openingPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			frame.dispose();
			Calculator calc = new Calculator((Integer)(comboBox.getSelectedItem()));
		}
	}

}
