/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcl.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import orcl.entity.Produtos;
import orcl.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oracle
 */
public class menuProdutos extends javax.swing.JFrame {

    /**
     * Creates new form menuProdutos
     */
    public menuProdutos() {
        initComponents();
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
    }

    private static String SELECT = "from Produtos";

    private void insertRow(String cod, String desc, int prezo) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            String id = null;
            tx = session.getTransaction();
            session.beginTransaction();
            Produtos p = new Produtos(cod, desc, BigDecimal.valueOf(prezo));
            session.save(p);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private void updateAll(String cod, String desc, String prezo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.getTransaction();
        session.beginTransaction();
        Produtos p = (Produtos) session.get(Produtos.class, cod);

        if (!desc.isEmpty()) {
            p.setDescricion(desc);
        }

        if (!prezo.isEmpty()) {
            p.setPrezo(BigDecimal.valueOf(Integer.parseInt(prezo)));
        }

        session.update(p);
        tx.commit();
    }

    private void updatePrezo(String cod, int prezo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.getTransaction();
        session.beginTransaction();
        Produtos p = (Produtos) session.get(Produtos.class, cod);

        p.setPrezo(BigDecimal.valueOf(prezo));
        session.update(p);
        tx.commit();
    }

    private void updateDesc(String cod, String desc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.getTransaction();
        session.beginTransaction();
        Produtos p = (Produtos) session.get(Produtos.class, cod);
        p.setDescricion(desc);
        session.update(p);
        tx.commit();
    }

    private void deleteProduto(String cod) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            tx = session.getTransaction();
            session.beginTransaction();
            Produtos p = new Produtos(cod);
            session.delete(p);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private void selectAll() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(SELECT);
            ArrayList resultList = (ArrayList) q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private void selectCod(String cod) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(SELECT + " p where p.codigo=('" + cod + "')");
            ArrayList resultList = (ArrayList) q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private void actualizacionMasiva(String desc, int prezoref) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("update produtos set descricion='" + desc + "' where prezo>" + prezoref);
        query.executeUpdate();
        tx.commit();

        /*OTRA FORMA
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("update produtos set descricion='" + desc + "' where prezo>" + prezoref);
        query.executeUpdate();
        session.getTransaction().commit();*/
    }

    private void displayResult(ArrayList resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("Código");
        tableHeaders.add("Descrición");
        tableHeaders.add("Prezo");

        for (Object o : resultList) {
            Produtos pro = (Produtos) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(pro.getCodigo());
            oneRow.add(pro.getDescricion());
            oneRow.add(pro.getPrezo());
            tableData.add(oneRow);
        }
        Tabla.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lMenuProd = new javax.swing.JLabel();
        lCodigo = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        lDescricion = new javax.swing.JLabel();
        tDescricion = new javax.swing.JTextField();
        lPrezo = new javax.swing.JLabel();
        tPrezo = new javax.swing.JTextField();
        bInsertar = new javax.swing.JButton();
        bBuscar = new javax.swing.JButton();
        bModPrez = new javax.swing.JButton();
        bModDesc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        bBorrar = new javax.swing.JButton();
        bModificarTodo = new javax.swing.JButton();
        bBuscarCod = new javax.swing.JButton();
        bActMasiva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lMenuProd.setFont(new java.awt.Font("DejaVu Sans", 0, 48)); // NOI18N
        lMenuProd.setText("Menú de produtos");

        lCodigo.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        lCodigo.setText("Código:");

        lDescricion.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        lDescricion.setText("Descrición:");

        lPrezo.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        lPrezo.setText("Prezo:");

        bInsertar.setText("Insertar");
        bInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertarActionPerformed(evt);
            }
        });

        bBuscar.setText("Buscar");
        bBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarActionPerformed(evt);
            }
        });

        bModPrez.setText("Modificar prezo");
        bModPrez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModPrezActionPerformed(evt);
            }
        });

        bModDesc.setText("Modificar descrición");
        bModDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModDescActionPerformed(evt);
            }
        });

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Descrición", "Prezo"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        bBorrar.setText("Borrar");
        bBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorrarActionPerformed(evt);
            }
        });

        bModificarTodo.setText("Modificar todo");
        bModificarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarTodoActionPerformed(evt);
            }
        });

        bBuscarCod.setText("Buscar código");
        bBuscarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarCodActionPerformed(evt);
            }
        });

        bActMasiva.setText("Actualización masiva");
        bActMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActMasivaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(282, 282, 282)
                            .addComponent(lMenuProd))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lCodigo)
                            .addGap(20, 20, 20)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(81, 81, 81)
                            .addComponent(lDescricion)
                            .addGap(36, 36, 36)
                            .addComponent(tDescricion, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(87, 87, 87)
                            .addComponent(lPrezo)
                            .addGap(18, 18, 18)
                            .addComponent(tPrezo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(bInsertar)
                            .addGap(18, 18, 18)
                            .addComponent(bBuscar)
                            .addGap(18, 18, 18)
                            .addComponent(bBuscarCod)
                            .addGap(18, 18, 18)
                            .addComponent(bModPrez)
                            .addGap(18, 18, 18)
                            .addComponent(bModDesc)
                            .addGap(29, 29, 29)
                            .addComponent(bModificarTodo)
                            .addGap(18, 18, 18)
                            .addComponent(bBorrar)
                            .addGap(18, 18, 18)
                            .addComponent(bActMasiva))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lMenuProd)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricion)
                    .addComponent(tDescricion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCodigo)
                    .addComponent(lPrezo)
                    .addComponent(tPrezo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBuscar)
                    .addComponent(bModPrez)
                    .addComponent(bInsertar)
                    .addComponent(bModDesc)
                    .addComponent(bBorrar)
                    .addComponent(bModificarTodo)
                    .addComponent(bBuscarCod)
                    .addComponent(bActMasiva))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed
        selectAll();
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertarActionPerformed
        insertRow(tCodigo.getText(), tDescricion.getText(), Integer.parseInt(tPrezo.getText()));
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bInsertarActionPerformed

    private void bBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorrarActionPerformed
        deleteProduto(tCodigo.getText());
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bBorrarActionPerformed

    private void bModificarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarTodoActionPerformed
        updateAll(tCodigo.getText(), tDescricion.getText(), tPrezo.getText());
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bModificarTodoActionPerformed

    private void bBuscarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarCodActionPerformed
        selectCod(tCodigo.getText());
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
    }//GEN-LAST:event_bBuscarCodActionPerformed

    private void bModPrezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModPrezActionPerformed
        updatePrezo(tCodigo.getText(), Integer.parseInt(tPrezo.getText()));
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bModPrezActionPerformed

    private void bModDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModDescActionPerformed
        updateDesc(tCodigo.getText(), tDescricion.getText());
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bModDescActionPerformed

    private void bActMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActMasivaActionPerformed
        actualizacionMasiva(tDescricion.getText(), Integer.parseInt(tPrezo.getText()));
        tCodigo.setText("");
        tDescricion.setText("");
        tPrezo.setText("");
        selectAll();
    }//GEN-LAST:event_bActMasivaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton bActMasiva;
    private javax.swing.JButton bBorrar;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bBuscarCod;
    private javax.swing.JButton bInsertar;
    private javax.swing.JButton bModDesc;
    private javax.swing.JButton bModPrez;
    private javax.swing.JButton bModificarTodo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCodigo;
    private javax.swing.JLabel lDescricion;
    private javax.swing.JLabel lMenuProd;
    private javax.swing.JLabel lPrezo;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tDescricion;
    private javax.swing.JTextField tPrezo;
    // End of variables declaration//GEN-END:variables
}
