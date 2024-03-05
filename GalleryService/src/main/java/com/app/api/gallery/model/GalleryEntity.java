package com.app.api.gallery.model;

public class GalleryEntity {
    private String GalleryId;
    private Integer userId; 
    private String name;
    private String description; 

    
    /**
     * @return the GalleryId
     */
    public String getGalleryId() {
        return GalleryId;
    }

    /**
     * @param GalleryId the GalleryId to set
     */
    public void setGalleryId(String GalleryId) {
        this.GalleryId = GalleryId;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
