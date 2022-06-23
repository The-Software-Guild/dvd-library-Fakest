package com.harrybeggs.ui;

import com.harrybeggs.dto.Dvd;

import java.util.List;

public class DvdLibraryView {
    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int PrintMenuAndGetSelection(){
        io.print("Menu");
        io.print("1. List DVD IDs");
        io.print("2. Create New DVD");
        io.print("3. Edit DVD");
        io.print("4. View a DVD by ID");
        io.print("5. View a DVD by title");
        io.print("6. Remove a DVD");
        io.print("7. Exit");

        return io.readInt("Please Select from the above choices.", 1, 7);

    }
    public Dvd getNewDvdInfo(){
        String DvdId = io.readString("Please enter the DVD ID");
        String title = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the release date");
        String ageRating = io.readString("Please enter the age rating");
        String director = io.readString("Please enter the director");
        String studio = io.readString("Please enter the studio");
        String userRating = io.readString("Please enter the user rating");

        Dvd currentDvd = new Dvd(DvdId);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setAgeRating(ageRating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }
    public Dvd getEditDvdInfo(String DvdId){
        String title = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the release date");
        String ageRating = io.readString("Please enter the age rating");
        String director = io.readString("Please enter the director");
        String studio = io.readString("Please enter the studio");
        String userRating = io.readString("Please enter the user rating");

        Dvd currentDvd = new Dvd(DvdId);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setAgeRating(ageRating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }
    public void displayCreateDvdBanner(){
        io.print("=== CREATE DVD ===");
    }
    public void displayCreateSuccessBanner(){
        io.readString("DVD successfully Created. Please hit enter to continue.");

    }
    public void displayDvdList(List<Dvd> library){
        for(Dvd currentDvd : library){
            String dvdInfo = String.format("#%s : %s %s %s %s %s %s",
                    currentDvd.getDvdId(),
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getAgeRating(),
                    currentDvd.getDirector(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner(){
        io.print("=== Display All DVDs ===");
    }
    public void displayDisplayDvdBanner(){
        io.print("=== Display DVD ===");
    }
    public String getDvdIdChoice(){
        return io.readString("Please enter the DVD ID.");
    }
    public String getDvdTitleChoice(){
        return io.readString("Please enter the DVD title you want to search for.");
    }
    public void displayDvd(Dvd dvd){
        if(dvd != null){
            io.print(dvd.getDvdId());
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getAgeRating());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner(){
        io.print("Good Bye!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown command!");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditResult(Dvd edittedDvd) {
        if(edittedDvd != null) {
            io.print("DVD successfully editted.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Press enter to continue");
    }
    public void DisplayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
