
package testlogin;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import static sun.misc.ClassFileTransformer.add;

/**
 *
 * @author sa'liou
 */
class JTableDemo 
{
    JFrame f;
    JPanel p1, p2, p3;
    JTabbedPane tp;
    
    JLabel l1, l2, l3,l4;
    JTextField tf1, tf2, tf3,tf4;
    JScrollPane sp1;
    JButton savebtn, resetbtn, editbtn;
 
   public  JTableDemo() {
        f = new JFrame("Gestion de la pharmacie");
        
        p1 = new JPanel(new GridLayout(5, 2));
        p2 = new JPanel(new GridLayout(5, 2));

            
        tp = new JTabbedPane();
        l1 = new JLabel("Id");
        l2 = new JLabel("nom");
        l3 = new JLabel("type");
        l4 = new JLabel("prix");
        tf1 = new JTextField(12); 
        tf2 = new JTextField(12);
        tf3 = new JTextField(12);
        tf4 = new JTextField(12);
        
        savebtn = new JButton(" ajouter ");
        resetbtn = new JButton(" mettre a jour");
        editbtn = new JButton(" afficher");

        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(tf2);
        p1.add(l3);
        p1.add(tf3);
        p1.add(l4);
        p1.add(tf4);
        
        p1.add(savebtn);
        p1.add(resetbtn);

        p2.add(editbtn);
        
        resetbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                clear();
            }
        });
        savebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int idProd,prix;
                String nom, type;
                idProd = Integer.parseInt(tf1.getText());
                prix = Integer.parseInt(tf4.getText());
                nom = tf2.getText();
                type = tf3.getText();

                String url = "jdbc:mysql://localhost:3306/Gestion_Pharmacie";
                String userid = "root";
                String password = "";
                try {
                    Connection connection = DriverManager.getConnection(url,
                            userid, password);
                    Statement st = connection.createStatement();
                    if (nom != "" && type != "" ) {
                        st.executeUpdate("insert into Produit  values('" + idProd
                                + "','" + nom + "','" + prix + "','" + type + "')");
                           JOptionPane.showMessageDialog(null, "Insertion réeussie");
                        clear();
                    } else {
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        editbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                TableFromMySqlDatabase frame = new TableFromMySqlDatabase();
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
               
                frame.getContentPane().setBackground(Color.green);
                 frame.setPreferredSize(new Dimension(1000, 500));
                 
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    void dis() {
        f.getContentPane().add(tp);
        tp.addTab("ajout", p1);
        tp.addTab("Editer/supprimer", p2);

        f.setSize(350, 180);
        f.setVisible(true);
        f.setResizable(true);
    }
    
    void clear()
    {
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    } 

    public static void main(String args[]) {
        JTableDemo data = new JTableDemo();
        data.dis();
       
    }

   

   
}

class TableFromMySqlDatabase extends JFrame {
    public TableFromMySqlDatabase() {
        Vector columnNames = new Vector();
        Vector data = new Vector();

        // Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/Gestion_Pharmacie";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM Produit";

        try {
            Connection connection = DriverManager.getConnection(url, userid,
                    password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            // Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.add(md.getColumnName(i));
            }

            // Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.add(rs.getObject(i));
                }
                row.add("supprimer");
                row.add("mettre à jour");
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        columnNames.add("Action1");
        columnNames.add("Action2");
        // Create table with database data
        JTable table = new JTable(data, columnNames) {
            public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);

                    if (o != null) {
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };

        table.getColumn("Action1").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action1").setCellEditor(
                new ButtonEditor(new JCheckBox(), table));
        table.getColumn("Action2").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action2").setCellEditor(
                new ButtonEditor(new JCheckBox(), table));

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    /*
     * public static void main(String[] args) { TableFromMySqlDatabase frame =
     * new TableFromMySqlDatabase();
     * frame.setDefaultCloseOperation(EXIT_ON_CLOSE); frame.pack();
     * frame.setVisible(true); }
     */

}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;
    JTable t1;
    private boolean isPushed;
    int idProd,prix;
    String nom, type;

    public ButtonEditor(JCheckBox checkBox, JTable t1) {
        super(checkBox);
        this.t1 = t1;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        idProd = Integer.parseInt(t1.getModel().getValueAt(row, 0).toString());
        nom = t1.getModel().getValueAt(row, 1).toString();
         prix = Integer.parseInt(t1.getModel().getValueAt(row, 2).toString());
        type = t1.getModel().getValueAt(row, 3).toString();

        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    // update, delete
    public Object getCellEditorValue() {
        if (isPushed) {
            String url = "jdbc:mysql://localhost:3306/Gestion_Pharmacie";
            String userid = "root";
            String password = "";

            try {
                Connection connection = DriverManager.getConnection(url,
                        userid, password);
                Statement stmt = connection.createStatement();

                if (label.equalsIgnoreCase(" mise à jour ")) {
                    stmt
                            .executeUpdate("update Produit set nom='" + nom
                                    + "', prix='" + prix + "', type='" + type + "' where idProd='"
                                    + idProd + "'");

                    JOptionPane.showMessageDialog(button, label
                            + ":mise à jour réussi");

                } else if (label.equalsIgnoreCase("Supprimer")) {
                    stmt.executeUpdate("delete from Produit where idProd='" + idProd
                            + "'");

                    JOptionPane.showMessageDialog(button, label
                            + ":suppression réeussie");

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
