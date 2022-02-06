package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	public List<GalleryVo> getList() {
		System.out.println("GalleryService.getList()");
		return galleryDao.list();
	}

	public int add(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("GalleryService.add()");
		String saveDir = "C:\\javastudy\\upload";

		// 원본 이름 - 확장자 - 확장파일이름
		String orgName = file.getOriginalFilename();
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일패스 생성 , 파일 사이즈
		String filePath = saveDir + "\\" + saveName;
		long fileSize = file.getSize();

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
		GalleryVo newVo = new GalleryVo(filePath, orgName, saveName, fileSize);

		newVo.setUserNo(galleryVo.getUserNo());
		newVo.setContent(galleryVo.getContent());

		return galleryDao.save(newVo);
	}

	public GalleryVo view(int no) {
		System.out.println("GalleryService.view()");
		return galleryDao.view(no);
	}

	public String remove(GalleryVo galleryVo) {
		System.out.println("GalleryService.remove()");		
		int count = galleryDao.delete(galleryVo);
		
		if (count > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
