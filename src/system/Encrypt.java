package system;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {
	public static String generateSaltedHash(String password, String passwordSalt) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update((password + passwordSalt).getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String passwordHash = (new BigInteger(messageDigest.digest()))
				.toString(32);
		return passwordHash;
	}

	// Get random salt
	public static String nextSalt() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
