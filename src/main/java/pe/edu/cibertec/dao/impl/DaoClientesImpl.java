package pe.edu.cibertec.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.cibertec.bean.Cliente;
import pe.edu.cibertec.dao.ConnectionFactory;
import pe.edu.cibertec.dao.DaoClientes;

public class DaoClientesImpl implements DaoClientes {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public DaoClientesImpl() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public List<Cliente> clientesQry() {
        List<Cliente> list = null;
        
        String sql = "SELECT "
                + "codigocliente,"
                + "appaterno,"
                + "apmaterno,"
                + "nombres,"
                + "nacimiento,"
                + "direccion,"
                + "referencia,"
                + "IF(genero LIKE '1', 'M', 'F'),"
                + "IF(estado LIKE '1', 'Si', 'No') "
                + "FROM cliente "
                + "ORDER BY appaterno, apmaterno, nombres";

        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            resultSet = ptmt.executeQuery(sql);

            list = new ArrayList<>();

            while (resultSet.next()) {
                Cliente p = new Cliente();

                p.setCodigocliente(resultSet.getInt(1));
                p.setAppaterno(resultSet.getString(2));
                p.setApmaterno(resultSet.getString(3));
                p.setNombres(resultSet.getString(4));
                p.setNacimiento(resultSet.getDate(5));
                p.setDireccion(resultSet.getString(6));
                p.setReferencia(resultSet.getString(7));
                p.setGenero(resultSet.getString(8));
                p.setEstado(resultSet.getString(9));

                list.add(p);
            }

        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return list;
    }

    @Override
    public String clientesIns(Cliente clientes) {
        String result = null;
        String sql = "INSERT INTO cliente("
                + "appaterno,"
                + "apmaterno,"
                + "nombres,"
                + "nacimiento,"
                + "direccion,"
                + "referencia,"
                + "genero,"
                + "estado"
                + ") values(?,?,?,?,?,?,?,?)";

        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, clientes.getAppaterno());
            ptmt.setString(2, clientes.getApmaterno());
            ptmt.setString(3, clientes.getNombres());
            Date date = new Date(  clientes.getNacimiento().getTime() );
            ptmt.setDate(4, date);
            ptmt.setString(5, clientes.getDireccion());
            ptmt.setString(6, clientes.getReferencia());
            ptmt.setString(7, clientes.getGenero());
            ptmt.setString(8, clientes.getEstado());

            int ctos = ptmt.executeUpdate();
            if (ctos == 0) {
                result = "0 filas afectadas";
            }

        } catch (SQLException e) {
            result = e.getMessage();
        } finally {
            try {
                connection.close();
                result = "1";
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

    @Override
    public String clientesDel(String idcliente) {
        String result = null;
        String sql = "DELETE FROM cliente WHERE codigocliente=?";

        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setInt(1, Integer.valueOf(idcliente));
            int ctos = ptmt.executeUpdate();
            if (ctos == 1) {
                result = "1 filas afectadas";
            }

        } catch (SQLException e) {
            result = e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

    @Override
    public Cliente clientesGet(String idcliente) {
        Cliente p = null;
        
        String sql = "SELECT "
                + "codigocliente,"
                + "appaterno,"
                + "apmaterno,"
                + "nombres,"
                + "nacimiento,"
                + "direccion,"
                + "referencia,"
                + "IF(genero LIKE '1', 'M', 'F'),"
                + "IF(estado LIKE '1', 'Si', 'No') "
                + "FROM cliente "
                + "WHERE codigocliente=?";

        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setInt(1, Integer.valueOf(idcliente));
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                p = new Cliente();
                p.setCodigocliente(resultSet.getInt(1));
                p.setAppaterno(resultSet.getString(2));
                p.setApmaterno(resultSet.getString(3));
                p.setNombres(resultSet.getString(4));
                java.util.Date date = new java.util.Date( resultSet.getDate(5).getTime() );
                p.setNacimiento(date);
                p.setDireccion(resultSet.getString(6));
                p.setReferencia(resultSet.getString(7));
                p.setGenero(resultSet.getString(8));
                p.setEstado(resultSet.getString(9));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return p;
    }

    @Override
    public String clientesUpd(Cliente clientes) {
        String result = null;
        String sql = "UPDATE cliente "
                + "SET appaterno=?, "
                + "apmaterno=?, "
                + "nombres=?, "
                + "nacimiento=?, "
                + "direccion=?, "
                + "referencia=?, "
                + "genero=?, "
                + "estado=? "
                + "WHERE codigocliente=?";

        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, clientes.getAppaterno());
            ptmt.setString(2, clientes.getApmaterno());
            ptmt.setString(3, clientes.getNombres());
            Date date = new Date(  clientes.getNacimiento().getTime() );
            ptmt.setDate(4, date);
            ptmt.setString(5, clientes.getDireccion());
            ptmt.setString(6, clientes.getReferencia());
            ptmt.setString(7, clientes.getGenero());
            ptmt.setString(8, clientes.getEstado());
            ptmt.setInt(9, clientes.getCodigocliente());

            int ctos = ptmt.executeUpdate();
            if (ctos == 0) {
                result = "0 filas afectadas";
            }

        } catch (SQLException e) {
            result = e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }
}

