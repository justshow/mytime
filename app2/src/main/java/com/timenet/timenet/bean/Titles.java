package com.timenet.timenet.bean;

/**
 * Created by Administrator on 2016/4/9.
 */
public class Titles {
  private int image;
  private String title;

    public Titles(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "image=" + image +
                ", title='" + title + '\'' +
                '}';
    }
}
