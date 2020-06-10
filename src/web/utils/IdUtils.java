package web.utils;

import java.util.UUID;

public class IdUtils {

	public static String getUUID() {
		String code = 	UUID.randomUUID().toString();
		return code;
	}

}
