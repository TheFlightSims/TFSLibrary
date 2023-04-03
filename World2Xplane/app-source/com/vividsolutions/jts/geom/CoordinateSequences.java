/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.util.StringUtil;
/*     */ 
/*     */ public class CoordinateSequences {
/*     */   public static void reverse(CoordinateSequence seq) {
/*  50 */     int last = seq.size() - 1;
/*  51 */     int mid = last / 2;
/*  52 */     for (int i = 0; i <= mid; i++)
/*  53 */       swap(seq, i, last - i); 
/*     */   }
/*     */   
/*     */   public static void swap(CoordinateSequence seq, int i, int j) {
/*  66 */     if (i == j)
/*     */       return; 
/*  67 */     for (int dim = 0; dim < seq.getDimension(); dim++) {
/*  68 */       double tmp = seq.getOrdinate(i, dim);
/*  69 */       seq.setOrdinate(i, dim, seq.getOrdinate(j, dim));
/*  70 */       seq.setOrdinate(j, dim, tmp);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void copy(CoordinateSequence src, int srcPos, CoordinateSequence dest, int destPos, int length) {
/*  87 */     for (int i = 0; i < length; i++)
/*  88 */       copyCoord(src, srcPos + i, dest, destPos + i); 
/*     */   }
/*     */   
/*     */   public static void copyCoord(CoordinateSequence src, int srcPos, CoordinateSequence dest, int destPos) {
/* 104 */     int minDim = Math.min(src.getDimension(), dest.getDimension());
/* 105 */     for (int dim = 0; dim < minDim; dim++)
/* 106 */       dest.setOrdinate(destPos, dim, src.getOrdinate(srcPos, dim)); 
/*     */   }
/*     */   
/*     */   public static boolean isRing(CoordinateSequence seq) {
/* 122 */     int n = seq.size();
/* 123 */     if (n == 0)
/* 123 */       return true; 
/* 125 */     if (n <= 3)
/* 126 */       return false; 
/* 128 */     return (seq.getOrdinate(0, 0) == seq.getOrdinate(n - 1, 0) && seq.getOrdinate(0, 1) == seq.getOrdinate(n - 1, 1));
/*     */   }
/*     */   
/*     */   public static CoordinateSequence ensureValidRing(CoordinateSequenceFactory fact, CoordinateSequence seq) {
/* 146 */     int n = seq.size();
/* 148 */     if (n == 0)
/* 148 */       return seq; 
/* 150 */     if (n <= 3)
/* 151 */       return createClosedRing(fact, seq, 4); 
/* 153 */     boolean isClosed = (seq.getOrdinate(0, 0) == seq.getOrdinate(n - 1, 0) && seq.getOrdinate(0, 1) == seq.getOrdinate(n - 1, 1));
/* 155 */     if (isClosed)
/* 155 */       return seq; 
/* 157 */     return createClosedRing(fact, seq, n + 1);
/*     */   }
/*     */   
/*     */   private static CoordinateSequence createClosedRing(CoordinateSequenceFactory fact, CoordinateSequence seq, int size) {
/* 162 */     CoordinateSequence newseq = fact.create(size, seq.getDimension());
/* 163 */     int n = seq.size();
/* 164 */     copy(seq, 0, newseq, 0, n);
/* 166 */     for (int i = n; i < size; i++)
/* 167 */       copy(seq, 0, newseq, i, 1); 
/* 168 */     return newseq;
/*     */   }
/*     */   
/*     */   public static CoordinateSequence extend(CoordinateSequenceFactory fact, CoordinateSequence seq, int size) {
/* 173 */     CoordinateSequence newseq = fact.create(size, seq.getDimension());
/* 174 */     int n = seq.size();
/* 175 */     copy(seq, 0, newseq, 0, n);
/* 177 */     if (n > 0)
/* 178 */       for (int i = n; i < size; i++)
/* 179 */         copy(seq, n - 1, newseq, i, 1);  
/* 181 */     return newseq;
/*     */   }
/*     */   
/*     */   public static boolean isEqual(CoordinateSequence cs1, CoordinateSequence cs2) {
/* 197 */     int cs1Size = cs1.size();
/* 198 */     int cs2Size = cs2.size();
/* 199 */     if (cs1Size != cs2Size)
/* 199 */       return false; 
/* 200 */     int dim = Math.min(cs1.getDimension(), cs2.getDimension());
/* 201 */     for (int i = 0; i < cs1Size; i++) {
/* 202 */       for (int d = 0; d < dim; d++) {
/* 203 */         double v1 = cs1.getOrdinate(i, d);
/* 204 */         double v2 = cs2.getOrdinate(i, d);
/* 205 */         if (cs1.getOrdinate(i, d) != cs2.getOrdinate(i, d))
/* 208 */           if (!Double.isNaN(v1) || !Double.isNaN(v2))
/* 210 */             return false;  
/*     */       } 
/*     */     } 
/* 213 */     return true;
/*     */   }
/*     */   
/*     */   public static String toString(CoordinateSequence cs) {
/* 228 */     int size = cs.size();
/* 229 */     if (size == 0)
/* 230 */       return "()"; 
/* 231 */     int dim = cs.getDimension();
/* 232 */     StringBuffer buf = new StringBuffer();
/* 233 */     buf.append('(');
/* 234 */     for (int i = 0; i < size; i++) {
/* 235 */       if (i > 0)
/* 235 */         buf.append(" "); 
/* 236 */       for (int d = 0; d < dim; d++) {
/* 237 */         if (d > 0)
/* 237 */           buf.append(","); 
/* 238 */         buf.append(StringUtil.toString(cs.getOrdinate(i, d)));
/*     */       } 
/*     */     } 
/* 241 */     buf.append(')');
/* 242 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateSequences.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */