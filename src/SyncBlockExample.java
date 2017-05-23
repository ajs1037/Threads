/*
 * Below is an example shows how synchronized methods and object locks are 
 * used to coordinate access to a common object by multiple threads. If the 
 * ‘synchronized’ keyword is removed, the message is displayed in random fashion.
 */
public class SyncBlockExample extends Thread {
	static String[] msg = { "Beginner", "java", "tutorial,", ".,",
							"com", "is", "the", "best" };
    
	public SyncBlockExample(String id) {
		super(id);
	}
	
	public static void main(String[] args) {
		SyncBlockExample thread1 = new SyncBlockExample("thread1: ");
		SyncBlockExample thread2 = new SyncBlockExample("thread2: ");
    
		thread1.start();
		thread2.start();
    
        boolean t1IsAlive = true;
        boolean t2IsAlive = true;
        
        do {
            if(t1IsAlive && !thread1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }
            
            if(t2IsAlive && !thread2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);
    }                            
                                
    
    void randomWait() {
        try {
            Thread.currentThread().sleep((long)(3000*Math.random()));
        } catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }    

    
    public void run() {
        synchronized(System.out) {
            for( int i=0; i<msg.length; i++ ) {
                randomWait();
                System.out.println(getName() + msg[i]);
            }    
        }
    }
}   

   