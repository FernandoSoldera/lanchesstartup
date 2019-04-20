//Constants
const alface = 0.40;
const bacon = 2.00;
const hamburguer = 3.00;
const ovo = 0.80;
const queijo = 1.50;

//This is a listener to close the modal if the user press 'ESC'
$("body")[0].addEventListener('keydown', function(event){ if(event.keyCode == 27) { closeModal(); } })

//Open the connection to the server
var websocket = new WebSocket('ws://localhost:8080/socket');

//Websocket onmessage
websocket.onmessage = function(message)
{
	if(message.data != null && message.data != undefined && message.data != "")
	{
		//If the message is 1, the data is the menu
		var json = JSON.parse(message.data);
		if(json.id == 1)
		{
			createMenu(json);
			createAdditionalList(json);
		}
		else if(json.id == 2)
		{
			createCart(json);
		}
		else if(json.id == 4)
		{
			openModalCloseOrder(json);
		}
	}
}

//Function to create the menu list (iterate the json array that came from the backend)
function createMenu(data)
{
	var array = data.content;
	for(var i=0; i<array.length; i++)
	{
		$(".list").append(	"<div class='product'>" +
								"<div class='productImage'>" +
									"<img src='" + array[i].image + "'>" +
								"</div>" +
								"<div class='productInfo'>" +
									"<span class='spanProductTitle'>" + array[i].name + "</span>" +
									"<span class='spanPrice'>R$ " + array[i].valor.toFixed(2) + "</span>" +
									"<span class='spanProductContent'>" + array[i].description + "</span>" +
								"</div>" +
								"<div class='productButton'>" +
									"<input type='button' value='Pedir' onclick=\"openModal('" + array[i].name + "', '" + array[i].image + "', '" + array[i].description + "', " + array[i].alface + ", " + array[i].bacon + ", " + array[i].hamburguer + ", " + array[i].ovo + ", " + array[i].queijo + ");\">" +
								"</div>" +
							"</div>");
	}
}

//Function to create the additional list (iterate the json array that came from the backend)
function createAdditionalList(data)
{
	var array = data.contentAdditional;
	for(var i=0; i<array.length; i++)
	{
		$(".listAdditional").append("<div class='additional'>" +
										"<span>" + array[i].description + " <span class='additionalPrice'>(R$ " + array[i].valor.toFixed(2) + ")</span></span>" +
										"<div>" +
											"<input class='buttonMinus' type='button' value='-' onclick='decreaseAmount(\"inputAmount" + array[i].name + "\");'>" +
											"<input id='inputAmount" + array[i].name + "' class='inputAmount' type='number' value='0' onkeyup='refreshSubTotal()'>" +
											"<input class='buttonSum' type='button' value='+' onclick='increaseAmount(\"inputAmount" + array[i].name + "\");'>" +
										"</div>" +
									"</div>");
	}
}

//Function to create the cart list (iterate the json array that came from the backend)
function createCart(data)
{
	$(".order").text(""); //Clean the order list
	
	var array = data.content;
	for(var i=0; i< array.length; i++)
	{
		$(".order").append(	"<div class='cartProduct'>" +
				"<div>" +
					"<span class='cartNameProduct'> - " + array[i].name + "</span>" +
					"<span class='cartPriceProduct'> (R$ "+ array[i].valor.toFixed(2) +")</span>" +
				"</div>" +
				"<span class='deleteCartProduct' onclick='deleteCartProduct(" + array[i].idLanche + ");'>X</span>" +
			"</div>");
	}
	
	$("#spanTotal").text("R$ " + data.totalPrice.toFixed(2));
	if(array.length > 0)
	{
		$("#buttonCloseOrder").removeClass("disable");
	}
	else
	{
		$("#buttonCloseOrder").addClass("disable");
	}
}

//Function to increase the number from an input
function increaseAmount(element)
{
	$("#" + element).val((isNaN(parseInt($("#" + element).val())) ? 0 : parseInt($("#" + element).val())) + 1);
	refreshSubTotal();
}

//Function to decrease the number from an input
function decreaseAmount(element)
{
	var number = parseInt($("#" + element).val()) - 1;
	if(number >= 0) //Dont let amount less than 0
	{
		$("#" + element).val(parseInt($("#" + element).val()) - 1);
	}
	else
	{
		$("#" + element).val("0");
	}
	refreshSubTotal();
}

//Function to open the modal and fill the information about the product
function openModal(name, image, description, amountAlface, amountBacon, amountHamburguer, amountOvo, amountQueijo)
{
	$(".modalBackground").css("display", "flex");
	$("#spanTitleProduct").html(name);
	$("#spanDescriptionProduct").html(description);
	$(".additionalImage")[0].src = image;
	$("#inputAmountalface").val(amountAlface);
	$("#inputAmountbacon").val(amountBacon);
	$("#inputAmounthamburguer").val(amountHamburguer);
	$("#inputAmountovo").val(amountOvo);
	$("#inputAmountqueijo").val(amountQueijo);
	refreshSubTotal();
}

//Function to close the modal
function closeModal()
{
	$(".modalBackground").css("display", "none");
}

//This function refresh the subtotal of the product taking the additional into account
function refreshSubTotal()
{
	var amountAlface = parseInt($("#inputAmountalface").val());
	var amountBacon = parseInt($("#inputAmountbacon").val());
	var amountHamburguer = parseInt($("#inputAmounthamburguer").val());
	var amountOvo = parseInt($("#inputAmountovo").val());
	var amountQueijo = parseInt($("#inputAmountqueijo").val());
	
	var subTotal = 0;
	subTotal += (isNaN(amountAlface) ? 0 : amountAlface) * alface;
	subTotal += (isNaN(amountBacon) ? 0 : amountBacon) * bacon;
	subTotal += (isNaN(amountHamburguer) ? 0 : amountHamburguer) * hamburguer;
	subTotal += (isNaN(amountOvo) ? 0 : amountOvo) * ovo;
	subTotal += (isNaN(amountQueijo) ? 0 : amountQueijo) * queijo;
	
	$("#modalSubTotal").html("R$ " + subTotal.toFixed(2));
}

//This function send to backend the request to put this lanche in the cart
function addProductCart()
{
	var json = {};
	json["id"] = 2;
	json["name"] = $("#spanTitleProduct").text();
	json["alface"] = parseInt($("#inputAmountalface").val());
	json["bacon"]  = parseInt($("#inputAmountbacon").val());
	json["hamburguer"] = parseInt($("#inputAmounthamburguer").val());
	json["ovo"] = parseInt($("#inputAmountovo").val());
	json["queijo"] = parseInt($("#inputAmountqueijo").val());
	websocket.send(JSON.stringify(json));
	
	closeModal();
}

//Send delete request to backend
function deleteCartProduct(idLanche)
{
	websocket.send("{'id':3, 'idLanche':" + idLanche + "}");
}

//Send close order request to backend
function closeOrder()
{
	websocket.send("{'id':4}");
}

//Open modal and show full order
function openModalCloseOrder(data)
{
	$(".orderModal").text(""); //Clean the order list
	$(".order").text("");
	$("#spanTotal").text("R$ 0.00");
	
	var array = data.content;
	for(var i=0; i< array.length; i++)
	{
		$(".orderModal").append(	"<div class='cartProduct'>" +
				"<div>" +
					"<span class='cartNameProduct'> - " + array[i].name + "</span>" +
					"<span class='cartPriceProduct'> (R$ "+ array[i].valor.toFixed(2) +")</span>" +
				"</div>" +
			"</div>");
	}
	
	$("#spanTotalModal").text("R$ " + data.totalPrice.toFixed(2));
	
	$(".modalBackgroundCloseOrder").css("display", "flex");
}

//Close modal
function closeModalCloseOrder()
{
	$(".modalBackgroundCloseOrder").css("display", "none");
}