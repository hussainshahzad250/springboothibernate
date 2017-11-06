/**
 * 
 */
package com.amhi.configuration;

import java.util.ResourceBundle;

public class Constants {

	public static final String RESOURCE_BUNDLE_NAME = "application";

	static {
		@SuppressWarnings("unused")
		ResourceBundle res = ResourceBundle
				.getBundle(Constants.RESOURCE_BUNDLE_NAME);
	}

}
