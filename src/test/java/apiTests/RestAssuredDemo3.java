package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo3 {

    static {
        baseURI = "http://localhost:8080/app";
    }

    @Test(enabled = false)
    public void testPut(){


        given().
                header("Accept","application/json").
                header( "Content-Type", "application/json"  ).
                        body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"name\": \"Need for speed\",\n" +
                        "  \"releaseDate\": \"2010-03-10\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"Driving\",\n" +
                        "  \"rating\": \"Universal\"\n" +
                        "}" ).
                pathParam( "/videogames","2").
        when().log().all().
                put("/videogames/{videoGameId}").

        then().log().all(). //The verifying methods happen under the then(). ****
                statusCode(200).
                body( "id",equalTo( 2 ) ).
                header( "Content-Type",equalTo( "application/json" ) );



    }

    @Test
    public void testDelete(){

        //In this we will test what kind of response we will get but not send any body.


        given().
                header("Accept","application/json").
                pathParam( "videoGameId","2").
        when().log().all().
                delete("/videogames/{videoGameId}").
        then().log().all().
                statusCode(is(200  )).
                body( "status",equalTo( "Record Deleted Successfully" ) ).
                header( "Content-Type",equalTo( "application/json" ) );



    }
}
