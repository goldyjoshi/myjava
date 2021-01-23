package com.university.strathclyde.library.resource;

import com.university.strathclyde.library.Utility;

/***
 * Class represent an ElectronicDevice like tablet, computer, laptop etc.
 * it's a child class of library resource.
 * @author Vijayshree Joshi
 */
public class ElectronicDevice extends LibraryResource {

    private boolean isAvailable;  // to check is electronic device is available or not.
    private String deviceLocation;  // to represent the location of device in library.
    private String model;    // to represent the model of electronic device of library.
    private String brand;   // to store the model of electronic device of library
    private String damage;   // To store all damage done to electronic device.

    /***
     * Constructor to initialized data field of ElectronicDevice class and its parent LibraryResource class. resourceId,
     * resourceName are field of LibraryResource.
     * @param resourceId to give unique identification  to electronic device of library.
     * @param resourceName name of resource of device in library.
     * @param deviceLocation it represent location of device in library.
     * @param model represent model of electronic device available in library.
     * @param brand represent brand of electronic device available in library.
     */
    public ElectronicDevice(final String resourceId, final String resourceName, final String deviceLocation, final String model, final String brand) {
        super(resourceId, resourceName);
        this.deviceLocation = deviceLocation;
        this.model = model;
        this.brand = brand;
    }

    /***
     * Function to check availability of device.
     * @return false in case device is damaged or not installed on it's location.
     */
    public boolean isDeviceAvailableToUse() {
        if (deviceLocation != null && !deviceLocation.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * This method confirms the device is available or not in the library.
     * @return boolean value of isAvailable field.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /***
     * By this setter method we set whether the device is available or not in library.
     * @param available boolean value to set for isAvailable field.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /***
     * This getter method  give device location of library.
     * @return String value of deviceLocation.
     */
    public String getDeviceLocation() {
        return deviceLocation;
    }

    /***
     *  This setter method add the location of device in library.
     * @param deviceLocation location of device in library.
     */
    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    /***
     * Getter method to get model of device.
     * @return String model of device.
     */
    public String getModel() {
        return model;
    }

    /***
     * With this setter method add model of device.
     * @param model model of device.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /***
     * Getter method to get brand of device.
     * @return String value of device brand.
     */
    public String getBrand() {
        return brand;
    }

    /***
     * Method to add brand of device.
     * @param brand brand of device.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /***
     * Function to record all damage done to electronic device.
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
     * Function return damage done to electronic device.
     * @return damage done to resource.
     */
    public String getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        String availableMessage = resourceName + " is not occupied by any library member.";
        if (!isAvailable) {
            availableMessage = resourceName + " is occupied by any library member.";
        }
        return "ElectronicDevice{" + '\n' +
                " IsAvailable = " + availableMessage + '\n' +
                " DeviceLocation = " + deviceLocation + '\n' +
                " Model = " + model + '\n' +
                " Brand = " + brand + '\n' +
                " ResourceId = " + resourceId + '\n' +
                " ResourceName = " + resourceName + '\n' +
                " Damage = " + damage + '\n' +
                " Age = " + age + '\n' +
                '}';
    }

    /***
     * Method will print details of all fields of class ElectronicDevice means information of device.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }

}
