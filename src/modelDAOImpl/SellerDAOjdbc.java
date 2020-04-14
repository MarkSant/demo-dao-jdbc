package modelDAOImpl;
import modelDAO.*;
import modelEntities.*;
import java.util.*;
import java.sql.*;
import db.*;

public class SellerDAOjdbc implements SellerDAO
{

		private Connection conn;
	
		public SellerDAOjdbc (Connection conn) {
				this.conn = conn;
		}
		
		@Override
		public void insert(Seller obj)
		{
				// TODO: Implement this method
		}

		@Override
		public void update(Seller obj)
		{
				// TODO: Implement this method
		}

		@Override
		public void deleteById(Integer id)
		{
				// TODO: Implement this metho
				
		}

		@Override
		public Seller findById(Integer id)
		{
				PreparedStatement st = null;
				ResultSet rs = null;
				try {
						st = conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DepartmentId = department.Id "
						+ "WHERE seller.Id = ?");
						st.setInt(1, id);
						rs = st.executeQuery();
						if(rs.next()) {
								 Department dep = instantiateDepartment(rs);
								 Seller obj = instantiateSeller(rs, dep);
								 return obj;
						}
						return null;
				} catch (SQLException e) {
						throw new DbException(e.getMessage());
				} finally {
						DB.closeStatement(st);
						DB.closeResultSet(rs); 
				}
				
		}

		@Override
		public List<Seller> findByDepartment(Department department)
		{
				PreparedStatement st = null;
				ResultSet rs = null;
				try {
						st = conn.prepareStatement(
								"SELECT seller.*,department.Name as DepName "
								+ "FROM seller INNER JOIN department "
								+ "ON seller.DepartmentId = department.Id "
								+ "WHERE DepartmentId = ?"
								+ "ORDER BY Name");
						st.setInt(1, department.getId());
						rs = st.executeQuery();
						List<Seller> list = new ArrayList();
						Map<Integer,Department> map = new HashMap();
						while(rs.next()) {
							
								Department dep = map.get(rs.getInt("DepartmentId"));
								if(dep ==null) {
										dep = instantiateDepartment(rs);
										map.put(rs.getInt("DepartmentId"), dep);
								}
								
								Seller obj = instantiateSeller(rs, dep);
								list.add(obj);
								}
								return list;
						}
						
				catch (SQLException e) {
						throw new DbException(e.getMessage());
				} finally {
						DB.closeStatement(st);
						DB.closeResultSet(rs); 
				}
				
		}
		
		@Override
		public List<Seller> findAll()
		{
				return null;
		}
	
	
		
		private Department instantiateDepartment(ResultSet rs) throws SQLException {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				return dep;
		}
	
		private Seller instantiateSeller (ResultSet rs, Department dep) throws SQLException {
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
		}
}
