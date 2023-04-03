/*     */ package org.apache.commons.lang.builder;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ 
/*     */ public class EqualsBuilder {
/*     */   private boolean isEquals = true;
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs) {
/* 124 */     return reflectionEquals(lhs, rhs, false, null, null);
/*     */   }
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, Collection excludeFields) {
/* 147 */     return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*     */   }
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, String[] excludeFields) {
/* 170 */     return reflectionEquals(lhs, rhs, false, null, excludeFields);
/*     */   }
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients) {
/* 194 */     return reflectionEquals(lhs, rhs, testTransients, null, null);
/*     */   }
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class reflectUpToClass) {
/* 223 */     return reflectionEquals(lhs, rhs, testTransients, reflectUpToClass, null);
/*     */   }
/*     */   
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class reflectUpToClass, String[] excludeFields) {
/*     */     Class testClass;
/* 254 */     if (lhs == rhs)
/* 255 */       return true; 
/* 257 */     if (lhs == null || rhs == null)
/* 258 */       return false; 
/* 264 */     Class lhsClass = lhs.getClass();
/* 265 */     Class rhsClass = rhs.getClass();
/* 267 */     if (lhsClass.isInstance(rhs)) {
/* 268 */       testClass = lhsClass;
/* 269 */       if (!rhsClass.isInstance(lhs))
/* 271 */         testClass = rhsClass; 
/* 273 */     } else if (rhsClass.isInstance(lhs)) {
/* 274 */       testClass = rhsClass;
/* 275 */       if (!lhsClass.isInstance(rhs))
/* 277 */         testClass = lhsClass; 
/*     */     } else {
/* 281 */       return false;
/*     */     } 
/* 283 */     EqualsBuilder equalsBuilder = new EqualsBuilder();
/*     */     try {
/* 285 */       reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/* 286 */       while (testClass.getSuperclass() != null && testClass != reflectUpToClass) {
/* 287 */         testClass = testClass.getSuperclass();
/* 288 */         reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/*     */       } 
/* 290 */     } catch (IllegalArgumentException e) {
/* 296 */       return false;
/*     */     } 
/* 298 */     return equalsBuilder.isEquals();
/*     */   }
/*     */   
/*     */   private static void reflectionAppend(Object lhs, Object rhs, Class clazz, EqualsBuilder builder, boolean useTransients, String[] excludeFields) {
/* 319 */     Field[] fields = clazz.getDeclaredFields();
/* 320 */     AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/* 321 */     for (int i = 0; i < fields.length && builder.isEquals; i++) {
/* 322 */       Field f = fields[i];
/* 323 */       if (!ArrayUtils.contains((Object[])excludeFields, f.getName()) && f.getName().indexOf('$') == -1 && (useTransients || !Modifier.isTransient(f.getModifiers())) && !Modifier.isStatic(f.getModifiers()))
/*     */         try {
/* 328 */           builder.append(f.get(lhs), f.get(rhs));
/* 329 */         } catch (IllegalAccessException e) {
/* 332 */           throw new InternalError("Unexpected IllegalAccessException");
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public EqualsBuilder appendSuper(boolean superEquals) {
/* 348 */     if (!this.isEquals)
/* 349 */       return this; 
/* 351 */     this.isEquals = superEquals;
/* 352 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(Object lhs, Object rhs) {
/* 366 */     if (!this.isEquals)
/* 367 */       return this; 
/* 369 */     if (lhs == rhs)
/* 370 */       return this; 
/* 372 */     if (lhs == null || rhs == null) {
/* 373 */       setEquals(false);
/* 374 */       return this;
/*     */     } 
/* 376 */     Class lhsClass = lhs.getClass();
/* 377 */     if (!lhsClass.isArray()) {
/* 379 */       this.isEquals = lhs.equals(rhs);
/* 380 */     } else if (lhs.getClass() != rhs.getClass()) {
/* 382 */       setEquals(false);
/* 386 */     } else if (lhs instanceof long[]) {
/* 387 */       append((long[])lhs, (long[])rhs);
/* 388 */     } else if (lhs instanceof int[]) {
/* 389 */       append((int[])lhs, (int[])rhs);
/* 390 */     } else if (lhs instanceof short[]) {
/* 391 */       append((short[])lhs, (short[])rhs);
/* 392 */     } else if (lhs instanceof char[]) {
/* 393 */       append((char[])lhs, (char[])rhs);
/* 394 */     } else if (lhs instanceof byte[]) {
/* 395 */       append((byte[])lhs, (byte[])rhs);
/* 396 */     } else if (lhs instanceof double[]) {
/* 397 */       append((double[])lhs, (double[])rhs);
/* 398 */     } else if (lhs instanceof float[]) {
/* 399 */       append((float[])lhs, (float[])rhs);
/* 400 */     } else if (lhs instanceof boolean[]) {
/* 401 */       append((boolean[])lhs, (boolean[])rhs);
/*     */     } else {
/* 404 */       append((Object[])lhs, (Object[])rhs);
/*     */     } 
/* 406 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(long lhs, long rhs) {
/* 421 */     if (!this.isEquals)
/* 422 */       return this; 
/* 424 */     this.isEquals = (lhs == rhs);
/* 425 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(int lhs, int rhs) {
/* 436 */     if (!this.isEquals)
/* 437 */       return this; 
/* 439 */     this.isEquals = (lhs == rhs);
/* 440 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(short lhs, short rhs) {
/* 451 */     if (!this.isEquals)
/* 452 */       return this; 
/* 454 */     this.isEquals = (lhs == rhs);
/* 455 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(char lhs, char rhs) {
/* 466 */     if (!this.isEquals)
/* 467 */       return this; 
/* 469 */     this.isEquals = (lhs == rhs);
/* 470 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(byte lhs, byte rhs) {
/* 481 */     if (!this.isEquals)
/* 482 */       return this; 
/* 484 */     this.isEquals = (lhs == rhs);
/* 485 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(double lhs, double rhs) {
/* 502 */     if (!this.isEquals)
/* 503 */       return this; 
/* 505 */     return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(float lhs, float rhs) {
/* 522 */     if (!this.isEquals)
/* 523 */       return this; 
/* 525 */     return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(boolean lhs, boolean rhs) {
/* 536 */     if (!this.isEquals)
/* 537 */       return this; 
/* 539 */     this.isEquals = (lhs == rhs);
/* 540 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(Object[] lhs, Object[] rhs) {
/* 554 */     if (!this.isEquals)
/* 555 */       return this; 
/* 557 */     if (lhs == rhs)
/* 558 */       return this; 
/* 560 */     if (lhs == null || rhs == null) {
/* 561 */       setEquals(false);
/* 562 */       return this;
/*     */     } 
/* 564 */     if (lhs.length != rhs.length) {
/* 565 */       setEquals(false);
/* 566 */       return this;
/*     */     } 
/* 568 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 569 */       append(lhs[i], rhs[i]); 
/* 571 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(long[] lhs, long[] rhs) {
/* 585 */     if (!this.isEquals)
/* 586 */       return this; 
/* 588 */     if (lhs == rhs)
/* 589 */       return this; 
/* 591 */     if (lhs == null || rhs == null) {
/* 592 */       setEquals(false);
/* 593 */       return this;
/*     */     } 
/* 595 */     if (lhs.length != rhs.length) {
/* 596 */       setEquals(false);
/* 597 */       return this;
/*     */     } 
/* 599 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 600 */       append(lhs[i], rhs[i]); 
/* 602 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(int[] lhs, int[] rhs) {
/* 616 */     if (!this.isEquals)
/* 617 */       return this; 
/* 619 */     if (lhs == rhs)
/* 620 */       return this; 
/* 622 */     if (lhs == null || rhs == null) {
/* 623 */       setEquals(false);
/* 624 */       return this;
/*     */     } 
/* 626 */     if (lhs.length != rhs.length) {
/* 627 */       setEquals(false);
/* 628 */       return this;
/*     */     } 
/* 630 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 631 */       append(lhs[i], rhs[i]); 
/* 633 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(short[] lhs, short[] rhs) {
/* 647 */     if (!this.isEquals)
/* 648 */       return this; 
/* 650 */     if (lhs == rhs)
/* 651 */       return this; 
/* 653 */     if (lhs == null || rhs == null) {
/* 654 */       setEquals(false);
/* 655 */       return this;
/*     */     } 
/* 657 */     if (lhs.length != rhs.length) {
/* 658 */       setEquals(false);
/* 659 */       return this;
/*     */     } 
/* 661 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 662 */       append(lhs[i], rhs[i]); 
/* 664 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(char[] lhs, char[] rhs) {
/* 678 */     if (!this.isEquals)
/* 679 */       return this; 
/* 681 */     if (lhs == rhs)
/* 682 */       return this; 
/* 684 */     if (lhs == null || rhs == null) {
/* 685 */       setEquals(false);
/* 686 */       return this;
/*     */     } 
/* 688 */     if (lhs.length != rhs.length) {
/* 689 */       setEquals(false);
/* 690 */       return this;
/*     */     } 
/* 692 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 693 */       append(lhs[i], rhs[i]); 
/* 695 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(byte[] lhs, byte[] rhs) {
/* 709 */     if (!this.isEquals)
/* 710 */       return this; 
/* 712 */     if (lhs == rhs)
/* 713 */       return this; 
/* 715 */     if (lhs == null || rhs == null) {
/* 716 */       setEquals(false);
/* 717 */       return this;
/*     */     } 
/* 719 */     if (lhs.length != rhs.length) {
/* 720 */       setEquals(false);
/* 721 */       return this;
/*     */     } 
/* 723 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 724 */       append(lhs[i], rhs[i]); 
/* 726 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(double[] lhs, double[] rhs) {
/* 740 */     if (!this.isEquals)
/* 741 */       return this; 
/* 743 */     if (lhs == rhs)
/* 744 */       return this; 
/* 746 */     if (lhs == null || rhs == null) {
/* 747 */       setEquals(false);
/* 748 */       return this;
/*     */     } 
/* 750 */     if (lhs.length != rhs.length) {
/* 751 */       setEquals(false);
/* 752 */       return this;
/*     */     } 
/* 754 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 755 */       append(lhs[i], rhs[i]); 
/* 757 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(float[] lhs, float[] rhs) {
/* 771 */     if (!this.isEquals)
/* 772 */       return this; 
/* 774 */     if (lhs == rhs)
/* 775 */       return this; 
/* 777 */     if (lhs == null || rhs == null) {
/* 778 */       setEquals(false);
/* 779 */       return this;
/*     */     } 
/* 781 */     if (lhs.length != rhs.length) {
/* 782 */       setEquals(false);
/* 783 */       return this;
/*     */     } 
/* 785 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 786 */       append(lhs[i], rhs[i]); 
/* 788 */     return this;
/*     */   }
/*     */   
/*     */   public EqualsBuilder append(boolean[] lhs, boolean[] rhs) {
/* 802 */     if (!this.isEquals)
/* 803 */       return this; 
/* 805 */     if (lhs == rhs)
/* 806 */       return this; 
/* 808 */     if (lhs == null || rhs == null) {
/* 809 */       setEquals(false);
/* 810 */       return this;
/*     */     } 
/* 812 */     if (lhs.length != rhs.length) {
/* 813 */       setEquals(false);
/* 814 */       return this;
/*     */     } 
/* 816 */     for (int i = 0; i < lhs.length && this.isEquals; i++)
/* 817 */       append(lhs[i], rhs[i]); 
/* 819 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isEquals() {
/* 829 */     return this.isEquals;
/*     */   }
/*     */   
/*     */   protected void setEquals(boolean isEquals) {
/* 839 */     this.isEquals = isEquals;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 847 */     this.isEquals = true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\EqualsBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */