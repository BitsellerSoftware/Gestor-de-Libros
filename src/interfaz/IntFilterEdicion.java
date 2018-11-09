package interfaz;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


public class IntFilterEdicion extends DocumentFilter{
	
	 private boolean test(String text) {
	      int numero;
		   try {
	         numero = Integer.parseInt(text);
	         if( numero < 0 ) {
		    	 // numero ingresado no es positivo
	        	 return false;
	         }
	         return true;
	      } catch (NumberFormatException e) {
	    	  //lo que ingresaste no fue un numero
	         return false;
	      }
	   }
	
	 @Override
	   public void replace(FilterBypass fb, int offset, int length, String text,
	         AttributeSet attrs) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.replace(offset, offset + length, text);

	      if (test(sb.toString())) {
	         super.replace(fb, offset, length, text, attrs);
	      } else {
	         // no ingresas nada y avisas que el valor ingresado es incorrecto
	    	  JOptionPane.showMessageDialog( null, "Error en Edicion\nDebe ser un numero mayor a 0");
	      }

	   }
	   
	   
	   @Override
	   public void remove(FilterBypass fb, int offset, int length)
	         throws BadLocationException {
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.delete(offset, offset + length);
	      
	      //if ( sb.equals("") || test(sb.toString()) ) {
	      // borra siempre
	      super.remove(fb, offset, length);
	      //} else {
	         // warn the user and don't allow the insert
	      //  System.out.println("mandaste cualquiera mostro 3");
	      //}

	   }
}
