/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ public final class DirectionAlongMeridian implements Comparable, Serializable {
/*     */   private static final long serialVersionUID = 1602711631943838328L;
/*     */   
/*     */   static final double EPS = 1.0E-10D;
/*     */   
/*  54 */   private static final Pattern EPSG = Pattern.compile("(\\p{Graph}+)\\s+along\\s+([\\-\\p{Digit}\\.]+)\\s*(deg|°)\\s*(\\p{Graph}+)?", 2);
/*     */   
/*  62 */   private static final AxisDirection[] BASE_DIRECTIONS = new AxisDirection[] { AxisDirection.NORTH, AxisDirection.SOUTH, AxisDirection.EAST, AxisDirection.WEST };
/*     */   
/*     */   private transient AxisDirection direction;
/*     */   
/*     */   public final AxisDirection baseDirection;
/*     */   
/*     */   public final double meridian;
/*     */   
/*     */   private DirectionAlongMeridian(AxisDirection baseDirection, double meridian) {
/*  91 */     this.baseDirection = baseDirection;
/*  92 */     this.meridian = meridian;
/*     */   }
/*     */   
/*     */   public static DirectionAlongMeridian parse(AxisDirection direction) {
/* 100 */     DirectionAlongMeridian candidate = parse(direction.name());
/* 101 */     if (candidate != null)
/* 102 */       candidate.direction = direction; 
/* 104 */     return candidate;
/*     */   }
/*     */   
/*     */   public static DirectionAlongMeridian parse(String name) {
/*     */     double meridian;
/* 112 */     Matcher m = EPSG.matcher(name);
/* 113 */     if (!m.matches())
/* 115 */       return null; 
/* 117 */     String group = m.group(1);
/* 118 */     AxisDirection baseDirection = findDirection(BASE_DIRECTIONS, group);
/* 119 */     if (baseDirection == null || !AxisDirection.NORTH.equals(baseDirection.absolute()))
/* 121 */       return null; 
/* 123 */     group = m.group(2);
/*     */     try {
/* 126 */       meridian = Double.parseDouble(group);
/* 127 */     } catch (NumberFormatException exception) {
/* 130 */       return null;
/*     */     } 
/* 132 */     if (meridian < -180.0D || meridian > 180.0D)
/* 134 */       return null; 
/* 136 */     group = m.group(4);
/* 137 */     if (group != null) {
/* 138 */       AxisDirection sign = findDirection(BASE_DIRECTIONS, group);
/* 139 */       AxisDirection abs = sign.absolute();
/* 140 */       if (sign == null || !AxisDirection.EAST.equals(abs))
/* 142 */         return null; 
/* 144 */       if (sign != abs)
/* 145 */         meridian = -meridian; 
/*     */     } 
/* 148 */     return new DirectionAlongMeridian(baseDirection, meridian);
/*     */   }
/*     */   
/*     */   private static AxisDirection findDirection(AxisDirection[] values, String direction) {
/* 155 */     for (int i = 0; i < values.length; i++) {
/* 156 */       AxisDirection candidate = values[i];
/* 157 */       String name = candidate.name();
/* 158 */       if (direction.equalsIgnoreCase(name))
/* 159 */         return candidate; 
/* 163 */       if (direction.length() == 1) {
/* 164 */         if (candidate == AxisDirection.NORTH && direction.equals("N"))
/* 165 */           return candidate; 
/* 166 */         if (candidate == AxisDirection.SOUTH && direction.equals("S"))
/* 167 */           return candidate; 
/* 168 */         if (candidate == AxisDirection.WEST && direction.equals("W"))
/* 169 */           return candidate; 
/* 170 */         if (candidate == AxisDirection.EAST && direction.equals("E"))
/* 171 */           return candidate; 
/*     */       } 
/*     */     } 
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   static AxisDirection findDirection(String direction) {
/* 181 */     AxisDirection[] values = AxisDirection.values();
/* 182 */     AxisDirection candidate = findDirection(values, direction);
/* 183 */     if (candidate == null) {
/* 184 */       String modified = direction.replace('-', '_');
/* 185 */       if (modified != direction) {
/* 186 */         direction = modified;
/* 187 */         candidate = findDirection(values, modified);
/*     */       } 
/* 189 */       if (candidate == null) {
/* 190 */         modified = direction.replace(' ', '_');
/* 191 */         if (modified != direction)
/* 192 */           candidate = findDirection(values, modified); 
/*     */       } 
/*     */     } 
/* 196 */     return candidate;
/*     */   }
/*     */   
/*     */   public AxisDirection getDirection() {
/* 204 */     if (this.direction != null)
/* 205 */       return this.direction; 
/* 207 */     String name = toString();
/* 208 */     synchronized (AxisDirection.class) {
/* 217 */       this.direction = findDirection(name);
/* 218 */       if (this.direction == null)
/* 219 */         this.direction = AxisDirection.valueOf(name); 
/*     */     } 
/* 222 */     return this.direction;
/*     */   }
/*     */   
/*     */   public double getAngle(DirectionAlongMeridian other) {
/* 235 */     if (!this.baseDirection.equals(other.baseDirection))
/* 236 */       return Double.NaN; 
/* 243 */     double angle = this.meridian - other.meridian;
/* 247 */     if (angle < -180.0D) {
/* 248 */       angle += 360.0D;
/* 249 */     } else if (angle > 180.0D) {
/* 250 */       angle -= 360.0D;
/*     */     } 
/* 256 */     if (!this.baseDirection.equals(this.baseDirection.absolute()))
/* 257 */       angle = -angle; 
/* 259 */     return angle;
/*     */   }
/*     */   
/*     */   public int compareTo(Object object) {
/* 277 */     DirectionAlongMeridian that = (DirectionAlongMeridian)object;
/* 278 */     int c = this.baseDirection.compareTo((CodeList)that.baseDirection);
/* 279 */     if (c != 0)
/* 280 */       return c; 
/* 282 */     double angle = getAngle(that);
/* 283 */     if (angle < 0.0D)
/* 283 */       return 1; 
/* 284 */     if (angle > 0.0D)
/* 284 */       return -1; 
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 294 */     if (object instanceof DirectionAlongMeridian) {
/* 295 */       DirectionAlongMeridian that = (DirectionAlongMeridian)object;
/* 296 */       return (this.baseDirection.equals(that.baseDirection) && Double.doubleToLongBits(this.meridian) == Double.doubleToLongBits(that.meridian));
/*     */     } 
/* 299 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 307 */     long code = Double.doubleToLongBits(this.meridian);
/* 308 */     return 0xDB662A78 ^ (int)code ^ (int)(code >> 32L) + 37 * this.baseDirection.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 321 */     StringBuilder buffer = new StringBuilder(this.baseDirection.name());
/* 322 */     toLowerCase(buffer, 0);
/* 323 */     buffer.append(" along ");
/* 324 */     double md = Math.abs(this.meridian);
/* 325 */     int mi = (int)md;
/* 326 */     if (md == mi) {
/* 327 */       buffer.append(mi);
/*     */     } else {
/* 329 */       buffer.append(md);
/*     */     } 
/* 331 */     buffer.append(" deg");
/* 332 */     if (md != 0.0D && mi != 180) {
/* 333 */       buffer.append(' ');
/* 334 */       int base = buffer.length();
/* 335 */       AxisDirection sign = (this.meridian < 0.0D) ? AxisDirection.WEST : AxisDirection.EAST;
/* 336 */       buffer.append(sign.name());
/* 337 */       toLowerCase(buffer, base);
/*     */     } 
/* 339 */     String name = buffer.toString();
/* 340 */     assert EPSG.matcher(name).matches() : name;
/* 341 */     return name;
/*     */   }
/*     */   
/*     */   private static void toLowerCase(StringBuilder buffer, int base) {
/* 349 */     for (int i = buffer.length(); --i > base;)
/* 350 */       buffer.setCharAt(i, Character.toLowerCase(buffer.charAt(i))); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DirectionAlongMeridian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */