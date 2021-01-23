package com.university.strathclyde.library.resource;

import org.junit.Assert;
import org.junit.Test;

/***
 * Test class to validate functionality of ElectronicReadingResource class.
 * @author Vijayshree Joshi
 */
public class TestElectronicReadingResource {

    ElectronicReadingResource javaTutorialByKostas = new ElectronicReadingResource("book-1453","JavaTutorial"
            , "Kostas", "Inheritance in java", "10-3467-84754", "1KB", "pdf");

    ElectronicDevice samsungTablet = new ElectronicDevice("Tab-1001", "Samsung Tablet","shelf1",
            "Tab S7", "Samsung");

    /***
     * Method to test option available to read a ElectronicReadingResource.
     */
    @Test
    public void testOptionToRead() {
        // check no option available until a reading device added to electronic resource.
        assert javaTutorialByKostas.optionToRead().contains("No device is available to read");

        // add electronic device to read a electronic resource.
        javaTutorialByKostas.addReadingDevice(samsungTablet);

        // check added tablet is available to reading option.
        assert javaTutorialByKostas.optionToRead().contains(javaTutorialByKostas.getResourceName() +
                " is available on following device : " + javaTutorialByKostas.getAvailableDeviceToRead().toString());
    }

    /***
     * Method to test availability of a ElectronicReadingResource.
     */
    @Test
    public void testIsAvailable() {
        // check electronic reading resource is not available until a electronic device not added to resource.
        assert !javaTutorialByKostas.isAvailable();

        // add a electronic device to electronic reading resource.
        javaTutorialByKostas.addReadingDevice(samsungTablet);

        // check electronic reading resource available after adding electronic device to resource.
        assert javaTutorialByKostas.isAvailable();
    }

    /***
     * Method to test addition of electronic device to read a ElectronicReadingResource.
     */
    @Test
    public void testAddReadingDevice() {
        // check no reading device added.
        assert javaTutorialByKostas.getAvailableDeviceToRead().size() == 0;
        // check reading device added.
        javaTutorialByKostas.addReadingDevice(samsungTablet);
        assert javaTutorialByKostas.getAvailableDeviceToRead().size() == 1;

        // check same device can't be added again.
        javaTutorialByKostas.addReadingDevice(samsungTablet);
        assert javaTutorialByKostas.getAvailableDeviceToRead().size() == 1;
    }

    @Test
    public void testIdentificationNumber() {
        final String expectedIdentificationNumber = "12_23_34_34";
        javaTutorialByKostas.setIdentificationNumber(expectedIdentificationNumber);
        final String actualIdentificationNumber = javaTutorialByKostas.getIdentificationNumber();
        Assert.assertEquals("Actual and expected identification number are not same", expectedIdentificationNumber,actualIdentificationNumber);
    }

    @Test
    public void testTypeOfResource() {
        final String expectedResource = "Harry Potter";
        javaTutorialByKostas.setTypeOfEResource(expectedResource);
        final String actualResource = javaTutorialByKostas.getTypeOfEResource();
        Assert.assertEquals("Expected and actual resources after modify are not same" , expectedResource,actualResource);
    }

    @Test
    public void testSetAndGetSize() {
        final String expectedSize = "1KB";
        javaTutorialByKostas.setSize(expectedSize);
        final String actualSize = javaTutorialByKostas.getSize();
        Assert.assertEquals("Expected and actual size after modify are not same", expectedSize, actualSize);
    }
}
