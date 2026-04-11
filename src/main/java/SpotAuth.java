import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

/**
*
* Author: Malik Kouyate
* Created: 4/10/2026
* Purpose:
*
**/

public class SpotAuth {
	
	static final private int LEN = 64;
	static final private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	final static private String verifier = genVerifier();
	final static private String challenge = genChallenge();
	final static private Properties properties = getProperties();
	final static private String clientId = properties.getProperty("USER_CLIENT");
	final static private String uri = properties.getProperty("URI");
	final static private URI spotUri = SpotifyHttpManager.makeUri(uri);
	final static private SpotifyApi spotApi = new SpotifyApi.Builder().setClientId(clientId).setRedirectUri(spotUri).build();
	final static private AuthorizationCodeUriRequest authCodeUriReq = spotApi.authorizationCodePKCEUri(challenge).response_type("code").build();
	
	public static void authCodeSync(){
		System.out.println("PORT: " + spotApi.getPort());
		System.out.println("DEFAULT_PORT: " + spotApi.DEFAULT_PORT);
		System.out.println("DEFAULT_HOST: " + spotApi.DEFAULT_HOST);
		final URI req = authCodeUriReq.execute();
		System.out.println("Please go to the following link and authenticate the application: " + req);
		getCode();
		
	}
	
	private static String genVerifier(){
		Random r = new Random();
		String pass = "";
		for( int i = 0; i < LEN; i++){
			pass.concat(String.valueOf(letters.charAt(Math.abs(r.nextInt()%letters.length()))));
		}
		return pass;
	}
	
	private static String genChallenge() {
		String ver = new String(verifier);
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(ver.getBytes(StandardCharsets.UTF_8));
			String b64 = Base64.getEncoder().encodeToString(hash);
			return b64;
		} catch (NoSuchAlgorithmException e) {
			System.out.printf("ERROR: %s", e.toString());
			return null;
		}
		
	}
	
	private static Properties getProperties(){
		File f = new File(".env");
		Properties p = new Properties();
		try(FileReader file = new FileReader(f)){
			p.load(file);
			return p;
		}catch (IOException e) {
			System.out.printf("ERROR: %s", e.toString());
			return null;
		}
		
	}
	private static void storeProperties(String k , String v){
		File f = new File(".env");
		try(FileWriter file = new FileWriter(f)){
			properties.setProperty(k,v);
			properties.store(file, null);
			System.out.println(properties);
		}catch (IOException e) {
			System.out.printf("ERROR: %s", e.toString());
		}
		
	}
	
	
	private static void getCode(){
		System.out.println("Please paste the url here: ");
		Scanner input = new Scanner(System.in);
		String code = null;
		while(code == null ){
			String check = input.nextLine().toString();
			System.out.println((check));
			if(check.contains(uri.substring(0, uri.length() - 4))){
				code = check.substring(check.indexOf("=") + 1, check.length());
				break;
			}else{
				System.out.println("Enter proper url: ");
			}
		}
		
		storeProperties("CODE", code);
	}
	
	static void main() {
		authCodeSync();
		getCode();
	}
	
	



}
