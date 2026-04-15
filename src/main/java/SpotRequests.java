import java.io.IOException;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
/**
*
* Author: Malik Kouyate
* Created: 4/11/2026
* Purpose:
*
**/

public class SpotRequests {
	
	private SpotifyApi client;
	private SearchItemRequest request;
	private SearchResult response;
	
	
	SpotRequests(SpotClient c) {
		client = c.client;
		System.out.println("client: " + client);
		System.out.println(client.getAccessToken());
	}
	
	
	public String search(String find, String type) {
		request = new SearchItemRequest.Builder(client.getAccessToken()).q(find).type(type).limit(5).offset(0).build();
		try{
			response = request.execute();
			switch (type) {
				case "album":
					return albumSearch(response);
				case "track":
					return trackSearch(response);
				default:
					System.out.println("Search type doesn't match");
					return null;
			}
		}catch( SpotifyWebApiException | ParseException | IOException e) {
			System.out.println("An error has occurred");
			return e.toString();
		}
	}
	
	private String albumSearch(SearchResult result) {
		AlbumSimplified album  = result.getAlbums().getItems()[0];
		String albumName = album.getName();
		String albumArtist = album.getArtists()[0].getName();
		String albumID = album.getId();
		String albumInfo = String.format("%s \n %s\n %s\n", albumName, albumArtist, albumID);
		return albumInfo;
	}
	
	private String trackSearch(SearchResult result) {
		Track track  = result.getTracks().getItems()[0];
		String trackName = track.getName();
		String trackArtist = track.getArtists()[0].getName();
		String trackID = track.getId();
		String trackInfo = String.format("%s \n %s\n %s\n", trackName, trackArtist, trackID);
		return trackInfo;
	
	}
}
	
	
	
	
	
	
	
	
	
	
	

