package com.kabat.photos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabat.photos.entity.Photo;
import com.kabat.photos.repository.PhotoRepository;
import com.kabat.photos.service.PhotoService;

@Service("PhotoService")
public class PhotoImpl implements PhotoService {
	
	@Autowired
	PhotoRepository repository;

	@Override
	public Photo altaPhoto(Photo in) throws Exception {
		try {
			return repository.save(in);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}

	@Override
	public List<Photo> getPhoto() throws Exception {
		try {
			return repository.findAll();
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
	}

	@Override
	public Photo getPhoto(Integer id) throws Exception{
		try {
			Optional<Photo> out = repository.findById(id);
			if(out.isPresent()) {
				return out.get();
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return null;
	}

	@Override
	public int deletePhoto(Integer id) throws Exception {
		try {
			Optional<Photo> out = repository.findById(id);
			if(out.isPresent()) {
				repository.deleteById(id);
				return 1;
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return 0;
	}
}