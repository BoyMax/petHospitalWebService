package com.petHospital.backend.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

import com.petHospital.backend.model.User;

public class UserRepositoryImpl implements UserRepository {
    private EntityManager em = Persistence.createEntityManagerFactory("myjpa").createEntityManager();
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(User arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll(Iterable<? extends User> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean existsById(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<User> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<User> findById(Long arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public <S extends User> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends User> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByName(String userName) {
		String jpql = "SELECT user FROM User user WHERE user.name = :userName";
		Query query = em.createQuery(jpql,User.class).setParameter("userName", userName);
		List<User> users = query.getResultList();
		if(CollectionUtils.isEmpty(users)) {
			return null;
		}
		return users.get(0);
		
	
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Iterable<? extends User> arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<User> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends User> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
