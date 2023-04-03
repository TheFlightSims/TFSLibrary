/*     */ package org.apache.commons.lang.enum;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ 
/*     */ public abstract class ValuedEnum extends Enum {
/*     */   private static final long serialVersionUID = -7129650521543789085L;
/*     */   
/*     */   private final int iValue;
/*     */   
/*     */   protected ValuedEnum(String name, int value) {
/* 126 */     super(name);
/* 127 */     this.iValue = value;
/*     */   }
/*     */   
/*     */   protected static Enum getEnum(Class enumClass, int value) {
/* 143 */     if (enumClass == null)
/* 144 */       throw new IllegalArgumentException("The Enum Class must not be null"); 
/* 146 */     List list = Enum.getEnumList(enumClass);
/* 147 */     for (Iterator it = list.iterator(); it.hasNext(); ) {
/* 148 */       ValuedEnum enumeration = it.next();
/* 149 */       if (enumeration.getValue() == value)
/* 150 */         return enumeration; 
/*     */     } 
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   public final int getValue() {
/* 162 */     return this.iValue;
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/* 179 */     return this.iValue - ((ValuedEnum)other).iValue;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 190 */     if (this.iToString == null) {
/* 191 */       String shortName = ClassUtils.getShortClassName(getEnumClass());
/* 192 */       this.iToString = shortName + "[" + getName() + "=" + getValue() + "]";
/*     */     } 
/* 194 */     return this.iToString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enum\ValuedEnum.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */