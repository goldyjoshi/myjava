package com.university.strathclyde.library.resource;


import com.university.strathclyde.library.Utility;

/***
 * LibraryResource is a abstract class and it is a parent class for any type of library resource
 * either it's an reading resource or a device in library.
 * @author Vijayshree Joshi
 */
public abstract class LibraryResource {

    protected String resourceId;    // A unique value that represent a resource uniquely.
    protected String resourceName;  // Represent the name of resource which available in library.
    protected Double age = 0.0;     // Variable is used to identify how old resource(book, computer etc) is in library.

    /***
     * Constructor to initialized LibraryResource data fields. Resource Id, name and title are mandatory fields.
     * @param resourceId represent unique value of resource of library.
     * @param resourceName represent value of name of resource of library.
     */
    public LibraryResource(final String resourceId, final String resourceName) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }

    /***
     * Abstract method to check an resource is available or not.
     * Different type of resource can have different behaviour.
     * @return true if resource is available.
     */
    public abstract boolean isAvailable();

    /***
     * Function return unique value of resource in Library.
     * @return Value of resourceId.
     */
    public String getResourceId() {
        return resourceId;
    }

    /***
     *  Function to set value of resourceId
     * @param resourceId to identify resource uniquely in library
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /***
     * This method is used to get name of resource present in library.
     * @return String value of resourceName.
     */
    public String getResourceName() {
        return resourceName;
    }

    /***
     *  This method is used to set name of resource in library.
     * @param resourceName to set new resource.
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /***
     * This getter method will return the value of age means how old resource is in library?
     * @return Double value of age(age of resource).
     */
    public Double getAge() {
        return age;
    }

    /***
     * This setter method is used to put value of age.
     * @param age put age of resource present in library.
     */
    public void setAge(Double age) {
        this.age = age;
    }

    /***
     * This toString method returns the value given to member variable of class LibraryResource in string format with descriptive text.
     * @return String representation of variables.
     */
    @Override
    public String toString() {
        return " LibraryResource { " + '\n' +
                " ResourceId = " + resourceId + '\n' +
                " ResourceName = " + resourceName + '\n' +
                " Age = " + age + '\n' +
                '}';
    }

    /***
     * This printDetails method will print value of member variable by calling method toString.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }
}
