package api.tests;

import api.adapter.*;
import api.payload.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import io.restassured.response.*;
import org.testng.*;
import org.testng.annotations.*;

public class objectTests {

    private static String objectName;
    private static int year;
    private Double objectPrice;
    private String cpuModel;
    private String diskSize;
    private String objectColor;
    private  String id;

    @AfterTest()
    public void deleteObject(){
        ServiceAdapter.deleteObject(id);
    }


    @Test()
    public void testCreateObject() throws JsonProcessingException {
        objectName = "Apple MacBook Pro 16";
        year = 2019;
        objectPrice = 1849.99;
        cpuModel = "Intel Core i9";
        diskSize = "1 TB";

        ObjectPojo objectPojo = new ObjectPojo();
        objectPojo.setName(objectName);

        ObjectDataPojo data = new ObjectDataPojo();
        data.setYear(year);
        data.setPrice(objectPrice);
        data.setCPU_model(cpuModel);
        data.setHard_disk_size(diskSize);
        objectPojo.setData(data);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo);

        //Create Object
        Response objectCreateResponse = ServiceAdapter.createObject(jsonPayload);
        id = objectCreateResponse.jsonPath().getString("id");
        objectCreateResponse.then().log().all();

        Assert.assertEquals(objectCreateResponse.statusCode(),200);
        Assert.assertEquals(objectCreateResponse.jsonPath().getString("name"),objectName);
        Assert.assertEquals(objectCreateResponse.jsonPath().getString("data.year"),String.valueOf(year));
        Assert.assertEquals(objectCreateResponse.jsonPath().getString("data.price"),String.valueOf(objectPrice));
        Assert.assertEquals(objectCreateResponse.jsonPath().getString("data.cpu_model"),cpuModel);
        Assert.assertEquals(objectCreateResponse.jsonPath().getString("data.hard_disk_size"),diskSize);
    }

    @Test()
    public void testUpdateObject() throws JsonProcessingException {
        objectName = "Apple MacBook Pro 16";
        year = 2019;
        objectPrice = 1849.99;
        cpuModel = "Intel Core i9";
        diskSize = "1 TB";
        objectColor = "silver";

        ObjectPojo objectPojo1 = new ObjectPojo();
        objectPojo1.setName(objectName);

        ObjectDataPojo data1 = new ObjectDataPojo();
        data1.setYear(year);
        data1.setPrice(objectPrice);
        data1.setCPU_model(cpuModel);
        data1.setHard_disk_size(diskSize);
        objectPojo1.setData(data1);

        ObjectPojo objectPojo2 = new ObjectPojo();
        objectPojo2.setName(objectName);

        ObjectDataPojo data2 = new ObjectDataPojo();
        data2.setYear(year);
        data2.setPrice(objectPrice);
        data2.setCPU_model(cpuModel);
        data2.setHard_disk_size(diskSize);
        data2.setColor(objectColor);
        objectPojo2.setData(data2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo1);

        //Create Object
        Response objectCreateResponse = ServiceAdapter.createObject(jsonPayload);
        id = objectCreateResponse.jsonPath().getString("id");

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonPayload1 = objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo2);

        //Update Object
        Response objectUpdateResponse = ServiceAdapter.updateObject(jsonPayload1,id);
        objectUpdateResponse.then().log().all();

        Assert.assertEquals(objectUpdateResponse.statusCode(),200);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("name"),objectName);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.year"),String.valueOf(year));
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.price"),String.valueOf(objectPrice));
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.cpu_model"),cpuModel);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.hard_disk_size"),diskSize);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.color"),objectColor);
    }

    @Test()
    public void testGetObjectListsObject(){

        Response objectListGetResponse = ServiceAdapter.getObjectList();
        objectListGetResponse.then().log().all();

        Assert.assertEquals(objectListGetResponse.statusCode(),200);
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[0].id"),"1");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[0].name"),"Google Pixel 6 Pro");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[0].data.color"),"Cloudy White");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[0].data.capacity"),"128 GB");

        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[1].id"),"2");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[1].name"),"Apple iPhone 12 Mini, 256GB, Blue");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[1].data"),null);

        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[2].id"),"3");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[2].name"),"Apple iPhone 12 Pro Max");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[2].data.color"),"Cloudy White");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[2].data.'capacity GB'"),"512");

        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[12].id"),"13");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[12].name"),"Apple iPad Air");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[12].data.Generation"),"4th");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[12].data.Price"),"519.99");
        Assert.assertEquals(objectListGetResponse.jsonPath().getString("[12].data.Capacity"),"256 GB");

    }

    @Test()
    public void testGetSelectedObjectListsObject() {

    Response selectedObjectListGetResponse = ServiceAdapter.getSelectedObjectList("3", "5", "10");
    selectedObjectListGetResponse.then().log().all();

    Assert.assertEquals(selectedObjectListGetResponse.statusCode(), 200);
    Assert.assertEquals(selectedObjectListGetResponse.jsonPath().getString("[0].id"), "3");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[0].name"), "Apple iPhone 12 Pro Max");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[0].data.color"), "Cloudy White");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[0].data.'capacity GB'"), "512");

    Assert.assertEquals(selectedObjectListGetResponse.jsonPath().getString("[1].id"), "5");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[1].name"), "Samsung Galaxy Z Fold2");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[1].data.price"), "689.99");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[1].data.color"), "Brown");

    Assert.assertEquals(selectedObjectListGetResponse.jsonPath().getString("[2].id"), "10");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[2].name"), "Apple iPad Mini 5th Gen");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[2].data.Capacity"), "64 GB");
    Assert.assertEquals(
        selectedObjectListGetResponse.jsonPath().getString("[2].data.'Screen size'"), "7.9");
  }

    @Test()
    public void testGetSingleObject() {
    Response objectGetResponse = ServiceAdapter.getObject("7");
    objectGetResponse.then().log().all();

    Assert.assertEquals(objectGetResponse.statusCode(), 200);
    Assert.assertEquals(objectGetResponse.jsonPath().getString("id"), "7");
    Assert.assertEquals(objectGetResponse.jsonPath().getString("name"), "Apple MacBook Pro 16");
    Assert.assertEquals(objectGetResponse.jsonPath().getString("data.year"), "2019");
    Assert.assertEquals(objectGetResponse.jsonPath().getString("data.price"), "1849.99");
    Assert.assertEquals(
        objectGetResponse.jsonPath().getString("data.'CPU model'"), "Intel Core i9");
    Assert.assertEquals(objectGetResponse.jsonPath().getString("data.'Hard disk size'"), "1 TB");

  }

    @Test()
    public void testPatchObject() throws JsonProcessingException {
        objectName = "Apple MacBook Pro 16";
        String updateObjectName = "New Apple MacBook Pro 16";
        year = 2019;
        objectPrice = 1849.99;
        cpuModel = "Intel Core i9";
        diskSize = "1 TB";
        objectColor = "silver";

        ObjectPojo objectPojo1 = new ObjectPojo();
        objectPojo1.setName(objectName);

        ObjectDataPojo data1 = new ObjectDataPojo();
        data1.setYear(year);
        data1.setPrice(objectPrice);
        data1.setCPU_model(cpuModel);
        data1.setHard_disk_size(diskSize);
        data1.setColor(objectColor);
        objectPojo1.setData(data1);

        ObjectPojo objectPojo2 = new ObjectPojo();
        objectPojo2.setName(updateObjectName);

        ObjectDataPojo data2 = new ObjectDataPojo();
        data2.setYear(year);
        data2.setPrice(objectPrice);
        data2.setCPU_model(cpuModel);
        data2.setHard_disk_size(diskSize);
        data2.setColor(objectColor);
        objectPojo2.setData(data2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo1);

        //Create Object
        Response objectCreateResponse = ServiceAdapter.createObject(jsonPayload);
        id = objectCreateResponse.jsonPath().getString("id");

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonPayload1 = objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo2);

        //patch Object
        Response objectUpdateResponse = ServiceAdapter.patchObject(updateObjectName,id);
        objectUpdateResponse.then().log().all();

        Assert.assertEquals(objectUpdateResponse.statusCode(),200);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("name"),updateObjectName);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.year"),String.valueOf(year));
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.price"),String.valueOf(objectPrice));
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.cpu_model"),cpuModel);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.hard_disk_size"),diskSize);
        Assert.assertEquals(objectUpdateResponse.jsonPath().getString("data.color"),objectColor);
    }


    @Test()
    public void testDeleteObject() throws JsonProcessingException {
        objectName = "Apple MacBook Pro 16";
        year = 2019;
        objectPrice = 1849.99;
        cpuModel = "Intel Core i9";
        diskSize = "1 TB";

        ObjectPojo objectPojo = new ObjectPojo();
        objectPojo.setName(objectName);

        ObjectDataPojo data = new ObjectDataPojo();
        data.setYear(year);
        data.setPrice(objectPrice);
        data.setCPU_model(cpuModel);
        data.setHard_disk_size(diskSize);
        objectPojo.setData(data);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectPojo);

        //Create Object
        Response objectCreateResponse = ServiceAdapter.createObject(jsonPayload);
        id = objectCreateResponse.jsonPath().getString("id");


        Response objectDeleteResponse = ServiceAdapter.deleteObject(id);
        objectDeleteResponse.then().log().all();

        Assert.assertEquals(objectDeleteResponse.statusCode(),200);
        Assert.assertEquals(objectDeleteResponse.jsonPath().getString("message"),"Object with id = "+id+ " has been deleted.");
    }
}


