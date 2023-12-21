package com.choongang.gb2023501.dhutils;

import java.io.File;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUploadDeleteUtil {

	// 파일 이름 관리 (중복 파일 이름 처리)
	UUID uuid = UUID.randomUUID();
	String pathDB = null;
	String g_attach_name = null;
	String g_attach_path = null;
	File fileSaveAndDelete = null;
	String[] uploadResult = null;

	// 파일 업로드 처리
	public String[] uploadFile(MultipartFile file) throws FileUploadException {

		try {
			if (file != null && !file.isEmpty()) {
				g_attach_name = uuid + "_" + file.getOriginalFilename();
				pathDB = "..\\photos\\";
				g_attach_path = System.getProperty("user.dir") + "\\src\\main\\webapp\\photos";
				fileSaveAndDelete = new File(g_attach_path, g_attach_name);

				// 업로드된 파일을 저장
				file.transferTo(fileSaveAndDelete);
			} else {
				// 파일이 없을 경우 처리
				g_attach_name = ""; // 빈 문자열 또는 다른 기본값으로 설정
				pathDB = ""; // 빈 문자열 또는 다른 기본값으로 설정
				g_attach_path = "";
			}
			// 파일이름, 용량, 경로를 반환
			uploadResult = new String[] { g_attach_name, pathDB};

			log.info("uploadFile g_attach_name : {}", g_attach_name);
			log.info("uploadFile pathDB : {}", pathDB);
			log.info("uploadFile realPath : {}", g_attach_path);
			log.info("uploadFile savaFile : {}", fileSaveAndDelete);
		} catch (Exception e) {
			throw new FileUploadException("파일 업로드 중 오류 발생", e);
		}
		return uploadResult;
	}

	// 파일 삭제 처리
	public void deleteFile(String g_attach_name) throws FileUploadException {
		// 파일 유효성 검사 (파일 유형, 크기 등)
		try {
			// 실제 경로
			g_attach_path = System.getProperty("user.dir") + "\\src\\main\\webapp\\photos";

			// 구현체 생성(실 경로 + 파일명)
			fileSaveAndDelete = new File(g_attach_path, g_attach_name);

			// 원본 File 삭제
			fileSaveAndDelete.delete();

			log.info("deleteFile fileName : {}", g_attach_name);
			log.info("deleteFile realPath : {}", g_attach_path);

		} catch (Exception e) {
			log.error("deleteFile errer : {}", e.getMessage());
		}
	}
}
