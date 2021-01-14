package com.yorijori.cook.classDAO;

import com.yorijori.cook.DTO.BuyDTO;
import com.yorijori.cook.DTO.CloveDTO;

public interface ClassBasketDAO {
	
	public int selectClove(CloveDTO clove);
	
	public int insertClove(CloveDTO clove);
	
	public int selectBuy(BuyDTO buy);
	
	public int insertBuy(BuyDTO buy);

	public void updateClass(BuyDTO buy);

}
