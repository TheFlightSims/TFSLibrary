/*      */ package org.geotools.util;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Arrays;
/*      */ import java.util.Comparator;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.SortedSet;
/*      */ import org.geotools.resources.ClassChanger;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.opengis.util.Cloneable;
/*      */ 
/*      */ public class RangeSet<T extends Comparable<? super T>> extends AbstractSet<Range<T>> implements SortedSet<Range<T>>, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 2439002271813328080L;
/*      */   
/*   69 */   private static final Comparator<Range> COMPARATOR = new Comparator<Range>() {
/*      */       public int compare(Range r1, Range r2) {
/*   72 */         int cmin = r1.getMinValue().compareTo(r2.getMinValue());
/*   73 */         int cmax = r1.getMaxValue().compareTo(r2.getMaxValue());
/*   74 */         if (cmin == 0)
/*   74 */           cmin = (r1.isMinIncluded() ? -1 : 0) - (r2.isMinIncluded() ? -1 : 0); 
/*   75 */         if (cmax == 0)
/*   75 */           cmax = (r1.isMaxIncluded() ? 1 : 0) - (r2.isMaxIncluded() ? 1 : 0); 
/*   76 */         if (cmin == cmax)
/*   76 */           return cmax; 
/*   77 */         if (cmin == 0)
/*   77 */           return cmax; 
/*   78 */         if (cmax == 0)
/*   78 */           return cmin; 
/*   80 */         throw new IllegalArgumentException("Unordered ranges");
/*      */       }
/*      */     };
/*      */   
/*      */   private final Class<T> elementClass;
/*      */   
/*      */   private final Class<?> relaxedClass;
/*      */   
/*      */   private final Class<?> arrayElementClass;
/*      */   
/*      */   private final byte arrayElementCode;
/*      */   
/*      */   private Object array;
/*      */   
/*      */   private int modCount;
/*      */   
/*      */   private final boolean isPrimitive;
/*      */   
/*      */   private final boolean useClassChanger;
/*      */   
/*      */   private final boolean isNumeric;
/*      */   
/*      */   public RangeSet(Class<T> type) throws IllegalArgumentException {
/*  163 */     if (!Comparable.class.isAssignableFrom(type))
/*  164 */       throw new IllegalArgumentException(Errors.format(123, type)); 
/*  166 */     Class<?> elementType = ClassChanger.getTransformedClass(type);
/*  167 */     this.useClassChanger = (elementType != type);
/*  168 */     this.elementClass = type;
/*  169 */     this.arrayElementClass = Classes.wrapperToPrimitive(elementType);
/*  170 */     this.arrayElementCode = Classes.getEnumConstant(this.arrayElementClass);
/*  171 */     this.isPrimitive = this.arrayElementClass.isPrimitive();
/*  172 */     this.isNumeric = Number.class.isAssignableFrom(type);
/*  173 */     this.relaxedClass = this.isNumeric ? Number.class : type;
/*      */   }
/*      */   
/*      */   private Comparable<?> toArrayElement(Comparable<?> value) {
/*  180 */     if (!this.relaxedClass.isInstance(value))
/*  181 */       throw new IllegalArgumentException((value == null) ? Errors.format(143, "value") : Errors.format(61, value.getClass(), this.elementClass)); 
/*  185 */     if (this.useClassChanger)
/*      */       try {
/*  186 */         value = (Comparable)ClassChanger.toNumber(value);
/*  187 */       } catch (ClassNotFoundException cause) {
/*  192 */         ClassCastException exception = new ClassCastException(Errors.format(61, value.getClass(), this.elementClass));
/*  194 */         exception.initCause(cause);
/*  195 */         throw exception;
/*      */       }  
/*  197 */     return value;
/*      */   }
/*      */   
/*      */   public Comparator<Range<T>> comparator() {
/*  205 */     return (Comparator)COMPARATOR;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  213 */     this.array = null;
/*  214 */     this.modCount++;
/*      */   }
/*      */   
/*      */   public int size() {
/*  221 */     return (this.array != null) ? (Array.getLength(this.array) / 2) : 0;
/*      */   }
/*      */   
/*      */   public boolean add(Range<T> range) {
/*  238 */     if (!range.isMinIncluded() || !range.isMaxIncluded())
/*  239 */       throw new UnsupportedOperationException("Open interval not yet supported"); 
/*  241 */     return add((Comparable<?>)range.getMinValue(), (Comparable<?>)range.getMaxValue());
/*      */   }
/*      */   
/*      */   public <N> boolean add(Comparable<? super N> min, Comparable<? super N> max) throws IllegalArgumentException {
/*      */     int i1;
/*  256 */     Comparable<?> lower = toArrayElement(min);
/*  257 */     Comparable<?> upper = toArrayElement(max);
/*  258 */     if (lower.compareTo(upper) > 0)
/*  259 */       throw new IllegalArgumentException(Errors.format(14, min, max)); 
/*  261 */     if (this.array == null) {
/*  262 */       this.modCount++;
/*  263 */       this.array = Array.newInstance(this.arrayElementClass, 2);
/*  264 */       Array.set(this.array, 0, lower);
/*  265 */       Array.set(this.array, 1, upper);
/*  266 */       return true;
/*      */     } 
/*  268 */     int modCountChk = this.modCount;
/*  269 */     int i0 = binarySearch(lower);
/*  271 */     if (i0 < 0) {
/*  284 */       if (((i0 ^= 0xFFFFFFFF) & 0x1) != 0) {
/*  285 */         lower = (Comparable)Array.get(this.array, --i0);
/*  286 */         i1 = binarySearch(upper);
/*  298 */       } else if (i0 != Array.getLength(this.array) && (i1 = binarySearch(upper)) != (i0 ^ 0xFFFFFFFF)) {
/*  299 */         this.modCount++;
/*  300 */         Array.set(this.array, i0, lower);
/*      */       } else {
/*  313 */         this.modCount++;
/*  314 */         Object old = this.array;
/*  315 */         int length = Array.getLength(this.array);
/*  316 */         this.array = Array.newInstance(this.arrayElementClass, length + 2);
/*  317 */         System.arraycopy(old, 0, this.array, 0, i0);
/*  318 */         System.arraycopy(old, i0, this.array, i0 + 2, length - i0);
/*  319 */         Array.set(this.array, i0 + 0, lower);
/*  320 */         Array.set(this.array, i0 + 1, upper);
/*  321 */         return true;
/*      */       } 
/*      */     } else {
/*  325 */       i0 &= 0xFFFFFFFE;
/*  326 */       i1 = binarySearch(upper);
/*      */     } 
/*  332 */     if (i1 < 0) {
/*  343 */       if (((i1 ^= 0xFFFFFFFF) & 0x1) != 0) {
/*  344 */         upper = (Comparable)Array.get(this.array, i1);
/*      */       } else {
/*  356 */         this.modCount++;
/*  357 */         Array.set(this.array, --i1, upper);
/*      */       } 
/*      */     } else {
/*  360 */       i1 |= 0x1;
/*      */     } 
/*  367 */     assert (i0 & 0x1) == 0 : i0;
/*  368 */     assert (i1 & 0x1) != 0 : i1;
/*  369 */     int n = i1 - ++i0;
/*  370 */     if (n > 0) {
/*  371 */       this.modCount++;
/*  372 */       Object old = this.array;
/*  373 */       int length = Array.getLength(this.array);
/*  374 */       this.array = Array.newInstance(this.arrayElementClass, length - n);
/*  375 */       System.arraycopy(old, 0, this.array, 0, i0);
/*  376 */       System.arraycopy(old, i1, this.array, i0, length - i1);
/*      */     } 
/*  378 */     assert (Array.getLength(this.array) & 0x1) == 0;
/*  379 */     return (modCountChk != this.modCount);
/*      */   }
/*      */   
/*      */   public boolean add(byte lower, byte upper) throws IllegalArgumentException {
/*  393 */     return add(Byte.valueOf(lower), Byte.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean add(short lower, short upper) throws IllegalArgumentException {
/*  407 */     return add(Short.valueOf(lower), Short.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean add(int lower, int upper) throws IllegalArgumentException {
/*  421 */     return add(Integer.valueOf(lower), Integer.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean add(long lower, long upper) throws IllegalArgumentException {
/*  435 */     return add(Long.valueOf(lower), Long.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean add(float lower, float upper) throws IllegalArgumentException {
/*  449 */     return add(Float.valueOf(lower), Float.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean add(double lower, double upper) throws IllegalArgumentException {
/*  463 */     return add(Double.valueOf(lower), Double.valueOf(upper));
/*      */   }
/*      */   
/*      */   public <N> boolean remove(Comparable<? super N> min, Comparable<? super N> max) throws IllegalArgumentException {
/*  477 */     Comparable<?> lower = toArrayElement(min);
/*  478 */     Comparable<?> upper = toArrayElement(max);
/*  479 */     if (lower.compareTo(upper) >= 0)
/*  480 */       throw new IllegalArgumentException(Errors.format(14, min, max)); 
/*  483 */     if (this.array == null)
/*  484 */       return false; 
/*  486 */     int modCountChk = this.modCount;
/*  487 */     int i0 = binarySearch(lower);
/*  488 */     int i1 = binarySearch(upper);
/*  489 */     if (i0 < 0) {
/*  490 */       if (((i0 ^= 0xFFFFFFFF) & 0x1) != 0) {
/*  502 */         this.modCount++;
/*  503 */         if (i1 != (i0 ^ 0xFFFFFFFF)) {
/*  504 */           Array.set(this.array, i0, lower);
/*      */         } else {
/*  514 */           Object old = this.array;
/*  515 */           int length = Array.getLength(this.array);
/*  516 */           this.array = Array.newInstance(this.arrayElementClass, length + 2);
/*  517 */           System.arraycopy(old, 0, this.array, 0, i0);
/*  518 */           System.arraycopy(old, i0, this.array, i0 + 2, length - i0);
/*  519 */           Array.set(this.array, i0 + 0, lower);
/*  520 */           Array.set(this.array, i0 + 1, upper);
/*  521 */           return true;
/*      */         } 
/*      */       } else {
/*  534 */         i0--;
/*      */       } 
/*  537 */     } else if ((i0 & 0x1) == 0) {
/*  538 */       i0--;
/*      */     } 
/*  545 */     if (i1 < 0) {
/*  556 */       if (((i1 ^= 0xFFFFFFFF) & 0x1) != 0) {
/*  557 */         this.modCount++;
/*  558 */         Array.set(this.array, --i1, upper);
/*      */       } 
/*      */     } else {
/*  572 */       i1 &= 0xFFFFFFFE;
/*      */     } 
/*  579 */     assert (i0 & 0x1) != 0 : i0;
/*  580 */     assert (i1 & 0x1) == 0 : i1;
/*  581 */     int n = i1 - ++i0;
/*  582 */     if (n > 0) {
/*  583 */       this.modCount++;
/*  584 */       Object old = this.array;
/*  585 */       int length = Array.getLength(this.array);
/*  586 */       this.array = Array.newInstance(this.arrayElementClass, length - n);
/*  587 */       System.arraycopy(old, 0, this.array, 0, i0);
/*  588 */       System.arraycopy(old, i1, this.array, i0, length - i1);
/*      */     } 
/*  590 */     assert (Array.getLength(this.array) & 0x1) == 0;
/*  591 */     return (modCountChk != this.modCount);
/*      */   }
/*      */   
/*      */   public boolean remove(byte lower, byte upper) throws IllegalArgumentException {
/*  603 */     return remove(Byte.valueOf(lower), Byte.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean remove(short lower, short upper) throws IllegalArgumentException {
/*  615 */     return remove(Short.valueOf(lower), Short.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean remove(int lower, int upper) throws IllegalArgumentException {
/*  627 */     return remove(Integer.valueOf(lower), Integer.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean remove(long lower, long upper) throws IllegalArgumentException {
/*  639 */     return remove(Long.valueOf(lower), Long.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean remove(float lower, float upper) throws IllegalArgumentException {
/*  651 */     return remove(Float.valueOf(lower), Float.valueOf(upper));
/*      */   }
/*      */   
/*      */   public boolean remove(double lower, double upper) throws IllegalArgumentException {
/*  663 */     return remove(Double.valueOf(lower), Double.valueOf(upper));
/*      */   }
/*      */   
/*      */   private int binarySearch(Comparable value) {
/*  675 */     switch (this.arrayElementCode) {
/*      */       case 8:
/*  676 */         return Arrays.binarySearch((double[])this.array, ((Number)value).doubleValue());
/*      */       case 7:
/*  677 */         return Arrays.binarySearch((float[])this.array, ((Number)value).floatValue());
/*      */       case 6:
/*  678 */         return Arrays.binarySearch((long[])this.array, ((Number)value).longValue());
/*      */       case 5:
/*  679 */         return Arrays.binarySearch((int[])this.array, ((Number)value).intValue());
/*      */       case 4:
/*  680 */         return Arrays.binarySearch((short[])this.array, ((Number)value).shortValue());
/*      */       case 3:
/*  681 */         return Arrays.binarySearch((byte[])this.array, ((Number)value).byteValue());
/*      */       case 2:
/*  682 */         return Arrays.binarySearch((char[])this.array, ((Character)value).charValue());
/*      */     } 
/*  683 */     return Arrays.binarySearch((Object[])this.array, value);
/*      */   }
/*      */   
/*      */   private Range<T> newRange(T lower, T upper) {
/*  694 */     if (this.isNumeric)
/*  695 */       return (Range)new NumberRange<Number>((Class)this.elementClass, (Comparable<Number>)lower, (Comparable<Number>)upper); 
/*  697 */     return new Range<T>(this.elementClass, lower, upper);
/*      */   }
/*      */   
/*      */   private T get(int index) {
/*  706 */     Comparable value = (Comparable)Array.get(this.array, index);
/*  707 */     if (this.useClassChanger)
/*      */       try {
/*  708 */         value = ClassChanger.toComparable((Number)value, this.elementClass);
/*  709 */       } catch (ClassNotFoundException exception) {
/*  712 */         throw new IllegalStateException(exception);
/*      */       }  
/*  714 */     return this.elementClass.cast(value);
/*      */   }
/*      */   
/*      */   public final double getMinValueAsDouble(int index) throws IndexOutOfBoundsException, ClassCastException {
/*  730 */     index *= 2;
/*  731 */     return this.isPrimitive ? Array.getDouble(this.array, index) : ((Number)Array.get(this.array, index)).doubleValue();
/*      */   }
/*      */   
/*      */   public final double getMaxValueAsDouble(int index) throws IndexOutOfBoundsException, ClassCastException {
/*  748 */     index = 2 * index + 1;
/*  749 */     return this.isPrimitive ? Array.getDouble(this.array, index) : ((Number)Array.get(this.array, index)).doubleValue();
/*      */   }
/*      */   
/*      */   public int indexOfRange(Comparable<?> value) {
/*  761 */     int index = binarySearch(toArrayElement(value));
/*  762 */     if (index < 0) {
/*  765 */       index ^= 0xFFFFFFFF;
/*  766 */       if ((index & 0x1) == 0)
/*  767 */         return -1; 
/*      */     } 
/*  770 */     index /= 2;
/*  771 */     assert newRange(get(2 * index), get(2 * index + 1)).contains(value) : value;
/*  772 */     return index;
/*      */   }
/*      */   
/*      */   public boolean contains(Object object) {
/*  784 */     Range<T> range = (Range<T>)object;
/*  785 */     if (this.elementClass.equals(range.elementClass) && 
/*  786 */       range.isMinIncluded() && range.isMaxIncluded()) {
/*  787 */       int index = binarySearch(toArrayElement((Comparable<?>)range.getMinValue()));
/*  788 */       if (index >= 0 && (index & 0x1) == 0) {
/*  790 */         int c = get(index + 1).compareTo(range.getMaxValue());
/*  791 */         return (c == 0);
/*      */       } 
/*      */     } 
/*  795 */     return false;
/*      */   }
/*      */   
/*      */   public Range<T> first() throws NoSuchElementException {
/*  804 */     if (this.array != null && Array.getLength(this.array) != 0)
/*  805 */       return newRange(get(0), get(1)); 
/*  807 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public Range<T> last() throws NoSuchElementException {
/*  816 */     if (this.array != null) {
/*  817 */       int length = Array.getLength(this.array);
/*  818 */       if (length != 0)
/*  819 */         return newRange(get(length - 2), get(length - 1)); 
/*      */     } 
/*  822 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public SortedSet<Range<T>> subSet(Range<T> lower, Range<T> upper) {
/*  834 */     throw new UnsupportedOperationException("Not yet implemented");
/*      */   }
/*      */   
/*      */   public SortedSet<Range<T>> headSet(Range<T> upper) {
/*  845 */     throw new UnsupportedOperationException("Not yet implemented");
/*      */   }
/*      */   
/*      */   public SortedSet<Range<T>> tailSet(Range<T> lower) {
/*  856 */     throw new UnsupportedOperationException("Not yet implemented");
/*      */   }
/*      */   
/*      */   public java.util.Iterator<Range<T>> iterator() {
/*  865 */     return new Iterator();
/*      */   }
/*      */   
/*      */   private final class Iterator implements java.util.Iterator<Range<T>> {
/*  880 */     private int modCount = RangeSet.this.modCount;
/*      */     
/*  885 */     private int length = (RangeSet.this.array != null) ? Array.getLength(RangeSet.this.array) : 0;
/*      */     
/*      */     private int position;
/*      */     
/*      */     public boolean hasNext() {
/*  896 */       return (this.position < this.length);
/*      */     }
/*      */     
/*      */     public Range<T> next() {
/*  903 */       if (hasNext()) {
/*  904 */         Comparable comparable1 = (Comparable)RangeSet.this.get(this.position++);
/*  905 */         Comparable comparable2 = (Comparable)RangeSet.this.get(this.position++);
/*  906 */         if (RangeSet.this.modCount != this.modCount)
/*  909 */           throw new ConcurrentModificationException(); 
/*  911 */         return RangeSet.this.newRange((T)comparable1, (T)comparable2);
/*      */       } 
/*  913 */       throw new NoSuchElementException();
/*      */     }
/*      */     
/*      */     public void remove() {
/*  921 */       if (this.position != 0) {
/*  922 */         if (RangeSet.this.modCount == this.modCount) {
/*  923 */           Object newArray = Array.newInstance(RangeSet.this.arrayElementClass, this.length -= 2);
/*  924 */           System.arraycopy(RangeSet.this.array, this.position, newArray, this.position -= 2, this.length - this.position);
/*  925 */           System.arraycopy(RangeSet.this.array, 0, newArray, 0, this.position);
/*  926 */           RangeSet.this.array = newArray;
/*  927 */           this.modCount = ++RangeSet.this.modCount;
/*      */         } else {
/*  929 */           throw new ConcurrentModificationException();
/*      */         } 
/*      */       } else {
/*  932 */         throw new IllegalStateException();
/*      */       } 
/*      */     }
/*      */     
/*      */     private Iterator() {}
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  944 */     int code = this.elementClass.hashCode();
/*  945 */     if (this.array != null)
/*  946 */       for (int i = Array.getLength(this.array); i >= 0;)
/*  947 */         code = code * 37 + Array.get(this.array, i).hashCode();  
/*  950 */     return code;
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/*  961 */     if (object != null && object.getClass().equals(getClass())) {
/*  962 */       RangeSet that = (RangeSet)object;
/*  963 */       if (Utilities.equals(this.elementClass, that.elementClass)) {
/*  964 */         switch (this.arrayElementCode) {
/*      */           case 8:
/*  965 */             return Arrays.equals((double[])this.array, (double[])that.array);
/*      */           case 7:
/*  966 */             return Arrays.equals((float[])this.array, (float[])that.array);
/*      */           case 6:
/*  967 */             return Arrays.equals((long[])this.array, (long[])that.array);
/*      */           case 5:
/*  968 */             return Arrays.equals((int[])this.array, (int[])that.array);
/*      */           case 4:
/*  969 */             return Arrays.equals((short[])this.array, (short[])that.array);
/*      */           case 3:
/*  970 */             return Arrays.equals((byte[])this.array, (byte[])that.array);
/*      */           case 2:
/*  971 */             return Arrays.equals((char[])this.array, (char[])that.array);
/*      */         } 
/*  972 */         return Arrays.equals((Object[])this.array, (Object[])that.array);
/*      */       } 
/*      */     } 
/*  976 */     return false;
/*      */   }
/*      */   
/*      */   public RangeSet clone() {
/*      */     RangeSet set;
/*      */     try {
/*  988 */       set = (RangeSet)super.clone();
/*  989 */     } catch (CloneNotSupportedException exception) {
/*  991 */       throw new AssertionError(exception);
/*      */     } 
/*  993 */     switch (set.arrayElementCode) {
/*      */       case 8:
/*  994 */         set.array = ((double[])set.array).clone();
/* 1003 */         return set;
/*      */       case 7:
/*      */         set.array = ((float[])set.array).clone();
/* 1003 */         return set;
/*      */       case 6:
/*      */         set.array = ((long[])set.array).clone();
/* 1003 */         return set;
/*      */       case 5:
/*      */         set.array = ((int[])set.array).clone();
/* 1003 */         return set;
/*      */       case 4:
/*      */         set.array = ((short[])set.array).clone();
/* 1003 */         return set;
/*      */       case 3:
/*      */         set.array = ((byte[])set.array).clone();
/* 1003 */         return set;
/*      */       case 2:
/*      */         set.array = ((char[])set.array).clone();
/* 1003 */         return set;
/*      */     } 
/*      */     set.array = ((Object[])set.array).clone();
/* 1003 */     return set;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1013 */     StringBuilder buffer = new StringBuilder(Classes.getShortClassName(this));
/* 1014 */     buffer.append('[');
/* 1015 */     boolean first = true;
/* 1016 */     for (Range range : this) {
/* 1017 */       if (!first)
/* 1018 */         buffer.append(','); 
/* 1020 */       buffer.append('{').append(range.getMinValue()).append("..").append(range.getMaxValue()).append('}');
/* 1022 */       first = false;
/*      */     } 
/* 1024 */     return buffer.append(']').toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\RangeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */