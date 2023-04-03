/*     */ package ch.qos.logback.core.joran.spi;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ElementPath {
/*  14 */   ArrayList<String> partList = new ArrayList<String>();
/*     */   
/*     */   public ElementPath() {}
/*     */   
/*     */   public ElementPath(List<String> list) {
/*  20 */     this.partList.addAll(list);
/*     */   }
/*     */   
/*     */   public ElementPath(String pathStr) {
/*  29 */     if (pathStr == null)
/*     */       return; 
/*  33 */     String[] partArray = pathStr.split("/");
/*  34 */     if (partArray == null)
/*     */       return; 
/*  36 */     for (String part : partArray) {
/*  37 */       if (part.length() > 0)
/*  38 */         this.partList.add(part); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ElementPath duplicate() {
/*  44 */     ElementPath p = new ElementPath();
/*  45 */     p.partList.addAll(this.partList);
/*  46 */     return p;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  52 */     if (o == null || !(o instanceof ElementPath))
/*  53 */       return false; 
/*  56 */     ElementPath r = (ElementPath)o;
/*  58 */     if (r.size() != size())
/*  59 */       return false; 
/*  62 */     int len = size();
/*  64 */     for (int i = 0; i < len; i++) {
/*  65 */       if (!equalityCheck(get(i), r.get(i)))
/*  66 */         return false; 
/*     */     } 
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   private boolean equalityCheck(String x, String y) {
/*  75 */     return x.equalsIgnoreCase(y);
/*     */   }
/*     */   
/*     */   public List<String> getCopyOfPartList() {
/*  79 */     return new ArrayList<String>(this.partList);
/*     */   }
/*     */   
/*     */   public void push(String s) {
/*  83 */     this.partList.add(s);
/*     */   }
/*     */   
/*     */   public String get(int i) {
/*  87 */     return this.partList.get(i);
/*     */   }
/*     */   
/*     */   public void pop() {
/*  91 */     if (!this.partList.isEmpty())
/*  92 */       this.partList.remove(this.partList.size() - 1); 
/*     */   }
/*     */   
/*     */   public String peekLast() {
/*  97 */     if (!this.partList.isEmpty()) {
/*  98 */       int size = this.partList.size();
/*  99 */       return this.partList.get(size - 1);
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 106 */     return this.partList.size();
/*     */   }
/*     */   
/*     */   protected String toStableString() {
/* 111 */     StringBuilder result = new StringBuilder();
/* 112 */     for (String current : this.partList)
/* 113 */       result.append("[").append(current).append("]"); 
/* 115 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 120 */     return toStableString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\spi\ElementPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */