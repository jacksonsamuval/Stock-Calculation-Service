package com.stock.Microservices.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StockService")
public interface StockClients 
{
	@GetMapping("/api/stockPrice/{companyName}")
	public ResponseEntity<Double> getStockPrice(@PathVariable String companyName);
}