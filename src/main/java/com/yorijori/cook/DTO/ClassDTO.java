package com.yorijori.cook.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ClassDTO {
	// Ŭ���� ID
	private int CLASS_ID;
	// ȸ�� ID
	private String MEMBER_ID;
	// Ŭ���� ����
	private String CLASS_TITLE;
	// Ŭ���� ��ǥ����
	private String CLASS_MAIN_IMG;
	// Ŭ���� ��û ������
	private String CLASS_APP_STARTDATE;
	// Ŭ���� ��û ������
	private String CLASS_APP_ENDDATE;
	// Ŭ���� ���� ������
	private String CLASS_STARTDATE;
	// Ŭ���� ���� ������
	private String CLASS_ENDDATE;
	// Ŭ���� ���� ���۽ð�
	private String CLASS_STARTTIME;
	// Ŭ���� ���� ����ð�
	private String CLASS_ENDTIME;
	// Ŭ���� ���� ��ü�ο�
	private int CLASS_PEOPLE;
	// Ŭ���� ������û�ο�
	private int CLASS_REG_PEOPLE;
	// Ŭ���� ���� ���
	private int CLASS_COST;
	// Ŭ���� ���
	private String CLASS_ADDRESS;
	// Ŭ���� ���� �Ұ�
	private String CLASS_LECTURER;
	
	// class_regi.jsp���� name �Ӽ� Ȯ�� 
	private MultipartFile uploadFile;
	private String originalFile;	// ÷�ε� ���� �̸�
		
	// �ּ�ó��
	private String postcode;
	private String address;
	private String address_etc;
	
	private MemberDTO member;
	private ClassReviewDTO classReview;

}
