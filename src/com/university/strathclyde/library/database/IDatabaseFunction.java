package com.university.strathclyde.library.database;

import com.university.strathclyde.library.LibraryMember;
import com.university.strathclyde.library.resource.PhysicalBook;

import java.sql.ResultSet;

public interface IDatabaseFunction {
    public ResultSet getAllLoginDetails();
    public void addLibraryMember(LibraryMember newLibraryMember);
    public ResultSet getLibraryMember();
    public void addABook(PhysicalBook physicalBook);
    public ResultSet checkBookAvailable(final String isbn);
}
