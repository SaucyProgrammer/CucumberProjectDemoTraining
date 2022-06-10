package apiTests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.Base64;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Authentication {

    static {

        baseURI = "https://maps.googleapis.com/maps/api";
    }

    /** External API Authorization:
     *
     * For external authorization we can use Bearer Token or OAuth 2.0
     *
     *
     * OAuth2:
     *
     * syntax:
     *
     * - given().auth().oauth2(accessToken)...
     *
     * This will put oAuth2 accessToken in Header. to be more explicit you can also do:
     *
     * - given().auth().preemptive().oauth2(accessToken)...
     *
     * ex:
     *
     * OAuth can be used in a scenario of if you login into Gmail you will have to access through your phone with a authorization api key that will be sent to the server
     * requests can be sent right away and it will send a secret key to your temporary key. This is 2 requests or 2 authorizations.
     *
     * **/





    /**
     *
     * Internal API Authorization
     *
     * - Basic Authentication:
     *
     * Is a lightweight security mechanism used commonly in the internal API's.
     *
     * Using this type of method, the sender places a "username:password" into the request header.
     *
     * The username and password are encoded with Base64, which is an encoding technique that converts the username and password into a set of 64 characters to ensure
     * safe transmission.
     *
     * ex:                   "Authorization: Basic b69Gs74vg284ZQpr3=="
     *
     *
     *
     * - Bearer Token is very similar to Basic Authorization, just replace with Bearer.
     **/




    @Test
    public void authResponse() {


        given().
                pathParam( "type" ,"json" ).
                queryParam( "input" ,"Duotech Academy" ).
                queryParam( "inputtype" ,"textquery" ).
                queryParam( "key" ,"AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8" ).//If this key is removed then it will not work!
                //API KEY is usually static and will not change
                //Api keys are aka "tokens"
                //To authenticate we need a api key. The Key can be set as part of the parameter or a query parameter for authorization

                        when().log().all().
                get( "/place/findplacefromtext/{type}" ).
                then().log().all().
                statusCode( 200 );


    }



    @Test
    public void basicAuthentication(){
        given().
                auth(). //in rest assured you want to use this auth, only use that when in postman it shows you need to deal with it
                basic( "postman","password" ). //This is where you place the basic authentication username and password method!
        when().log().all().
                get("https://postman-echo.com/basic-auth"). //the URI
        then().log().all().
                statusCode( 200 ).
                body( "authenticated",equalTo( true ) );



    }

    @Test
    public void basicAuthenticationExample2(){
        given().
//                auth().
//                basic( "postman","password" ).
                /** Lets say now that there is a header and your api authorization is dynamic we will have to declare as a header like this: **/

                header( "Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==").
                /**
                 * "cG9zdG1hbjpwYXNzd29yZA==" is basic( "postman","password" ). but encoded!
                 * you can go to baseg4decode.org to show you the username and password!!!!!!!
                 *
                 *
                 * This "base64" code must be hardcoded into java for rest assured if it is a dynamic header meaning if it keeps changing.
                 * **/


                when().log().all().
                get("https://postman-echo.com/basic-auth").
                then().log().all().
                statusCode( 200 ).
                body( "authenticated",equalTo( true ) );



    }


    //IMPORTANT: *****
    /** This method will show basic authentication but with Base64 class demo to encode your username and password **/
    @Test
    public void basicAuthenticationExample3(){

        //syntax:

        String basicAuth = Base64.getEncoder().encodeToString( "postman:password".getBytes() ); //this uses the method to use "username:password","username:password" these are given to you by development team!

        given().


               // header( "Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA=="). //HARDCODED STATIC

                       header( "Authorization", "Basic " + basicAuth). //SAVED INTO A VALUE IF ITS DYNAMIC

                /** THIS DEALS WITH DYNAMIC API KEYS SO IF YOU DON'T HAVE A STATIC VALUE API KEY, IF IT KEEPS CHANGING THIS IS HOW YOU GET IT**/

        when().log().all().
                get("https://postman-echo.com/basic-auth").
        then().log().all().
                statusCode( 200 ).
                body( "authenticated",equalTo( true ) );



    }


}
