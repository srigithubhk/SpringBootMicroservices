package com.app.api.gallery.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.gallery.model.GalleryEntity;
import com.app.api.gallery.model.GalleryResponseModel;
import com.app.api.gallery.service.GalleryService;

@RestController
@RequestMapping("/galleries")
public class GalleryController {

	@Autowired
    GalleryService GallerysService;
	
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping( 
            produces = { 
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
            })
    public ResponseEntity<Object> allGallerys() {

        List<GalleryResponseModel> returnValue = new ArrayList<>();
        
        List<GalleryEntity> GallerysEntities = GallerysService.getAllGalleries();
        
        if(GallerysEntities == null || GallerysEntities.isEmpty())
        {
        	return new ResponseEntity<>("There are no galleries at the moment: ", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        
        Type listType = new TypeToken<List<GalleryResponseModel>>(){}.getType();
 
        returnValue = new ModelMapper().map(GallerysEntities, listType);
        logger.info("Returning " + returnValue.size() + " Galleries");
        return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping( value="/{galleryId}",
            produces = { 
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
            })
    public ResponseEntity<Object> getGallerybyId(@PathVariable String galleryId) {

        List<GalleryResponseModel> returnValue = new ArrayList<>();
        
        List<GalleryEntity> GallerysEntities = GallerysService.getGalleryByGalleryId(galleryId);
        
        if(GallerysEntities == null || GallerysEntities.isEmpty()) {
        	logger.error("Request not found. There is no gallery for the gallery ID: " + galleryId);
        	return new ResponseEntity<>("There is no gallery for the gallery ID: "+galleryId, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        
        Type listType = new TypeToken<List<GalleryResponseModel>>(){}.getType();
 
        returnValue = new ModelMapper().map(GallerysEntities, listType);
        logger.info("Returning " + returnValue.size() + " Galleries");
        return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping( value="/users/{userId}",
            produces = { 
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
            })
    public ResponseEntity<Object> getUserGalleries(@PathVariable Integer userId) {

        List<GalleryResponseModel> returnValue = new ArrayList<>();
        
        List<GalleryEntity> GallerysEntities = GallerysService.getUserGalleries(userId);
        
        if(GallerysEntities == null || GallerysEntities.isEmpty()) {
        	logger.error("Request not found. There are no galleries for the user ID: " + userId);
        	return new ResponseEntity<>("There are no galleries for the user ID: "+userId, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        
        Type listType = new TypeToken<List<GalleryResponseModel>>(){}.getType();
 
        returnValue = new ModelMapper().map(GallerysEntities, listType);
        logger.info("Returning " + returnValue.size() + " Galleries");
        return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    }
}
