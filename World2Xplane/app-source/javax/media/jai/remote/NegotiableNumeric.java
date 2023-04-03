/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ public class NegotiableNumeric implements Negotiable {
/*     */   Number number;
/*     */   
/*     */   Class elementClass;
/*     */   
/*     */   public NegotiableNumeric(byte b) {
/*  37 */     this.number = new Byte(b);
/*  38 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(short s) {
/*  48 */     this.number = new Short(s);
/*  49 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(int i) {
/*  59 */     this.number = new Integer(i);
/*  60 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(long l) {
/*  70 */     this.number = new Long(l);
/*  71 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(float f) {
/*  81 */     this.number = new Float(f);
/*  82 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(double d) {
/*  92 */     this.number = new Double(d);
/*  93 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public NegotiableNumeric(Number n) {
/* 106 */     if (n == null)
/* 107 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableNumeric0")); 
/* 111 */     this.number = n;
/* 112 */     this.elementClass = this.number.getClass();
/*     */   }
/*     */   
/*     */   public Number getNumber() {
/* 122 */     return this.number;
/*     */   }
/*     */   
/*     */   public Negotiable negotiate(Negotiable other) {
/* 138 */     if (other == null)
/* 139 */       return null; 
/* 141 */     if (!(other instanceof NegotiableNumeric) || other.getNegotiatedValueClass() != this.elementClass)
/* 143 */       return null; 
/* 146 */     NegotiableNumeric otherNN = (NegotiableNumeric)other;
/* 148 */     if (this.number.equals(otherNN.getNumber()))
/* 149 */       return new NegotiableNumeric(this.number); 
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public Object getNegotiatedValue() {
/* 162 */     return this.number;
/*     */   }
/*     */   
/*     */   public Class getNegotiatedValueClass() {
/* 173 */     return this.elementClass;
/*     */   }
/*     */   
/*     */   public byte getNegotiatedValueAsByte() {
/* 183 */     if (this.elementClass != Byte.class)
/* 184 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 186 */     return this.number.byteValue();
/*     */   }
/*     */   
/*     */   public short getNegotiatedValueAsShort() {
/* 196 */     if (this.elementClass != Short.class)
/* 197 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 199 */     return this.number.shortValue();
/*     */   }
/*     */   
/*     */   public int getNegotiatedValueAsInt() {
/* 209 */     if (this.elementClass != Integer.class)
/* 210 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 212 */     return this.number.intValue();
/*     */   }
/*     */   
/*     */   public long getNegotiatedValueAsLong() {
/* 222 */     if (this.elementClass != Long.class)
/* 223 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 225 */     return this.number.longValue();
/*     */   }
/*     */   
/*     */   public float getNegotiatedValueAsFloat() {
/* 235 */     if (this.elementClass != Float.class)
/* 236 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 238 */     return this.number.floatValue();
/*     */   }
/*     */   
/*     */   public double getNegotiatedValueAsDouble() {
/* 248 */     if (this.elementClass != Double.class)
/* 249 */       throw new ClassCastException(JaiI18N.getString("NegotiableNumeric1")); 
/* 251 */     return this.number.doubleValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\NegotiableNumeric.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */