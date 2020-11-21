package com.Liuyichen.oa.entity;

public class Image {
    private String createSn;

    private String imageName;

    private String imagePath;

    private String noyes;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public String getCreateSn() {
        return createSn;
    }

    public String getNoyes() {
        return noyes;
    }

    public void setNoyes(String noyes) {
        this.noyes = noyes;
    }
}
