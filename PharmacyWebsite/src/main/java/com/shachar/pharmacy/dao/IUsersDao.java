package com.shachar.pharmacy.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shachar.pharmacy.entities.Users;



public interface IUsersDao  extends CrudRepository <Users,Long>{
	Users findByUserName (String userName);
	Users findById(long userId);
	List<Users> findByCompanyId(long companyId);
}
