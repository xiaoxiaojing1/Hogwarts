package com.chapter7.class3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class stocksDeleteAdd extends login1 {

    @Parameterized.Parameters
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"YRD"},
                {"PDD"}
        });
    }

    @Parameterized.Parameter
    public String code;

    @Test
    public void stockDelete(){
        given()
                .log().all()
                .cookie("xq_a_token",token1).cookie("u",uid1)
                .formParam("symbol",code)
        .when()
                .post("https://api.xueqiu.com/v4/stock/portfolio/delstock.json")
        .then()
                .log().all().statusCode(200)
                .body("success",equalTo(true));

        given().spec(requestSpecification)
                .queryParam("category",2)
                .queryParam("type",6)
                .queryParam("size",1000)
                .log().all()
        .when()
                .get("https://api.xueqiu.com/v4/stock/portfolio/stock_list.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("stocks.code",not(hasItem(code)));
    }


    @Test
    public void stockAdd(){
        given().log().all().spec(requestSpecification)
                .formParam("symbol","PDD")
                .formParam("category",2)
                .when()
                .post("https://api.xueqiu.com/v4/stock/portfolio/addstock.json")
                .then()
                .log().all().statusCode(200)
                .body("success",equalTo(true));

        given().log().all().spec(requestSpecification)
                .queryParam("category",2)
                .queryParam("type",6)
                .queryParam("size",1000)
                .when()
                .get("https://api.xueqiu.com/v4/stock/portfolio/stock_list.json")
                .then()
                .log().all().statusCode(200)
                .body("stocks.code",hasItem("PDD"));

    }
}
