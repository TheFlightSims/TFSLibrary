/*     */ package org.openstreetmap.osmosis.osmbinary;
/*     */ 
/*     */ import com.google.protobuf.ByteString;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class StringTable {
/*     */   private HashMap<String, Integer> counts;
/*     */   
/*     */   private HashMap<String, Integer> stringmap;
/*     */   
/*     */   private String[] set;
/*     */   
/*     */   public StringTable() {
/*  32 */     clear();
/*     */   }
/*     */   
/*     */   public void incr(String s) {
/*  40 */     if (this.counts.containsKey(s)) {
/*  41 */       this.counts.put(s, new Integer(((Integer)this.counts.get(s)).intValue() + 1));
/*     */     } else {
/*  43 */       this.counts.put(s, new Integer(1));
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getIndex(String s) {
/*  54 */     return ((Integer)this.stringmap.get(s)).intValue();
/*     */   }
/*     */   
/*     */   public void finish() {
/*  58 */     Comparator<String> comparator = new Comparator<String>() {
/*     */         public int compare(String s1, String s2) {
/*  61 */           int diff = ((Integer)StringTable.this.counts.get(s2)).intValue() - ((Integer)StringTable.this.counts.get(s1)).intValue();
/*  62 */           return diff;
/*     */         }
/*     */       };
/* 101 */     this.set = (String[])this.counts.keySet().toArray((Object[])new String[0]);
/* 102 */     if (this.set.length > 0) {
/* 104 */       Arrays.sort(this.set, comparator);
/* 112 */       Arrays.sort((Object[])this.set, Math.min(128, this.set.length - 1), Math.min(16384, this.set.length - 1));
/* 114 */       Arrays.sort(this.set, Math.min(16384, this.set.length - 1), Math.min(2097152, this.set.length - 1), comparator);
/*     */     } 
/* 117 */     this.stringmap = new HashMap<String, Integer>(2 * this.set.length);
/* 118 */     for (int i = 0; i < this.set.length; i++)
/* 119 */       this.stringmap.put(this.set[i], new Integer(i + 1)); 
/* 121 */     this.counts = null;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     this.counts = new HashMap<String, Integer>(100);
/* 126 */     this.stringmap = null;
/* 127 */     this.set = null;
/*     */   }
/*     */   
/*     */   public Osmformat.StringTable.Builder serialize() {
/* 131 */     Osmformat.StringTable.Builder builder = Osmformat.StringTable.newBuilder();
/* 133 */     builder.addS(ByteString.copyFromUtf8(""));
/* 134 */     for (int i = 0; i < this.set.length; i++)
/* 135 */       builder.addS(ByteString.copyFromUtf8(this.set[i])); 
/* 136 */     return builder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\StringTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */