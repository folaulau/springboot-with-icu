package com.folautech.icu;

import com.folautech.icu.dao.UserRepository;
import com.folautech.icu.model.User;
import com.folautech.icu.service.UserService;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class SpringbootWithIcuApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void getSortedUsers() throws Exception {

		//Sort.by(Sort.Order.desc("firstName"))
        Pageable pageable = PageRequest.of(0, 20);

        Page<User> page = userService.getUsers(pageable);


        RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(Locale.US); // Swedish locale
        collator.setStrength(Collator.QUATERNARY);
        collator.setAlternateHandlingShifted(true);

        List<User> users = page.getContent();

        for (User user : users) {
            System.out.println(user.getFirstName());

            String collationKeyToString = new String(collator.getCollationKey(user.getAge()+"").toByteArray(), StandardCharsets.UTF_16BE);

            System.out.println("collationKeyToString: "+collator.getCollationKey(user.getAge()+"").hashCode());
        }

        List<User> copiedUsers = new ArrayList<>();
        for (User obj : users) {
            copiedUsers.add(obj); // Assuming MyObject has a copy constructor
        }

		System.out.println("=================");

		Collections.sort(copiedUsers, (User u1, User u2) -> collator.compare(u1.getFirstName(), u2.getFirstName()));

        for (User user : copiedUsers) {
            System.out.println(user.getFirstName());
        }
    }

    @Test
    void test_hymn_song_titles() throws Exception {

        List<String> songs = new ArrayList<>();

        songs.add("Behold, the Mountain of the Lord");
        songs.add("Lo, the Mighty God Appearing!");
        songs.add("All Creatures of Our God and King");
        songs.add("For All the Saints");
        songs.add("How Firm a Foundation");
        songs.add("How Great Thou Art");
        songs.add("The Spirit of God");
        songs.add("’Twas Witnessed in the Morning Sky");
        songs.add("Because I Have Been Given Much");
        songs.add("Children of Our Heavenly Father");
        songs.add("Be Still, My Soul");
        songs.add("Zing");

        RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(Locale.US); // Swedish locale
        collator.setStrength(Collator.QUATERNARY);
        collator.setAlternateHandlingShifted(true);

        for (String title : songs) {
//            System.out.println(title);

            long hash = 0;
            for(String word : title.split(" ")){
                int h = collator.getCollationKey(word).hashCode();
                System.out.println("word: "+word+", h: "+h);
                hash += h;
            }

            System.out.println("title: "+title+", hascode: "+hash);
        }

        System.out.println("======= Sorted Songs ==========");

        Collections.sort(songs, (String u1, String u2) -> collator.compare(u1, u2));

        for (String title : songs) {
//            System.out.println("title: "+title+", hascode: "+collator.getCollationKey(title).hashCode()+", sourceString: "+collator.getCollationKey(title).toString());
            System.out.println("title: "+title);

        }
    }

}
