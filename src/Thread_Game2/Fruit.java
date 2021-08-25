package Thread_Game2;


public class Fruit extends Thread{
	private FruitTouch f;
	private FruitAction action;
	public Fruit(FruitAction action,FruitTouch f) {
		super();
		this.action=action;
		this.f=f;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
				action.setX(action.getX()+(int)(Math.random()*7));
				f.getPan().repaint();
				
				if(action.getX()>500) {
					action.setX(0);
					action.setY((int)(Math.random()*400));
					f.getPan().repaint();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
	
	

}