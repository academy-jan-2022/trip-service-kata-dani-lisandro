package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserShould {

    @Test
    void return_false_when_no_friend() {
        User user = new UserBuilder().build();

        Assertions.assertFalse(user.isFriendsWith(new User()));
    }

    @Test
    void return_true_when_friend() {
        User friend = new User();

        User user = new UserBuilder()
                .withFriends(friend)
                .build();

        Assertions.assertTrue(user.isFriendsWith(friend));
    }

    @Test
    void return_false_when_different_friend() {
        User differentFriend = new User();
        User user = new UserBuilder()
                .withFriends(differentFriend)
                .build();

        User loggedUser = new User();
        Assertions.assertFalse(user.isFriendsWith(loggedUser));
    }
}
