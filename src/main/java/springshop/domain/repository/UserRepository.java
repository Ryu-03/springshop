package springshop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User , String>{
		
	User findByUserId(String userId);
	public void deleteById(String userId);

}
