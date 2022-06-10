package apiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {


    /** To access the api for testing and practicing use: http://localhost:8080/swagger-ui/index.html#/**/

    /** Writing RestAssured allows us to write chain methods which is why we needed a static import.**/
    /* ERROR CODES:

      "2**" 200 codes means it was accepted and ran successfully
      "4**" 400 level means it was users error that caused error
      "5**" 500 level means servers are broken


    */



    @Test(priority=1)
    public void testRestAssured(){
        //rest assured library can be easily used in TDD style test
        // Imported into your pom file by getting the dependency through maven repo
        // Imported the static packages to easily write code.
        //Rest assured uses similar framework of cucumber and gherkin by using "Given, when and then"


 //--------------------------------------------------------------------------------------------------------------------------------------------------------------

        /** Steps: **/

        //1. Set the base URI or "url":
        baseURI = "http://localhost:8080/app";

//-----------------------------------------------------------------------------------------------------------------------------------------------------------
        //2.

        //given- request specifications added here such as headers, parameters, authorization, body, etc...
        //when- these are parameters or requests
        //then - assertions are done here

        /** syntax to writing rest assured json **/
        //This is called method chaining
//
//        given().
//                 header("Accept","application/json").
//        when().
//                get("/videogames/4").
////"GET /videogames/{videoGameId}" or using:"/videogames/4" will get you the specific video game at id =4
//        then().
//                assertThat().
//                      statusCode(200);
//    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------

//Syntax to verify that information
    given().
            header("Accept","application/json").
    when().log().all().
            get("/videogames/4").
    then().log().all().
            assertThat(). //assertion method
            statusCode(200). //status code line
            body( "id",equalTo( 4 ) ). //equal to comes from hamcrest library
            body( "name",equalTo( "Super Mario 64") ).
            body( "releaseDate",equalTo( "1996-10-20" ) ).
            body( "reviewScore",equalTo( 90 ) );
        System.out.println("\n Positive test case ran successfully\n");
}


    @Test(priority = 2)
    public void negativeTestCase(){
        try {
            given().
                header("Accept","application/json").
            when().log().all().
                get("/videogames/4").
            then().log().all().
                assertThat(). //assertion method
                statusCode(201). //status code line
                body( "id",equalTo( 4 ) ). //equal to comes from hamcrest library
                body( "name",equalTo( "Super Mario 64") ).
                body( "releaseDate",equalTo( "1996-10-20" ) ).
                body( "reviewScore",equalTo( 89) );

        }catch (AssertionError e){
            System.out.println(e.getMessage());
        }
        System.out.println("\nNegative test case ran successfully\n");




}

/** testing more headers and cookies, etc....... **/
//    @Test
//    public void verifyingHeaderValues(){
//        /** Verifies content headers **/
//        //To verify another header, go into post man check under Headers on the bottom bar above terminal and check what does it have
//        // To check for content-length is done normally unless its a dynamic value but in this example we will check it
//        //dates are dynamic so it will change everytime you run a new test, this is renewed every day
//
//
//
////
////        given().
////                header("Accept","application/json").
////        when().log().all().
////                get("/videogames/4").
////        then().log().all().
////                assertThat(). //assertion method
////                statusCode(equalTo( 200 )). //status code line
////                body( "id",equalTo( 4 ) ). //equal to comes from hamcrest library
////                body( "name",equalTo( "Super Mario 64") ).
////                body( "releaseDate",equalTo( "1996-10-20" ) ).
////                body( "reviewScore",equalTo( 90 ) ).
////                header( "Content-Type",equalTo( "application/json" ) ).
////                header("Content-Length",equalTo( "119" )).
////                header("Date",containsString( "Tue, 31 May 2022" ));
                   /** Cookies can also be verified  **/


////
////        System.out.println("\n Positive test case ran successfully\n");
//
//}

    /** Testing time in api / testing api speed **/
//    @Test
//    public void testTime(){
//
//        given().
//                header("Accept","application/json").
//        when().log().all().
//                get("/videogames/4").
//        then().log().all().
//                assertThat().
//                  statusCode(equalTo( 200 )).
//                  body( "id",equalTo( 4 ) ).
//                  body( "name",equalTo( "Super Mario 64") ).
//                  body( "releaseDate",equalTo( "1996-10-20" ) ).
//                  body( "reviewScore",equalTo( 90 ) ).
//                  time( lessThan( 1000L ) ); //This checks to see if the api is less than 500 ms
//
//
//        /** The time() method can be used to check to see if API is performing in the right amount of time. **/
//
//        System.out.println("\n Positive test case ran successfully\n");
//
//    }






    /** What to test if conducting API testing?
     *
     * 1. Verify that correct tHTTP status code.
     *   - ex: Creating a resource should return a 201 CREATED and permitted requests should return a 403 FORBIDDEN.
     *
     * -----------------------------------------------------------------------------------------------------------------------
     * 2. Verify response payload.
     *   -Check valid JSON body and correct field names, types, and values.
     *
     *   - Example of the response body is the exact code:
     *
     *   {
     *     "id": 0,
     *     "name": "string",
     *     "releaseDate": "2022-05-31T22:51:38.858Z",
     *     "reviewScore": 0,
     *     "category": "string",
     *     "rating": "string"
     *   }
     *
     *   --Check to see if id matches the id value, etc....**
     *
     * ------------------------------------------------------------------------------------------------------------------------
     *
     * 3. Verify the header.
     * Http servers headers have implications on both security and performance.
     *
     * ------------------------------------------------------------------------------------------------------------------------
     *
     * 4. Verify correct application state.
     * -This is optional and applies mainly to manual testing, or when a UI or another interface can be deployed.
     *
     * ------------------------------------------------------------------------------------------------------------------------
     *
     * 5. Verify basic performance sanity.
     * - If a operation was completed successfully but took an unreasonable amount of time, the test fails.
     *
     *
     *
     *
     * Notes: Other than headers, body, id's, names, etc
     *  -- time can also be tested as we can check how many milli seconds have past as api needs to be fast enough so that the
     *  customer or client is not annoyed or angered by such long waiting times.
     * **/
}
