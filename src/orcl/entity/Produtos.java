package orcl.entity;
// Generated Oct 24, 2019 2:18:02 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Produtos generated by hbm2java
 */
public class Produtos  implements java.io.Serializable {


     private String codigo;
     private String descricion;
     private BigDecimal prezo;

    public Produtos() {
    }

	
    public Produtos(String codigo) {
        this.codigo = codigo;
    }
    public Produtos(String codigo, String descricion, BigDecimal prezo) {
       this.codigo = codigo;
       this.descricion = descricion;
       this.prezo = prezo;
    }
   
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricion() {
        return this.descricion;
    }
    
    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
    public BigDecimal getPrezo() {
        return this.prezo;
    }
    
    public void setPrezo(BigDecimal prezo) {
        this.prezo = prezo;
    }




}


