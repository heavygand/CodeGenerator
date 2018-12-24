package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class Änum {

    private String name;
    private String id;
    private HashMap<String, String> literals;
    
    
    
    /**
    *   F I E L D  NAME
    */
    public static final String PROPERTY_NAME = "name";
    public String getName() {
    
        return this.name;
    }
    public void setName(String value) {
    
        if (this.name != value)
          {         
            String oldValue = this.name;
            this.name = value;
            firePropertyChange(PROPERTY_NAME, oldValue, value);
          }
    }
    public Änum withName(String value) {
    
        setName(value);
        return this;
    }
    /**
    *   F I E L D  ID
    */
    public static final String PROPERTY_ID = "id";
    public String getId() {
    
        return this.id;
    }
    public void setId(String value) {
    
        if (this.id != value)
          {         
            String oldValue = this.id;
            this.id = value;
            firePropertyChange(PROPERTY_ID, oldValue, value);
          }
    }
    public Änum withId(String value) {
    
        setId(value);
        return this;
    }
    /**
    *   F I E L D  LITERALS
    */
    public static final String PROPERTY_LITERALS = "literals";
    public HashMap<String, String> getLiterals() {
    
        return this.literals;
    }
    public void setLiterals(HashMap<String, String> value) {
    
        if (this.literals != value)
          {         
            HashMap<String, String> oldValue = this.literals;
            this.literals = value;
            firePropertyChange(PROPERTY_LITERALS, oldValue, value);
          }
    }
    public Änum withLiterals(HashMap<String, String> value) {
    
        setLiterals(value);
        return this;
    }
    
    
/**
*   PROPERTYCHANGESTUFF
*/
    protected PropertyChangeSupport listeners = null;
    
    public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (listeners != null) {
           listeners.firePropertyChange(propertyName, oldValue, newValue);
           return true;
        }
        return false;
     }

     public boolean addPropertyChangeListener(PropertyChangeListener listener)
     {
        if (listeners == null) {
           listeners = new PropertyChangeSupport(this);
        }
        listeners.addPropertyChangeListener(listener);
        return true;
     }

     public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
     {
        if (listeners == null) {
           listeners = new PropertyChangeSupport(this);
        }
        listeners.addPropertyChangeListener(propertyName, listener);
        return true;
     }

     public boolean removePropertyChangeListener(PropertyChangeListener listener)
     {
        if (listeners != null) {
           listeners.removePropertyChangeListener(listener);
        }
        listeners.removePropertyChangeListener(listener);
        return true;
     }

     public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
     {
        if (listeners != null) {
           listeners.removePropertyChangeListener(propertyName, listener);
        }
        return true;
     }
	        
    public PropertyChangeSupport getPropertyChangeSupport()
    {
    	if(listeners == null) listeners = new PropertyChangeSupport(this);
    	
       return listeners;
    }
}
