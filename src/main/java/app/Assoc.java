package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Assoc {

    private String id;
    private String fromCard;
    private String fromName;
    private String toCard;
    private String toName;
    private Klasse from;
    private Klasse to;
    
    
    
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
    public Assoc withId(String value) {
    
        setId(value);
        return this;
    }
    /**
    *   F I E L D  FROMCARD
    */
    public static final String PROPERTY_FROMCARD = "fromCard";
    public String getFromCard() {
    
        return this.fromCard;
    }
    public void setFromCard(String value) {
    
        if (this.fromCard != value)
          {         
            String oldValue = this.fromCard;
            this.fromCard = value;
            firePropertyChange(PROPERTY_FROMCARD, oldValue, value);
          }
    }
    public Assoc withFromCard(String value) {
    
        setFromCard(value);
        return this;
    }
    /**
    *   F I E L D  FROMNAME
    */
    public static final String PROPERTY_FROMNAME = "fromName";
    public String getFromName() {
    
        return this.fromName;
    }
    public void setFromName(String value) {
    
        if (this.fromName != value)
          {         
            String oldValue = this.fromName;
            this.fromName = value;
            firePropertyChange(PROPERTY_FROMNAME, oldValue, value);
          }
    }
    public Assoc withFromName(String value) {
    
        setFromName(value);
        return this;
    }
    /**
    *   F I E L D  TOCARD
    */
    public static final String PROPERTY_TOCARD = "toCard";
    public String getToCard() {
    
        return this.toCard;
    }
    public void setToCard(String value) {
    
        if (this.toCard != value)
          {         
            String oldValue = this.toCard;
            this.toCard = value;
            firePropertyChange(PROPERTY_TOCARD, oldValue, value);
          }
    }
    public Assoc withToCard(String value) {
    
        setToCard(value);
        return this;
    }
    /**
    *   F I E L D  TONAME
    */
    public static final String PROPERTY_TONAME = "toName";
    public String getToName() {
    
        return this.toName;
    }
    public void setToName(String value) {
    
        if (this.toName != value)
          {         
            String oldValue = this.toName;
            this.toName = value;
            firePropertyChange(PROPERTY_TONAME, oldValue, value);
          }
    }
    public Assoc withToName(String value) {
    
        setToName(value);
        return this;
    }
    /**
    *   F I E L D  FROM
    */
    public static final String PROPERTY_FROM = "from";
    public Klasse getFrom() {
    
        return this.from;
    }
    public void setFrom(Klasse value) {
    
        if (this.from != value)
          {         
            Klasse oldValue = this.from;
            this.from = value;
            firePropertyChange(PROPERTY_FROM, oldValue, value);
          }
    }
    public Assoc withFrom(Klasse value) {
    
        setFrom(value);
        return this;
    }
    /**
    *   F I E L D  TO
    */
    public static final String PROPERTY_TO = "to";
    public Klasse getTo() {
    
        return this.to;
    }
    public void setTo(Klasse value) {
    
        if (this.to != value)
          {         
            Klasse oldValue = this.to;
            this.to = value;
            firePropertyChange(PROPERTY_TO, oldValue, value);
          }
    }
    public Assoc withTo(Klasse value) {
    
        setTo(value);
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
