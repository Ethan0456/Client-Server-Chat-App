import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Graphical extends JFrame {
    // declare components
    private Socket socket;
    private String name;
    private JLabel heading;
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font( "JetBrainsMono Nerd Font Mono" , Font.PLAIN, 20);
    private BufferedReader bufferReader;
    private PrintWriter printWriter;

    // constructor
    public Graphical(String name, Socket socket) {
        try {
            this.socket = socket;
            this.name = name;
            System.out.println("This is " + name);
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            heading = new JLabel(name);
            createGUI();
            runThreadedTextListening();
            handleEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runThreadedTextListening(){
        // thread for textView listening
        Runnable r = ()-> {
            try {
                while (!socket.isClosed()) {
                    String msg = bufferReader.readLine();
                    messageArea.append(msg+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();
    }


    void handleEvents(){
        messageInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    String contentToSend = messageInput.getText();
                    messageArea.append("ME : " + contentToSend + "\n");
                    printWriter.println(name + " : " + contentToSend);
                    printWriter.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }
        });
    }

    private void createGUI()
    {
        // gui code
        this.setTitle("client Messager[END]");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // coding for components
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);

        // setting the frame layout
        this.setLayout(new BorderLayout());

        // adding the components to the frame
        this.add(heading,BorderLayout.NORTH);
        this.add(messageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);


        this.setVisible(true);
    }
}