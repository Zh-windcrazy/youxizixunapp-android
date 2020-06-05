package cn.edu.scujcc.youxizixun;

import java.io.Serializable;

public class Game implements Serializable {
        private String cover;
        private String id;
        private String title;
        private String price;

    @Override
    public String toString() {
        return "Game{" +
                "cover='" + cover + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
