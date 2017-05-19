package de.solutionsdirekt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleAppApplicationTests {

	@Test
	public void contextLoads() throws IOException {

		System.out.println("doing some testing");

        String url = "http://simpleapp:8080/hello";

        URL obj = null;

            obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        Assert.assertEquals("Response Code",404,responseCode);

    }

}
