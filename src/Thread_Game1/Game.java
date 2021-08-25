package Thread_Game1;
//왜 WindowThreadEx2에서 StringAction을 때로 뺀거지?-x,y좌표 저장하려고?

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Frame {
	private Pan pan;
	private String drop="똥";
	private String people="나";
	private Font font=new Font("궁서", Font.BOLD, 30);
	
	
	
	private int x[]=new int[7];
	private int y[]=new int[7];
	private int cnt=0;
	
	private int a=230;
	private int b=460;
	
	
	
	
	public Pan getPan() {
		return pan;
	}


	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	


	

	public Game() {
		pan=new Pan();
		this.add(pan); //캔버스 프레임에 덮어씀
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==37) { //<-
					if(a>0)
					a-=5;
				}else if(e.getKeyCode()==39) { //->
					if(a<422)
					a+=5;
				}
				
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {//윈도우가 처음 생성됐을 때 실행 //왜 여기서 thread를 실행시키는건지?
				threadRun t=new threadRun();
				t.start();
				
			}
			
		});
		
		this.setBounds(20, 20, 500, 500);
		this.setVisible(true);
		
	}
	

	
	public class Pan extends Canvas{
		public Pan() {
			for (int i = 0; i < x.length; i++) {
				x[i]=(int)(Math.random()*500);
				y[i]=-10-i*70;
			}
			this.setFont(font);
			
		}

		@Override
		public void paint(Graphics g) {
			for (int i = 0; i < x.length; i++) {
				g.drawString(drop, x[i], y[i]);
				
			}
			g.drawString(people, a, b);
		}

//		@Override
//		public void update(Graphics g) { //update안의 super.update에 clear기능이 있음. 클리어 안하려면 이 메소드 오버라이딩해서 super.update를 제거하면 됨-맞나?
//			super.update(g);
//			paint(g);
//		}
		
	}
	
	public class threadRun extends Thread{
		public threadRun() {
		}

		@Override
		public void run() {
			while(true) {
				for (int j = 0; j < x.length; j++) {
					try {
						Thread.sleep(40-cnt>10?40-cnt:1);
						y[j]+=40;
						getPan().repaint(); //여기서 repaint가 update를 호출하는거 맞나? 그래서 좌표가 계속 삭제되고 덮어써지는거?
						for (int i = 0; i < x.length; i++) {
							if(a+10>x[i] && x[i]>a-10 && y[i]>b) {
								System.exit(0);
							}
							
						}
						if(y[j]>510) {
							x[j]=(int)(Math.random()*500);
							y[j]=-10-j*70;
							cnt++;
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}



}
