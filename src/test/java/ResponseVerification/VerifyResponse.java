package ResponseVerification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

 public class VerifyResponse 
{  
	 @Test(priority=0)
   public void verifyResponseStatus()
  {
	  given().get("http://services.groupkt.com/country/get/all").then().assertThat().statusCode(200).log().all();
	  given().get("http://services.groupkt.com/country/get/all").then().assertThat().statusLine("HTTP/1.1 200 200").log().all();
	  given().get("http://services.groupkt.com/country/get/all").then().assertThat().statusLine(containsString("HTTP")).log().all();
  }
	 @Test(priority=1)
	public void verifyResponseHeader()
	{    
		 //header name and value matching needs to give key and value pairs
		 given().get("http://services.groupkt.com/country/get/all").then().assertThat().header("Server", "Apache/2.4.25 (Debian)");
		 given().get("http://services.groupkt.com/country/get/all").then().assertThat().headers("Date",containsString("Sun, 24 Dec 2017"));
	}
	 @Test(priority=2)
	 public void validateBodyResponse()
	 {
		 String res = given().get("http://services.groupkt.com/country/get/all").asString();
		 
		 given().get("http://services.groupkt.com/country/get/all").then().assertThat().body(equalTo(res));
		 
	 }
	 
	 @Test(priority=3,description=" Using java 7/8 lambda expression we will validate the response of body ")
	 public void validateBodyWithLambdaExpression()
	 {
		 
		 //with JAVA 7
		 given().get("http://jsonplaceholder.typicode.com/photos/1").
		 then().body("thumbnailUrl",new ResponseAwareMatcher(){

			public Matcher<?> matcher(ResponseBody response) throws Exception {
				// TODO Auto-generated method stub
				return equalTo("http://placehold.it/150/92c952");
			}
			 
		 });
		 
		 //With Java 8
		 given().get("http://jsonplaceholder.typicode.com/photos/1").then().assertThat().body("thumbnailUrl", response -> equalTo("http://placehold.it/150/92c952"));
		 
		 given().get("http://jsonplaceholder.typicode.com/photos/1").then().assertThat().body("thumbnailUrl", endsWith("92c952")); 
	 }
	 
	  @Test(priority=4,description="  this method is used for api timing ")
	  public void validateTime()
	 {
		 long l = given().get("http://jsonplaceholder.typicode.com/photos/1").time();
		 
		 long ms = given().get("http://jsonplaceholder.typicode.com/photos/1").timeIn(TimeUnit.MILLISECONDS);
		 
		 System.out.println(" Time take is -->  "+l);
		 System.out.println(" Time taken into millisecounds -->  "+ms);
		 
		 given().get("http://jsonplaceholder.typicode.com/photos/1").then().time(lessThan(500l));
	 }
}
