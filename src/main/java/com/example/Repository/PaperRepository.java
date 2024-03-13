package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.Paper;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
    Paper findByPaperId(int paperId);

    @Query("SELECT p FROM Paper p WHERE p.user.userid = :userid")
    List<Paper> findPapersByUserId(@Param("userid") Integer userid);
}
