package com.callor.iolist.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.iolist.models.IolistVO;
import com.callor.iolist.service.IolistService;
import com.callor.iolist.utils.Contract;
import com.callor.iolist.utils.Line;

public class IolistServiceImplV1 implements IolistService {

	protected final String ioListFile;
	protected final List<IolistVO> ioList;
	protected final Scanner fileReader;

	public IolistServiceImplV1(String ioListFile) throws FileNotFoundException {
		super();
		this.ioListFile = ioListFile;
		this.ioList = new ArrayList<>();

		InputStream fileInput = new FileInputStream(ioListFile);
		this.fileReader = new Scanner(fileInput);
	}

	@Override
	public void loadIoData() {
		fileReader.nextLine();
		while (fileReader.hasNext()) {
			String line = fileReader.nextLine();
			String[] lines = line.split(",");

			IolistVO vo = new IolistVO();
			vo.ic_day = Date.valueOf(lines[Contract.IOLIST.tradeDate]);
			vo.ic_division = Integer.valueOf(lines[Contract.IOLIST.division]);
			vo.ic_name = lines[Contract.IOLIST.name];
			vo.ic_amount = Integer.valueOf(lines[Contract.IOLIST.amount]);
			vo.ic_cost = Integer.valueOf(lines[Contract.IOLIST.cost]);
			this.ioList.add(vo);
		}

	}

	@Override
	public void printIoList() {
		int purchaseTotal = 0;
		int salesTotal = 0;
		System.out.println(Line.dLine(100));
		System.out.println("거래일자\t 거래구분\t 상품이름\t\t매입금액\t 매출금액");

		for(IolistVO one : ioList) {
			System.out.printf("%s\t",one.ic_day);
			if(one.ic_division == 1) {
				System.out.print("   매입\t\t");
			}else if(one.ic_division == 2) {
				System.out.print("   매출\t\t");
			}
			System.out.printf("%s\t\t",one.ic_name);
			System.out.printf("%d\t\t",one.ic_purchaseCost());
			purchaseTotal += one.ic_purchaseCost();
			System.out.printf("%d\n",one.ic_salesCost());
			salesTotal += one.ic_salesCost();
		}
		
		System.out.println(Line.sLine(100));
		System.out.print("거래건수\t   ");
		System.out.printf("%d건\t\t\t\t",ioList.size());
		System.out.printf("%d\t",purchaseTotal);
		System.out.printf("%d\n",salesTotal);
		System.out.println(Line.dLine(100));
	}

}
