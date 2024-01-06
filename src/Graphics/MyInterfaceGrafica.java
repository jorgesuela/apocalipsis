package Graphics;

import Activable.Superviviente;
import Activable.Zombi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class MyInterfaceGrafica implements Serializable{

    private List<Zombi> zombis;
    private List<Superviviente> supervivientes;
    private JFrame myFrame;
    private JPanel myPanel;

    public MyInterfaceGrafica(List<Zombi> zombis, List<Superviviente> supervivientes, int tamano) {
        this.zombis = zombis;
        this.supervivientes = supervivientes;
        initializeFrame(tamano);
    }

    private void initializeFrame(int tamano) {
        myFrame = new JFrame("apocalipsis");
        myFrame.setBounds(10, 10, 512, 512);
        myFrame.setSize(tamano*66, tamano*70);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new JPanel(new GridLayout(tamano, tamano)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int y = 0; y < tamano; y++) {
                    for (int x = 0; x < tamano; x++) {
                        if ((x + y) % 2 == 0) {
                            g.setColor(Color.LIGHT_GRAY);
                        } else {
                            g.setColor(Color.DARK_GRAY);
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                    }
                }
                supervivientes.forEach(s -> drawImage(g, s.getPosicion().getCoordy(), s.getPosicion().getCoordx(), 64, "/ashabajo.png"));
                zombis.forEach(z -> drawImage(g, z.getPosicion().getCoordy(), z.getPosicion().getCoordx(), 64, z.getImageName()));
                drawImage(g, tamano-1, tamano-1, 64, "/exit.png");
            }
        };

        myFrame.add(myPanel);
    }

    public void drawImage(Graphics g, int x, int y, int size, String imagePath) {
        try {
            URL imageURL = getClass().getResource(imagePath);
            if (imageURL != null) {
                Image image = ImageIO.read(imageURL);
                g.drawImage(image, x * size, y * size, size, size, null);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void repaintPanel() {
        myPanel.repaint();
    }

    public void showFrame() {
        myFrame.setVisible(true);
    }


}