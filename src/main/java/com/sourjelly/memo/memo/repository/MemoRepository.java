package com.sourjelly.memo.memo.repository;

import com.sourjelly.memo.memo.domain.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    // where `user_id` = #{} ORDER BY `id` DESC
    public List<Memo> findByUserId(long userId, Sort sort);
}
