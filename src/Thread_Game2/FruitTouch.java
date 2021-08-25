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
	Font font=new Font("궁서",Font.BOLD,20);
	
	Image buffImage; // 더블 버퍼링용
	Graphics buffg; // 더블 버퍼링용

	public Pan getPan() {
		return pan;
	}

	public void setPan(Pan pan) {
		this.pan = pan;
	}

	public void paint(Graphics g) { // 더블버퍼링
		buffImage = createImage(40, 40); // 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		update(g);
	}

	public void Draw_Char() { // 실제로 그림들을 그릴 부분
//			buffg.clearRect(0, 100, 500, 400);
		for (int i = 0; i < action.length; i++) {
			buffg.drawImage(action[i].getImg(), action[i].getX(), action[i].getY(), 40, 40, this);
		}
	}

	public void update(Graphics g) {
		Draw_Char();// 실제로 그려진 그림을 가져온다
		g.drawImage(buffImage, 0, 0, this); // 화면에 버퍼에 그린 그림을 가져와 그리기
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
		setLayout(null); // 라벨이랑 캔버스랑 각각 사용자 지정으로 위치 조절하기 위해 setLayout(null)로 지정
		scorelabel.setBounds(100, 0, 200, 100); // 각각 사용자 지정해줌
		timerlabel.setBounds(300,0,200,100);
		pan.setBounds(0, 100, 500, 400);
		this.add(scorelabel); // frame에 라벨 추가
		this.add(timerlabel);
		this.add(pan); // frame에 캔버스 추가
		
		Timer m_timer=new Timer(); //실제 타이머 기능 제공
		TimerTask m_task=new TimerTask(){ //timer클래스가 수행되어야하는 내용 작성
			@Override
			public void run() {
				if(timecount>0) {
					timecount--;
					timerlabel.setText("time:"+Integer.toString(timecount));
				}
				else {
					m_timer.cancel(); //타이머 종료 메소드
					System.out.println("score:"+score);
					System.exit(0);
				}
				
			}
		};
		m_timer.schedule(m_task, 0,1000);
		//schedule(TimerTask객체,최초 delay시간,반복주기)-반복주기 간격으로 run메소드 실행

		pan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getX(); // 마우스 클릭한 좌표값 저장
				my = e.getY();
//				System.out.println(mx);
//				System.out.println(my);
				for (int i = 1; i < 4; i++) {
					if (mx > action[i].getX() + 5 && mx < action[i].getX() + 20 && my > action[i].getY() + 5
							&& my < action[i].getY() + 20) {
						score+=3;
						scorelabel.setText("score:" + Integer.toString(score));
						action[i].setX(-(int)(Math.random()*10));
						action[i].setY((int)(Math.random() * 400));// 이미지 클릭시 해당 이미지 좌표 다시 초기화
					}
				} // for
				for (int i = 4; i < 7; i++) {
					if (mx > action[i].getX() + 5 && mx < action[i].getX() + 60 && my > action[i].getY() + 5
							&& my < action[i].getY() + 60) {
						score++;
						scorelabel.setText("score:" + Integer.toString(score));
						action[i].setX(-(int)(Math.random()*10));
						action[i].setY((int)(Math.random() * 400));// 이미지 클릭시 해당 이미지 좌표 다시 초기화
					}
				} // for
				if (mx > action[0].getX()  && mx < action[0].getX() + 80 && my > action[0].getY()
						&& my < action[0].getY() + 80) {
					System.out.println("score:"+score);
					System.exit(0); // 폭탄 클릭시 게임종료
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
	}// FruitTouch생성자

	public class Pan extends Canvas {
		public Pan() {

			Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지 저장
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
		}// pan생성자

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