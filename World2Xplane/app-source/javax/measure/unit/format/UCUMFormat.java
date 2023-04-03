/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.measure.converter.MultiplyConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.unit.AlternateUnit;
/*     */ import javax.measure.unit.AnnotatedUnit;
/*     */ import javax.measure.unit.BaseUnit;
/*     */ import javax.measure.unit.ProductUnit;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.TransformedUnit;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ 
/*     */ public abstract class UCUMFormat extends UnitFormat {
/*     */   final SymbolMap _symbolMap;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static UCUMFormat getPrintInstance() {
/*  74 */     return Print.DEFAULT;
/*     */   }
/*     */   
/*     */   public static UCUMFormat getPrintInstance(SymbolMap symbolMap) {
/*  79 */     return new Print(symbolMap);
/*     */   }
/*     */   
/*     */   public static UCUMFormat getCaseSensitiveInstance() {
/*  87 */     return Parsing.DEFAULT_CS;
/*     */   }
/*     */   
/*     */   public static UCUMFormat getCaseSensitiveInstance(SymbolMap symbolMap) {
/*  95 */     return new Parsing(symbolMap, true);
/*     */   }
/*     */   
/*     */   public static UCUMFormat getCaseInsensitiveInstance() {
/* 103 */     return Parsing.DEFAULT_CI;
/*     */   }
/*     */   
/*     */   public static UCUMFormat getCaseInsensitiveInstance(SymbolMap symbolMap) {
/* 111 */     return new Parsing(symbolMap, false);
/*     */   }
/*     */   
/*     */   UCUMFormat(SymbolMap symbolMap) {
/* 127 */     this._symbolMap = symbolMap;
/*     */   }
/*     */   
/*     */   public Appendable format(Unit<?> unit, Appendable appendable) throws IOException {
/* 136 */     CharSequence symbol, annotation = null;
/* 137 */     if (unit instanceof AnnotatedUnit) {
/* 138 */       unit = ((AnnotatedUnit)unit).getRealUnit();
/* 139 */       annotation = ((AnnotatedUnit)unit).getAnnotation();
/*     */     } 
/* 141 */     String mapSymbol = this._symbolMap.getSymbol(unit);
/* 142 */     if (mapSymbol != null) {
/* 143 */       symbol = mapSymbol;
/* 144 */     } else if (unit instanceof ProductUnit) {
/* 145 */       ProductUnit<?> productUnit = (ProductUnit)unit;
/* 146 */       StringBuffer app = new StringBuffer();
/* 147 */       for (int i = 0; i < productUnit.getUnitCount(); i++) {
/* 148 */         if (productUnit.getUnitRoot(i) != 1)
/* 149 */           throw new IllegalArgumentException("Unable to format units in UCUM (fractional powers not supported)"); 
/* 152 */         StringBuffer temp = new StringBuffer();
/* 153 */         temp = (StringBuffer)format(productUnit.getUnit(i), temp);
/* 154 */         if (temp.indexOf(".") >= 0 || temp.indexOf("/") >= 0) {
/* 155 */           temp.insert(0, '(');
/* 156 */           temp.append(')');
/*     */         } 
/* 158 */         int pow = productUnit.getUnitPow(i);
/* 159 */         if (i > 0) {
/* 160 */           if (pow >= 0) {
/* 161 */             app.append('.');
/* 162 */           } else if (i < productUnit.getUnitCount() - 1) {
/* 163 */             app.append('.');
/*     */           } else {
/* 165 */             app.append('/');
/* 166 */             pow = -pow;
/*     */           } 
/* 168 */         } else if (pow < 0) {
/* 169 */           app.append('/');
/* 170 */           pow = -pow;
/*     */         } 
/* 172 */         app.append(temp);
/* 173 */         if (pow != 1)
/* 174 */           app.append(Integer.toString(pow)); 
/*     */       } 
/* 177 */       symbol = app;
/* 178 */     } else if (unit instanceof TransformedUnit || unit.equals(SI.KILOGRAM)) {
/*     */       UnitConverter converter;
/*     */       boolean printSeparator;
/* 180 */       StringBuffer temp = new StringBuffer();
/* 183 */       if (unit.equals(SI.KILOGRAM)) {
/* 187 */         temp = format(UCUM.GRAM, temp, new FieldPosition(0));
/* 188 */         converter = Prefix.KILO.getConverter();
/* 189 */         printSeparator = true;
/*     */       } else {
/* 191 */         TransformedUnit<?> transformedUnit = (TransformedUnit)unit;
/* 192 */         Unit<?> parentUnits = transformedUnit.getParentUnit();
/* 193 */         converter = transformedUnit.toParentUnit();
/* 194 */         if (parentUnits.equals(SI.KILOGRAM)) {
/* 197 */           parentUnits = UCUM.GRAM;
/* 198 */           converter = converter.concatenate(Prefix.KILO.getConverter());
/*     */         } 
/* 201 */         temp = format(parentUnits, temp, new FieldPosition(0));
/* 202 */         printSeparator = !parentUnits.equals(Unit.ONE);
/*     */       } 
/* 204 */       formatConverter(converter, printSeparator, temp);
/* 205 */       symbol = temp;
/* 206 */     } else if (unit instanceof BaseUnit) {
/* 207 */       symbol = ((BaseUnit)unit).getSymbol();
/* 208 */     } else if (unit instanceof AlternateUnit) {
/* 209 */       symbol = ((AlternateUnit)unit).getSymbol();
/*     */     } else {
/* 211 */       throw new IllegalArgumentException("Cannot format the given Object as UCUM units (unsupported unit " + unit.getClass().getName() + "). " + "Custom units types should override the toString() method as the default implementation uses the UCUM format.");
/*     */     } 
/* 218 */     appendable.append(symbol);
/* 219 */     if (annotation != null && annotation.length() > 0)
/* 220 */       appendAnnotation(unit, symbol, annotation, appendable); 
/* 223 */     return appendable;
/*     */   }
/*     */   
/*     */   void appendAnnotation(Unit<?> unit, CharSequence symbol, CharSequence annotation, Appendable appendable) throws IOException {
/* 228 */     appendable.append('{');
/* 229 */     appendable.append(annotation);
/* 230 */     appendable.append('}');
/*     */   }
/*     */   
/*     */   void formatConverter(UnitConverter converter, boolean continued, StringBuffer buffer) {
/* 253 */     boolean unitIsExpression = (buffer.indexOf(".") >= 0 || buffer.indexOf("/") >= 0);
/* 255 */     Prefix prefix = this._symbolMap.getPrefix(converter);
/* 256 */     if (prefix != null && !unitIsExpression) {
/* 257 */       buffer.insert(0, this._symbolMap.getSymbol(prefix));
/* 258 */     } else if (converter != UnitConverter.IDENTITY) {
/* 260 */       if (converter instanceof MultiplyConverter) {
/* 261 */         if (unitIsExpression) {
/* 262 */           buffer.insert(0, '(');
/* 263 */           buffer.append(')');
/*     */         } 
/* 265 */         MultiplyConverter multiplyConverter = (MultiplyConverter)converter;
/* 266 */         double factor = multiplyConverter.getFactor();
/* 267 */         long lFactor = (long)factor;
/* 268 */         if (lFactor != factor || lFactor < -9007199254740992L || lFactor > 9007199254740992L)
/* 270 */           throw new IllegalArgumentException("Only integer factors are supported in UCUM"); 
/* 273 */         if (continued)
/* 274 */           buffer.append('.'); 
/* 276 */         buffer.append(lFactor);
/* 277 */       } else if (converter instanceof RationalConverter) {
/* 278 */         if (unitIsExpression) {
/* 279 */           buffer.insert(0, '(');
/* 280 */           buffer.append(')');
/*     */         } 
/* 282 */         RationalConverter rationalConverter = (RationalConverter)converter;
/* 283 */         if (!rationalConverter.getDividend().equals(BigInteger.ONE)) {
/* 284 */           if (continued)
/* 285 */             buffer.append('.'); 
/* 287 */           buffer.append(rationalConverter.getDividend());
/*     */         } 
/* 289 */         if (!rationalConverter.getDivisor().equals(BigInteger.ONE)) {
/* 290 */           buffer.append('/');
/* 291 */           buffer.append(rationalConverter.getDivisor());
/*     */         } 
/*     */       } else {
/* 294 */         throw new IllegalArgumentException("Unable to format units in UCUM (unsupported UnitConverter " + converter + ")");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class Print extends UCUMFormat {
/* 311 */     private static final SymbolMap PRINT_SYMBOLS = new SymbolMap(ResourceBundle.getBundle("javax.measure.unit.format.UCUM_Print"));
/*     */     
/* 314 */     private static final Print DEFAULT = new Print(PRINT_SYMBOLS);
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Print(SymbolMap symbols) {
/* 317 */       super(symbols);
/*     */     }
/*     */     
/*     */     public Unit<? extends Quantity> parse(CharSequence csq, ParsePosition pos) throws IllegalArgumentException {
/* 323 */       throw new UnsupportedOperationException("The print format is for pretty-printing of units only. Parsing is not supported.");
/*     */     }
/*     */     
/*     */     void appendAnnotation(Unit<?> unit, CharSequence symbol, CharSequence annotation, Appendable appendable) throws IOException {
/* 331 */       if (symbol != null && symbol.length() > 0) {
/* 332 */         appendable.append('(');
/* 333 */         appendable.append(annotation);
/* 334 */         appendable.append(')');
/*     */       } else {
/* 336 */         appendable.append(annotation);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Parsing extends UCUMFormat {
/* 351 */     private static final SymbolMap CASE_SENSITIVE_SYMBOLS = new SymbolMap(ResourceBundle.getBundle("javax.measure.unit.format.UCUM_CS"));
/*     */     
/* 353 */     private static final SymbolMap CASE_INSENSITIVE_SYMBOLS = new SymbolMap(ResourceBundle.getBundle("javax.measure.unit.format.UCUM_CI"));
/*     */     
/* 355 */     private static final Parsing DEFAULT_CS = new Parsing(CASE_SENSITIVE_SYMBOLS, true);
/*     */     
/* 357 */     private static final Parsing DEFAULT_CI = new Parsing(CASE_INSENSITIVE_SYMBOLS, false);
/*     */     
/*     */     private final boolean _caseSensitive;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Parsing(SymbolMap symbols, boolean caseSensitive) {
/* 362 */       super(symbols);
/* 363 */       this._caseSensitive = caseSensitive;
/*     */     }
/*     */     
/*     */     public Unit<? extends Quantity> parse(CharSequence csq, ParsePosition cursor) throws IllegalArgumentException {
/* 371 */       int start = cursor.getIndex();
/* 372 */       int end = csq.length();
/* 373 */       if (end <= start)
/* 374 */         return Unit.ONE; 
/* 375 */       String source = csq.subSequence(start, end).toString().trim();
/* 376 */       if (source.length() == 0)
/* 377 */         return Unit.ONE; 
/* 378 */       if (!this._caseSensitive)
/* 379 */         source = source.toUpperCase(); 
/* 381 */       UCUMParser parser = new UCUMParser(this._symbolMap, new ByteArrayInputStream(source.getBytes()));
/*     */       try {
/* 384 */         Unit<?> result = parser.parseUnit();
/* 385 */         cursor.setIndex(end);
/* 386 */         return (Unit)result;
/* 387 */       } catch (ParseException e) {
/* 388 */         if (e.currentToken != null) {
/* 389 */           cursor.setErrorIndex(start + e.currentToken.endColumn);
/*     */         } else {
/* 391 */           cursor.setErrorIndex(start);
/*     */         } 
/* 393 */         throw new IllegalArgumentException(e.getMessage());
/* 394 */       } catch (TokenMgrError e) {
/* 395 */         cursor.setErrorIndex(start);
/* 396 */         throw new IllegalArgumentException(e.getMessage());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UCUMFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */