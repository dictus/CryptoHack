package com.crypto.CryptoHack.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import elemental.json.Json;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.time.Instant;

public class CommitDeserializer extends JsonObjectDeserializer<Currency> {

	@Override
	protected Currency deserializeObject(JsonParser jsonParser,
			DeserializationContext deserializationContext,
			ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

		JsonNode date1 = jsonNode.get("Time Series (Digital Currency Intraday)");
		JsonNode meta_data = jsonNode.get("Meta Data");
		String price = nullSafeValue(jsonNode.get("1a. price (USD)"), String.class);
		String marketCap = nullSafeValue(jsonNode.get("3. market cap (USD)"), String.class);
		String sha = nullSafeValue(jsonNode.get("2. volume"), String.class);
		String date = nullSafeValue(jsonNode.get("date"), String.class);
		/*String message = singleLine(nullSafeValue(commitNode.get("message"), String.class));

		String committerId = nullSafeValue(committerNode.get("login"), String.class);
		String committerName = nullSafeValue(commitNode.get("committer").get("name"), String.class);
		String date = nullSafeValue(commitNode.get("committer").get("date"), String.class);
		String committerAvatar = nullSafeValue(committerNode.get("avatar_url"), String.class)*/;
		Currency.CurrencyDetails currencyDetails =
				new Currency.CurrencyDetails(price, marketCap, sha);
		return new Currency(Instant.parse(date),currencyDetails);
	}

	private String singleLine(String s) {
		if (s == null) {
			return null;
		}
		int i = s.indexOf('\n');
		if (i != -1) {
			return s.substring(0, i);
		}
		return s;
	}

}