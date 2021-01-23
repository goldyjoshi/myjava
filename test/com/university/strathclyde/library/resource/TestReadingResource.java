package com.university.strathclyde.library.resource;

import org.junit.Assert;
import org.junit.Test;

/***
 * This test for abstract class name ReadingResource will  create object of type reading resource points to PhysicalBook class.
 * @author Vijayshree Joshi
 */
public class TestReadingResource {
    private ReadingResource readingResource =  new PhysicalBook("978-3-16-148410-0", "Harry Potter",
            "J. K. Rowling","Harry Potter and The Philosopher's Stone" , "978-3-16-148410-0");

    /***
     * Method to test Setter and Getter method of author name.
     */
    @Test
    public void testSetAndGetAuthorName() {
        final String expectedAuthorName = "J. K. Rowling";
        final String actualAuthorName = readingResource.getAuthorName();
        Assert.assertEquals("Expected and Actual author name are not same!",expectedAuthorName,actualAuthorName);

        final String modifiedAuthorName = "Shakespeare";
        readingResource.setAuthorName(modifiedAuthorName);
        final String authorNameAfterModification = readingResource.getAuthorName();
        Assert.assertEquals("Error while modifying author name.", modifiedAuthorName, authorNameAfterModification);
    }

    /***
     * Method to test Setter and Getter method of title.
     */
    @Test
    public void testSetAndGetTitle() {
        final String expectedTitle = "Computer";
        readingResource.setTitle(expectedTitle);
        final String actualTitle = readingResource.getTitle();
        Assert.assertEquals("Expected title and Actual title are not same", expectedTitle,actualTitle);
    }
}
