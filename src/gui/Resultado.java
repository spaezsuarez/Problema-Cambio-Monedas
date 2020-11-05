package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JScrollPane;
import logic.CambioMoneda;
import java.util.ArrayList;

public class Resultado extends JFrame implements ActionListener {

    private final int ancho, alto;
    private final double[][] data;
    private final String[][] composiciones;
    private double[] valoresMonedas;

    private JLabel[][] result;
    private JPanel panel;
    private JButton btnItems;

    public Resultado(CambioMoneda controller) {
        ancho = 1400;
        alto = 600;
        panel = new JPanel();
        btnItems = new JButton("Resultado");
        data = controller.getMatrizNumeros();
        result = new JLabel[data.length+1][data[0].length+1];
        composiciones = controller.getMatrizComposicion();
        valoresMonedas = controller.getMonedas();

    }

    public void initComponents() {

        Border border = LineBorder.createGrayLineBorder();
        JScrollPane jsp = new JScrollPane(panel);
        
        panel.setPreferredSize(new Dimension(ancho,alto-100));
        panel.setLayout(new GridLayout(result.length,result[0].length));
        panel.setBackground(new Color(52,58,64));
 
        jsp.setBounds(0, 0,ancho,alto-100);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        int auxCont = 0;
        
        result[0][0] = new JLabel("Valores");
        
        for(int x = 1; x < result.length; x++){
            if(x > 1){
                result[x][0] = new JLabel("Valor " + valoresMonedas[auxCont]);
                auxCont++;
            }else{
                result[x][0] = new JLabel("Valor 0");
            }
            
        }
        int contador = 0;
        
        for(int k = 1; k < result[0].length;k++){
            result[0][k] = new JLabel(String.valueOf(contador));
            contador++;
        }
        
        
        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[i].length; j++) {
               if(data[i-1][j-1] != Double.POSITIVE_INFINITY){
                   result[i][j] = new JLabel("<html><head><></head><body><p> "+ data[i-1][j-1] +"</p><br>" + "<p style='color:yellow;'>"+composiciones[i-1][j-1] + " </p></body></html>");
               }else{
                   result[i][j] = new JLabel("<html><head><></head><body><p style='font-size:30px;'> &infin; </p><br> </body></html>");
               }
            }
        }
        
        
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j].setPreferredSize(new Dimension(60,45));
                result[i][j].setMaximumSize(new Dimension(60,145));
                result[i][j].setHorizontalAlignment(JLabel.CENTER);
                result[i][j].setFont(new Font("Arial", Font.BOLD, 15));
                result[i][j].setForeground(Color.white);
                result[i][j].setBorder(border);
                panel.add(result[i][j]);
            }
        }

        add(jsp);
        
        btnItems.setSize(new Dimension(150,40));
        btnItems.setLocation((this.getWidth()-btnItems.getWidth())/2, 520);
        btnItems.addActionListener(this);
        btnItems.setFont(new Font("Arial", Font.BOLD, 20));
        btnItems.setBackground(new Color(52,58,64));
        btnItems.setForeground(Color.WHITE);
        btnItems.setFocusable(false);
        add(btnItems);

    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Resultado");
        setSize(new Dimension(ancho, alto));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(108,117,125));
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        String respuestaDos = composiciones[composiciones.length-1][composiciones[0].length-1];
        String respuestaFinal = "";

        String[] elementos = respuestaDos.split("\\+");
        ArrayList<Integer> numeros = new ArrayList<>(),posiciones = new ArrayList<>();
        
        
        for(String s: elementos){
            String[] temp = s.split(":");
            if((!temp[0].equals("0")) && (!temp[1].equals("0")) ){
               if(!numeros.contains(Integer.parseInt(temp[1]))){
                numeros.add(Integer.parseInt(temp[1]));
                posiciones.add(0);
               }
                
                
            }
                
        }
        
        for (int i = 0; i < numeros.size() - 1; i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(i) > numeros.get(j)) {
                    int monedaTemp = numeros.get(i);
                    numeros.set(i,numeros.get(j));
                    numeros.set(j,monedaTemp);
                }
            }
        }
        
        for(String s: elementos){
            String[] temp = s.split(":");
            if((!temp[0].equals("0")) && (!temp[1].equals("0")) ){
                posiciones.set(numeros.indexOf(Integer.parseInt(temp[1])),posiciones.get(numeros.indexOf(Integer.parseInt(temp[1])))+1);
            }
                
        }
        
        for(int k = 0; k < numeros.size(); k++){
            if(posiciones.get(k) == 1){
                respuestaFinal += posiciones.get(k)+" moneda de $"+numeros.get(k)+"\n";
            }else{
                respuestaFinal += posiciones.get(k)+" monedas de $"+numeros.get(k)+"\n";
            }
            
        }
        
        JOptionPane.showMessageDialog(null, respuestaFinal,"Su cambio es" ,JOptionPane.DEFAULT_OPTION);
        
        
        
    }

}
