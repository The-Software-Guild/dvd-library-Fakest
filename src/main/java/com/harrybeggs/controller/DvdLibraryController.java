package com.harrybeggs.controller;

import com.harrybeggs.dao.LibraryDao;
import com.harrybeggs.dao.LibraryDaoException;
import com.harrybeggs.dao.LibraryDaoFileImpl;
import com.harrybeggs.dto.Dvd;
import com.harrybeggs.ui.DvdLibraryView;
import com.harrybeggs.ui.UserIO;
import com.harrybeggs.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdLibraryController {
    private UserIO io = new UserIOConsoleImpl();

    private DvdLibraryView view;
    private LibraryDao dao;
    public void run(DvdLibraryView view, LibraryDao dao) throws LibraryDaoException {
        boolean keepRunning = true;
        int menuSelection = 0;
        this.view = view;
        this.dao = dao;

        while(keepRunning){
            menuSelection = getMenuSelection();
            switch (menuSelection){
                case 1:
                    listDvds();
                    break;
                case 2:
                    createDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    viewDvd();
                    break;
                case 5:
                    viewDvdByTitle();
                    break;
                case 6:
                    removeDvd();
                    break;
                case 7:
                    keepRunning = false;
                    break;
                default:
                    unknownCommand();
                    break;

            }
        }
        exitMessage();
    }

    private void editDvd() throws LibraryDaoException {
        view.displayEditDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd newDvd = view.getEditDvdInfo(dvdId);
        Dvd edittedDvd = dao.editDvd(dvdId, newDvd);
        view.displayEditResult(edittedDvd);

    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void removeDvd() throws LibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void viewDvd() throws LibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void viewDvdByTitle() throws LibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvdByTitle(dvdTitle);
        view.displayDvd(dvd);
    }

    private void listDvds() throws LibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void createDvd() throws LibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdId(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private int getMenuSelection() {
        return view.PrintMenuAndGetSelection();
    }

}
