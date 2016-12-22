package com.nika.web.java.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nika.web.java.rest.JSONBasedReverser;

public class JSONBasedReverserTest {

	private JSONBasedReverser reverser;

	@Before
	public void setUp() {
		reverser = new JSONBasedReverser();
	}

	@Test
	public void testNoWord() {
		JSONObject expected = new JSONObject();
		expected.put("original", "ANKARA");
		expected.put("reversed", "ARAKNA");
		Response response = reverser.defaultReverser();
		Assert.assertEquals(response.getStatus(), 200);
		JSONObject actual = new JSONObject((String) response.getEntity());
		assertEquals(actual.get("original"), expected.get("original"));
		assertEquals(actual.get("reversed"), expected.get("reversed"));
	}

	@Test
	public void testWord() {
		JSONObject expected = new JSONObject();
		expected.put("original", "foobar");
		expected.put("reversed", "raboof");
		Response response = reverser.reverser("foobar");
		Assert.assertEquals(response.getStatus(), 200);
		JSONObject actual = new JSONObject((String) response.getEntity());
		assertEquals(actual.get("original"), expected.get("original"));
		assertEquals(actual.get("reversed"), expected.get("reversed"));
	}
}
