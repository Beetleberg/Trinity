package com.example.george.tztrinity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by George on 08.08.2018.
 */
// ЕСЛИ ЧТО СМОТРИ ИМПОРТИРУЕМЫЕ КЛАССЫ

public class User extends RealmObject {

    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lastSeen")
    @Expose
    private String lastSeen;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("similarity")
    @Expose
    private Integer similarity;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("unreadMessages")
    @Expose
    private Integer unreadMessages;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param id
     * @param unreadMessages
     * @param lastSeen
     * @param status
     * @param name
     * @param age
     * @param similarity
     * @param avatar
     */
    public User(Integer age, String avatar, Integer id, String lastSeen, String name, Integer similarity, String status, Integer unreadMessages) {
        super();
        this.age = age;
        this.avatar = avatar;
        this.id = id;
        this.lastSeen = lastSeen;
        this.name = name;
        this.similarity = similarity;
        this.status = status;
        this.unreadMessages = unreadMessages;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

}