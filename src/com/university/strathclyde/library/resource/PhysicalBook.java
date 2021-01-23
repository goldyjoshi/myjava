package com.university.strathclyde.library.resource;

import com.university.strathclyde.library.LibraryMember;
import com.university.strathclyde.library.Utility;

/***
 * Class represent a PhysicalBook and it's functionality.
 * it's a child class of ReadingResource and LibraryResource.
 * @author Vijayshree Joshi
 */
public class PhysicalBook extends ReadingResource {

    private String isbn;        // Variable to store unique isbn number of book.
    private String damage;      // To store all damage done to book.
    private double price;       // Variable to store price of book.
    private String version;     // Store the version of book like edition.
    private LibraryMember issuedTo; // Variable used to identify who is holding particular resource.

    /***
     * Constructor to initialized PhysicalBook data fields and its parent ReadingResource class. Resource Id, resourceName,authorName and title are pass from
     * parent class ReadingResource and isbn variable is local variable.
     * @param resourceId store Id for book as resource of library.
     * @param resourceName  store name of book.
     * @param authorName Store the books's author name.
     * @param title  variable to store the title of book.
     * @param isbn Unique number assign to  book when it is written.
     */
    public PhysicalBook(final String resourceId, final String resourceName, final String authorName, final String title,
                        final String isbn) {
        super(resourceId, resourceName, authorName, title);
        this.isbn = isbn;
    }

    /***
     * This method giving a message to user either he can borrow the book for home or he can read in library.
     * @return String message as option to read a book.
     */
    @Override
    public String optionToRead() {
        return "Option 1: Issue a book." + "\n" + " Option 2: You can read book in library seating area.";
    }

    /***
     * Method will return true if book is not issue to any library member means available in library otherwise false
     * @return boolean value of isAvailable.
     */
    public boolean isAvailable() {
        boolean isAvailable = true;
        if (issuedTo != null) {
            isAvailable = false;
        }
        return isAvailable;
    }

    /***
     *This toString method returns the value given to member variable of class PhysicalBook in string format with descriptive text.
     * @return String representation of variables.
     */
    @Override
    public String toString() {
        String issuedDetail = resourceName + " book is available.";
        if (issuedTo != null) {
            issuedDetail = issuedTo.toString();
        }
        return " PhysicalBook { " + '\n' +
                " Isbn = " + isbn + '\n' +
                " Price = " + price + '\n' +
                " Version = " + version + '\n' +
                " IssuedTo = " + issuedDetail + '\n' +
                " AuthorName = " + authorName + '\n' +
                " Title = " + title + '\n' +
                " ResourceId = " + resourceId + '\n' +
                " ResourceName = " + resourceName + '\n' +
                " Damage = " + damage + '\n' +
                " Age = " + age + '\n' +
                '}';
    }

    /***
     * This printDetails method will print value of member variable by calling method toString.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }

    /***
     * Method will give value of ISBN of book.
     * @return String value of 11 to 13 digit ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /***
     * This method used to add isbn of a particular book.
     * @param isbn unique  13 digit number of book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /***
     * Function to record all damage done to physical book.
     * Function append current damage to existing one in new line so that it can be represent in more readable way.
     * @param damage damage done to a library resource.
     */
    public void setDamage(String damage) {
        if (this.damage == null) {
            this.damage = damage;
        } else {
            this.damage = this.damage.concat("\n").concat(damage);
        }
    }

    /***
     * Function return damage done to physical book.
     * @return damage done to resource.
     */
    public String getDamage() {
        return damage;
    }

    /***
     * Method will give name of author of book.
     * @return String value of authorName.
     */
    public String getAuthorName() {
        return authorName;
    }

    /***
     * This method used to add name of author of a new book.
     * @param authorName to add new author name of new book for library.
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /***
     *  This method will give value of price of book.
     * @return Double value of price
     */
    public Double getPrice() {
        return price;
    }

    /***
     * This setter method will add price of new book of library.
     * @param price add a price of book as library resource
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /***
     * This method to get the specific version of book which is available in library.
     * @return String value of version of library book.
     */
    public String getVersion() {
        return version;
    }

    /***
     * Method will set  new version of  new book in library.
     * @param version add new version of book.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /***
     * This method will check which library member is currently holding book.
     * @return LibraryMember type value of issuedTo
     */
    public LibraryMember getIssuedTo() {
        return issuedTo;
    }

    /***
     * Method will set variable issuedTo if book will be issue to any library member.
     * @param issuedTo set value of issuedTo.
     */
    public void setIssuedTo(LibraryMember issuedTo) {
        this.issuedTo = issuedTo;
    }

}
