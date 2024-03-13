import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimplePaint extends JFrame {

    private int currentX, currentY, oldX, oldY;
    private JButton clearButton;
    private JPanel drawPanel;

    public SimplePaint() {
        super("Simple Paint");

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        drawPanel = new JPanel();
        drawPanel.setBackground(Color.WHITE);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                Graphics g = drawPanel.getGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(oldX, oldY, currentX, currentY);
                oldX = currentX;
                oldY = currentY;
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(drawPanel, BorderLayout.CENTER);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void clear() {
        Graphics g = drawPanel.getGraphics();
        g.setColor(drawPanel.getBackground());
        g.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimplePaint().setVisible(true);
            }
        });
    }
}
