package com.amhi.app;

import java.io.IOException;
import java.util.Random;

//import org.restlet.data.MediaType;
//import org.restlet.resource.ClientResource;
//import org.restlet.resource.ResourceException;

public class GenerateRandomString {

	private static final String OTP_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args){

		// new
		// ClientResource("http://localhost:8090/get").get().write(System.out);
		//
		// ClientResource cr = new ClientResource("http://localhost:8090/get");
		// cr.get(MediaType.APPLICATION_JSON).write(System.out);

		String generate = generate();
		System.out.println(generate);

	}

	private static String generate() {
		int length = 4;
		Random randomGenerator = new Random();
		StringBuilder password = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			password.append(OTP_STRING.charAt(randomGenerator
					.nextInt(OTP_STRING.length())));
		}
		return password.toString();
	}

}
