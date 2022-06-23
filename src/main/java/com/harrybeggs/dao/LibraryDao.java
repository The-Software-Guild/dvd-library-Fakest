package com.harrybeggs.dao;

import com.harrybeggs.dto.Dvd;

import java.util.List;

public interface LibraryDao {
    Dvd addDvd(String DvdId, Dvd film) throws LibraryDaoException;
    List<Dvd> getAllDvds() throws LibraryDaoException;
    Dvd getDvd(String DvdId) throws LibraryDaoException;
    Dvd removeDvd(String DvdId) throws LibraryDaoException;

    Dvd getDvdByTitle(String dvdTitle) throws LibraryDaoException;

    Dvd editDvd(String dvdId, Dvd film) throws LibraryDaoException;
}
