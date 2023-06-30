package api.adapter;


import io.restassured.response.*;

import static api.constants.baseUrl;
import static io.restassured.RestAssured.given;


public class ServiceAdapter {

    public static Response createObject(String objectPayloadTest){

        Response createObjectResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(objectPayloadTest)
                        .when()
                        .post(baseUrl)
                        .then()
                        .extract()
                        .response();

        return createObjectResponse;

    }

    public static Response updateObject(String updatePayload,String id){

        Response updateObjectResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(updatePayload)
                        .when()
                        .put(baseUrl +"/"+id)
                        .then()
                        .extract()
                        .response();

        return updateObjectResponse;

    }

    public static Response getObjectList(){

        Response getObjectListResponse =
                given()
                        .when()
                        .get(baseUrl)
                        .then()
                        .extract()
                        .response();

        return getObjectListResponse;

    }

    public static Response getSelectedObjectList(String id1, String id2, String id3){

        Response getSelectedObjectsResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .get(baseUrl+"?id="+id1+"&id="+id2+"&id="+id3)
                        .then()
                        .extract()
                        .response();

        return getSelectedObjectsResponse;

    }

    public static Response getObject(String id1){

        Response getObject =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .get(baseUrl+"/"+id1)
                        .then()
                        .extract()
                        .response();

        return getObject;

    }

    public static Response patchObject(String name,String id){

        Response patchObjectResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body("{\"name\": \""+name+"\"\n"
                                + "}")
                        .when()
                        .patch(baseUrl +"/"+id)
                        .then()
                        .extract()
                        .response();

        return patchObjectResponse;

    }

    public static Response deleteObject(String id1){

        Response deleteObject =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .delete(baseUrl+"/"+id1)
                        .then()
                        .extract()
                        .response();

        return deleteObject;

    }

}
