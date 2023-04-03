/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NavigableSet;
/*     */ 
/*     */ public final class Fun {
/*  32 */   public static final Comparator COMPARATOR = new Comparator<Comparable>() {
/*     */       public int compare(Comparable<Comparable> o1, Comparable o2) {
/*  35 */         if (o1 == null)
/*  36 */           return (o2 == null) ? 0 : -1; 
/*  37 */         if (o2 == null)
/*  37 */           return 1; 
/*  39 */         if (o1 == Fun.HI)
/*  40 */           return (o2 == Fun.HI) ? 0 : 1; 
/*  41 */         if (o2 == Fun.HI)
/*  41 */           return -1; 
/*  43 */         return o1.compareTo(o2);
/*     */       }
/*     */     };
/*     */   
/*  47 */   public static final Comparator<Comparable> REVERSE_COMPARATOR = new Comparator<Comparable>() {
/*     */       public int compare(Comparable o1, Comparable o2) {
/*  50 */         return -Fun.COMPARATOR.compare((T)o1, (T)o2);
/*     */       }
/*     */     };
/*     */   
/*  56 */   public static final Iterator EMPTY_ITERATOR = (new ArrayList(0)).iterator();
/*     */   
/*  59 */   public static final Comparator<Tuple2> TUPLE2_COMPARATOR = (Comparator)new Tuple2Comparator<Object, Object>(null, null);
/*     */   
/*  60 */   public static final Comparator<Tuple3> TUPLE3_COMPARATOR = (Comparator)new Tuple3Comparator<Object, Object, Object>(null, null, null);
/*     */   
/*  61 */   public static final Comparator<Tuple4> TUPLE4_COMPARATOR = (Comparator)new Tuple4Comparator<Object, Object, Object, Object>(null, null, null, null);
/*     */   
/*  62 */   public static final Comparator<Tuple5> TUPLE5_COMPARATOR = (Comparator)new Tuple5Comparator<Object, Object, Object, Object, Object>(null, null, null, null, null);
/*     */   
/*  63 */   public static final Comparator<Tuple6> TUPLE6_COMPARATOR = (Comparator)new Tuple6Comparator<Object, Object, Object, Object, Object, Object>(null, null, null, null, null, null);
/*     */   
/*  70 */   public static final Object HI = new Comparable() {
/*     */       public String toString() {
/*  72 */         return "HI";
/*     */       }
/*     */       
/*     */       public int compareTo(Object o) {
/*  77 */         return (o == Fun.HI) ? 0 : 1;
/*     */       }
/*     */     };
/*     */   
/*     */   public static <A> A HI() {
/*  83 */     return (A)HI;
/*     */   }
/*     */   
/*     */   public static <A, B> Tuple2<A, B> t2(A a, B b) {
/*  87 */     return new Tuple2<A, B>(a, b);
/*     */   }
/*     */   
/*     */   public static <A, B, C> Tuple3<A, B, C> t3(A a, B b, C c) {
/*  91 */     return new Tuple3<A, B, C>(a, b, c);
/*     */   }
/*     */   
/*     */   public static <A, B, C, D> Tuple4<A, B, C, D> t4(A a, B b, C c, D d) {
/*  95 */     return new Tuple4<A, B, C, D>(a, b, c, d);
/*     */   }
/*     */   
/*     */   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> t5(A a, B b, C c, D d, E e) {
/*  99 */     return new Tuple5<A, B, C, D, E>(a, b, c, d, e);
/*     */   }
/*     */   
/*     */   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> t6(A a, B b, C c, D d, E e, F f) {
/* 103 */     return new Tuple6<A, B, C, D, E, F>(a, b, c, d, e, f);
/*     */   }
/*     */   
/*     */   public static boolean eq(Object a, Object b) {
/* 108 */     return (a == b || (a != null && a.equals(b)));
/*     */   }
/*     */   
/*     */   public static final class Tuple2<A, B> implements Comparable<Tuple2<A, B>>, Serializable {
/*     */     private static final long serialVersionUID = -8816277286657643283L;
/*     */     
/*     */     public final A a;
/*     */     
/*     */     public final B b;
/*     */     
/*     */     public Tuple2(A a, B b) {
/* 120 */       this.a = a;
/* 121 */       this.b = b;
/*     */     }
/*     */     
/*     */     protected Tuple2(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 126 */       objectStack.add(this);
/* 127 */       this.a = (A)serializer.deserialize(in, objectStack);
/* 128 */       this.b = (B)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compareTo(Tuple2<A, B> o) {
/* 133 */       return Fun.TUPLE2_COMPARATOR.compare(this, o);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 137 */       if (this == o)
/* 137 */         return true; 
/* 138 */       if (o == null || getClass() != o.getClass())
/* 138 */         return false; 
/* 140 */       Tuple2 t = (Tuple2)o;
/* 142 */       return (Fun.eq(this.a, t.a) && Fun.eq(this.b, t.b));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 146 */       int result = (this.a != null) ? this.a.hashCode() : 0;
/* 147 */       result = 31 * result + ((this.b != null) ? this.b.hashCode() : 0);
/* 148 */       return result;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 152 */       return "Tuple2[" + this.a + ", " + this.b + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple3<A, B, C> implements Comparable<Tuple3<A, B, C>>, Serializable {
/*     */     private static final long serialVersionUID = 11785034935947868L;
/*     */     
/*     */     public final A a;
/*     */     
/*     */     public final B b;
/*     */     
/*     */     public final C c;
/*     */     
/*     */     public Tuple3(A a, B b, C c) {
/* 165 */       this.a = a;
/* 166 */       this.b = b;
/* 167 */       this.c = c;
/*     */     }
/*     */     
/*     */     protected Tuple3(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack, int extra) throws IOException {
/* 172 */       objectStack.add(this);
/* 173 */       this.a = (A)serializer.deserialize(in, objectStack);
/* 174 */       this.b = (B)serializer.deserialize(in, objectStack);
/* 175 */       this.c = (C)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compareTo(Tuple3<A, B, C> o) {
/* 181 */       return Fun.TUPLE3_COMPARATOR.compare(this, o);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 186 */       return "Tuple3[" + this.a + ", " + this.b + ", " + this.c + "]";
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 191 */       if (this == o)
/* 191 */         return true; 
/* 192 */       if (o == null || getClass() != o.getClass())
/* 192 */         return false; 
/* 194 */       Tuple3 t = (Tuple3)o;
/* 195 */       return (Fun.eq(this.a, t.a) && Fun.eq(this.b, t.b) && Fun.eq(this.c, t.c));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 200 */       int result = (this.a != null) ? this.a.hashCode() : 0;
/* 201 */       result = 31 * result + ((this.b != null) ? this.b.hashCode() : 0);
/* 202 */       result = 31 * result + ((this.c != null) ? this.c.hashCode() : 0);
/* 203 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple4<A, B, C, D> implements Comparable<Tuple4<A, B, C, D>>, Serializable {
/*     */     private static final long serialVersionUID = 1630397500758650718L;
/*     */     
/*     */     public final A a;
/*     */     
/*     */     public final B b;
/*     */     
/*     */     public final C c;
/*     */     
/*     */     public final D d;
/*     */     
/*     */     public Tuple4(A a, B b, C c, D d) {
/* 217 */       this.a = a;
/* 218 */       this.b = b;
/* 219 */       this.c = c;
/* 220 */       this.d = d;
/*     */     }
/*     */     
/*     */     protected Tuple4(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 225 */       objectStack.add(this);
/* 226 */       this.a = (A)serializer.deserialize(in, objectStack);
/* 227 */       this.b = (B)serializer.deserialize(in, objectStack);
/* 228 */       this.c = (C)serializer.deserialize(in, objectStack);
/* 229 */       this.d = (D)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compareTo(Tuple4<A, B, C, D> o) {
/* 234 */       return Fun.TUPLE4_COMPARATOR.compare(this, o);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 239 */       return "Tuple4[" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + "]";
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 244 */       if (this == o)
/* 244 */         return true; 
/* 245 */       if (o == null || getClass() != o.getClass())
/* 245 */         return false; 
/* 247 */       Tuple4 t = (Tuple4)o;
/* 249 */       return (Fun.eq(this.a, t.a) && Fun.eq(this.b, t.b) && Fun.eq(this.c, t.c) && Fun.eq(this.d, t.d));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 254 */       int result = (this.a != null) ? this.a.hashCode() : 0;
/* 255 */       result = 31 * result + ((this.b != null) ? this.b.hashCode() : 0);
/* 256 */       result = 31 * result + ((this.c != null) ? this.c.hashCode() : 0);
/* 257 */       result = 31 * result + ((this.d != null) ? this.d.hashCode() : 0);
/* 258 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple5<A, B, C, D, E> implements Comparable<Tuple5<A, B, C, D, E>>, Serializable {
/*     */     private static final long serialVersionUID = 3975016300758650718L;
/*     */     
/*     */     public final A a;
/*     */     
/*     */     public final B b;
/*     */     
/*     */     public final C c;
/*     */     
/*     */     public final D d;
/*     */     
/*     */     public final E e;
/*     */     
/*     */     public Tuple5(A a, B b, C c, D d, E e) {
/* 274 */       this.a = a;
/* 275 */       this.b = b;
/* 276 */       this.c = c;
/* 277 */       this.d = d;
/* 278 */       this.e = e;
/*     */     }
/*     */     
/*     */     protected Tuple5(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 285 */       objectStack.add(this);
/* 286 */       this.a = (A)serializer.deserialize(in, objectStack);
/* 287 */       this.b = (B)serializer.deserialize(in, objectStack);
/* 288 */       this.c = (C)serializer.deserialize(in, objectStack);
/* 289 */       this.d = (D)serializer.deserialize(in, objectStack);
/* 290 */       this.e = (E)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compareTo(Tuple5<A, B, C, D, E> o) {
/* 295 */       return Fun.TUPLE5_COMPARATOR.compare(this, o);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 301 */       return "Tuple5[" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ", " + this.e + "]";
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 306 */       if (this == o)
/* 306 */         return true; 
/* 307 */       if (o == null || getClass() != o.getClass())
/* 307 */         return false; 
/* 309 */       Tuple5 t = (Tuple5)o;
/* 311 */       return (Fun.eq(this.a, t.a) && Fun.eq(this.b, t.b) && Fun.eq(this.c, t.c) && Fun.eq(this.d, t.d) && Fun.eq(this.e, t.e));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 316 */       int result = (this.a != null) ? this.a.hashCode() : 0;
/* 317 */       result = 31 * result + ((this.b != null) ? this.b.hashCode() : 0);
/* 318 */       result = 31 * result + ((this.c != null) ? this.c.hashCode() : 0);
/* 319 */       result = 31 * result + ((this.d != null) ? this.d.hashCode() : 0);
/* 320 */       result = 31 * result + ((this.e != null) ? this.e.hashCode() : 0);
/* 321 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple6<A, B, C, D, E, F> implements Comparable<Tuple6<A, B, C, D, E, F>>, Serializable {
/*     */     private static final long serialVersionUID = 7500397586163050718L;
/*     */     
/*     */     public final A a;
/*     */     
/*     */     public final B b;
/*     */     
/*     */     public final C c;
/*     */     
/*     */     public final D d;
/*     */     
/*     */     public final E e;
/*     */     
/*     */     public final F f;
/*     */     
/*     */     public Tuple6(A a, B b, C c, D d, E e, F f) {
/* 338 */       this.a = a;
/* 339 */       this.b = b;
/* 340 */       this.c = c;
/* 341 */       this.d = d;
/* 342 */       this.e = e;
/* 343 */       this.f = f;
/*     */     }
/*     */     
/*     */     protected Tuple6(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 350 */       objectStack.add(this);
/* 351 */       this.a = (A)serializer.deserialize(in, objectStack);
/* 352 */       this.b = (B)serializer.deserialize(in, objectStack);
/* 353 */       this.c = (C)serializer.deserialize(in, objectStack);
/* 354 */       this.d = (D)serializer.deserialize(in, objectStack);
/* 355 */       this.e = (E)serializer.deserialize(in, objectStack);
/* 356 */       this.f = (F)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compareTo(Tuple6<A, B, C, D, E, F> o) {
/* 361 */       return Fun.TUPLE6_COMPARATOR.compare(this, o);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 367 */       return "Tuple6[" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ", " + this.e + ", " + this.f + "]";
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 372 */       if (this == o)
/* 372 */         return true; 
/* 373 */       if (o == null || getClass() != o.getClass())
/* 373 */         return false; 
/* 375 */       Tuple6 t = (Tuple6)o;
/* 377 */       return (Fun.eq(this.a, t.a) && Fun.eq(this.b, t.b) && Fun.eq(this.c, t.c) && Fun.eq(this.d, t.d) && Fun.eq(this.e, t.e) && Fun.eq(this.f, t.f));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 382 */       int result = (this.a != null) ? this.a.hashCode() : 0;
/* 383 */       result = 31 * result + ((this.b != null) ? this.b.hashCode() : 0);
/* 384 */       result = 31 * result + ((this.c != null) ? this.c.hashCode() : 0);
/* 385 */       result = 31 * result + ((this.d != null) ? this.d.hashCode() : 0);
/* 386 */       result = 31 * result + ((this.e != null) ? this.e.hashCode() : 0);
/* 387 */       result = 31 * result + ((this.f != null) ? this.f.hashCode() : 0);
/* 388 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple2Comparator<A, B> implements Comparator<Tuple2<A, B>>, Serializable {
/*     */     private static final long serialVersionUID = 1156568632023474010L;
/*     */     
/*     */     protected final Comparator<A> a;
/*     */     
/*     */     protected final Comparator<B> b;
/*     */     
/*     */     public Tuple2Comparator(Comparator<A> a, Comparator<B> b) {
/* 400 */       this.a = (a == null) ? Fun.COMPARATOR : a;
/* 401 */       this.b = (b == null) ? Fun.COMPARATOR : b;
/*     */     }
/*     */     
/*     */     protected Tuple2Comparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 407 */       objectStack.add(this);
/* 408 */       this.a = (Comparator<A>)serializer.deserialize(in, objectStack);
/* 409 */       this.b = (Comparator<B>)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Fun.Tuple2<A, B> o1, Fun.Tuple2<A, B> o2) {
/* 414 */       int i = this.a.compare(o1.a, o2.a);
/* 415 */       if (i != 0)
/* 415 */         return i; 
/* 416 */       i = this.b.compare(o1.b, o2.b);
/* 417 */       return i;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 422 */       if (this == o)
/* 422 */         return true; 
/* 423 */       if (o == null || getClass() != o.getClass())
/* 423 */         return false; 
/* 425 */       Tuple2Comparator that = (Tuple2Comparator)o;
/* 427 */       return (this.a.equals(that.a) && this.b.equals(that.b));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 432 */       int result = this.a.hashCode();
/* 433 */       result = 31 * result + this.b.hashCode();
/* 434 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple3Comparator<A, B, C> implements Comparator<Tuple3<A, B, C>>, Serializable {
/*     */     private static final long serialVersionUID = 6908945189367914695L;
/*     */     
/*     */     protected final Comparator<A> a;
/*     */     
/*     */     protected final Comparator<B> b;
/*     */     
/*     */     protected final Comparator<C> c;
/*     */     
/*     */     public Tuple3Comparator(Comparator<A> a, Comparator<B> b, Comparator<C> c) {
/* 447 */       this.a = (a == null) ? Fun.COMPARATOR : a;
/* 448 */       this.b = (b == null) ? Fun.COMPARATOR : b;
/* 449 */       this.c = (c == null) ? Fun.COMPARATOR : c;
/*     */     }
/*     */     
/*     */     protected Tuple3Comparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack, int extra) throws IOException {
/* 454 */       objectStack.add(this);
/* 455 */       this.a = (Comparator<A>)serializer.deserialize(in, objectStack);
/* 456 */       this.b = (Comparator<B>)serializer.deserialize(in, objectStack);
/* 457 */       this.c = (Comparator<C>)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Fun.Tuple3<A, B, C> o1, Fun.Tuple3<A, B, C> o2) {
/* 462 */       int i = this.a.compare(o1.a, o2.a);
/* 463 */       if (i != 0)
/* 463 */         return i; 
/* 464 */       i = this.b.compare(o1.b, o2.b);
/* 465 */       if (i != 0)
/* 465 */         return i; 
/* 466 */       return this.c.compare(o1.c, o2.c);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 471 */       if (this == o)
/* 471 */         return true; 
/* 472 */       if (o == null || getClass() != o.getClass())
/* 472 */         return false; 
/* 474 */       Tuple3Comparator that = (Tuple3Comparator)o;
/* 475 */       return (this.a.equals(that.a) && this.b.equals(that.b) && this.c.equals(that.c));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 480 */       int result = this.a.hashCode();
/* 481 */       result = 31 * result + this.b.hashCode();
/* 482 */       result = 31 * result + this.c.hashCode();
/* 483 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple4Comparator<A, B, C, D> implements Comparator<Tuple4<A, B, C, D>>, Serializable {
/*     */     private static final long serialVersionUID = 4994247318830102213L;
/*     */     
/*     */     protected final Comparator<A> a;
/*     */     
/*     */     protected final Comparator<B> b;
/*     */     
/*     */     protected final Comparator<C> c;
/*     */     
/*     */     protected final Comparator<D> d;
/*     */     
/*     */     public Tuple4Comparator(Comparator<A> a, Comparator<B> b, Comparator<C> c, Comparator<D> d) {
/* 497 */       this.a = (a == null) ? Fun.COMPARATOR : a;
/* 498 */       this.b = (b == null) ? Fun.COMPARATOR : b;
/* 499 */       this.c = (c == null) ? Fun.COMPARATOR : c;
/* 500 */       this.d = (d == null) ? Fun.COMPARATOR : d;
/*     */     }
/*     */     
/*     */     protected Tuple4Comparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 505 */       objectStack.add(this);
/* 506 */       this.a = (Comparator<A>)serializer.deserialize(in, objectStack);
/* 507 */       this.b = (Comparator<B>)serializer.deserialize(in, objectStack);
/* 508 */       this.c = (Comparator<C>)serializer.deserialize(in, objectStack);
/* 509 */       this.d = (Comparator<D>)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Fun.Tuple4<A, B, C, D> o1, Fun.Tuple4<A, B, C, D> o2) {
/* 514 */       int i = this.a.compare(o1.a, o2.a);
/* 515 */       if (i != 0)
/* 515 */         return i; 
/* 516 */       i = this.b.compare(o1.b, o2.b);
/* 517 */       if (i != 0)
/* 517 */         return i; 
/* 518 */       i = this.c.compare(o1.c, o2.c);
/* 519 */       if (i != 0)
/* 519 */         return i; 
/* 520 */       return this.d.compare(o1.d, o2.d);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 525 */       if (this == o)
/* 525 */         return true; 
/* 526 */       if (o == null || getClass() != o.getClass())
/* 526 */         return false; 
/* 528 */       Tuple4Comparator that = (Tuple4Comparator)o;
/* 529 */       return (this.a.equals(that.a) && this.b.equals(that.b) && this.c.equals(that.c) && this.d.equals(that.d));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 534 */       int result = this.a.hashCode();
/* 535 */       result = 31 * result + this.b.hashCode();
/* 536 */       result = 31 * result + this.c.hashCode();
/* 537 */       result = 31 * result + this.d.hashCode();
/* 538 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple5Comparator<A, B, C, D, E> implements Comparator<Tuple5<A, B, C, D, E>>, Serializable {
/*     */     private static final long serialVersionUID = -6571610438255691118L;
/*     */     
/*     */     protected final Comparator<A> a;
/*     */     
/*     */     protected final Comparator<B> b;
/*     */     
/*     */     protected final Comparator<C> c;
/*     */     
/*     */     protected final Comparator<D> d;
/*     */     
/*     */     protected final Comparator<E> e;
/*     */     
/*     */     public Tuple5Comparator(Comparator<A> a, Comparator<B> b, Comparator<C> c, Comparator<D> d, Comparator<E> e) {
/* 555 */       this.a = (a == null) ? Fun.COMPARATOR : a;
/* 556 */       this.b = (b == null) ? Fun.COMPARATOR : b;
/* 557 */       this.c = (c == null) ? Fun.COMPARATOR : c;
/* 558 */       this.d = (d == null) ? Fun.COMPARATOR : d;
/* 559 */       this.e = (e == null) ? Fun.COMPARATOR : e;
/*     */     }
/*     */     
/*     */     protected Tuple5Comparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 566 */       objectStack.add(this);
/* 567 */       this.a = (Comparator<A>)serializer.deserialize(in, objectStack);
/* 568 */       this.b = (Comparator<B>)serializer.deserialize(in, objectStack);
/* 569 */       this.c = (Comparator<C>)serializer.deserialize(in, objectStack);
/* 570 */       this.d = (Comparator<D>)serializer.deserialize(in, objectStack);
/* 571 */       this.e = (Comparator<E>)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Fun.Tuple5<A, B, C, D, E> o1, Fun.Tuple5<A, B, C, D, E> o2) {
/* 576 */       int i = this.a.compare(o1.a, o2.a);
/* 577 */       if (i != 0)
/* 577 */         return i; 
/* 578 */       i = this.b.compare(o1.b, o2.b);
/* 579 */       if (i != 0)
/* 579 */         return i; 
/* 580 */       i = this.c.compare(o1.c, o2.c);
/* 581 */       if (i != 0)
/* 581 */         return i; 
/* 582 */       i = this.d.compare(o1.d, o2.d);
/* 583 */       if (i != 0)
/* 583 */         return i; 
/* 584 */       return this.e.compare(o1.e, o2.e);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 589 */       if (this == o)
/* 589 */         return true; 
/* 590 */       if (o == null || getClass() != o.getClass())
/* 590 */         return false; 
/* 592 */       Tuple5Comparator that = (Tuple5Comparator)o;
/* 593 */       return (this.a.equals(that.a) && this.b.equals(that.b) && this.c.equals(that.c) && this.d.equals(that.d) && this.e.equals(that.e));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 598 */       int result = this.a.hashCode();
/* 599 */       result = 31 * result + this.b.hashCode();
/* 600 */       result = 31 * result + this.c.hashCode();
/* 601 */       result = 31 * result + this.d.hashCode();
/* 602 */       result = 31 * result + this.e.hashCode();
/* 603 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Tuple6Comparator<A, B, C, D, E, F> implements Comparator<Tuple6<A, B, C, D, E, F>>, Serializable {
/*     */     private static final long serialVersionUID = 4254578670751190479L;
/*     */     
/*     */     protected final Comparator<A> a;
/*     */     
/*     */     protected final Comparator<B> b;
/*     */     
/*     */     protected final Comparator<C> c;
/*     */     
/*     */     protected final Comparator<D> d;
/*     */     
/*     */     protected final Comparator<E> e;
/*     */     
/*     */     protected final Comparator<F> f;
/*     */     
/*     */     public Tuple6Comparator(Comparator<A> a, Comparator<B> b, Comparator<C> c, Comparator<D> d, Comparator<E> e, Comparator<F> f) {
/* 620 */       this.a = (a == null) ? Fun.COMPARATOR : a;
/* 621 */       this.b = (b == null) ? Fun.COMPARATOR : b;
/* 622 */       this.c = (c == null) ? Fun.COMPARATOR : c;
/* 623 */       this.d = (d == null) ? Fun.COMPARATOR : d;
/* 624 */       this.e = (e == null) ? Fun.COMPARATOR : e;
/* 625 */       this.f = (f == null) ? Fun.COMPARATOR : f;
/*     */     }
/*     */     
/*     */     protected Tuple6Comparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 632 */       objectStack.add(this);
/* 633 */       this.a = (Comparator<A>)serializer.deserialize(in, objectStack);
/* 634 */       this.b = (Comparator<B>)serializer.deserialize(in, objectStack);
/* 635 */       this.c = (Comparator<C>)serializer.deserialize(in, objectStack);
/* 636 */       this.d = (Comparator<D>)serializer.deserialize(in, objectStack);
/* 637 */       this.e = (Comparator<E>)serializer.deserialize(in, objectStack);
/* 638 */       this.f = (Comparator<F>)serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Fun.Tuple6<A, B, C, D, E, F> o1, Fun.Tuple6<A, B, C, D, E, F> o2) {
/* 643 */       int i = this.a.compare(o1.a, o2.a);
/* 644 */       if (i != 0)
/* 644 */         return i; 
/* 645 */       i = this.b.compare(o1.b, o2.b);
/* 646 */       if (i != 0)
/* 646 */         return i; 
/* 647 */       i = this.c.compare(o1.c, o2.c);
/* 648 */       if (i != 0)
/* 648 */         return i; 
/* 649 */       i = this.d.compare(o1.d, o2.d);
/* 650 */       if (i != 0)
/* 650 */         return i; 
/* 651 */       i = this.e.compare(o1.e, o2.e);
/* 652 */       if (i != 0)
/* 652 */         return i; 
/* 653 */       return this.f.compare(o1.f, o2.f);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 658 */       if (this == o)
/* 658 */         return true; 
/* 659 */       if (o == null || getClass() != o.getClass())
/* 659 */         return false; 
/* 661 */       Tuple6Comparator that = (Tuple6Comparator)o;
/* 662 */       return (this.a.equals(that.a) && this.b.equals(that.b) && this.c.equals(that.c) && this.d.equals(that.d) && this.e.equals(that.e) && this.f.equals(that.f));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 667 */       int result = this.a.hashCode();
/* 668 */       result = 31 * result + this.b.hashCode();
/* 669 */       result = 31 * result + this.c.hashCode();
/* 670 */       result = 31 * result + this.d.hashCode();
/* 671 */       result = 31 * result + this.e.hashCode();
/* 672 */       result = 31 * result + this.f.hashCode();
/* 673 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public static <K, V> Function1<K, Tuple2<K, V>> extractKey() {
/* 692 */     return new Function1<K, Tuple2<K, V>>() {
/*     */         public K run(Fun.Tuple2<K, V> t) {
/* 695 */           return (K)t.a;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <K, V> Function1<V, Tuple2<K, V>> extractValue() {
/* 701 */     return new Function1<V, Tuple2<K, V>>() {
/*     */         public V run(Fun.Tuple2<K, V> t) {
/* 704 */           return (V)t.b;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <K> Function1<K, K> extractNoTransform() {
/* 711 */     return new Function1<K, K>() {
/*     */         public K run(K k) {
/* 714 */           return k;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/* 720 */   public static final Comparator<byte[]> BYTE_ARRAY_COMPARATOR = new Comparator<byte[]>() {
/*     */       public int compare(byte[] o1, byte[] o2) {
/* 723 */         if (o1 == o2)
/* 723 */           return 0; 
/* 724 */         int len = Math.min(o1.length, o2.length);
/* 725 */         for (int i = 0; i < len; ) {
/* 726 */           if (o1[i] == o2[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 728 */           if (o1[i] > o2[i])
/* 729 */             return 1; 
/* 730 */           return -1;
/*     */         } 
/* 732 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/* 737 */   public static final Comparator<char[]> CHAR_ARRAY_COMPARATOR = new Comparator<char[]>() {
/*     */       public int compare(char[] o1, char[] o2) {
/* 740 */         if (o1 == o2)
/* 740 */           return 0; 
/* 741 */         int len = Math.min(o1.length, o2.length);
/* 742 */         for (int i = 0; i < len; ) {
/* 743 */           if (o1[i] == o2[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 745 */           if (o1[i] > o2[i])
/* 746 */             return 1; 
/* 747 */           return -1;
/*     */         } 
/* 749 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/* 753 */   public static final Comparator<int[]> INT_ARRAY_COMPARATOR = new Comparator<int[]>() {
/*     */       public int compare(int[] o1, int[] o2) {
/* 756 */         if (o1 == o2)
/* 756 */           return 0; 
/* 757 */         int len = Math.min(o1.length, o2.length);
/* 758 */         for (int i = 0; i < len; ) {
/* 759 */           if (o1[i] == o2[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 761 */           if (o1[i] > o2[i])
/* 762 */             return 1; 
/* 763 */           return -1;
/*     */         } 
/* 765 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/* 769 */   public static final Comparator<long[]> LONG_ARRAY_COMPARATOR = new Comparator<long[]>() {
/*     */       public int compare(long[] o1, long[] o2) {
/* 772 */         if (o1 == o2)
/* 772 */           return 0; 
/* 773 */         int len = Math.min(o1.length, o2.length);
/* 774 */         for (int i = 0; i < len; ) {
/* 775 */           if (o1[i] == o2[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 777 */           if (o1[i] > o2[i])
/* 778 */             return 1; 
/* 779 */           return -1;
/*     */         } 
/* 781 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/* 785 */   public static final Comparator<double[]> DOUBLE_ARRAY_COMPARATOR = new Comparator<double[]>() {
/*     */       public int compare(double[] o1, double[] o2) {
/* 788 */         if (o1 == o2)
/* 788 */           return 0; 
/* 789 */         int len = Math.min(o1.length, o2.length);
/* 790 */         for (int i = 0; i < len; ) {
/* 791 */           if (o1[i] == o2[i]) {
/*     */             i++;
/*     */             continue;
/*     */           } 
/* 793 */           if (o1[i] > o2[i])
/* 794 */             return 1; 
/* 795 */           return -1;
/*     */         } 
/* 797 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/* 803 */   public static final Comparator<Object[]> COMPARABLE_ARRAY_COMPARATOR = new Comparator<Object[]>() {
/*     */       public int compare(Object[] o1, Object[] o2) {
/* 806 */         if (o1 == o2)
/* 806 */           return 0; 
/* 807 */         int len = Math.min(o1.length, o2.length);
/* 808 */         for (int i = 0; i < len; i++) {
/* 809 */           int r = Fun.COMPARATOR.compare(o1[i], o2[i]);
/* 810 */           if (r != 0)
/* 811 */             return r; 
/*     */         } 
/* 813 */         return Fun.intCompare(o1.length, o2.length);
/*     */       }
/*     */     };
/*     */   
/*     */   public static final class ArrayComparator implements Comparator<Object[]> {
/*     */     protected final Comparator[] comparators;
/*     */     
/*     */     public ArrayComparator(Comparator<?>[] comparators2) {
/* 822 */       this.comparators = (Comparator[])comparators2.clone();
/* 823 */       for (int i = 0; i < this.comparators.length; i++) {
/* 824 */         if (this.comparators[i] == null)
/* 825 */           this.comparators[i] = Fun.COMPARATOR; 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected ArrayComparator(SerializerBase serializer, DataInput in, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 831 */       objectStack.add(this);
/* 832 */       this.comparators = (Comparator[])serializer.deserialize(in, objectStack);
/*     */     }
/*     */     
/*     */     public int compare(Object[] o1, Object[] o2) {
/* 838 */       if (o1 == o2)
/* 838 */         return 0; 
/* 839 */       int len = Math.min(o1.length, o2.length);
/* 840 */       for (int i = 0; i < len; i++) {
/* 841 */         int r = this.comparators[i].compare(o1[i], o2[i]);
/* 842 */         if (r != 0)
/* 843 */           return r; 
/*     */       } 
/* 845 */       return Fun.intCompare(o1.length, o2.length);
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 850 */       if (this == o)
/* 850 */         return true; 
/* 851 */       if (o == null || getClass() != o.getClass())
/* 851 */         return false; 
/* 853 */       ArrayComparator that = (ArrayComparator)o;
/* 854 */       return Arrays.equals((Object[])this.comparators, (Object[])that.comparators);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 859 */       return Arrays.hashCode((Object[])this.comparators);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int intCompare(int x, int y) {
/* 864 */     return (x < y) ? -1 : ((x == y) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public static <K2, K1> Iterable<K1> filter(NavigableSet<Tuple2<K2, K1>> secondaryKeys, K2 secondaryKey) {
/* 879 */     return filter(secondaryKeys, secondaryKey, true, secondaryKey, true);
/*     */   }
/*     */   
/*     */   public static <K2, K1> Iterable<K1> filter(final NavigableSet<Tuple2<K2, K1>> secondaryKeys, final K2 lo, final boolean loInc, final K2 hi, final boolean hiInc) {
/* 883 */     return new Iterable<K1>() {
/*     */         public Iterator<K1> iterator() {
/* 887 */           final Iterator<Fun.Tuple2<K2, K1>> iter = secondaryKeys.subSet(Fun.t2((K2)lo, null), loInc, Fun.t2((K2)hi, hiInc ? (K1)Fun.HI : null), hiInc).iterator();
/* 894 */           return new Iterator() {
/*     */               public boolean hasNext() {
/* 897 */                 return iter.hasNext();
/*     */               }
/*     */               
/*     */               public K1 next() {
/* 902 */                 return (K1)((Fun.Tuple2)iter.next()).b;
/*     */               }
/*     */               
/*     */               public void remove() {
/* 907 */                 iter.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <A, B, C> Iterable<C> filter(final NavigableSet<Tuple3<A, B, C>> secondaryKeys, final A a, final B b) {
/* 918 */     return new Iterable<C>() {
/*     */         public Iterator<C> iterator() {
/* 922 */           final Iterator<Fun.Tuple3> iter = secondaryKeys.subSet(Fun.t3(a, b, null), Fun.t3(a, (b == null) ? Fun.HI() : b, Fun.HI())).iterator();
/* 929 */           return new Iterator() {
/*     */               public boolean hasNext() {
/* 932 */                 return iter.hasNext();
/*     */               }
/*     */               
/*     */               public C next() {
/* 937 */                 return ((Fun.Tuple3)iter.next()).c;
/*     */               }
/*     */               
/*     */               public void remove() {
/* 942 */                 iter.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <A, B, C, D> Iterable<D> filter(final NavigableSet<Tuple4<A, B, C, D>> secondaryKeys, final A a, final B b, final C c) {
/* 951 */     return new Iterable<D>() {
/*     */         public Iterator<D> iterator() {
/* 955 */           final Iterator<Fun.Tuple4> iter = secondaryKeys.subSet(Fun.t4(a, b, c, null), Fun.t4(a, (b == null) ? Fun.HI() : b, (c == null) ? Fun.HI() : c, Fun.HI())).iterator();
/* 962 */           return new Iterator() {
/*     */               public boolean hasNext() {
/* 965 */                 return iter.hasNext();
/*     */               }
/*     */               
/*     */               public D next() {
/* 970 */                 return ((Fun.Tuple4)iter.next()).d;
/*     */               }
/*     */               
/*     */               public void remove() {
/* 975 */                 iter.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static interface Function2<R, A, B> {
/*     */     R run(A param1A, B param1B);
/*     */   }
/*     */   
/*     */   public static interface Function1<R, A> {
/*     */     R run(A param1A);
/*     */   }
/*     */   
/*     */   public static interface Function0<R> {
/*     */     R run();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Fun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */