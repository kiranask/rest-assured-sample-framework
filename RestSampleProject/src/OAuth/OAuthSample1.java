package OAuth;
import static io.restassured.RestAssured.given;

import com.sun.xml.bind.v2.runtime.reflect.Accessor.GetterSetterReflection;

import io.restassured.path.json.JsonPath;

public class OAuthSample1 
{

	public static void main(String[] args) {
		
		
		String accessTokenResponse = 
		given().
			queryParams("code","").
			queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
			queryParams("redirect_url","https://rahulshettyacademy.com/getCourse.php").
			queryParams("state","qwerty").
			queryParams("auth_url","https://accounts.google.com/o/oauth2/v2/auth").
			queryParams("scope","https://www.googleapis.com/auth/userinfo.email").log().all().
			queryParams("grant_type","autherization_code").
		when().
			post("https://www.googleapis.com/oauth2/v4/token").asString();
			JsonPath jp = new JsonPath(accessTokenResponse);
			String accessToken = jp.getString("access_token");
			String response = given().queryParam("access_token", "accessToken").
					when().get("https://rahulshettyacademy.com/getCourse.php").
					asString();
		
			System.out.println(response);
		
			
		}
	}


