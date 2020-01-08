package guis;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {
	
	private int texture;
	private Vector2f position;
	private Vector2f scale;
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		super();
		this.texture = texture;
		this.position = position;
		this.scale = scale;
	}
	
	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPositionByTime(int time) {
		this.position.x = (float) (0.375 + ((float) (time - 0) / (float) 24)*(0.9 - 0.375));
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	
	
}
