package Window_Caculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window_Calculator extends JFrame implements ActionListener,TextListener{
	
	private JPanel panel1, panel2, panel3;
	JButton array[]=new JButton[12];
	JButton plus;
	JButton minus;
	JButton multiply;
	JButton division;
	
	private JTextField tf;
	private JTextArea ta;
	private JTextArea taresult;
	private JScrollPane sp;
	


	public Window_Calculator() {
		
		panel1=new JPanel();
		for (int i = 0; i < array.length; i++) {
			if(i<9)
				panel1.add(array[i]=new JButton(Integer.toString(i+1)));
			else if(i==9)
				panel1.add(array[i]=new JButton("."));
			else if(i==10)
				panel1.add(array[i]=new JButton("0"));
			else
				panel1.add(array[i]=new JButton("="));
			array[i].setPreferredSize(new Dimension(80,80)); //��ư ũ�� ����
			array[i].addActionListener(this);
		}
		
		panel2=new JPanel();
		panel2.add("North",ta=new JTextArea(0,30));
//		ta.addKeyListener(new KeyListener() { //Ű���带 ������ �� ȣ��Ǵ� �޼��带 ������ �ִ� �������̽�
//			
//			@Override
//			public void keyTyped(KeyEvent e) { //���ڸ� ������ �� ȣ��, ����Ű���� ����
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) { //Ű���带 ���� �� ȣ��, ��� Ű���忡 ����
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) { //Ű���带 ������ �� ȣ��, ��� Ű���忡 ����
//				if(e.getKeyCode()==10||e.getKeyCode()==13) {
//					result();
//					ta.setText("");
//				}
//			}
//		});
		panel2.add("South",taresult=new JTextArea(6, 30));
		sp=new JScrollPane(taresult,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel2.add(sp);

		
		panel3=new JPanel();
		panel3.add(multiply=new JButton("x"));
		multiply.setPreferredSize(new Dimension(80,80));
		panel3.add(division=new JButton("%"));
		division.setPreferredSize(new Dimension(80,80));
		panel3.add(plus=new JButton("+"));
		plus.setPreferredSize(new Dimension(80,80));
		panel3.add(minus=new JButton("-"));
		minus.setPreferredSize(new Dimension(80,80));
		//JTextArea.setPreferredSize�� �����ϸ� ��ũ�ѹٰ� ������ ����

		
		multiply.addActionListener(this);
		division.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		
		this.setLayout(null); //�г��� ��ġ ���밪����
		panel1.setBounds(0,0,300,350);
		panel2.setBounds(0,350,400,150);
		panel3.setBounds(280,0,80,350);
		this.add(panel1); //1~0���� ����
		this.add(panel2); //ta
		this.add(panel3); //��Ģ����
		this.setBounds(100, 100, 400, 550);
		this.setResizable(false); //â ũ�� ����
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				 System.exit(0);
			}
			
			
		});
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < array.length; i++) {
			if(e.getSource()==array[i]) {
				if(i==9)
					ta.append(".");
				else if(i==10)
					ta.append("0");
				else if(i<9)
				ta.append(Integer.toString(i+1));
			}
		}
		if(e.getSource()==multiply) ta.append("x");
		else if(e.getSource()==division) ta.append("%");
		else if(e.getSource()==plus) ta.append("+");
		else if(e.getSource()==minus) ta.append("-");
		if(e.getSource()==array[11]) {
			result();
			ta.setText(""); //���ڿ� �ʱ�ȭ
			//=������ ���� ��� ���
		}
			
	}
	

	
	public void result() {
		ArrayList <Character>resultMath=new ArrayList<Character>();
		ArrayList<String> resultNum=new ArrayList<String>();
		ArrayList<Double> resultNum2=new ArrayList<Double>(); //ArrayList�� ����
		
		String a=ta.getText();
//		String a="250x2.1+3-210%10+55%5+2%5";
		int resultNumLength = -1;
		

		if(!(a.contains("+")|a.contains("-")|a.contains("x")|a.contains("%"))) {
//			taresult.append(ta.getText());
		}
		else if(a.contains("+")|a.contains("-")|a.contains("x")|a.contains("%")) {
			for (int i = 0; i < a.split("\\+|-|x|%").length; i++) { //���� ArrayList�� ����
				resultNum.add(a.split("\\+|-|x|%")[i]);
				resultNum2.add(Double.parseDouble(a.split("\\+|-|x|%")[i]));
			}
			for (int i = 0; i < a.split("\\+|-|x|%").length-1; i++) {
				resultNumLength+=resultNum.get(i).length()+1; //���� �ڸ��� ���ϱ�
//				System.out.println(resultNumLength);
				resultMath.add(a.charAt(resultNumLength));
			}
//			System.out.println(resultNum2);
//			System.out.println(resultMath);
			for (int i = 0; i < resultMath.size(); i++) {
				if(resultMath.get(i)=='%') {
					resultNum2.set(i,resultNum2.get(i)/resultNum2.get(i+1));
					resultNum2.remove(i+1);
					resultMath.remove(i);
					i--;
				}
			}
			for (int i = 0; i < resultMath.size(); i++) {
				if(resultMath.get(i)=='x') {
					resultNum2.set(i,resultNum2.get(i)*resultNum2.get(i+1));
					resultNum2.remove(i+1);
					resultMath.remove(i);
					i--;
				}
			}
			for (int i = 0; i < resultMath.size(); i++) {
				if(resultMath.get(i)=='+') {
					resultNum2.set(i,resultNum2.get(i)+resultNum2.get(i+1));
					resultNum2.remove(i+1);
					resultMath.remove(i);
					i--;
				}
			}
			for (int i = 0; i < resultMath.size(); i++) {
				if(resultMath.get(i)=='-') {
					resultNum2.set(i,resultNum2.get(i)-resultNum2.get(i+1));
					resultNum2.remove(i+1);
					resultMath.remove(i);
					i--;
				}
			}
			taresult.append(ta.getText()+"="+Double.toString(resultNum2.get(0))+"\n");
			
		}
	}

	@Override
	public void textValueChanged(TextEvent e) {

		
	}



	public static void main(String[] args) {
		new Window_Calculator().result();
	}

}
