/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.HashMap;
/*     */ import javax.measure.converter.AddConverter;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.MultiplyConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public abstract class Unit<Q extends Quantity> implements Serializable {
/*  60 */   public static final Unit<Dimensionless> ONE = new ProductUnit<Dimensionless>();
/*     */   
/*  65 */   static final HashMap<String, Unit<?>> SYMBOL_TO_UNIT = new HashMap<String, Unit<?>>();
/*     */   
/*     */   public abstract Unit<? super Q> getStandardUnit();
/*     */   
/*     */   public abstract UnitConverter toStandardUnit();
/*     */   
/*     */   public abstract int hashCode();
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */   
/*     */   public boolean isStandardUnit() {
/* 130 */     return getStandardUnit().equals(this);
/*     */   }
/*     */   
/*     */   public final boolean isCompatible(Unit<?> that) {
/* 144 */     return (this == that || getStandardUnit().equals(that.getStandardUnit()) || getDimension().equals(that.getDimension()));
/*     */   }
/*     */   
/*     */   public final <T extends Quantity> Unit<T> asType(Class<T> type) throws ClassCastException {
/* 167 */     Dimension dim1 = getDimension();
/* 168 */     Unit<T> u = null;
/*     */     try {
/* 170 */       u = (Unit<T>)type.getField("UNIT").get(null);
/* 171 */     } catch (Exception e) {
/* 172 */       throw new Error(e);
/*     */     } 
/* 174 */     Dimension dim2 = u.getDimension();
/* 175 */     if (!dim1.equals(dim2))
/* 176 */       throw new ClassCastException(); 
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public final Dimension getDimension() {
/* 187 */     Unit<?> systemUnit = getStandardUnit();
/* 188 */     if (systemUnit instanceof BaseUnit)
/* 189 */       return Dimension.getModel().getDimension((BaseUnit)systemUnit); 
/* 190 */     if (systemUnit instanceof AlternateUnit)
/* 191 */       return ((AlternateUnit)systemUnit).getParent().getDimension(); 
/* 193 */     ProductUnit<?> productUnit = (ProductUnit)systemUnit;
/* 194 */     Dimension dimension = Dimension.NONE;
/* 195 */     for (int i = 0; i < productUnit.getUnitCount(); i++) {
/* 196 */       Unit<?> unit = productUnit.getUnit(i);
/* 197 */       Dimension d = unit.getDimension().pow(productUnit.getUnitPow(i)).root(productUnit.getUnitRoot(i));
/* 199 */       dimension = dimension.times(d);
/*     */     } 
/* 201 */     return dimension;
/*     */   }
/*     */   
/*     */   public final UnitConverter getConverterTo(Unit<?> that) throws ConversionException {
/* 214 */     if (equals(that))
/* 215 */       return UnitConverter.IDENTITY; 
/* 216 */     Unit<?> thisSystemUnit = getStandardUnit();
/* 217 */     Unit<?> thatSystemUnit = that.getStandardUnit();
/* 218 */     if (thisSystemUnit.equals(thatSystemUnit))
/* 219 */       return that.toStandardUnit().inverse().concatenate(toStandardUnit()); 
/* 222 */     if (!thisSystemUnit.getDimension().equals(thatSystemUnit.getDimension()))
/* 224 */       throw new ConversionException(this + " is not compatible with " + that); 
/* 227 */     UnitConverter thisTransform = toStandardUnit().concatenate(transformOf(getBaseUnits()));
/* 229 */     UnitConverter thatTransform = that.toStandardUnit().concatenate(transformOf(that.getBaseUnits()));
/* 231 */     return thatTransform.inverse().concatenate(thisTransform);
/*     */   }
/*     */   
/*     */   private Unit<?> getBaseUnits() {
/* 235 */     Unit<?> systemUnit = getStandardUnit();
/* 236 */     if (systemUnit instanceof BaseUnit)
/* 236 */       return systemUnit; 
/* 237 */     if (systemUnit instanceof AlternateUnit)
/* 238 */       return ((AlternateUnit)systemUnit).getParent().getBaseUnits(); 
/* 239 */     if (systemUnit instanceof ProductUnit) {
/* 240 */       ProductUnit<?> productUnit = (ProductUnit)systemUnit;
/* 241 */       Unit<?> baseUnits = ONE;
/* 242 */       for (int i = 0; i < productUnit.getUnitCount(); i++) {
/* 243 */         Unit<?> unit = productUnit.getUnit(i).getBaseUnits();
/* 244 */         unit = unit.pow(productUnit.getUnitPow(i));
/* 245 */         unit = unit.root(productUnit.getUnitRoot(i));
/* 246 */         baseUnits = baseUnits.times(unit);
/*     */       } 
/* 248 */       return baseUnits;
/*     */     } 
/* 250 */     throw new InternalError("System Unit cannot be an instance of " + getClass());
/*     */   }
/*     */   
/*     */   private static UnitConverter transformOf(Unit<?> baseUnits) {
/* 255 */     if (baseUnits instanceof BaseUnit)
/* 256 */       return Dimension.getModel().getTransform((BaseUnit)baseUnits); 
/* 258 */     ProductUnit<?> productUnit = (ProductUnit)baseUnits;
/* 259 */     UnitConverter converter = UnitConverter.IDENTITY;
/* 260 */     for (int i = 0; i < productUnit.getUnitCount(); i++) {
/* 261 */       Unit<?> unit = productUnit.getUnit(i);
/* 262 */       UnitConverter cvtr = transformOf(unit);
/* 263 */       if (!cvtr.isLinear())
/* 264 */         throw new ConversionException(baseUnits + " is non-linear, cannot convert"); 
/* 266 */       if (productUnit.getUnitRoot(i) != 1)
/* 267 */         throw new ConversionException(productUnit + " holds a base unit with fractional exponent"); 
/* 269 */       int pow = productUnit.getUnitPow(i);
/* 270 */       if (pow < 0) {
/* 271 */         pow = -pow;
/* 272 */         cvtr = cvtr.inverse();
/*     */       } 
/* 274 */       for (int j = 0; j < pow; j++)
/* 275 */         converter = converter.concatenate(cvtr); 
/*     */     } 
/* 278 */     return converter;
/*     */   }
/*     */   
/*     */   public final <A extends Quantity> AlternateUnit<A> alternate(String symbol) {
/* 299 */     return new AlternateUnit<A>(symbol, this);
/*     */   }
/*     */   
/*     */   public final CompoundUnit<Q> compound(Unit<Q> subunit) {
/* 315 */     return new CompoundUnit<Q>(this, subunit);
/*     */   }
/*     */   
/*     */   public final Unit<Q> transform(UnitConverter operation) {
/* 329 */     if (this instanceof TransformedUnit) {
/* 330 */       TransformedUnit<Q> tf = (TransformedUnit<Q>)this;
/* 331 */       Unit<Q> parent = tf.getParentUnit();
/* 332 */       UnitConverter toParent = tf.toParentUnit().concatenate(operation);
/* 333 */       if (toParent == UnitConverter.IDENTITY)
/* 334 */         return parent; 
/* 335 */       return new TransformedUnit<Q>(parent, toParent);
/*     */     } 
/* 337 */     if (operation == UnitConverter.IDENTITY)
/* 338 */       return this; 
/* 339 */     return new TransformedUnit<Q>(this, operation);
/*     */   }
/*     */   
/*     */   public final Unit<Q> plus(double offset) {
/* 351 */     return transform((UnitConverter)new AddConverter(offset));
/*     */   }
/*     */   
/*     */   public final Unit<Q> times(long factor) {
/* 362 */     return transform((UnitConverter)new RationalConverter(factor, 1L));
/*     */   }
/*     */   
/*     */   public final Unit<Q> times(double factor) {
/* 373 */     return transform((UnitConverter)new MultiplyConverter(factor));
/*     */   }
/*     */   
/*     */   public final Unit<? extends Quantity> times(Unit<?> that) {
/* 383 */     return ProductUnit.getProductInstance(this, that);
/*     */   }
/*     */   
/*     */   public final Unit<? extends Quantity> inverse() {
/* 392 */     return ProductUnit.getQuotientInstance(ONE, this);
/*     */   }
/*     */   
/*     */   public final Unit<Q> divide(long divisor) {
/* 403 */     return transform((UnitConverter)new RationalConverter(1L, divisor));
/*     */   }
/*     */   
/*     */   public final Unit<Q> divide(double divisor) {
/* 413 */     return transform((UnitConverter)new MultiplyConverter(1.0D / divisor));
/*     */   }
/*     */   
/*     */   public final Unit<? extends Quantity> divide(Unit<?> that) {
/* 423 */     return times(that.inverse());
/*     */   }
/*     */   
/*     */   public final Unit<? extends Quantity> root(int n) {
/* 434 */     if (n > 0)
/* 435 */       return ProductUnit.getRootInstance(this, n); 
/* 436 */     if (n == 0)
/* 437 */       throw new ArithmeticException("Root's order of zero"); 
/* 439 */     return ONE.divide(root(-n));
/*     */   }
/*     */   
/*     */   public final Unit<? extends Quantity> pow(int n) {
/* 450 */     if (n > 0)
/* 451 */       return times(pow(n - 1)); 
/* 452 */     if (n == 0)
/* 453 */       return (Unit)ONE; 
/* 455 */     return ONE.divide(pow(-n));
/*     */   }
/*     */   
/*     */   public static Unit<? extends Quantity> valueOf(CharSequence csq) {
/*     */     try {
/* 479 */       return UnitFormat.getInstance().parseProductUnit(csq, new ParsePosition(0));
/* 481 */     } catch (ParseException e) {
/* 482 */       throw new IllegalArgumentException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 499 */     return UnitFormat.getInstance().format(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\Unit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */