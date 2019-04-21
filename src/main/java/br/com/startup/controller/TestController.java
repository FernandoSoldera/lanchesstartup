package br.com.startup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.startup.Constants;
import br.com.startup.model.Lanche;


@RestController
public class TestController
{
	@RequestMapping(value = "/xbacon")
	public Lanche testXBacon()
	{
		Lanche lanche = new Lanche(0, "X-Bacon", "Bacon, hamburguer de carne e queijo.", "images/x-bacon.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/xburguer")
	public Lanche testXBurguer()
	{
		Lanche lanche = new Lanche(0, "X-Burger", "Hambuguer de carne e queijo.", "images/x-burguer.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/xegg")
	public Lanche testXEgg()
	{
		Lanche lanche = new Lanche(0, "X-Egg", "Ovo, hamburguer de carne e queijo.", "images/x-egg.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/xeggbacon")
	public Lanche testXEggBacon()
	{
		Lanche lanche = new Lanche(0, "X-Egg Bacon", "Ovo, bacon, hamburguer de carne e queijo.", "images/x-egg bacon.jpg");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/light")
	public Lanche testLight()
	{
		Lanche lanche = new Lanche(0, "Light", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 1, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/muitaCarne")
	public Lanche testMuitaCarne()
	{
		Lanche lanche = new Lanche(0, "MuitaCarne", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 3, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/muitoQueijo")
	public Lanche testMuitoQueijo()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 3, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/geral")
	public Lanche testGeral()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 1, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 1, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
	
	@RequestMapping(value = "/geralx2")
	public Lanche testGeralx2()
	{
		Lanche lanche = new Lanche(0, "MuitoQueijo", "", "");
		lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 2, Constants.ALFACE_PRICE);
		lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 2, Constants.BACON_PRICE);
		lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 2, Constants.OVO_PRICE);
		lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "HambÃºguer de carne", 2, Constants.HAMBURGUER_PRICE);
		lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 2, Constants.QUEIJO_PRICE);
		lanche.computePrice();

		return lanche;
	}
}
