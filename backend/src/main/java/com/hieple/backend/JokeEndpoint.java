/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.hieple.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.hieple.com",
    ownerName = "backend.hieple.com",
    packagePath=""
  )
)
public class JokeEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
//    @ApiMethod(name = "sayHi")
//    public MyBean sayHi(@Named("name") String name) {
//        MyBean response = new MyBean();
////        response.setData("Hi, " + name);
//
//        return response;
//    }

    @ApiMethod(name = "putJoke")
    public JokeBean putJoke(JokeBean joke) {
//        JokerBean response = new JokerBean();
//        response.setJoke("Hi, " + name);
        return joke;
    }

}
