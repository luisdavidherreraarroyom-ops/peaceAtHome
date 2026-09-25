package com.peaceathome.data;

import com.peaceathome.conexion.Conexion;
import com.peaceathome.model.Propiedad;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropiedadDAO {

    /**
     * Obtiene todas las propiedades registradas, sin importar el agente.
     * @return Lista de propiedades. Lista vacía si no hay ninguna o si ocurre un error.
     */
    public List<Propiedad> listarTodas() {
        List<Propiedad> propiedades = new ArrayList<>();
        String sql = "SELECT id_propiedad, id_agente, direccion, precio, estado, descripcion, tipo FROM dbo.Propiedad";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Propiedad p = new Propiedad();
                p.setIdPropiedad(rs.getInt("id_propiedad"));
                p.setIdAgente(rs.getInt("id_agente"));
                p.setDireccion(rs.getString("direccion"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setTipo(rs.getString("tipo"));
                propiedades.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar las propiedades.");
            e.printStackTrace();
        }

        return propiedades;
    }

    /**
     * Cuenta cuántas propiedades hay registradas en total.
     * @return El número total de propiedades, 0 si hay error.
     */
    public int contarTotal() {
        String sql = "SELECT COUNT(*) AS total FROM dbo.Propiedad";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al contar las propiedades.");
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Busca propiedades aplicando filtros opcionales. Cualquier parámetro puede venir null/vacío para no filtrar por él.
     * @param tipo Tipo de propiedad (Casa, Apartamento, etc.) o null/vacío para no filtrar.
     * @param precioMin Precio mínimo, o null para no filtrar por mínimo.
     * @param precioMax Precio máximo, o null para no filtrar por máximo.
     * @param estado Estado (Disponible, Arrendado, Vendido) o null/vacío para no filtrar.
     * @return Lista de propiedades que cumplen los filtros.
     */
    public List<Propiedad> buscarConFiltros(String tipo, BigDecimal precioMin, BigDecimal precioMax, String estado) {
        List<Propiedad> propiedades = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT id_propiedad, id_agente, direccion, precio, estado, descripcion, tipo FROM dbo.Propiedad WHERE 1=1"
        );

        if (tipo != null && !tipo.isEmpty()) {
            sql.append(" AND tipo = ?");
        }
        if (precioMin != null) {
            sql.append(" AND precio >= ?");
        }
        if (precioMax != null) {
            sql.append(" AND precio <= ?");
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND estado = ?");
        }

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;
            if (tipo != null && !tipo.isEmpty()) {
                ps.setString(index++, tipo);
            }
            if (precioMin != null) {
                ps.setBigDecimal(index++, precioMin);
            }
            if (precioMax != null) {
                ps.setBigDecimal(index++, precioMax);
            }
            if (estado != null && !estado.isEmpty()) {
                ps.setString(index++, estado);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Propiedad p = new Propiedad();
                    p.setIdPropiedad(rs.getInt("id_propiedad"));
                    p.setIdAgente(rs.getInt("id_agente"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setPrecio(rs.getBigDecimal("precio"));
                    p.setEstado(rs.getString("estado"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setTipo(rs.getString("tipo"));
                    propiedades.add(p);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar propiedades con filtros.");
            e.printStackTrace();
        }

        return propiedades;
    }
}