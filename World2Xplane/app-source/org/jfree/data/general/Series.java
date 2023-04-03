/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class Series implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -6906561437538683581L;
/*     */   
/*     */   private Comparable key;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private EventListenerList listeners;
/*     */   
/*     */   private PropertyChangeSupport propertyChangeSupport;
/*     */   
/*     */   private boolean notify;
/*     */   
/*     */   protected Series(Comparable key) {
/* 100 */     this(key, null);
/*     */   }
/*     */   
/*     */   protected Series(Comparable key, String description) {
/* 110 */     if (key == null)
/* 111 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 113 */     this.key = key;
/* 114 */     this.description = description;
/* 115 */     this.listeners = new EventListenerList();
/* 116 */     this.propertyChangeSupport = new PropertyChangeSupport(this);
/* 117 */     this.notify = true;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/* 127 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(Comparable key) {
/* 136 */     if (key == null)
/* 137 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 139 */     Comparable old = this.key;
/* 140 */     this.key = key;
/* 141 */     this.propertyChangeSupport.firePropertyChange("Key", old, key);
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 150 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 159 */     String old = this.description;
/* 160 */     this.description = description;
/* 161 */     this.propertyChangeSupport.firePropertyChange("Description", old, description);
/*     */   }
/*     */   
/*     */   public boolean getNotify() {
/* 173 */     return this.notify;
/*     */   }
/*     */   
/*     */   public void setNotify(boolean notify) {
/* 183 */     if (this.notify != notify) {
/* 184 */       this.notify = notify;
/* 185 */       fireSeriesChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 208 */     Series clone = (Series)super.clone();
/* 209 */     clone.listeners = new EventListenerList();
/* 210 */     clone.propertyChangeSupport = new PropertyChangeSupport(clone);
/* 211 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 224 */     if (obj == this)
/* 225 */       return true; 
/* 228 */     if (!(obj instanceof Series))
/* 229 */       return false; 
/* 231 */     Series that = (Series)obj;
/* 232 */     if (!getKey().equals(that.getKey()))
/* 233 */       return false; 
/* 236 */     if (!ObjectUtilities.equal(getDescription(), that.getDescription()))
/* 237 */       return false; 
/* 240 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 250 */     int result = this.key.hashCode();
/* 251 */     result = 29 * result + ((this.description != null) ? this.description.hashCode() : 0);
/* 253 */     return result;
/*     */   }
/*     */   
/*     */   public void addChangeListener(SeriesChangeListener listener) {
/* 266 */     this.listeners.add(SeriesChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(SeriesChangeListener listener) {
/* 276 */     this.listeners.remove(SeriesChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public void fireSeriesChanged() {
/* 284 */     if (this.notify)
/* 285 */       notifyListeners(new SeriesChangeEvent(this)); 
/*     */   }
/*     */   
/*     */   protected void notifyListeners(SeriesChangeEvent event) {
/* 297 */     Object[] listenerList = this.listeners.getListenerList();
/* 298 */     for (int i = listenerList.length - 2; i >= 0; i -= 2) {
/* 299 */       if (listenerList[i] == SeriesChangeListener.class)
/* 300 */         ((SeriesChangeListener)listenerList[i + 1]).seriesChanged(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 314 */     this.propertyChangeSupport.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 323 */     this.propertyChangeSupport.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(String property, Object oldValue, Object newValue) {
/* 336 */     this.propertyChangeSupport.firePropertyChange(property, oldValue, newValue);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\Series.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */