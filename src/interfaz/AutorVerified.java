package interfaz;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JTextField;

public class AutorVerified extends InputVerifier{
	
//	public boolean test(JComponent input) {
//		   try {
//			  if( input.indexOf(',') <1) {
//		    	 // mandaste un numero muy grande o muy chiiqui
//	        	 return false;
//	         }
//	        	System.out.println("HOLA");
//	         return true;
//	      } catch (NumberFormatException e) {
//	    	  //no mandaste un numero
//	    	  JOptionPane.showMessageDialog(null, "HOLA");
//	         return false;
//	      }
//	   }

	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();
        try {
        	int valor = text.indexOf(',');
        	//System.out.println(valor);
//        	if(text.matches("([a-zA-Z]|,|\\s)+")) {
//        		System.out.println("HOLAAAAA");
//        	}else {
//        		System.out.println("chaaaaau");
//        	}
        	System.out.println("valor" + valor);
        	System.out.println("longitud" + text.length() );
            if(text.matches("([a-zA-Z]|,|\\s)+") && valor > 1 && valor != text.length()-1) {
            	return true;}
            else {
            	JOptionPane.showMessageDialog(null, "ERROR EN AUTOR\nApellido, Nombre");
            return false;}
        } catch (NumberFormatException e) {
            return false;
        }
}
}
//	public void shouldyieldfocus(JComponent input) {
//	      if (verify(input)) {
//	    	  System.out.println("HOLA");
//		      } else {
//		         // no ingresas nada y le avisas al tipo que mando cualquiera
//		    	  JOptionPane.showMessageDialog( null, "autor no valido");
//	}
//
//	
//}
//	}
//	 @Override
//	   public void replace(FilterBypass fb, int offset, int length, String text,
//	         AttributeSet attrs) throws BadLocationException {
//
//	      Document doc = fb.getDocument();
//	      StringBuilder sb = new StringBuilder();
//	      sb.append(doc.getText(0, doc.getLength()));
//	      sb.replace(offset, offset + length, text);
//
//	      if (test(sb.toString())) {
//	         super.replace(fb, offset, length, text, attrs);
//	      } else {
//	         // no ingresas nada y le avisas al tipo que mando cualquiera
//	    	  JOptionPane.showMessageDialog( null, "autor no valido");
//	      }
//
//	   }
//	   
//	   
//	   @Override
//	   public void remove(FilterBypass fb, int offset, int length)
//	         throws BadLocationException {
//	      Document doc = fb.getDocument();
//	      StringBuilder sb = new StringBuilder();
//	      sb.append(doc.getText(0, doc.getLength()));
//	      sb.delete(offset, offset + length);
//	      
//	      //if ( sb.equals("") || test(sb.toString()) ) {
//	      // borra siempre
//	      super.remove(fb, offset, length);
//	      //} else {
//	         // warn the user and don't allow the insert
//	      //  System.out.println("mandaste cualquiera mostro 3");
//	      //}
//
//	   }
//}
