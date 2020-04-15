package modelDAO;
import modelDAOImpl.*;
import db.*;

public class DAOFactory
{
		public static SellerDAO createSellerDao() {
				return new SellerDAOjdbc(DB.getConnection());
		}
		public static DepartmentDAO createDepartmentDao(){
				return new DepartmentDAOJdbc(DB.getConnection());
				
		}
}
