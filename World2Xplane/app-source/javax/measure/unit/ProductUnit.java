/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public final class ProductUnit<Q extends Quantity> extends DerivedUnit<Q> {
/*     */   private final Element[] _elements;
/*     */   
/*     */   private int _hashCode;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   ProductUnit() {
/*  49 */     this._elements = new Element[0];
/*     */   }
/*     */   
/*     */   public ProductUnit(Unit<?> productUnit) {
/*  60 */     this._elements = ((ProductUnit)productUnit)._elements;
/*     */   }
/*     */   
/*     */   private ProductUnit(Element[] elements) {
/*  69 */     this._elements = elements;
/*     */   }
/*     */   
/*     */   private static Unit<? extends Quantity> getInstance(Element[] leftElems, Element[] rightElems) {
/*  84 */     Element[] result = new Element[leftElems.length + rightElems.length];
/*  85 */     int resultIndex = 0;
/*     */     int i;
/*  86 */     for (i = 0; i < leftElems.length; i++) {
/*  87 */       Unit unit = (leftElems[i])._unit;
/*  88 */       int p1 = (leftElems[i])._pow;
/*  89 */       int r1 = (leftElems[i])._root;
/*  90 */       int p2 = 0;
/*  91 */       int r2 = 1;
/*  92 */       for (int k = 0; k < rightElems.length; k++) {
/*  93 */         if (unit.equals((rightElems[k])._unit)) {
/*  94 */           p2 = (rightElems[k])._pow;
/*  95 */           r2 = (rightElems[k])._root;
/*     */           break;
/*     */         } 
/*     */       } 
/*  99 */       int pow = p1 * r2 + p2 * r1;
/* 100 */       int root = r1 * r2;
/* 101 */       if (pow != 0) {
/* 102 */         int gcd = gcd(Math.abs(pow), root);
/* 103 */         result[resultIndex++] = new Element(unit, pow / gcd, root / gcd);
/*     */       } 
/*     */     } 
/* 108 */     for (i = 0; i < rightElems.length; i++) {
/* 109 */       Unit unit = (rightElems[i])._unit;
/* 110 */       boolean hasBeenMerged = false;
/* 111 */       for (int k = 0; k < leftElems.length; k++) {
/* 112 */         if (unit.equals((leftElems[k])._unit)) {
/* 113 */           hasBeenMerged = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 117 */       if (!hasBeenMerged)
/* 118 */         result[resultIndex++] = rightElems[i]; 
/*     */     } 
/* 123 */     if (resultIndex == 0)
/* 124 */       return (Unit)ONE; 
/* 125 */     if (resultIndex == 1 && (result[0])._pow == (result[0])._root)
/* 126 */       return (Unit)(result[0])._unit; 
/* 128 */     Element[] elems = new Element[resultIndex];
/* 129 */     for (int j = 0; j < resultIndex; j++)
/* 130 */       elems[j] = result[j]; 
/* 132 */     return new ProductUnit<Quantity>(elems);
/*     */   }
/*     */   
/*     */   static Unit<? extends Quantity> getProductInstance(Unit<?> left, Unit<?> right) {
/*     */     Element[] leftElems;
/*     */     Element[] rightElems;
/* 145 */     if (left instanceof ProductUnit) {
/* 146 */       leftElems = ((ProductUnit)left)._elements;
/*     */     } else {
/* 148 */       leftElems = new Element[] { new Element(left, 1, 1) };
/*     */     } 
/* 151 */     if (right instanceof ProductUnit) {
/* 152 */       rightElems = ((ProductUnit)right)._elements;
/*     */     } else {
/* 154 */       rightElems = new Element[] { new Element(right, 1, 1) };
/*     */     } 
/* 156 */     return getInstance(leftElems, rightElems);
/*     */   }
/*     */   
/*     */   static Unit<? extends Quantity> getQuotientInstance(Unit<?> left, Unit<?> right) {
/*     */     Element[] leftElems;
/*     */     Element[] rightElems;
/* 168 */     if (left instanceof ProductUnit) {
/* 169 */       leftElems = ((ProductUnit)left)._elements;
/*     */     } else {
/* 171 */       leftElems = new Element[] { new Element(left, 1, 1) };
/*     */     } 
/* 174 */     if (right instanceof ProductUnit) {
/* 175 */       Element[] elems = ((ProductUnit)right)._elements;
/* 176 */       rightElems = new Element[elems.length];
/* 177 */       for (int i = 0; i < elems.length; i++)
/* 178 */         rightElems[i] = new Element((elems[i])._unit, -(elems[i])._pow, (elems[i])._root); 
/*     */     } else {
/* 182 */       rightElems = new Element[] { new Element(right, -1, 1) };
/*     */     } 
/* 184 */     return getInstance(leftElems, rightElems);
/*     */   }
/*     */   
/*     */   static Unit<? extends Quantity> getRootInstance(Unit<?> unit, int n) {
/*     */     Element[] unitElems;
/* 198 */     if (unit instanceof ProductUnit) {
/* 199 */       Element[] elems = ((ProductUnit)unit)._elements;
/* 200 */       unitElems = new Element[elems.length];
/* 201 */       for (int i = 0; i < elems.length; i++) {
/* 202 */         int gcd = gcd(Math.abs((elems[i])._pow), (elems[i])._root * n);
/* 203 */         unitElems[i] = new Element((elems[i])._unit, (elems[i])._pow / gcd, (elems[i])._root * n / gcd);
/*     */       } 
/*     */     } else {
/* 207 */       unitElems = new Element[] { new Element(unit, 1, n) };
/*     */     } 
/* 209 */     return getInstance(unitElems, new Element[0]);
/*     */   }
/*     */   
/*     */   static Unit<? extends Quantity> getPowInstance(Unit<?> unit, int n) {
/*     */     Element[] unitElems;
/* 222 */     if (unit instanceof ProductUnit) {
/* 223 */       Element[] elems = ((ProductUnit)unit)._elements;
/* 224 */       unitElems = new Element[elems.length];
/* 225 */       for (int i = 0; i < elems.length; i++) {
/* 226 */         int gcd = gcd(Math.abs((elems[i])._pow * n), (elems[i])._root);
/* 227 */         unitElems[i] = new Element((elems[i])._unit, (elems[i])._pow * n / gcd, (elems[i])._root / gcd);
/*     */       } 
/*     */     } else {
/* 231 */       unitElems = new Element[] { new Element(unit, n, 1) };
/*     */     } 
/* 233 */     return getInstance(unitElems, new Element[0]);
/*     */   }
/*     */   
/*     */   public int getUnitCount() {
/* 242 */     return this._elements.length;
/*     */   }
/*     */   
/*     */   public Unit<? extends Quantity> getUnit(int index) {
/* 255 */     return (Unit)this._elements[index].getUnit();
/*     */   }
/*     */   
/*     */   public int getUnitPow(int index) {
/* 267 */     return this._elements[index].getPow();
/*     */   }
/*     */   
/*     */   public int getUnitRoot(int index) {
/* 279 */     return this._elements[index].getRoot();
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 291 */     if (this == that)
/* 292 */       return true; 
/* 293 */     if (that instanceof ProductUnit) {
/* 296 */       Element[] elems = ((ProductUnit)that)._elements;
/* 297 */       if (this._elements.length == elems.length) {
/* 298 */         for (int i = 0; i < this._elements.length; i++) {
/* 299 */           boolean unitFound = false;
/* 300 */           for (int j = 0; j < elems.length; j++) {
/* 301 */             if ((this._elements[i])._unit.equals((elems[j])._unit)) {
/* 302 */               if ((this._elements[i])._pow != (elems[j])._pow || (this._elements[i])._root != (elems[j])._root)
/* 304 */                 return false; 
/* 306 */               unitFound = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 311 */           if (!unitFound)
/* 312 */             return false; 
/*     */         } 
/* 315 */         return true;
/*     */       } 
/*     */     } 
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 324 */     if (this._hashCode != 0)
/* 325 */       return this._hashCode; 
/* 326 */     int code = 0;
/* 327 */     for (int i = 0; i < this._elements.length; i++)
/* 328 */       code += (this._elements[i])._unit.hashCode() * ((this._elements[i])._pow * 3 - (this._elements[i])._root * 2); 
/* 331 */     this._hashCode = code;
/* 332 */     return code;
/*     */   }
/*     */   
/*     */   public Unit<? super Q> getStandardUnit() {
/* 338 */     if (hasOnlyStandardUnit())
/* 339 */       return this; 
/* 340 */     Unit<Dimensionless> systemUnit = ONE;
/* 341 */     for (int i = 0; i < this._elements.length; i++) {
/* 342 */       Unit<? extends Quantity> unit = (this._elements[i])._unit.getStandardUnit();
/* 343 */       unit = unit.pow((this._elements[i])._pow);
/* 344 */       unit = unit.root((this._elements[i])._root);
/* 345 */       systemUnit = (Unit)systemUnit.times(unit);
/*     */     } 
/* 347 */     return (Unit)systemUnit;
/*     */   }
/*     */   
/*     */   public UnitConverter toStandardUnit() {
/* 352 */     if (hasOnlyStandardUnit())
/* 353 */       return UnitConverter.IDENTITY; 
/* 354 */     UnitConverter converter = UnitConverter.IDENTITY;
/* 355 */     for (int i = 0; i < this._elements.length; i++) {
/* 356 */       UnitConverter cvtr = (this._elements[i])._unit.toStandardUnit();
/* 357 */       if (!cvtr.isLinear())
/* 358 */         throw new ConversionException((this._elements[i])._unit + " is non-linear, cannot convert"); 
/* 360 */       if ((this._elements[i])._root != 1)
/* 361 */         throw new ConversionException((this._elements[i])._unit + " holds a base unit with fractional exponent"); 
/* 363 */       int pow = (this._elements[i])._pow;
/* 364 */       if (pow < 0) {
/* 365 */         pow = -pow;
/* 366 */         cvtr = cvtr.inverse();
/*     */       } 
/* 368 */       for (int j = 0; j < pow; j++)
/* 369 */         converter = converter.concatenate(cvtr); 
/*     */     } 
/* 372 */     return converter;
/*     */   }
/*     */   
/*     */   private boolean hasOnlyStandardUnit() {
/* 382 */     for (int i = 0; i < this._elements.length; i++) {
/* 383 */       Unit<?> u = (this._elements[i])._unit;
/* 384 */       if (!u.isStandardUnit())
/* 385 */         return false; 
/*     */     } 
/* 387 */     return true;
/*     */   }
/*     */   
/*     */   private static int gcd(int m, int n) {
/* 398 */     if (n == 0)
/* 399 */       return m; 
/* 401 */     return gcd(n, m % n);
/*     */   }
/*     */   
/*     */   private static final class Element implements Serializable {
/*     */     private final Unit<?> _unit;
/*     */     
/*     */     private final int _pow;
/*     */     
/*     */     private final int _root;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private Element(Unit<?> unit, int pow, int root) {
/* 433 */       this._unit = unit;
/* 434 */       this._pow = pow;
/* 435 */       this._root = root;
/*     */     }
/*     */     
/*     */     public Unit<?> getUnit() {
/* 444 */       return this._unit;
/*     */     }
/*     */     
/*     */     public int getPow() {
/* 454 */       return this._pow;
/*     */     }
/*     */     
/*     */     public int getRoot() {
/* 464 */       return this._root;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\ProductUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */