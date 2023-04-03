/*     */ package org.apache.commons.digester.substitution;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class VariableAttributes implements Attributes {
/*  39 */   private ArrayList values = new ArrayList(10);
/*     */   
/*     */   private Attributes attrs;
/*     */   
/*     */   private VariableExpander expander;
/*     */   
/*     */   public void init(Attributes attrs, VariableExpander expander) {
/*  50 */     this.attrs = attrs;
/*  51 */     this.expander = expander;
/*  55 */     this.values.clear();
/*     */   }
/*     */   
/*     */   public String getValue(int index) {
/*  59 */     if (index >= this.values.size()) {
/*  65 */       this.values.ensureCapacity(index + 1);
/*  66 */       for (int i = this.values.size(); i <= index; i++)
/*  67 */         this.values.add(null); 
/*     */     } 
/*  71 */     String s = this.values.get(index);
/*  73 */     if (s == null) {
/*  77 */       s = this.attrs.getValue(index);
/*  78 */       if (s != null) {
/*  79 */         s = this.expander.expand(s);
/*  80 */         this.values.set(index, s);
/*     */       } 
/*     */     } 
/*  84 */     return s;
/*     */   }
/*     */   
/*     */   public String getValue(String qname) {
/*  88 */     int index = this.attrs.getIndex(qname);
/*  89 */     if (index == -1)
/*  90 */       return null; 
/*  92 */     return getValue(index);
/*     */   }
/*     */   
/*     */   public String getValue(String uri, String localname) {
/*  96 */     int index = this.attrs.getIndex(uri, localname);
/*  97 */     if (index == -1)
/*  98 */       return null; 
/* 100 */     return getValue(index);
/*     */   }
/*     */   
/*     */   public int getIndex(String qname) {
/* 105 */     return this.attrs.getIndex(qname);
/*     */   }
/*     */   
/*     */   public int getIndex(String uri, String localpart) {
/* 109 */     return this.attrs.getIndex(uri, localpart);
/*     */   }
/*     */   
/*     */   public int getLength() {
/* 113 */     return this.attrs.getLength();
/*     */   }
/*     */   
/*     */   public String getLocalName(int index) {
/* 117 */     return this.attrs.getLocalName(index);
/*     */   }
/*     */   
/*     */   public String getQName(int index) {
/* 121 */     return this.attrs.getQName(index);
/*     */   }
/*     */   
/*     */   public String getType(int index) {
/* 125 */     return this.attrs.getType(index);
/*     */   }
/*     */   
/*     */   public String getType(String qname) {
/* 129 */     return this.attrs.getType(qname);
/*     */   }
/*     */   
/*     */   public String getType(String uri, String localname) {
/* 133 */     return this.attrs.getType(uri, localname);
/*     */   }
/*     */   
/*     */   public String getURI(int index) {
/* 137 */     return this.attrs.getURI(index);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\substitution\VariableAttributes.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */