package com.chapter7.class3;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class login1 {

    public static String token1="";
    public static String uid1="";

    public static RequestSpecification requestSpecification;

    @BeforeClass
    public static void login1(){
        useRelaxedHTTPSValidation();
/*        Response response=
                given()
                        .log().all()
                        .formParam("telephone","18500976204")
                        .formParam("password","")
                        .formParam("grant_type","password")
                        .formParam("x","1.18")
                        .formParam("client_secret","txsDfr9FphRSPov5oQou74")
                        .formParam("areacode","86")
                        .formParam("client_id","JtXbaMn7eP")
                        .formParam("sid","1HUAWEI5be56f3f97b527d917615f8385e4b286")
                        .cookie("xq_a_token","1e7899c8e351722340aea07406c37c858bc4a8d2")
                        .cookie("u","6587643557")
                .when()
                        .post("https://api.xueqiu.com/provider/oauth/token?_t=1HUAWEI5be56f3f97b527d917615f8385e4b286.6587643557.1533628993153.1533629622096&_s=20a134")
                .then()
                        .log().all()
                        .extract().response()
                ;

        token1=response.path("access_token");
        uid1=response.path("uid");
*/

        token1="cfa0bb8069a80ab5524150312daca497933a9535";
        uid1="7628838121";

        RequestSpecBuilder reqBuilder=new RequestSpecBuilder();
        reqBuilder.addCookie("xq_a_token",token1);
        reqBuilder.addCookie("u",uid1);

        requestSpecification=reqBuilder.build();

    }
}
