package br.tech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto1?useTimezone=true&serverTimezone=UTC", "root", "googleex");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Mensagem> getLista() {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM mensagens;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				Mensagem message = new Mensagem();
				message.setId(rs.getInt("Id_mensagem"));
				message.setMens(rs.getString("mensagem"));
				stmt2 = connection.prepareStatement("SELECT * FROM tag WHERE mensagem_id = "+rs.getInt("Id_mensagem"));
				rs2 = stmt2.executeQuery();
				List<String> tagis = new ArrayList<String>();
				List<Integer> idtagis = new ArrayList<Integer>();
				while(rs2.next()) {
					tagis.add(rs2.getString("tags"));	
					idtagis.add(rs2.getInt("mensagem_id"));
				}
				message.setTag(tagis);
				message.setTag_id(idtagis);
				
				mensagens.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensagens;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adiciona(Mensagem mens) {
		String sql = "INSERT INTO mensagens(mensagem) VALUES(?)";
		String sql2 = "INSERT INTO tag(mensagem_id,tag) VALUES(?,?)";
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2 = connection.prepareStatement(sql2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.setString(1, mens.getMens());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String str : mens.getTag()) {
			try {
				stmt2.setInt(1, mens.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt2.setString(2, str);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt2.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt2.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void altera(Mensagem msg) {
		String sql = "UPDATE mensagens SET mensagem=? WHERE id=?";
		String sql2 = "UPDATE tag SET tags=? WHERE id=?";
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2 = connection.prepareStatement(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			stmt.setString(1, msg.getMens());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setInt(2, msg.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> strList = msg.getTag();
		List<Integer> intList = msg.getTag_id();
		for (int i = 0;i< msg.getTag().size();i++) {
			try {
				stmt2.setString(1, strList.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt2.setInt(2, intList.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void exclui(Integer id) {
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			stmt = connection.prepareStatement("DELETE FROM mensagem WHERE id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setLong(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2 = connection.prepareStatement("DELETE FROM tag WHERE mensagem_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2.setLong(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
	