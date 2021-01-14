package com.yorijori.cook.service;

import com.yorijori.cook.DTO.BuyDTO;
import com.yorijori.cook.DTO.CloveDTO;
import com.yorijori.cook.DTO.ScrapDTO;
import com.yorijori.cook.DTO.SubscribeDTO;

public interface BasketService {

	int scrapProcess(ScrapDTO scrap);

	int subscribeProcess(SubscribeDTO subscribe);

	int cloveProcess(CloveDTO clove);

	int buyProcess(BuyDTO buy);
	
		
}
