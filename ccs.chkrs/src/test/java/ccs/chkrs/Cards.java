package ccs.chkrs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.net.UnknownHostException;
import io.restassured.http.ContentType;

public class Cards {
	private String deck_id;
	
	public void setDeckId(String deck_id) {
		this.deck_id=deck_id;
	}
	
	public String getDeckId() {
		return this.deck_id;
	}
	
	
	public boolean isSiteUp() throws UnknownHostException {
		RestAssured.baseURI = "https://deckofcardsapi.com/"; 
		Response response = given()
		        .contentType(ContentType.HTML)
		        .when()
		        .get("")
		        .then()
		        .extract().response();
		System.out.println("response.statusCode="+response.statusCode());
		if(response.statusCode() == 200) {
			return true;
		}
		return false;
        
	}
	
	public boolean getNewDeck() throws UnknownHostException {
		RestAssured.baseURI = "https://deckofcardsapi.com"; 
		Response response = given()
		        .contentType(ContentType.HTML)
		        .when()
		        .get("/api/deck/new/")
		        .then()
		        .extract().response();
		if(response.statusCode() == 200) {
			System.out.println("response status of getNewDeck="+response.getBody().asPrettyString());
			JsonPath jsonVal=response.getBody().jsonPath();
			String deck_id=jsonVal.get("deck_id").toString();
			setDeckId(deck_id);
			System.out.println("jsonVal.deck_id()="+jsonVal.get("deck_id").toString());
			return true;
		}
		return false;
		
	}
	
	public boolean shuffleThedeck() {
		RestAssured.baseURI = "https://deckofcardsapi.com"; 
		
		Response response = given()
		        .contentType(ContentType.HTML)
		        .when()
		        .get("api/deck/"+getDeckId()+"/shuffle/")
		        .then()
		        .extract().response();
		System.out.println("response status of shuffleThedeck="+response.getBody().asPrettyString());
		if(response.statusCode() == 200) {
			return true;
		}
		
		return false;
	}
	
	public boolean drawingPiles() {
		RestAssured.baseURI = "https://deckofcardsapi.com"; 
		
		Response response = given()
		        .contentType(ContentType.HTML)
		        .when()
		        .get("api/deck/"+getDeckId()+"/pile/pile_name/draw/?count=6")
		        .then()
		        .extract().response();
		System.out.println("response status of shuffleThedeck="+response.getBody().asPrettyString());
		if(response.statusCode() == 200) {
			return true;
		}
		
		return false;
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Cards cs=new Cards();
		if(cs.isSiteUp()==true) {
			System.out.println("The site is Up");
			if(cs.getNewDeck()==true) {
				System.out.println("Successfully got the new deck");
				if(cs.shuffleThedeck()==true) {
					System.out.println("Successfully shuffle the cards");
					if(cs.drawingPiles()==true) {
						System.out.println("Drawing from piles");
					} else {
						System.out.println("Not able to drawing from piles");
					}
				} else {
					System.out.println("Not able to shuffle the cards");
				}
			}else {
				System.out.println("Not able to get the new deck");
			}
		} else {
			System.out.println("The site is not Up");
		}
		
	}

}
