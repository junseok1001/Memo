package com.sourjelly.memo.memo.service;

import com.sourjelly.memo.common.FileManger;
import com.sourjelly.memo.memo.domain.Memo;
import com.sourjelly.memo.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

//    @Autowired
    public MemoService(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    public boolean createMemo(
            long userId
            , String title
            , String contents
            , MultipartFile imageFile) {

        String imagePath = FileManger.saveFile(userId, imageFile);

        Memo memo = Memo.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .imagePath(imagePath)
                .build();
        try{
            memoRepository.save(memo);
        }catch(DataAccessException e){
            return false;
        }

        return true;
    }

    public List<Memo> getMemoList(long userId){
        return memoRepository.findByUserId(userId, Sort.by("id").descending());

    }

    public Memo getMemo(long id){
        Optional<Memo> optionalMemo = memoRepository.findById(id);

        return optionalMemo.get();
    }

    public boolean updateMemo(long id, String title, String contents){
        // 수정 대상 행 조회
        // 수정 내용 적용 된 객체 만들기
        // 수정된 객체 저장

        Optional<Memo> optionalMemo = memoRepository.findById(id);

        if(optionalMemo.isPresent()){
            Memo memo = optionalMemo.get();

            memo = memo.toBuilder()
                        .title(title)
                        .contents(contents)
                        .build();
            try {
                memoRepository.save(memo);
            }catch(DataAccessException e){
                return false;
            }

        }else{
            return false;
        }

        return true;
    }

    public boolean deleteMemo(long id){
        // 삭제할 데이터 조회
        // 삭제한다.
        Optional<Memo> optionalMemo = memoRepository.findById(id);

        if(optionalMemo.isPresent()){
            Memo memo = optionalMemo.get();

            FileManger.removeFile(memo.getImagePath());

            try{
                memoRepository.delete(memo);
            }catch(DataAccessException e){
                return false;
            }

        }else{
            return false;
        }

        return true;
    }

}
