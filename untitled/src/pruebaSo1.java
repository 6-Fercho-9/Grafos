import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class pruebaSo1 {
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null); // Using null layout for absolute positioning

        JTextField textField = new JTextField("x1 + x2 + x3");
        textField.setBounds(50, 50, 100, 30); // Initial position
        frame.add(textField);

        JButton button = new JButton("Move");
        button.setBounds(150, 300, 100, 30);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            private Timer timer;
            private int targetX = 200;
            private int targetY = 200;
            private int delay = 10; // milliseconds
            private int step = 2; // pixels

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(delay, new ActionListener() {
                    private int currentX = textField.getX();
                    private int currentY = textField.getY();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (currentX < targetX) {
                            currentX += step;
                        } else if (currentX > targetX) {
                            currentX -= step;
                        }
                        if (currentY < targetY) {
                            currentY += step;
                        } else if (currentY > targetY) {
                            currentY -= step;
                        }
                        textField.setLocation(currentX, currentY);

                        if (currentX == targetX && currentY == targetY) {
                            ((Timer)e.getSource()).stop();
                        }
                    }
                });
                timer.start();
            }
        });

        frame.setVisible(true);
    }*/
    public static void main(String[] args) {
        JPanel contenedor=new JPanel();
        JFrame frame = new JFrame("TextField Animation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        contenedor.setBounds(0,0,400,200);
        //frame.setBackground(Color.red);
        frame.setLayout(null); // Usando diseño nulo para posicionamiento absoluto
        frame.setContentPane(contenedor);
        contenedor.setBackground(Color.red);
        JLabel originalLabel = new JLabel("x1 + x2 + x3");
        originalLabel.setBounds(50, 50, 200, 30); // Posición inicial
        frame.add(originalLabel);

        JLabel newLabel = new JLabel();//nuveo label para la animacion(?
        newLabel.setBounds(50, 100, 50, 30); // Posición inicial para x1
        frame.add(newLabel);

        JButton button = new JButton("Animate");
        button.setBounds(260, 50, 100, 30);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            private Timer timer;
            private int delay = 10; // milisegundos
            private int step = 2; // píxeles
            private int targetX = 50; // Posición objetivo para x2 + x3
            private int currentX = 100; // Posición inicial de x2 + x3

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] parts = originalLabel.getText().split(" \\+ ");
                if (parts.length >= 3) {
                    String x1 = parts[0];
                    String x2 = parts[1];
                    String x3 = parts[2];

                    newLabel.setText(x1);

                    originalLabel.setText(x2 + " + " + x3);
                    originalLabel.setBounds(currentX, 50, 200, 30); // Ajustar la posición inicial

                    if (timer != null && timer.isRunning()) {
                        timer.stop();
                    }

                    timer = new Timer(delay, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (currentX > targetX) {
                                currentX -= step;
                                originalLabel.setBounds(currentX, 50, 200, 30); // Mover el texto
                            } else {
                                ((Timer) e.getSource()).stop();
                            }
                        }
                    });
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(frame, "The text field must contain 'x1 + x2 + x3'");
                }
            }
        });

        frame.setVisible(true);
    }
}

