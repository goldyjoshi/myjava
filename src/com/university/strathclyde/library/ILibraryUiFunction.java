package com.university.strathclyde.library;

import com.university.strathclyde.library.resource.ElectronicDevice;
import com.university.strathclyde.library.resource.LibraryResource;
import com.university.strathclyde.library.resource.PhysicalBook;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/***
 * ILibraryUiFunction expose library function related to UI.
 * It's a contract between library and it's UI.
 * @author Vijayshree Joshi
 */
public interface ILibraryUiFunction {
    void addLibraryResourceInLibraryCatalogue(final String resourceId, final LibraryResource libraryResource);
    List<LibraryMember> getLibraryMembers();
    boolean login(String loginEmail, String password) throws SQLException;
    String signUp(LibraryMember newLibraryMember);
    Map getLibraryCatalogue();
    LibraryResource getResourceByResourceId(String resourceId);
    List<PhysicalBook> printAvailableBooksToIssue();
    String issueBook(PhysicalBook physicalBook, LibraryMember libraryMember);
    String removeResourceFromCatalogue(final String resourceID);
    String addDeviceInLibrary(ElectronicDevice electronicDevice);
    List<ElectronicDevice> getListOfElectronicDevices();
    List<PhysicalBook> printDetailsOfPhysicalBook();
    String returnABook(PhysicalBook physicalBook, boolean recordDamage, String damageDescription);
}
