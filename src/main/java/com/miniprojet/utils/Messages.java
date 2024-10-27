package com.miniprojet.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class Messages {
	private static final String BUNDLE_NAME = "messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String format(String key, Object arg0) {
		return format(key, new Object[] { arg0 });
	}

	public static String format(String key, Object[] arguments) {
		return MessageFormat.format(getString(key), arguments);
	}
}
