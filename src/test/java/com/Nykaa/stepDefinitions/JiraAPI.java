package com.Nykaa.stepDefinitions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class JiraAPI {
	
	private static RequestSpecification req;
	static String basicAuthKey = "Basic c2h1YmhhbXNpc3dhMTUwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBkcVluYm9ueHkybGFZWmpFZmVZZm1kNHVPeEp0QjFYd04xMUhMY2U4c1lnVXlHV19PcDBySEJ6NEwwdzRBU0JVaUh5d0hWZFVzTjh6Z1V4cWR3M212N1lndGZXSnlINGlSNkc1c1lhWnVLempya2t1aFFoQXlaWjczQXVOemxIMFdFNnlYbG9MdE5TYnJQZERBRjVKbG5rQTExcUtsWnRGQmRnMGlXZTc3Mms9NjA0MDBFRUY=";
	
	public static void CreateBugOnTestFailure(String attachmentFilePath, String bugTitle, String projectKey, String bugDescription) throws FileNotFoundException {
		RequestSpecification();
		String createIssueResponse = given().spec(req).body(JiraAPI.CreateBug(bugTitle, projectKey, bugDescription)).when().post("rest/api/3/issue").then().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		RestAssured.baseURI = "https://shubhamautomationtester.atlassian.net/";
		given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header("Authorization", basicAuthKey).multiPart("file", new File(attachmentFilePath)).post("rest/api/3/issue/{key}/attachments").then().assertThat().statusCode(200);
	}
	
	public static RequestSpecification RequestSpecification() throws FileNotFoundException {
		if(req==null) {
			PrintStream log = new PrintStream(new FileOutputStream("JiraLogs.txt"));
			req = new io.restassured.builder.RequestSpecBuilder().setBaseUri("https://shubhamautomationtester.atlassian.net/").addHeader("Content-Type", "application/json").addHeader("Authorization", basicAuthKey).setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;
	}
	
	
	public static String CreateBug(String bugTitle, String projectKey, String bugDescription) {
		return "{\r\n"
        		+ "  \"fields\": {\r\n"
        		+ "    \"project\": {\r\n"
        		+ "      \"key\": \""+projectKey+"\"\r\n"
        		+ "    },\r\n"
        		+ "    \"summary\": \""+bugTitle+"\",\r\n"
        		+ "    \"description\": {\r\n"
        		+ "      \"type\": \"doc\",\r\n"
        		+ "      \"version\": 1,\r\n"
        		+ "      \"content\": [\r\n"
        		+ "        {\r\n"
        		+ "          \"type\": \"paragraph\",\r\n"
        		+ "          \"content\": [\r\n"
        		+ "            {\r\n"
        		+ "              \"type\": \"text\",\r\n"
        		+ "              \"text\": \""+bugDescription+"\"\r\n"
        		+ "            }\r\n"
        		+ "          ]\r\n"
        		+ "        }\r\n"
        		+ "      ]\r\n"
        		+ "    },\r\n"
        		+ "    \"issuetype\": {\r\n"
        		+ "      \"name\": \"Bug\"\r\n"
        		+ "    }\r\n"
        		+ "  }\r\n"
        		+ "}\r\n"
        		+ "";
	}
	
}
