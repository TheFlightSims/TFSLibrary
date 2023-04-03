/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class DatasetGroup implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -3640642179674185688L;
/*     */   
/*     */   private String id;
/*     */   
/*     */   public DatasetGroup() {
/*  66 */     this.id = "NOID";
/*     */   }
/*     */   
/*     */   public DatasetGroup(String id) {
/*  75 */     if (id == null)
/*  76 */       throw new IllegalArgumentException("Null 'id' argument."); 
/*  78 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getID() {
/*  87 */     return this.id;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  98 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 109 */     if (obj == this)
/* 110 */       return true; 
/* 112 */     if (!(obj instanceof DatasetGroup))
/* 113 */       return false; 
/* 115 */     DatasetGroup that = (DatasetGroup)obj;
/* 116 */     if (!this.id.equals(that.id))
/* 117 */       return false; 
/* 119 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DatasetGroup.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */