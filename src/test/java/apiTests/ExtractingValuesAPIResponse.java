package apiTests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;


public class ExtractingValuesAPIResponse {

static {

    baseURI = "https://maps.googleapis.com/maps/api";
}





    @Test
    public void extractValuesFromResponse() {
    //In this method we are trying to get the place_id and extracting it

        // "place_id": "ChIJw1TOzP9LtokRyxvViNtMsWk"

    /** IMPORTANT: **/
    //You can store the entire JSON as a String variable!
        //- meaning if you wanted to you can also perform string operations on this!!!!!!!!!!!

        /** the better way to extract is using the JSON .jsonPath();
         * - behind the scenes it uses Groovy g path to store it.
         * -syntax:
         *
         *    .extract().jsonPath();
         **/

        JsonPath responseBodyAsJsonPath=  given().
                pathParam( "type" ,"json" ).
                queryParam( "input" ,"Duotech Academy" ).
                queryParam( "inputtype" ,"textquery" ).
                queryParam( "key" ,"AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8" ).

         when().log().all().
                get( "/place/findplacefromtext/{type}" ).
         then().log().all().
                statusCode( 200 ).extract().jsonPath();



        //JSON Path behind the scenes uses Groovy G Path to access the place_id similar to Xpath!
        String place_id = responseBodyAsJsonPath.getString( "candidates[0].place_id");



        /** Now since we extracted this place id and we stored as a String variable, meaning we can use it in our other methods for later use!
         * Or we can use it for our next api call. **/

        System.out.println("\n Extracted Place id: "+place_id);


                 }

}
