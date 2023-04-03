/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ public class CalcUtil {
/*     */   static Number sum(Number[] numbers) {
/*  36 */     Number newSum = (Number)getObject((Object[])numbers);
/*  38 */     if (newSum == null)
/*  39 */       return null; 
/*  43 */     if (newSum instanceof Integer) {
/*  44 */       int sum = 0;
/*  47 */       for (int i = 0; i < numbers.length; i++) {
/*  48 */         int nextValue = numbers[i].intValue();
/*  49 */         sum += nextValue;
/*     */       } 
/*  52 */       newSum = new Integer(sum);
/*  53 */     } else if (newSum instanceof Long) {
/*  54 */       long sum = 0L;
/*  57 */       for (int i = 0; i < numbers.length; i++) {
/*  58 */         long nextValue = numbers[i].longValue();
/*  59 */         sum += nextValue;
/*     */       } 
/*  62 */       newSum = new Long(sum);
/*  63 */     } else if (newSum instanceof Float) {
/*  64 */       float sum = 0.0F;
/*  67 */       for (int i = 0; i < numbers.length; i++) {
/*  68 */         float nextValue = numbers[i].floatValue();
/*  69 */         sum += nextValue;
/*     */       } 
/*  72 */       newSum = new Float(sum);
/*  73 */     } else if (newSum instanceof Double) {
/*  74 */       double sum = 0.0D;
/*  77 */       for (int i = 0; i < numbers.length; i++) {
/*  78 */         double nextValue = numbers[i].doubleValue();
/*  79 */         sum += nextValue;
/*     */       } 
/*  82 */       newSum = new Double(sum);
/*     */     } else {
/*  84 */       return null;
/*     */     } 
/*  87 */     return newSum;
/*     */   }
/*     */   
/*     */   static Number divide(Number num1, Number num2) {
/*  98 */     Number[] both = new Number[2];
/*  99 */     both[0] = num1;
/* 100 */     both[1] = num2;
/* 102 */     Number division = (Number)getObject((Object[])both);
/* 104 */     if (division == null)
/* 105 */       return null; 
/* 109 */     if (division instanceof Integer)
/* 111 */       return new Double(num1.doubleValue() / num2.doubleValue()); 
/* 112 */     if (division instanceof Long)
/* 113 */       return new Long(num1.longValue() / num2.longValue()); 
/* 114 */     if (division instanceof Float)
/* 115 */       return new Float(num1.floatValue() / num2.floatValue()); 
/* 116 */     if (division instanceof Double)
/* 117 */       return new Double(num1.doubleValue() / num2.doubleValue()); 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   static Number average(Number[] numbers) {
/* 128 */     Number sum = sum(numbers);
/* 130 */     return divide(sum, new Integer(numbers.length));
/*     */   }
/*     */   
/*     */   static Class bestClass(Object[] objects) {
/* 139 */     boolean hasInt = false;
/* 140 */     boolean hasFloat = false;
/* 141 */     boolean hasLong = false;
/* 142 */     boolean hasDouble = false;
/* 143 */     boolean hasString = false;
/* 145 */     for (int i = 0; i < objects.length; i++) {
/* 146 */       if (objects[i] instanceof Double) {
/* 147 */         hasDouble = true;
/* 148 */       } else if (objects[i] instanceof Float) {
/* 149 */         hasFloat = true;
/* 150 */       } else if (objects[i] instanceof Long) {
/* 151 */         hasLong = true;
/* 152 */       } else if (objects[i] instanceof Integer) {
/* 153 */         hasInt = true;
/* 154 */       } else if (objects[i] instanceof String) {
/* 155 */         hasString = true;
/*     */       } 
/*     */     } 
/* 159 */     if (hasString)
/* 160 */       return String.class; 
/* 161 */     if (hasDouble)
/* 162 */       return Double.class; 
/* 163 */     if (hasFloat)
/* 164 */       return Float.class; 
/* 165 */     if (hasLong)
/* 166 */       return Long.class; 
/* 167 */     if (hasInt)
/* 168 */       return Integer.class; 
/* 171 */     return null;
/*     */   }
/*     */   
/*     */   static Object convert(Object var, Class<Integer> type) {
/* 183 */     if (var instanceof Number) {
/* 185 */       Number newNum = (Number)var;
/* 187 */       if (type == Integer.class)
/* 188 */         return new Integer(newNum.intValue()); 
/* 189 */       if (type == Long.class)
/* 190 */         return new Long(newNum.longValue()); 
/* 191 */       if (type == Float.class)
/* 192 */         return new Float(newNum.floatValue()); 
/* 193 */       if (type == Double.class)
/* 194 */         return new Double(newNum.doubleValue()); 
/* 195 */       if (type == String.class)
/* 196 */         return new String(newNum.toString()); 
/*     */     } else {
/* 200 */       if (type == Integer.class)
/* 201 */         return new Integer(((Integer)var).intValue()); 
/* 202 */       if (type == Long.class)
/* 203 */         return new Long(((Long)var).longValue()); 
/* 204 */       if (type == Float.class)
/* 205 */         return new Float(((Float)var).floatValue()); 
/* 206 */       if (type == Double.class)
/* 207 */         return new Double(((Double)var).doubleValue()); 
/* 208 */       if (type == String.class)
/* 209 */         return new String(var.toString()); 
/*     */     } 
/* 213 */     return null;
/*     */   }
/*     */   
/*     */   static Object convert(Object[] objects, Object var) {
/* 217 */     Object newVar = getObject(objects);
/* 219 */     if (newVar instanceof Number) {
/* 220 */       Number newNum = (Number)var;
/* 222 */       if (newVar instanceof Integer)
/* 223 */         return new Integer(newNum.intValue()); 
/* 224 */       if (newVar instanceof Long)
/* 225 */         return new Long(newNum.longValue()); 
/* 226 */       if (newVar instanceof Float)
/* 227 */         return new Float(newNum.floatValue()); 
/* 228 */       if (newVar instanceof Double)
/* 229 */         return new Double(newNum.doubleValue()); 
/* 231 */       return null;
/*     */     } 
/* 233 */     if (newVar instanceof String)
/* 234 */       return new String((String)newVar); 
/* 237 */     return null;
/*     */   }
/*     */   
/*     */   static Object getObject(Object[] objects) {
/* 250 */     Class<String> bestClass = bestClass(objects);
/* 252 */     if (bestClass == String.class)
/* 253 */       return new String(""); 
/* 254 */     if (bestClass == Double.class)
/* 255 */       return new Double(0.0D); 
/* 256 */     if (bestClass == Float.class)
/* 257 */       return new Float(0.0F); 
/* 258 */     if (bestClass == Long.class)
/* 259 */       return new Long(0L); 
/* 260 */     if (bestClass == Integer.class)
/* 261 */       return new Integer(0); 
/* 263 */     return null;
/*     */   }
/*     */   
/*     */   static int compare(Comparable<Comparable> val1, Comparable val2) {
/* 276 */     if (val1.getClass() == val2.getClass())
/* 278 */       return val1.compareTo(val2); 
/* 282 */     Object[] objects = { val1, val2 };
/* 283 */     Class<?> bestClass = bestClass(objects);
/* 285 */     if (bestClass != val1.getClass())
/* 286 */       val1 = (Comparable<Comparable>)convert(val1, bestClass); 
/* 289 */     if (bestClass != val2.getClass())
/* 290 */       val2 = (Comparable)convert(val2, bestClass); 
/* 294 */     return val1.compareTo(val2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\CalcUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */