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
		int result = 1;  // ��ũ���� �Ϸ�Ǿ����� Ȯ���ϴ� ��(��ũ�� �Ұ�: 0, ��ũ�� ����: 1, ��ũ�� ����: 2)
		
		int preResult = recipeBasketDao.selectScrap(scrap);  // �̹� ��ũ���Ǿ��ִ��� Ȯ��(1: ��Ʈ�� �Ұ�, 0: ��ũ�� ����)
		
		if(preResult == 1) {
			result = 0;  // ��ũ�� �Ұ� ǥ��
		}else {  // ��ũ�� ����
			result = recipeBasketDao.insertScrap(scrap);  // ��ũ��(����: 1, ����: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int subscribeProcess(SubscribeDTO subscribe) {
		int result = 1;  // ������ �Ϸ�Ǿ����� Ȯ���ϴ� ��(���� �Ұ�: 0, ���� ����: 1, ���� ����: 2)
		
		int preResult = recipeBasketDao.selectSubscribe(subscribe);  // �̹� �����Ǿ��ִ��� Ȯ��(1: ���� �Ұ�, 0: ���� ����)
		
		if(preResult == 1) {
			result = 0;  // ���� �Ұ� ǥ��
		}else {  // ���� ����
			result = recipeBasketDao.insertSubscribe(subscribe);  // ����(����: 1, ����: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int cloveProcess(CloveDTO clove) {
		int result = 1;  // ���ϱⰡ �Ϸ�Ǿ����� Ȯ���ϴ� ��(���ϱ� �Ұ�: 0, ���ϱ� ����: 1, ���ϱ� ����: 2)
		
		int preResult = classBasketDao.selectClove(clove);  // �̹� ���ϱ�Ǿ��ִ��� Ȯ��(1: ���ϱ� �Ұ�, 0: ���ϱ� ����)
		
		if(preResult == 1) {
			result = 0;  // ���ϱ� �Ұ� ǥ��
		}else {  // ���ϱ� ����
			result = classBasketDao.insertClove(clove);  // ���ϱ�(����: 1, ����: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int buyProcess(BuyDTO buy) {
		int result = 1;  // ������û�� �Ϸ�Ǿ����� Ȯ���ϴ� ��(������û �Ұ�: 0, ������û ����: 1, ������û ����: 2)
		
		int preResult = classBasketDao.selectBuy(buy);  // �̹� ������û�Ǿ��ִ��� Ȯ��(1: ������û �Ұ�, 0: ������û ����)
		
		if(preResult == 1) {
			result = 0;  // ������û �Ұ� ǥ��
		}else {  // ������û ����
			result = classBasketDao.insertBuy(buy);  // ������û(����: 1, ����: 0)
			if(result == 0) {
				result = 2;
			}else {
				classBasketDao.updateClass(buy);
			}
		}
		
		return result;
	}

}
