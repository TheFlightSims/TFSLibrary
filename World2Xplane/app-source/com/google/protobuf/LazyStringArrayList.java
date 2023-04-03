/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class LazyStringArrayList extends AbstractList<String> implements LazyStringList, RandomAccess {
/*  66 */   public static final LazyStringList EMPTY = new UnmodifiableLazyStringList(new LazyStringArrayList());
/*     */   
/*     */   private final List<Object> list;
/*     */   
/*     */   public LazyStringArrayList() {
/*  72 */     this.list = new ArrayList();
/*     */   }
/*     */   
/*     */   public LazyStringArrayList(List<String> from) {
/*  76 */     this.list = new ArrayList(from);
/*     */   }
/*     */   
/*     */   public String get(int index) {
/*  81 */     Object o = this.list.get(index);
/*  82 */     if (o instanceof String)
/*  83 */       return (String)o; 
/*  85 */     ByteString bs = (ByteString)o;
/*  86 */     String s = bs.toStringUtf8();
/*  87 */     if (Internal.isValidUtf8(bs))
/*  88 */       this.list.set(index, s); 
/*  90 */     return s;
/*     */   }
/*     */   
/*     */   public int size() {
/*  96 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public String set(int index, String s) {
/* 101 */     Object o = this.list.set(index, s);
/* 102 */     return asString(o);
/*     */   }
/*     */   
/*     */   public void add(int index, String element) {
/* 107 */     this.list.add(index, element);
/* 108 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends String> c) {
/* 113 */     boolean ret = this.list.addAll(index, c);
/* 114 */     this.modCount++;
/* 115 */     return ret;
/*     */   }
/*     */   
/*     */   public String remove(int index) {
/* 120 */     Object o = this.list.remove(index);
/* 121 */     this.modCount++;
/* 122 */     return asString(o);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 126 */     this.list.clear();
/* 127 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public void add(ByteString element) {
/* 132 */     this.list.add(element);
/* 133 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public ByteString getByteString(int index) {
/* 138 */     Object o = this.list.get(index);
/* 139 */     if (o instanceof String) {
/* 140 */       ByteString b = ByteString.copyFromUtf8((String)o);
/* 141 */       this.list.set(index, b);
/* 142 */       return b;
/*     */     } 
/* 144 */     return (ByteString)o;
/*     */   }
/*     */   
/*     */   private String asString(Object o) {
/* 149 */     if (o instanceof String)
/* 150 */       return (String)o; 
/* 152 */     return ((ByteString)o).toStringUtf8();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\LazyStringArrayList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */