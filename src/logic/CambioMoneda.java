package logic;

import javax.swing.JTextField;

public class CambioMoneda {
    
    private final double[][] matrizNumeros;
    private double[] monedas;
    private final String[][] matrizComposicion;

    public double[][] getMatrizNumeros() {
        return matrizNumeros;
    }

    public String[][] getMatrizComposicion() {
        return matrizComposicion;
    }

    public double[] getMonedas() {
        return monedas;
    }
    
    public void setMonedas(JTextField[] elements){
        monedas = new double[elements.length];
        
        for(int i = 0; i < elements.length; i++){
            monedas[i] = Double.parseDouble(elements[i].getText());
        }
        
        for (int i = 0; i < monedas.length - 1; i++) {
            for (int j = i + 1; j < monedas.length; j++) {
                if (monedas[i] > monedas[j]) {
                    double monedaTemp = monedas[i];
                    monedas[i] = monedas[j];
                    monedas[j] = monedaTemp;
                }
            }
        }
    }
    
    
    public CambioMoneda(JTextField[] elements,int monedas,int valor){
        matrizNumeros = new double[monedas+1][valor+1];
        matrizComposicion = new String[monedas+1][valor+1];
        setMonedas(elements);
    }
    
    public double min(double num1,double num2,int i,int j){
        if(num1 < num2){
            matrizComposicion[i][j] = String.valueOf(matrizComposicion[i-1][j]);
            return num1;
        }
        matrizComposicion[i][j] = String.valueOf("1:"+(int)monedas[i-1]+"+\n"+matrizComposicion[i][j - (int)monedas[i-1]]);
        return num2;
    }
    
    public void initMatrizNumeros(){
        
        for(int k = 0; k < matrizNumeros[0].length; k++){
            matrizNumeros[0][k] = Double.POSITIVE_INFINITY;
        }
        
        for(int z = 1; z < matrizNumeros.length; z++){
            matrizNumeros[z][0] = 0;
            matrizComposicion[z][0] = "0:0";
        }
        
        for(int i = 0; i < matrizComposicion.length; i++){
            for(int j = 0; j < matrizComposicion[i].length;j++){
                matrizComposicion[i][j] = "0:0";
            }
        }
        
        for(int i = 1; i < matrizNumeros.length; i++){
            for(int j = 1; j < matrizNumeros[0].length; j++ ){
                             
                if(i == 1 &&  j < monedas[i-1]){
                    matrizNumeros[i][j] = Double.POSITIVE_INFINITY;
                }else if(i == 1){
                    matrizNumeros[i][j] = 1 + matrizNumeros[1][j-(int)monedas[0]];
                    matrizComposicion[i][j] = String.valueOf("1:"+(int)monedas[0]+"+\n"+matrizComposicion[1][j - (int)monedas[0]]);
                        
                }else if(j < monedas[i-1]){
                    matrizNumeros[i][j] = matrizNumeros[i-1][j];
                    matrizComposicion[i][j] = matrizComposicion[i-1][j];
                    
                }else{
                    matrizNumeros[i][j] = min(matrizNumeros[i-1][j],1+matrizNumeros[i][j-(int)monedas[i-1]],i,j);
                    
                }   
            }
        }
    }
    
    
}
