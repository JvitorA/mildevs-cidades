package br.com.mesttra.cidades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mesttra.cidades.connectionfactory.ConnectionFactory;
import br.com.mesttra.cidades.pojo.CidadePOJO;

public class CidadeDAO {
	
//	INSERT INTO cidades.cidade(
//			ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito)
//			VALUES (?, ?, ?, ?, ?, ?, ?);
	private Connection conn;

	public CidadeDAO() {
		this.conn = ConnectionFactory.getConnection();
	}
	
	public boolean insereCidade(CidadePOJO cidade) {
		
		String sql = "INSERT INTO cidades.cidade(ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, cidade.getDdd());
			stmt.setString(2, cidade.getNome());
			stmt.setInt(3, cidade.getNroHabitantes());
			stmt.setDouble(4, cidade.getRendaPerCapita());
			stmt.setBoolean(5, cidade.isCapital());
			stmt.setString(6, cidade.getEstado());
			stmt.setString(7, cidade.getNomePrefeito());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Erro ao realizar inserção");
			System.err.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public boolean removeCidade(int ddd) {
		
		String sql = "DELETE FROM cidades.cidade WHERE ddd = ?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, ddd);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Erro ao realizar remoção");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	public List<CidadePOJO> listaCidades() {
		
		String sql = "SELECT * FROM cidades.cidade";
		List<CidadePOJO> listaRetornada = new ArrayList<>();
		
		try {
			PreparedStatement stmt  = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				CidadePOJO cidadeRetornada = new CidadePOJO();
				
//				this.ddd = ddd;
//				this.nome = nome;
//				this.nroHabitantes = nroHabitantes;
//				this.rendaPerCapita = rendaPerCapita;
//				this.capital = capital;
//				this.estado = estado;
//				this.nomePrefeito = nomePrefeito;
				
//				INSERT INTO cidades.cidade(
//				ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito)
//				VALUES (?, ?, ?, ?, ?, ?, ?);
				
				cidadeRetornada.setDdd(rs.getInt("ddd"));
				cidadeRetornada.setNome(rs.getString("nome"));
				cidadeRetornada.setNroHabitantes(rs.getInt("nro_habitantes"));
				cidadeRetornada.setRendaPerCapita(rs.getDouble("renda_per_capita"));
				cidadeRetornada.setCapital(rs.getBoolean("capital"));
				cidadeRetornada.setEstado(rs.getString("estado"));
				cidadeRetornada.setNomePrefeito(rs.getString("nome_prefeito"));
				
				listaRetornada.add(cidadeRetornada);
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao listar cidades");
			System.err.println(e.getMessage());
		}
		
		
		return listaRetornada;
	}
	

}
