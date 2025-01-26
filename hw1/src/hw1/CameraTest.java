package hw1;

public class CameraTest {
	public static void main(String args[]) {
		CameraBattery cb = new CameraBattery(1000,1500);
		
		cb.moveBatteryExternal();
		cb.buttonPress();
		cb.externalCharge(2000);
		
		
	}
	
}
