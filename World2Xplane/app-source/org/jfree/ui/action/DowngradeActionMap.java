/*     */ package org.jfree.ui.action;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import javax.swing.Action;
/*     */ 
/*     */ public class DowngradeActionMap {
/*  77 */   private final HashMap actionMap = new HashMap();
/*     */   
/*  78 */   private final ArrayList actionList = new ArrayList();
/*     */   
/*     */   private DowngradeActionMap parent;
/*     */   
/*     */   public void setParent(DowngradeActionMap map) {
/*  87 */     this.parent = map;
/*     */   }
/*     */   
/*     */   public DowngradeActionMap getParent() {
/*  97 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void put(Object key, Action action) {
/* 111 */     if (action == null) {
/* 112 */       remove(key);
/*     */     } else {
/* 115 */       if (this.actionMap.containsKey(key))
/* 116 */         remove(key); 
/* 118 */       this.actionMap.put(key, action);
/* 119 */       this.actionList.add(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Action get(Object key) {
/* 131 */     Action retval = (Action)this.actionMap.get(key);
/* 132 */     if (retval != null)
/* 133 */       return retval; 
/* 135 */     if (this.parent != null)
/* 136 */       return this.parent.get(key); 
/* 138 */     return null;
/*     */   }
/*     */   
/*     */   public void remove(Object key) {
/* 147 */     this.actionMap.remove(key);
/* 148 */     this.actionList.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 155 */     this.actionMap.clear();
/* 156 */     this.actionList.clear();
/*     */   }
/*     */   
/*     */   public Object[] keys() {
/* 165 */     return this.actionList.toArray();
/*     */   }
/*     */   
/*     */   public int size() {
/* 174 */     return this.actionMap.size();
/*     */   }
/*     */   
/*     */   public Object[] allKeys() {
/* 185 */     if (this.parent == null)
/* 186 */       return keys(); 
/* 188 */     Object[] parentKeys = this.parent.allKeys();
/* 189 */     Object[] key = keys();
/* 190 */     Object[] retval = new Object[parentKeys.length + key.length];
/* 191 */     System.arraycopy(key, 0, retval, 0, key.length);
/* 192 */     System.arraycopy(retval, 0, retval, key.length, retval.length);
/* 193 */     return retval;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\action\DowngradeActionMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */