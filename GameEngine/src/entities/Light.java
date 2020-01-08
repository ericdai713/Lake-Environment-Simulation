package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
	private Vector3f colour;
	private Vector3f attenuation = new Vector3f(1,0,0);
	
	public Light(Vector3f position, Vector3f colour) {
		this.position = position;
		this.colour = colour;
	}
	
	public Light(Vector3f position, Vector3f colour, Vector3f attenuation) {
		this.position = position;
		this.colour = colour;
		this.attenuation = attenuation;
	}

	public Vector3f getAttenuation() {
		return attenuation;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	// ONLY FOR SUN !!
	public void setSunPositionByTime(int time) {
		this.position.x = 0 + (float) (((float) (time - 0) / (float) 24) * (1100 - 0));
		
		float sunY = -1000;
		if (time >= 0 && time <= 12) {
			sunY = -1000 + ((float)(time - 0) / (float)12) * (1000 - (-1000));
		}
		if (time > 12 && time <= 24) {
			sunY = -1000 + (1 - (float)(time - 12) / (float)12) * (1000 - (-1000));
		}
		this.position.y = sunY;
	}
	
	// ONLY FOR SUN !!
	public void setSunColourByTime(int time) {
		float intensity = 0;
		if (time >= 0 && time <= 12) {
			intensity = (float)(time - 0) / (float)12;
		}
		if (time >= 12 && time <= 24) {
			intensity = 1 - (float)(time - 12) / (float)12;
		}
		this.colour = new Vector3f(intensity, intensity, intensity);
	}
	
	// ONLY FOR LAMP
	public void setLampColourByTime(int time) {
		if ((time >= 17 && time <= 24) || (time >= 0 && time <= 5)) {
			this.colour =  new Vector3f(0,2,2);
		}
		else {
			this.colour =  new Vector3f(0.0f,0.0f,0.0f);
		}
		
		
	}
	
	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	

}
