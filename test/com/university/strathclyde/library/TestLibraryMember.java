package com.university.strathclyde.library;

import org.junit.Assert;
import org.junit.Test;

/***
 * Test class to validate functionality of LibraryMember class.
 * @author Vijayshree Joshi
 */
public class TestLibraryMember {
    LibraryMember johnSmith = new LibraryMember(251033515, "John", "Smith",
            "Glasgow G40UH", "johnsmith@gmail.com", "passw0rd@");

    /***
     * Method to test Setter and Getter method of member Id.
     */
    @Test
    public void testSetAndGetMemberId() {
        final long expectedMemberID = 251033515;
        johnSmith.setMemberId(expectedMemberID);
        final long actualMemberId = johnSmith.getMemberId();
        Assert.assertEquals("Expected Member Id and Actual Member Id are not same", expectedMemberID, actualMemberId);
    }

    /***
     * Method to test Setter and Getter method of first name of library member.
     */
    @Test
    public void testSetAndGetFirstName() {
        final String expectedFirstName = "Jhon";
        johnSmith.setFirstName(expectedFirstName);
        final String actualFirstName = johnSmith.getFirstName();
        Assert.assertEquals("Expected and Actual first name are not same.", expectedFirstName, actualFirstName);
    }

    /***
     * Method to test Setter and Getter method of last name of library member.
     */
    @Test
    public void testSetAndGetLastName() {
        final String modifiedLastName = "Smith";
        johnSmith.setLastName(modifiedLastName);
        final String lastNameAfterModification = johnSmith.getLastName();
        Assert.assertEquals("Expected and Actual last name are not same.", modifiedLastName, lastNameAfterModification);
    }

    /***
     * Method to test Setter and Getter method of notification which sent to library member.
     */
    @Test
    public void testSetAndGetNotifications() {
        assert johnSmith.getNotification() == null;
        String notificationMessage = "Last 5 days left to return Head First Book.";
        johnSmith.setNotification(notificationMessage);
        assert johnSmith.getNotification().equals(notificationMessage);
        String nextNotificationMessage = "Library will be closed on 25th Dec.";
        johnSmith.setNotification(nextNotificationMessage);
        String expectedNotification = notificationMessage + "\n" + nextNotificationMessage;
        assert johnSmith.getNotification().equals(expectedNotification);
    }

    /***
     * Method to test Setter and Getter method of member password of library member.
     */
    @Test
    public void testSetAndGetMemberPassword() {
        final String expectedPassword = "jhon230@";
        johnSmith.setPassword(expectedPassword);
        final String actualPassword = johnSmith.getPassword();
        Assert.assertEquals("Expected and Actual first name are not same.", expectedPassword, actualPassword);
    }


    /***
     * Method to test Setter and Getter method of email Id of library member.
     */
    @Test
    public void testSetAndGetEmailId() {
        final String expectedEmailId = "Smithjhon18@outlook.com";
        johnSmith.setEmailId(expectedEmailId);
        final String actualEmailId = johnSmith.getEmailId();
        Assert.assertEquals("Expected and Actual email Id are not same.", expectedEmailId, actualEmailId);
    }

    /***
     * Method to test Setter and Getter method of address of library member.
     */
    @Test
    public void testAddress() {
        final String expectedAddress = "0/7 Springburn G59QQ";
        johnSmith.setAddress(expectedAddress);
        final String actualAddress = johnSmith.getAddress();
        Assert.assertEquals("Expected and Actual address are not same.", expectedAddress, actualAddress);
    }
}