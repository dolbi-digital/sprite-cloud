package api;

import io.restassured.response.Response;
import pojo.Pet;

import static api.SpecBuilder.getRequestSpec;
import static api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PetAPI {
    //String baseUri = "https://petstore.swagger.io/v2/pet/";
    String petId = "6666666666666666666";
    public static Response post(Pet requestPet) {
        return given(getRequestSpec()).
                body(requestPet).
                when().post().
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response put(Pet requestPet) {
        return given(getRequestSpec()).
                body(requestPet).
                when().put().
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String petId) {
        return given(getRequestSpec()).
                basePath(petId).
                when().get().
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response delete(String petId) {
        return given(getRequestSpec()).
                basePath(petId).
                when().delete().
                then().spec(getResponseSpec()).
                extract().response();
    }
}