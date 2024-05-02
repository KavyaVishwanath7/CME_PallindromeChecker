package com.cme.palindrome.dao;

import com.cme.palindrome.common.PalindromeObject;
import com.cme.palindrome.common.StorageUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class PalindromeFileRepository {

    private final String filename;
    private final File file;
    StorageUtilities storageUtilities = new StorageUtilities();

    // constructor to initialize filename
    @Autowired
    public PalindromeFileRepository(@Value("${app.storageFileName}") String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    // writes PalindromeObject to json file
    public void storePalindrome(PalindromeObject palindromeObject) throws IOException {
        storageUtilities.writeToJsonFile(palindromeObject, file);
    }

    // reads PalindromeObject(s) from json file
    public List<PalindromeObject> getPalindromeData(){
        return storageUtilities.readFromJsonFile(file);
    }

}
