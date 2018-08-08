package com.chapter7.class3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

@RunWith(Parameterized.class)
public class stocks1 extends login1 {

    @Parameterized.Parameters(name="{4}")
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {2,1,1000,"INDEXNASDAQ","全部股票"},
                {2,5,1000,"SZ","沪深"},
                {2,6,1000,"NYSE","美股"},
                {2,7,1000,"HK","港股"}
        });
    }

    @Parameterized.Parameter
    public Integer category;
    @Parameterized.Parameter(1)
    public Integer type;
    @Parameterized.Parameter(2)
    public Integer size;
    @Parameterized.Parameter(3)
    public String exchange;
    @Parameterized.Parameter(4)
    public String testName;

    @Test
    public void test1(){

        given()
                .log().all().spec(requestSpecification)
                .cookie("xq_a_token",token1).cookie("u",uid1)
                .queryParam("category",category)
                .queryParam("type",type)
                .queryParam("size",size)
                .log().all()
        .when()
                .get("https://api.xueqiu.com/v4/stock/portfolio/stock_list.json")
        .then()
                .log().all().statusCode(200)
                .body("stocks.size()",greaterThan(1))
                .body("stocks.exchange",hasItem(exchange));
    }
}
