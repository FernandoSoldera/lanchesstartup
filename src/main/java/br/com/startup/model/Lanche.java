package br.com.startup.model;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.startup.Constants;

public class Lanche
{
	private int id;
	private String name;
	private String description;
	private String image;
	@JsonIgnore
	private HashMap<Integer, Ingrediente> ingredientes = new HashMap<Integer, Ingrediente>();
	private double valor;
	
	public Lanche(int id, String name, String description, String image)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
	}
	
	//Compute the Lanche price (including promotional discounts)
	public void computePrice()
	{
		this.valor = 0;
		for(Ingrediente ingrediente : ingredientes.values())
		{
			this.valor += ingrediente.getTotalValor();
		}
		if(ingredientes.get(Constants.ALFACE_ID).getAmount() > 0 && ingredientes.get(Constants.BACON_ID).getAmount() == 0) //Promo��o Light
		{
			this.valor = this.valor * 0.9;
		}
		if(ingredientes.get(Constants.HAMBURGUER_ID).getAmount() >= 3)//Promo��o muito hamburguer
		{
			int quantity = ingredientes.get(Constants.HAMBURGUER_ID).getAmount() / 3;
			this.valor = this.valor - (quantity * Constants.HAMBURGUER_PRICE);
		}
		if(ingredientes.get(Constants.QUEIJO_ID).getAmount() >= 3)//Promo��o muito queijo
		{
			int quantity = ingredientes.get(Constants.QUEIJO_ID).getAmount() / 3;
			this.valor = this.valor - (quantity * Constants.QUEIJO_PRICE);
		}
	}
	
	//This method return all the info about this lanche in JSON format
	@JsonIgnore
	public JSONObject getLancheJson() throws JSONException
	{
		JSONObject json = new JSONObject();
		json.put("idLanche", this.id);
		json.put("name", this.name);
		json.put("description", this.description);
		json.put("image", this.image);
		json.put("valor", this.valor);
		for(Ingrediente ingrediente : ingredientes.values())
		{
			json.put(ingrediente.getName(), ingrediente.getAmount());
		}
		return json;
	}
	
	public void addIngrediente(int idIngrediente, String name, String description, int amount, double valor)
	{
		this.ingredientes.put(idIngrediente, new Ingrediente(idIngrediente, name, description, amount, valor));
	}
	
	public int getId() { return id; }

	public String getName() { return name; }

	public double getValor() { return valor; }
	
	public String getDescription() { return description; }
	
	public String getImage() { return image; }

	public HashMap<Integer, Ingrediente> getIngredientes() {
		return ingredientes;
	}
}
