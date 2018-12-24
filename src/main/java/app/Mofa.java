package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Mofa {

	   /********************************************************************
	    * <pre>
	    *              one                       one
	    * Mofa ----------------------------------- Mofa
	    *              parent                   child
	    * </pre>
	    */
	   
	   public static final String PROPERTY_BEZIEHUNG = "beziehung";

	   private Mofa beziehung = null;

	   public Mofa getBeziehung()
	   {
	      return this.beziehung;
	   }

	   public boolean setBeziehung(Mofa value)
	   {
	      boolean changed = false;
	      
	      if (this.beziehung != value)
	      {
	         Mofa oldValue = this.beziehung;
	         
	         if (this.beziehung != null)
	         {
	            this.beziehung = null;
	            oldValue.setBeziehung(null);
	         }
	         
	         this.beziehung = value;
	         
	         if (value != null)
	         {
	            value.withBeziehung(this);
	         }
	         
	         getPropertyChangeSupport().firePropertyChange(PROPERTY_BEZIEHUNG, oldValue, value);
	         changed = true;
	      }
	      
	      return changed;
	   }

	   public Mofa withBeziehung(Mofa value)
	   {
	      setBeziehung(value);
	      return this;
	   } 

	   public Mofa createBeziehung()
	   {
	      Mofa value = new Mofa();
	      withBeziehung(value);
	      return value;
	   }
	   
	   //==========================================================================
	   
	   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	   
	   public PropertyChangeSupport getPropertyChangeSupport()
	   {
	      return listeners;
	   }
	   
	   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
	   {
	      getPropertyChangeSupport().addPropertyChangeListener(listener);
	      return true;
	   }
	   
	   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
	      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
	      return true;
	   }
	   
	   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
	      getPropertyChangeSupport().removePropertyChangeListener(listener);
	      return true;
	   }

	   
	   //==========================================================================
}
