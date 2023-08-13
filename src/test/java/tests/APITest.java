package tests;

import api.PetAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Warning;
import pojo.Pet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Sprite Cloud Test Task")
@Feature("Pets store API")
public class APITest {
    public Pet petBuilder(Long id, String name, String status) {
        return new Pet().setId(id).setName(name).setStatus(status);
    }

    @Step
    public void assertPetEquals(Pet requestPet, Pet responsePet) {
        assertThat(requestPet.getId(), equalTo(responsePet.getId()));
        assertThat(requestPet.getName(), equalTo(responsePet.getName()));
        assertThat(requestPet.getStatus(), equalTo(responsePet.getStatus()));
    }

    @Step
    public void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode(), equalTo(expectedStatusCode));
    }

    @Step
    public void assertWarning(int code, String type, String message, Warning responseWarning) {
        assertThat(code, equalTo(responseWarning.getCode()));
        assertThat(type, equalTo(responseWarning.getType()));
        assertThat(message, equalTo(responseWarning.getMessage()));
    }

    @Story("Add pet to the store")
    @Link("https://example.com")
    @Link(name = "allure", type = "myLink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("This is test description")
    @Test(dependsOnMethods="getPetError")
    public void addPet() {
        Pet requestPet = petBuilder(6666666666666666666L, "Funtik", "pending");
        Response response = PetAPI.post(requestPet);
        assertStatusCode(response, 200);
        Pet responsePet = response.as(Pet.class);
        assertPetEquals(requestPet, responsePet);
    }

    @Test(dependsOnMethods="addPet")
    public void updatePet() {
        Pet requestPet = petBuilder(6666666666666666666L, "Richard", "available");
        Response response = PetAPI.put(requestPet);
        assertStatusCode(response, 200);
        Pet responsePet = response.as(Pet.class);
        assertPetEquals(requestPet, responsePet);
    }

    @Test(dependsOnMethods="updatePet")
    public void getPet() {
        Pet requestPet = petBuilder(6666666666666666666L, "Richard", "available");
        Response response = PetAPI.get("6666666666666666666");
        assertStatusCode(response, 200);
        Pet responsePet = response.as(Pet.class);
        assertPetEquals(requestPet, responsePet);
    }

    @Test(dependsOnMethods="getPet")
    public void deletePet() {
        Response response = PetAPI.delete("6666666666666666666");
        assertStatusCode(response, 200);
        Warning responseWarning = response.as(Warning.class);
        assertWarning(200, "unknown", "6666666666666666666", responseWarning);
    }

    @Test
    public void getPetError() {
        Response response = PetAPI.get("6666666666666666666");
        assertStatusCode(response, 404);
        Warning responseWarning = response.as(Warning.class);
        assertWarning(1, "error", "Pet not found", responseWarning);
    }
}