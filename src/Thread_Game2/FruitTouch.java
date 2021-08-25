package Thread_Game2;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class FruitTouch extends Frame {

	private Pan pan;
	private Image bi[] = new Image[7];
	private FruitAction action[] = new FruitAction[7];
	private Fruit[] f = new Fruit[7];
	private int score = 0;
	private int mx, my;
	private Label scorelabel;
	private Label timerlabel;
	int timecount=20;
	Font font=new Font("�ü�",Font.BOLD,20);
	
	Image buffImage; // ���� ���۸���
	Graphics buffg; // ���� ���۸���

	public Pan getPan() {
		return pan;
	}

	public void setPan(Pan pan) {
		this.pan = pan;
	}

	public void paint(Graphics g) { // ������۸�
		buffImage = createImage(40, 40); // ������۸� ���� ũ�⸦ ȭ�� ũ��� ���� ����
		buffg = buffImage.getGraphics(); // ������ �׷��� ��ü�� ���
		update(g);
	}

	public void Draw_Char() { // ������ �׸����� �׸� �κ�
//			buffg.clearRect(0, 100, 500, 400);
		for (int i = 0; i < action.length; i++) {
			buffg.drawImage(action[i].getImg(), action[i].getX(), action[i].getY(), 40, 40, this);
		}
	}

	public void update(Graphics g) {
		Draw_Char();// ������ �׷��� �׸��� �����´�
		g.drawImage(buffImage, 0, 0, this); // ȭ�鿡 ���ۿ� �׸� �׸��� ������ �׸���
		repaint();
	}

	public FruitTouch() {
		pan = new Pan();
		scorelabel = new Label();
		timerlabel = new Label();
		scorelabel.setFont(font);
		timerlabel.setFont(font);
		scorelabel.setText("score:0");
//		timerlabel.setText("time:"+Integer.toString(timecount));
		setLayout(null); // ���̶� ĵ������ ���� ����� �������� ��ġ �����ϱ� ���� setLayout(null)�� ����
		scorelabel.setBounds(100, 0, 200, 100); // ���� ����� ��������
		timerlabel.setBounds(300,0,200,100);
		pan.setBounds(0, 100, 500, 400);
		this.add(scorelabel); // frame�� �� �߰�
		this.add(timerlabel);
		this.add(pan); // frame�� ĵ���� �߰�
		
		Timer m_timer=new Timer(); //���� Ÿ�̸� ��� ����
		TimerTask m_task=new TimerTask(){ //timerŬ������ ����Ǿ���ϴ� ���� �ۼ�
			@Override
			public void run() {
				if(timecount>0) {
					timecount--;
					timerlabel.setText("time:"+Integer.toString(timecount));
				}
				else {
					m_timer.cancel(); //Ÿ�̸� ���� �޼ҵ�
					System.out.println("score:"+score);
					System.exit(0);
				}
				
			}
		};
		m_timer.schedule(m_task, 0,1000);
		//schedule(TimerTask��ü,���� delay�ð�,�ݺ��ֱ�)-�ݺ��ֱ� �������� run�޼ҵ� ����

		pan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getX(); // ���콺 Ŭ���� ��ǥ�� ����
				my = e.getY();
//				System.out.println(mx);
//				System.out.println(my);
				for (int i = 1; i < 4; i++) {
					if (mx > action[i].getX() + 5 && mx < action[i].getX() + 20 && my > action[i].getY() + 5
							&& my < action[i].getY() + 20) {
						score+=3;
						scorelabel.setText("score:" + Integer.toString(score));
						action[i].setX(-(int)(Math.random()*10));
						action[i].setY((int)(Math.random() * 400));// �̹��� Ŭ���� �ش� �̹��� ��ǥ �ٽ� �ʱ�ȭ
					}
				} // for
				for (int i = 4; i < 7; i++) {
					if (mx > action[i].getX() + 5 && mx < action[i].getX() + 60 && my > action[i].getY() + 5
							&& my < action[i].getY() + 60) {
						score++;
						scorelabel.setText("score:" + Integer.toString(score));
						action[i].setX(-(int)(Math.random()*10));
						action[i].setY((int)(Math.random() * 400));// �̹��� Ŭ���� �ش� �̹��� ��ǥ �ٽ� �ʱ�ȭ
					}
				} // for
				if (mx > action[0].getX()  && mx < action[0].getX() + 80 && my > action[0].getY()
						&& my < action[0].getY() + 80) {
					System.out.println("score:"+score);
					System.exit(0); // ��ź Ŭ���� ��������
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				for (int i = 0; i < action.length; i++) {
					f[i] = new Fruit(action[i], FruitTouch.this);
					f[i].start();
				}
			}

		});

		this.setBounds(10, 10, 500, 500);
		this.setVisible(true);
	}// FruitTouch������

	public class Pan extends Canvas {
		public Pan() {

			Toolkit tk = Toolkit.getDefaultToolkit(); // �̹��� ����
			bi[0] = tk.getImage("images/bomb.png");
			bi[1] = tk.getImage("images/apple.png");
			bi[2] = tk.getImage("images/avocado.png");
			bi[3] = tk.getImage("images/grape.png");
			bi[4] = tk.getImage("images/mango.png");
			bi[5] = tk.getImage("images/tomato.png");
			bi[6] = tk.getImage("images/watermelon.png");

			for (int i = 0; i < action.length; i++) {
				action[i] = new FruitAction(-i * 50, (int)(Math.random() * 400), bi[i]);

			}
		}// pan������

		@Override
		public void paint(Graphics g) {
			g.drawImage(action[0].getImg(), action[0].getX(), action[0].getY(), 90, 90, this);
			for (int i = 1; i < 4; i++) {
				g.drawImage(action[i].getImg(), action[i].getX(), action[i].getY(), 30, 30, this);
			}
			for (int i = 4; i < 7; i++) {
				g.drawImage(action[i].getImg(), action[i].getX(), action[i].getY(), 70, 70, this);
			}
		}

		@Override
		public void update(Graphics g) {
			super.update(g);
			paint(g);
		}
	}

	public static void main(String[] args) {
		new FruitTouch();
	}
}