import javax.swing.*;
import java.awt.*;

public class AntScenario extends JPanel {

 private int scaling = 8;
 
 public AntScenario() {
  setPreferredSize(new Dimension(etude13.plane.length*scaling, etude13.plane[0].length*scaling));
  setBackground(new Color(120, 180, 100));
 }

 public void paintComponent(Graphics g) {
  super.paintComponent(g);

  //Draw ant trails
  for (int i = 0; i < etude13.plane.length; i++) {
   for (int j = 0; j < etude13.plane[0].length; j++) {
    char c = etude13.plane[i][j][0];
    if (c == 'a') {
     g.setColor(Color.orange);
     g.fillRect(j*scaling, i*scaling, scaling, scaling);
    } else if (c == 'z') {
     g.setColor(new Color(150, 100, 10));
     g.fillRect(j*scaling, i*scaling, scaling, scaling);
    }
   }
  }

  //Draw uninfected ants (Green)
  for(Ant ant : etude13.antList){
   g.setColor(Color.black);
   g.fillOval(ant.getX()*scaling, ant.getY()*scaling, scaling, scaling);
  }

  //Draw zombies (Red)
  for(Ant zombie : etude13.zombieList){
   g.setColor(Color.red);
   g.fillOval(zombie.getX()*scaling, zombie.getY()*scaling, scaling, scaling);
  }


 }



}
