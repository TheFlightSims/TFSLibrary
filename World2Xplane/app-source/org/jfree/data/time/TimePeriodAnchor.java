/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class TimePeriodAnchor implements Serializable {
/*     */   private static final long serialVersionUID = 2011955697457548862L;
/*     */   
/*  59 */   public static final TimePeriodAnchor START = new TimePeriodAnchor("TimePeriodAnchor.START");
/*     */   
/*  63 */   public static final TimePeriodAnchor MIDDLE = new TimePeriodAnchor("TimePeriodAnchor.MIDDLE");
/*     */   
/*  67 */   public static final TimePeriodAnchor END = new TimePeriodAnchor("TimePeriodAnchor.END");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private TimePeriodAnchor(String name) {
/*  79 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  88 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (this == obj)
/* 102 */       return true; 
/* 104 */     if (!(obj instanceof TimePeriodAnchor))
/* 105 */       return false; 
/* 108 */     TimePeriodAnchor position = (TimePeriodAnchor)obj;
/* 109 */     if (!this.name.equals(position.name))
/* 110 */       return false; 
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 122 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 133 */     if (equals(START))
/* 134 */       return START; 
/* 136 */     if (equals(MIDDLE))
/* 137 */       return MIDDLE; 
/* 139 */     if (equals(END))
/* 140 */       return END; 
/* 142 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimePeriodAnchor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */