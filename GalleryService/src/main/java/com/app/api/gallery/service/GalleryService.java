package com.app.api.gallery.service;

import java.util.List;

import com.app.api.gallery.model.GalleryEntity;

public interface GalleryService {

	List<GalleryEntity> getAllGalleries();
	List<GalleryEntity> getGalleryByGalleryId(String galleryId);
	List<GalleryEntity> getUserGalleries(Integer userId);
}
