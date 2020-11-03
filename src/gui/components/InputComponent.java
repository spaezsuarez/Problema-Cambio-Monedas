package gui.components;

//Elementos Graficos
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
//Elementos Decorativos
import java.awt.Font;
import java.awt.Dimension;


public class InputComponent extends JPanel {

    private final JTextField[] monedas;

    public InputComponent(int numeroMonedas,int ancho,int alto) {
        
        setSize(new Dimension(ancho,alto));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        
        monedas = new JTextField[numeroMonedas];

    }
   
    public void initInputElements() {
        for (int i = 0; i < monedas.length; i++) {
            monedas[i] = new JTextField();
            monedas[i].setPreferredSize(new Dimension(30,20));
            monedas[i].setHorizontalAlignment(JTextField.CENTER);
            monedas[i].setFont(new Font("Arial", Font.BOLD, 15));
            this.add(monedas[i]);
        }

        repaint();
    }

    public void removeInputElements() {
        removeAll();
        repaint();
    }

    public JTextField[] getArticulos() {
        return monedas;
    }
}
