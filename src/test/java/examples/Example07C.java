package examples;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Example07C extends JsonplaceholderBaseUrl {

            /*
        Given
              https://jsonplaceholder.typicode.com/todos
        When
          I send GET Request to the URL == > URL'e Get Request gonderin
        Then
          1)Status code is 200 == > Status kodu 200 olmali
          2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
            Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
          3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
            Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
          4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
            Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
            basliginin "delectus aut autem" icerdigini dogrulayin
     */

    @Test
    public void test07C(){

        //First Step:Set the Url
        spec.pathParam("first","todos");

        //Second Step:Set The Expected Data(Payload)

        //Third Step:Send The Request and Get The Response
        Response response=given().spec(spec).when().get("/{first}");

        //Fourth Step:Do Assertion
          //1)Status code is 200 == > Status kodu 200 olmali
        assertEquals(200,response.statusCode());

         //2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        JsonPath json=response.jsonPath();
        List<Integer>idler=json.getList("findAll{it.id>190}.id");
        System.out.println("idler = " + idler);

        //Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        assertEquals(10,idler.size());

        //3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        List<Integer>userIdler=json.getList("findAll{it.id<5}.userId");
        System.out.println("userIdler = " + userIdler);

        //Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
        assertEquals(4,userIdler.size());

        //4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        List<String>titles=json.getList("findAll{it.id<5}.title");
        System.out.println("titles = " + titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        //basliginin "delectus aut autem" icerdigini dogrulayin
        assertTrue(titles.contains("delectus aut autem"));

    }
}
