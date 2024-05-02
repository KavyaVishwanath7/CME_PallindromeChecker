package com.cme.palindrome.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PalindromeObject {

    @NotNull
    String userName;
    @ValidPalindromeInput
    String textValue;
    Boolean isPalindrome;
}
