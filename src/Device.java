/**
 * 
 */

import java.util.*;

/**
 * @author Sergey
 *
 */
public class Device {
	private String deviceName;
	private String deviceId;
	private List<Component> components = new ArrayList<Component>();
	
	public void printDevice() {
		System.out.println("****************************************************");
		System.out.println("Device name: " + deviceName);
		System.out.println("Device Id: " + deviceId);
		System.out.println("****************************************************");
		for (int i = 0; i < components.size(); i++) {
			Component component = components.get(i);
			System.out.println("------------");
			System.out.println("Component: " + (i + 1));
			System.out.println("	Id: " + component.getId());
			System.out.println("	Name: " + component.getName());
			System.out.println("	Origin: " + component.getOrigin());
			System.out.println("	Price: " + component.getPrice() + " " + component.getCurrency());
			System.out.print  ("	Type:");
			System.out.println("   peripheral: " + (component.getType().isPeripheral() ? "Yes" : "No"));
			System.out.println("		power cons"
					+ "umption: " + component.getType().getPowerConsumption() + " " + component.getType().getPowerConsumptionValueName());
			System.out.println("		cooler: " + (component.getType().isCooler() ? "Yes" : "No"));
			System.out.println("		components group: " + component.getType().getComponentsGroup());
			System.out.println("		ports: " + ((component.getType().getPorts() != null) ? component.getType().getPorts() : "No"));
			System.out.println("		critical: " + (component.isCritical() ? "Yes" : "No"));
			System.out.println();



		}
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public void setComponents(List components) {
		this.components = components;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public List<Component> getComponents() {
		return components;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	

}
