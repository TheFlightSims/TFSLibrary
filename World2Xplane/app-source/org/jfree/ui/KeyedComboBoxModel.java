/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ComboBoxModel;
/*     */ import javax.swing.event.ListDataEvent;
/*     */ import javax.swing.event.ListDataListener;
/*     */ 
/*     */ public class KeyedComboBoxModel implements ComboBoxModel {
/*     */   private int selectedItem;
/*     */   
/*     */   private ArrayList data;
/*     */   
/*     */   private ArrayList listdatalistener;
/*     */   
/*     */   private transient ListDataListener[] tempListeners;
/*     */   
/*     */   private static class ComboBoxItemPair {
/*     */     private Object key;
/*     */     
/*     */     private Object value;
/*     */     
/*     */     public ComboBoxItemPair(Object key, Object value) {
/*  79 */       this.key = key;
/*  80 */       this.value = value;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/*  88 */       return this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/*  96 */       return this.value;
/*     */     }
/*     */     
/*     */     public void setValue(Object value) {
/* 104 */       this.value = value;
/*     */     }
/*     */   }
/*     */   
/*     */   public KeyedComboBoxModel() {
/* 121 */     this.data = new ArrayList();
/* 122 */     this.listdatalistener = new ArrayList();
/*     */   }
/*     */   
/*     */   public KeyedComboBoxModel(Object[] keys, Object[] values) {
/* 133 */     this();
/* 134 */     setData(keys, values);
/*     */   }
/*     */   
/*     */   public void setData(Object[] keys, Object[] values) {
/* 145 */     if (values.length != keys.length)
/* 146 */       throw new IllegalArgumentException("Values and text must have the same length."); 
/* 149 */     this.data.clear();
/* 150 */     this.data.ensureCapacity(keys.length);
/* 152 */     for (int i = 0; i < values.length; i++)
/* 153 */       add(keys[i], values[i]); 
/* 156 */     this.selectedItem = -1;
/* 157 */     ListDataEvent evt = new ListDataEvent(this, 0, 0, this.data.size() - 1);
/* 159 */     fireListDataEvent(evt);
/*     */   }
/*     */   
/*     */   protected synchronized void fireListDataEvent(ListDataEvent evt) {
/* 168 */     if (this.tempListeners == null)
/* 169 */       this.tempListeners = (ListDataListener[])this.listdatalistener.toArray((Object[])new ListDataListener[this.listdatalistener.size()]); 
/* 172 */     for (int i = 0; i < this.tempListeners.length; i++) {
/* 173 */       ListDataListener l = this.tempListeners[i];
/* 174 */       l.contentsChanged(evt);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getSelectedItem() {
/* 184 */     if (this.selectedItem >= this.data.size())
/* 185 */       return null; 
/* 188 */     if (this.selectedItem < 0)
/* 189 */       return null; 
/* 192 */     ComboBoxItemPair item = this.data.get(this.selectedItem);
/* 193 */     return item.getValue();
/*     */   }
/*     */   
/*     */   public void setSelectedKey(Object anItem) {
/* 203 */     if (anItem == null) {
/* 204 */       this.selectedItem = -1;
/*     */     } else {
/* 207 */       int newSelectedItem = findDataElementIndex(anItem);
/* 208 */       this.selectedItem = newSelectedItem;
/*     */     } 
/* 210 */     fireListDataEvent(new ListDataEvent(this, 0, -1, -1));
/*     */   }
/*     */   
/*     */   public void setSelectedItem(Object anItem) {
/* 220 */     if (anItem == null) {
/* 221 */       this.selectedItem = -1;
/*     */     } else {
/* 224 */       int newSelectedItem = findElementIndex(anItem);
/* 225 */       this.selectedItem = newSelectedItem;
/*     */     } 
/* 227 */     fireListDataEvent(new ListDataEvent(this, 0, -1, -1));
/*     */   }
/*     */   
/*     */   public synchronized void addListDataListener(ListDataListener l) {
/* 237 */     this.listdatalistener.add(l);
/* 238 */     this.tempListeners = null;
/*     */   }
/*     */   
/*     */   public Object getElementAt(int index) {
/* 248 */     if (index >= this.data.size())
/* 249 */       return null; 
/* 252 */     ComboBoxItemPair datacon = this.data.get(index);
/* 253 */     if (datacon == null)
/* 254 */       return null; 
/* 256 */     return datacon.getValue();
/*     */   }
/*     */   
/*     */   public Object getKeyAt(int index) {
/* 266 */     if (index >= this.data.size())
/* 267 */       return null; 
/* 270 */     if (index < 0)
/* 271 */       return null; 
/* 274 */     ComboBoxItemPair datacon = this.data.get(index);
/* 275 */     if (datacon == null)
/* 276 */       return null; 
/* 278 */     return datacon.getKey();
/*     */   }
/*     */   
/*     */   public Object getSelectedKey() {
/* 287 */     return getKeyAt(this.selectedItem);
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 296 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public void removeListDataListener(ListDataListener l) {
/* 306 */     this.listdatalistener.remove(l);
/* 307 */     this.tempListeners = null;
/*     */   }
/*     */   
/*     */   private int findDataElementIndex(Object anItem) {
/* 318 */     if (anItem == null)
/* 319 */       throw new NullPointerException("Item to find must not be null"); 
/* 322 */     for (int i = 0; i < this.data.size(); i++) {
/* 323 */       ComboBoxItemPair datacon = this.data.get(i);
/* 324 */       if (anItem.equals(datacon.getKey()))
/* 325 */         return i; 
/*     */     } 
/* 328 */     return -1;
/*     */   }
/*     */   
/*     */   public int findElementIndex(Object key) {
/* 338 */     if (key == null)
/* 339 */       throw new NullPointerException("Item to find must not be null"); 
/* 342 */     for (int i = 0; i < this.data.size(); i++) {
/* 343 */       ComboBoxItemPair datacon = this.data.get(i);
/* 344 */       if (key.equals(datacon.getValue()))
/* 345 */         return i; 
/*     */     } 
/* 348 */     return -1;
/*     */   }
/*     */   
/*     */   public void removeDataElement(Object key) {
/* 357 */     int idx = findDataElementIndex(key);
/* 358 */     if (idx == -1)
/*     */       return; 
/* 362 */     this.data.remove(idx);
/* 363 */     ListDataEvent evt = new ListDataEvent(this, 2, idx, idx);
/* 365 */     fireListDataEvent(evt);
/*     */   }
/*     */   
/*     */   public void add(Object key, Object cbitem) {
/* 375 */     ComboBoxItemPair con = new ComboBoxItemPair(key, cbitem);
/* 376 */     this.data.add(con);
/* 377 */     ListDataEvent evt = new ListDataEvent(this, 1, this.data.size() - 2, this.data.size() - 2);
/* 379 */     fireListDataEvent(evt);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 386 */     int size = getSize();
/* 387 */     this.data.clear();
/* 388 */     ListDataEvent evt = new ListDataEvent(this, 2, 0, size - 1);
/* 389 */     fireListDataEvent(evt);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\KeyedComboBoxModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */