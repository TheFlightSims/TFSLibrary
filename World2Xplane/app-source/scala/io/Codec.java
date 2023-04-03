/*     */ package scala.io;
/*     */ 
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005a\001B\001\003\001\035\021QaQ8eK\016T!a\001\003\002\005%|'\"A\003\002\013M\034\027\r\\1\004\001M\021\001\001\003\t\003\023)i\021\001B\005\003\027\021\021a!\0218z%\0264\007\002C\007\001\005\013\007I\021\001\b\002\017\rD\027M]*fiV\tq\002\005\002\021/5\t\021C\003\002\023'\00591\r[1sg\026$(B\001\013\026\003\rq\027n\034\006\002-\005!!.\031<b\023\tA\022CA\004DQ\006\0248/\032;\t\021i\001!\021!Q\001\n=\t\001b\0315beN+G\017\t\005\0069\001!\t!H\001\007y%t\027\016\036 \025\005y\001\003CA\020\001\033\005\021\001\"B\007\034\001\004yQ\001\002\022\001\001\r\022\021bQ8oM&<WO]3\026\005\021b\003\003B\005&OUJ!A\n\003\003\rQ+\b\017\\33!\021I\001F\013\026\n\005%\"!!\003$v]\016$\030n\03482!\tYC\006\004\001\005\0135\n#\031\001\030\003\003Q\013\"a\f\032\021\005%\001\024BA\031\005\005\035qu\016\0365j]\036\004\"!C\032\n\005Q\"!aA!osB\021\021BN\005\003o\021\021qAQ8pY\026\fg.\002\003:\001\001Q$a\002%b]\022dWM\035\t\005\023!Zd\b\005\002\021y%\021Q(\005\002\031\007\"\f'/Y2uKJ\034u\016Z5oO\026C8-\0329uS>t\007CA\005@\023\t\001EAA\002J]RDaA\021\001!B\023\031\025!E0p]6\013GNZ8s[\026$\027J\0349viB\021\001\003R\005\003\013F\021\021cQ8eS:<WI\035:pe\006\033G/[8o\021\0319\005\001)Q\005\007\0061rl\0348V]6\f\007\017]1cY\026\034\005.\031:bGR,'\017\003\004J\001\001\006KAS\001\025?\026t7m\0343j]\036\024V\r\0357bG\026lWM\034;\021\007%YU*\003\002M\t\t)\021I\035:bsB\021\021BT\005\003\037\022\021AAQ=uK\"1\021\013\001Q!\nI\013Ac\0303fG>$\027N\\4SKBd\027mY3nK:$\bCA*W\035\tIA+\003\002V\t\0051\001K]3eK\032L!a\026-\003\rM#(/\0338h\025\t)F\001\003\004[\001\001\006KaW\001\023?>t7i\0343j]\036,\005pY3qi&|g\016\005\002]q5\t\001\001C\003_\001\021\005s,\001\005u_N#(/\0338h)\005\001\007CA1e\033\005\021'BA2\026\003\021a\027M\\4\n\005]\023\007\"\0024\001\t\0039\027\001E8o\033\006dgm\034:nK\022Le\016];u)\ta\006\016C\003jK\002\0071)A\005oK^\f5\r^5p]\")1\016\001C\001Y\006)rN\\+o[\006\004\b/\0312mK\016C\027M]1di\026\024HC\001/n\021\025I'\0161\001D\021\025y\007\001\"\001q\003M!WmY8eS:<'+\0329mC\016,w+\033;i)\ta\026\017C\003s]\002\007!+\001\boK^\024V\r\0357bG\026lWM\034;\t\013Q\004A\021A;\002'\025t7m\0343j]\036\024V\r\0357bG\026<\026\016\0365\025\005q3\b\"\002:t\001\004Q\005\"\002=\001\t\003I\030!E8o\007>$\027N\\4Fq\016,\007\017^5p]R\021AL\037\005\006w^\004\raW\001\bQ\006tG\r\\3s\021\025i\b\001\"\001\003\021q\027-\\3\026\003\001Dq!!\001\001\t\003\t\031!A\004f]\016|G-\032:\026\005\005\025\001c\001\t\002\b%\031\021\021B\t\003\035\rC\027M]:fi\026s7m\0343fe\"9\021Q\002\001\005\002\005=\021a\0023fG>$WM]\013\003\003#\0012\001EA\n\023\r\t)\"\005\002\017\007\"\f'o]3u\t\026\034w\016Z3s\021\035\tI\002\001C\001\0037\tAa\036:baR\031a(!\b\t\023\005}\021q\003CA\002\005\005\022\001\0022pIf\004B!CA\022}%\031\021Q\005\003\003\021q\022\027P\\1nKzBq!!\013\001\t\023\tY#\001\bbaBd\027PR;oGRLwN\\:\026\t\0055\022\021\007\013\007\003_\t\031$a\016\021\007-\n\t\004\002\004.\003O\021\rA\f\005\t\003k\t9\0031\001\0020\005\t\001\020\003\005\002:\005\035\002\031AA\036\003\t17\017E\003\n\003{\t\t%C\002\002@\021\021!\002\020:fa\026\fG/\0323?!\021a\026%a\f\b\017\005\025#\001#\001\002H\005)1i\0343fGB\031q$!\023\007\r\005\021\001\022AA&'\025\tI\005CA'!\ry\022qJ\005\004\003#\022!!\007'poB\023\030n\034:jif\034u\016Z3d\0236\004H.[2jiNDq\001HA%\t\003\t)\006\006\002\002H!Q\021\021LA%\005\004%)!a\027\002\017%\033v\n\017\0356sU\ta\004\003\005\002`\005%\003\025!\004\037\003!I5k\024\0359ke\002\003BCA2\003\023\022\r\021\"\002\002\\\005!Q\013\026$9\021!\t9'!\023!\002\033q\022!B+U\rb\002\003\002CA6\003\023\"\t!a\027\002'\021,g-Y;mi\016C\027M]:fi\016{G-Z2\t\021\005=\024\021\nC\001\0037\n\021CZ5mK\026s7m\0343j]\036\034u\016Z3d\021!\t\031(!\023\005\002\005m\023a\0023fM\006,H\016\036\005\t\003o\nI\005\"\001\002z\005)\021\r\0359msR\031a$a\037\t\017\005u\024Q\017a\001%\006AQM\\2pI&tw\r\003\005\002x\005%C\021AAA)\rq\0221\021\005\007\033\005}\004\031A\b\t\021\005]\024\021\nC\001\003\017#2AHAE\021!\ti!!\"A\002\005E\001\002CAG\003\023\"\t!a$\002\021\031\024x.\\+U\rb\"B!!%\002\032B!\021bSAJ!\rI\021QS\005\004\003/#!\001B\"iCJDq!a'\002\f\002\007!*A\003csR,7\017\013\005\002\f\006}\0251VAX!\021\t\t+a*\016\005\005\r&bAAS\t\005Q\021M\0348pi\006$\030n\0348\n\t\005%\0261\025\002\n[&<'/\031;j_:\f#!!,\002/RC\027n\035\021nKRDw\016\032\021xCN\004\003O]3wS>,8\017\\=![&\034h.Y7fI\002\002Go\\+U\rb\002g\006I\"p]Z,'\017^:!MJ|W\016I!se\006L8LQ=uKv\003Co\034\021BeJ\f\027pW\"iCJlf&\t\002\0022\006)!GL\035/a!A\021QRA%\t\003\t)\f\006\005\002\022\006]\026\021XA_\021\035\tY*a-A\002)Cq!a/\0024\002\007a(\001\004pM\032\034X\r\036\005\b\003\013\031\f1\001?\003\raWM\034\005\t\003\007\fI\005\"\001\002F\0061Ao\\+U\rb\"2ASAd\021!\tI-!1A\002\005-\027AA2t!\r\t\027QZ\005\004\003\037\024'\001D\"iCJ\034V-];f]\016,\007\006CAa\003?\013\031.a,\"\005\005U\027\001\031+iSN\004S.\032;i_\022\004s/Y:!aJ,g/[8vg2L\b%\\5t]\006lW\r\032\021aMJ|W.\026+Gq\001t\003eQ8om\026\024Ho\035\021ge>l\007e\0315be\006\034G/\032:!g\026\fX/\0328dK\002\"x\016I!se\006L8LQ=uKvs\003\002CAb\003\023\"\t!!7\025\017)\013Y.a8\002b\"A\021Q\\Al\001\004\t\t*A\003dQ\006\0248\017C\004\002<\006]\007\031\001 \t\017\005}\026q\033a\001}!A\021Q]A%\t\007\t9/\001\007tiJLgn\032\032d_\022,7\rF\002\037\003SDq!a;\002d\002\007!+A\001t\021!\ty/!\023\005\004\005E\030!D2iCJ\034X\r\036\032d_\022,7\rF\002\037\003gDq!!>\002n\002\007q\"A\001d\021!\tI0!\023\005\004\005m\030!\0043fG>$WM\035\032d_\022,7\rF\002\037\003{D\001\"a@\002x\002\007\021\021C\001\003G\022\004")
/*     */ public class Codec {
/*     */   private final Charset charSet;
/*     */   
/*     */   public static Codec fallbackSystemCodec() {
/*     */     return Codec$.MODULE$.fallbackSystemCodec();
/*     */   }
/*     */   
/*     */   public static Codec decoder2codec(CharsetDecoder paramCharsetDecoder) {
/*     */     return Codec$.MODULE$.decoder2codec(paramCharsetDecoder);
/*     */   }
/*     */   
/*     */   public static Codec charset2codec(Charset paramCharset) {
/*     */     return Codec$.MODULE$.charset2codec(paramCharset);
/*     */   }
/*     */   
/*     */   public static Codec string2codec(String paramString) {
/*     */     return Codec$.MODULE$.string2codec(paramString);
/*     */   }
/*     */   
/*     */   public static byte[] toUTF8(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
/*     */     return Codec$.MODULE$.toUTF8(paramArrayOfchar, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static byte[] toUTF8(CharSequence paramCharSequence) {
/*     */     return Codec$.MODULE$.toUTF8(paramCharSequence);
/*     */   }
/*     */   
/*     */   public static char[] fromUTF8(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
/*     */     return Codec$.MODULE$.fromUTF8(paramArrayOfbyte, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static char[] fromUTF8(byte[] paramArrayOfbyte) {
/*     */     return Codec$.MODULE$.fromUTF8(paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static Codec apply(CharsetDecoder paramCharsetDecoder) {
/*     */     return Codec$.MODULE$.apply(paramCharsetDecoder);
/*     */   }
/*     */   
/*     */   public static Codec apply(Charset paramCharset) {
/*     */     return Codec$.MODULE$.apply(paramCharset);
/*     */   }
/*     */   
/*     */   public static Codec apply(String paramString) {
/*     */     return Codec$.MODULE$.apply(paramString);
/*     */   }
/*     */   
/*     */   public static Codec default() {
/*     */     return Codec$.MODULE$.default();
/*     */   }
/*     */   
/*     */   public static Codec fileEncodingCodec() {
/*     */     return Codec$.MODULE$.fileEncodingCodec();
/*     */   }
/*     */   
/*     */   public static Codec defaultCharsetCodec() {
/*     */     return Codec$.MODULE$.defaultCharsetCodec();
/*     */   }
/*     */   
/*     */   public static Codec UTF8() {
/*     */     return Codec$.MODULE$.UTF8();
/*     */   }
/*     */   
/*     */   public static Codec ISO8859() {
/*     */     return Codec$.MODULE$.ISO8859();
/*     */   }
/*     */   
/*     */   public Charset charSet() {
/*  30 */     return this.charSet;
/*     */   }
/*     */   
/*  36 */   public CodingErrorAction scala$io$Codec$$_onMalformedInput = null;
/*     */   
/*  37 */   public CodingErrorAction scala$io$Codec$$_onUnmappableCharacter = null;
/*     */   
/*  38 */   public byte[] scala$io$Codec$$_encodingReplacement = null;
/*     */   
/*  39 */   public String scala$io$Codec$$_decodingReplacement = null;
/*     */   
/*  40 */   private Function1<CharacterCodingException, Object> _onCodingException = (Function1<CharacterCodingException, Object>)new $anonfun$1(this);
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1<CharacterCodingException, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply(CharacterCodingException e) {
/*  40 */       throw e;
/*     */     }
/*     */     
/*     */     public $anonfun$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public String toString() {
/*  43 */     return name();
/*     */   }
/*     */   
/*     */   public Codec onMalformedInput(CodingErrorAction newAction) {
/*  46 */     this.scala$io$Codec$$_onMalformedInput = newAction;
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   public Codec onUnmappableCharacter(CodingErrorAction newAction) {
/*  47 */     this.scala$io$Codec$$_onUnmappableCharacter = newAction;
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public Codec decodingReplaceWith(String newReplacement) {
/*  48 */     this.scala$io$Codec$$_decodingReplacement = newReplacement;
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public Codec encodingReplaceWith(byte[] newReplacement) {
/*  49 */     this.scala$io$Codec$$_encodingReplacement = newReplacement;
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public Codec onCodingException(Function1<CharacterCodingException, Object> handler) {
/*  50 */     this._onCodingException = handler;
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public String name() {
/*  52 */     return charSet().name();
/*     */   }
/*     */   
/*     */   public CharsetEncoder encoder() {
/*  54 */     (new Tuple2[3])[0] = 
/*  55 */       new Tuple2(new Codec$$anonfun$encoder$1(this), BoxesRunTime.boxToBoolean(!(this.scala$io$Codec$$_onMalformedInput == null)));
/*  56 */     (new Tuple2[3])[1] = new Tuple2(new Codec$$anonfun$encoder$2(this), BoxesRunTime.boxToBoolean(!(this.scala$io$Codec$$_onUnmappableCharacter == null)));
/*  57 */     (new Tuple2[3])[2] = new Tuple2(new Codec$$anonfun$encoder$3(this), BoxesRunTime.boxToBoolean((this.scala$io$Codec$$_encodingReplacement != null)));
/*     */     return applyFunctions(charSet().newEncoder(), (Seq<Tuple2<Function1<CharsetEncoder, CharsetEncoder>, Object>>)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[3]));
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$1 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$1) {
/*     */       return x$1.onMalformedInput(this.$outer.scala$io$Codec$$_onMalformedInput);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$2 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$2) {
/*     */       return x$2.onUnmappableCharacter(this.$outer.scala$io$Codec$$_onUnmappableCharacter);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$2(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$3 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$3) {
/*  57 */       return x$3.replaceWith(this.$outer.scala$io$Codec$$_encodingReplacement);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$3(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public CharsetDecoder decoder() {
/*  61 */     (new Tuple2[3])[0] = 
/*  62 */       new Tuple2(new Codec$$anonfun$decoder$1(this), BoxesRunTime.boxToBoolean(!(this.scala$io$Codec$$_onMalformedInput == null)));
/*  63 */     (new Tuple2[3])[1] = new Tuple2(new Codec$$anonfun$decoder$2(this), BoxesRunTime.boxToBoolean(!(this.scala$io$Codec$$_onUnmappableCharacter == null)));
/*  64 */     (new Tuple2[3])[2] = new Tuple2(new Codec$$anonfun$decoder$3(this), BoxesRunTime.boxToBoolean(!(this.scala$io$Codec$$_decodingReplacement == null)));
/*     */     return applyFunctions(charSet().newDecoder(), (Seq<Tuple2<Function1<CharsetDecoder, CharsetDecoder>, Object>>)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[3]));
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$1 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$4) {
/*     */       return x$4.onMalformedInput(this.$outer.scala$io$Codec$$_onMalformedInput);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$2 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$5) {
/*     */       return x$5.onUnmappableCharacter(this.$outer.scala$io$Codec$$_onUnmappableCharacter);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$2(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$3 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$6) {
/*  64 */       return x$6.replaceWith(this.$outer.scala$io$Codec$$_decodingReplacement);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$3(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public int wrap(Function0 body) {
/*     */     try {
/*     */     
/*  68 */     } catch (CharacterCodingException characterCodingException) {}
/*  68 */     return BoxesRunTime.unboxToInt(this._onCodingException.apply(characterCodingException));
/*     */   }
/*     */   
/*     */   private <T> T applyFunctions(Object x, Seq fs) {
/*  72 */     return (T)fs.foldLeft(x, (Function2)new Codec$$anonfun$applyFunctions$1(this));
/*     */   }
/*     */   
/*     */   public Codec(Charset charSet) {}
/*     */   
/*     */   public class Codec$$anonfun$applyFunctions$1 extends AbstractFunction2<T, Tuple2<Function1<T, T>, Object>, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final T apply(Object x, Tuple2 pair) {
/*  72 */       if (pair != null)
/*  72 */         return 
/*  73 */           pair._2$mcZ$sp() ? (T)((Function1)pair._1()).apply(x) : (T)x; 
/*     */       throw new MatchError(pair);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$applyFunctions$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public static class Codec$$anon$1 extends Codec {
/*     */     private final CharsetDecoder _decoder$1;
/*     */     
/*     */     public CharsetDecoder decoder() {
/* 101 */       return this._decoder$1;
/*     */     }
/*     */     
/*     */     public Codec$$anon$1(CharsetDecoder decoder$1, CharsetDecoder _decoder$1) {
/* 101 */       super(decoder$1.charset());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Codec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */