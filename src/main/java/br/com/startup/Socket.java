package br.com.startup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.com.startup.model.Ingrediente;
import br.com.startup.model.Lanche;

@Component
public class Socket extends TextWebSocketHandler
{
	List<Lanche> cart = Collections.synchronizedList(new ArrayList<Lanche>());
	private int idLanche = 0;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		try
		{
			//When the user open the session, we create the list of lanches
			String menu = "{\"id\":1, \"content\":" + createMenuJson() + ", \"contentAdditional\":" + createAdditionalListJson() + "}";
			session.sendMessage(new TextMessage(menu));
		}
		catch (Exception e)
		{
			System.out.println("Error open the websocket session");
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
	{
		try
		{
			JSONObject json = new JSONObject(message.getPayload());
			
			if(json.getInt("id") == 2) //Add product in the cart
			{
				addProduct(session, json);
			}
			else if(json.getInt("id") == 3)
			{
				removeProduct(session, json.getInt("idLanche")); //Remove product from the cart
			}
			else if(json.getInt("id") == 4)
			{
				String cartString = "{\"id\":4, \"totalPrice\":" + calculateCartPrice() + ", \"content\":" + createCartJson() + "}";
				session.sendMessage(new TextMessage(cartString));
				
				cart.clear();
			}
		}
		catch (Exception e)
		{
			System.out.println("Error trying receive message");
			e.printStackTrace();
		}
	}
	
	//Create the lanche and put it in the cart
	private void addProduct(WebSocketSession session, JSONObject json)
	{
		try
		{
			Lanche lanche = new Lanche(++idLanche, json.getString("name"), null, null);
			lanche.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", json.getInt("alface"), Constants.ALFACE_PRICE);
			lanche.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", json.getInt("bacon"), Constants.BACON_PRICE);
			lanche.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", json.getInt("ovo"), Constants.OVO_PRICE);
			lanche.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", json.getInt("hamburguer"), Constants.HAMBURGUER_PRICE);
			lanche.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", json.getInt("queijo"), Constants.QUEIJO_PRICE);
			lanche.computePrice();
			
			synchronized (cart)
			{
				cart.add(lanche);
			}
			
			//When the user add a new lanche, we create this lanche and send the cart json to fill the cart in frontend
			String cart = "{\"id\":2, \"totalPrice\":" + calculateCartPrice() + ", \"content\":" + createCartJson() + "}";
			session.sendMessage(new TextMessage(cart));
		}
		catch (Exception e)
		{
			System.out.println("Error trying add the product to the cart");
			e.printStackTrace();
		}
	}
	
	//Remove the lanche from the cart
	private void removeProduct(WebSocketSession session, int idLanche)
	{
		try
		{
			synchronized (cart)
			{
				for(Lanche lanche: cart)
				{
					if(lanche.getId() == idLanche)
					{
						cart.remove(lanche);
						break;
					}
				}
			}
			
			//refresh the cart
			String cart = "{\"id\":2, \"totalPrice\":" + calculateCartPrice() + ", \"content\":" + createCartJson() + "}";
			session.sendMessage(new TextMessage(cart));
		}
		catch (Exception e)
		{
			System.out.println("Error trying remove the product from the cart");
			e.printStackTrace();
		}
	}
	
	//Method to create the menu json, this json will be used in frontend to fill the menu
	private String createMenuJson() throws JSONException
	{
		Lanche xbacon = new Lanche(0, "X-Bacon", "Bacon, hambúrguer de carne e queijo.", "images/x-bacon.jpg");
		xbacon.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		xbacon.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		xbacon.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		xbacon.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", 1, Constants.HAMBURGUER_PRICE);
		xbacon.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		xbacon.computePrice();
		
		Lanche xburguer = new Lanche(0, "X-Burger", "Hambúrguer de carne e queijo.", "images/x-burguer.jpg");
		xburguer.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		xburguer.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		xburguer.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		xburguer.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", 1, Constants.HAMBURGUER_PRICE);
		xburguer.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		xburguer.computePrice();
		
		Lanche xegg = new Lanche(0, "X-Egg", "Ovo, hambúrguer de carne e queijo.", "images/x-egg.jpg");
		xegg.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		xegg.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 0, Constants.BACON_PRICE);
		xegg.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		xegg.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", 1, Constants.HAMBURGUER_PRICE);
		xegg.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		xegg.computePrice();
		
		Lanche xeggbacon = new Lanche(0, "X-Egg Bacon", "Ovo, bacon, hambúrguer de carne e queijo.", "images/x-egg bacon.jpg");
		xeggbacon.addIngrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		xeggbacon.addIngrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		xeggbacon.addIngrediente(Constants.OVO_ID, "ovo", "Ovo", 1, Constants.OVO_PRICE);
		xeggbacon.addIngrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", 1, Constants.HAMBURGUER_PRICE);
		xeggbacon.addIngrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
		xeggbacon.computePrice();
		
		ArrayList<Lanche> menu = new ArrayList<Lanche>();
		
		menu.add(xbacon);
		menu.add(xburguer);
		menu.add(xegg);
		menu.add(xeggbacon);
		
		JSONArray jsonArrayMenu = new JSONArray();
		
		for(Lanche lanche : menu)
		{
			jsonArrayMenu.put(lanche.getLancheJson());
		}
		
		return jsonArrayMenu.toString();
	}
	
	//Method to create the additional list of ingredientes, this json will be used in frontend to fill the menu
	private String createAdditionalListJson() throws JSONException
	{
		Ingrediente alface = new Ingrediente(Constants.ALFACE_ID, "alface", "Alface", 0, Constants.ALFACE_PRICE);
		Ingrediente bacon = new Ingrediente(Constants.BACON_ID, "bacon", "Bacon", 1, Constants.BACON_PRICE);
		Ingrediente hamburguer = new Ingrediente(Constants.HAMBURGUER_ID, "hamburguer", "Hambúguer de carne", 1, Constants.HAMBURGUER_PRICE);
		Ingrediente ovo = new Ingrediente(Constants.OVO_ID, "ovo", "Ovo", 0, Constants.OVO_PRICE);
		Ingrediente queijo = new Ingrediente(Constants.QUEIJO_ID, "queijo", "Queijo", 1, Constants.QUEIJO_PRICE);
	
		ArrayList<Ingrediente> additional = new ArrayList<Ingrediente>();
		
		additional.add(alface);
		additional.add(bacon);
		additional.add(hamburguer);
		additional.add(ovo);
		additional.add(queijo);
		
		JSONArray jsonArrayAdditional = new JSONArray();
		
		for(Ingrediente ingrediente : additional)
		{
			jsonArrayAdditional.put(ingrediente.getIngredienteJson());
		}
		
		return jsonArrayAdditional.toString();
	}
	
	//This method sum the total price of the order
	private double calculateCartPrice()
	{
		double totalPrice = 0;
		synchronized (cart)
		{
			for(Lanche lanche: cart)
			{
				totalPrice += lanche.getValor();
			}
		}
		return totalPrice;
	}
	
	//Method to create the cart json, this json will be used in frontend to fill the cart 
	private String createCartJson() throws JSONException
	{
		JSONArray jsonArrayMenu = new JSONArray();
		
		synchronized (cart)
		{
			for(Lanche lanche : cart)
			{
				jsonArrayMenu.put(lanche.getLancheJson());
			}
		}
		
		return jsonArrayMenu.toString();
	}
	
}
