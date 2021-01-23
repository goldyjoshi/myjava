package com.university.strathclyde.library;

import com.university.strathclyde.library.resource.ElectronicDevice;
import com.university.strathclyde.library.resource.ElectronicReadingResource;
import com.university.strathclyde.library.resource.LibraryResource;
import com.university.strathclyde.library.resource.PhysicalBook;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Test class to validate functionality of Library class.
 * @author Vijayshree Joshi
 */
public class TestLibrary {

    private static final String harryPotterResourceId = "978-3-16-148410-0";
    private static final String journalByAntonioResourceId = "Journal-0001";
    private static final String javaTutorialByKostasResourceId = "book-1453";

    private Library theMitchellLibrary = new Library("The Mitchell Library",
                                                    "North St, Glasgow G3 7DN, United Kingdom");

    private LibraryMember johnSmith = new LibraryMember(251033515, "John", "Smith",
            "Glasgow G40UH", "johnsmith@gmail.com", "passw0rd@");

    private PhysicalBook harryPotter = new PhysicalBook(harryPotterResourceId, "Harry Potter",
            "J. K. Rowling","Harry Potter and The Philosopher's Stone" , "978-3-16-148410-0");

    private ElectronicReadingResource javaTutorialByKostas = new ElectronicReadingResource(javaTutorialByKostasResourceId,"JavaTutorial"
            , "Kostas", "Inheritance in java", "10-3467-84754", "1KB", "pdf");
    private ElectronicReadingResource journalByAntonio = new ElectronicReadingResource("Journal-0001","Leonardo da Vinci"
            , "Antonio V Sterpetti", "Man in Tribology", "1528-8897", "2KB", "Journal");

    ElectronicDevice electronicDevice = new ElectronicDevice("Tab-1001", "Samsung Tablet","Shelf 5, Row2",
            "Tab S7", "Samsung");

    /***
     * Method to test sign up process for library system.
     */
    @Test
    public void testSignUp() {
        // check no member in the library initially
        assert theMitchellLibrary.getLibraryMembers().size() == 0;
        theMitchellLibrary.signUp(johnSmith);

        // check member successfully signed up for library.
        assert theMitchellLibrary.getLibraryMembers().size() == 1;

        // check same member can't signup more than once.
        theMitchellLibrary.signUp(johnSmith);
        assert theMitchellLibrary.getLibraryMembers().size() == 1;
    }

    /***
     * Method to test a particular resource is available in library or not.
     */
    @Test
    public void testisResourceAvailable() {
        // check library resource not present as it is not added yey.
        assert theMitchellLibrary.isResourceAvailable(harryPotter) == false;

        // add harryPotter to library catalogue
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // check resource available in the library after addition.
        assert theMitchellLibrary.isResourceAvailable(harryPotter) == true;
    }

    /***
     * Method to test modification in title of reading resource.
     */
    @Test
    public void testModifyTitleOfResource() {
        // add a resource in library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // change title of above resource.
        String newTitle = "";
        theMitchellLibrary.modifyTitleOfResource(harryPotterResourceId, newTitle);

        // check title has been modified in the above resource.
        String title = ((PhysicalBook) theMitchellLibrary.getResourceByResourceId(harryPotterResourceId)).getTitle();
        assert title.equals(newTitle);
    }

    /***
     * Method to test retrieval of a resource from library catalogue.
     */
    @Test
    public void testGetResourceFromCatalogue() {
        // check resource that is not in catalogue.
        Assert.assertNull(theMitchellLibrary.getResourceFromCatalogue(harryPotter));

        // add resource in library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // check that resource is available once added in library catalogue.
        Assert.assertEquals(theMitchellLibrary.getResourceFromCatalogue(harryPotter), harryPotter);
    }

    /***
     * Method to test a resource is available or not using isbn.
     */
    @Test
    public void testSearchResourceByIsbn() {
        // add a book in library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // check a resource with different isbn that is not part of library.
        Assert.assertFalse(theMitchellLibrary.searchResourceByIsbn("232424-234-23"));

        // check a resource with a isbn that belongs to resource available in library.
        Assert.assertTrue(theMitchellLibrary.searchResourceByIsbn(harryPotterResourceId));
    }

    /***
     * Method to test retrieval of all resource using author name.
     */
    @Test
    public void testSearchResourceByAuthorName() {
        // add a reading resource in library
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(journalByAntonioResourceId, journalByAntonio);

        // check that resource not available with different Author than exist in library
        assert theMitchellLibrary.searchResourceByAuthorName("Shakespeare").size() == 0;

        // check a resource with author name that belongs to resource available in library.
        assert theMitchellLibrary.searchResourceByAuthorName("Antonio V Sterpetti").size() == 1;
    }

    /***
     * Method to test removal of a resource from library catalogue.
     */
    @Test
    public void testRemoveResourceFromCatalogue() {
        // add a resource in library
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(journalByAntonioResourceId, journalByAntonio);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // check the resource is added in library.
        assert theMitchellLibrary.getLibraryCatalogue().size() == 2;

        // remove resource from library by resource id
        theMitchellLibrary.removeResourceFromCatalogue(journalByAntonioResourceId);

        // check the resource removed from catalogue for specific resource id
        Assert.assertNull(theMitchellLibrary.getResourceByResourceId(journalByAntonioResourceId));

        // remove resource from library.
        theMitchellLibrary.removeResourceFromCatalogue(harryPotter);

        // check the resource removed from catalogue for specific resource id
        Assert.assertNull(theMitchellLibrary.getResourceFromCatalogue(harryPotter));

    }

    /***
     * Method to test all available book to issue in library.
     */
    @Test
    public void testPrintAvailableBooksToIssue() {
        // add a resource in library
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(journalByAntonioResourceId, journalByAntonio);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

        // check available PhysicalBook in the library.
        assert theMitchellLibrary.printAvailableBooksToIssue().size() == 1;

        // issue a book.
        theMitchellLibrary.issueBook(harryPotter, johnSmith);

        // check harryPotter is no more available in list of available books.
        Assert.assertFalse(theMitchellLibrary.printAvailableBooksToIssue().contains(harryPotter));
    }

    /***
     * Method to test number of resource available in library system.
     */
    @Test
    public void testPrintNumberOfResource() {
        // add a resource in library
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(journalByAntonioResourceId, journalByAntonio);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(javaTutorialByKostasResourceId, javaTutorialByKostas);

        // check number or resource in library is as same as number of added resource.
        assert theMitchellLibrary.printNumberOfResource() == 3;

        // check number of resource after removing a resource.
        theMitchellLibrary.removeResourceFromCatalogue(journalByAntonio);
        assert theMitchellLibrary.printNumberOfResource() == 2;
    }

    /***
     * Method to test retrieval of a resource using it's resource id from library catalogue.
     */
    @Test
    public void testGetResourceByResourceId() {
        /// add a resource in library
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(journalByAntonioResourceId, journalByAntonio);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(javaTutorialByKostasResourceId, javaTutorialByKostas);

        // check added and retrieved harryPotter book is same.
        Assert.assertEquals(theMitchellLibrary.getResourceByResourceId(harryPotterResourceId), harryPotter);
    }

    /***
     * Method to test process of issuing a book in library.
     */
    @Test
    public void testIssueBook() {
        // check if book is not in library
        theMitchellLibrary.issueBook(harryPotter, johnSmith);
        assert johnSmith.getCollectionOfBookBorrowed().size() == 0;
        Assert.assertNull(harryPotter.getIssuedTo());

        // create books.
        PhysicalBook harryPotter4thBook = new PhysicalBook(harryPotterResourceId, "Harry Potter",
                "J. K. Rowling","Harry Potter and the Goblet of Fire" , "978-3-16-148410-3");
        PhysicalBook harryPotter2ndBook = new PhysicalBook(harryPotterResourceId, "Harry Potter",
                "J. K. Rowling","Harry Potter and the Chamber of Secrets" , "978-3-16-148410-1");
        PhysicalBook harryPotter3rdBook = new PhysicalBook(harryPotterResourceId, "Harry Potter",
                "J. K. Rowling","Harry Potter and the Prisoner of Azkaban" , "978-3-16-148410-2");
        PhysicalBook harryPotter5thBook = new PhysicalBook(harryPotterResourceId, "Harry Potter",
                "J. K. Rowling","Harry Potter and the Order of the Phoenix" , "978-3-16-148410-4");
        PhysicalBook harryPotter6thBook = new PhysicalBook(harryPotterResourceId, "Harry Potter",
                "J. K. Rowling","HHarry Potter and the Half-Blood Prince" , "978-3-16-148410-5");

        // add more than 5 book to library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue("978-3-16-148410-1", harryPotter2ndBook);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue("978-3-16-148410-2", harryPotter3rdBook);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue("978-3-16-148410-3", harryPotter4thBook);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue("978-3-16-148410-4", harryPotter5thBook);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue("978-3-16-148410-5", harryPotter6thBook);

        // check book got issued once it's available in library.
        theMitchellLibrary.issueBook(harryPotter, johnSmith);
        assert johnSmith.getCollectionOfBookBorrowed().size() == 1;
        Assert.assertEquals(johnSmith, harryPotter.getIssuedTo());

        // issue 5 more book
        theMitchellLibrary.issueBook(harryPotter2ndBook, johnSmith);
        theMitchellLibrary.issueBook(harryPotter3rdBook, johnSmith);
        theMitchellLibrary.issueBook(harryPotter4thBook, johnSmith);
        theMitchellLibrary.issueBook(harryPotter5thBook, johnSmith);
        theMitchellLibrary.issueBook(harryPotter6thBook, johnSmith);

        //check no more than 5 book can be issue to a member.
        assert johnSmith.getCollectionOfBookBorrowed().size() == 5;
        Assert.assertEquals(johnSmith, harryPotter.getIssuedTo());
        Assert.assertEquals(johnSmith, harryPotter2ndBook.getIssuedTo());
        Assert.assertEquals(johnSmith, harryPotter3rdBook.getIssuedTo());
        Assert.assertEquals(johnSmith, harryPotter4thBook.getIssuedTo());
        Assert.assertEquals(johnSmith, harryPotter5thBook.getIssuedTo());
        Assert.assertNull(harryPotter6thBook.getIssuedTo());

    }

    /***
     * Method to test process of returning a book.
     */
    @Test
    public void testReturnABook() {
        // add a book harryPotter in library and issue to library member johnSmith
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.issueBook(harryPotter, johnSmith);

        //check book has been issued to johnSmith
        assert johnSmith.getCollectionOfBookBorrowed().contains(harryPotter);
        Assert.assertEquals(harryPotter.getIssuedTo(), johnSmith);

        // johnSmith return a book.
        String damageToRecord = "Pages is missing";
        theMitchellLibrary.returnABook(harryPotter,true, damageToRecord);

        // check harryPotter book is available to issue to another library member.
        Assert.assertNull(harryPotter.getIssuedTo());

        // check book harryPotter has been return successfully by johnSmith
        assert !johnSmith.getCollectionOfBookBorrowed().contains(harryPotter);

        // check damage is recorded after return.
        Assert.assertTrue(harryPotter.getDamage().contains(damageToRecord));
    }

    /***
     * Method will test printDetailsOfNotification method of Library class.
     */
    @Test
    public void testPrintDetailsOfNotification() {
        // add a book harryPotter in library and issue to library member johnSmith
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.issueBook(harryPotter, johnSmith);

        // send notification to library member holding PhysicalBook.
        String message = "Merry x-mas. Library will be closed from 24th Dec to 26th Dec.";
        theMitchellLibrary.printDetailsOfNotification(message);

        // check notification has been sent to library member holding PhysicalBook.
        Assert.assertTrue(johnSmith.getNotification().contains(message));
    }

    /***
     * Method to test retrieval of all PhysicalBook in library.
     */
    @Test
    public void testPrintDetailsOfPhysicalBook() {
        // add some PhysicalBook and other resource in library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(javaTutorialByKostasResourceId, javaTutorialByKostas);

        // check only 1 PhysicalBook exist in library
        assert theMitchellLibrary.printDetailsOfPhysicalBook().size() == 1;
    }

    /***
     * Method to test retrieval of all ElectronicReadingResource in the library.
     */
    @Test
    public void testPrintDetailsOfAllElectronicResources() {
        // add some ElectronicReadingResource and other resource in library.
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);
        theMitchellLibrary.addLibraryResourceInLibraryCatalogue(javaTutorialByKostasResourceId, javaTutorialByKostas);

        // check only 1 ElectronicReadingResource exist in library
        assert theMitchellLibrary.printDetailsOfAllElectronicResources().size() == 1;
    }

    /***
     * Method to test Setter and Getter method of libraryAddress of Library class.
     */
   @Test
    public void testSetAndGetLibraryAddress() {
        final String modifyingAddress = "0/7, Gorbals Library G40 3DB";
        // Checking address has set by setter method.
       theMitchellLibrary.setLibraryAddress(modifyingAddress);
       //check address has been modified.
        final String addressAfterModification = theMitchellLibrary.getLibraryAddress();
        Assert.assertEquals("Error while modification.", modifyingAddress, addressAfterModification);
   }

    /***
     * Method to test Setter and Getter method of libraryName of Library class.
     */
   @Test
   public void testSetAndGetLibraryName() {
       final String expectedLibraryName = "The Mitchell Library";
       final String actualLibraryName = theMitchellLibrary.getLibraryName();
       Assert.assertEquals("Actual and Expected library name are not same!", expectedLibraryName, actualLibraryName);

       final String modifyLibraryName = "Gorbals";
       theMitchellLibrary.setLibraryName(modifyLibraryName);
       final String libraryNameAfterModification = theMitchellLibrary.getLibraryName();
       Assert.assertEquals("Error while modification.", modifyLibraryName, libraryNameAfterModification);
   }

    /***
     * Method to test Setter and Getter method of libraryCatalogue.
     */
   @Test
    public void testSetAndGetLibraryCatalogue() {
       PhysicalBook harryPotter2ndBook = new PhysicalBook("978-3-16-148410-2", "Harry Potter",
               "J. K. Rowling","Harry Potter and the Chamber of Secrets" , "978-3-16-148410-2");
       theMitchellLibrary.addLibraryResourceInLibraryCatalogue(harryPotterResourceId, harryPotter);

       // check get catalogue return a Map with size 1.
       assert theMitchellLibrary.getLibraryCatalogue().size() == 1;

      // create new catalogue.
       Map<String, LibraryResource> newCatalogue = new HashMap<>();
       newCatalogue.put(harryPotterResourceId, harryPotter);
       newCatalogue.put("978-3-16-148410-2", harryPotter2ndBook);

       //check set new catalogue in library system.
       theMitchellLibrary.setLibraryCatalogue(newCatalogue);
       assert theMitchellLibrary.getLibraryCatalogue().size() == 2;
   }

    /***
     * Method to test Setter and Getter method of libraryMembers
     */
    @Test
    public void testSetAndGetLibraryMembers() {
        // check no library member in library.
        assert theMitchellLibrary.getLibraryMembers().size() == 0;

        // create new List of Library member.
        List<LibraryMember> newListOfLibraryMember = new ArrayList<>();
        newListOfLibraryMember.add(johnSmith);

        // check set method changed list of library system.
        theMitchellLibrary.setLibraryMembers(newListOfLibraryMember);
        assert theMitchellLibrary.getLibraryMembers().size() == 1;
    }

    /***
     * Method to test Setter and Getter method of electronic device of library.
     */
    @Test
    public void testSetAndGetElectronicDevice() {
        // check no device in library.
        assert theMitchellLibrary.getListOfElectronicDevices().size() == 0;

        // check new list of electronic device.
        List<ElectronicDevice> newListOfElectronicDevice = new ArrayList<>();
        newListOfElectronicDevice.add(electronicDevice);

        //check set method changed list of library system.
        theMitchellLibrary.setListOfElectronicDevices(newListOfElectronicDevice);
        assert theMitchellLibrary.getListOfElectronicDevices().size() == 1;
    }

}
