/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ 
/*     */ public class StandardPieURLGenerator implements PieURLGenerator, Serializable {
/*     */   private static final long serialVersionUID = 1626966402065883419L;
/*     */   
/*  67 */   private String prefix = "index.html";
/*     */   
/*  70 */   private String categoryParameterName = "category";
/*     */   
/*  73 */   private String indexParameterName = "pieIndex";
/*     */   
/*     */   public StandardPieURLGenerator() {}
/*     */   
/*     */   public StandardPieURLGenerator(String prefix) {
/*  88 */     this.prefix = prefix;
/*     */   }
/*     */   
/*     */   public StandardPieURLGenerator(String prefix, String categoryParameterName) {
/*  99 */     this.prefix = prefix;
/* 100 */     this.categoryParameterName = categoryParameterName;
/*     */   }
/*     */   
/*     */   public StandardPieURLGenerator(String prefix, String categoryParameterName, String indexParameterName) {
/* 114 */     this.prefix = prefix;
/* 115 */     this.categoryParameterName = categoryParameterName;
/* 116 */     this.indexParameterName = indexParameterName;
/*     */   }
/*     */   
/*     */   public String generateURL(PieDataset data, Comparable key, int pieIndex) {
/* 130 */     String url = this.prefix;
/* 131 */     if (url.indexOf("?") > -1) {
/* 132 */       url = url + "&amp;" + this.categoryParameterName + "=" + key.toString();
/*     */     } else {
/* 135 */       url = url + "?" + this.categoryParameterName + "=" + key.toString();
/*     */     } 
/* 137 */     if (this.indexParameterName != null)
/* 138 */       url = url + "&amp;" + this.indexParameterName + "=" + String.valueOf(pieIndex); 
/* 141 */     return url;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 154 */     if (obj == null)
/* 155 */       return false; 
/* 157 */     if (obj == this)
/* 158 */       return true; 
/* 161 */     if (!(obj instanceof StandardPieURLGenerator))
/* 162 */       return false; 
/* 165 */     StandardPieURLGenerator generator = (StandardPieURLGenerator)obj;
/* 166 */     return (this.categoryParameterName.equals(generator.categoryParameterName) && this.prefix.equals(generator.prefix));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\StandardPieURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */