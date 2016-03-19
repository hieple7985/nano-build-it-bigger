package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Hiep Le on 19/03/2016.
 */
public class NonEmptyStringTest extends AndroidTestCase {

    private static final String LOG_TAG = "NonEmptyStringTest";

    public void test() {

        // Testing that Async task successfully retrieves a non-empty string
        // You can test this from androidTest -> Run 'All Tests'
        Log.v(LOG_TAG, "Running NonEmptyStringTest test");
        String result = null;
        JokeAsyncTask asyncTask = new JokeAsyncTask(getContext());
        asyncTask.execute();
        try {
            result = asyncTask.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
    }

}
