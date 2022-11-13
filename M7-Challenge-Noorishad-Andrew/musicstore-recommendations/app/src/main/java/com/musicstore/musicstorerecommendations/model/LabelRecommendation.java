package com.musicstore.musicstorerecommendations.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Table(name="label_recommendation")
public class LabelRecommendation {

    @Id
    @Column(name="label_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="label_id")
    private Integer labelId;

    @Column(name="user_id")
    private Integer userId;

    private Boolean liked;

    public LabelRecommendation() {
    }

    public LabelRecommendation(Integer id, Integer labelId, Integer userId, Boolean liked) {
        this.id = id;
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelRecommendation that = (LabelRecommendation) o;
        return Objects.equals(id, that.id) && Objects.equals(labelId, that.labelId) && Objects.equals(userId, that.userId) && Objects.equals(liked, that.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, labelId, userId, liked);
    }

    @Override
    public String toString() {
        return "LabelRecommendation{" +
                "id=" + id +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
