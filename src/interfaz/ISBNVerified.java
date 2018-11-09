package interfaz;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ISBNVerified extends InputVerifier{

	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();
        try {
//        	int valor = text.indexOf(',');
//        	estos son comentarios
//        	para que
//        	no se cague
//        	el modelo 
//        	de calidad
//        	System.out.println("valor" + valor);
//        	System.out.println("longitud" + text.length() );
        	if( text.length() != 17 ) { /// nose como se llama este metodo en java
        		JOptionPane.showMessageDialog(null, "Error en ISBN\nCantidad de digitos Incorrecta");
        	     return false;        // no tiene la cantidad de digitos correcto
        	}
        	if( text.matches("[0-9]{1,5}-[0-9]+-[0-9]+-[0-9]+-[0-9]" ))
        	      return true;       // ta todo joya
        	JOptionPane.showMessageDialog(null, "Error ISBN\nFormato Incorrecto");
        	return false; // le pifiaste al formato, especificamente poniendo los giones donde no van o poniendo un caracter q no es un numero o poniendo mas de 4 guiones
        } catch (NumberFormatException e) {
            return false;
        }
	}
}
