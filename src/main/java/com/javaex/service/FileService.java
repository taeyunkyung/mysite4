package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String save(MultipartFile file) {
		System.out.println("FileService.save()");
		String saveDir = "C:\\javastudy\\upload";

		// 원본 이름 - 확장자 - 확장파일이름
		String orgName = file.getOriginalFilename();
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일패스 생성 , 파일 사이즈
		String filePath = saveDir + "\\" + saveName;
		// long fileSize = file.getSize();
		
		// 파일저장		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		// DB에 저장
		
		return saveName;		
	}
}
