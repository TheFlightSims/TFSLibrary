/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class SimpleInternationalString extends AbstractInternationalString implements Serializable {
/*     */   private static final long serialVersionUID = 3543963804501667578L;
/*     */   
/*     */   public SimpleInternationalString(String message) {
/*  50 */     this.defaultValue = message;
/*  51 */     ensureNonNull("message", message);
/*     */   }
/*     */   
/*     */   public static AbstractInternationalString wrap(CharSequence string) {
/*  63 */     if (string == null || string instanceof AbstractInternationalString)
/*  64 */       return (AbstractInternationalString)string; 
/*  66 */     return new SimpleInternationalString(string.toString());
/*     */   }
/*     */   
/*     */   public String toString(Locale locale) {
/*  73 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  84 */     if (object != null && object.getClass().equals(getClass())) {
/*  85 */       SimpleInternationalString that = (SimpleInternationalString)object;
/*  86 */       return Utilities.equals(this.defaultValue, that.defaultValue);
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  96 */     return 0xB029EEFA ^ this.defaultValue.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 103 */     out.defaultWriteObject();
/* 104 */     out.writeUTF(this.defaultValue);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 111 */     in.defaultReadObject();
/* 112 */     this.defaultValue = in.readUTF();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\SimpleInternationalString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */