package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	PrintStream log;
	static Properties prop;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "resources\\config.properties");
		prop = new Properties();
		prop.load(fi);
		return prop.getProperty(key);

	}

	public static Instant getTimeZone() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant dateTimeZone = timestamp.toInstant();
		return dateTimeZone;
	}
}
