package com.harrybeggs.dao;

import com.harrybeggs.dto.Dvd;

import java.io.*;
import java.util.*;

public class LibraryDaoFileImpl implements LibraryDao{
    public Map<String, Dvd> library = new HashMap<>();
    public static final String LIBRARY_FILE = "src/main/java/library.txt";
    public static final String DELIMITER = "::";

    private Dvd unmarshallDvd(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdId = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(dvdId);
        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setAgeRating(dvdTokens[3]);
        dvdFromFile.setDirector(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
    }

    private String marshallDvd(Dvd aDvd){
        String dvdAsText = aDvd.getDvdId() + DELIMITER;
        dvdAsText += aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getAgeRating() + DELIMITER;
        dvdAsText += aDvd.getDirector() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating() + DELIMITER;
        return dvdAsText;

    }

    private void writeLibrary() throws LibraryDaoException {
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        }catch (IOException e){
            throw new LibraryDaoException("Could not save DVD data", e);
        }

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for(Dvd currentDvd : dvdList){
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private void loadLibrary() throws LibraryDaoException{
        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new LibraryDaoException("Couldn't load roster data into memory.", e);

        }
        String currentLine;
        Dvd currentDvd;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            library.put(currentDvd.getDvdId(), currentDvd);
        }
        scanner.close();
    }
    @Override
    public Dvd addDvd(String DvdId, Dvd film) throws LibraryDaoException{
        loadLibrary();
        Dvd newDvd = library.put(DvdId, film);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws LibraryDaoException{
        loadLibrary();
        return new ArrayList<Dvd>(library.values());
    }

    @Override
    public Dvd getDvd(String DvdId) throws LibraryDaoException{
        loadLibrary();
        return library.get(DvdId);
    }

    @Override
    public Dvd removeDvd(String DvdId) throws LibraryDaoException{
        loadLibrary();
        Dvd removedDvd = library.remove(DvdId);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd getDvdByTitle(String dvdTitle) throws LibraryDaoException {
        Dvd dvd = null;
        loadLibrary();
        for(Map.Entry<String, Dvd> entry: library.entrySet()){
            if(entry.getValue().getTitle().compareTo(dvdTitle) == 0){
                dvd = entry.getValue();
                break;
            }
        }
        return dvd;
    }

    @Override
    public Dvd editDvd(String dvdId, Dvd newDvd) throws LibraryDaoException {
        loadLibrary();
        Dvd oldDvd = library.put(dvdId, newDvd);
        writeLibrary();
        return oldDvd;
    }
}
