/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class CaselessStringArrayTable implements Serializable {
/*     */   private CaselessStringKey[] keys;
/*     */   
/*     */   private Hashtable indices;
/*     */   
/*     */   public CaselessStringArrayTable() {
/*  34 */     this((CaselessStringKey[])null);
/*     */   }
/*     */   
/*     */   public CaselessStringArrayTable(CaselessStringKey[] keys) {
/*  42 */     this.keys = keys;
/*  43 */     this.indices = new Hashtable();
/*  45 */     if (keys != null)
/*  46 */       for (int i = 0; i < keys.length; i++)
/*  47 */         this.indices.put(keys[i], new Integer(i));  
/*     */   }
/*     */   
/*     */   public CaselessStringArrayTable(String[] keys) {
/*  55 */     this(toCaselessStringKey(keys));
/*     */   }
/*     */   
/*     */   private static CaselessStringKey[] toCaselessStringKey(String[] strings) {
/*  62 */     if (strings == null)
/*  63 */       return null; 
/*  65 */     CaselessStringKey[] keys = new CaselessStringKey[strings.length];
/*  67 */     for (int i = 0; i < strings.length; i++)
/*  68 */       keys[i] = new CaselessStringKey(strings[i]); 
/*  70 */     return keys;
/*     */   }
/*     */   
/*     */   public int indexOf(CaselessStringKey key) {
/*  80 */     if (key == null)
/*  81 */       throw new IllegalArgumentException(JaiI18N.getString("CaselessStringArrayTable0")); 
/*  85 */     Integer i = (Integer)this.indices.get(key);
/*  87 */     if (i == null)
/*  88 */       throw new IllegalArgumentException(key.getName() + " - " + JaiI18N.getString("CaselessStringArrayTable1")); 
/*  92 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public int indexOf(String key) {
/* 102 */     return indexOf(new CaselessStringKey(key));
/*     */   }
/*     */   
/*     */   public String getName(int i) {
/* 111 */     if (this.keys == null)
/* 112 */       throw new ArrayIndexOutOfBoundsException(); 
/* 114 */     return this.keys[i].getName();
/*     */   }
/*     */   
/*     */   public CaselessStringKey get(int i) {
/* 124 */     if (this.keys == null)
/* 125 */       throw new ArrayIndexOutOfBoundsException(); 
/* 127 */     return this.keys[i];
/*     */   }
/*     */   
/*     */   public boolean contains(CaselessStringKey key) {
/* 136 */     if (key == null)
/* 137 */       throw new IllegalArgumentException(JaiI18N.getString("CaselessStringArrayTable0")); 
/* 141 */     return (this.indices.get(key) != null);
/*     */   }
/*     */   
/*     */   public boolean contains(String key) {
/* 150 */     return contains(new CaselessStringKey(key));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\CaselessStringArrayTable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */