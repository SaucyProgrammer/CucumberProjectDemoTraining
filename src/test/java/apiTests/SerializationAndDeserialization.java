package apiTests;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SerializationAndDeserialization {

    static {
        baseURI = "http://localhost:8080/app";
    }

    int id;


    @Test
    public void testPostRequests() {

        //Instead of creating it like this, we can use maps to create it!
        //  body("{\n" +
        //                        "  \"id\": 15,\n" +
        //                        "  \"name\": \"Skyrim\",\n" +
        //                        "  \"releaseDate\": \"2011-11-11T19:47:53.790Z\",\n" +
        //                        "  \"reviewScore\": 99,\n" +
        //                        "  \"category\": \"RPG\",\n" +
        //                        "  \"rating\": \"10\"\n" +
        //                        "}" ).


        /** Creating the body for your api object: **/

        //Video game is a java map object that can be used to interact with the api to send and receive data

        int id = 100+ new Random().nextInt(1000);

        Map<String, Object> videoGameMap = new LinkedHashMap<>();
        videoGameMap.put( "id" ,id); //id has to be dynamic
        videoGameMap.put( "name" ,"Tetris" );
        videoGameMap.put( "releaseDate" ,"1984-06-25" );
        videoGameMap.put( "reviewScore" ,88 );
        videoGameMap.put( "category" ,"Puzzle" );
        videoGameMap.put( "rating" ,"Universal" );


        given().
                header( "Accept" ,"application/json" ).
                header( "Content-Type" ,"application/json" ).
                body( videoGameMap ).
        when().log().all().
                post( "/videogames" ). //to make a post request we must use the post() with uri of the end point
        then().log().all().
                assertThat().
                statusCode( 200 ).
                header( "Content-Type" ,equalTo( "application/json" ) ).
                body( "status" ,containsString( "Successfully" ) );



        /** Serialization doesn't just need to encompass maps but also Classes! As classes are also objects in java.**/
    }



    @Test
    public void testPostPOJOSerialization() {

        //Using a java class to create the body



         id = 100+ new Random().nextInt(1000);

        VideoGame videoGame = new VideoGame( id,"Modern Warfare","2020-11-19",91,"FPS","8.5" );



      String videoGamePojo =   given().
                header( "Accept" ,"application/json" ).
                header( "Content-Type" ,"application/json" ).

                body( videoGame ). //passing the JAVA POJO as the body
                when().log().all().
                post( "/videogames" ). //to make a post request we must use the post() with uri of the end point
                then().log().all().
                assertThat().
                statusCode( 200 ).
                extract().asString();

        System.out.println(videoGamePojo);

        /** Serialization doesn't just need to encompass maps but also Classes! As classes are also objects in java.**/


        //Output:

        //{
        //    "id": 866,
        //    "name": "Modern Warfare",
        //    "releaseDate": "2020-11-19",
        //    "reviewScore": 91,
        //    "category": "FPS",
        //    "rating": "8.5"
        //}
        //HTTP/1.1 200
        //Content-Type: application/json
        //Content-Length: 39
        //Date: Fri, 03 Jun 2022 00:56:30 GMT
        //
        //{
        //    "status": "Record Added Successfully"
        //}
        //{"status": "Record Added Successfully"}
        //
        //===============================================
        //Default Suite
        //Total tests run: 1, Passes: 1, Failures: 0, Skips: 0
        //===============================================
        //
        //
        //Process finished with exit code 0

    }


    //--------------------------------------------------------------------------------------------------------------------------------------------

    /** Deserialzation is is opposite process of Serialization. As you are not sending values into the body but removing. **/
    //return response is converted back into Java Object.


    @Test
    public void deserializeUsingMap(){

        /** Successful test to deserialize the object **/


        /** Converting back into map **/



        VideoGame videoGame = new VideoGame( 5,"Modern Warfare","2020-11-19",91,"FPS","8.5" );




        /** To extract the entire response as a map **/

//A. Save the POJO to be converted from api into Java Object map (generic object map):


   Map map =     given().
                                    header("Accept","application/json").
                                    header( "Content-Type", "application/json"  ).

                                    body(videoGame).
                                    pathParam( "videoGameId","5" ).
                when().log().all().
                                    put( "/videogames/{videoGameId}").//B. This is where the new JSON body will be posted to
                then().log().all().

                                    statusCode(200).
                                                //NOw to extract the POJO body we need to extract it into some type.
                                                        body( "id",equalTo( 5 ) ).
                                                header( "Content-Type",equalTo( "application/json" ) ).extract().as( Map.class );//C. where extracting will occur

                                                    //D.Printout

                                                    System.out.println(map);

                                                    System.out.println(map.get( "name" ));
    }




    @Test
    public void deserializeUsingPOJO(){

        /** Successful test to deserialize the object in a Java Class not a variable **/


        /** Converting back into map **/



        VideoGame videoGame = new VideoGame( 5,"Modern Warfare","2020-11-19",91,"FPS","8.5" );




        /** To extract the entire response as a map **/

//A. Save the POJO to be converted from api into Java Object map (generic object map):


     VideoGame responseAsAVideoGameCLass  =     given().
                header("Accept","application/json").
                header( "Content-Type", "application/json"  ).

                body(videoGame).
                pathParam( "videoGameId","5" ).
                when().log().all().
                put( "/videogames/{videoGameId}").//B. This is where the new JSON body will be posted to
                then().log().all().

                statusCode(200).

                        header( "Content-Type",equalTo( "application/json" ) ).extract().as( VideoGame.class );//C. where extracting will occur

        //D.Printout

        System.out.println(responseAsAVideoGameCLass);

        System.out.println(responseAsAVideoGameCLass);
        System.out.println(responseAsAVideoGameCLass.getName());

    }




}
