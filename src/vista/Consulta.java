/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.PeliculaDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Pelicula;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class Consulta extends JFrame{
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAnio, lblEn_Proyeccion;

    public JTextField nombre, director, pais, anio;
    
    public JComboBox clasificacion;

    
    public JCheckBox proyeccion;
    
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Pelicula");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAnio);
        container.add(lblEn_Proyeccion);
        container.add(nombre);
        container.add(director);
        container.add(proyeccion);
        container.add(pais);
        container.add(clasificacion);
        container.add(anio);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        setSize(650, 650);
        eventos();
    }

    public final void agregarLabels() {
        lblNombre = new JLabel("Nombre:");
        lblDirector = new JLabel("Director:");
        lblPais = new JLabel("Pais:");
        lblClasificacion   = new JLabel("Clasificaion:");
        lblAnio   = new JLabel("Anio:");
        lblEn_Proyeccion   = new JLabel("Proyeccion:");
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 140, ANCHOC, ALTOC);
        lblPais.setBounds(10,200 , ANCHOC, ALTOC);
        lblClasificacion.setBounds(250,60 , ANCHOC, ALTOC);
        lblAnio.setBounds(250,140 , ANCHOC, ALTOC);
        lblEn_Proyeccion.setBounds(250,200, ANCHOC, ALTOC);
    }

    public final void formulario() {
        nombre = new JTextField();
        director = new JTextField();
        pais = new JTextField();
        clasificacion = new JComboBox();
        anio = new JTextField();
        proyeccion = new JCheckBox();
        
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");

        table = new JPanel();
        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");
        
        proyeccion = new JCheckBox();
        

        nombre.setBounds(80, 50, ANCHOC, ALTOC);
        director.setBounds(80, 140, 100, ALTOC);
        pais.setBounds(80, 200, 100, ALTOC);
        clasificacion.setBounds(350, 50, 100, ALTOC);
        anio.setBounds(350, 140, 100, ALTOC);
        proyeccion.setBounds(350, 200, 100, ALTOC);//(x,y,ANCHOC,ALTOC)
        

        buscar.setBounds(450, 300, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 350, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificaion");
        tm.addColumn("Anio");
        tm.addColumn("Proyeccion");

        PeliculaDao pd = new PeliculaDao();
        ArrayList<Pelicula> filtros = pd.readAll();

        for (Pelicula p : filtros) {
            System.out.println(p.getEn_proyeccion());
            tm.addRow(new Object[]{p.getNombre(), p.getDirector(), p.getPais(), p.getClasificacion(),  p.getAnio(),  p.getEn_proyeccion()});
        }

        resultados.setModel(tm);
    }

   
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao pd = new PeliculaDao();
                Pelicula p = new Pelicula(nombre.getText(), director.getText(), pais.getText(), clasificacion.getSelectedItem().toString(),Integer.parseInt(anio.getText()), proyeccion.isSelected());

//                if (proyeccion.isSelected()) {
//                    p.setEn_proyeccion(true);
//                } else {
//                    p.setEn_proyeccion(false);
//                }
                System.out.println(proyeccion.isSelected());
                
                
                

                if (pd.create(p)) {
                    JOptionPane.showMessageDialog(null, "Pelicula registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear la pelicula");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao pd = new PeliculaDao();
                Pelicula p = new Pelicula(nombre.getText(), director.getText(), pais.getText(), clasificacion.getSelectedItem().toString(),Integer.parseInt(anio.getText()), true);

                if (proyeccion.isSelected()) {
                    p.setEn_proyeccion(true);
                }

                if (pd.update(p)) {//cambiar en metodo de Object Key a Generic g.
                    JOptionPane.showMessageDialog(null, "Pelicula modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar la pelicula");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao pd = new PeliculaDao();
                if (pd.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Pelicula eliminada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar la pelicula");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao pd = new PeliculaDao();
                Pelicula p = pd.read(nombre.getText());
                if (p == null) {
                    JOptionPane.showMessageDialog(null, "La pelicula buscada no se ha encontrado");
                } else {
                    nombre.setText(String.valueOf(p.getNombre()));
                    nombre.setText(p.getNombre());
                    director.setText(p.getDirector());
                    pais.setText(p.getPais());
                    clasificacion.setSelectedItem(p.getClasificacion());
                    //anio.setText(Integer.parseInt(p.getAnio()));//no me convierte int a string
                    //proyeccion.setText(Boolean.parseBoolean(p.getEn_proyeccion()));//no me convierte boolean a string

                    if (p.getEn_proyeccion()) {
                        proyeccion.setSelected(true);
                    } 
                }
            }
        });
        
        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    director.setText(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    pais.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
//                    edad.setText(resultados.getValueAt(resultados.getSelectedRow(), 3).toString()); 
                    clasificacion.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 3).toString()); 
                    anio.setText(resultados.getValueAt(resultados.getSelectedRow(), 4).toString()); 
                    proyeccion.setText(resultados.getValueAt(resultados.getSelectedRow(), 5).toString()); 
                    if (resultados.getValueAt(resultados.getSelectedRow(), 5).toString() == "false") {
                        proyeccion.setSelected(true);
                    }
                }
            }
        });
    }
    
    public void limpiarCampos() {
        nombre.setText("");
        director.setText("");
        pais.setText("");
        clasificacion.setSelectedItem("");
        anio.setText("");
        proyeccion.setSelected(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
    
}