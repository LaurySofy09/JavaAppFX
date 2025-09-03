import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

public class ConsultaCedulaOracle {

    public static ResultadoConsulta consultarCedula(String ced1, String ced2, String ced3) {
        ResultadoConsulta resultado = new ResultadoConsulta(); // ← AQUÍ ESTÁ LA CORRECCIÓN

        try (Connection conn = ConexionOracle.conectar()) {
            if (conn != null) {
                String call = "{ call EPD.procesar_planilla_vs_banco(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
                CallableStatement stmt = conn.prepareCall(call);

                stmt.setString(1, ced1);
                stmt.setString(2, ced2);
                stmt.setString(3, ced3);

                stmt.registerOutParameter(4, Types.VARCHAR); // nombres
                stmt.registerOutParameter(5, Types.VARCHAR); // apellido_p
                stmt.registerOutParameter(6, Types.VARCHAR); // apellido_m
                stmt.registerOutParameter(7, Types.VARCHAR); // apellido_c
                stmt.registerOutParameter(8, Types.VARCHAR); // tib
                stmt.registerOutParameter(9, Types.VARCHAR); // descripcion_tib
                stmt.registerOutParameter(10, Types.DATE);   // fecha_ini_lab
                stmt.registerOutParameter(11, Types.VARCHAR); // cod_banco
                stmt.registerOutParameter(12, Types.VARCHAR); // cuenta_num
                stmt.registerOutParameter(13, Types.VARCHAR); // cuenta_tipo
                stmt.registerOutParameter(14, Types.VARCHAR); // descripcion_banco

                stmt.execute();

                resultado.setNombres(stmt.getString(4));
                resultado.setApellidos(
                        stmt.getString(5) + " " +
                                stmt.getString(6) + " " +
                                stmt.getString(7)
                );
                resultado.setTipo(stmt.getString(8));
                resultado.setDescripcionTipo(stmt.getString(9));
                resultado.setFechaInicio(stmt.getDate(10));
                resultado.setBanco(stmt.getString(11));
                resultado.setCuenta(stmt.getString(12));
                resultado.setCuentaTipo(stmt.getString(13));
                resultado.setCuentaTipo(stmt.getString(14));

                resultado.setEncontrado(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado.setEncontrado(false);
            resultado.setError(ex.getMessage());
        }

        return resultado;
    }
}
