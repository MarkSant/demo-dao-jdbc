package modelDAO;
import modelDAOImpl.*;

public class DAOFactory
{
		public static SellerDAO createSellerDao() {
				return new SellerDAOjdbc();
		}
}
