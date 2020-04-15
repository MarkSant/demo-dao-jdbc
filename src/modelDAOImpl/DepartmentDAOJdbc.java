package modelDAOImpl;
import modelDAO.*;
import java.sql.*;
import java.util.*;
import modelEntities.*;
import db.*;

public class DepartmentDAOJdbc implements DepartmentDAO
{

		@Override
		public void insert(Department obj)
		{
				PreparedStatement st = null;
				try {
						conn.prepareStatement( " INSERT INTO Department "
																	+ "(Name) "
																	+ "VALUES "
																	+ "(?)",
																	Statement.RETURN_GENERATED_KEYS);
						st.setString(1, obj.getName());
						
						int rowsAffected = st.executeUpdate();
						if(rowsAffected > 0) {
								ResultSet rs = st.getGeneratedKeys();
								if( rs.next()) {
										int id = rs.getInt(1);
										obj.setId(id);
								}
								DB.closeResultSet(rs);
						} else {
								throw new DbException("No row affected");
						}
				} catch (SQLException e) {
						throw new DbException(e.getMessage());
				} finally {
						DB.closeStatement(st);
				}
		}

		@Override
		public void update(Department obj)
		{
				PreparedStatement st = null;
				try {
						conn.prepareStatement( " UPDATE Department "
																	+ "SET Name = ? "
																	+ "WHERE Id = ?",
																	Statement.RETURN_GENERATED_KEYS);
						st.setString(1, obj.getName());
						
						st.setInt(2, obj.getId());

						st.executeUpdate();

				} catch (SQLException e) {
						throw new DbException(e.getMessage());
				} finally {
						DB.closeStatement(st);
				}
		}

		@Override
		public void deleteById(Integer id)
		{
				PreparedStatement st = null;
				try{
						st = conn.prepareStatement( " DELETE FROM Department WHERE Id = ?");
						st.setInt(1, id);
						st.executeUpdate();
				} catch (SQLException e) {
						throw new DbException(e.getMessage());
				}
		}

		@Override
		public Department findById(Integer id)
		{
				PreparedStatement st = null;
				ResultSet rs = null;
				try {
						st = conn.prepareStatement(
								"SELECT Department.* "
								+ "WHERE Department.Id = ?");
						st.setInt(1, id);
						rs = st.executeQuery();
						if(rs.next()) {
								Department dep = instantiateDepartment(rs);
								
								return dep;
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
		public List<Department> findAll()
		{
				PreparedStatement st = null;
				ResultSet rs = null;
				try {
						st = conn.prepareStatement(
								"SELECT Department.* "
								+ "ORDER BY Name");

						rs = st.executeQuery();
						List<Department> list = new ArrayList();
						
						while(rs.next()) {

								Department obj = new Department();
								obj.setId(rs.getInt("Id"));
								obj.setName(rs.getString("Name"));
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

		private Connection conn = null;
	
		public DepartmentDAOJdbc(Connection conn){
				this.conn = conn;
		}
		private Department instantiateDepartment(ResultSet rs) throws SQLException {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				return dep;
		}
}

		

