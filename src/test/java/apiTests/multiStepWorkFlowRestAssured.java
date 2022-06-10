package apiTests;

import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class multiStepWorkFlowRestAssured {

    static {
        baseURI = "http://localhost:8080/app";

    }

    //Example of API Horizontal testing:
    /** Create a new video game  use POST
     * Then verify if it has been created, use GET
     * update the same video game with a different name use PUT
     * verify that the game updated with GET
     * Delete the same video game
     * Verify with GET
     **/

    @Test
    public void testMultiFlowRequests(){
        //This post will create a new video game to our api to be viewed by the ui for later:

        int id = 100+ new Random().nextInt(1000); //random unique id

        given().
                header("Accept","application/json").
                header( "Content-Type", "application/json"  ).
        body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"Skyrim\",\n" +
                        "  \"releaseDate\": \"2011-11-11\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"RPG\",\n" +
                        "  \"rating\": \"10\"\n" +
                        "}" ).
        when().log().all().
                post( "/videogames"). //This is where the new JSON body will be posted to
        then().log().all().
                assertThat().
                statusCode(200).
                header( "Content-Type",equalTo( "application/json" ) ).
                body( "status",containsString(  "Successfully" ) );




       //this will get the game
        given().
                header("Accept","application/json").
                pathParam( "videoGameId",id ).
        when().log().all().
                get("/videogames/{videoGameId}").
        then().log().all().
                assertThat().
                statusCode(200).
                body( "id",equalTo( id ) );






        //Now update the game
        given().
                header("Accept","application/json").
                header( "Content-Type", "application/json"  ).
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"Modern Warfare\",\n" +
                        "  \"releaseDate\": \"2011-11-11\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"RPG\",\n" +
                        "  \"rating\": \"10\"\n" +
                        "}" ).
                pathParam( "videoGameId",id).
                when().log().all().
                put("/videogames/{videoGameId}").

                then().log().all(). //The verifying methods happen under the then(). ****
                statusCode(200).
                body( "id",equalTo( id ) ).
                body( "name",equalTo( "Modern Warfare" ) ).
                header( "Content-Type",equalTo( "application/json" ) );




        //verify the game again with a new name
        given().
                header("Accept","application/json").
                pathParam( "videoGameId",id ).
        when().log().all().
                get("/videogames/{videoGameId}").
        then().log().all().
                assertThat().
                statusCode(200).
                body( "name",equalTo( "Modern Warfare" ) );



        //then delete the game
        given().
                header("Accept","application/json").
                pathParam( "videoGameId",id).
                when().log().all().
                delete("/videogames/{videoGameId}").
                then().log().all().
                statusCode(is(200  )).
                body( "status",equalTo( "Record Deleted Successfully" ) ).
                header( "Content-Type",equalTo( "application/json" ) );





        //this will get the game
        given().
                header("Accept","application/json").
                pathParam( "videoGameId",id ).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(500); //using status code 500 will make sure we verified that the game does not exist

    }










}
