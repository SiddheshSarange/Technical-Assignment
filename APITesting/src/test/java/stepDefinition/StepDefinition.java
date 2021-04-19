package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payload.AddPayload;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import org.testng.Assert;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqSpec;
	String response;
	ResponseSpecification resSpec;

	@Given("Add payload with email {string} password {string}")
	public void add_payload_with_email_password(String Email, String Password) throws IOException {

		AddPayload req = new AddPayload();
		req.setEmail(Email);
		req.setPassword(Password);
		reqSpec = given().spec(requestSpecification().body(req));
	}

	@When("User calls registerAPI")
	public void user_calls_register_api() {

		response = reqSpec.when().post("api/register").then().extract().response().asString();

	}

	@Then("API response with {string} and {int}")
	public void api_response_with_and(String status, Integer code) {

		resSpec = new ResponseSpecBuilder().expectStatusLine(status).expectStatusCode(code).build();
	}

	@Then("response id and token")
	public void response_id_and_token() {
		JsonPath js = new JsonPath(response);
		String Token = js.getString("token");

	}

	@When("User calls loginAPI")
	public void user_calls_login_api() {

		response = reqSpec.when().post("api/login").then().extract().response().asString();

	}

	@Given("Add create payload with name {string} and job {string}")
	public void add_create_payload_with_email_and_password_missing(String Name, String Job) throws IOException {

		AddPayload req1 = new AddPayload();
		req1.setName(Name);
		req1.setJob(Job);
		reqSpec = given().spec(requestSpecification().body(req1));

	}

	@When("User calls createAPI")
	public void user_calls_create_api() {

		response = reqSpec.when().post("api/users").then().extract().response().asString();

	}

	@Then("response name {string} job {string} and createAt")
	public void response_name_job_and_create_at(String Name, String Job) {

		JsonPath js1 = new JsonPath(response);
		Assert.assertEquals(js1.getString("name"), Name);
		Assert.assertEquals(js1.getString("job"), Job);
		Assert.assertEquals(js1.getString("createdAt"),getTimeZone());
	}

	@Given("Add get request with page number {int}")
	public void add_get_request_with_page_number(Integer PageNo) throws IOException {

		reqSpec = given().spec(requestSpecification().queryParam("page", PageNo));

	}

	@When("User calls getAPI")
	public void user_calls_get_api() {

		response = reqSpec.when().get("api/users").then().extract().response().asString();
	}

	@Then("response page {int} perPage {int} and user details")
	public void response_page_per_page_and_user_details(Integer page, Integer perPg) {

		JsonPath js2 = new JsonPath(response);
		Assert.assertEquals(js2.getString("per_page"), perPg);
		Assert.assertEquals(js2.getString("page"), page);

	}

	@Given("Add delete request with user number {int}")
	public void add_delete_request_with_user_number(Integer IdNo) throws IOException {

		reqSpec = given().spec(requestSpecification().pathParam("users", IdNo));

	}

	@When("User calls deleteAPI")
	public void user_calls_delete_api() {

		response = reqSpec.when().delete("api/users").then().extract().response().asString();
	}
}