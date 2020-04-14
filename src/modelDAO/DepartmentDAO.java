package modelDAO;
import modelEntities.*;
import java.util.*;

public interface DepartmentDAO
{
		void insert(Department obj);
		void update(Department obj);
		void deleteById(Integer id);
		Department findById(Integer id);
		List<Department> findAll();
}
