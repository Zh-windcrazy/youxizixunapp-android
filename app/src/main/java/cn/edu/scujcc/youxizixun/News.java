package cn.edu.scujcc.youxizixun;

import java.io.Serializable;

public class News implements Serializable {
    private String cover;
    private String id;
    private String title;
    private String content;
    private String uname;

    @Override
    public String toString() {
        return "News{" +
                "cover='" + cover + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", uname='" + uname + '\'' +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
