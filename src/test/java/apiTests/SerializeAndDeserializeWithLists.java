package apiTests;

import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SerializeAndDeserializeWithLists {

    static {
        baseURI = "http://localhost:8080/app";
    }

    @Test
    public void deserializeAsList(){
    List list =  given().
           header( "Accept","application/json" ).

       when().log().all().
            get("/videogames").
       then().log().all().
            assertThat().
               statusCode( equalTo( 200 ) ).extract().as( List.class );

        System.out.println(list.get( 4 ));

    }


    @Test
    public void deleteModernWar(){

        given().
                header( "Accept","application/json" ).
                pathParam( "videoGameId",4 ).
        when().log().all().
                delete("/videogames/{videoGameId}").
                then().log().all().
                statusCode(is(200  )).
                body( "status",equalTo( "Record Deleted Successfully" ) ).
                header( "Content-Type",equalTo( "application/json" ) );


    }


}
