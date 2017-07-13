package com.guy.calc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calcJB.Calculator;


@Path("mycalc")
public class MyResource {
Calculator c = Calculator.constructor();
   
    @GET
    @Path("qus/{ask}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("ask") String s ) {
    //	System.out.println(c.runForGui(s));
        return c.runForGui(s);
    }
}
