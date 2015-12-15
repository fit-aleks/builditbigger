package com.fitaleks.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements IJokeDownloadListener {
    public ApplicationTest() {
        super(Application.class);
    }

    private GetJokeAsyncTask getJokeAsyncTask;
    private CountDownLatch signal;
    private String returnedJoke;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getJokeAsyncTask = new GetJokeAsyncTask(this);
        signal = new CountDownLatch(1);
        returnedJoke = "";
    }

    @UiThreadTest
    public void testLoadingJoke() throws InterruptedException {
        getJokeAsyncTask.execute();
        signal.await(5, TimeUnit.SECONDS);
        assertTrue("Signal was not recieved", signal.getCount() == 0);
        assertTrue("Recieved string is not empty", !returnedJoke.equals(""));
    }

    @Override
    public void onJokeLoaded(String joke) {
        returnedJoke = joke;
        signal.countDown();
    }
}