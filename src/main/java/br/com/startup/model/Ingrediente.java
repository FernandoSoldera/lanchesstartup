package br.com.startup.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingrediente
{
	private int id;
	private String name;
	private String description;
	private int amount;
	private double valor;
	
	public Ingrediente(int id, String name, String description, int amount, double valor)
	{
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.valor = valor;
		this.description = description;
	}
	
	public double getTotalValor()
	{
		return this.valor * this.amount;
	}
	
	public JSONObject getIngredienteJson() throws JSONException
	{
		JSONObject json = new JSONObject();
		json.put("name", this.name);
		json.put("description", this.description);
		json.put("valor", this.valor);
		return json;
	}

	public int getId() { return id; }

	public String getName() { return name; }

	public int getAmount() { return amount; }
	
	public double getValor() { return valor; }
	
	public String getDescription() { return description; }
}
