package com.folautech.icu;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

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

    @Test
    public void testOptional(){


        Integer number = null;

        System.out.println("number? "+Optional.ofNullable(number)
            .map(n -> !n.equals(1))
            .orElse(true));

        number = 2;

        System.out.println("number? "+Optional.ofNullable(number)
            .map(n -> !n.equals(1))
            .orElse(true));

        number = 1;

        System.out.println("number? "+Optional.ofNullable(number)
            .map(n -> !n.equals(1))
            .orElse(true));

        number = null;

        System.out.println("number? "+Optional.ofNullable(number)
            .filter(n -> n.equals(1))
            .isPresent());

        number = 2;

        System.out.println("number? "+Optional.ofNullable(number)
            .filter(n -> n.equals(1))
            .isPresent());

        number = 1;

        System.out.println("number? "+Optional.ofNullable(number)
            .filter(n -> n.equals(1))
            .isPresent());
    }
}
