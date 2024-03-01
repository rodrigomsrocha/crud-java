package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ingresso;

public class IngressoDAO extends DAO {
	
	public IngressoDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Ingresso ingresso) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO ingresso (id, nome, descricao) "
				       	+ "VALUES ("+ingresso.getId()+ ", '" + ingresso.getNome() + "', '" + ingresso.getDescricao() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Ingresso get(int id) {
		Ingresso ingresso = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ingresso WHERE id=" + id;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 ingresso = new Ingresso(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ingresso;
	}
	
	
	public List<Ingresso> get() {
		return get("");
	}

	
	public List<Ingresso> getOrderById() {
		return get("id");		
	}
	
	
	public List<Ingresso> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Ingresso> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	private List<Ingresso> get(String orderBy) {	
	
		List<Ingresso> ingressos = new ArrayList<Ingresso>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ingresso" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Ingresso i = new Ingresso(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
						ingressos.add(i);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ingressos;
	}
	
	
	public boolean update(Ingresso ingresso) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE ingresso SET nome = '" + ingresso.getNome() + "', descricao = '"  
				       + ingresso.getDescricao()
					   + " WHERE id = " + ingresso.getId();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM ingresso WHERE id = " + id;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}