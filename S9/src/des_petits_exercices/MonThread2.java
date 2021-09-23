package des_petits_exercices;

class MonRunnable implements Runnable {
	private String affiche;
	private boolean arret=false;

	public MonRunnable(String affiche) {
		this.affiche = affiche;
	}

	@Override
	public void run() {
		for (int i = 1; i < 100; i++){
			if(!arret) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i + " " + affiche);
			}
			else {
				break;
			}
			
		}

	}
	public void arret() {
		arret=true;
	}
}

class ExempleMonThread2{
	public static void main(String[] args) {
		Runnable r1 = new MonRunnable("hello");
		Runnable r2 = new MonRunnable("world");
		Runnable r3 = new MonRunnable("what's");
		Runnable r4 = new MonRunnable("up");
		Thread thread1 = new Thread(r1);
		Thread thread2 = new Thread(r2);
		Thread thread3 = new Thread(r3);
		Thread thread4 = new Thread(r4);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		try {
			Thread.sleep(1000);
			((MonRunnable) r1).arret();
			((MonRunnable) r2).arret();
			((MonRunnable) r3).arret();
			((MonRunnable) r4).arret();
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Goodbye");
		
	}
}
