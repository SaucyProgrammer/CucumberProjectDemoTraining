package apiTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredDifferentTypes {

    @BeforeMethod
    public void setUpTests(){
        baseURI = "http://localhost:8080/app";
    }

    @Test(enabled = false) //Since you already posted one, now you must stop running this same post test as it will cause a error status
    public void testPostRequests(){
        //This post will create a new video game to our api to be viewed by the ui for later:

        given().
                header("Accept","application/json").
                header( "Content-Type", "application/json"  ).
                /** When creating a new post item, you must place the body in here as you are sending the request to be made.**/
                //Use this way as copy and paste into here where you can hard code a new post here, only with simple design like this
                body("{\n" +
                        "  \"id\": 15,\n" +
                        "  \"name\": \"Skyrim\",\n" +
                        "  \"releaseDate\": \"2011-11-11T19:47:53.790Z\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"RPG\",\n" +
                        "  \"rating\": \"10\"\n" +
                        "}" ).
        when().log().all().
                post( "/videogames"). //to make a post request we must use the post() with uri of the end point
        then().log().all().
                assertThat().
                statusCode(200).
                   header( "Content-Type",equalTo( "application/json" ) ).
                   body( "status",containsString(  "Successfully" ) );



    }


    @Test
    public void getSkyrim(){
        given().
                header("Accept","application/json").
        when().log().all().
                get("/videogames/15").
        then().log().all().
                assertThat(). //assertion method
                statusCode(200). //status code line
                body( "id",equalTo( 15 ) ). //equal to comes from hamcrest library
                body( "name",equalTo( "Skyrim") ).
                body( "releaseDate",equalTo( "2011-11-11" ) ).
                body( "reviewScore",equalTo( 99 ) );
        System.out.println("\n Positive test case ran successfully\n");
    }
}
