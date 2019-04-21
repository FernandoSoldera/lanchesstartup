package br.com.startup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanchesStartupApplicationTests
{
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testXBacon() throws Exception
	{
		mockMvc.perform(get("/xbacon")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("6.5"));
	}
	
	@Test
	public void testXBurguer() throws Exception
	{
		mockMvc.perform(get("/xburguer")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("4.5"));
	}
	
	@Test
	public void testXEgg() throws Exception
	{
		mockMvc.perform(get("/xegg")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("5.3"));
	}
	
	@Test
	public void testXEggBacon() throws Exception
	{
		mockMvc.perform(get("/xeggbacon")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("7.3"));
	}
	
	@Test
	public void testLight() throws Exception
	{
		mockMvc.perform(get("/light")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("4.41"));
	}
	
	@Test
	public void testMuitaCarne() throws Exception
	{
		mockMvc.perform(get("/muitaCarne")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("7.5"));
	}
	
	@Test
	public void testMuitoQueijo() throws Exception
	{
		mockMvc.perform(get("/muitoQueijo")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("6.0"));
	}
	
	@Test
	public void testGeral() throws Exception
	{
		mockMvc.perform(get("/geral")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("7.7"));
	}

	@Test
	public void testGeralx2() throws Exception
	{
		mockMvc.perform(get("/geralx2")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.valor").value("15.4"));
	}
}
