package com.folautech.icu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
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
    public void sortHymmNames(){


        String[] words = {"A Child’s Prayer", "A Heritage of Faith", "A Sinless, Pure, and Holy Man", "As the Shadows Fall", "A Pioneering Heart"
            ,"Angels Round About"
            ,"An Old-Fashioned Christmas"
            ,"Awake and Arise"
            ,"Away in a Manger"};

//        // Create a Collator instance for a specific locale
//        Collator collator = Collator.getInstance(Locale.US); // Swedish locale

        Map<String, String> data = new HashMap<>();
        List<String> sortedWords = new ArrayList<>();


        RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(Locale.US);
        collator.setStrength(Collator.TERTIARY);
        collator.setAlternateHandlingShifted(true);

        for (String word : words) {
            
            byte[] sortKey = collator.getCollationKey(word).toByteArray();
            String encodedKey = Base64.getEncoder().encodeToString(sortKey);
            data.put(encodedKey, word);
            sortedWords.add(encodedKey);
        }

        sortedWords = sortedWords.stream().sorted().collect(Collectors.toList());

        // Print the sorted array
        for (String word : sortedWords) {
            System.out.println(word+" -> "+data.get(word));

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
