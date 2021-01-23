package com.university.strathclyde.library.resource;

import org.junit.Assert;
import org.junit.Test;

/***
 *  This test for abstract class name LibraryResource will  create object of type library resource points to ElectronicDevice class
 *  @author Vijayshree Joshi
 */
public class TestLibraryResource {

    LibraryResource libraryResource = new ElectronicDevice("Lib-556", "Computer",
            "shelf002", "MacBookPro2020", "Apple");

    /***
     *  This test method test setter and getter method of Resource Id.
     */
    @Test
    public void testSetAndGetResourceID() {
        final String expectedResourceId = "Lib-556";
        libraryResource.setResourceId(expectedResourceId);
        final String actualResourceId = libraryResource.getResourceId();
        Assert.assertEquals("Expected resourceId and Actual resourceId are not same", expectedResourceId, actualResourceId);
    }

    /***
     *  This test method test setter and getter method of Resource Name.
     */
    @Test
    public void testSetAndGetResourceName() {
        final String expectedResourceName = "Computer";
        libraryResource.setResourceName(expectedResourceName);
        final String actualResourceName = libraryResource.getResourceName();
        Assert.assertEquals("Expected resource name and Actual resource name are not same", expectedResourceName,actualResourceName);
    }

    /***
     *  This test method test setter and getter method of age.
     */
    @Test
    public void testSetAndGetAge() {
        final Double expectedAge = 14.0;
        libraryResource.setAge(expectedAge);
        final Double actualAge = libraryResource.getAge();
        Assert.assertEquals("Expected age description and Actual age description are not same.", expectedAge, actualAge);
    }
}
