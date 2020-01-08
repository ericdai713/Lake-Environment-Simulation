package postProcessing;

import org.lwjgl.input.Keyboard;

public class PostProcessingController {
	public boolean bloom;
	public boolean blur;
	public boolean contrast;
	public boolean isPostProcessing;
	
	
	public PostProcessingController() {
		super();
		this.bloom = false;
		this.blur = false;
		this.contrast = false;
		this.isPostProcessing = false;
	}


	public void update() {
		// post effect
		if(Keyboard.isKeyDown(Keyboard.KEY_B)) {
			bloom = true;
			blur = false;
			contrast = false;
			isPostProcessing = true;
		}
					
		if(Keyboard.isKeyDown(Keyboard.KEY_V)) {
			bloom = false;
			blur = true;
			contrast = false;
			isPostProcessing = true;
		}
					
		if(Keyboard.isKeyDown(Keyboard.KEY_C)) {
			bloom = false;
			blur = false;
			contrast = true;
			isPostProcessing = true;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
			bloom = false;
			blur = false;
			contrast = false;
			isPostProcessing = false;
		}
	}
}
