package interfaz;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EditorialVerified extends InputVerifier{
	
	
	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();

//        	int valor = text.indexOf(',');
//        	estos son comentarios
//        	del modelo 
//        	de calidad
//        	System.out.println("valor" + valor);
//        	System.out.println("longitud" + text.length() );
            if(text.length()>0) {
            	//editorial correcta
            	return true;
            }
            else {
            	//editorial incorrecta
            	JOptionPane.showMessageDialog(null, "ERROR en la editorial");
            	return false;
            }
	}
}
