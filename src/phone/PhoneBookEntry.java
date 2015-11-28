package phone;

public class PhoneBookEntry {
	
	private String name;
	private String phoneNumber;
	private int id;
	private static int idCounter = 0;
	
	public PhoneBookEntry(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.id = ++idCounter;
	}
	
	public int getId() {
		return id;
	}	
	
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s - %s", id, phoneNumber, name);		
	}	
	
	
}
