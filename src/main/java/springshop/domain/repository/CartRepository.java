package springshop.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springshop.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
	
	List<Cart> findByUserName(String userId);	
	public void deleteById(long number);
	
}
