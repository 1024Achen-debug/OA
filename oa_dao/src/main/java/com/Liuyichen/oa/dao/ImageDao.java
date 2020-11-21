package com.Liuyichen.oa.dao;

import com.Liuyichen.oa.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imageDao")
public interface ImageDao {
    void insert(Image image);

    void insertCopy(Image image);

    void delete(String createSn);

    void deleteCopy(String createSn);

    void deleteAll();

    List<Image> select(String noyes);

    List<Image> selectCopy(String noyes);

    List<Image> selectAll();

    List<Image> selectAllCopy();
}
