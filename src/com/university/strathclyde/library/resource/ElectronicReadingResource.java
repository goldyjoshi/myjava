package com.university.strathclyde.library.resource;

import com.university.strathclyde.library.Utility;

import java.util.ArrayList;
import java.util.List;

/***
 * Class represent an ElectronicReadingResource like e-book, journal etc.
 * it's a child class of ReadingResource and LibraryResource.
 * @author Vijayshree Joshi
 */
public class ElectronicReadingResource extends ReadingResource {

    private String identificationNumber;  // to store unique identification of electronic resource.
                                          // for e-book it will be 10 to 13 character long ISBN, for Journal etc it will be 11 character ISSN.
    private String size;  // to store size of electronic Resource for example 1KB etc.
    private String typeOfEResource;  // To store type of electronic resource like e-book, Journal etc.
    private List<ElectronicDevice> availableDeviceToRead = new ArrayList<>();  // List to store available device to read E-Resources.

    /***
     * Constructor to initialized ElectronicReadingResource data fields and its parent ReadingResource class. Resource Id, resourceName,authorName and title are pass from
     * parent class ReadingResource.
     * identificationNumber, size, typeOfEResource are local variable.
     * @param resourceId  Unique identification of library reading resource.
     * @param resourceName  Initialize the name of   electronic resource.
     * @param authorName  Name of author  of resource.
     * @param title  Title of resource.
     * @param identificationNumber  identification number like ISbn for E-book and ISSN for Journal etc.
     * @param size  size of electronic resource in KB MB,or GB
     * @param typeOfEResource  this will define which type resource is available.
     */
    public ElectronicReadingResource(final String resourceId, final String resourceName, final String authorName, final String title,
                                     final String identificationNumber, final String size, final String typeOfEResource) {
        super(resourceId, resourceName, authorName, title);
        this.identificationNumber = identificationNumber;
        this.size = size;
        this.typeOfEResource = typeOfEResource;
    }

    /***
     * Method provide reading option available for electronic reading resource.
     * @return String value for device option to read resource.
     */
    @Override
    public String optionToRead() {
        String readingOption = "No device is available to read" + resourceName;
        if (isAvailable()) {
            readingOption = resourceName + " is available on following device : " + availableDeviceToRead.toString();
        }
        return readingOption;
    }

    /***
     * Method return false in case no device is available to read resource, otherwise return true.
     * @return Availability of resource
     */
    @Override
    public boolean isAvailable() {
        boolean isResourceAvailableToRead = false;
        if (availableDeviceToRead.size() != 0) {
            isResourceAvailableToRead = true;
        }
        return isResourceAvailableToRead;
    }

    /***
     * Method add device into list of available device to read a resource.
     * In case device already available, a suitable message displayed to user.
     * @param electronicDevice device to add in the list of available device.
     */
    public void addReadingDevice(ElectronicDevice electronicDevice) {
        if (availableDeviceToRead.contains(electronicDevice)) {
            String userMessage = resourceName + " is already available on " + electronicDevice;
            Utility.printMessage(userMessage);
        } else {
            availableDeviceToRead.add(electronicDevice);
        }
    }

    /***
     * This printDetails method will print value of member variable by calling method toString.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }

    /***
     * This toString method returns the value given to member variable of class ElectronicReadingResource in string format with the proper
     * information whether device is available to read or not.
     * @return String representation of variables.
     */
    @Override
    public String toString() {
        String availableDeviceForReading = "";
        if (!isAvailable()) {
            for (ElectronicDevice electronicDevice : availableDeviceToRead) {
                availableDeviceForReading = availableDeviceForReading.concat(electronicDevice.getResourceId());
            }
        } else {
            availableDeviceForReading = "No available device found to read " + title;
        }
        return "ElectronicReadingResource { " + '\n' +
                " IdentificationNumber = " + identificationNumber + '\n' +
                " Size(KB/MB) = " + size + '\n' +
                " TypeOfEResource = " + typeOfEResource + '\n' +
                " AvailableDeviceToRead = " + availableDeviceForReading + '\n' +
                " AuthorName = " + authorName + '\n' +
                " Title = " + title + '\n' +
                " ResourceId = " + resourceId + '\n' +
                " ResourceName = " + resourceName + '\n' +
                " Age = " + age + '\n' +
                '}';
    }

    /***
     * Method will return availability of electronic device to read electronic reading resource.
     * @return available device to read a electronic reading resource.
     */
    public List<ElectronicDevice> getAvailableDeviceToRead() {
        return availableDeviceToRead;
    }

    /***
     * This method will give size of resources(like pdf) in KB, MB .
     * @return String value of size
     */
    public String getSize() {
        return size;
    }

    /***
     * To set the size means how much memory will be stored by resource.
     * @param size to add size of electronic resources in KB, MB etc
     */
    public void setSize(String size) {
        this.size = size;
    }

    /***
     * Getter method to get type of E-resource
     * @return String value of typeOFResource.
     */
    public String getTypeOfEResource() {
        return typeOfEResource;
    }

    /***
     * Method to set type of resource.
     * @param typeOfEResource to add resource type like E-book , Journal etc.
     */
    public void setTypeOfEResource(String typeOfEResource) {
        this.typeOfEResource = typeOfEResource;
    }

    /***
     * Method will give unique identification number of  reading resource e available in library.
     * @return String identificationNumber.
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /***
     * Method to set  unique identification number for  reading resource.
     * @param identificationNumber to add identification number.
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

}
