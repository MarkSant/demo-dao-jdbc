package modelDAO;
import modelDAOImpl.*;
import db.*;

public class DAOFactory
{
		public static SellerDAO createSellerDao() {
				return new SellerDAOjdbc(DB.getConnection());
		}
}
