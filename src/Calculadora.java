import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Calculadora {
    public static void main(String[] args) {
        new MarcoDeCalculadora();
    }
}
class MarcoDeCalculadora extends JFrame{
    public MarcoDeCalculadora(){
        setVisible(true);
        setBounds(600, 200, 400, 400);
        setTitle("Calculadora en Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new LayerDeCalculadora());
        //pack();//el tamaño por defecto de los componentes que agregamos
    }
}
class LayerDeCalculadora extends JPanel{
    public LayerDeCalculadora(){
        Principio = true;
        setLayout(new BorderLayout());
        pantalla = new JButton("0");
        pantalla.setEnabled(false);   //no podemos interactuar con el boton
        add(pantalla,BorderLayout.NORTH);
        teclas = new JPanel();
        teclas.setLayout(new GridLayout(4,4)); 
        ActionListener insertar = new InsertaNumero();
        ActionListener operacion = new AccionOrden();
        ponerBoton("7",insertar);ponerBoton("8",insertar);ponerBoton("9",insertar);ponerBoton("x",operacion);
        ponerBoton("4",insertar);ponerBoton("5",insertar);ponerBoton("6",insertar);ponerBoton("-",operacion);
        ponerBoton("1",insertar);ponerBoton("2",insertar);ponerBoton("3",insertar);ponerBoton("+",operacion);
        ponerBoton("0",insertar);ponerBoton(",",insertar);ponerBoton("/",operacion);ponerBoton("=",operacion);
        add(teclas,BorderLayout.CENTER);
        ultimaoperacion = "=";

    }
    private void ponerBoton(String nombre_tecla, ActionListener oyente){
        JButton Boton = new JButton(nombre_tecla);
        Boton.addActionListener(oyente);
        teclas.add(Boton);
    }
    private double resultado = 0;
    private boolean Principio;
    private JButton pantalla;
    private JPanel teclas ;  //la declaro aca para poder usarla tanto en constructor como en metodos
    private String ultimaoperacion;

    private class InsertaNumero implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String entrada = e.getActionCommand();  //devuelve string asociado con el boton
            if (Principio == true){pantalla.setText(""); Principio = false;}
            pantalla.setText(pantalla.getText() + entrada);         
            
        }

    }
    private class AccionOrden implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String operacion = e.getActionCommand();   //almacena el texto del boton
            calcular(Double.parseDouble(pantalla.getText()));   //manda los digitos de la pantalla a hacer el calculo
            ultimaoperacion = operacion;            //almacena operaciones
            Principio = true;                                   //limpia la pantalla

        }
    }
    public void calcular(Double x){

        if(ultimaoperacion.equals("+")){resultado+=x;}
        else if (ultimaoperacion.equals("-")){resultado-=x;}
        else if (ultimaoperacion.equals("x")){resultado*=x;}
        else if (ultimaoperacion.equals("/")){resultado/=x;}
        else if(ultimaoperacion.equals("=")) {resultado=x;}
        pantalla.setText("" + resultado);  //resultado es tipo double, lo concatenamos con un string vacio y lo "convertimos en string"
    }
}