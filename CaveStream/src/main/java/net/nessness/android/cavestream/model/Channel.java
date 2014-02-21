package net.nessness.android.cavestream.model;

import java.util.List;

public class Channel {
    private String name;
    protected String id;
    protected String ip;
    private String url;
    private String genre;
    private String description;
    private int listeners;
    //protected int relays;
    private int kbps;
    //protected String _11;
    //protected String _12;
    //protected String _13;
    //protected String _14;
    //protected String nameEncoded;
    private String time;
    //protected String status;
    private String comment;
    //protected String _19;

    private List<String> tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public int getKbps() {
        return kbps;
    }

    public void setKbps(int kbps) {
        this.kbps = kbps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
