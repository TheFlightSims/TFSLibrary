/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @Beta
/*     */ @GwtCompatible
/*     */ public abstract class Converter<A, B> implements Function<A, B> {
/*     */   private final boolean handleNullAutomatically;
/*     */   
/*     */   private transient Converter<B, A> reverse;
/*     */   
/*     */   protected Converter() {
/* 103 */     this(true);
/*     */   }
/*     */   
/*     */   Converter(boolean handleNullAutomatically) {
/* 110 */     this.handleNullAutomatically = handleNullAutomatically;
/*     */   }
/*     */   
/*     */   protected abstract B doForward(A paramA);
/*     */   
/*     */   protected abstract A doBackward(B paramB);
/*     */   
/*     */   @Nullable
/*     */   public final B convert(@Nullable A a) {
/* 147 */     return correctedDoForward(a);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   B correctedDoForward(@Nullable A a) {
/* 152 */     if (this.handleNullAutomatically)
/* 154 */       return (a == null) ? null : Preconditions.<B>checkNotNull(doForward(a)); 
/* 156 */     return doForward(a);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   A correctedDoBackward(@Nullable B b) {
/* 162 */     if (this.handleNullAutomatically)
/* 164 */       return (b == null) ? null : Preconditions.<A>checkNotNull(doBackward(b)); 
/* 166 */     return doBackward(b);
/*     */   }
/*     */   
/*     */   public Iterable<B> convertAll(final Iterable<? extends A> fromIterable) {
/* 179 */     Preconditions.checkNotNull(fromIterable, "fromIterable");
/* 180 */     return new Iterable<B>() {
/*     */         public Iterator<B> iterator() {
/* 182 */           return new Iterator<B>() {
/* 183 */               private final Iterator<? extends A> fromIterator = fromIterable.iterator();
/*     */               
/*     */               public boolean hasNext() {
/* 187 */                 return this.fromIterator.hasNext();
/*     */               }
/*     */               
/*     */               public B next() {
/* 192 */                 return (B)Converter.this.convert(this.fromIterator.next());
/*     */               }
/*     */               
/*     */               public void remove() {
/* 197 */                 this.fromIterator.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Converter<B, A> reverse() {
/* 212 */     Converter<B, A> result = this.reverse;
/* 213 */     return (result == null) ? (this.reverse = new ReverseConverter<A, B>(this)) : result;
/*     */   }
/*     */   
/*     */   private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
/*     */     final Converter<A, B> original;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     ReverseConverter(Converter<A, B> original) {
/* 221 */       this.original = original;
/*     */     }
/*     */     
/*     */     protected A doForward(B b) {
/* 233 */       throw new AssertionError();
/*     */     }
/*     */     
/*     */     protected B doBackward(A a) {
/* 238 */       throw new AssertionError();
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     A correctedDoForward(@Nullable B b) {
/* 244 */       return this.original.correctedDoBackward(b);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     B correctedDoBackward(@Nullable A a) {
/* 250 */       return this.original.correctedDoForward(a);
/*     */     }
/*     */     
/*     */     public Converter<A, B> reverse() {
/* 255 */       return this.original;
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object object) {
/* 260 */       if (object instanceof ReverseConverter) {
/* 261 */         ReverseConverter<?, ?> that = (ReverseConverter<?, ?>)object;
/* 262 */         return this.original.equals(that.original);
/*     */       } 
/* 264 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 269 */       return this.original.hashCode() ^ 0xFFFFFFFF;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 274 */       return this.original + ".reverse()";
/*     */     }
/*     */   }
/*     */   
/*     */   public <C> Converter<A, C> andThen(Converter<B, C> secondConverter) {
/* 288 */     return new ConverterComposition<A, B, C>(this, Preconditions.<Converter<B, C>>checkNotNull(secondConverter));
/*     */   }
/*     */   
/*     */   private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
/*     */     final Converter<A, B> first;
/*     */     
/*     */     final Converter<B, C> second;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     ConverterComposition(Converter<A, B> first, Converter<B, C> second) {
/* 297 */       this.first = first;
/* 298 */       this.second = second;
/*     */     }
/*     */     
/*     */     protected C doForward(A a) {
/* 310 */       throw new AssertionError();
/*     */     }
/*     */     
/*     */     protected A doBackward(C c) {
/* 315 */       throw new AssertionError();
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     C correctedDoForward(@Nullable A a) {
/* 321 */       return this.second.correctedDoForward(this.first.correctedDoForward(a));
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     A correctedDoBackward(@Nullable C c) {
/* 327 */       return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object object) {
/* 332 */       if (object instanceof ConverterComposition) {
/* 333 */         ConverterComposition<?, ?, ?> that = (ConverterComposition<?, ?, ?>)object;
/* 334 */         return (this.first.equals(that.first) && this.second.equals(that.second));
/*     */       } 
/* 337 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 342 */       return 31 * this.first.hashCode() + this.second.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 347 */       return this.first + ".andThen(" + this.second + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public final B apply(@Nullable A a) {
/* 360 */     return convert(a);
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object object) {
/* 376 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
/* 398 */     return new FunctionBasedConverter<A, B>(forwardFunction, backwardFunction);
/*     */   }
/*     */   
/*     */   private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
/*     */     private final Function<? super A, ? extends B> forwardFunction;
/*     */     
/*     */     private final Function<? super B, ? extends A> backwardFunction;
/*     */     
/*     */     private FunctionBasedConverter(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
/* 409 */       this.forwardFunction = Preconditions.<Function<? super A, ? extends B>>checkNotNull(forwardFunction);
/* 410 */       this.backwardFunction = Preconditions.<Function<? super B, ? extends A>>checkNotNull(backwardFunction);
/*     */     }
/*     */     
/*     */     protected B doForward(A a) {
/* 415 */       return this.forwardFunction.apply(a);
/*     */     }
/*     */     
/*     */     protected A doBackward(B b) {
/* 420 */       return this.backwardFunction.apply(b);
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object object) {
/* 425 */       if (object instanceof FunctionBasedConverter) {
/* 426 */         FunctionBasedConverter<?, ?> that = (FunctionBasedConverter<?, ?>)object;
/* 427 */         return (this.forwardFunction.equals(that.forwardFunction) && this.backwardFunction.equals(that.backwardFunction));
/*     */       } 
/* 430 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 435 */       return this.forwardFunction.hashCode() * 31 + this.backwardFunction.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 440 */       return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T> Converter<T, T> identity() {
/* 449 */     return IdentityConverter.INSTANCE;
/*     */   }
/*     */   
/*     */   private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
/* 457 */     static final IdentityConverter INSTANCE = new IdentityConverter();
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     protected T doForward(T t) {
/* 461 */       return t;
/*     */     }
/*     */     
/*     */     protected T doBackward(T t) {
/* 466 */       return t;
/*     */     }
/*     */     
/*     */     public IdentityConverter<T> reverse() {
/* 471 */       return this;
/*     */     }
/*     */     
/*     */     public <S> Converter<T, S> andThen(Converter<T, S> otherConverter) {
/* 476 */       return Preconditions.<Converter<T, S>>checkNotNull(otherConverter, "otherConverter");
/*     */     }
/*     */     
/*     */     public String toString() {
/* 486 */       return "Converter.identity()";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 490 */       return INSTANCE;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Converter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */