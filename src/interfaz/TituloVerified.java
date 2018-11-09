package interfaz;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TituloVerified extends InputVerifier{
	
	
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
            if(text.length()>0) {
            	return true;}
            else {
            	JOptionPane.showMessageDialog(null, "ERROR EN Titulo");
            return false;}
        } catch (NumberFormatException e) {
            return false;
        }
}
}
