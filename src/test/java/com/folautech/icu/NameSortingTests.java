package com.folautech.icu;

import java.util.Arrays;
import java.util.Locale;

import com.ibm.icu.text.Collator;
import org.junit.jupiter.api.Test;

public class NameSortingTests {


    @Test
    public void sortNames(){


        String[] words = {"äpple", "apple", "banana", "örange", "orange"};

        // Create a Collator instance for a specific locale
        Collator collator = Collator.getInstance(Locale.US); // Swedish locale

        // Sort the array of strings
        Arrays.sort(words, collator);

        // Print the sorted array
        for (String word : words) {
            System.out.println(word);
        }
    }
}
