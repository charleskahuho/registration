import javax.lang.model.element.NestingKind;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class login {
    private JTextField name;
    private JTextField username;
    private JTextField password;
    private JTextField confirm;
    private JTextField email;
    private JTextField phone;
    private JTextField address;
    private JButton submit;
    private JButton close;
    private JButton reset;
    private JPanel register;

    public login() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = String.valueOf(password.getText());
                String conf = String.valueOf(confirm.getText());

                if(!pass.equals(conf)){
                    JOptionPane.showMessageDialog(null, "passowrd des not match");
                }else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cat", "root", "124567");
                        String sql = "INSERT INTO REGISTER(name, username, password, email, phone, address) VALUES(?,?,?,?,?,?)";
                        PreparedStatement pr = con.prepareStatement(sql);
                        pr.setString(1, name.getText());
                        pr.setString(2, username.getText());
                        pr.setString(3,pass);
                        pr.setString(4, email.getText());
                        pr.setString(5, phone.getText());
                        pr.setString(6, address.getText());
                        int count = 0;
                        if (count == 1){
                            JOptionPane.showMessageDialog(null, "submission accepted");
                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                username.setText("");
                password.setText("");
                confirm.setText("");
                email.setText("");
                phone.setText("");
                address.setText("");

            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }

    public static void main(String[] args) {
        login r = new login();
        r.setContentPane(r.register);
        int width = 400, height = 300;
        int x = (1368-width)/2;
        int y = (768 - height)/2;
        r.setSize(width, height);
        r.setBounds(x, y, width, height);
        r.setVisible(true);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
