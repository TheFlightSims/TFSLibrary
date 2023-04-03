/*     */ package org.apache.commons.lang.enums;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
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
/* 131 */     super(name);
/* 132 */     this.iValue = value;
/*     */   }
/*     */   
/*     */   protected static Enum getEnum(Class enumClass, int value) {
/* 148 */     if (enumClass == null)
/* 149 */       throw new IllegalArgumentException("The Enum Class must not be null"); 
/* 151 */     List list = Enum.getEnumList(enumClass);
/* 152 */     for (Iterator it = list.iterator(); it.hasNext(); ) {
/* 153 */       ValuedEnum enumeration = it.next();
/* 154 */       if (enumeration.getValue() == value)
/* 155 */         return enumeration; 
/*     */     } 
/* 158 */     return null;
/*     */   }
/*     */   
/*     */   public final int getValue() {
/* 167 */     return this.iValue;
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/* 188 */     if (other == this)
/* 189 */       return 0; 
/* 191 */     if (other.getClass() != getClass()) {
/* 192 */       if (other.getClass().getName().equals(getClass().getName()))
/* 193 */         return this.iValue - getValueInOtherClassLoader(other); 
/* 195 */       throw new ClassCastException("Different enum class '" + ClassUtils.getShortClassName(other.getClass()) + "'");
/*     */     } 
/* 198 */     return this.iValue - ((ValuedEnum)other).iValue;
/*     */   }
/*     */   
/*     */   private int getValueInOtherClassLoader(Object other) {
/*     */     try {
/* 209 */       Method mth = other.getClass().getMethod("getValue", null);
/* 210 */       Integer value = (Integer)mth.invoke(other, null);
/* 211 */       return value.intValue();
/* 212 */     } catch (NoSuchMethodException e) {
/*     */     
/* 214 */     } catch (IllegalAccessException e) {
/*     */     
/* 216 */     } catch (InvocationTargetException e) {}
/* 219 */     throw new IllegalStateException("This should not happen");
/*     */   }
/*     */   
/*     */   public String toString() {
/* 230 */     if (this.iToString == null) {
/* 231 */       String shortName = ClassUtils.getShortClassName(getEnumClass());
/* 232 */       this.iToString = shortName + "[" + getName() + "=" + getValue() + "]";
/*     */     } 
/* 234 */     return this.iToString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enums\ValuedEnum.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */