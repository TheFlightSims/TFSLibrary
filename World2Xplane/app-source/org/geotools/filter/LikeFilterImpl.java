/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class LikeFilterImpl extends AbstractFilterImpl implements LikeFilter {
/*  43 */   private Expression attribute = null;
/*     */   
/*  46 */   private String pattern = null;
/*     */   
/*  49 */   private String wildcardSingle = ".?";
/*     */   
/*  52 */   private String wildcardMulti = ".*";
/*     */   
/*  55 */   private String escape = "\\";
/*     */   
/*  58 */   private Pattern compPattern = null;
/*     */   
/*     */   boolean matchingCase;
/*     */   
/*     */   protected MultiValuedFilter.MatchAction matchAction;
/*     */   
/*     */   public static String convertToSQL92(char escape, char multi, char single, boolean matchCase, String pattern) throws IllegalArgumentException {
/* 104 */     if (escape == '\'' || multi == '\'' || single == '\'')
/* 105 */       throw new IllegalArgumentException("do not use single quote (') as special char!"); 
/* 107 */     StringBuffer result = new StringBuffer(pattern.length() + 5);
/* 108 */     for (int i = 0; i < pattern.length(); i++) {
/* 110 */       char chr = pattern.charAt(i);
/* 111 */       if (chr == escape) {
/* 114 */         if (i != pattern.length() - 1)
/* 115 */           result.append(pattern.charAt(i + 1)); 
/* 116 */         i++;
/* 118 */       } else if (chr == single) {
/* 120 */         result.append('_');
/* 122 */       } else if (chr == multi) {
/* 124 */         result.append('%');
/* 126 */       } else if (chr == '\'') {
/* 128 */         result.append('\'');
/* 129 */         result.append('\'');
/*     */       } else {
/* 133 */         result.append(matchCase ? chr : Character.toUpperCase(chr));
/*     */       } 
/*     */     } 
/* 137 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getSQL92LikePattern() throws IllegalArgumentException {
/* 147 */     if (this.escape.length() != 1)
/* 149 */       throw new IllegalArgumentException("Like Pattern --> escape char should be of length exactly 1"); 
/* 151 */     if (this.wildcardSingle.length() != 1)
/* 153 */       throw new IllegalArgumentException("Like Pattern --> wildcardSingle char should be of length exactly 1"); 
/* 155 */     if (this.wildcardMulti.length() != 1)
/* 157 */       throw new IllegalArgumentException("Like Pattern --> wildcardMulti char should be of length exactly 1"); 
/* 159 */     return convertToSQL92(this.escape.charAt(0), this.wildcardMulti.charAt(0), this.wildcardSingle.charAt(0), this.matchingCase, this.pattern);
/*     */   }
/*     */   
/*     */   public void setWildCard(String wildCard) {
/* 168 */     this.wildcardMulti = wildCard;
/* 169 */     this.compPattern = null;
/*     */   }
/*     */   
/*     */   public void setSingleChar(String singleChar) {
/* 173 */     this.wildcardSingle = singleChar;
/* 174 */     this.compPattern = null;
/*     */   }
/*     */   
/*     */   public void setEscape(String escape) {
/* 178 */     this.escape = escape;
/* 179 */     this.compPattern = null;
/*     */   }
/*     */   
/*     */   public void setMatchCase(boolean matchingCase) {
/* 183 */     this.matchingCase = matchingCase;
/* 184 */     this.compPattern = null;
/*     */   }
/*     */   
/*     */   public boolean isMatchingCase() {
/* 188 */     return this.matchingCase;
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/* 192 */     return this.matchAction;
/*     */   }
/*     */   
/*     */   public void setMatchingCase(boolean matchingCase) {
/* 196 */     this.matchingCase = matchingCase;
/*     */   }
/*     */   
/*     */   private Matcher getMatcher(String string) {
/* 200 */     if (this.compPattern == null) {
/* 203 */       String pattern1 = new String(this.pattern);
/* 204 */       String wildcardMulti1 = new String(this.wildcardMulti);
/* 205 */       String wildcardSingle1 = new String(this.wildcardSingle);
/* 206 */       String escape1 = new String(this.escape);
/* 212 */       char esc = escape1.charAt(0);
/* 213 */       if (LOGGER.isLoggable(Level.FINER)) {
/* 214 */         LOGGER.finer("wildcard " + wildcardMulti1 + " single " + wildcardSingle1);
/* 215 */         LOGGER.finer("escape " + escape1 + " esc " + esc + " esc == \\ " + ((esc == '\\') ? 1 : 0));
/*     */       } 
/* 219 */       String escapedWildcardMulti = fixSpecials(wildcardMulti1);
/* 220 */       String escapedWildcardSingle = fixSpecials(wildcardSingle1);
/* 223 */       StringBuffer tmp = new StringBuffer("");
/* 225 */       boolean escapedMode = false;
/* 227 */       for (int i = 0; i < pattern1.length(); i++) {
/* 228 */         char chr = pattern1.charAt(i);
/* 229 */         if (LOGGER.isLoggable(Level.FINER))
/* 230 */           LOGGER.finer("tmp = " + tmp + " looking at " + chr); 
/* 233 */         if (pattern1.regionMatches(false, i, escape1, 0, escape1.length())) {
/* 235 */           LOGGER.finer("escape ");
/* 236 */           escapedMode = true;
/* 238 */           i += escape1.length();
/* 239 */           chr = pattern1.charAt(i);
/*     */         } 
/* 242 */         if (pattern1.regionMatches(false, i, wildcardMulti1, 0, wildcardMulti1.length())) {
/* 244 */           LOGGER.finer("multi wildcard");
/* 246 */           if (escapedMode) {
/* 247 */             LOGGER.finer("escaped ");
/* 248 */             tmp.append(escapedWildcardMulti);
/*     */           } else {
/* 250 */             tmp.append(".*");
/*     */           } 
/* 253 */           i += wildcardMulti1.length() - 1;
/* 254 */           escapedMode = false;
/* 259 */         } else if (pattern1.regionMatches(false, i, wildcardSingle1, 0, wildcardSingle1.length())) {
/* 262 */           LOGGER.finer("single wildcard");
/* 264 */           if (escapedMode) {
/* 265 */             LOGGER.finer("escaped ");
/* 266 */             tmp.append(escapedWildcardSingle);
/*     */           } else {
/* 270 */             tmp.append(".{1}");
/*     */           } 
/* 273 */           i += wildcardSingle1.length() - 1;
/* 274 */           escapedMode = false;
/* 279 */         } else if (isSpecial(chr)) {
/* 280 */           LOGGER.finer("special");
/* 281 */           tmp.append(this.escape + chr);
/* 282 */           escapedMode = false;
/*     */         } else {
/* 287 */           tmp.append(chr);
/* 288 */           escapedMode = false;
/*     */         } 
/*     */       } 
/* 291 */       pattern1 = tmp.toString();
/* 292 */       if (LOGGER.isLoggable(Level.FINER))
/* 293 */         LOGGER.finer("final pattern " + pattern1); 
/* 295 */       this.compPattern = isMatchingCase() ? Pattern.compile(pattern1) : Pattern.compile(pattern1, 2);
/*     */     } 
/* 299 */     return this.compPattern.matcher(string);
/*     */   }
/*     */   
/*     */   protected LikeFilterImpl() {
/* 306 */     this(MultiValuedFilter.MatchAction.ANY);
/*     */   }
/*     */   
/*     */   public LikeFilterImpl(Expression expr, String pattern, String wildcardMulti, String wildcardSingle, String escape) {
/* 311 */     this();
/* 312 */     setExpression(expr);
/* 313 */     setLiteral(pattern);
/* 314 */     setWildCard(wildcardMulti);
/* 315 */     setSingleChar(wildcardSingle);
/* 316 */     setEscape(escape);
/*     */   }
/*     */   
/*     */   protected LikeFilterImpl(MultiValuedFilter.MatchAction matchAction) {
/* 320 */     super(CommonFactoryFinder.getFilterFactory(null));
/* 321 */     this.filterType = 20;
/* 322 */     this.matchAction = matchAction;
/*     */   }
/*     */   
/*     */   public LikeFilterImpl(Expression expr, String pattern, String wildcardMulti, String wildcardSingle, String escape, MultiValuedFilter.MatchAction matchAction) {
/* 327 */     this(matchAction);
/* 328 */     setExpression(expr);
/* 329 */     setLiteral(pattern);
/* 330 */     setWildCard(wildcardMulti);
/* 331 */     setSingleChar(wildcardSingle);
/* 332 */     setEscape(escape);
/*     */   }
/*     */   
/*     */   public final void setValue(Expression attribute) throws IllegalFilterException {
/* 343 */     setExpression(attribute);
/*     */   }
/*     */   
/*     */   public final Expression getValue() {
/* 354 */     return this.attribute;
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 365 */     return getValue();
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 369 */     Expression attribute = (Expression)e;
/* 370 */     if (attribute.getType() != 111 || this.permissiveConstruction) {
/* 372 */       this.attribute = attribute;
/*     */     } else {
/* 374 */       throw new IllegalFilterException("Attempted to add something other than a string attribute expression to a like filter.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void setPattern(Expression p, String wildcardMulti, String wildcardSingle, String escape) {
/* 399 */     setPattern(p.toString(), wildcardMulti, wildcardSingle, escape);
/*     */   }
/*     */   
/*     */   public final void setPattern(String pattern, String wildcardMulti, String wildcardSingle, String escape) {
/* 422 */     setLiteral(pattern);
/* 423 */     setWildCard(wildcardMulti);
/* 424 */     setSingleChar(wildcardSingle);
/* 425 */     setEscape(escape);
/*     */   }
/*     */   
/*     */   public final String getPattern() {
/* 436 */     return getLiteral();
/*     */   }
/*     */   
/*     */   public String getLiteral() {
/* 443 */     return this.pattern;
/*     */   }
/*     */   
/*     */   public void setLiteral(String literal) {
/* 450 */     this.pattern = literal;
/* 451 */     this.compPattern = null;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/* 466 */     if (this.attribute == null)
/* 467 */       return false; 
/* 479 */     Object value = eval(this.attribute, feature);
/* 481 */     if (null == value)
/* 482 */       return false; 
/* 486 */     if (value instanceof java.util.Collection) {
/* 487 */       int count = 0;
/* 489 */       for (Object element : value) {
/* 490 */         Matcher matcher1 = getMatcher(element.toString());
/* 491 */         boolean temp = matcher1.matches();
/* 492 */         if (temp)
/* 493 */           count++; 
/* 496 */         switch (this.matchAction) {
/*     */           case ONE:
/* 497 */             if (count > 1)
/* 497 */               return false; 
/*     */           case ALL:
/* 498 */             if (!temp)
/* 498 */               return false; 
/*     */           case ANY:
/* 499 */             if (temp)
/* 499 */               return true; 
/*     */         } 
/*     */       } 
/* 502 */       switch (this.matchAction) {
/*     */         case ONE:
/* 503 */           return (count == 1);
/*     */         case ALL:
/* 504 */           return true;
/*     */         case ANY:
/* 505 */           return false;
/*     */       } 
/* 506 */       return false;
/*     */     } 
/* 509 */     Matcher matcher = getMatcher(value.toString());
/* 510 */     return matcher.matches();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 520 */     return "[ " + this.attribute.toString() + " is like " + this.pattern + " ]";
/*     */   }
/*     */   
/*     */   public String getEscape() {
/* 529 */     return this.escape;
/*     */   }
/*     */   
/*     */   public final String getWildcardMulti() {
/* 541 */     return this.wildcardMulti;
/*     */   }
/*     */   
/*     */   public String getWildCard() {
/* 553 */     return getWildcardMulti();
/*     */   }
/*     */   
/*     */   public final String getWildcardSingle() {
/* 564 */     return this.wildcardSingle;
/*     */   }
/*     */   
/*     */   public String getSingleChar() {
/* 576 */     return getWildcardSingle();
/*     */   }
/*     */   
/*     */   private boolean isSpecial(char chr) {
/* 588 */     return (chr == '.' || chr == '?' || chr == '*' || chr == '^' || chr == '$' || chr == '+' || chr == '[' || chr == ']' || chr == '(' || chr == ')' || chr == '|' || chr == '\\' || chr == '&');
/*     */   }
/*     */   
/*     */   private String fixSpecials(String inString) {
/* 603 */     StringBuffer tmp = new StringBuffer("");
/* 605 */     for (int i = 0; i < inString.length(); i++) {
/* 606 */       char chr = inString.charAt(i);
/* 608 */       if (isSpecial(chr)) {
/* 609 */         tmp.append(this.escape + chr);
/*     */       } else {
/* 611 */         tmp.append(chr);
/*     */       } 
/*     */     } 
/* 615 */     return tmp.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 629 */     if (obj instanceof LikeFilterImpl) {
/* 630 */       LikeFilterImpl lFilter = (LikeFilterImpl)obj;
/* 633 */       return (lFilter.getFilterType() == this.filterType && lFilter.getValue().equals(this.attribute) && lFilter.getPattern().equals(this.pattern));
/*     */     } 
/* 637 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 646 */     int result = 17;
/* 647 */     result = 37 * result + ((this.attribute == null) ? 0 : this.attribute.hashCode());
/* 649 */     result = 37 * result + ((this.pattern == null) ? 0 : this.pattern.hashCode());
/* 651 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 665 */     return visitor.visit(this, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LikeFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */