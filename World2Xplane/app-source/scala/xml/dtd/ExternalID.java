/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ import scala.xml.parsing.TokenTests;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0253Q!\001\002\002\002%\021!\"\022=uKJt\027\r\\%E\025\t\031A!A\002ei\022T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\0312\001\001\006\017!\tYA\"D\001\007\023\tiaA\001\004B]f\024VM\032\t\003\037Ii\021\001\005\006\003#\021\tq\001]1sg&tw-\003\002\024!\tQAk\\6f]R+7\017^:\t\013U\001A\021\001\f\002\rqJg.\033;?)\0059\002C\001\r\001\033\005\021\001\"\002\016\001\t\003Y\022AB9v_R,G\r\006\002\035IA\021QDI\007\002=)\021q\004I\001\005Y\006twMC\001\"\003\021Q\027M^1\n\005\rr\"AB*ue&tw\rC\003&3\001\007a%A\001t!\t9#F\004\002\fQ%\021\021FB\001\007!J,G-\0324\n\005\rZ#BA\025\007\021\025i\003\001\"\021/\003!!xn\025;sS:<G#\001\024\t\013A\002A\021A\031\002\027\t,\030\016\0343TiJLgn\032\013\003ey\002\"aM\036\017\005QJdBA\0339\033\0051$BA\034\t\003\031a$o\\8u}%\tq!\003\002;\r\0059\001/Y2lC\036,\027B\001\037>\0055\031FO]5oO\n+\030\016\0343fe*\021!H\002\005\006=\002\rAM\001\003g\nDQ!\021\001\007\002\t\013\001b]=ti\026l\027\nZ\013\002M!)A\t\001D\001\005\006A\001/\0362mS\016LE\r")
/*     */ public abstract class ExternalID implements TokenTests {
/*     */   public final boolean isSpace(char ch) {
/*  17 */     return TokenTests.class.isSpace(this, ch);
/*     */   }
/*     */   
/*     */   public final boolean isSpace(Seq cs) {
/*  17 */     return TokenTests.class.isSpace(this, cs);
/*     */   }
/*     */   
/*     */   public boolean isAlpha(char c) {
/*  17 */     return TokenTests.class.isAlpha(this, c);
/*     */   }
/*     */   
/*     */   public boolean isAlphaDigit(char c) {
/*  17 */     return TokenTests.class.isAlphaDigit(this, c);
/*     */   }
/*     */   
/*     */   public boolean isNameChar(char ch) {
/*  17 */     return TokenTests.class.isNameChar(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isNameStart(char ch) {
/*  17 */     return TokenTests.class.isNameStart(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isName(String s) {
/*  17 */     return TokenTests.class.isName(this, s);
/*     */   }
/*     */   
/*     */   public boolean isPubIDChar(char ch) {
/*  17 */     return TokenTests.class.isPubIDChar(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isValidIANAEncoding(Seq ianaEncoding) {
/*  17 */     return TokenTests.class.isValidIANAEncoding(this, ianaEncoding);
/*     */   }
/*     */   
/*     */   public boolean checkSysID(String s) {
/*  17 */     return TokenTests.class.checkSysID(this, s);
/*     */   }
/*     */   
/*     */   public boolean checkPubID(String s) {
/*  17 */     return TokenTests.class.checkPubID(this, s);
/*     */   }
/*     */   
/*     */   public ExternalID() {
/*  17 */     TokenTests.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String quoted(String s) {
/*  20 */     Predef$ predef$ = Predef$.MODULE$;
/*  20 */     char c = (new StringOps(s)).contains(BoxesRunTime.boxToCharacter('"')) ? '\'' : '"';
/*  21 */     return (new StringBuilder()).append(c).append(s).append(BoxesRunTime.boxToCharacter(c)).toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  27 */     ObjectRef quotedSystemLiteral$lzy = new ObjectRef(null);
/*  28 */     ObjectRef quotedPublicLiteral$lzy = new ObjectRef(null);
/* 188 */     VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/*     */     return (publicId() == null) ? (new StringBuilder()).append("SYSTEM ").append(quotedSystemLiteral$1(quotedSystemLiteral$lzy, bitmap$0)).toString() : (new StringBuilder()).append("PUBLIC ").append(quotedPublicLiteral$1(quotedPublicLiteral$lzy, bitmap$0)).append((systemId() == null) ? "" : (new StringBuilder()).append(" ").append(quotedSystemLiteral$1(quotedSystemLiteral$lzy, bitmap$0)).toString()).toString();
/*     */   }
/*     */   
/*     */   private final String quotedSystemLiteral$lzycompute$1(ObjectRef quotedSystemLiteral$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     synchronized (this) {
/* 188 */       if ((byte)(bitmap$0$1.elem & 0x1) == 0) {
/*     */         quotedSystemLiteral$lzy$1.elem = quoted(systemId());
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/dtd/ExternalID}} */
/*     */       return (String)quotedSystemLiteral$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String quotedSystemLiteral$1(ObjectRef quotedSystemLiteral$lzy$1, VolatileByteRef bitmap$0$1) {
/* 188 */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? quotedSystemLiteral$lzycompute$1(quotedSystemLiteral$lzy$1, bitmap$0$1) : (String)quotedSystemLiteral$lzy$1.elem;
/*     */   }
/*     */   
/*     */   private final String quotedPublicLiteral$lzycompute$1(ObjectRef quotedPublicLiteral$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     synchronized (this) {
/* 188 */       if ((byte)(bitmap$0$1.elem & 0x2) == 0) {
/*     */         quotedPublicLiteral$lzy$1.elem = quoted(publicId());
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x2);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/dtd/ExternalID}} */
/*     */       return (String)quotedPublicLiteral$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String quotedPublicLiteral$1(ObjectRef quotedPublicLiteral$lzy$1, VolatileByteRef bitmap$0$1) {
/* 188 */     return ((byte)(bitmap$0$1.elem & 0x2) == 0) ? quotedPublicLiteral$lzycompute$1(quotedPublicLiteral$lzy$1, bitmap$0$1) : (String)quotedPublicLiteral$lzy$1.elem;
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/*     */     return sb.append(toString());
/*     */   }
/*     */   
/*     */   public abstract String systemId();
/*     */   
/*     */   public abstract String publicId();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ExternalID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */