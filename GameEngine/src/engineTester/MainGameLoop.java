package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import postProcessing.Fbo;
import postProcessing.PostProcessing;
import postProcessing.PostProcessingController;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import time.TimeController;
import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
import water.WaterFrameBuffers;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Terrain terrain1 = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
		Terrain terrain2 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
		Terrain terrain3 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
		Terrain terrain4 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")),"heightmap");
		
		RawModel tree = OBJLoader.loadObjModel("tree", loader);
		RawModel lamp = OBJLoader.loadObjModel("lamp", loader);
		RawModel grass =  OBJLoader.loadObjModel("grassModel", loader);
		RawModel fern =  OBJLoader.loadObjModel("fern", loader);
		
		TexturedModel statictree = new TexturedModel(tree,new ModelTexture(loader.loadTexture("tree")));
		TexturedModel staticlamp = new TexturedModel(lamp,new ModelTexture(loader.loadTexture("lamp")));
		staticlamp.getTexture().setUseFakeLighting(true);
		TexturedModel staticgrass = new TexturedModel(grass,new ModelTexture(loader.loadTexture("grassTexture")));
		staticgrass.getTexture().setHasTransparency(true);
		staticgrass.getTexture().setUseFakeLighting(true);
		TexturedModel staticfern = new TexturedModel(fern,new ModelTexture(loader.loadTexture("fern")));
		staticfern.getTexture().setHasTransparency(true);
		staticfern.getTexture().setUseFakeLighting(true);
		
		List<Entity> entities = new ArrayList<Entity>();
		List<Terrain> terrains = new ArrayList<Terrain>();
		//random generate trees, grasses, and ferns
		Random random = new Random();
		for(int i=0;i<100;i++){
			float x = random.nextFloat()*800 - 400;
			float z = random.nextFloat() * -600;
			float y = 0;
			if(x<=800 && x>=0 && z<=800 && z>=0){
				y = terrain1.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<=800 && z>=0){
				y = terrain2.getHeightOfTerrain(x, z);
			}else if(x<=800 && x>=0 && z<0 && z>=-800){
				y = terrain3.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<0 && z>=-800){
				y = terrain4.getHeightOfTerrain(x, z);
			}
			entities.add(new Entity(statictree, new Vector3f(x, y, z),0,0,0,6));
		}
		
		entities.add(new Entity(statictree, new Vector3f(490, terrain3.getHeightOfTerrain(490, -110), -110),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(510, terrain3.getHeightOfTerrain(510, -100), -100),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(520, terrain3.getHeightOfTerrain(520, -200), -200),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(540, terrain3.getHeightOfTerrain(540, -210), -210),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(600, terrain3.getHeightOfTerrain(600, -210), -210),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(620, terrain3.getHeightOfTerrain(620, -230), -230),0,0,0,6));
		entities.add(new Entity(statictree, new Vector3f(650, terrain3.getHeightOfTerrain(650, -150), -150),0,0,0,6));
		
		/*
		for(int i=0;i<500;i++){
			float x = random.nextFloat()*800 - 400;
			float z = random.nextFloat() * -600;
			float y = 0;
			if(x<=800 && x>=0 && z<=800 && z>=0){
				y = terrain1.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<=800 && z>=0){
				y = terrain2.getHeightOfTerrain(x, z);
			}else if(x<=800 && x>=0 && z<0 && z>=-800){
				y = terrain3.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<0 && z>=-800){
				y = terrain4.getHeightOfTerrain(x, z);
			}
			entities.add(new Entity(staticgrass, new Vector3f(x, y, z),0,0,0,1));
		}*/
		
		for(int i=0;i<500;i++){
			float x = random.nextFloat()*800 - 400;
			float z = random.nextFloat() * -600;
			float y = 0;
			if(x<=800 && x>=0 && z<=800 && z>=0){
				y = terrain1.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<=800 && z>=0){
				y = terrain2.getHeightOfTerrain(x, z);
			}else if(x<=800 && x>=0 && z<0 && z>=-800){
				y = terrain3.getHeightOfTerrain(x, z);
			}else if(x<0 && x>=-800 && z<0 && z>=-800){
				y = terrain4.getHeightOfTerrain(x, z);
			}
			entities.add(new Entity(staticfern, new Vector3f(x, y, z),0,0,0,0.6f));
		}
		List<Light> lights = new ArrayList<Light>();
		// sun
		Light sun = new Light(new Vector3f(0,0,-10), new Vector3f(0.4f,0.4f,0.4f));
		lights.add(sun);
		////put point light right above lamps, height = 14.7
		lights.add(new Light(new Vector3f(610,14.7f + terrain3.getHeightOfTerrain(610, -100),-100), new Vector3f(2,0,0), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(500,14.7f + terrain3.getHeightOfTerrain(500, -110), -110), new Vector3f(0,2,2), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(550,14.7f + terrain3.getHeightOfTerrain(550, -200), -200), new Vector3f(0,2,2), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(650,14.7f + terrain3.getHeightOfTerrain(650, -220), -220), new Vector3f(0,2,2), new Vector3f(1,0.01f,0.002f)));
		
		//lamp
		entities.add(new Entity(staticlamp, new Vector3f(610,terrain3.getHeightOfTerrain(610, -100), -100),0,0,0,1));
		entities.add(new Entity(staticlamp, new Vector3f(500,terrain3.getHeightOfTerrain(500, -110), -110),0,0,0,1));
		entities.add(new Entity(staticlamp, new Vector3f(550,terrain3.getHeightOfTerrain(550, -200), -200),0,0,0,1));
		entities.add(new Entity(staticlamp, new Vector3f(650,terrain3.getHeightOfTerrain(650, -220), -220),0,0,0,1));
		
		terrains.add(terrain1);
		terrains.add(terrain2);
		terrains.add(terrain3);
		terrains.add(terrain4);
		
		
		Camera camera = new Camera();	
		MasterRenderer renderer = new MasterRenderer(loader);
		
		WaterFrameBuffers fbos = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), fbos);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile(550, -75, -8);
		waters.add(water);
		
		// GUI
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("day_night"), new Vector2f(0.7f, 0.9f), new Vector2f(0.35f, 0.054f));
		guis.add(gui);
		gui = new GuiTexture(loader.loadTexture("num"), new Vector2f(0.68f, 0.8f), new Vector2f(0.35f, 0.054f));
		guis.add(gui);
		gui = new GuiTexture(loader.loadTexture("button"), new Vector2f(0.375f, 0.9f), new Vector2f(0.005f, 0.042f));
		guis.add(gui);
		
		Fbo fbo = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_RENDER_BUFFER);
		PostProcessing.init(loader);
		PostProcessingController postProcessingController = new PostProcessingController();
		
		/*GuiTexture refraction = new GuiTexture(fbos.getRefractionTexture(), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
		GuiTexture reflection = new GuiTexture(fbos.getReflectionTexture(), new Vector2f(-0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
		guis.add(refraction);
		guis.add(reflection);*/
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		TimeController timeController = new TimeController(0);
		
		while(!Display.isCloseRequested()){
			camera.move();
			int time = timeController.getTime();
			
			// Change the position of the button
			guis.get(2).setPositionByTime(time);
			
			// Change the position of sun
			lights.get(0).setSunPositionByTime(time);
			lights.get(0).setSunColourByTime(time);
			
			// Change the intensity of lamps
			for(int i = 1; i < 5; i ++) {
				lights.get(i).setLampColourByTime(time);
			}
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			//render reflection texture
			fbos.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, terrains, lights, camera, time, new Vector4f(0, 1, 0, -water.getHeight()+1f));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			//render refraction texture
			fbos.bindRefractionFrameBuffer();
			renderer.renderScene(entities, terrains, lights, camera, time, new Vector4f(0, -1, 0, water.getHeight()));
			
			//render to screen
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			fbos.unbindCurrentFrameBuffer();
			
			renderer.renderScene(entities, terrains, lights, camera, time, new Vector4f(0, -1, 0, 10000));
			waterRenderer.render(waters, camera, sun);
			
			postProcessingController.update();
			if(postProcessingController.isPostProcessing) {
				// With postProcessing
				fbo.bindFrameBuffer();
				renderer.renderScene(entities, terrains, lights, camera, time, new Vector4f(0, -1, 0, 10000));
				waterRenderer.render(waters, camera, sun);
				fbo.unbindFrameBuffer();
				// blooming
				if (postProcessingController.bloom) {
					PostProcessing.doBloom(fbo.getColourTexture());
				}
				else if (postProcessingController.blur) {
					PostProcessing.doBlur(fbo.getColourTexture());
				}
				else if (postProcessingController.contrast) {
					PostProcessing.doContrast(fbo.getColourTexture());
				}
			}
			else {
				// No postProcessing
				renderer.renderScene(entities, terrains, lights, camera, time, new Vector4f(0, -1, 0, 10000));
				waterRenderer.render(waters, camera, sun);
			}
			
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
			timeController.updateTime();
		}
		
		PostProcessing.cleanUp();
		fbo.cleanUp();
		fbos.cleanUp();
		guiRenderer.cleanUp();
		waterShader.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
