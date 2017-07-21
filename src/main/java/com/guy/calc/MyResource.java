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
   private String fixDevid(String s){
	  
	   StringBuffer temp = new StringBuffer();
	   for(int i=0;i<s.length();i++){
		   if(s.charAt(i)=='&'){
			   temp.append("/");
		   }else{
			   temp.append(s.charAt(i));
		   }
		   
	   }
	 
	   return temp.toString();
   }
    @GET
    @Path("qus/{ask}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("ask") String s ) {
    	
    //	System.out.println(c.runForGui(s));
    	System.out.println(fixDevid(s));
        return c.runForGui(fixDevid(s));
    }
}
