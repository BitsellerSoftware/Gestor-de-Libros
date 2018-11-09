package interfaz;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ISBNVerified extends InputVerifier{

	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();
//        	int valor = text.indexOf(',');
//        	estos son comentarios
//        	de calidad
//        	System.out.println("valor" + valor);
//        	System.out.println("longitud" + text.length() );
        	if( text.length() != 17 ) { // se fija que tenga la cantidad de digitos correcto
        		JOptionPane.showMessageDialog(null, "Error en ISBN\nCantidad de digitos Incorrecta");
        	     return false;        // no tiene la cantidad de digitos correcto
        	}
        	if( text.matches("[0-9]{1,5}-[0-9]+-[0-9]+-[0-9]+-[0-9]" ))
        	      return true;       // formato correcto
        	JOptionPane.showMessageDialog(null, "Error ISBN\nFormato Incorrecto");
        	return false; // formato incorrecto, especificamente:
        				  //poniendo los giones donde no van o
        	              //poniendo un caracter q no es un numero o
        				  //poniendo mas de 4 guiones
	}
}
