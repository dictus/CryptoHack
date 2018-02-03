package com.crypto.CryptoHack;

import com.crypto.CryptoHack.dto.*;
import com.crypto.CryptoHack.dto.Currency;
import com.crypto.CryptoHack.services.GetCryptoAPIClient;
import com.crypto.CryptoHack.services.GetCryptoDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestClientTest(CryptoAPIClient.class)
public class CryptoHackApplicationTests {

	@Autowired
	CryptoAPIClient crpCryptoAPIClient;
	/*@Autowired
			@Qualifier("cryptoAPIClient")
	GetCryptoAPIClient cryptoAPIClient;*/

	@Autowired
	private MockRestServiceServer mockServer;
	private final String testurl = "https://gturnquist-quoters.cfapps.io/api/random";

	private final String uri = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol=BTC&market=USD&apikey=UOJJU0KYGVCUZEMC";

	@Test
	public void contextLoads() {
		expectJson(uri,"api_resp.json");
		List<CryptoDTO> recentCommits = this.crpCryptoAPIClient.getRecentCommits();
		System.out.println(recentCommits.get(0).getTimeSeris());
		//Quote quote = cryptoAPIClient.getLiveQuote();
		this.mockServer.verify();
	}
	private void expectJson(String url, String bodyPath) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		this.mockServer.expect(requestTo(url))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
				.andRespond(withStatus(HttpStatus.OK)
						//.body(new ClassPathResource(bodyPath))
						.headers(httpHeaders));
	}


	/*Resource stateFile = new ClassPathResource("api_resp.json"); */
	//@Test
	public void testcheckJson() throws IOException,ParseException{

		String jsonFile = readFromFile("C:\\POC\\CryptoHack\\src\\test\\resources\\api_resp.json");
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonFile);
		TimeStampValues tmsp = new TimeStampValues();
		tmsp.setTimeSeris(jsonNode.get("Meta Data").get("4. Market Code").textValue());
		//tmsp.setMycryptoDetails(Instant.parse());
		JsonNode timeDet =	jsonNode.get("Time Series (Digital Currency Intraday)");
		/*while (iterator.hasNext()){

			JsonNode node = iterator.next();
			System.out.println(node.textValue());
		}*/
		Iterator<Map.Entry<String, JsonNode>> fields = timeDet.fields();

		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> entry = fields.next();
			String date = entry.getKey().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date insidate = sdf.parse(date);
			String price = entry.getValue().get("1a. price (USD)").textValue();
			String marketCap = entry.getValue().get("3. market cap (USD)").textValue();
			String valuemo = entry.getValue().get("3. market cap (USD)").textValue();
			tmsp.getMycryptoDetails().put(insidate,new SellingComponent(valuemo,price,marketCap,insidate));
		}





		System.out.println(jsonNode.get("Time Series (Digital Currency Intraday)").elements());
		//tmsp.setDate(Instant.parse(jsonNode.get("Time Series (Digital Currency Intraday)").get(0).textValue()));
		System.out.println(tmsp.getTimeSeris());
		String jsonFile2 = readFromFile("C:\\POC\\CryptoHack\\src\\test\\resources\\eaasy.json");
		JsonNode jsonNode2 = objectMapper.readTree(jsonFile2);
		System.out.println(jsonNode2.get("id").textValue());

		//TimeStampValues emp = objectMapper.readValue(jsonFile, TimeStampValues.class);
		//System.out.println(emp);
	}

	public static String readFromFile(String fileName) {
		StringBuffer contents = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line.trim());
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.getStackTrace();
		}
		return contents.toString();
	}
}
