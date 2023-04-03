/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class DateTickMarkPosition implements Serializable {
/*     */   private static final long serialVersionUID = 2540750672764537240L;
/*     */   
/*  58 */   public static final DateTickMarkPosition START = new DateTickMarkPosition("DateTickMarkPosition.START");
/*     */   
/*  62 */   public static final DateTickMarkPosition MIDDLE = new DateTickMarkPosition("DateTickMarkPosition.MIDDLE");
/*     */   
/*  66 */   public static final DateTickMarkPosition END = new DateTickMarkPosition("DateTickMarkPosition.END");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private DateTickMarkPosition(String name) {
/*  78 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  87 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 100 */     if (this == obj)
/* 101 */       return true; 
/* 103 */     if (!(obj instanceof DateTickMarkPosition))
/* 104 */       return false; 
/* 106 */     DateTickMarkPosition position = (DateTickMarkPosition)obj;
/* 107 */     if (!this.name.equals(position.toString()))
/* 108 */       return false; 
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 122 */     if (equals(START))
/* 123 */       return START; 
/* 125 */     if (equals(MIDDLE))
/* 126 */       return MIDDLE; 
/* 128 */     if (equals(END))
/* 129 */       return END; 
/* 131 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\DateTickMarkPosition.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */