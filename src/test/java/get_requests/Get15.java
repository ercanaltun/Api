package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatePojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {

     /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
    {
    "firstname": "Guoqiang",
    "lastname": "Morante Briones",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */


    @Test
    public void Get15() {

        //Set the Url
        spec.pathParams("first","booking","second",22);
        //Set the Expectd Data(Payload)
        BookingDatePojo data =new BookingDatePojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Guoqiang","Morante Briones",111,true,data,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the Request and get the Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
       BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(data.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(data.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        //Soft Assertion
        //1. Adım: SoftAssert Objesi Oluştur
        SoftAssert softAssert = new SoftAssert();

        //2. Adım: Assertion Yap
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"Firstname uyusmadi");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"depositpaid uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"additionalneeds uyusmadi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), data.getCheckin(), "checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), data.getCheckout(), "checkout uyusmadi");

        //3. Adım: assertAll() methodunu kullan
        softAssert.assertAll();

    }
}
