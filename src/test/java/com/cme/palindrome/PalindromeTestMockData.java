package com.cme.palindrome;

import com.cme.palindrome.common.PalindromeObject;

public class PalindromeTestMockData {

    public static String getValidPalindromeInput = "{\n" +
            "    \"userName\": \"kavya Vishwanath\",\n" +
            "    \"textValue\": \"deed\"\n" +
            "}";

    public static String getNumberInput = "{\n" +
            "    \"userName\": \"kavya Vishwanath\",\n" +
            "    \"textValue\": \"1234\"\n" +
            "}";

    public static String getEmptyInput = "{\n" +
            "    \"userName\": \"kavya Vishwanath\",\n" +
            "    \"textValue\": \"\"\n" +
            "}";

    public static String getNullInput = "{\n" +
            "    \"userName\": \"kavya Vishwanath\",\n" +
            "    \"textValue\": null\n" +
            "}";

    public static String getSpacedInput = "{\n" +
            "    \"userName\": \"kavya Vishwanath\",\n" +
            "    \"textValue\": \"deed deed\"\n" +
            "}";

    public static PalindromeObject getPalindromeObject() {
        PalindromeObject palindromeObject = new PalindromeObject();
        palindromeObject.setUserName("user1");
        palindromeObject.setTextValue("deed");
        return palindromeObject;
    }
}
