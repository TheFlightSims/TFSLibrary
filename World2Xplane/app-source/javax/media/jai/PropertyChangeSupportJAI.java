/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ 
/*     */ public final class PropertyChangeSupportJAI extends PropertyChangeSupport {
/*     */   protected Object propertyChangeEventSource;
/*     */   
/*     */   public PropertyChangeSupportJAI(Object propertyChangeEventSource) {
/*  50 */     super(propertyChangeEventSource);
/*  52 */     this.propertyChangeEventSource = propertyChangeEventSource;
/*     */   }
/*     */   
/*     */   public Object getPropertyChangeEventSource() {
/*  61 */     return this.propertyChangeEventSource;
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/*  74 */     if (propertyName == null)
/*  75 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  78 */     super.addPropertyChangeListener(propertyName.toLowerCase(), listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/*  91 */     if (propertyName == null)
/*  92 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  95 */     super.removePropertyChangeListener(propertyName.toLowerCase(), listener);
/*     */   }
/*     */   
/*     */   public void firePropertyChange(PropertyChangeEvent evt) {
/* 107 */     if (!(evt instanceof PropertyChangeEventJAI))
/* 108 */       evt = new PropertyChangeEventJAI(evt.getSource(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue()); 
/* 113 */     super.firePropertyChange(evt);
/*     */   }
/*     */   
/*     */   public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
/* 129 */     PropertyChangeEventJAI evt = new PropertyChangeEventJAI(this.propertyChangeEventSource, propertyName, oldValue, newValue);
/* 132 */     super.firePropertyChange(evt);
/*     */   }
/*     */   
/*     */   public synchronized boolean hasListeners(String propertyName) {
/* 144 */     return super.hasListeners(propertyName.toLowerCase());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyChangeSupportJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */