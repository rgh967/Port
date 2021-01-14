package com.yorijori.cook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.cook.DTO.BuyDTO;
import com.yorijori.cook.DTO.CloveDTO;
import com.yorijori.cook.DTO.ScrapDTO;
import com.yorijori.cook.DTO.SubscribeDTO;
import com.yorijori.cook.classDAO.ClassBasketDAO;
import com.yorijori.cook.recipeDAO.RecipeBasketDAO;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

	@Autowired
	private RecipeBasketDAO recipeBasketDao;
	
	@Autowired
	private ClassBasketDAO classBasketDao;
	
	@Override
	public int scrapProcess(ScrapDTO scrap) {
		int result = 1;  // 스크랩이 완료되었는지 확인하는 값(스크랩 불가: 0, 스크랩 성공: 1, 스크랩 실패: 2)
		
		int preResult = recipeBasketDao.selectScrap(scrap);  // 이미 스크랩되어있는지 확인(1: 스트랩 불가, 0: 스크랩 가능)
		
		if(preResult == 1) {
			result = 0;  // 스크랩 불가 표시
		}else {  // 스크랩 가능
			result = recipeBasketDao.insertScrap(scrap);  // 스크랩(성공: 1, 실패: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int subscribeProcess(SubscribeDTO subscribe) {
		int result = 1;  // 구독이 완료되었는지 확인하는 값(구독 불가: 0, 구독 성공: 1, 구독 실패: 2)
		
		int preResult = recipeBasketDao.selectSubscribe(subscribe);  // 이미 구독되어있는지 확인(1: 구독 불가, 0: 구독 가능)
		
		if(preResult == 1) {
			result = 0;  // 구독 불가 표시
		}else {  // 구독 가능
			result = recipeBasketDao.insertSubscribe(subscribe);  // 구독(성공: 1, 실패: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int cloveProcess(CloveDTO clove) {
		int result = 1;  // 찜하기가 완료되었는지 확인하는 값(찜하기 불가: 0, 찜하기 성공: 1, 찜하기 실패: 2)
		
		int preResult = classBasketDao.selectClove(clove);  // 이미 찜하기되어있는지 확인(1: 찜하기 불가, 0: 찜하기 가능)
		
		if(preResult == 1) {
			result = 0;  // 찜하기 불가 표시
		}else {  // 찜하기 가능
			result = classBasketDao.insertClove(clove);  // 찜하기(성공: 1, 실패: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int buyProcess(BuyDTO buy) {
		int result = 1;  // 수강신청이 완료되었는지 확인하는 값(수강신청 불가: 0, 수강신청 성공: 1, 수강신청 실패: 2)
		
		int preResult = classBasketDao.selectBuy(buy);  // 이미 수강신청되어있는지 확인(1: 수강신청 불가, 0: 수강신청 가능)
		
		if(preResult == 1) {
			result = 0;  // 수강신청 불가 표시
		}else {  // 수강신청 가능
			result = classBasketDao.insertBuy(buy);  // 수강신청(성공: 1, 실패: 0)
			if(result == 0) {
				result = 2;
			}else {
				classBasketDao.updateClass(buy);
			}
		}
		
		return result;
	}

}
