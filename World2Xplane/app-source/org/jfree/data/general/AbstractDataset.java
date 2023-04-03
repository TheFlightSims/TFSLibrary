/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectInputValidation;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.EventListener;
/*     */ import java.util.List;
/*     */ import javax.swing.event.EventListenerList;
/*     */ 
/*     */ public abstract class AbstractDataset implements Dataset, Cloneable, Serializable, ObjectInputValidation {
/*     */   private static final long serialVersionUID = 1918768939869230744L;
/*     */   
/*  97 */   private DatasetGroup group = new DatasetGroup();
/*     */   
/*  98 */   private transient EventListenerList listenerList = new EventListenerList();
/*     */   
/*     */   public DatasetGroup getGroup() {
/* 107 */     return this.group;
/*     */   }
/*     */   
/*     */   public void setGroup(DatasetGroup group) {
/* 116 */     if (group == null)
/* 117 */       throw new IllegalArgumentException("Null 'group' argument."); 
/* 119 */     this.group = group;
/*     */   }
/*     */   
/*     */   public void addChangeListener(DatasetChangeListener listener) {
/* 128 */     this.listenerList.add(DatasetChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(DatasetChangeListener listener) {
/* 138 */     this.listenerList.remove(DatasetChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public boolean hasListener(EventListener listener) {
/* 151 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 152 */     return list.contains(listener);
/*     */   }
/*     */   
/*     */   protected void fireDatasetChanged() {
/* 159 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */   protected void notifyListeners(DatasetChangeEvent event) {
/* 175 */     Object[] listeners = this.listenerList.getListenerList();
/* 176 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 177 */       if (listeners[i] == DatasetChangeListener.class)
/* 178 */         ((DatasetChangeListener)listeners[i + 1]).datasetChanged(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 197 */     AbstractDataset clone = (AbstractDataset)super.clone();
/* 198 */     clone.listenerList = new EventListenerList();
/* 199 */     return clone;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 210 */     stream.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 223 */     stream.defaultReadObject();
/* 224 */     this.listenerList = new EventListenerList();
/* 225 */     stream.registerValidation(this, 10);
/*     */   }
/*     */   
/*     */   public void validateObject() throws InvalidObjectException {
/* 247 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\AbstractDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */