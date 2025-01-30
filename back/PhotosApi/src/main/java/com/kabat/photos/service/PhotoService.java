package com.kabat.photos.service;

import java.util.List;

import com.kabat.photos.entity.Photo;

public interface PhotoService {
	public Photo altaPhoto(Photo in)throws Exception;
	public List<Photo> getPhoto()throws Exception;
	public Photo getPhoto(Integer id)throws Exception;
	public int deletePhoto(Integer id) throws Exception;
}