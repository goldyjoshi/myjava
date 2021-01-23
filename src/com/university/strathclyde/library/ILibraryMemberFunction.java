package com.university.strathclyde.library;

import com.university.strathclyde.library.resource.PhysicalBook;

import java.util.List;

/***
 * ILibraryMemberFunction represent contract between Library member and it's functionality.
 * @author Vijayshree Joshi
 */
public interface ILibraryMemberFunction {
    void printNotificationMessage();
    void borrowNewBook(PhysicalBook newBook);
    void printDetailsOfBookBorrowedByLibraryMember();
    Integer numberOfBooksBorrowedByLibraryMember();
}
