package com.Liuyichen.oa.biz.impl;

import com.Liuyichen.oa.biz.ImageBiz;
import com.Liuyichen.oa.dao.ImageDao;
import com.Liuyichen.oa.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("imageBizImpl")
public class ImageBizImpl implements ImageBiz {
    @Qualifier("imageDao")
    @Autowired
    private ImageDao imageDao;

    public void add(Image image) {
        imageDao.insert(image);
    }

    public void addCopy(Image image) {
        imageDao.insertCopy(image);
    }

    public void remove(String createSn) {
        imageDao.delete(createSn);
    }

    public void removeCopy(String createSn) {
        imageDao.deleteCopy(createSn);
    }

    public void removeAll() {
        imageDao.deleteAll();
    }

    public List<Image> get(String noyes) {
        return imageDao.select(noyes);
    }

    public List<Image> getCopy(String noyes) {
        return imageDao.selectCopy(noyes);
    }

    public List<Image> getAll() {
        return imageDao.selectAll();
    }

    public List<Image> getAllCopy() {
        return imageDao.selectAllCopy();
    }
}
