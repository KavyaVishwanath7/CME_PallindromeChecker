package com.cme.palindrome.service;

import com.cme.palindrome.common.PalindromeObject;
import com.cme.palindrome.dao.PalindromeFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class PalindromeService {
    private final PalindromeFileRepository palindromeFileRepository;
    private final ConcurrentHashMap<String, Object> palindromeCache = new ConcurrentHashMap<>();

    /* DB repository, findAll() and save() can be used for PalindromeObject transactions */
    //private final PalindromeDBRepository palindromeDBRepository;

    @Autowired
    public PalindromeService(PalindromeFileRepository palindromeFileRepository) {
        this.palindromeFileRepository = palindromeFileRepository;
    }

    /* cache configuration to store palindrome checks */
    @Cacheable(value = "palindromeCache", key = "#palindromeObject.userName + '|' + #palindromeObject.textValue + '|' + #palindromeObject.isPalindrome")
    public boolean isPalindrome(PalindromeObject palindromeObject) throws IOException {
        log.debug("isPalindrome called from {} for input {}", getClass(), palindromeObject.getTextValue());
        // return from cache if the value pair already exists
        if (palindromeCache.containsKey(palindromeObject.getTextValue())) {
            log.info("isPalindrome called from cache for input {}", palindromeObject.getTextValue());
            return (boolean) palindromeCache.get(palindromeObject.getTextValue());
        }

        // calls checkPalindrome method if not present in cache
        boolean isPalindromeResult = checkPalindrome(palindromeObject.getTextValue());
        palindromeObject.setIsPalindrome(isPalindromeResult);

        // store the result in cache
        palindromeCache.put(palindromeObject.getTextValue(), isPalindromeResult);
        // adds the data to json file
        palindromeFileRepository.storePalindrome(palindromeObject);

        return isPalindromeResult;

    }

    /* This is invoked after application is started, reads existing palindrome data from json file and stored in cache */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeCache() {
        log.info("Initializing cache ...");
        List<PalindromeObject> dataList = palindromeFileRepository.getPalindromeData();

        // retrieve palindrome data from json file and store in cache
        for (PalindromeObject data : dataList) {
            palindromeCache.put(data.getTextValue(), data.getIsPalindrome());
        }

        log.info("Cache loaded...{}", palindromeCache);
    }

    // logic to verify if the input is palindrome
    private static boolean checkPalindrome(String input) {
        log.info("CheckPalindrome called for {}", input);

        // compares the character from start with end and until the middle of the given word
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}