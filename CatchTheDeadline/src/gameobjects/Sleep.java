package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Sleep extends PowerUps {
	
ImageIcon sleep = new ImageIcon(getClass().getResource("/images/sleep.png"));
	
    public Sleep( float x, float y, ObjectType type, int value ){
          super( x, y, type, value );
          this.value = value;
    }
      
    @Override
    public void collisionDetector(LinkedList<GameObject> objects) {}
     
    @Override
    public void render(Graphics graphics) {
    	graphics.drawImage( sleep.getImage(), (int) posX-18, (int) posY-18, null );
    }
      
    @Override
    public Rectangle objectBounds() {
    	return new Rectangle((int) posX, (int) posY, 50, 50 );
    }
}