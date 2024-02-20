package com.example.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Paper;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
	Paper findByPaperId(int paperId);

    List<Paper> findPapersByUserUserid(Integer userId);
}
