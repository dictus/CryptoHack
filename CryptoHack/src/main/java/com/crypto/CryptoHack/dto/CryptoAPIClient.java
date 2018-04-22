package com.crypto.CryptoHack.dto;

import com.crypto.CryptoHack.SellingDataStoreEntityImpl;
import com.crypto.CryptoHack.backjpa.SellingDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
//@ConfigurationProperties
public class CryptoAPIClient {

	private final String uri = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol=BTC&market=USD&apikey=UOJJU0KYGVCUZEMC";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SellingDataStoreEntityImpl e;

	public CryptoAPIClient() {
		//this.restTemplate = restTemplateBuilder.build();
		//this.e = e;
		//updateDB(e);
	}
	//@PostConstruct
	private void updateDB() {
		List<SellingComponent> listOfInsert =  getDatewise();
		int count = 0;
		for (SellingComponent sell:listOfInsert) {
			e.save(new SellingDataStore(++count,sell.getVolume(),sell.getPrice(),sell.getMarketCap(),sell.getToDay()));
		}
	}


	public List<CryptoDTO> getRecentCommits() {
		String url = String.format(
				uri);
		//TimeStampValues currencyR = restTemplate.getForObject(uri,TimeStampValues.class);
		//System.out.println(currencyR);
		//Object obj = restTemplate.getForEntity(url,Object.class);
		//Object obj = this.restTemplate.exchange(createRequestEntity(url), Object.class);
		//ResponseEntity<CryptoDTO> response = (ResponseEntity<CryptoDTO>)obj;
		//ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, createRequestEntity(url), String.class);
		//String tmp = response.getBody();
		List<CryptoDTO> cryptoDTOList = new ArrayList<>();
		cryptoDTOList.add(getCryptoDTOSListforAPI());


		return cryptoDTOList;
	}

	public CryptoDTO getCryptoDTOSListforAPI() {
		CryptoDTO quote = restTemplate.getForObject(uri,
				CryptoDTO.class);
		/*List<CryptoDTO> cryptoDTOList = new ArrayList<>();
		cryptoDTOList.add(quote);*/

		return quote;
	}

	public List<SellingComponent> getDatewise(){
		return getRecentCommits().get(0).getSellingComponents();
	}

	private RequestEntity<?> createRequestEntity(String url) {
		try {
			return RequestEntity.get(new URI(url))
					.accept(MediaType.APPLICATION_JSON).build();
		}
		catch (URISyntaxException ex) {
			throw new IllegalStateException("Invalid URL " + url, ex);
		}
	}

	private static class GithubAppTokenInterceptor implements ClientHttpRequestInterceptor {

		private final String token;

		GithubAppTokenInterceptor(String token) {
			this.token = token;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
				ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
			if (StringUtils.hasText(this.token)) {
				byte[] basicAuthValue = this.token.getBytes(StandardCharsets.UTF_8);
				httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION,
						"Basic " + Base64Utils.encodeToString(basicAuthValue));
			}
			return clientHttpRequestExecution.execute(httpRequest, bytes);
		}

	}

}
