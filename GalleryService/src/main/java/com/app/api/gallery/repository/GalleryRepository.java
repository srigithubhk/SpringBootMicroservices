package com.app.api.gallery.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.api.gallery.model.GalleryEntity;

@Repository
public class GalleryRepository {
	
	static List<GalleryEntity> galleryDataPool = new ArrayList<>();
    
	static {
		for(int i=0; i<5; i++) {
		    GalleryEntity GalleryEntity = new GalleryEntity();
		    GalleryEntity.setUserId(i);
		    GalleryEntity.setGalleryId("gal-"+i);
		    GalleryEntity.setDescription("Gallery "+i+" description");
		    GalleryEntity.setName("Gallery "+i+" name");   	    
		    
		    galleryDataPool.add(GalleryEntity);
		}
    
	}
	
	public static List<GalleryEntity> galleryDataPool() {		
		
		return GalleryRepository.galleryDataPool;
	}

}
