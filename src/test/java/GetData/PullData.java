package GetData;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;

 public class PullData 
{

	   @Test(priority = 0)
	  public void responsecode()
	 {
	    Response res =  RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
	    int code = res.statusCode();
	    
	    System.out.println(" Response body is --> "+res.body().asString());
	 
	    System.out.println(" Status code :-> "+code);
	    
	    System.out.println(" Headers --> "+res.headers());
	    
	    System.out.println(" contentType --> "+res.contentType());
	    
	    System.out.println(" cookies --> "+res.cookies());
	    
	    System.out.println(" getSessionId --> "+res.getSessionId());
	    
	    System.out.println(" htmlPath --> "+res.htmlPath());
        
	    System.out.println(" jsonPath --> "+res.jsonPath());
	    
	    System.out.println(" xmlPath --> "+res.xmlPath());
	    
	    Assert.assertEquals(code, 200);
	 
	 }
	 
	   @Test(priority = 1)
	   public void responseasString()
	 {
		Response res =	 RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		String st = res.asString();
		
		System.out.println(" As String response --> "+st);
				
		System.out.println(" As InputStream --> "+res.asInputStream().toString().length());
	 }
	   
	   @Test(priority=2)
	   public void responseAsByteArray()
	   {
		  byte[] arr = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").asByteArray();
	   
	      System.out.println("  Byte Array length is ---> "+arr.length);
	   }
	   
	  
}
