package com.university.strathclyde.library;

import com.university.strathclyde.library.database.DBConnection;
import com.university.strathclyde.library.database.IDatabaseFunction;
import com.university.strathclyde.library.resource.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/***
 *  Class to represent library and it's functionality.
 *  Library class implement ILibraryUiFunction interface used by UI.
 *  @author Vijayshree Joshi
 */
public class Library implements ILibraryUiFunction {

    private String libraryName;
    private String libraryAddress;
    private Map<String, LibraryResource> libraryCatalogue = new HashMap<>();   // Map to store library catalogue, unique resourceId is a key of map.
    private List<LibraryMember> libraryMembers = new ArrayList<>();    // A list to store all library member.
    private List<ElectronicDevice> listOfElectronicDevices = new ArrayList<>();   // A list to store all name of library device.
    private IDatabaseFunction dbFunction;

    /***
     * Constructor to create a Object of Library class and initialized it's member variables.
     * @param libraryName name of library.
     * @param libraryAddress address of library.
     */
    public Library(final String libraryName, final String libraryAddress) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        dbFunction = new DBConnection();
    }

    /***
     * Function to sign up for library.
     * A new member will be added to list of library members after validation.
     * @param newLibraryMember a new library member
     * @return sign up status message.
     */
    public String signUp(LibraryMember newLibraryMember) {
        String message = "Member already signup for " + libraryName;
        if (!libraryMembers.contains(newLibraryMember)) {
            libraryMembers.add(newLibraryMember);
            message = "Welcome to " + libraryName + ". Sign Up successfully completed. \n " +
                    "Please Login using your account.";
        }
        dbFunction.addLibraryMember(newLibraryMember);
        return message;
    }

    /***
     * Method to check valid library member for login.
     * @param loginEmail email id of library member.
     * @param password of library member.
     * @return true if a valid library member found.
     */
    public boolean login(String loginEmail, String password) throws SQLException {
//        for (LibraryMember member : libraryMembers) {
//            if (member.getEmailId().equals(loginEmail) && member.getPassword().equals(password)) {
//                Utility.printMessage("Valid library member found. Login success");
//                return true;
//            }
//        }
        ResultSet loginDetails = dbFunction.getAllLoginDetails();
        String dbUserName;
        String dbPassword;
        if (loginDetails != null) {
            while (loginDetails.next()) {
                dbUserName = loginDetails.getString(ProgramConstant.EMAILID_COLUMN);
                dbPassword = loginDetails.getString(ProgramConstant.USER_PASSWORD);
                if (dbUserName.equals(loginEmail) && dbPassword.equals(password)) {
                    Utility.printMessage("Login successful");
                    return true;
                }
            }
        }
        Utility.printMessage("Not a valid loginEmail/password.");
        return false;
    }

    /***
     * Function print details of library using toString method.
     */
    public void printDetails() {
        Utility.printMessage(this.toString());
    }

    /***
     * Function check requested resource is available in library catalogue or not.
     * @param resource to check availability in library.
     * @return true if resource available in library catalogue.
     */
    public boolean isResourceAvailable(LibraryResource resource) {
        if (libraryCatalogue.containsKey(resource.getResourceId())) {
            Utility.printMessage(resource.getResourceName() +" is available in the Library.");
            return true;
        } else {
            Utility.printMessage(resource.getResourceName() +" is not currently available in the Library.");
            return false;
        }
    }

    /***
     * Method to modify title of give resource.
     * @param resourceId to find resource in catalogue.
     * @param newTitle of the resource need to modify
     */
    public void modifyTitleOfResource(String resourceId, String newTitle) {
        // checking resource is present in catalogue before modification.
        if (libraryCatalogue.containsKey(resourceId)) {
            LibraryResource resourceToEdit = libraryCatalogue.get(resourceId);
            // only reading resource have title field
            if (resourceToEdit instanceof PhysicalBook || resourceToEdit instanceof ElectronicReadingResource) {
                ((ReadingResource) resourceToEdit).setTitle(newTitle);
            } else {
                // resource like electronic device don't have title field.
                Utility.printMessage("Only reading resource has title field.");
            }
        }
    }

    /***
     * Method to find a resource in library.
     * @param resourceToSearch in library.
     * @return true if resource find in library catalogue.
     */
    public LibraryResource getResourceFromCatalogue(LibraryResource resourceToSearch) {
        if (libraryCatalogue.containsValue(resourceToSearch)) {
            Utility.printMessage("Resource has been find successfully in catalogue.");
            return libraryCatalogue.get(resourceToSearch.getResourceId());
        } else {
            return null;
        }
    }

    /***
     * Method to search library catalogue with an isbn value.
     * @param isbn to search
     * @return true if a resource is find in catalogue with same isbn.
     */
    public boolean searchResourceByIsbn(String isbn) {
        boolean isFound = false;
        List<PhysicalBook> booksWithRequestedIsbn = new ArrayList<>();
        LibraryResource libraryResource;
        PhysicalBook physicalBook;
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            // Only PhysicalBook will be having isbn field.
            if (libraryResource instanceof PhysicalBook) {
                physicalBook = (PhysicalBook) libraryResource;
                if (isbn.equalsIgnoreCase(physicalBook.getIsbn())) {
                    booksWithRequestedIsbn.add(physicalBook);
                    isFound = true;
                }
            } else {
                Utility.printMessage("Only physical book can has ISBN.");
            }
        }
        // printing all the resource with same isbn
        for (PhysicalBook book : booksWithRequestedIsbn) {
            Utility.printMessage(book.toString());
        }
        Utility.printMessage(booksWithRequestedIsbn.size() +" Resource has been found with same ISBN.");
        return isFound;
    }

    /***
     * Method to find all resource with same author name.
     * @param authorName name of author to search in library catalogue.
     * @return list of resource written by an author.
     */
    public List<ReadingResource> searchResourceByAuthorName(String authorName) {
        List<ReadingResource> resourceListWithSameAuthor = new ArrayList<>();
        ReadingResource readingResource;
        LibraryResource libraryResource;
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            // only reading resource can have author name
            if (libraryResource instanceof PhysicalBook || libraryResource instanceof ElectronicReadingResource) {
                readingResource = (ReadingResource) libraryResource;
                if (authorName.equalsIgnoreCase(readingResource.getAuthorName())) {
                    resourceListWithSameAuthor.add(readingResource);
                }
            } else {
                Utility.printMessage("Electronic device don't have author");
            }
        }
        for (ReadingResource resourceWithSameAuthor : resourceListWithSameAuthor) {
            Utility.printMessage(resourceWithSameAuthor.toString());
        }
        Utility.printMessage(resourceListWithSameAuthor.size() + " resource has been found with author name: " + authorName);
        return resourceListWithSameAuthor;
    }

    /***
     * Method to remove a resource from catalogue.
     * @param libraryResourceToRemove to be removed
     */
    public void removeResourceFromCatalogue(final LibraryResource libraryResourceToRemove) {
        if (libraryCatalogue.containsValue(libraryResourceToRemove)) {
            libraryCatalogue.remove(libraryResourceToRemove.getResourceId());
            Utility.printMessage("Following resource has been successfully removed from library: " + libraryResourceToRemove);
        }
    }

    /***
     * Method remove resource from catalogue by resource id.
     * @param resourceID of resource needs to be removed.
     * @return corresponding message to user.
     */
    public String removeResourceFromCatalogue(final String resourceID) {
        String message = "";
        if (libraryCatalogue.containsKey(resourceID)) {
            libraryCatalogue.remove(resourceID);
            message = resourceID +" resource has been removed from library.";
        } else {
            message = "Resource with " + resourceID + " is not available in library catalogue.";
        }
        Utility.printMessage(message);
        return message;
    }

    /***
     * Method print details of all available PhysicalBook in Library.
     * @return list of available PhysicalBook in Library.
     */
    public List<PhysicalBook> printAvailableBooksToIssue() {
        LibraryResource libraryResource;
        List<PhysicalBook> availablePhysicalBook = new ArrayList<>();
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            if (libraryResource instanceof PhysicalBook) {
                if (((PhysicalBook) libraryResource).isAvailable()) {
                    Utility.printMessage(((PhysicalBook) libraryResource).toString());
                    availablePhysicalBook.add((PhysicalBook) libraryResource);
                }

            } else {
                Utility.printMessage("Only physical books are allowed to be issued");
            }
        }
        return availablePhysicalBook;
    }

    /***
     * Method print number of resource in library
     * @return number of resource
     */
    public int printNumberOfResource() {
        int numberOfResourceInLibrary = libraryCatalogue.size();
        Utility.printMessage("Number of resources in the library = " + numberOfResourceInLibrary);
        return numberOfResourceInLibrary;
    }

    /***
     * Method to add a resource in library catalogue.
     * @param resourceId as key for library catalogue hashmap
     * @param libraryResource resource as a value for library catalogue hashmap
     */
    public void addLibraryResourceInLibraryCatalogue(final String resourceId, final LibraryResource libraryResource) {
//        if (libraryCatalogue.containsKey(resourceId)) {
//            Utility.printMessage("resource alr4edy present in catalogue, can't add further");
//        } else {
//            libraryCatalogue.put(resourceId, libraryResource);
//            if (libraryResource instanceof PhysicalBook) {
//
//                dbFunction.addABook((PhysicalBook)libraryResource);
//            }
//            Utility.printMessage("Following resource is successfully added: " + "\n" + libraryResource);
//        }
        if (libraryResource instanceof PhysicalBook) {
            PhysicalBook bookToBeAdded = (PhysicalBook) libraryResource;
            ResultSet isbnInDb = dbFunction.checkBookAvailable(bookToBeAdded.getIsbn());
            boolean isBooKAvailable = false;

            try {
                while (isbnInDb.next()) {
                    if (isbnInDb.getString("isbn").equalsIgnoreCase(bookToBeAdded.getIsbn())) {
                        isBooKAvailable = true;
                        break;
                    }
                }
                if (isBooKAvailable) {
                    Utility.printMessage("resource already present in catalogue, can't add further");
                } else {
                    dbFunction.addABook(bookToBeAdded);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }

    /***
     * Method to get a resource from library catalogue by it's key
     * @param resourceId of resource
     * @return a resource
     */
    public LibraryResource getResourceByResourceId(String resourceId) {
        return libraryCatalogue.get(resourceId);
    }

    /***
     * Method issue a PhysicalBook to a library member.
     * Before issuing a book method check following:
     *      Book available in library.
     *      Book is not issued to any library member.
     *      Library member not have more than 5 book.
     * @param physicalBook to be issue.
     * @param libraryMember to issue a book.
     * @return corresponding message to user.
     */
    public String issueBook(PhysicalBook physicalBook, LibraryMember libraryMember) {
        String message = "";
        try {
            if (libraryCatalogue.keySet().contains(physicalBook.getResourceId())) {
                if (physicalBook.isAvailable()) {
                    if (libraryMember.getCollectionOfBookBorrowed().size() < 5) {
                        physicalBook.setIssuedTo(libraryMember);
                        libraryMember.borrowNewBook(physicalBook);
                        message = "Book has been issued successfully.";
                    } else {
                        message = libraryMember + " have access the limit of 5 books. Can not " +
                                "borrow more book.";
                    }
                } else {
                    message = "The" + physicalBook + " is not available in the library at the moment.";
                }
            } else {
                message = "The " + physicalBook + " is not present in library catalogue.";
            }
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
        Utility.printMessage(message);
        return message;
    }

    /***
     * Method to return a book.
     * On return book became available to issue and removed from the list of borrowed book of library member.
     * It also record a damage if recordDamage is true.
     * Following condition are validated while returning:
     *      Book is available in library catalogue.
     *      Book is issued to a library member.
     * @param physicalBook to return
     * @param recordDamage if true only then record damage
     * @param damageDescription to record.
     * @return corresponding message to user.
     */
    public String returnABook(PhysicalBook physicalBook, boolean recordDamage, String damageDescription) {
        String message = "";
        if (libraryCatalogue.keySet().contains(physicalBook.getResourceId())) {
            if (physicalBook.getIssuedTo() != null) {
                physicalBook.getIssuedTo().getCollectionOfBookBorrowed().remove(physicalBook);
                physicalBook.setIssuedTo(null);
                if (recordDamage && damageDescription != null) {
                    physicalBook.setDamage(damageDescription);
                }
                message = physicalBook.getTitle() + " has been returned.";
            } else {
                message = physicalBook.getTitle() + " is not issued to any library member.";
            }
        } else {
            message = physicalBook.getTitle()  + " is not belongs to library.";
        }
        Utility.printMessage(message);
        return message;
    }

    /***
     * Method to send notification to all library member holding a PhysicalBook.
     * Method
     * @param message to notify.
     */
    public void printDetailsOfNotification(String message) {
        LibraryResource libraryResource;
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            if (libraryResource instanceof PhysicalBook) {
                LibraryMember libraryMember = ((PhysicalBook) libraryResource).getIssuedTo();
                if (libraryMember != null) {
                    libraryMember.setNotification(message);
                }
            }
        }
        Utility.printMessage("Notification has been successfully sent to all library member holding physical book.");
    }

    /***
     * Method to print details of all PhysicalBook in library
     * @return list of all PhysicalBook in the library.
     */
    public List<PhysicalBook> printDetailsOfPhysicalBook() {
        List<PhysicalBook> physicalBookList = new ArrayList<>();
        LibraryResource libraryResource;
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            if (libraryResource instanceof PhysicalBook) {
                physicalBookList.add((PhysicalBook) libraryResource);
                Utility.printMessage("details of physical book " + ((PhysicalBook) (libraryResource)).toString());
            }
        }
        return physicalBookList;
    }

    /***
     * Method to print details of all ElectronicReadingResource in the library.
     * @return list of ElectronicReadingResource in the library.
     */
    public List<ElectronicReadingResource> printDetailsOfAllElectronicResources() {
        LibraryResource libraryResource;
        List<ElectronicReadingResource> electronicReadingResource = new ArrayList<>();
        for (String resourceId : libraryCatalogue.keySet()) {
            libraryResource = libraryCatalogue.get(resourceId);
            if (libraryResource instanceof ElectronicReadingResource) {
                electronicReadingResource.add((ElectronicReadingResource) libraryResource);
                Utility.printMessage("Details of electronic resource " + ((ElectronicReadingResource)(libraryResource)).toString());
            }
        }
        return electronicReadingResource;
    }

    /***
     * Method to add new device in library.
     * @param electronicDevice electronic device to add.
     * @return corresponding message to user.
     */
    public String addDeviceInLibrary(ElectronicDevice electronicDevice) {
        String message = "";
        if (listOfElectronicDevices.contains(electronicDevice)) {
            message = "Device is already present in library.";
        } else {
            listOfElectronicDevices.add(electronicDevice);
           message = "Device is successfully added in library";
        }
        Utility.printMessage(message);
        return message;
    }

    /***
     * Method to get list of electronic device of library.
     * @return List of electronic device.
     */
    public List<ElectronicDevice> getListOfElectronicDevices() {
        return listOfElectronicDevices;
    }

    /***
     * Method to set list of electronic devices.
     * @param listOfElectronicDevices  electronic device list.
     */

    public void setListOfElectronicDevices(List<ElectronicDevice> listOfElectronicDevices) {
        this.listOfElectronicDevices = listOfElectronicDevices;
    }

    /***
     * Method set library catalogue.
     * @param libraryCatalogue to set
     */
    public void setLibraryCatalogue(Map<String, LibraryResource> libraryCatalogue) {
        this.libraryCatalogue = libraryCatalogue;
    }

    /***
     * Method to get Library catalogue.
     * @return HashMap of library catalogue
     */
    public Map getLibraryCatalogue() {
        return libraryCatalogue;
    }

    /***
     * Method set list of LibraryMember in the library.
     * @param libraryMembers to set
     */
    public void setLibraryMembers(List<LibraryMember> libraryMembers) {
        this.libraryMembers = libraryMembers;
    }

    /***
     * Method to get list of library member.
     * @return libraryMembers
     */
    public List<LibraryMember> getLibraryMembers() {
        return libraryMembers;
    }

    /***
     * Method to return library name.
     * @return libraryName
     */
    public String getLibraryName() {
        return libraryName;
    }

    /***
     * Method to set name of library
     * @param libraryName to set
     */
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    /***
     * Method to get address of library.
     * @return libraryAddress
     */
    public String getLibraryAddress() {
        return libraryAddress;
    }

    /***
     * Method to set library address.
     * @param libraryAddress to set
     */
    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    /***
     * This toString method returns the value given to member variable of class Library in string format with descriptive text.
     * @return String representation of variables.
     */
    @Override
    public String toString() {
        String catalogueDetails = "Library Catalogue is empty.";
        String memberDetails = "No member added to library yet.";
        String electronicDeviceDetails = "No device found in Library.";
        if (libraryCatalogue.keySet().size() != 0) {
            catalogueDetails = libraryCatalogue.toString();
        }
        if (libraryMembers.size() != 0) {
            memberDetails = libraryMembers.toString();
        }
        if (listOfElectronicDevices.size() != 0) {
            electronicDeviceDetails = listOfElectronicDevices.toString();
        }

        return "Library{" + '\n' +
                "libraryName=" + libraryName + '\n' +
                "libraryAddress=" + libraryAddress + '\n' +
                "libraryCatalogue=" + catalogueDetails + '\n' +
                "libraryMembers=" + memberDetails + '\n' +
                "listOfElectronicDevices=" + electronicDeviceDetails + '\n' +
                '}';
    }
}
