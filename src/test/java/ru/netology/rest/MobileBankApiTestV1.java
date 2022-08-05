package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200);
    }
    @Test
    void shouldReturnDemoAccountsContentType() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .contentType(ContentType.JSON);
    }
    @Test
    void shouldReturnDemoAccountsBody() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("", hasSize(3));
    }
    @Test
    void shouldReturnDemoAccountsBodyCurrency() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].currency", equalTo("RUB"));
    }
    @Test
    void shouldReturnDemoAccountsBodyBalance() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[1].balance", greaterThanOrEqualTo(0));
    }
    @Test
    void shouldReturnDemoAccountsBodyBalancePositive() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body( "every{ it.balance >= 0 }", is(true));
    }
    @Test
    void shouldReturnDemoAccountsJsonSchema() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}
