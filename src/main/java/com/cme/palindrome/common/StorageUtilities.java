package com.cme.palindrome.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class StorageUtilities {
    ObjectMapper objectMapper = new ObjectMapper();

    /** This class has methods to read and write from json file for PalindromeData transaction **/

    public void writeToJsonFile(PalindromeObject palindromeObject, File file) throws IOException {
        List<PalindromeObject> palindromeDataList = new ArrayList<>();
        palindromeDataList.add(palindromeObject);

        try {
            // if file doesn't exist it creates new file and write palindromeDataList
            if (!file.exists())
                objectMapper.writeValue(file, palindromeDataList);
            else {
                // if file exists then reads any existing data
                List<PalindromeObject> existingPalindromeObjects = objectMapper.readValue(file, new TypeReference<List<PalindromeObject>>() {});

                // adds existing palindromeData to the list
                existingPalindromeObjects.add(palindromeObject);

                // writes all to the file
                objectMapper.writeValue(file, existingPalindromeObjects);
            }
        }catch (IOException e) {
            log.error("Error writing to file: {}, error: {}",file.getName(),e);
        }
    }

    public List<PalindromeObject> readFromJsonFile(File file) {
        List<PalindromeObject> palindromeDataList = Collections.emptyList();

        try {
            // checks for file and then reads json data and converts to PalindromeObject
            if (file.exists())
                palindromeDataList = objectMapper.readValue(file, new TypeReference<List<PalindromeObject>>() {});
        } catch (IOException e) {
            log.error("Error reading from file: {}, error: {}",file.getName(),e);
        }
        return palindromeDataList;
    }

}
