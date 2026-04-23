import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

/**
*
* Author: Malik Kouyate
* Created: 4/14/2026
* Purpose: Driver class for various data access classes.
*
**/

public class Driver{
	
	public static void main(String[] args) {
		
		UserDatabase db = new UserDatabase();
		
		//db.dropTables();
		db.createTables();
		
		//test for insertUser
		db.insertUser("testUser", "testPassword");
		
		//test for insertAlbu
		db.insertAlbum("testAlbum", "testArtist", "testId");
		
		//test for registeruser
	/*	db.insertReview("testArtist", "testAlbum", "This is a test review", 1, 1);
		
		String user = "user";
		String user1 = "user1";
		String pass = "pass1";
		db.createTables();
		db.registerUser("bill","12345");
		db.registerUser("bill","12345");
		db.registerUser("jacob","pizza");
		//Test update review
		db.updateReview("New updated review", 1 );
		//Test Register user for duplicate usernames
		db.registerUser(user, pass);
		db.registerUser(user1,pass);
		db.registerUser(user, pass);
		
		db.readDatabase();*/
		
		//db.dropTables();
		
		
	}

	/**
	*
	* Author: Malik Kouyate
	* Created: 4/11/2026
	* Purpose:
	*
	**/

	public static class SpotClient {

		static private Properties props = getProps();
		static private final String CLIENT_ID = getClientId(); //= props.getProperty("CLIENT_ID")
		static private final String CLIENT_SECRET = getClientSecret();// = props.getProperty("CLIENT_SECRET");

		public final SpotifyApi client;
		private final ClientCredentialsRequest clientCredentialsReq;
		public SpotClient(){
			client =  new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();
			clientCredentialsReq =  client.clientCredentials().build();
		}

		public void getClientCredentials(){
			try{
				final ClientCredentials clientCredentials = clientCredentialsReq.execute();

				client.setAccessToken(clientCredentials.getAccessToken());
				System.out.println("Client creds: " + clientCredentials);

				System.out.println(("Client creds expire in: " + clientCredentials.getExpiresIn()));

			}catch(IOException | SpotifyWebApiException | ParseException e ){
				System.out.println("Error: " + e);
			}
		}

		static private Properties getProps(){
			File f = new File(".env");
			Properties p = new Properties();
			try (FileReader file = new FileReader(f)) {
				p.load(file);
				return p;
			}catch (IOException e ){
				System.out.println("Couldn't load from file.");
				return null;
			}
		}

		static private String getClientSecret(){
			return props.getProperty("CLIENT_SECRET");
		}
		static private String getClientId(){
			return props.getProperty("CLIENT_ID");
		}

	}
}
