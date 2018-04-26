package main;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class Puzzle {
	public static final String PUZZLE_ID = "BBSE_E01";

	public static void main(String[] args) {

		byte[] domain = hexStringToByteArray("0000f00000000000000000000000000000000000000000000000000000000000");
		long x = 0;
		while (true) {
			try {
				byte[] puzzleResult = hash(PUZZLE_ID + x);
				if ( byteArrayToHexString(domain).compareTo(byteArrayToHexString(puzzleResult))> 0) {
					System.out.println(byteArrayToHexString(puzzleResult));
					System.out.println("Value of x that solves the puzzle : " + x);
					break;
				}
				x++;
			} catch (GeneralSecurityException e) {
				System.out.println("Algorithm not found!");
			}

		}
	}
	public static void printByteArray(byte[] array){
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}

	public static int compareByteArray(byte[] src, byte[] dst) {
		for (int i = 0; i < dst.length; i++) {
			if (src[i] > dst[i]) {
				return 1;
			} else if (src[i] < dst[i]) {
				return -1;
			}
		}
		return 0;

	}

	public static byte[] hash(String input) throws GeneralSecurityException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		return hash;
	}

	// Converting a string of hex character to bytes
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// Converting a bytes array to string of hex character
	public static String byteArrayToHexString(byte[] b) {
		int len = b.length;
		String data = new String();

		for (int i = 0; i < len; i++) {
			data += Integer.toHexString((b[i] >> 4) & 0xf);
			data += Integer.toHexString(b[i] & 0xf);
		}
		return data;
	}

	public static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
