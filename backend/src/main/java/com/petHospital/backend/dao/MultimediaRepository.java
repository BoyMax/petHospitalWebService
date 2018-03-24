package com.petHospital.backend.dao;
import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Multimedia;

public interface MultimediaRepository extends CrudRepository<Multimedia, Long>{
}