package gui;

//Elementos Graficos
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

//Componentes
import gui.components.InputComponent;
import java.awt.Color;

//Elementos Decoratvos
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;


public class Menu extends JFrame implements ActionListener {

    private final int ANCHO, ALTO;
    private InputComponent listaMonedas;
    private JButton btnStart, btnCampos, btnLimpiar;
    private JLabel title, txtNumeroMonedas, txtValorCambio;
    private JTextField inputNumeroMonedas, inputValorCambia;
    private JPanel panelUno;
    private JScrollPane jsp;

    public Menu() {
        ANCHO = 1200;
        ALTO = 750;
        btnStart = new JButton("Iniciar");
        btnCampos = new JButton("Generar Campos");
        btnLimpiar = new JButton("Limpiar Campos");
        title = new JLabel("Cambio de Monedas con Programación dinámica");
        txtNumeroMonedas = new JLabel("Numero de monedas: ");
        txtValorCambio = new JLabel("Valor de Cambio: ");
        panelUno = new JPanel();
        inputNumeroMonedas = new JTextField();
        inputValorCambia = new JTextField();
    }

    public void initElements() {

        panelUno.setSize(new Dimension(ANCHO - 100, 175));
        panelUno.setBackground(Color.GRAY);
        panelUno.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panelUno.setLocation(50, 100);
        panelUno.setLayout(null);

        btnStart.setSize(new Dimension(200, 50));
        btnStart.setLocation((this.getWidth() - btnStart.getWidth()) / 2, 600);
        btnStart.setFocusable(false);
        btnStart.setFont(new Font("Arial", Font.BOLD, 25));
        btnStart.addActionListener(this);
        add(btnStart);

        title.setSize(new Dimension(700, 100));
        title.setLocation(((this.getWidth() - title.getWidth()) / 2) + 75, 5);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        add(title);

        btnCampos.setSize(new Dimension(200, 35));
        btnCampos.setLocation((panelUno.getWidth() - btnCampos.getWidth()) / 2, 75);
        btnCampos.setFocusable(false);
        btnCampos.setFont(new Font("Arial", Font.BOLD, 16));
        btnCampos.addActionListener(this);
        panelUno.add(btnCampos);

        btnLimpiar.setSize(new Dimension(200, 35));
        btnLimpiar.setLocation((panelUno.getWidth() - btnCampos.getWidth()) / 2, 120);
        btnLimpiar.setFocusable(false);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        btnLimpiar.addActionListener(this);
        panelUno.add(btnLimpiar);

        txtValorCambio.setSize(new Dimension(200, 30));
        txtValorCambio.setLocation(800, 20);
        txtValorCambio.setFont(new Font("Arial", Font.BOLD, 20));
        panelUno.add(txtValorCambio);

        txtNumeroMonedas.setSize(new Dimension(250, 30));
        txtNumeroMonedas.setLocation(50, 20);
        txtNumeroMonedas.setFont(new Font("Arial", Font.BOLD, 20));
        panelUno.add(txtNumeroMonedas);

        inputNumeroMonedas.setSize(30, 30);
        inputNumeroMonedas.setLocation(275, 20);
        inputNumeroMonedas.setHorizontalAlignment(SwingConstants.CENTER);
        panelUno.add(inputNumeroMonedas);

        inputValorCambia.setSize(30, 30);
        inputValorCambia.setLocation(1000, 20);
        inputValorCambia.setHorizontalAlignment(SwingConstants.CENTER);
        panelUno.add(inputValorCambia);

        add(panelUno);

    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Menu");
        Image icon = new ImageIcon(getClass().getResource("../resources/icon.png")).getImage();
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(ANCHO, ALTO));
        initElements();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnLimpiar) {

            listaMonedas.removeInputElements();
            remove(listaMonedas);
            remove(jsp);
            repaint();

        } else if (event.getSource() == btnCampos) {

            try {
                int numeroMonedas = Integer.parseInt(inputNumeroMonedas.getText());
                listaMonedas = new InputComponent(numeroMonedas, ANCHO-20, 100);
                listaMonedas.initInputElements();

                jsp = new JScrollPane(listaMonedas);
                listaMonedas.setPreferredSize(new Dimension(ANCHO-20,100));
                listaMonedas.setLayout(new GridLayout(1,listaMonedas.getArticulos().length));
                jsp.setBounds(10, 300, ANCHO-20, 100);
                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                add(jsp);
                repaint();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (event.getSource() == btnStart) {
            System.out.println("3");
        }
    }

}
