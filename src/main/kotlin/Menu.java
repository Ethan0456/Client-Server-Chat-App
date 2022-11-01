import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JPanel panel1;
    private JPanel Start;
    private JPanel ClientMenu;
    private JPanel ServerMenu;
    private JButton btnServer;
    private JButton btnClient;
    private JTextField tfIPClient;
    private JTextField tfPortClient;
    private JTextField tfNameClient;
    private JButton btnNextClient;
    private JTextField tfPortServer;
    private JTextField tfNameServer;
    private JButton btnNextServer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public Menu() {
        btnServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start.setVisible(false);
                ServerMenu.setVisible(true);
            }
        });
        btnClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start.setVisible(false);
                ClientMenu.setVisible(true);
            }
        });
        btnNextClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = tfIPClient.getText();
                String port = tfPortClient.getText();
                String name = tfNameClient.getText();

                new Client(ip, port, name);
            }
        });
        btnNextServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String port = tfPortServer.getText();
                String name = tfNameServer.getText() + " (Server)";

                new Server().startServer(port, name);
            }
        });
    }
}
