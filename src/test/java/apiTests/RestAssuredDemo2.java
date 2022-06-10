package apiTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredDemo2 {


    @BeforeMethod
    public void setUpSessionAPI(){
        // baseURI = "http://localhost:8080/app"; // is only set once in per set or per class
        //Base URI can be placed here to work will all test methods in a class or suite
    }

    /** Parameters ex**/
    @Test(priority=1)
    public void testRestAssured(){
        baseURI = "http://localhost:8080/app";



        given().
                header("Accept","application/json").
                pathParam( "videoGameId","4" ). //passing a value in parameters
        when().log().all().
//              get("/videogames/4"). //path param hardcoded here
                get("/videogames/{videoGameId}"). // the parameter, full path also works here
        then().log().all().
                assertThat(). //assertion method
                statusCode(200). //status code line
                body( "id",equalTo( 4 ) ). //equal to comes from hamcrest library
                body( "name",equalTo( "Super Mario 64") ).
                body( "releaseDate",equalTo( "1996-10-20" ) ).
                body( "reviewScore",equalTo( 90 ) );
        System.out.println("\n Positive test case ran successfully\n");
    }



    /** Query parameters example **/
    @Test
    public void testRestAssuredComplexResponse(){

        baseURI="https://maps.googleapis.com/maps/api";

        given().
                pathParam( "type","json" ).
                queryParam( "input","Duotech Academy" ).
                queryParam( "inputtype","textquery" ).
                queryParam( "key","AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8" ).//hard coding this api key is not to be done here as hard code, should be placed in config file this is like a password.
                queryParam( "fields","formatted_address,name,rating,opening_hours,geometry,photo" ).
        when().log().all().
                get("/place/findplacefromtext/{type}").
        then().log().all().
                     statusCode( 200 );

         //will print out this:



        //SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
        //Request method:	GET
        //Request URI:	https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Duotech%20Academy&inputtype=textquery&key=AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry%2Cphoto
        //Proxy:			<none>
        //Request params:	<none>
        //Query params:	input=Duotech Academy
        //				inputtype=textquery
        //				key=AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8
        //				fields=formatted_address,name,rating,opening_hours,geometry,photo
        //Form params:	<none>
        //Path params:	type=json
        //Headers:		Accept=*/*
        //Cookies:		<none>
        //Multiparts:		<none>
        //Body:			<none>
        //HTTP/1.1 200 OK
        //Content-Encoding: gzip
        //Server: scaffolding on HTTPServer2
        //Content-Length: 628
        //X-XSS-Protection: 0
        //X-Frame-Options: SAMEORIGIN
        //Server-Timing: gfet4t7; dur=427
        //Date: Wed, 01 Jun 2022 00:44:01 GMT
        //Expires: Wed, 01 Jun 2022 00:49:01 GMT
        //Cache-Control: public, max-age=300
        //Content-Type: application/json; charset=UTF-8
        //Age: 31
        //Alt-Svc: h3=":443"; ma=2592000,h3-29=":443"; ma=2592000,h3-Q050=":443"; ma=2592000,h3-Q046=":443"; ma=2592000,h3-Q043=":443"; ma=2592000,quic=":443"; ma=2592000; v="46,43"
        //
        //{
        //    "candidates": [
        //        {
        //            "formatted_address": "2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States",
        //            "geometry": {
        //                "location": {
        //                    "lat": 38.878936,
        //                    "lng": -77.22223799999999
        //                },
        //                "viewport": {
        //                    "northeast": {
        //                        "lat": 38.88025452989272,
        //                        "lng": -77.22103787010727
        //                    },
        //                    "southwest": {
        //                        "lat": 38.87755487010728,
        //                        "lng": -77.22373752989272
        //                    }
        //                }
        //            },
        //            "name": "Duotech Academy",
        //            "opening_hours": {
        //                "open_now": true
        //            },
        //            "photos": [
        //                {
        //                    "height": 562,
        //                    "html_attributions": [
        //                        "<a href=\"https://maps.google.com/maps/contrib/106148087424852379805\">A Google User</a>"
        //                    ],
        //                    "photo_reference": "Aap_uEDWXWDUWuHKoNxOxcO-tC-8_91WHid3fa3QZw81bpD38IDhJWIt-VAPvYXaeY8yDqPvfcHD3oAmy-pHI1YVuEOyStAMuGBxRxMJdAtI5P6GuCXQhMoijlVoXfPCHjcc4S50GjtyXcGCgsRPbjQLGkJBa069wLOVU-3L5asSH2ylTBT7",
        //                    "width": 1477
        //                }
        //            ],
        //            "rating": 5
        //        }
        //    ],
        //    "status": "OK"
        //}
        //
        //===============================================
        //Default Suite
        //Total tests run: 1, Passes: 1, Failures: 0, Skips: 0
        //===============================================
    }

    /** GroovyGPath example **/
    @Test
    public void testRestAssuredComplexResponse2() {


        /** Use JSON Editor Online tool to show where nested array JSON elements are. Makes it easier to access **/

        baseURI = "https://maps.googleapis.com/maps/api";

        given().
                pathParam( "type" ,"json" ).
                queryParam( "input" ,"Duotech Academy" ).
                queryParam( "inputtype" ,"textquery" ).
                queryParam( "key" ,"AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8" ).//hard coding this api key is not to be done here as hard code, should be placed in config file this is like a password.
                queryParam( "fields" ,"formatted_address,name,rating,opening_hours,geometry,photo" ).
        when().log().all().
                get( "/place/findplacefromtext/{type}" ).
        then().log().all().
                statusCode( 200 ).

                //Groovy g path syntax:
                body( "candidates[0].formatted_address",equalTo( "2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States" ) ).
                body( "candidates[0].geometry.location.lat",equalTo( 38.878937F ) ).
                body("candidates[0].rating",equalTo( 5 )).
                body( "candidates[0].photos[0].html_attributions[0]",equalTo( "<a href=\"https://maps.google.com/maps/contrib/106148087424852379805\">A Google User</a>" ) );

        //JSON array access from java:
        //candidates[0].photos[0].html_attributions[0]",equalTo( "<a href=\"https://maps.google.com/maps/contrib/106148087424852379805\">A Google User</a>" ) );
        //candidates[0] is a array with element at position 0
        //photos[0] is a inner array of candidates[0] with a array at position 0
        //html_attributions is the inner most array with html element <a> tag which we use to get.





        /** Groovy G path Notes:
         * Groovy g path is like using xpath **/
        // "candidates[0].formatted_address" -> GroovyGPath syntax
        // body( "candidates",arrayWithSize( equalTo( 2 ) ) );
        //- candidates is the key of a Json array. this is like a variable of a array
        //--candidates[0] is accessing the array called "candidates" at index of 0, first position.
        // This last body is like using xpath or regex.
        // body( "candidates",equalTo( "2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States" ) ); this is a example of using a groovy g path


    }
    }
