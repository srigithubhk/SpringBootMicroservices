package com.app.api.gallery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.api.gallery.model.GalleryEntity;
import com.app.api.gallery.repository.GalleryRepository;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	
	@Override
    public List<GalleryEntity> getAllGalleries() {
        List<GalleryEntity> returnValue = GalleryRepository.galleryDataPool();
        
        return returnValue;
	 }
	
	@Override
	public List<GalleryEntity> getGalleryByGalleryId(String galleryId) {
		List<GalleryEntity> galleryDataPool = GalleryRepository.galleryDataPool();
		List<GalleryEntity> returnValue = galleryDataPool.stream().filter(gal -> gal.getGalleryId().equals(galleryId)).collect(Collectors.toList());
        
        return returnValue;
	}
	
	@Override
	public List<GalleryEntity> getUserGalleries(Integer userId) {
		List<GalleryEntity> galleryDataPool = GalleryRepository.galleryDataPool();
		List<GalleryEntity> returnValue = galleryDataPool.stream().filter(gal -> gal.getUserId().equals(userId)).collect(Collectors.toList());
        
        
        return returnValue;
	}

}
