/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.fitaleks.builditbigger.backend;

import com.fitaleks.builditbigger.JokesProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Random;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.builditbigger.fitaleks.com",
    ownerName = "backend.builditbigger.fitaleks.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public JokeBean sayHi(@Named("name") String name) {
        JokeBean response = new JokeBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "sayJoke")
    public JokeBean sayJoke() {
        JokeBean response = new JokeBean();
        final Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            response.setData(JokesProvider.getJoke());
        } else {
            response.setData(JokesProvider.getImgJoke());
        }
        return response;
    }

}
