package com.cme.palindrome;

import com.test.palindrome.api.PalindromeCheckerAPIController;
import com.test.palindrome.service.PalindromeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class PalindromeControllerTest {

    protected MockMvc mvc;
    static final String url = "/api/v1/test/checkPalindrome/";

    @InjectMocks
    PalindromeCheckerAPIController palindromeCheckerAPIController;

    @Mock
    PalindromeService palindromeService;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(palindromeCheckerAPIController).build();
    }

    @Test
    public void isPalindrome_validInput_returnsResponse() throws Exception {

        // Force the service to return the object we sent in, since this test is only testing that validation occurs on inputs
        Mockito.when(palindromeService.isPalindrome(PalindromeTestMockData.getPalindromeObject())).thenReturn(true);

        //String inputJson = mapToJson(PalindromeTestMockData.getPalindromeObject());
        // Make a Post call to api with a body
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PalindromeTestMockData.getValidPalindromeInput
                        ))
                .andExpect(status().isOk())
                .andReturn();

        verify(palindromeService, times(1)).isPalindrome(any());
        assertTrue(mvcResult.getResponse().toString(),true);
    }

    @Test
    public void isPalindrome_inputNumber_returns400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PalindromeTestMockData.getNumberInput))
                .andExpect(status().is4xxClientError())
                .andReturn();

        verify(palindromeService, times(0)).isPalindrome(any());
    }

    @Test
    public void isPalindrome_inputNull_returns400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PalindromeTestMockData.getNullInput))
                .andExpect(status().is4xxClientError())
                .andReturn();

        verify(palindromeService, times(0)).isPalindrome(any());
    }

    @Test
    public void isPalindrome_inputEmpty_returns400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PalindromeTestMockData.getEmptyInput))
                .andExpect(status().is4xxClientError())
                .andReturn();

        verify(palindromeService, times(0)).isPalindrome(any());
    }

    @Test
    public void isPalindrome_inputSpace_returns400() throws Exception {
        // Make a Post call to api with a body
        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(PalindromeTestMockData.getSpacedInput))
                .andExpect(status().is4xxClientError())
                .andReturn();

        verify(palindromeService, times(0)).isPalindrome(any());
    }

    @Test
    public void isPalindrome_noInput_returns400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

        verify(palindromeService, times(0)).isPalindrome(any());
    }
}
