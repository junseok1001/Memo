package com.sourjelly.memo.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 파일 저장 삭제 기능을 수행하는 거 만들기
public class FileManger {

    // 멤버 변수 형태로 나는 사용할거고 변경하면 안돼닌깐 final
    // final 은 상수라고 말한다.
    public final static String FILE_UPLOAD_PATH="D:\\webInventer\\springProject\\upload\\memo";

    // 파일을 전달받아, 정해진 경로에 저장하고,
    // 해당 파일을 클라이언트가 접근할 수 있는 url 경로 리턴
    public static String saveFile(long userId, MultipartFile file){

        if(file == null){
            return null;
        }

        // 원본 파일 이름 그대로 저장
        // 디렉터리(폴더)로 구분해서 파일 저장
        // 디렉터리 이름 : 사용자 정보 + 시간 정보 ex) 3_32198792314
        // UNIX TIME : 1970년 1월 1일 0시 0분 0초 이후로 흐른시간 (millisecond)


        String directoryName = "/" + userId + "_" + System.currentTimeMillis();

        //디렉터리 만들기
        // 전체 디렉터리 경로
        String directoryPath = FILE_UPLOAD_PATH + directoryName;

        File directory = new File(directoryPath);

        // directory.mkdir()은 잘 만들었젔는지를 true false 로 리턴
        if(!directory.mkdir()){
            // 디렉터리 생성 실패
            return null;
        }

        // 파일 저장
        String filePath = directoryPath + "/" + file.getOriginalFilename();

        try {

            // 정보를 저장??
            byte[] bytes = file.getBytes();

            //경로를 관리하는 객체 생성
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

        } catch (IOException e) {
            return null;
        }

        //url path 규칙 만들기
//        서버 파일 경로 : D:\\webInventer\\springProject\\upload\\memo/3_123456789/test.png
        // url path : /images/3_123456789/test.png

        return "/images"+ directoryName + "/" + file.getOriginalFilename();


    }

}
