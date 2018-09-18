package br.tech;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	private int nextId;

	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/projeto1?useTimezone=true&serverTimezone=UTC", "root", "googleex");
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
				stmt2 = connection
						.prepareStatement("SELECT * FROM tag WHERE mensagem_id = " + rs.getInt("Id_mensagem"));
				rs2 = stmt2.executeQuery();
				List<String> tagis = new ArrayList<String>();
				List<Integer> idtagis = new ArrayList<Integer>();
				while (rs2.next()) {
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
		String sql = "INSERT INTO mensagens(Id_mensagem,mensagem) VALUES(?,?)";
		String sql2 = "INSERT INTO tag(mensagem_id,tags) VALUES(?,?)";
		String sql3 = "SELECT * FROM mensagens";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;

		try {
			stmt3 = connection.prepareStatement(sql3);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			rs = stmt3.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (rs.next()) {
				nextId = rs.getInt("Id_mensagem") + 1;
				System.out.println("LastId: " + nextId);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

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
			stmt.setInt(1, nextId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			stmt.setString(2, mens.getMens());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String str : mens.getTag()) {
			System.out.println("Passou por: " + str);
			try {
				stmt2.setInt(1, nextId);
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
		String sql = "UPDATE mensagens SET mensagem=? WHERE Id_mensagem=?";
		String sql2 = "INSERT INTO tag(mensagem_id,tags) VALUES(?,?)";
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;

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
			stmt3 = connection.prepareStatement("DELETE FROM tag WHERE mensagem_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt3.setInt(1, msg.getId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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
		try {
			stmt3.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> strList = msg.getTag();
		for (int i = 0; i < msg.getTag().size(); i++) {
			try {
				stmt2.setString(2, strList.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt2.setInt(1, msg.getId());
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
	}

	public void exclui(Integer id) {
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;

		try {
			stmt = connection.prepareStatement("DELETE FROM mensagens WHERE Id_mensagem=?");
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

	public List<Integer> getBuscaId(String tagBusca) {
		List<Integer> listaId = new ArrayList<>();
		String sql = "SELECT mensagem_id FROM tag WHERE tag.tags = ?";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		try {
			stmt.setString(1, tagBusca);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				listaId.add(rs.getInt("mensagem_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaId;

	}

	public List<Mensagem> getListaBusca(List<Integer> listaId) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();		
		ResultSet rs = null;
		ResultSet rs2 = null;

		for (Integer id : listaId) {
			PreparedStatement stmt = null;
			PreparedStatement stmt2 = null;
			try {
				stmt = connection.prepareStatement("SELECT * FROM mensagens WHERE mensagens.Id_mensagem = ?");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.setInt(1, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					try {
					stmt2 = connection.prepareStatement("SELECT * FROM tag WHERE mensagem_id = " + rs.getInt("Id_mensagem"));
					rs2 = stmt2.executeQuery();
					} catch (Exception e) {
						// TODO: handle exception

					}
					
					List<String> tagis = new ArrayList<String>();
					List<Integer> idtagis = new ArrayList<Integer>();
					while (rs2.next()) {
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
			

		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("ué");
			e.printStackTrace();
		}
		return mensagens;

	}

	public Mensagem getMensagemByID(Integer id) {
		//Função que utiliza o ID da mensagem para enviar para o EDIT os dados da mensagem e as tags
		
		Mensagem mensagem = new Mensagem();
		String sql = "SELECT * FROM mensagens WHERE Id_mensagem="+id;
		String sql2 = "SELECT * FROM tag WHERE mensagem_id="+id;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
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
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs2 = stmt2.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mensagem.setId(id);
		try {
			mensagem.setMens(rs.getString("mensagem"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> tags = new ArrayList<String>();
		List<Integer> idtags = new ArrayList<Integer>();
		try {
			while (rs2.next()) {
				tags.add(rs2.getString("tags"));
				idtags.add(rs2.getInt("mensagem_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mensagem.setTag(tags);
		mensagem.setTag_id(idtags);		
		return mensagem;
		
	}
}