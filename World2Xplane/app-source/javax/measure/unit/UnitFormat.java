/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import javax.measure.converter.AddConverter;
/*     */ import javax.measure.converter.MultiplyConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public abstract class UnitFormat extends Format {
/*  48 */   private static final DefaultFormat DEFAULT = new DefaultFormat();
/*     */   
/*  53 */   private static final ASCIIFormat ASCII = new ASCIIFormat();
/*     */   
/*     */   public static UnitFormat getInstance() {
/*  63 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static UnitFormat getInstance(Locale inLocale) {
/*  72 */     return DEFAULT;
/*     */   }
/*     */   
/*     */   public static UnitFormat getUCUMInstance() {
/*  84 */     return ASCII;
/*     */   }
/*     */   
/*     */   public final StringBuffer format(Object unit, final StringBuffer toAppendTo, FieldPosition pos) {
/*     */     try {
/* 185 */       Object dest = toAppendTo;
/* 186 */       if (dest instanceof Appendable) {
/* 187 */         format((Unit)unit, (Appendable)dest);
/*     */       } else {
/* 189 */         format((Unit)unit, new Appendable() {
/*     */               public Appendable append(char arg0) throws IOException {
/* 192 */                 toAppendTo.append(arg0);
/* 193 */                 return null;
/*     */               }
/*     */               
/*     */               public Appendable append(CharSequence arg0) throws IOException {
/* 197 */                 toAppendTo.append(arg0);
/* 198 */                 return null;
/*     */               }
/*     */               
/*     */               public Appendable append(CharSequence arg0, int arg1, int arg2) throws IOException {
/* 202 */                 toAppendTo.append(arg0.subSequence(arg1, arg2));
/* 203 */                 return null;
/*     */               }
/*     */             });
/*     */       } 
/* 206 */       return toAppendTo;
/* 207 */     } catch (IOException e) {
/* 208 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Unit<?> parseObject(String source, ParsePosition pos) {
/* 222 */     int start = pos.getIndex();
/*     */     try {
/* 224 */       return parseProductUnit(source, pos);
/* 225 */     } catch (ParseException e) {
/* 226 */       pos.setIndex(start);
/* 227 */       pos.setErrorIndex(e.getErrorOffset());
/* 228 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class Exponent {
/*     */     public final int pow;
/*     */     
/*     */     public final int root;
/*     */     
/*     */     public Exponent(int pow, int root) {
/* 241 */       this.pow = pow;
/* 242 */       this.root = root;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class DefaultFormat extends UnitFormat {
/* 254 */     final HashMap<String, Unit<?>> _nameToUnit = new HashMap<String, Unit<?>>();
/*     */     
/* 259 */     final HashMap<Unit<?>, String> _unitToName = new HashMap<Unit<?>, String>();
/*     */     
/*     */     private static final int EOF = 0;
/*     */     
/*     */     private static final int IDENTIFIER = 1;
/*     */     
/*     */     private static final int OPEN_PAREN = 2;
/*     */     
/*     */     private static final int CLOSE_PAREN = 3;
/*     */     
/*     */     private static final int EXPONENT = 4;
/*     */     
/*     */     private static final int MULTIPLY = 5;
/*     */     
/*     */     private static final int DIVIDE = 6;
/*     */     
/*     */     private static final int PLUS = 7;
/*     */     
/*     */     private static final int INTEGER = 8;
/*     */     
/*     */     private static final int FLOAT = 9;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public void label(Unit<?> unit, String label) {
/* 263 */       if (!isValidIdentifier(label))
/* 264 */         throw new IllegalArgumentException("Label: " + label + " is not a valid identifier."); 
/* 266 */       synchronized (this) {
/* 267 */         this._nameToUnit.put(label, unit);
/* 268 */         this._unitToName.put(unit, label);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void alias(Unit<?> unit, String alias) {
/* 274 */       if (!isValidIdentifier(alias))
/* 275 */         throw new IllegalArgumentException("Alias: " + alias + " is not a valid identifier."); 
/* 277 */       synchronized (this) {
/* 278 */         this._nameToUnit.put(alias, unit);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isValidIdentifier(String name) {
/* 284 */       if (name == null || name.length() == 0)
/* 285 */         return false; 
/* 286 */       for (int i = 0; i < name.length(); i++) {
/* 287 */         if (!isUnitIdentifierPart(name.charAt(i)))
/* 288 */           return false; 
/*     */       } 
/* 290 */       return true;
/*     */     }
/*     */     
/*     */     static boolean isUnitIdentifierPart(char ch) {
/* 294 */       return (Character.isLetter(ch) || (!Character.isWhitespace(ch) && !Character.isDigit(ch) && ch != '·' && ch != '*' && ch != '/' && ch != '(' && ch != ')' && ch != '[' && ch != ']' && ch != '¹' && ch != '²' && ch != '³' && ch != '^' && ch != '+' && ch != '-'));
/*     */     }
/*     */     
/*     */     public String nameFor(Unit<?> unit) {
/* 305 */       String label = this._unitToName.get(unit);
/* 306 */       if (label != null)
/* 307 */         return label; 
/* 308 */       if (unit instanceof BaseUnit)
/* 309 */         return ((BaseUnit)unit).getSymbol(); 
/* 310 */       if (unit instanceof AlternateUnit)
/* 311 */         return ((AlternateUnit)unit).getSymbol(); 
/* 312 */       if (unit instanceof TransformedUnit) {
/* 313 */         TransformedUnit<?> tfmUnit = (TransformedUnit)unit;
/* 314 */         Unit<?> baseUnits = tfmUnit.getStandardUnit();
/* 315 */         UnitConverter cvtr = tfmUnit.toStandardUnit();
/* 316 */         StringBuffer result = new StringBuffer();
/* 317 */         String baseUnitName = baseUnits.toString();
/* 318 */         if (baseUnitName.indexOf('·') >= 0 || baseUnitName.indexOf('*') >= 0 || baseUnitName.indexOf('/') >= 0) {
/* 324 */           result.append('(');
/* 325 */           result.append(baseUnitName);
/* 326 */           result.append(')');
/*     */         } else {
/* 328 */           result.append(baseUnitName);
/*     */         } 
/* 330 */         if (cvtr instanceof AddConverter) {
/* 331 */           result.append('+');
/* 332 */           result.append(((AddConverter)cvtr).getOffset());
/* 333 */         } else if (cvtr instanceof RationalConverter) {
/* 334 */           long dividend = ((RationalConverter)cvtr).getDividend();
/* 335 */           if (dividend != 1L) {
/* 336 */             result.append('*');
/* 337 */             result.append(dividend);
/*     */           } 
/* 339 */           long divisor = ((RationalConverter)cvtr).getDivisor();
/* 340 */           if (divisor != 1L) {
/* 341 */             result.append('/');
/* 342 */             result.append(divisor);
/*     */           } 
/* 344 */         } else if (cvtr instanceof MultiplyConverter) {
/* 345 */           result.append('*');
/* 346 */           result.append(((MultiplyConverter)cvtr).getFactor());
/*     */         } else {
/* 348 */           return "[" + baseUnits + "?]";
/*     */         } 
/* 350 */         return result.toString();
/*     */       } 
/* 353 */       if (unit instanceof CompoundUnit) {
/* 354 */         CompoundUnit<?> cpdUnit = (CompoundUnit)unit;
/* 355 */         return nameFor(cpdUnit.getHigher()).toString() + ":" + nameFor(cpdUnit.getLower());
/*     */       } 
/* 358 */       return null;
/*     */     }
/*     */     
/*     */     public Unit<?> unitFor(String name) {
/* 363 */       Unit<?> unit = this._nameToUnit.get(name);
/* 364 */       if (unit != null)
/* 365 */         return unit; 
/* 366 */       unit = Unit.SYMBOL_TO_UNIT.get(name);
/* 367 */       return unit;
/*     */     }
/*     */     
/*     */     public Unit<? extends Quantity> parseSingleUnit(CharSequence csq, ParsePosition pos) throws ParseException {
/* 376 */       int startIndex = pos.getIndex();
/* 377 */       String name = readIdentifier(csq, pos);
/* 378 */       Unit<?> unit = unitFor(name);
/* 379 */       check((unit != null), name + " not recognized", csq, startIndex);
/* 380 */       return (Unit)unit;
/*     */     }
/*     */     
/*     */     public Unit<? extends Quantity> parseProductUnit(CharSequence csq, ParsePosition pos) throws ParseException {
/* 387 */       Unit<Dimensionless> result = Unit.ONE;
/* 388 */       int token = nextToken(csq, pos);
/* 389 */       switch (token) {
/*     */         case 1:
/* 391 */           result = (Unit)parseSingleUnit(csq, pos);
/*     */           break;
/*     */         case 2:
/* 394 */           pos.setIndex(pos.getIndex() + 1);
/* 395 */           result = (Unit)parseProductUnit(csq, pos);
/* 396 */           token = nextToken(csq, pos);
/* 397 */           check((token == 3), "')' expected", csq, pos.getIndex());
/* 398 */           pos.setIndex(pos.getIndex() + 1);
/*     */           break;
/*     */       } 
/* 401 */       token = nextToken(csq, pos);
/*     */       while (true) {
/*     */         UnitFormat.Exponent e;
/* 403 */         switch (token) {
/*     */           case 4:
/* 405 */             e = readExponent(csq, pos);
/* 406 */             if (e.pow != 1)
/* 407 */               result = (Unit)result.pow(e.pow); 
/* 409 */             if (e.root != 1)
/* 410 */               result = (Unit)result.root(e.root); 
/*     */             break;
/*     */           case 5:
/* 414 */             pos.setIndex(pos.getIndex() + 1);
/* 415 */             token = nextToken(csq, pos);
/* 416 */             if (token == 8) {
/* 417 */               long n = readLong(csq, pos);
/* 418 */               if (n != 1L)
/* 419 */                 result = result.times(n); 
/*     */               break;
/*     */             } 
/* 421 */             if (token == 9) {
/* 422 */               double d = readDouble(csq, pos);
/* 423 */               if (d != 1.0D)
/* 424 */                 result = result.times(d); 
/*     */               break;
/*     */             } 
/* 427 */             result = (Unit)result.times(parseProductUnit(csq, pos));
/*     */             break;
/*     */           case 6:
/* 431 */             pos.setIndex(pos.getIndex() + 1);
/* 432 */             token = nextToken(csq, pos);
/* 433 */             if (token == 8) {
/* 434 */               long n = readLong(csq, pos);
/* 435 */               if (n != 1L)
/* 436 */                 result = result.divide(n); 
/*     */               break;
/*     */             } 
/* 438 */             if (token == 9) {
/* 439 */               double d = readDouble(csq, pos);
/* 440 */               if (d != 1.0D)
/* 441 */                 result = result.divide(d); 
/*     */               break;
/*     */             } 
/* 444 */             result = (Unit)result.divide(parseProductUnit(csq, pos));
/*     */             break;
/*     */           case 7:
/* 448 */             pos.setIndex(pos.getIndex() + 1);
/* 449 */             token = nextToken(csq, pos);
/* 450 */             if (token == 8) {
/* 451 */               long n = readLong(csq, pos);
/* 452 */               if (n != 1L)
/* 453 */                 result = result.plus(n); 
/*     */               break;
/*     */             } 
/* 455 */             if (token == 9) {
/* 456 */               double d = readDouble(csq, pos);
/* 457 */               if (d != 1.0D)
/* 458 */                 result = result.plus(d); 
/*     */               break;
/*     */             } 
/* 461 */             throw new ParseException("not a number", pos.getIndex());
/*     */           case 0:
/*     */           case 3:
/* 466 */             return (Unit)result;
/*     */           default:
/* 468 */             throw new ParseException("unexpected token " + token, pos.getIndex());
/*     */         } 
/* 470 */         token = nextToken(csq, pos);
/*     */       } 
/*     */     }
/*     */     
/*     */     private int nextToken(CharSequence csq, ParsePosition pos) {
/* 486 */       int length = csq.length();
/* 487 */       while (pos.getIndex() < length) {
/* 488 */         char c = csq.charAt(pos.getIndex());
/* 489 */         if (isUnitIdentifierPart(c))
/* 490 */           return 1; 
/* 491 */         if (c == '(')
/* 492 */           return 2; 
/* 493 */         if (c == ')')
/* 494 */           return 3; 
/* 495 */         if (c == '^' || c == '¹' || c == '²' || c == '³')
/* 496 */           return 4; 
/* 497 */         if (c == '*') {
/* 498 */           char c2 = csq.charAt(pos.getIndex() + 1);
/* 499 */           if (c2 == '*')
/* 500 */             return 4; 
/* 502 */           return 5;
/*     */         } 
/* 504 */         if (c == '·')
/* 505 */           return 5; 
/* 506 */         if (c == '/')
/* 507 */           return 6; 
/* 508 */         if (c == '+')
/* 509 */           return 7; 
/* 510 */         if (c == '-' || Character.isDigit(c)) {
/* 511 */           int index = pos.getIndex() + 1;
/* 512 */           while (index < length && (Character.isDigit(c) || c == '-' || c == '.' || c == 'E')) {
/* 514 */             c = csq.charAt(index++);
/* 515 */             if (c == '.')
/* 516 */               return 9; 
/*     */           } 
/* 519 */           return 8;
/*     */         } 
/* 521 */         pos.setIndex(pos.getIndex() + 1);
/*     */       } 
/* 523 */       return 0;
/*     */     }
/*     */     
/*     */     private void check(boolean expr, String message, CharSequence csq, int index) throws ParseException {
/* 528 */       if (!expr)
/* 529 */         throw new ParseException(message + " (in " + csq + " at index " + index + ")", index); 
/*     */     }
/*     */     
/*     */     private UnitFormat.Exponent readExponent(CharSequence csq, ParsePosition pos) {
/* 535 */       char c = csq.charAt(pos.getIndex());
/* 536 */       if (c == '^') {
/* 537 */         pos.setIndex(pos.getIndex() + 1);
/* 538 */       } else if (c == '*') {
/* 539 */         pos.setIndex(pos.getIndex() + 2);
/*     */       } 
/* 541 */       int length = csq.length();
/* 542 */       int pow = 0;
/* 543 */       boolean isPowNegative = false;
/* 544 */       int root = 0;
/* 545 */       boolean isRootNegative = false;
/* 546 */       boolean isRoot = false;
/* 547 */       while (pos.getIndex() < length) {
/* 548 */         c = csq.charAt(pos.getIndex());
/* 549 */         if (c == '¹') {
/* 550 */           if (isRoot) {
/* 551 */             root = root * 10 + 1;
/*     */           } else {
/* 553 */             pow = pow * 10 + 1;
/*     */           } 
/* 555 */         } else if (c == '²') {
/* 556 */           if (isRoot) {
/* 557 */             root = root * 10 + 2;
/*     */           } else {
/* 559 */             pow = pow * 10 + 2;
/*     */           } 
/* 561 */         } else if (c == '³') {
/* 562 */           if (isRoot) {
/* 563 */             root = root * 10 + 3;
/*     */           } else {
/* 565 */             pow = pow * 10 + 3;
/*     */           } 
/* 567 */         } else if (c == '-') {
/* 568 */           if (isRoot) {
/* 569 */             isRootNegative = true;
/*     */           } else {
/* 571 */             isPowNegative = true;
/*     */           } 
/* 573 */         } else if (c >= '0' && c <= '9') {
/* 574 */           if (isRoot) {
/* 575 */             root = root * 10 + c - 48;
/*     */           } else {
/* 577 */             pow = pow * 10 + c - 48;
/*     */           } 
/* 579 */         } else if (c == ':') {
/* 580 */           isRoot = true;
/*     */         } else {
/*     */           break;
/*     */         } 
/* 584 */         pos.setIndex(pos.getIndex() + 1);
/*     */       } 
/* 586 */       if (pow == 0)
/* 586 */         pow = 1; 
/* 587 */       if (root == 0)
/* 587 */         root = 1; 
/* 588 */       return new UnitFormat.Exponent(isPowNegative ? -pow : pow, isRootNegative ? -root : root);
/*     */     }
/*     */     
/*     */     private long readLong(CharSequence csq, ParsePosition pos) {
/* 593 */       int length = csq.length();
/* 594 */       int result = 0;
/* 595 */       boolean isNegative = false;
/* 596 */       while (pos.getIndex() < length) {
/* 597 */         char c = csq.charAt(pos.getIndex());
/* 598 */         if (c == '-') {
/* 599 */           isNegative = true;
/* 600 */         } else if (c >= '0' && c <= '9') {
/* 601 */           result = result * 10 + c - 48;
/*     */         } else {
/*     */           break;
/*     */         } 
/* 605 */         pos.setIndex(pos.getIndex() + 1);
/*     */       } 
/* 607 */       return isNegative ? -result : result;
/*     */     }
/*     */     
/*     */     private double readDouble(CharSequence csq, ParsePosition pos) {
/* 611 */       int length = csq.length();
/* 612 */       int start = pos.getIndex();
/* 613 */       int end = start + 1;
/* 614 */       while (end < length && 
/* 615 */         "012356789+-.E".indexOf(csq.charAt(end)) >= 0)
/* 618 */         end++; 
/* 620 */       pos.setIndex(end + 1);
/* 621 */       return Double.parseDouble(csq.subSequence(start, end).toString());
/*     */     }
/*     */     
/*     */     private String readIdentifier(CharSequence csq, ParsePosition pos) {
/* 625 */       int length = csq.length();
/* 626 */       int start = pos.getIndex();
/* 627 */       int i = start;
/* 628 */       while (++i < length && isUnitIdentifierPart(csq.charAt(i)));
/* 629 */       pos.setIndex(i);
/* 630 */       return csq.subSequence(start, i).toString();
/*     */     }
/*     */     
/*     */     public Appendable format(Unit<?> unit, Appendable appendable) throws IOException {
/* 639 */       String name = nameFor(unit);
/* 640 */       if (name != null)
/* 641 */         return appendable.append(name); 
/* 642 */       if (!(unit instanceof ProductUnit))
/* 643 */         throw new IllegalArgumentException("Cannot format given Object as a Unit"); 
/* 646 */       ProductUnit<?> productUnit = (ProductUnit)unit;
/* 647 */       int invNbr = 0;
/* 650 */       boolean start = true;
/*     */       int i;
/* 651 */       for (i = 0; i < productUnit.getUnitCount(); i++) {
/* 652 */         int pow = productUnit.getUnitPow(i);
/* 653 */         if (pow >= 0) {
/* 654 */           if (!start)
/* 655 */             appendable.append('·'); 
/* 657 */           name = nameFor(productUnit.getUnit(i));
/* 658 */           int root = productUnit.getUnitRoot(i);
/* 659 */           append(appendable, name, pow, root);
/* 660 */           start = false;
/*     */         } else {
/* 662 */           invNbr++;
/*     */         } 
/*     */       } 
/* 667 */       if (invNbr != 0) {
/* 668 */         if (start)
/* 669 */           appendable.append('1'); 
/* 671 */         appendable.append('/');
/* 672 */         if (invNbr > 1)
/* 673 */           appendable.append('('); 
/* 675 */         start = true;
/* 676 */         for (i = 0; i < productUnit.getUnitCount(); i++) {
/* 677 */           int pow = productUnit.getUnitPow(i);
/* 678 */           if (pow < 0) {
/* 679 */             name = nameFor(productUnit.getUnit(i));
/* 680 */             int root = productUnit.getUnitRoot(i);
/* 681 */             if (!start)
/* 682 */               appendable.append('·'); 
/* 684 */             append(appendable, name, -pow, root);
/* 685 */             start = false;
/*     */           } 
/*     */         } 
/* 688 */         if (invNbr > 1)
/* 689 */           appendable.append(')'); 
/*     */       } 
/* 692 */       return appendable;
/*     */     }
/*     */     
/*     */     private void append(Appendable appendable, CharSequence symbol, int pow, int root) throws IOException {
/* 697 */       appendable.append(symbol);
/* 698 */       if (pow != 1 || root != 1)
/* 700 */         if (pow == 2 && root == 1) {
/* 701 */           appendable.append('²');
/* 702 */         } else if (pow == 3 && root == 1) {
/* 703 */           appendable.append('³');
/*     */         } else {
/* 706 */           appendable.append('^');
/* 707 */           appendable.append(String.valueOf(pow));
/* 708 */           if (root != 1) {
/* 709 */             appendable.append(':');
/* 710 */             appendable.append(String.valueOf(root));
/*     */           } 
/*     */         }  
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class ASCIIFormat extends DefaultFormat {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public String nameFor(Unit<?> unit) {
/* 727 */       String name = this._unitToName.get(unit);
/* 728 */       if (name != null)
/* 729 */         return name; 
/* 731 */       return UnitFormat.DEFAULT.nameFor(unit);
/*     */     }
/*     */     
/*     */     public Unit<?> unitFor(String name) {
/* 737 */       Unit<?> unit = this._nameToUnit.get(name);
/* 738 */       if (unit != null)
/* 739 */         return unit; 
/* 741 */       return UnitFormat.DEFAULT.unitFor(name);
/*     */     }
/*     */     
/*     */     public Appendable format(Unit<?> unit, Appendable appendable) throws IOException {
/* 747 */       String name = nameFor(unit);
/* 748 */       if (name != null)
/* 749 */         return appendable.append(name); 
/* 750 */       if (!(unit instanceof ProductUnit))
/* 751 */         throw new IllegalArgumentException("Cannot format given Object as a Unit"); 
/* 754 */       ProductUnit<?> productUnit = (ProductUnit)unit;
/* 755 */       for (int i = 0; i < productUnit.getUnitCount(); i++) {
/* 756 */         if (i != 0)
/* 757 */           appendable.append('*'); 
/* 759 */         name = nameFor(productUnit.getUnit(i));
/* 760 */         int pow = productUnit.getUnitPow(i);
/* 761 */         int root = productUnit.getUnitRoot(i);
/* 762 */         appendable.append(name);
/* 763 */         if (pow != 1 || root != 1) {
/* 765 */           appendable.append('^');
/* 766 */           appendable.append(String.valueOf(pow));
/* 767 */           if (root != 1) {
/* 768 */             appendable.append(':');
/* 769 */             appendable.append(String.valueOf(root));
/*     */           } 
/*     */         } 
/*     */       } 
/* 773 */       return appendable;
/*     */     }
/*     */   }
/*     */   
/* 783 */   private static final Unit<?>[] SI_UNITS = new Unit[] { 
/* 783 */       SI.AMPERE, SI.BECQUEREL, SI.CANDELA, SI.COULOMB, SI.FARAD, SI.GRAY, SI.HENRY, SI.HERTZ, SI.JOULE, SI.KATAL, 
/* 783 */       SI.KELVIN, SI.LUMEN, SI.LUX, SI.METRE, SI.MOLE, SI.NEWTON, SI.OHM, SI.PASCAL, SI.RADIAN, SI.SECOND, 
/* 783 */       SI.SIEMENS, SI.SIEVERT, SI.STERADIAN, SI.TESLA, SI.VOLT, SI.WATT, SI.WEBER };
/*     */   
/* 789 */   private static final String[] PREFIXES = new String[] { 
/* 789 */       "Y", "Z", "E", "P", "T", "G", "M", "k", "h", "da", 
/* 789 */       "d", "c", "m", "µ", "n", "p", "f", "a", "z", "y" };
/*     */   
/* 793 */   private static final UnitConverter[] CONVERTERS = new UnitConverter[] { 
/* 793 */       (UnitConverter)SI.E24, (UnitConverter)SI.E21, (UnitConverter)SI.E18, (UnitConverter)SI.E15, (UnitConverter)SI.E12, (UnitConverter)SI.E9, (UnitConverter)SI.E6, (UnitConverter)SI.E3, (UnitConverter)SI.E2, (UnitConverter)SI.E1, 
/* 793 */       (UnitConverter)SI.Em1, (UnitConverter)SI.Em2, (UnitConverter)SI.Em3, (UnitConverter)SI.Em6, (UnitConverter)SI.Em9, (UnitConverter)SI.Em12, (UnitConverter)SI.Em15, (UnitConverter)SI.Em18, (UnitConverter)SI.Em21, (UnitConverter)SI.Em24 };
/*     */   
/*     */   private static String asciiPrefix(String prefix) {
/* 798 */     return (prefix == "µ") ? "micro" : prefix;
/*     */   }
/*     */   
/*     */   static {
/*     */     int i;
/* 802 */     for (i = 0; i < SI_UNITS.length; i++) {
/* 803 */       for (int j = 0; j < PREFIXES.length; j++) {
/* 804 */         Unit<?> si = SI_UNITS[i];
/* 805 */         Unit<?> u = si.transform(CONVERTERS[j]);
/* 806 */         String symbol = (si instanceof BaseUnit) ? ((BaseUnit)si).getSymbol() : ((AlternateUnit)si).getSymbol();
/* 808 */         DEFAULT.label(u, PREFIXES[j] + symbol);
/* 809 */         if (PREFIXES[j] == "µ")
/* 810 */           ASCII.label(u, "micro" + symbol); 
/*     */       } 
/*     */     } 
/* 815 */     DEFAULT.label(SI.GRAM, "g");
/* 816 */     for (i = 0; i < PREFIXES.length; i++) {
/* 817 */       if (CONVERTERS[i] != SI.E3) {
/* 818 */         DEFAULT.label(SI.KILOGRAM.transform(CONVERTERS[i].concatenate((UnitConverter)SI.Em3)), PREFIXES[i] + "g");
/* 820 */         if (PREFIXES[i] == "µ")
/* 821 */           ASCII.label(SI.KILOGRAM.transform(CONVERTERS[i].concatenate((UnitConverter)SI.Em3)), "microg"); 
/*     */       } 
/*     */     } 
/* 826 */     DEFAULT.alias(SI.OHM, "Ohm");
/* 827 */     ASCII.label(SI.OHM, "Ohm");
/* 828 */     for (i = 0; i < PREFIXES.length; i++) {
/* 829 */       DEFAULT.alias(SI.OHM.transform(CONVERTERS[i]), PREFIXES[i] + "Ohm");
/* 830 */       ASCII.label(SI.OHM.transform(CONVERTERS[i]), asciiPrefix(PREFIXES[i]) + "Ohm");
/*     */     } 
/* 834 */     DEFAULT.label(SI.CELSIUS, "℃");
/* 835 */     DEFAULT.alias(SI.CELSIUS, "°C");
/* 836 */     ASCII.label(SI.CELSIUS, "Celsius");
/* 837 */     for (i = 0; i < PREFIXES.length; i++) {
/* 838 */       DEFAULT.label(SI.CELSIUS.transform(CONVERTERS[i]), PREFIXES[i] + "℃");
/* 839 */       DEFAULT.alias(SI.CELSIUS.transform(CONVERTERS[i]), PREFIXES[i] + "°C");
/* 840 */       ASCII.label(SI.CELSIUS.transform(CONVERTERS[i]), asciiPrefix(PREFIXES[i]) + "Celsius");
/*     */     } 
/* 847 */     DEFAULT.label(NonSI.PERCENT, "%");
/* 848 */     DEFAULT.label(NonSI.DECIBEL, "dB");
/* 849 */     DEFAULT.label(NonSI.G, "grav");
/* 850 */     DEFAULT.label(NonSI.ATOM, "atom");
/* 851 */     DEFAULT.label(NonSI.REVOLUTION, "rev");
/* 852 */     DEFAULT.label(NonSI.DEGREE_ANGLE, "°");
/* 853 */     ASCII.label(NonSI.DEGREE_ANGLE, "degree_angle");
/* 854 */     DEFAULT.label(NonSI.MINUTE_ANGLE, "'");
/* 855 */     DEFAULT.label(NonSI.SECOND_ANGLE, "\"");
/* 856 */     DEFAULT.label(NonSI.CENTIRADIAN, "centiradian");
/* 857 */     DEFAULT.label(NonSI.GRADE, "grade");
/* 858 */     DEFAULT.label(NonSI.ARE, "a");
/* 859 */     DEFAULT.label(NonSI.HECTARE, "ha");
/* 860 */     DEFAULT.label(NonSI.BYTE, "byte");
/* 861 */     DEFAULT.label(NonSI.MINUTE, "min");
/* 862 */     DEFAULT.label(NonSI.HOUR, "h");
/* 863 */     DEFAULT.label(NonSI.DAY, "day");
/* 864 */     DEFAULT.label(NonSI.WEEK, "week");
/* 865 */     DEFAULT.label(NonSI.YEAR, "year");
/* 866 */     DEFAULT.label(NonSI.MONTH, "month");
/* 867 */     DEFAULT.label(NonSI.DAY_SIDEREAL, "day_sidereal");
/* 868 */     DEFAULT.label(NonSI.YEAR_SIDEREAL, "year_sidereal");
/* 869 */     DEFAULT.label(NonSI.YEAR_CALENDAR, "year_calendar");
/* 870 */     DEFAULT.label(NonSI.E, "e");
/* 871 */     DEFAULT.label(NonSI.FARADAY, "Fd");
/* 872 */     DEFAULT.label(NonSI.FRANKLIN, "Fr");
/* 873 */     DEFAULT.label(NonSI.GILBERT, "Gi");
/* 874 */     DEFAULT.label(NonSI.ERG, "erg");
/* 875 */     DEFAULT.label(NonSI.ELECTRON_VOLT, "eV");
/* 876 */     DEFAULT.label(SI.KILO(NonSI.ELECTRON_VOLT), "keV");
/* 877 */     DEFAULT.label(SI.MEGA(NonSI.ELECTRON_VOLT), "MeV");
/* 878 */     DEFAULT.label(SI.GIGA(NonSI.ELECTRON_VOLT), "GeV");
/* 879 */     DEFAULT.label(NonSI.LAMBERT, "La");
/* 880 */     DEFAULT.label(NonSI.FOOT, "ft");
/* 881 */     DEFAULT.label(NonSI.FOOT_SURVEY_US, "foot_survey_us");
/* 882 */     DEFAULT.label(NonSI.YARD, "yd");
/* 883 */     DEFAULT.label(NonSI.INCH, "in");
/* 884 */     DEFAULT.label(NonSI.MILE, "mi");
/* 885 */     DEFAULT.label(NonSI.NAUTICAL_MILE, "nmi");
/* 886 */     DEFAULT.label(NonSI.MILES_PER_HOUR, "mph");
/* 887 */     DEFAULT.label(NonSI.ANGSTROM, "Å");
/* 888 */     ASCII.label(NonSI.ANGSTROM, "Angstrom");
/* 889 */     DEFAULT.label(NonSI.ASTRONOMICAL_UNIT, "ua");
/* 890 */     DEFAULT.label(NonSI.LIGHT_YEAR, "ly");
/* 891 */     DEFAULT.label(NonSI.PARSEC, "pc");
/* 892 */     DEFAULT.label(NonSI.POINT, "pt");
/* 893 */     DEFAULT.label(NonSI.PIXEL, "pixel");
/* 894 */     DEFAULT.label(NonSI.MAXWELL, "Mx");
/* 895 */     DEFAULT.label(NonSI.GAUSS, "G");
/* 896 */     DEFAULT.label(NonSI.ATOMIC_MASS, "u");
/* 897 */     DEFAULT.label(NonSI.ELECTRON_MASS, "me");
/* 898 */     DEFAULT.label(NonSI.POUND, "lb");
/* 899 */     DEFAULT.label(NonSI.OUNCE, "oz");
/* 900 */     DEFAULT.label(NonSI.TON_US, "ton_us");
/* 901 */     DEFAULT.label(NonSI.TON_UK, "ton_uk");
/* 902 */     DEFAULT.label(NonSI.METRIC_TON, "t");
/* 903 */     DEFAULT.label(NonSI.DYNE, "dyn");
/* 904 */     DEFAULT.label(NonSI.KILOGRAM_FORCE, "kgf");
/* 905 */     DEFAULT.label(NonSI.POUND_FORCE, "lbf");
/* 906 */     DEFAULT.label(NonSI.HORSEPOWER, "hp");
/* 907 */     DEFAULT.label(NonSI.ATMOSPHERE, "atm");
/* 908 */     DEFAULT.label(NonSI.BAR, "bar");
/* 909 */     DEFAULT.label(NonSI.MILLIMETER_OF_MERCURY, "mmHg");
/* 910 */     DEFAULT.label(NonSI.INCH_OF_MERCURY, "inHg");
/* 911 */     DEFAULT.label(NonSI.RAD, "rd");
/* 912 */     DEFAULT.label(NonSI.REM, "rem");
/* 913 */     DEFAULT.label(NonSI.CURIE, "Ci");
/* 914 */     DEFAULT.label(NonSI.RUTHERFORD, "Rd");
/* 915 */     DEFAULT.label(NonSI.SPHERE, "sphere");
/* 916 */     DEFAULT.label(NonSI.RANKINE, "°R");
/* 917 */     ASCII.label(NonSI.RANKINE, "degree_rankine");
/* 918 */     DEFAULT.label(NonSI.FAHRENHEIT, "°F");
/* 919 */     ASCII.label(NonSI.FAHRENHEIT, "degree_fahrenheit");
/* 920 */     DEFAULT.label(NonSI.KNOT, "kn");
/* 921 */     DEFAULT.label(NonSI.MACH, "Mach");
/* 922 */     DEFAULT.label(NonSI.C, "c");
/* 923 */     DEFAULT.label(NonSI.LITRE, "L");
/* 924 */     DEFAULT.label(SI.MICRO(NonSI.LITRE), "µL");
/* 925 */     ASCII.label(SI.MICRO(NonSI.LITRE), "microL");
/* 926 */     DEFAULT.label(SI.MILLI(NonSI.LITRE), "mL");
/* 927 */     DEFAULT.label(SI.CENTI(NonSI.LITRE), "cL");
/* 928 */     DEFAULT.label(SI.DECI(NonSI.LITRE), "dL");
/* 929 */     DEFAULT.label(NonSI.GALLON_LIQUID_US, "gal");
/* 930 */     DEFAULT.label(NonSI.OUNCE_LIQUID_US, "oz");
/* 931 */     DEFAULT.label(NonSI.GALLON_DRY_US, "gallon_dry_us");
/* 932 */     DEFAULT.label(NonSI.GALLON_UK, "gallon_uk");
/* 933 */     DEFAULT.label(NonSI.OUNCE_LIQUID_UK, "oz_uk");
/* 934 */     DEFAULT.label(NonSI.ROENTGEN, "Roentgen");
/* 935 */     if (Locale.getDefault().getCountry().equals("GB")) {
/* 936 */       DEFAULT.label(NonSI.GALLON_UK, "gal");
/* 937 */       DEFAULT.label(NonSI.OUNCE_LIQUID_UK, "oz");
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract Appendable format(Unit<?> paramUnit, Appendable paramAppendable) throws IOException;
/*     */   
/*     */   public abstract Unit<? extends Quantity> parseProductUnit(CharSequence paramCharSequence, ParsePosition paramParsePosition) throws ParseException;
/*     */   
/*     */   public abstract Unit<? extends Quantity> parseSingleUnit(CharSequence paramCharSequence, ParsePosition paramParsePosition) throws ParseException;
/*     */   
/*     */   public abstract void label(Unit<?> paramUnit, String paramString);
/*     */   
/*     */   public abstract void alias(Unit<?> paramUnit, String paramString);
/*     */   
/*     */   public abstract boolean isValidIdentifier(String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\UnitFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */