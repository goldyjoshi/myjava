package com.university.strathclyde.library;

import com.university.strathclyde.library.resource.PhysicalBook;


import java.util.HashSet;
import java.util.Set;

/***
 * Class represent a LibraryMember and information belongs to Library Member.
 * LibraryMember implement ILibraryMemberFunction interface to provide functionality of library member.
 * @author Vijayshree Joshi
 */
public class LibraryMember implements ILibraryMemberFunction {

    private long memberId;
    private String firstName;
    private String lastName;
    private String address;
    private String emailId;
    private String password;
    private String notification;
    private Set<PhysicalBook> collectionOfBookBorrowed = new HashSet<>();

    /***
     * Constructor to create a Object of LibraryMember and initialized it's member variables.
     * @param memberId unique id to identify a library member
     * @param firstName of library member
     * @param lastName of library member
     * @param address of library member
     * @param emailId of library member
     */
    public LibraryMember(final long memberId, final String firstName, final String lastName, final String address,
                         final String emailId, final String password) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
    }

    /***
     * Method to borrow a new book.
     * @param newBook to borrow.
     */
    public void borrowNewBook(PhysicalBook newBook) {
        if (collectionOfBookBorrowed.contains(newBook)) {
            Utility.printMessage("This book is already issued to you.");
        } else {
            collectionOfBookBorrowed.add(newBook);
        }
    }

    /***
     * Method to print details of all books borrowed by library member.
     */
    public void printDetailsOfBookBorrowedByLibraryMember() {
        Utility.printMessage("Following book has been borrowed by library member: " + "\n");
        for (PhysicalBook book : collectionOfBookBorrowed) {
            Utility.printMessage("---------------------" + "\n");
            Utility.printMessage("Book Details: " + book + "\n");
        }
    }

    /***
     * Method to return number of book borrowed by library member.
     * @return number of books borrowed.
     */
    public Integer numberOfBooksBorrowedByLibraryMember() {
        Integer numberOfBooks = collectionOfBookBorrowed.size();
        return numberOfBooks;
    }

    /***
     * Method to print details of library member.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }

    /***
     * Method to print notification sent to library member.
     */
    public void printNotificationMessage() {
        Utility.printMessage("Following notification messages has been sent to library member :" + "\n" + notification);
    }

    /***
     * Function to store all notification sent to library member.
     * Function append current notification in new line so that it can be represent in more readable way to user.
     * Refer printNotificationMessage() method.
     * @param notification current message to be append in notification.
     */
    public void setNotification(String notification) {
        if (this.notification == null) {
            this.notification = notification;
        } else {
            this.notification = this.notification.concat("\n").concat(notification);
        }
    }

    /***
     * Method to get notification.
     * @return String value of notification.
     */
    public String getNotification() {
        return notification;
    }

    /***
     * Method to get member id of library member
     * @return memberId
     */
    public long getMemberId() {
        return memberId;
    }

    /***
     * Method to set member id of library member.
     * @param memberId to set
     */
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    /***
     * Method to get member password of library member
     * @return String value of memberPassword.
     */
    public String getPassword() {
        return password;
    }

    /***
     * Method to set member password of library member.
     * @param password to set password of member in library.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * Method to get first name of library member.
     * @return firstName of library member.
     */
    public String getFirstName() {
        return firstName;
    }


    /***
     * This method is used to set first name of library member.
     * @param firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /***
     *  This method returns the value of last name of library member.
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /***
     * This method is used to set last name of library member.
     * @param lastName  to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /***
     * Method to get address of library member.
     * @return address of library member.
     */
    public String getAddress() {
        return address;
    }

    /***
     * Method to set address of library member.
     * @param address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /***
     * Method used to get emailId of library member.
     * @return value of emailId.
     */
    public String getEmailId() {
        return emailId;
    }

    /***
     * Method to set email Id of library member.
     * @param emailId to set.
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /***
     * Method to get books borrowed by a library member.
     * @return set of PhysicalBook borrowed by library member.
     */
    public Set<PhysicalBook> getCollectionOfBookBorrowed() {
        return collectionOfBookBorrowed;
    }

    /***
     * Method to set collection of borrowed book by library member.
     * @param collectionOfBookBorrowed to set.
     */
    public void setCollectionOfBookBorrowed(Set<PhysicalBook> collectionOfBookBorrowed) {
        this.collectionOfBookBorrowed = collectionOfBookBorrowed;
    }

    /***
     * String representation of library member with description of each field.
     * @return  String representation of LibraryMember.
     */
    @Override
    public String toString() {
        return " LibraryMember { " + '\n' +
                " MemberId = " + memberId +
                " FirstName = " + firstName + '\n' +
                " LastName = " + lastName + '\n' +
                " Address = " + address + '\n' +
                " EmailId = " + emailId + '\n' +
                " Notification = " + notification + '\n' +
                " CollectionOfBookBorrowed = " + '\n' +
                '}';
    }

}
