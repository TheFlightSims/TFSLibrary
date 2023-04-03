/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @GwtCompatible
/*     */ public final class Preconditions {
/*     */   public static void checkArgument(boolean expression) {
/* 110 */     if (!expression)
/* 111 */       throw new IllegalArgumentException(); 
/*     */   }
/*     */   
/*     */   public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
/* 124 */     if (!expression)
/* 125 */       throw new IllegalArgumentException(String.valueOf(errorMessage)); 
/*     */   }
/*     */   
/*     */   public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
/* 147 */     if (!expression)
/* 148 */       throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs)); 
/*     */   }
/*     */   
/*     */   public static void checkState(boolean expression) {
/* 160 */     if (!expression)
/* 161 */       throw new IllegalStateException(); 
/*     */   }
/*     */   
/*     */   public static void checkState(boolean expression, @Nullable Object errorMessage) {
/* 175 */     if (!expression)
/* 176 */       throw new IllegalStateException(String.valueOf(errorMessage)); 
/*     */   }
/*     */   
/*     */   public static void checkState(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
/* 199 */     if (!expression)
/* 200 */       throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs)); 
/*     */   }
/*     */   
/*     */   public static <T> T checkNotNull(T reference) {
/* 212 */     if (reference == null)
/* 213 */       throw new NullPointerException(); 
/* 215 */     return reference;
/*     */   }
/*     */   
/*     */   public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
/* 228 */     if (reference == null)
/* 229 */       throw new NullPointerException(String.valueOf(errorMessage)); 
/* 231 */     return reference;
/*     */   }
/*     */   
/*     */   public static <T> T checkNotNull(T reference, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
/* 251 */     if (reference == null)
/* 253 */       throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs)); 
/* 255 */     return reference;
/*     */   }
/*     */   
/*     */   public static int checkElementIndex(int index, int size) {
/* 295 */     return checkElementIndex(index, size, "index");
/*     */   }
/*     */   
/*     */   public static int checkElementIndex(int index, int size, @Nullable String desc) {
/* 312 */     if (index < 0 || index >= size)
/* 313 */       throw new IndexOutOfBoundsException(badElementIndex(index, size, desc)); 
/* 315 */     return index;
/*     */   }
/*     */   
/*     */   private static String badElementIndex(int index, int size, String desc) {
/* 319 */     if (index < 0)
/* 320 */       return format("%s (%s) must not be negative", new Object[] { desc, Integer.valueOf(index) }); 
/* 321 */     if (size < 0)
/* 322 */       throw new IllegalArgumentException("negative size: " + size); 
/* 324 */     return format("%s (%s) must be less than size (%s)", new Object[] { desc, Integer.valueOf(index), Integer.valueOf(size) });
/*     */   }
/*     */   
/*     */   public static int checkPositionIndex(int index, int size) {
/* 339 */     return checkPositionIndex(index, size, "index");
/*     */   }
/*     */   
/*     */   public static int checkPositionIndex(int index, int size, @Nullable String desc) {
/* 355 */     if (index < 0 || index > size)
/* 356 */       throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc)); 
/* 358 */     return index;
/*     */   }
/*     */   
/*     */   private static String badPositionIndex(int index, int size, String desc) {
/* 362 */     if (index < 0)
/* 363 */       return format("%s (%s) must not be negative", new Object[] { desc, Integer.valueOf(index) }); 
/* 364 */     if (size < 0)
/* 365 */       throw new IllegalArgumentException("negative size: " + size); 
/* 367 */     return format("%s (%s) must not be greater than size (%s)", new Object[] { desc, Integer.valueOf(index), Integer.valueOf(size) });
/*     */   }
/*     */   
/*     */   public static void checkPositionIndexes(int start, int end, int size) {
/* 385 */     if (start < 0 || end < start || end > size)
/* 386 */       throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size)); 
/*     */   }
/*     */   
/*     */   private static String badPositionIndexes(int start, int end, int size) {
/* 391 */     if (start < 0 || start > size)
/* 392 */       return badPositionIndex(start, size, "start index"); 
/* 394 */     if (end < 0 || end > size)
/* 395 */       return badPositionIndex(end, size, "end index"); 
/* 398 */     return format("end index (%s) must not be less than start index (%s)", new Object[] { Integer.valueOf(end), Integer.valueOf(start) });
/*     */   }
/*     */   
/*     */   static String format(String template, @Nullable Object... args) {
/* 413 */     template = String.valueOf(template);
/* 416 */     StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
/* 417 */     int templateStart = 0;
/* 418 */     int i = 0;
/* 419 */     while (i < args.length) {
/* 420 */       int placeholderStart = template.indexOf("%s", templateStart);
/* 421 */       if (placeholderStart == -1)
/*     */         break; 
/* 424 */       builder.append(template.substring(templateStart, placeholderStart));
/* 425 */       builder.append(args[i++]);
/* 426 */       templateStart = placeholderStart + 2;
/*     */     } 
/* 428 */     builder.append(template.substring(templateStart));
/* 431 */     if (i < args.length) {
/* 432 */       builder.append(" [");
/* 433 */       builder.append(args[i++]);
/* 434 */       while (i < args.length) {
/* 435 */         builder.append(", ");
/* 436 */         builder.append(args[i++]);
/*     */       } 
/* 438 */       builder.append(']');
/*     */     } 
/* 441 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Preconditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */