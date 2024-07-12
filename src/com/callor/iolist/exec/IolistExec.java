package com.callor.iolist.exec;

import java.io.FileNotFoundException;

import com.callor.iolist.service.IolistService;
import com.callor.iolist.service.impl.IolistServiceImplV1;

public class IolistExec {

	public static void main(String[] args) {
		IolistService iolistService = null;
		String ioListFile = "src/com/callor/iolist/lolist.txt";
		
		try {
			iolistService = new IolistServiceImplV1(ioListFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		iolistService.loadIoData();
		iolistService.printIoList();
		
	}
}
