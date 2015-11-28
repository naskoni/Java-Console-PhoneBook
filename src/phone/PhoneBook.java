package phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PhoneBook {	
	
	private Scanner input;
	private List<PhoneBookEntry> phoneDirectory;
	private Set<String> usedNamesAndNumbers;
	
	public PhoneBook() {
		input = new Scanner(System.in);
		phoneDirectory = new ArrayList<>();
		usedNamesAndNumbers = new HashSet<>();
	}

	public void manipulate() {				
		while (true) {			
			System.out.print("Please select action (N, R, L, Q): ");
			String command = input.nextLine().toUpperCase();
			
			switch (command) {
			case "N":
				createNewRecord();
				break;
			case "R":
				if (phoneDirectory.isEmpty()) {
					System.out.println("Phonebook is empty!");
					printSeparatorLine();
					break;
				}
				System.out.print("Record ID: ");
				int idToRemove = Integer.parseInt(input.nextLine());
				removeRecord(idToRemove);
				break;
			case "L":
			case "L:NAME":
			case "L:NAME!":
			case "L:PHONE":
			case "L:PHONE!":
				printRecords(command);
				break;
			case "Q":
				System.out.println("Bye!");
				return;
			default:
				System.out.println("Invalid command, try again.");
				printSeparatorLine();
				break;
			}
		}		
	}	

	private void printRecords(String command) {
		System.out.printf("Records (%d):%n", phoneDirectory.size());
					
		sortByCommand(command);			
		
		for (PhoneBookEntry phoneBookEntry : phoneDirectory) {
			System.out.println("  " + phoneBookEntry);
		}	
		
		printSeparatorLine(); 
	}	

	private void sortByCommand(String command) {
		switch (command) {
		case "L":
			Collections.sort(phoneDirectory, new Comparator<PhoneBookEntry>(){
				@Override
				public int compare(PhoneBookEntry o1, PhoneBookEntry o2) {
		            return Integer.compare(o2.getId(), o1.getId());
		        }
		    });				
			break;
		case "L:NAME":
			Collections.sort(phoneDirectory, new Comparator<PhoneBookEntry>(){
				@Override
				public int compare(PhoneBookEntry o1, PhoneBookEntry o2) {
		            return o1.getName().compareTo(o2.getName());
		        }
		    });
			break;
		case "L:NAME!":
			Collections.sort(phoneDirectory, new Comparator<PhoneBookEntry>(){
				@Override
				public int compare(PhoneBookEntry o1, PhoneBookEntry o2) {
		            return o2.getName().compareTo(o1.getName());
		        }
		    });
			break;
		case "L:PHONE":
			Collections.sort(phoneDirectory, new Comparator<PhoneBookEntry>(){
				@Override
				public int compare(PhoneBookEntry o1, PhoneBookEntry o2) {
		            return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
		        }
		    });
			break;
		case "L:PHONE!":
			Collections.sort(phoneDirectory, new Comparator<PhoneBookEntry>(){
				@Override
				public int compare(PhoneBookEntry o1, PhoneBookEntry o2) {
		            return o2.getPhoneNumber().compareTo(o1.getPhoneNumber());
		        }
		    });
			break;
		}		
	}

	private void removeRecord(int idToRemove) {		
		if (idToRemove < 1) {
			System.out.printf("Invalid input [%d], enter positive number!%n", idToRemove);
			printSeparatorLine();
			return;
		}
		
		for (int i = 0; i < phoneDirectory.size(); i++) {			
			if (phoneDirectory.get(i).getId() == idToRemove) {				
				usedNamesAndNumbers.remove(phoneDirectory.get(i).getName().toUpperCase());
				usedNamesAndNumbers.remove(phoneDirectory.get(i).getPhoneNumber());				
				phoneDirectory.remove(i);				
				
				System.out.printf("Record with ID %d has been removed!%n", idToRemove);
				printSeparatorLine();
				return;
			}						
		}
		
		System.out.printf("Òhere is no record with ID %d!%n", idToRemove);
		printSeparatorLine();
	}

	private void createNewRecord() {
		System.out.print("Name: ");
		String name = input.nextLine().trim();
		
		if (name.isEmpty()) {
			System.out.println("Error: The field \"name\" cannot be empty!");
			printSeparatorLine();
			return;
		}
		
		if (usedNamesAndNumbers.contains(name.toUpperCase())) {
			System.out.println("Error: A record with this name already exists!");
			printSeparatorLine();
			return;
		}
		
		System.out.print("Phone: ");
		String phoneNumber = input.nextLine().trim();
		
		if (phoneNumber.isEmpty()) {
			System.out.println("Error: The field \"phoneNumber\" cannot be empty!");
			printSeparatorLine();
			return;
		}
		
		if (usedNamesAndNumbers.contains(phoneNumber)) {
			System.out.println("Error: A record with this phone already exists!");
			printSeparatorLine();
			return;
		}
		
		PhoneBookEntry newRecord = new PhoneBookEntry(name, phoneNumber);		
		phoneDirectory.add(newRecord);
		usedNamesAndNumbers.add(name.toUpperCase());
		usedNamesAndNumbers.add(phoneNumber);
		System.out.printf("New record with ID %d has been created!%n", newRecord.getId());
		printSeparatorLine();
	}	

	private void printSeparatorLine() {
		System.out.println("--------------------");		
	}
}
