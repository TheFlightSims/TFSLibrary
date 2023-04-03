/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import org.opengis.temporal.Instant;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.temporal.RelativePosition;
/*     */ import org.opengis.temporal.TemporalOrder;
/*     */ import org.opengis.temporal.TemporalPrimitive;
/*     */ 
/*     */ public abstract class DefaultTemporalPrimitive extends DefaultTemporalObject implements TemporalPrimitive, TemporalOrder, Comparable<TemporalPrimitive> {
/*     */   public int compareTo(TemporalPrimitive that) {
/*  38 */     if (that == null)
/*  39 */       throw new IllegalArgumentException("Provided temporal object is null"); 
/*  40 */     RelativePosition pos = relativePosition(that);
/*  41 */     if (pos == null)
/*  42 */       throw new ClassCastException("The provided object cannot be compared to this one"); 
/*  43 */     if (pos == RelativePosition.BEFORE)
/*  44 */       return -1; 
/*  45 */     if (pos == RelativePosition.AFTER)
/*  46 */       return 1; 
/*  48 */     if (pos == RelativePosition.EQUALS)
/*  49 */       return 0; 
/*  52 */     if ((this instanceof Period && that instanceof Instant) || (this instanceof Instant && that instanceof Period))
/*  54 */       if (pos == RelativePosition.ENDED_BY || pos == RelativePosition.BEGUN_BY || pos == RelativePosition.CONTAINS)
/*  57 */         return 0;  
/*  61 */     if (this instanceof Period && that instanceof Period) {
/*  62 */       if (pos == RelativePosition.MEETS)
/*  63 */         return -1; 
/*  64 */       if (pos == RelativePosition.BEGINS)
/*  65 */         return -1; 
/*  66 */       if (pos == RelativePosition.BEGUN_BY)
/*  67 */         return 1; 
/*  68 */       if (pos == RelativePosition.ENDS)
/*  69 */         return 1; 
/*  70 */       if (pos == RelativePosition.ENDED_BY)
/*  71 */         return -1; 
/*  72 */       if (pos == RelativePosition.OVERLAPS)
/*  73 */         return -1; 
/*  74 */       if (pos == RelativePosition.OVERLAPPED_BY)
/*  75 */         return 1; 
/*  76 */       if (pos == RelativePosition.DURING || pos == RelativePosition.CONTAINS || pos == RelativePosition.EQUALS)
/*  79 */         return 0; 
/*     */     } 
/*  82 */     throw new IllegalStateException("Unable to compare the provided object with this one");
/*     */   }
/*     */   
/*     */   public RelativePosition relativePosition(TemporalPrimitive other) {
/*  93 */     if (this instanceof Instant && other instanceof Instant) {
/*  94 */       Instant timeobject = (Instant)this;
/*  95 */       Instant instantOther = (Instant)other;
/*  97 */       if (timeobject.getPosition().getDate().before(instantOther.getPosition().getDate()))
/*  98 */         return RelativePosition.BEFORE; 
/* 100 */       return (timeobject.getPosition().getDate().compareTo(instantOther.getPosition().getDate()) == 0) ? RelativePosition.EQUALS : RelativePosition.AFTER;
/*     */     } 
/* 104 */     if (this instanceof Period && other instanceof Instant) {
/* 105 */       Period timeobject = (Period)this;
/* 106 */       Instant instantarg = (Instant)other;
/* 108 */       if (timeobject.getEnding().getPosition().getDate().before(instantarg.getPosition().getDate()))
/* 109 */         return RelativePosition.BEFORE; 
/* 111 */       if (timeobject.getEnding().getPosition().getDate().compareTo(instantarg.getPosition().getDate()) == 0)
/* 112 */         return RelativePosition.ENDED_BY; 
/* 114 */       if (timeobject.getBeginning().getPosition().getDate().before(instantarg.getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().after(instantarg.getPosition().getDate()))
/* 116 */         return RelativePosition.CONTAINS; 
/* 118 */       return (timeobject.getBeginning().getPosition().getDate().compareTo(instantarg.getPosition().getDate()) == 0) ? RelativePosition.BEGUN_BY : RelativePosition.AFTER;
/*     */     } 
/* 123 */     if (this instanceof Instant && other instanceof Period) {
/* 124 */       Instant timeobject = (Instant)this;
/* 125 */       Period instantarg = (Period)other;
/* 127 */       if (instantarg.getEnding().getPosition().getDate().before(timeobject.getPosition().getDate()))
/* 128 */         return RelativePosition.AFTER; 
/* 130 */       if (instantarg.getEnding().getPosition().getDate().compareTo(timeobject.getPosition().getDate()) == 0)
/* 131 */         return RelativePosition.ENDS; 
/* 133 */       if (instantarg.getBeginning().getPosition().getDate().before(timeobject.getPosition().getDate()) && instantarg.getEnding().getPosition().getDate().after(timeobject.getPosition().getDate()))
/* 135 */         return RelativePosition.DURING; 
/* 137 */       return (instantarg.getBeginning().getPosition().getDate().compareTo(timeobject.getPosition().getDate()) == 0) ? RelativePosition.BEGINS : RelativePosition.BEFORE;
/*     */     } 
/* 142 */     if (this instanceof Period && other instanceof Period) {
/* 143 */       Period timeobject = (Period)this;
/* 144 */       Period instantarg = (Period)other;
/* 146 */       if (timeobject.getEnding().getPosition().getDate().before(instantarg.getBeginning().getPosition().getDate()))
/* 147 */         return RelativePosition.BEFORE; 
/* 149 */       if (timeobject.getEnding().getPosition().getDate().compareTo(instantarg.getBeginning().getPosition().getDate()) == 0)
/* 150 */         return RelativePosition.MEETS; 
/* 152 */       if (timeobject.getBeginning().getPosition().getDate().before(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().after(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().before(instantarg.getEnding().getPosition().getDate()))
/* 155 */         return RelativePosition.OVERLAPS; 
/* 157 */       if (timeobject.getBeginning().getPosition().getDate().compareTo(instantarg.getBeginning().getPosition().getDate()) == 0 && timeobject.getEnding().getPosition().getDate().before(instantarg.getEnding().getPosition().getDate()))
/* 159 */         return RelativePosition.BEGINS; 
/* 161 */       if (timeobject.getBeginning().getPosition().getDate().compareTo(instantarg.getBeginning().getPosition().getDate()) == 0 && timeobject.getEnding().getPosition().getDate().after(instantarg.getEnding().getPosition().getDate()))
/* 163 */         return RelativePosition.BEGUN_BY; 
/* 165 */       if (timeobject.getBeginning().getPosition().getDate().after(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().before(instantarg.getEnding().getPosition().getDate()))
/* 167 */         return RelativePosition.DURING; 
/* 169 */       if (timeobject.getBeginning().getPosition().getDate().before(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().after(instantarg.getEnding().getPosition().getDate()))
/* 171 */         return RelativePosition.CONTAINS; 
/* 173 */       if (timeobject.getBeginning().getPosition().getDate().compareTo(instantarg.getBeginning().getPosition().getDate()) == 0 && timeobject.getEnding().getPosition().getDate().compareTo(instantarg.getEnding().getPosition().getDate()) == 0)
/* 175 */         return RelativePosition.EQUALS; 
/* 177 */       if (timeobject.getBeginning().getPosition().getDate().after(instantarg.getBeginning().getPosition().getDate()) && timeobject.getBeginning().getPosition().getDate().before(instantarg.getEnding().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().after(instantarg.getEnding().getPosition().getDate()))
/* 180 */         return RelativePosition.OVERLAPPED_BY; 
/* 182 */       if (timeobject.getBeginning().getPosition().getDate().after(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().compareTo(instantarg.getEnding().getPosition().getDate()) == 0)
/* 184 */         return RelativePosition.ENDS; 
/* 186 */       if (timeobject.getBeginning().getPosition().getDate().before(instantarg.getBeginning().getPosition().getDate()) && timeobject.getEnding().getPosition().getDate().compareTo(instantarg.getEnding().getPosition().getDate()) == 0)
/* 188 */         return RelativePosition.ENDED_BY; 
/* 190 */       return (timeobject.getBeginning().getPosition().getDate().compareTo(instantarg.getEnding().getPosition().getDate()) == 0) ? RelativePosition.MET_BY : RelativePosition.AFTER;
/*     */     } 
/* 203 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultTemporalPrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */