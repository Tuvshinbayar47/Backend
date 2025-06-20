package ItpecB;

public class CountMain {
	  public static void main(String[] args) throws InterruptedException {
	    int countdown = 10;

	    while (countdown > 0) {
	      System.out.println(countdown);
	      Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
	      countdown--;
	    }

	    System.out.println("🎉 Happy New Year!! 🎉");
	  }
	}


