package time;

import org.lwjgl.input.Keyboard;

public class TimeController {
private int time = 0;
	
	public TimeController(int time) {
		super();
		this.time = time;
    }

	public void updateTime() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_UP && time <= 24) {
					time += 1;
					System.out.print(time + "\n");
					if (time > 24) {
						time = 0;
					}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && time >= 0) {
					time -= 1;
					System.out.print(time + "\n");
					if (time < 0) {
						time = 24;
					}
				}
			} 
			else {
				if(Keyboard.getEventKey() == Keyboard.KEY_UP) {
					//System.out.print("release\n");
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					//System.out.print("release\n");
				}
			}
		}
	}

	public int getTime() {
		return time;
	}
}
