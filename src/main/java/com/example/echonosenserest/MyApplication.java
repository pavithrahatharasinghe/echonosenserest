package com.example.echonosenserest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // Register your JAX-RS resources here
       //    classes.add(MyResource.class); // Replace with your actual resource class
        classes.add(CORSFilter.class); // Register the CORS filter
        return classes;
    }
}

