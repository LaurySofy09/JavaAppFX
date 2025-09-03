import java.sql.Date;

public class ResultadoConsulta {
    private boolean encontrado;
    private String nombres;
    private String apellidos;
    private String tipo;
    private String descripcionTipo;
    private Date fechaInicio;
    private String banco;
    private String cuenta;
    private String cuentaTipo;
    private String error;

    // Getters y Setters

    public boolean isEncontrado() { return encontrado; }
    public void setEncontrado(boolean encontrado) { this.encontrado = encontrado; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcionTipo() { return descripcionTipo; }
    public void setDescripcionTipo(String descripcionTipo) { this.descripcionTipo = descripcionTipo; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getCuentaTipo() { return cuentaTipo; }
    public void setCuentaTipo(String cuentaTipo) { this.cuentaTipo = cuentaTipo; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
