/*     */ package org.java.plugin.registry;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public final class Version implements Serializable, Comparable<Version> {
/*     */   private static final long serialVersionUID = -3054349171116917643L;
/*     */   
/*     */   public static final char SEPARATOR = '.';
/*     */   
/*     */   private transient int major;
/*     */   
/*     */   private transient int minor;
/*     */   
/*     */   private transient int build;
/*     */   
/*     */   private transient String name;
/*     */   
/*     */   private transient String asString;
/*     */   
/*     */   public static Version parse(String str) {
/*  49 */     Version result = new Version();
/*  50 */     result.parseString(str);
/*  51 */     return result;
/*     */   }
/*     */   
/*     */   private Version() {}
/*     */   
/*     */   private void parseString(String str) {
/*  65 */     this.major = 0;
/*  66 */     this.minor = 0;
/*  67 */     this.build = 0;
/*  68 */     this.name = "";
/*  69 */     StringTokenizer st = new StringTokenizer(str, ".", false);
/*  71 */     if (!st.hasMoreTokens())
/*     */       return; 
/*  74 */     String token = st.nextToken();
/*     */     try {
/*  76 */       this.major = Integer.parseInt(token, 10);
/*  77 */     } catch (NumberFormatException nfe) {
/*  78 */       this.name = token;
/*  79 */       while (st.hasMoreTokens())
/*  80 */         this.name += st.nextToken(); 
/*     */       return;
/*     */     } 
/*  85 */     if (!st.hasMoreTokens())
/*     */       return; 
/*  88 */     token = st.nextToken();
/*     */     try {
/*  90 */       this.minor = Integer.parseInt(token, 10);
/*  91 */     } catch (NumberFormatException nfe) {
/*  92 */       this.name = token;
/*  93 */       while (st.hasMoreTokens())
/*  94 */         this.name += st.nextToken(); 
/*     */       return;
/*     */     } 
/*  99 */     if (!st.hasMoreTokens())
/*     */       return; 
/* 102 */     token = st.nextToken();
/*     */     try {
/* 104 */       this.build = Integer.parseInt(token, 10);
/* 105 */     } catch (NumberFormatException nfe) {
/* 106 */       this.name = token;
/* 107 */       while (st.hasMoreTokens())
/* 108 */         this.name += st.nextToken(); 
/*     */       return;
/*     */     } 
/* 113 */     if (st.hasMoreTokens()) {
/* 114 */       this.name = st.nextToken();
/* 115 */       while (st.hasMoreTokens())
/* 116 */         this.name += st.nextToken(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Version(int aMajor, int aMinor, int aBuild, String aName) {
/* 132 */     this.major = aMajor;
/* 133 */     this.minor = aMinor;
/* 134 */     this.build = aBuild;
/* 135 */     this.name = (aName == null) ? "" : aName;
/*     */   }
/*     */   
/*     */   public int getBuild() {
/* 142 */     return this.build;
/*     */   }
/*     */   
/*     */   public int getMajor() {
/* 149 */     return this.major;
/*     */   }
/*     */   
/*     */   public int getMinor() {
/* 156 */     return this.minor;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 163 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isGreaterOrEqualTo(Version other) {
/* 184 */     if (other == null)
/* 185 */       return false; 
/* 187 */     if (this.major > other.major)
/* 188 */       return true; 
/* 190 */     if (this.major == other.major && this.minor > other.minor)
/* 191 */       return true; 
/* 193 */     if (this.major == other.major && this.minor == other.minor && this.build > other.build)
/* 195 */       return true; 
/* 197 */     if (this.major == other.major && this.minor == other.minor && this.build == other.build && this.name.equalsIgnoreCase(other.name))
/* 200 */       return true; 
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCompatibleWith(Version other) {
/* 222 */     if (other == null)
/* 223 */       return false; 
/* 225 */     if (this.major != other.major)
/* 226 */       return false; 
/* 228 */     if (this.minor > other.minor)
/* 229 */       return true; 
/* 231 */     if (this.minor < other.minor)
/* 232 */       return false; 
/* 234 */     if (this.build >= other.build)
/* 235 */       return true; 
/* 237 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEquivalentTo(Version other) {
/* 254 */     if (other == null)
/* 255 */       return false; 
/* 257 */     if (this.major != other.major)
/* 258 */       return false; 
/* 260 */     if (this.minor != other.minor)
/* 261 */       return false; 
/* 263 */     if (this.build >= other.build)
/* 264 */       return true; 
/* 266 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isGreaterThan(Version other) {
/* 279 */     if (other == null)
/* 280 */       return false; 
/* 282 */     if (this.major > other.major)
/* 283 */       return true; 
/* 285 */     if (this.major < other.major)
/* 286 */       return false; 
/* 288 */     if (this.minor > other.minor)
/* 289 */       return true; 
/* 291 */     if (this.minor < other.minor)
/* 292 */       return false; 
/* 294 */     if (this.build > other.build)
/* 295 */       return true; 
/* 297 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 306 */     return toString().hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 314 */     if (this == obj)
/* 315 */       return true; 
/* 317 */     if (!(obj instanceof Version))
/* 318 */       return false; 
/* 320 */     Version other = (Version)obj;
/* 321 */     if (this.major != other.major || this.minor != other.minor || this.build != other.build || !this.name.equalsIgnoreCase(other.name))
/* 324 */       return false; 
/* 326 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 337 */     if (this.asString == null)
/* 338 */       this.asString = "" + this.major + '.' + this.minor + '.' + this.build + ((this.name.length() == 0) ? "" : ('.' + this.name)); 
/* 341 */     return this.asString;
/*     */   }
/*     */   
/*     */   public int compareTo(Version obj) {
/* 350 */     if (equals(obj))
/* 351 */       return 0; 
/* 353 */     if (this.major != obj.major)
/* 354 */       return this.major - obj.major; 
/* 356 */     if (this.minor != obj.minor)
/* 357 */       return this.minor - obj.minor; 
/* 359 */     if (this.build != obj.build)
/* 360 */       return this.build - obj.build; 
/* 362 */     return this.name.toLowerCase(Locale.ENGLISH).compareTo(obj.name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 369 */     out.writeUTF(toString());
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 373 */     parseString(in.readUTF());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\Version.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */