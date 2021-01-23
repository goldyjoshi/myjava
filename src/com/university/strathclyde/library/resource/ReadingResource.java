package com.university.strathclyde.library.resource;

import com.university.strathclyde.library.Utility;

/***
 * ReadingResource is a abstract class that represent common behaviour of all type of reading resource.
 * ReadingResource is parent class of PhysicalBook and ElectronicReadingResource and child of LibraryResource.
 * @author Vijayshree Joshi
 */
public abstract class ReadingResource extends LibraryResource {

    protected String authorName;   // variable to store name of author of reading resource.
    protected String title;    // variable to store title of reading resource.

    /***
     * Constructor to initialized ReadingResource data fields and its parent LibraryResource class. Resource Id, resourceName are pass from
     * parent class LibraryResource and authorName, title variable is local variable.
     * @param resourceId Unique value of resources present in library.
     * @param resourceName Name of resources present in library.
     * @param authorName  Name of author of reading resources present in library.
     * @param title Title of reading resources present in library.
     */
    public ReadingResource(final String resourceId, final String resourceName, final String authorName, final String title) {
        super(resourceId, resourceName);
        this.authorName = authorName;
        this.title = title;
    }

    /***
     * This abstract method and options of reading are different for child class , can see in ElectronicReadingResource class
     * and PhysicalBook class.
     */
    public abstract String optionToRead();

    /***
     * Method to get name of author of reading resource.
     * @return String value of authorName.
     */
    public String getAuthorName() {
        return authorName;
    }

    /***
     * Method to add author name of reading resource.
     * @param authorName name of author of reading resource.
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /***
     * Getter method will give title of reading resource.
     * @return String value of title.
     */
    public String getTitle() {
        return title;
    }

    /***
     * Method to add title of reading resource.
     * @param title of reading resource.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     * This toString method returns the value given to member variable of class ReadingResource in string format.
     * @return String representation of variables
     */
    @Override
    public String toString() {
        return " ReadingResource { " + '\n' +
                " AuthorName = " + authorName + '\n' +
                " Title = " + title + '\n' +
                '}';
    }

    /***
     * This printDetails method will print value of member variable by calling method toString.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }
}
