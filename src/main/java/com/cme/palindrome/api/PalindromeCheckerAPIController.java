package com.cme.palindrome.api;

import com.cme.palindrome.common.PalindromeObject;
import com.cme.palindrome.service.PalindromeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import reactor.core.publisher.Mono;


@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/test")
@RestController
@Slf4j
public class PalindromeCheckerAPIController {
    private final PalindromeService palindromeService;

    @Autowired
    public PalindromeCheckerAPIController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping(path = "/checkPalindrome")
    public Mono<Boolean> isPalindrome(@NotNull @RequestBody @Valid PalindromeObject palindromeObject) throws IOException {
        log.debug("checkPalindrome api called from {} for input {}", getClass(), palindromeObject.getTextValue());
        // Use of Mono to make this api call non-blocking
        return Mono.fromSupplier(() -> {
            try {
                return palindromeService.isPalindrome(palindromeObject);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /** Created for testing purpose
     this is api with no non-blocking implementation **/
    @PostMapping(path = "/checkPalindromeBlocking")
    public Boolean isPalindromeBlocking(@NotNull @RequestBody @Valid PalindromeObject palindromeObject) throws IOException {
        log.debug("checkPalindromeBlocking api called from {} for input {}", getClass(), palindromeObject.getTextValue());
        return palindromeService.isPalindrome(palindromeObject);
    }
}
