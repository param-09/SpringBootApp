package com.param.repository;

import java.util.List;			

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.param.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> ,JpaSpecificationExecutor<User>,CrudRepository<User,Long>
{
	public User findByUsername(String username);
	
	
	
	
	/*@Query(value="select u from userdata u where u.username LIKE %:keyword%"
	+ "OR u.password LIKE %?1%"
	+ "OR u.contact LIKE %?1%"
	+ "OR u.email LIKE %?1%"
	+ "OR u.address LIKE %?1%" ,nativeQuery=true)
	*/
	//@Query(value="select id from userdata id where id.username like %:keyword% or id.password like %:keyword% or id.contact like %:keyword%  or id.email like %:keyword% or id.address like %:keyword% ", nativeQuery=true )	
	
	
	@Query(value="select * from userdata u where u.username like :keyword or u.password like :keyword or u.contact like :keyword  or u.email like :keyword or u.address like :keyword", nativeQuery=true )
	
	List<User> findByKeyword(@Param("keyword") String keyword);

}
