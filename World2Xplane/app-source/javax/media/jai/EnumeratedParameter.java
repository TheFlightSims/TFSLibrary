/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class EnumeratedParameter implements Serializable {
/*     */   private String name;
/*     */   
/*     */   private int value;
/*     */   
/*     */   public EnumeratedParameter(String name, int value) {
/*  73 */     this.name = name;
/*  74 */     this.value = value;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public int getValue() {
/*  90 */     return this.value;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  97 */     return (getClass().getName() + new Integer(this.value)).hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 106 */     return (o != null && getClass().equals(o.getClass()) && (this.name.equals(((EnumeratedParameter)o).getName()) || this.value == ((EnumeratedParameter)o).getValue()));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 126 */     return getClass().getName() + ":" + this.name + "=" + String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\EnumeratedParameter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */