package modelDAO;
import modelEntities.*;
import java.util.*;

public interface SellerDAO
{
		void insert(Seller obj);
		void update(Seller obj);
		void deleteById(Integer id);
		Seller findById(Integer id);
		List<Seller> findAll;
}
