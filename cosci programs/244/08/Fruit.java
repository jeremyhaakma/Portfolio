//Fruit.java

public class Fruit extends Thread {

   private String name;
   private int pauseSeconds;
   
   public Fruit (String name, int pauseSeconds) {
      this.name = name;
      this.pauseSeconds = pauseSeconds;
   }

   public void run() {
      for (int i = 0; i < 10; i++) {
         try {
            Thread.sleep(pauseSeconds * 1000);  // convert to milliseconds
         } catch (InterruptedException e) {
            System.err.println(e);
         }
         System.out.printf("%02d ", System.currentTimeMillis() / 1000 % 60);
         System.out.println(name + " " + i);
      }
   }
}
