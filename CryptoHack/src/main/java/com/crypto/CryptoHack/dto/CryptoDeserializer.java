package com.crypto.CryptoHack.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class CryptoDeserializer extends JsonObjectDeserializer<CryptoDTO> {

	@Override
	protected CryptoDTO deserializeObject(JsonParser jsonParser,
			DeserializationContext deserializationContext,
			ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

		CryptoDTO tmsp = new CryptoDTO();
		tmsp.setTimeSeris(jsonNode.get("Meta Data").get("4. Market Code").textValue());
		JsonNode timeDet =	jsonNode.get("Time Series (Digital Currency Intraday)");
		Iterator<Map.Entry<String, JsonNode>> fields = timeDet.fields();

		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> entry = fields.next();
			String date = entry.getKey().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date insidate = null;
			try{
				insidate = sdf.parse(date);
			}catch (ParseException e){

			}
			String price = entry.getValue().get("1a. price (USD)").textValue();
			String marketCap = entry.getValue().get("3. market cap (USD)").textValue();
			String valuemo = entry.getValue().get("3. market cap (USD)").textValue();
			if(null!= insidate) {
				tmsp.getMycryptoDetails().put(insidate, new SellingComponent(valuemo, price, marketCap,insidate));
				tmsp.getSellingComponents().add(new SellingComponent(valuemo, price, marketCap,insidate));
			}
		}

		return tmsp;

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