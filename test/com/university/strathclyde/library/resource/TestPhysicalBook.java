package com.university.strathclyde.library.resource;

import com.university.strathclyde.library.LibraryMember;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/***
 * Test class to validate functionality of PhysicalBook class.
 * @author Vijayshree Joshi
 */
public class TestPhysicalBook {

    public static final String bookVersion = "v3";
    public static final double bookPrice = 25.86;
    public static final double bookAge = 5.2;

    private PhysicalBook harryPotter = new PhysicalBook("978-3-16-148410-0", "Harry Potter",
            "J. K. Rowling","Harry Potter and The Philosopher's Stone" , "978-3-16-148410-0");

    /***
     * Method to setup test environment before each test.
     */
    @Before
    public void testSetup() {
        harryPotter.setPrice(bookPrice);
        harryPotter.setAge(bookAge);
        harryPotter.setVersion(bookVersion);
    }

    /***
     * Method to test option available to read physical book.
     */
    @Test
    public void testOptionToRead() {
        String option1ToRead = "Issue a book.";
        String option2ToRead = "You can read book in library seating area.";

        // check both the options are available.
        harryPotter.optionToRead().contains(option1ToRead);
        harryPotter.optionToRead().contains(option2ToRead);
    }

    /***
     * Method to test book is available to be issue to a library member.
     */
    @Test
    public void testIsBookAvailable() {
        assert harryPotter.isAvailable() == true;
        LibraryMember johnSmith = new LibraryMember(251033515, "John", "Smith",
                "Glasgow G40UH", "johnsmith@gmail.com", "passw0rd@");
        harryPotter.setIssuedTo(johnSmith);
        assert harryPotter.isAvailable() == false;

    }

    /***
     * Method to test Setter and Getter method of ISBN.
     */
    @Test
    public void testSetAndGetIsbn() {
        final String expectedIsbn = "978-3-16-148410-0";
        final String actualIsbn = harryPotter.getIsbn();
        Assert.assertEquals("Actual and expected ISBN are not same.", expectedIsbn, actualIsbn);

        final String modifiedIsbn = "978-3-16-148410-12";
        harryPotter.setIsbn(modifiedIsbn);
        final String isbnAfterModification = harryPotter.getIsbn();
        Assert.assertEquals("Error while modifying Isbn.", modifiedIsbn, isbnAfterModification);
    }


    /***
     *  This test method test setter and getter method of damage.
     *  Test also validate that setter method concatenate previous value of damage variable.
     */
    @Test
    public void testSetAndGetDamage() {
        assert  harryPotter.getDamage()  == null;
        String damage = "Returned book has ripped pages from page number 20 to page number 24.";
        harryPotter.setDamage(damage);
        assert harryPotter.getDamage().equals(damage);
        final String newDamageAppear = "Returned book has some pages are missing.";
        harryPotter.setDamage(newDamageAppear);
        final String expectedDamageAppear = damage + "\n" + newDamageAppear;
        assert harryPotter.getDamage().equals(expectedDamageAppear);
    }

    /***
     * Method to test Setter and Getter method of Version.
     */
    @Test
    public void testSetAndGetVersion() {
        final String expectedVersion = bookVersion;
        final String actualVersion = harryPotter.getVersion();
        Assert.assertEquals("Expected and Actual version are not same!",expectedVersion, actualVersion);

        final String modifiedVersion = "v12";
        harryPotter.setVersion(modifiedVersion);
        final String versionAfterModification = harryPotter.getVersion();
        Assert.assertEquals("Error while modifying version!",modifiedVersion, versionAfterModification);
    }

    /***
     * Method to test Setter and Getter method of Price.
     */
    @Test
    public void testSetAndGetPrice() {
        final Double expectedPrice = bookPrice;
        final Double actualPrice = harryPotter.getPrice();
        Assert.assertEquals("Expected and Actual price are not same!",expectedPrice, actualPrice);

        final Double modifiedPrice = 50.8;
        harryPotter.setPrice(modifiedPrice);
        final Double priceAfterModification = harryPotter.getPrice();
        Assert.assertEquals("Error while modifying price!",modifiedPrice, priceAfterModification);
    }

}
