package br.com.startup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanchesStartupApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void xbacon()
	{
		Lanche lanche = new Lanche(0, "X-Bacon", "Bacon, hambÃºrguer de carne e queijo.", "images/x-bacon.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(6.5, lanche.getValor());
	}
	
	@Test
	public void xburguer()
	{
		Lanche lanche = new Lanche(0, "X-Burger", "HambÃºrguer de carne e queijo.", "images/x-burguer.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(4.5, lanche.getValor());
	}
	
	@Test
	public void xegg()
	{
		Lanche lanche = new Lanche(0, "X-Egg", "Ovo, hambÃºrguer de carne e queijo.", "images/x-egg.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(5.3, lanche.getValor());
	}
	
	@Test
	public void xeggbacon()
	{
		Lanche lanche = new Lanche(0, "X-Egg Bacon", "Ovo, bacon, hambÃºrguer de carne e queijo.", "images/x-egg bacon.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(7.3, lanche.getValor());
	}
	
	@Test
	public void light()
	{
		Lanche lanche = new Lanche(0, "Light", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 1, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(4.41, lanche.getValor());
	}
	
	@Test
	public void muitaCarne()
	{
		Lanche lanche = new Lanche(0, "MuitaCarne", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 3, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(7.5, lanche.getValor());
	}
	
	@Test
	public void muitoQueijo()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 3, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(6.0, lanche.getValor());
	}
	
	@Test
	public void geral()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 1, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(7.7, lanche.getValor());
	}
	
	@Test
	public void geralX2()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 2, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 2, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 2, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 2, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 2, Constants.QUEIJO_PRICE);
		lanche.computePrice();
		
		assertEquals(15.4, lanche.getValor());
	}

}
