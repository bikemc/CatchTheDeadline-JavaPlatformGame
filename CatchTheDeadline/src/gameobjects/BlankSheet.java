package gameobjects;

import gameManager.Animation;
import gameManager.Handler;
import gameManager.Texture;
import window.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BlankSheet extends GameObject {
      
   // properties
	private Animation blankSheetFlip;

	private final float MAX_SPEED = 10;
	private Handler handler;
	Texture texture = GameEngine.getInstance();

	ImageIcon blankSheet = new ImageIcon(getClass().getResource("/images/blankSheet.png"));

	private float gravity = 0.15f;
	private float width = blankSheet.getIconWidth(), height = blankSheet.getIconHeight();

	// constructor
	public BlankSheet( float x, float y, Handler handler, ObjectType type ) {
		super(x, y, type);
		this.handler = handler;
		blankSheetFlip = new Animation( 1, texture.blankSheetFlipping );
		setVelocityX(5);
	}

	// methods
	public void collisions( LinkedList<GameObject> objects ) {

		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = null;
			try {
			 temp = handler.objectLinkedList.get(i);
			}
			catch (Exception e) {}
			/*if( temp.getType() == ObjectType.Block )
			{
				if( objectBounds().intersects( temp.objectBounds() ) ) {
					posY = temp.getPosY() - height;
					velocityY = 0;
					falling = false;
					jumping = false;
				}
				else
					falling = true;
			}*/
			if( temp.getType() == ObjectType.Player )
			{
			    if( objectBoundsTop().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			    }
			    if( objectBounds().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			    }
			    if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			    }
			    if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			    }
			    // end level here
			}
		}
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	@Override
	public void collisionDetector(LinkedList<GameObject> objects ) {
		collisions( objects );
		blankSheetFlip.runAnimation(); 
	}

	@Override
	public void render(Graphics graphics) {
		blankSheetFlip.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
	}

	@Override
	public Rectangle objectBounds() {
		return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int) ((int)posY+(height/2)), (int)width/2, (int)height/2 );
	}

	public Rectangle objectBoundsTop() {
		return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int)posY, (int)width/2, (int)height/2 );
	}

	public Rectangle objectBoundsRight() {
		return new Rectangle( (int) ((int)posX+width-5), (int)posY+5, (int)5, (int)height-10 );
	}

	public Rectangle objectBoundsLeft() {
		return new Rectangle( (int)posX, (int)posY+5, (int)5, (int)height-10 );
	}

	public Handler getHandler() {
		 return handler;
	}
}