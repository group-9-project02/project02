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
* Created: 4/11/2026
* Purpose:
*
**/

public class SpotClient {
	
	static private Properties props = getProps();
	static private final String CLIENT_ID = getClientId(); //= props.getProperty("CLIENT_ID")
	static private final String CLIENT_SECRET = getClientSecret();// = props.getProperty("CLIENT_SECRET");
	
	final SpotifyApi client;
	private final ClientCredentialsRequest clientCredentialsReq;
	SpotClient(){
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
