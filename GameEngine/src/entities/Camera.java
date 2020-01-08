package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import terrains.Terrain;
import textures.ModelTexture;

public class Camera {
	
	private Vector3f position = new Vector3f(550,10,0);
	private float pitch = 10;
	private float yaw = 0;
	private float roll = 0;
	Loader loader = new Loader();
	Terrain terrain1 = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
	Terrain terrain2 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
	Terrain terrain3 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
	Terrain terrain4 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
	public Camera(){}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.y+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_T)){
			pitch+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_G)){
			pitch-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			yaw-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_H)){
			yaw+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			roll+=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)){
			roll-=0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_N)){
			position.x = 550f;
			position.y = 5f;
			position.z = 0f;
			pitch = 10f;
			yaw = 0f;
			roll = 0f;
		}
		if(position.x<=800 && position.x>=0 && position.z<=800 && position.z>=0){
			if(position.y<terrain1.getHeightOfTerrain(position.x, position.z)){
				position.y = terrain1.getHeightOfTerrain(position.x, position.z)+2;
			}
		}else if(position.x<0 && position.x>=-800 && position.z<=800 && position.z>=0){
			if(position.y<terrain2.getHeightOfTerrain(position.x, position.z)){
				position.y = terrain2.getHeightOfTerrain(position.x, position.z)+2;
			}
		}else if(position.x<=800 && position.x>=0 && position.z<0 && position.z>=-800){
			if(position.y<terrain3.getHeightOfTerrain(position.x, position.z)){
				position.y = terrain3.getHeightOfTerrain(position.x, position.z)+2;
			}
		}else if(position.x<0 && position.x>=-800 && position.z<0 && position.z>=-800){
			if(position.y<terrain4.getHeightOfTerrain(position.x, position.z)){
				position.y = terrain4.getHeightOfTerrain(position.x, position.z)+2;
			}
		}
	}
	
	public void invertPitch(){
		this.pitch = -pitch;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
