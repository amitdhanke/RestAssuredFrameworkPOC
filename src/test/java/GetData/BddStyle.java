//below class is used to validate the GET response 


package GetData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class BddStyle
{
	//verify the status code is 200 ok or not???
	
     @Test(priority=0)
	 public void statusCode()
	 {
		 given().get("http://samples.openweathermap.org/data/2.5/weather?lat=90&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
		 then().statusCode(200);
		 
		 
	 }
	 
     @Test(priority=1,description=" This method is used to print the log on the console ")
     public void logConsole()
     {
    	 given().get("http://samples.openweathermap.org/data/2.5/weather?lat=90&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
    	 then().statusCode(200).
    	 log().all();
     }
     
     @Test(priority=2)
     public void bodyValidationContains()
     {
    	 given().get("http://samples.openweathermap.org/data/2.5/weather?lat=90&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
    	 then().body("weather.main", contains("Clear"));
     }
     
     @Test(priority=3)
     public void bodyequalTo()
     {
    	 when().get("http://samples.openweathermap.org/data/2.5/weather?lat=90&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
    	 then().body("coord.lon", equalTo("139.01"));
     }
     
     //root setting is needed when json is very big then we want to validate the response from it
      @Test(priority=4)
      public void rootSetting()
     {
    	 given().get("http://samples.openweathermap.org/data/2.5/weather?lat=90&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
    	 then().root("weather").and().body("id", is("800"));
     }
     
       @Test(priority=5)
     public void extractTheUrl()
     {
    	 String href = when().get("http://jsonplaceholder.typicode.com/photos/1"). 
    	 then().contentType(ContentType.JSON).body("albumId", equalTo(1)).
    	 extract().path("url");
      
    	 System.out.println(" href is --> "+href);
    	 
    	 when().get(href).then().statusCode(200);
     }
       //extracted the list from the json path
       
        @Test(priority=6)
        public void getTheListFromTheResponse()
       {
    	   Response res = given().get("http://services.groupkt.com/country/get/all");
    	   
    	   java.util.List<String> list = res.jsonPath().getList("RestResponse.result.name");
    	   
    	   
    	   System.out.println(" List size is "+list.size());
    	   
    	   for(String s : list)
    	   {
    		   if(s.contains("Australia"))
    		   {
    			   System.out.println(" Found My Country!!!!!! :D ");
    		   }
    	   }
       }
     
        @Test(priority=7)
       public void getHeadersAsList()
       {
        	Response res = given().get("http://services.groupkt.com/country/get/all");
        	
        	String head = res.getHeader("Date");
        	
        	System.out.println(" Header is --> "+head);
        	
        	Headers heads = res.headers();
        	
        	for(Header h : heads)
        	{
        		System.out.println(" Name is ---> "+h.getName()+"  Value is --->  "+h.getValue());
        	}
       }
        
}
