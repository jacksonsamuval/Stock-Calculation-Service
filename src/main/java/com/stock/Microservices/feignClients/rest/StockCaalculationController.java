package com.stock.Microservices.feignClients.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.Microservices.feignClients.StockClients;

@RestController
@RequestMapping("/api")
public class StockCaalculationController 
{
	@Autowired
	private StockClients service;
	
	@GetMapping("/calc/{companyName}/{quantity}")
	public ResponseEntity<?> calculate(@PathVariable String companyName,@PathVariable Integer quantity)
	{
		ResponseEntity<?> responseEntity=null;
		Double totalPrice=null;
		try
		{
			responseEntity=service.getStockPrice(companyName);
			int status = responseEntity.getStatusCode().value();
			if(status == 200)
			{
				Double price = (Double) responseEntity.getBody();
				totalPrice = price * quantity;
				String response = "the total price is :"+" "+ totalPrice;
				responseEntity = new ResponseEntity<String>(response, HttpStatus.OK);	
			}
		}
		catch(Exception e)
		{
			responseEntity = new ResponseEntity<String>("Company Not Found", HttpStatus.OK);	
		}
		return responseEntity;
	}
}
