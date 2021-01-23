package com.university.strathclyde.library.resource;


import org.junit.Assert;
import org.junit.Test;

/***
 * Test class to validate functionality of ElectronicDevice class.
 * @author Vijayshree Joshi
 */
public class TestElectronicDevice {

    ElectronicDevice electronicDevice = new ElectronicDevice("Tab-1001", "Samsung Tablet","Shelf 5, Row2",
            "Tab S7", "Samsung");

    /***
     * Method to test device is available to use or not.
     */
    @Test
    public void testIsDeviceAvailableToUse() {
        assert electronicDevice.isDeviceAvailableToUse() == true;
        electronicDevice.setDeviceLocation(null);
        assert electronicDevice.isDeviceAvailableToUse() == false;
    }

    /***
     * Method to test device is available in library.
     */
    @Test
    public void testIsAvailable() {
        assert electronicDevice.isAvailable() == false;
        electronicDevice.setAvailable(true);
        assert electronicDevice.isAvailable() == true;
    }

    /***
     * Method to test getter method of device location in library.
     */
    @Test
    public void testGetDeviceLocation() {
        final String expectedLocation = "101";
        electronicDevice.setDeviceLocation(expectedLocation);
        final String actualLocation = electronicDevice.getDeviceLocation();
        Assert.assertEquals("Actual and expected location are not same." , expectedLocation, actualLocation);
    }

    /***
     * Method to test setter method of device location.
     */
    @Test
    public void testSetDeviceLocation() {
        final String modifiedLocation = "102";
        electronicDevice.setDeviceLocation(modifiedLocation);
        final String locationAfterModification = electronicDevice.getDeviceLocation();
        Assert.assertEquals("Error while modifying location.",modifiedLocation, locationAfterModification);
    }

    /***
     * Method to test Getter method of model.
     */
    @Test
    public void testGetModel() {
        final String expectedModel = "M2-003";
        electronicDevice.setModel(expectedModel);
        final String actualModel = electronicDevice.getModel();
        Assert.assertEquals("Actual and expected location are not same.", expectedModel, actualModel);
    }

    /***
     * Method to test Setter method of model.
     */
    @Test
    public void testSetModel() {
        final String modifiedModel = "M2-003";
        electronicDevice.setModel("M2-003");
        final String modelAfterModify = electronicDevice.getModel();
        Assert.assertEquals("Error while modifying model.", modifiedModel, modelAfterModify);
    }

    /***
     * Method to test Getter method of brand.
     */
    @Test
    public void testGetBrand() {
        final String expectedBrand = "Asus";
        electronicDevice.setBrand(expectedBrand);
        final String actualBrand = electronicDevice.getBrand();
        Assert.assertEquals("Actual and expected brand are not same.",expectedBrand, actualBrand);
    }

    /***
     * Method to test Setter method of brand.
     */
    @Test
    public void testSetBrand() {
        final String modifiedBrand = "MacBook";
        electronicDevice.setBrand(modifiedBrand);
        final String brandAfterModify = electronicDevice.getBrand();
        Assert.assertEquals("Error while modifying brand.",modifiedBrand, brandAfterModify);
    }

    /***
     * Method to test Getter method of damage.
     */
    @Test
    public void testGetDamage() {
        // check no damage to new device when added in library.
        assert  electronicDevice.getDamage() == null;
        String expectedDamage = "Screen of Tab-1001 has been broken.";
        electronicDevice.setDamage(expectedDamage);
        final String actualDamage = electronicDevice.getDamage();
        Assert.assertEquals("Actual and expected damage are not same.", actualDamage, expectedDamage);
    }

    /***
     *  Method to test Setter method of damage.
     *  Test also validate that setter method concatenate previous value of damage variable.
     */
    @Test
    public void testSetAndGetDamage() {
        String damage = "Screen of Tab-1001 has been broken.";
        electronicDevice.setDamage(damage);
        // check damage recorded.
        assert electronicDevice.getDamage().equals(damage);

        // add another damage
        final String newDamageAppear = "Tab-1001 camera not working.";
        electronicDevice.setDamage(newDamageAppear);

        // check both damage recorded.
        final String expectedDamageAppear = damage + "\n" + newDamageAppear;
        assert electronicDevice.getDamage().equals(expectedDamageAppear);
    }
}
