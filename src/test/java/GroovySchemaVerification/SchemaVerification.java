package GroovySchemaVerification;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class SchemaVerification {

	@Test
	public void TC1()
	{
		//http://geo.groupkt.com/ip/172.217.3.14/json
		
		//use below method in body method .......
		//body(matchesJSONSchemaInClasspath(""));
		
		given().get("http://geo.groupkt.com/ip/172.217.3.14/json"). 
		then().assertThat().body("", null);
		
	}
}
