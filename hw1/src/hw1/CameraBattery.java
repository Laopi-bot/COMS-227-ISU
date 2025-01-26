package hw1;

/**
 * The CameraBattery class gives users the power to create and modify a
 * battery's charge along with simulating a camera and a charger to interact
 * with the battery the user creates
 * 
 * @author Lawson Port
 *
 */
public class CameraBattery {

	// instance variables

	/**
	 * stores the capacity of the battery (i.e. that battery's maximum charge)
	 */
	private double batteryCapacity = 0;

	/**
	 * this variable stores the current charge of the battery
	 */
	private double currentBatteryCharge = 0;

	/**
	 * this variable stores the current charge of the camera
	 */
	private double currentCameraCharge = 0;

	/**
	 * this variable stores the current setting of the charger. may only vary
	 * between 0 and 3
	 */
	private int chargerSetting = 0;

	/**
	 * this variable keeps track of the total drain that the battery experiences
	 */
	private double totalDrain = 0;

	/**
	 * this variable mimics the default power consumption constant double, but is
	 * added into calculations so if the user wanted to change said power
	 * consumption, they could and not have to modify the existing code
	 * 
	 * it felt redundant to multiply the default power consumption by the custom
	 * power consumption every time, so i just set this variable to be equal to the
	 * constant and then included that to be in calculations so it could be modified
	 * by the user as they please
	 */
	private double cameraPowerConsumption = 1;

	/**
	 * this variable keeps track of if the battery is connected to the camera by
	 * setting the value equal to 1 or 0 if the battery is disconnected, any camera
	 * charge calculation will result in 0 equals 0 if not connected and 1 if
	 * connected to the camera
	 */
	private double batteryConnected = 0;

	/**
	 * this variable keeps track of if the battery is connected to the charger by
	 * setting the value equal to 1 or 0 used in external charge calculation equals
	 * 0 if not connected and 1 if connected to the charger
	 */
	private double chargerConnected = 0;

	/**
	 * amount of charger settings
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;

	/**
	 * constant used in calculating the charge rate of the external charger
	 */
	public static final double CHARGE_RATE = 2.0;

	/**
	 * default power consumption of the camera at the start of the simulation
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;

	// constructor

	/**
	 * constructs a new camera battery starting disconnected from the camera and the
	 * external charger. variables for disconnection are already initialized to
	 * their proper states, as well as the charger setting
	 * 
	 * @param batteryStartingCharge
	 * @param givenBatteryCapacity
	 */
	public CameraBattery(double batteryStartingCharge, double givenBatteryCapacity) {
		batteryCapacity = givenBatteryCapacity;
		// takes either the given starting charge, or the capacity if user goes over
		currentBatteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
	}

	// methods

	/**
	 * indicates the user has pressed the setting button one time on the charger
	 * charge setting increments by one loops back to 0 when the setting goes past 3
	 */
	public void buttonPress() {
		// increases charger setting by one while looping it back to 0 if it tries to go
		// above 3
		chargerSetting = (chargerSetting + 1) % 4;
	}

	/**
	 * charges the battery connected to the camera for a given amount of minutes
	 * amount of charging is number of minutes * the CHARGE_RATE constant. Charging
	 * cannot exceed the capacity of the battery connected to the camera. As well as
	 * no charging if the battery is not connected to said camera
	 * 
	 * @param minutes
	 * @return actual amount the battery has been charged
	 */
	public double cameraCharge(double minutes) {
		// calculate initial charge for return statement
		double initialBatteryCharge = currentBatteryCharge;
		// calculate amount of charge
		double amountOfCharge = ((minutes * CHARGE_RATE) * batteryConnected);
		// update charge of camera and battery
		currentBatteryCharge = Math.min(batteryCapacity, currentBatteryCharge + amountOfCharge);
		currentCameraCharge = Math.min(batteryCapacity, currentCameraCharge + amountOfCharge);
		// update return statement based on actual amount charged
		amountOfCharge = currentBatteryCharge - initialBatteryCharge;
		return amountOfCharge;
	}

	/**
	 * drains the battery connected to the camera (has to be connected) for a given
	 * number of min drain is the number of minutes times the camera's power
	 * consumption. amount cannot drain more than the battery has.
	 * 
	 * @param minutes
	 * @return actual amount drained from the battery
	 */
	public double drain(double minutes) {
		// calculate initial charge for drain calculation
		double initialBatteryCharge = currentBatteryCharge;
		// calculate amount of drain to update totalDrain and the return statement if
		// the battery is connected
		double amountOfDrain = ((minutes * cameraPowerConsumption) * batteryConnected);
		// drain batteries
		currentBatteryCharge = Math.max(0, currentBatteryCharge - amountOfDrain);
		currentCameraCharge = Math.max(0, currentCameraCharge - amountOfDrain);
		// updating drain amounts based on resulting battery charge
		amountOfDrain = initialBatteryCharge - currentBatteryCharge;
		totalDrain += amountOfDrain;
		return amountOfDrain;
	}

	/**
	 * charges the battery connected to the charger for a given amount of minutes
	 * amount of charging is the number of minutes times the charger setting (number
	 * between 0 inclusive and NUM_CHARGER_SETTING exclusive) times the CHARGE_RATE
	 * constant cannot exceed the capacity of the battery connected to the camera
	 * 
	 * @param minutes
	 * @return amount the battery has been charged
	 */
	public double externalCharge(double minutes) {
		// calculate initial charge for return statement
		double initialBatteryCharge = currentBatteryCharge;
		// calculate amount of charge and then check if battery is connected to charger
		double amountOfCharge = ((minutes * CHARGE_RATE * chargerSetting) * chargerConnected);
		// update charge of battery
		currentBatteryCharge = Math.min(batteryCapacity, currentBatteryCharge + amountOfCharge);
		// update return statement based on actual amount charged
		amountOfCharge = currentBatteryCharge - initialBatteryCharge;
		return amountOfCharge;
	}

	/**
	 * reset the battery monitoring system by setting the total battery drain count
	 * back to 0
	 */
	public void resetBatteryMonitor() {
		totalDrain = 0;
	}

	/**
	 * gets the battery's capacity
	 * 
	 * @return the capacity of the battery
	 */
	public double getBatteryCapacity() {
		return batteryCapacity;
	}

	/**
	 * get the battery's current charge
	 * 
	 * @return charge of the battery
	 */
	public double getBatteryCharge() {
		return currentBatteryCharge;
	}

	/**
	 * get the current charge of the camera's battery
	 * 
	 * @return camera's battery charge
	 */
	public double getCameraCharge() {
		return currentCameraCharge;
	}

	/**
	 * get the power consumption of the camera
	 * 
	 * @return camera's consumption of power
	 */
	public double getCameraPowerConsumption() {
		return cameraPowerConsumption;
	}

	/**
	 * gets the external charger's setting
	 * 
	 * @return setting of the external charger
	 */
	public int getChargerSetting() {
		return chargerSetting;
	}

	/**
	 * gets the total amount of power drained from the battery since the battery
	 * monitor was last started or reset
	 * 
	 * @return total amount of power drained
	 */
	public double getTotalDrain() {
		return totalDrain;
	}

	/**
	 * move the battery to the external charger UPDATES VARIABLES TO REPRESENT MOVE
	 * FROM CAMERA TO CHARGER
	 */
	public void moveBatteryExternal() {
		// enables external charge to be on
		chargerConnected = 1;
		batteryConnected = 0;
	}

	/**
	 * move the battery to the camera UPDATES ANY VARIABLES TO REPRESENT MOVE FROM
	 * CHARGER TO CAMERA
	 */
	public void moveBatteryCamera() {
		// imitates putting battery in camera
		currentCameraCharge = currentBatteryCharge;
		// sets ability to charge and drain to be on
		batteryConnected = 1;
	}

	/**
	 * remove the battery from either the camera or the charger UPDATES ANY
	 * VARIABLES TO REPRESENT DISSCONNECTION FROM STUFF
	 */
	public void removeBattery() {
		// imitates removing battery from camera
		currentCameraCharge = 0;
		// sets ability to drain and charge from camera to be off
		batteryConnected = 0;
	}

	/**
	 * set the power consumption of the camera
	 * 
	 * @param givenCameraPowerConsumption
	 */
	public void setCameraPowerConsumption(double givenCameraPowerConsumption) {
		cameraPowerConsumption = givenCameraPowerConsumption;
	}
}