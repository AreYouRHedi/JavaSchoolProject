package des_petits_exercices;



class MonThread1 extends Thread {
	private String affiche;

	public MonThread1(String affiche) {
		this.affiche = affiche;
	}

	@Override
	public void run() {
		for (int i = 1; i < 100; i++){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				interrupt();
			}
			System.out.println(i + " " + affiche);
		}
	}
}

class ExempleMonThread1 {
	public static void main(String[] args) {
		MonThread1 thread1 = new MonThread1("hello");
		MonThread1 thread2 = new MonThread1("world");
		MonThread1 thread3 = new MonThread1("what's");
		MonThread1 thread4 = new MonThread1("up");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Goodbye");
		
	}
}
