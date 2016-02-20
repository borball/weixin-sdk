package com.riversoft.weixin.mp.care;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 11/25/2015.
 */
public class CareMessagesTest {

    @Test
    public void testText(){
        CareMessages.defaultCareMessages().voice("oELhlt7Q-lRmLbRsPsaKeVX6pqjg", "4GM9zYltLDSpw6wzGnq0d4Iy5t8R67wA_3mEgIHYi0jUrXWUARyt8cRsFEbmyT0z");
    }

    @Test
    public void testCard(){
        //o7Tmfs96UDesd920Gzi0jYJPnBzQ

        List<String> users = new ArrayList<>();
        users.add("o7Tmfs5ezesAcMR-Uu1HrFTm0VPM");
        users.add("o7Tmfs6Bx0Tw6en7idZabXGG9Di8");
        users.add("o7Tmfs3EHCndVenva5knKxA4D3XA");
        users.add("o7Tmfs8y4gBrwzX_YXKAuL7TFrsQ");
        users.add("o7Tmfs2uEnDbaJ_rs2OejuWZPH2M");
        users.add("o7Tmfs6gPvhH9FXtFz8JR2jfeL0g");
        users.add("o7Tmfs0CkrVdq7og3sKWmot55iS8");
        users.add("o7Tmfs7pE8QMVDj9jF--Y74wjUpE");
        users.add("o7Tmfs96UDesd920Gzi0jYJPnBzQ");

        for (String user: users) {
            try {
                CareMessages.defaultCareMessages().card(user, "p7Tmfs2kxdGp2YN7LX2BMzEYCI2Q");
            } catch (Exception e) {
                System.out.println(user + ": p7Tmfs2kxdGp2YN7LX2BMzEYCI2Q: failed.");
                e.printStackTrace();
            }
            try {
                CareMessages.defaultCareMessages().card(user, "p7Tmfs0e7WUNurXRrEnaagja7SQs");
            } catch (Exception e) {
                System.out.println(user + ": p7Tmfs2kxdGp2YN7LX2BMzEYCI2Q: failed.");
            }
        }

    }
}
