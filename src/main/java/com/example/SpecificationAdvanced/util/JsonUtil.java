package com.example.SpecificationAdvanced.util;

import org.apache.logging.log4j.util.Base64Util;
import org.apache.tomcat.util.codec.binary.Base64;

public class JsonUtil {
	public static String encodeBase64(String encodeMe) {
		return Base64Util.encode(encodeMe);
	}

	public static String decodeBase64(String decodeMe) {
		return new String(Base64.decodeBase64(decodeMe));
	}
}
