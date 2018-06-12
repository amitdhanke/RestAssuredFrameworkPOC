/* This example is POST method and different headers and params for post
 * 
 * 
 */

package PostData;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostDataExample
{
      @Test(enabled=false)
	  public void headerParamPost()
	 {
    	  //we can add the listr to the param 
    	 List<String> li = new ArrayList<String>();
    	 li.add("Amit");
    	 li.add("dhanke");
    	  
		 given().
		 header("Date", li, null).		 
		 headers("AppKey","Key-value").
		 param("wfsfristName", "fname").
		 param("wfslastName", "lastname").
		 param("list",li).
		 when().post("http://api.fonts.com/rest/json/Accounts/").
		 then().statusCode(401).log().all();
	 }
	 
      //below methods are used for setting request data
       
       @Test(priority = 1)
	  public void ReqestConnectData()
	 {
		 given().request("CONNECT","https://www.onlinesbi.com/").then().
		 statusCode(400).log().all();
	 }
      
         @Test(priority=2)
        public void queryParametr()
       {
    	  given().queryParam("v", "ZJ_2k07TUbw").
    	  when().get("https://www.youtube.com/watch").
    	  then().statusCode(200);
       }
         
         @Test(priority=3, description=" Form param is get used only POST method ")
         public void formParamPost()
        {
          given().formParam("v", "ZJ_2k07TUbw").
          when().post("http://api.fonts.com/rest/json/Domains/").
          then().statusCode(400);	
        }
         
          @Test(priority=4)
          public void pathParameter()
         {
        	given().
        	pathParam("type", "json").
        	pathParam("section", "Domains").
        	when().post("http://api.fonts.com/rest/{type}/{section}/").
        	then().statusCode(400); 
         }
         
          @Test(priority=5)
          public void setContentType()
         {
          given().
          contentType(ContentType.JSON).
          contentType("application/json;charset=UTF-8").
          when().get("http://services.groupkt.com/country/get/all").
          then().statusCode(200);	 
         }
}
