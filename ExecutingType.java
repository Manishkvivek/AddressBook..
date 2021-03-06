package com.cg;

import java.util.*;

public class ExecutingType {
	
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	
	Scanner sc = new Scanner(System.in);
	static String firstName, lastName, address, city, state, zipcode, phone, email;
	static String firstName1, lastName1, address1, city1, state1, zipcode1, phone1, email1;
	public void addDetails() {
		System.out.println("Enter first name: ");
		firstName = sc.nextLine();
		System.out.println("Enter last name: ");
		lastName = sc.nextLine();
		System.out.println("Enter address: ");
		address = sc.nextLine();
		System.out.println("Enter city: ");
		city = sc.nextLine();
		System.out.println("Enter state: ");
		state = sc.nextLine();
		System.out.println("Enter zip: ");
		zipcode = sc.nextLine();
		System.out.println("Enter phone number: ");
		phone = sc.nextLine();
		System.out.println("Enter email: ");
		email = sc.nextLine();
	}
	public void editDetails() {
		System.out.println("Enter first name: ");
		firstName1 = sc.nextLine();
		System.out.println("Enter last name: ");
		lastName1 = sc.nextLine();
		System.out.println("Enter address: ");
		address1 = sc.nextLine();
		System.out.println("Enter city: ");
		city1 = sc.nextLine();
		System.out.println("Enter state: ");
		state1 = sc.nextLine();
		System.out.println("Enter zip: ");
		zipcode1 = sc.nextLine();
		System.out.println("Enter phone number: ");
		phone1 = sc.nextLine();
		System.out.println("Enter email: ");
		email1 = sc.nextLine();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		AddressBook addBook = new AddressBook();
		List<AddressBook> book = new ArrayList<AddressBook>();
		Executor entDet = new Executor();
		int a = 5;
		int count = 0;
		int check = 9;
		int f = 0;
		
		System.out.println("Welome to Address Book!!!");
		
		do {
			System.out.println("Press 8 to search for Contacts by City");
			System.out.println("Press 9 to search for Contacts by State");
			System.out.println("Press 10 to add Address Book");
			System.out.println("Press 11 to exit");
			check = in.nextInt();
			
			if(check == 10) {
				book.add(addBook);
				do {
					
					System.out.println("Menu \nPress 1 to Add a contact \nPress 2 to Edit a Contact \nPress 3 to Remove a contact \nPress 4 to View all contact \nPress 5 to Read from File \nPress 6 to Write to File \nPress 7 to exit");
					a=in.nextInt();
					switch(a) {
					case 1:
						entDet.addDetails();
						count++;
						ContactDetailsRegex validateDetails = new ContactDetailsRegex();
						if(validateDetails.firstNameValidation.validate(firstName) && validateDetails.lastNameValidation.validate(lastName) && validateDetails.addressValidation.validate(address) && validateDetails.cityValidation.validate(city) && validateDetails.stateValidation.validate(state) && validateDetails.zipcodeValidation.validate(zipcode) && validateDetails.phoneValidation.validate(phone) && validateDetails.emailValidation.validate(email)) {
							ContactDetails contact = new ContactDetails(firstName, lastName, address, city, state, zipcode, phone, email);
							if(book.stream().anyMatch(n -> n.getAddressBook().contains(firstName))) {
								System.out.println("Duplicate entries found. Duplicate entries not allowed.");
								f = 1;
							}
							if(f == 0) {
								addBook.addContact(contact);
							}
						}
						else{
							System.out.println("Enetered inputs are not validated. Please enter valid inputs.");
						}
						break;
					
					case 2:
						entDet.editDetails();
						ContactDetailsRegex validateDetails1 = new ContactDetailsRegex();
						if(validateDetails1.firstNameValidation.validate(firstName1) && validateDetails1.lastNameValidation.validate(lastName1) && validateDetails1.addressValidation.validate(address1) && validateDetails1.cityValidation.validate(city1) && validateDetails1.stateValidation.validate(state1) && validateDetails1.zipcodeValidation.validate(zipcode1) && validateDetails1.phoneValidation.validate(phone1) && validateDetails1.emailValidation.validate(email1)) {
							ContactDetails contact = new ContactDetails(firstName1, lastName1, address1, city1, state1, zipcode1, phone1, email1);
							addBook.updateContact(contact);
						}
						else{
							System.out.println("Enetered inputs are not validated. Please enter valid inputs.");
						}
						break;
					case 3:
						System.out.println("Enter first name of contact to remove: ");
						String fname= in.nextLine();
						boolean b = addBook.removeContact(fname);
						if(b)
							System.out.println("Contact removed");
						else
							System.out.println("Contact not found");
						break;
					
					case 4:
						List<ContactDetails> contact1 = new ArrayList<ContactDetails>();
						contact1=addBook.showContact();
						System.out.println("Press 10 to view contacts sorted by First Name");
						System.out.println("Press 11 to view contacts sorted by City");
						System.out.println("Press 12 to view contacts sorted by State");
						System.out.println("Press 13 to view contacts sorted by Zipcode");
						int s = in.nextInt();
						if(s == 10) {
							System.out.println(addBook.showContact());
						}
						else if(s == 11) {
							System.out.println(addBook.showSortedContactsBasedOnCity());
						}
						else if(s ==12) {
							System.out.println(addBook.showSortedContactsBasedOnState());
						}
						else if(s == 13) {
							System.out.println(addBook.showSortedContactsBasedOnZipcode());
						}
						
						break;
						
					case 5: 
						IOService ioService = null;
						if(ioService.equals(IOService.FILE_IO)) {
							new AddressBookFileIOService().readData();
						}
						
					case 6:
						IOService ioService1 = null;
						if(ioService1.equals(IOService.FILE_IO)) {
							new AddressBookFileIOService().writeData((List<ContactDetails>) addBook);
						}
						
					case 7: break;
					
					}
			
				}while(a!=7);
				
				
			}
			
			else if(check == 8) {
				System.out.println("Enter the city : ");
				String city2 = in.nextLine();
				System.out.println(addBook.searchContactByCity(city2));
				System.out.println("Count of contacts based on city is : " + addBook.countOfContactByCity(city2));
			}
			
			else if(check == 9) {
				System.out.println("Enter the state : ");
				String state2 = in.nextLine();
				System.out.println(addBook.searchContactByState(state2));
				System.out.println("Count of contacts based on state is : " + addBook.countOfContactByState(state2));
			}
			
		}while(check!=11);
		
		
	}

}
