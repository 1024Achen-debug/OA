package com.Liuyichen.oa.biz;

import com.Liuyichen.oa.entity.Image;

import java.util.List;

public interface ImageBiz {
    void add(Image image);

    void addCopy(Image image);

    void remove(String createSn);

    void removeCopy(String createSn);

    void removeAll();

    List<Image> get(String noyes);

    List<Image> getCopy(String noyes);

    List<Image> getAll();

    List<Image> getAllCopy();
}
