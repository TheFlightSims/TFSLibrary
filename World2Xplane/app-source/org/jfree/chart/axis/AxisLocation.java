/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class AxisLocation implements Serializable {
/*     */   private static final long serialVersionUID = -3276922179323563410L;
/*     */   
/*  61 */   public static final AxisLocation TOP_OR_LEFT = new AxisLocation("AxisLocation.TOP_OR_LEFT");
/*     */   
/*  66 */   public static final AxisLocation TOP_OR_RIGHT = new AxisLocation("AxisLocation.TOP_OR_RIGHT");
/*     */   
/*  71 */   public static final AxisLocation BOTTOM_OR_LEFT = new AxisLocation("AxisLocation.BOTTOM_OR_LEFT");
/*     */   
/*  76 */   public static final AxisLocation BOTTOM_OR_RIGHT = new AxisLocation("AxisLocation.BOTTOM_OR_RIGHT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private AxisLocation(String name) {
/*  89 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  98 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 111 */     if (this == obj)
/* 112 */       return true; 
/* 114 */     if (!(obj instanceof AxisLocation))
/* 115 */       return false; 
/* 117 */     AxisLocation location = (AxisLocation)obj;
/* 118 */     if (!this.name.equals(location.toString()))
/* 119 */       return false; 
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   public static AxisLocation getOpposite(AxisLocation location) {
/* 133 */     if (location == null)
/* 134 */       throw new IllegalArgumentException("Null 'location' argument."); 
/* 136 */     AxisLocation result = null;
/* 137 */     if (location == TOP_OR_LEFT) {
/* 138 */       result = BOTTOM_OR_RIGHT;
/* 140 */     } else if (location == TOP_OR_RIGHT) {
/* 141 */       result = BOTTOM_OR_LEFT;
/* 143 */     } else if (location == BOTTOM_OR_LEFT) {
/* 144 */       result = TOP_OR_RIGHT;
/* 146 */     } else if (location == BOTTOM_OR_RIGHT) {
/* 147 */       result = TOP_OR_LEFT;
/*     */     } else {
/* 150 */       throw new IllegalStateException("AxisLocation not recognised.");
/*     */     } 
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 163 */     if (equals(TOP_OR_RIGHT))
/* 164 */       return TOP_OR_RIGHT; 
/* 166 */     if (equals(BOTTOM_OR_RIGHT))
/* 167 */       return BOTTOM_OR_RIGHT; 
/* 169 */     if (equals(TOP_OR_LEFT))
/* 170 */       return TOP_OR_LEFT; 
/* 172 */     if (equals(BOTTOM_OR_LEFT))
/* 173 */       return BOTTOM_OR_LEFT; 
/* 175 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\AxisLocation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */