package simpleBooksApi;

import io.cucumber.java.en.Given;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class requestsPractice {

    static {

        baseURI = "https://simple-books-api.glitch.me";

    }


    List listOfBooks;
    String accessToken = "3c382368b4cbd982d3b016a6f90cc26b1abfde8bc600ed838c1a13d59997c1bf";


    /**
     * This will verify that the status code is 200
     **/
    @Test(enabled = false)
    public void checkStatusAPI() {

        given().
                header( "Connection" ,"keep-alive" ).
                header( "Accept" ,"application/json" ).
                when().log().all().
                get( "/status" ).
                then().log().all().
                assertThat().
                statusCode( 200 ).
                body( "status" ,equalTo( "OK" ) );


    }


    /**
     * This will return the list of books stored in the api
     **/
    @Test
    public void getListOfBooks() {

        given().
                header( "Accept" ,"application/json" ).
                header( "Connection" ,"keep-alive" ).
                when().log().all().
                get( "/books" ).
                then().log().all().
                assertThat().
                statusCode( 200 );

    }


    /** This method will serialize and store a collection of books into a list **/


    /**
     * This will return the list of books stored in the api
     **/
    @Test
    public void serializingListOfBooks() {

        listOfBooks = given().
                header( "Accept" ,"application/json" ).
                header( "Connection" ,"keep-alive" ).
                when().log().all().
                get( "/books" ).
                then().log().all().
                assertThat().
                statusCode( 200 ).extract().as( List.class );

        System.out.println( listOfBooks );

    }


    /**
     * This will return the list of books stored in the api
     **/
    @Test
    public void serializingListOfBooksIntoCustomClassObject() {

        // Saving a object into a list
        List mapCollection = given().
                header( "Accept" ,"application/json" ).
                header( "Connection" ,"keep-alive" ).
                when().log().all().
                get( "/books" ).
                then().log().all().
                assertThat().
                statusCode( 200 ).extract().as( ArrayList.class );

        System.out.println( mapCollection );


/** Performing operations can be done with list class to sort , search, delete, get, update, etc: **/

//Lets look at book5
        String book5 = mapCollection.get( 4 ).toString();

        System.out.println( book5 );

//Now all the books can be displayed or stored in a separate object but to delete that object we need to get rid of it using Delete method


    }

}
