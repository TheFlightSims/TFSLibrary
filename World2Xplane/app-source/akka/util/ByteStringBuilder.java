/*     */ package akka.util;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.VectorBuilder;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tee\001B\001\003\005\035\021\021CQ=uKN#(/\0338h\005VLG\016Z3s\025\t\031A!\001\003vi&d'\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\t=!b#G\007\002!)\021\021CE\001\b[V$\030M\0317f\025\t\031\"\"\001\006d_2dWm\031;j_:L!!\006\t\003\017\t+\030\016\0343feB\021\021bF\005\0031)\021AAQ=uKB\021!dG\007\002\005%\021AD\001\002\013\005f$Xm\025;sS:<\007\"\002\020\001\t\003y\022A\002\037j]&$h\bF\001!!\tQ\002\001C\004#\001\001\007I\021B\022\002\017}cWM\\4uQV\tA\005\005\002\nK%\021aE\003\002\004\023:$\bb\002\025\001\001\004%I!K\001\f?2,gn\032;i?\022*\027\017\006\002+[A\021\021bK\005\003Y)\021A!\0268ji\"9afJA\001\002\004!\023a\001=%c!1\001\007\001Q!\n\021\n\001b\0307f]\036$\b\016\t\005\be\001\021\r\021\"\0034\003!y&-^5mI\026\024X#\001\033\021\007UB$(D\0017\025\t9$#A\005j[6,H/\0312mK&\021\021H\016\002\016-\026\034Go\034:Ck&dG-\032:\021\005mrdB\001\016=\023\ti$!\001\006CsR,7\013\036:j]\036L!a\020!\003\027\tKH/Z*ue&tw-\r\006\003{\tAaA\021\001!\002\023!\024!C0ck&dG-\032:!\021%!\005\0011AA\002\023%Q)A\003`i\026l\007/F\001G!\rIqIF\005\003\021*\021Q!\021:sCfD\021B\023\001A\002\003\007I\021B&\002\023}#X-\0349`I\025\fHC\001\026M\021\035q\023*!AA\002\031CaA\024\001!B\0231\025AB0uK6\004\b\005C\004Q\001\001\007I\021B\022\002\027}#X-\0349MK:<G\017\033\005\b%\002\001\r\021\"\003T\003=yF/Z7q\031\026tw\r\0365`I\025\fHC\001\026U\021\035q\023+!AA\002\021BaA\026\001!B\023!\023\001D0uK6\004H*\0328hi\"\004\003b\002-\001\001\004%IaI\001\016?R,W\016]\"ba\006\034\027\016^=\t\017i\003\001\031!C\0057\006\tr\f^3na\016\013\007/Y2jif|F%Z9\025\005)b\006b\002\030Z\003\003\005\r\001\n\005\007=\002\001\013\025\002\023\002\035}#X-\0349DCB\f7-\033;zA!)\001\r\001C\tC\006Ia-\0337m\003J\024\030-\037\013\003E&$\"a\0313\016\003\001AQ!Z0A\002\031\fAAZ5mYB)\021b\032$%U%\021\001N\003\002\n\rVt7\r^5p]JBQA[0A\002\021\n1\001\\3o\021\025a\007\001\"\006n\00391\027\016\0347CsR,')\0364gKJ$2A\\>})\t\031w\016C\003fW\002\007\001\017\005\003\ncNT\023B\001:\013\005%1UO\\2uS>t\027\007\005\002us6\tQO\003\002wo\006\031a.[8\013\003a\fAA[1wC&\021!0\036\002\013\005f$XMQ;gM\026\024\b\"\0026l\001\004!\003\"B?l\001\004q\030!\0032zi\026|%\017Z3s!\t!x0C\002\002\002U\024\021BQ=uK>\023H-\032:)\007-\f)\001E\002\n\003\017I1!!\003\013\005\031Ig\016\\5oK\"1\021Q\002\001\005\002\r\na\001\\3oORD\007bBA\t\001\021\005\0231C\001\tg&TX\rS5oiR\031!&!\006\t\r)\fy\0011\001%\021\035\tI\002\001C\005\0037\t\021b\0317fCJ$V-\0349\025\003)Bq!a\b\001\t\023\t\t#\001\006sKNL'0\032+f[B$2AKA\022\021\035\t)#!\bA\002\021\nAa]5{K\"9\021\021\006\001\005\n\005-\022AD3ogV\024X\rV3naNK'0\032\013\004U\0055\002bBA\023\003O\001\r\001\n\005\b\003c\001A\021AA\032\003!!\003\017\\;tI\025\fHcA2\0026!9\021qGA\030\001\0041\022\001B3mK6Dq!a\017\001\t\003\ni$A\007%a2,8\017\n9mkN$S-\035\013\004G\006}\002\002CA!\003s\001\r!a\021\002\005a\034\b#BA#\003+2b\002BA$\003#rA!!\023\002P5\021\0211\n\006\004\003\0332\021A\002\037s_>$h(C\001\f\023\r\t\031FC\001\ba\006\0347.Y4f\023\021\t9&!\027\003\037Q\023\030M^3sg\006\024G.Z(oG\026T1!a\025\013\021!\ti\006\001C\001\t\005}\023A\0059vi\nKH/Z!se\006LXK\\:bM\026$2aYA1\021\035\t\t%a\027A\002\031Cq!!\032\001\t\003\t9'\001\004baB,g\016\032\013\004G\006%\004bBA6\003G\002\r!G\001\003ENDq!a\034\001\t\003\t\t(A\004qkR\024\025\020^3\025\007\r\f\031\bC\004\002v\0055\004\031\001\f\002\003aDq!!\037\001\t\003\tY(\001\005qkR\034\006n\034:u)\021\ti(!!\025\007\r\fy\b\003\004~\003o\002\035A \005\b\003k\n9\b1\001%\021\035\t)\t\001C\001\003\017\013a\001];u\023:$H\003BAE\003\033#2aYAF\021\031i\0301\021a\002}\"9\021QOAB\001\004!\003bBAI\001\021\005\0211S\001\baV$Hj\0348h)\021\t)*!'\025\007\r\f9\n\003\004~\003\037\003\035A \005\t\003k\ny\t1\001\002\034B\031\021\"!(\n\007\005}%B\001\003M_:<\007bBAR\001\021\005\021QU\001\faV$Hj\0348h!\006\024H\017\006\004\002(\006-\026Q\026\013\004G\006%\006BB?\002\"\002\017a\020\003\005\002v\005\005\006\031AAN\021\035\ty+!)A\002\021\n\021A\034\005\b\003g\003A\021AA[\003!\001X\017\036$m_\006$H\003BA\\\003w#2aYA]\021\031i\030\021\027a\002}\"A\021QOAY\001\004\ti\fE\002\n\003K1!!1\013\005\0251En\\1u\021\035\t)\r\001C\001\003\017\f\021\002];u\t>,(\r\\3\025\t\005%\027Q\032\013\004G\006-\007BB?\002D\002\017a\020\003\005\002v\005\r\007\031AAh!\rI\021\021[\005\004\003'T!A\002#pk\ndW\rC\004\002X\002!\t!!7\002\021A,HOQ=uKN$2aYAn\021\035\ti.!6A\002\031\013Q!\031:sCfDq!a6\001\t\003\t\t\017F\004d\003G\f)/!;\t\017\005u\027q\034a\001\r\"9\021q]Ap\001\004!\023!B:uCJ$\bB\0026\002`\002\007A\005C\004\002n\002!\t!a<\002\023A,Ho\0255peR\034H\003BAy\003k$2aYAz\021\031i\0301\036a\002}\"A\021Q\\Av\001\004\t9\020\005\003\n\017\006e\bcA\005\002|&\031\021Q \006\003\013MCwN\035;\t\017\0055\b\001\"\001\003\002QA!1\001B\004\005\023\021Y\001F\002d\005\013Aa!`A\000\001\bq\b\002CAo\003\004\r!a>\t\017\005\035\030q a\001I!1!.a@A\002\021BqAa\004\001\t\003\021\t\"A\004qkRLe\016^:\025\t\tM!q\003\013\004G\nU\001BB?\003\016\001\017a\020\003\005\002^\n5\001\031\001B\r!\rIq\t\n\005\b\005\037\001A\021\001B\017)!\021yBa\t\003&\t\035BcA2\003\"!1QPa\007A\004yD\001\"!8\003\034\001\007!\021\004\005\b\003O\024Y\0021\001%\021\031Q'1\004a\001I!9!1\006\001\005\002\t5\022\001\0039vi2{gnZ:\025\t\t=\"1\007\013\004G\nE\002BB?\003*\001\017a\020\003\005\002^\n%\002\031\001B\033!\021Iq)a'\t\017\t-\002\001\"\001\003:QA!1\bB \005\003\022\031\005F\002d\005{Aa! B\034\001\bq\b\002CAo\005o\001\rA!\016\t\017\005\035(q\007a\001I!1!Na\016A\002\021BqAa\022\001\t\003\021I%A\005qkR4En\\1ugR!!1\nB()\r\031'Q\n\005\007{\n\025\0039\001@\t\021\005u'Q\ta\001\005#\002B!C$\002>\"9!q\t\001\005\002\tUC\003\003B,\0057\022iFa\030\025\007\r\024I\006\003\004~\005'\002\035A \005\t\003;\024\031\0061\001\003R!9\021q\035B*\001\004!\003B\0026\003T\001\007A\005C\004\003d\001!\tA!\032\002\025A,H\017R8vE2,7\017\006\003\003h\t-DcA2\003j!1QP!\031A\004yD\001\"!8\003b\001\007!Q\016\t\005\023\035\013y\rC\004\003d\001!\tA!\035\025\021\tM$q\017B=\005w\"2a\031B;\021\031i(q\016a\002}\"A\021Q\034B8\001\004\021i\007C\004\002h\n=\004\031\001\023\t\r)\024y\0071\001%\021\035\021y\b\001C\001\0037\tQa\0317fCJDqAa!\001\t\003\021))\001\004sKN,H\016\036\013\0023!9!\021\022\001\005\002\t-\025AD1t\037V$\b/\036;TiJ,\027-\\\013\003\005\033\003BAa$\003\0266\021!\021\023\006\004\005';\030AA5p\023\021\0219J!%\003\031=+H\017];u'R\024X-Y7")
/*     */ public final class ByteStringBuilder implements Builder<Object, ByteString> {
/*     */   private int _length;
/*     */   
/*     */   private final VectorBuilder<ByteString.ByteString1> _builder;
/*     */   
/*     */   private byte[] _temp;
/*     */   
/*     */   private int _tempLength;
/*     */   
/*     */   private int _tempCapacity;
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/* 489 */     Builder.class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/* 489 */     Builder.class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 489 */     Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<Object, NewTo> mapResult(Function1 f) {
/* 489 */     return Builder.class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<Object> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 489 */     return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder() {
/* 489 */     Growable.class.$init$((Growable)this);
/* 489 */     Builder.class.$init$(this);
/* 493 */     this._length = 0;
/* 494 */     this._builder = new VectorBuilder();
/* 496 */     this._tempLength = 0;
/* 497 */     this._tempCapacity = 0;
/*     */   }
/*     */   
/*     */   private int _length() {
/*     */     return this._length;
/*     */   }
/*     */   
/*     */   private void _length_$eq(int x$1) {
/*     */     this._length = x$1;
/*     */   }
/*     */   
/*     */   private VectorBuilder<ByteString.ByteString1> _builder() {
/*     */     return this._builder;
/*     */   }
/*     */   
/*     */   private byte[] _temp() {
/*     */     return this._temp;
/*     */   }
/*     */   
/*     */   private void _temp_$eq(byte[] x$1) {
/*     */     this._temp = x$1;
/*     */   }
/*     */   
/*     */   private int _tempLength() {
/*     */     return this._tempLength;
/*     */   }
/*     */   
/*     */   private void _tempLength_$eq(int x$1) {
/*     */     this._tempLength = x$1;
/*     */   }
/*     */   
/*     */   private int _tempCapacity() {
/* 497 */     return this._tempCapacity;
/*     */   }
/*     */   
/*     */   private void _tempCapacity_$eq(int x$1) {
/* 497 */     this._tempCapacity = x$1;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder fillArray(int len, Function2 fill) {
/* 500 */     ensureTempSize(_tempLength() + len);
/* 501 */     fill.apply(_temp(), BoxesRunTime.boxToInteger(_tempLength()));
/* 502 */     _tempLength_$eq(_tempLength() + len);
/* 503 */     _length_$eq(_length() + len);
/* 504 */     return this;
/*     */   }
/*     */   
/*     */   public final ByteStringBuilder fillByteBuffer(int len, ByteOrder byteOrder, Function1 fill) {
/* 508 */     return fillArray(len, (Function2<byte[], Object, BoxedUnit>)new ByteStringBuilder$$anonfun$fillByteBuffer$1(this, len, byteOrder, fill));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$fillByteBuffer$1 extends AbstractFunction2<byte[], Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int len$1;
/*     */     
/*     */     private final ByteOrder byteOrder$1;
/*     */     
/*     */     private final Function1 fill$1;
/*     */     
/*     */     public final void apply(byte[] x0$1, int x1$1) {
/* 508 */       Tuple2 tuple2 = new Tuple2(x0$1, BoxesRunTime.boxToInteger(x1$1));
/* 508 */       if (tuple2 != null) {
/* 509 */         byte[] array = (byte[])tuple2._1();
/* 509 */         int start = tuple2._2$mcI$sp();
/* 510 */         ByteBuffer buffer = ByteBuffer.wrap(array, start, this.len$1);
/* 511 */         buffer.order(this.byteOrder$1);
/* 512 */         BoxedUnit boxedUnit = (BoxedUnit)this.fill$1.apply(buffer);
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$fillByteBuffer$1(ByteStringBuilder $outer, int len$1, ByteOrder byteOrder$1, Function1 fill$1) {}
/*     */   }
/*     */   
/*     */   public int length() {
/* 516 */     return _length();
/*     */   }
/*     */   
/*     */   public void sizeHint(int len) {
/* 519 */     resizeTemp(len - _length() - _tempLength());
/*     */   }
/*     */   
/*     */   private void clearTemp() {
/* 523 */     if (_tempLength() > 0) {
/* 524 */       byte[] arr = new byte[_tempLength()];
/* 525 */       Array$.MODULE$.copy(_temp(), 0, arr, 0, _tempLength());
/* 526 */       _builder().$plus$eq(ByteString.ByteString1$.MODULE$.apply(arr));
/* 527 */       _tempLength_$eq(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void resizeTemp(int size) {
/* 532 */     byte[] newtemp = new byte[size];
/* 533 */     if (_tempLength() > 0)
/* 533 */       Array$.MODULE$.copy(_temp(), 0, newtemp, 0, _tempLength()); 
/* 534 */     _temp_$eq(newtemp);
/* 535 */     _tempCapacity_$eq((_temp()).length);
/*     */   }
/*     */   
/*     */   private void ensureTempSize(int size) {
/* 539 */     if (_tempCapacity() < size || _tempCapacity() == 0) {
/* 540 */       int newSize = (_tempCapacity() == 0) ? 16 : (_tempCapacity() * 2);
/* 541 */       for (; newSize < size; newSize *= 2);
/* 542 */       resizeTemp(newSize);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ByteStringBuilder $plus$eq(byte elem) {
/* 547 */     ensureTempSize(_tempLength() + 1);
/* 548 */     _temp()[_tempLength()] = elem;
/* 549 */     _tempLength_$eq(_tempLength() + 1);
/* 550 */     _length_$eq(_length() + 1);
/* 551 */     return this;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder $plus$plus$eq(TraversableOnce xs) {
/*     */     Growable growable;
/* 555 */     TraversableOnce traversableOnce = xs;
/* 556 */     if (traversableOnce instanceof ByteString.ByteString1C) {
/* 556 */       ByteString.ByteString1C byteString1C = (ByteString.ByteString1C)traversableOnce;
/* 557 */       clearTemp();
/* 558 */       _builder().$plus$eq(byteString1C.toByteString1());
/* 559 */       _length_$eq(_length() + byteString1C.length());
/* 559 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 560 */     } else if (traversableOnce instanceof ByteString.ByteString1) {
/* 560 */       ByteString.ByteString1 byteString1 = (ByteString.ByteString1)traversableOnce;
/* 561 */       clearTemp();
/* 562 */       _builder().$plus$eq(byteString1);
/* 563 */       _length_$eq(_length() + byteString1.length());
/* 563 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 564 */     } else if (traversableOnce instanceof ByteString.ByteStrings) {
/* 564 */       ByteString.ByteStrings byteStrings = (ByteString.ByteStrings)traversableOnce;
/* 565 */       clearTemp();
/* 566 */       _builder().$plus$plus$eq((TraversableOnce)byteStrings.bytestrings());
/* 567 */       _length_$eq(_length() + byteStrings.length());
/* 567 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 568 */     } else if (traversableOnce instanceof WrappedArray.ofByte) {
/* 568 */       WrappedArray.ofByte ofByte = (WrappedArray.ofByte)traversableOnce;
/* 569 */       ByteStringBuilder byteStringBuilder = putByteArrayUnsafe((byte[])ofByte.array().clone());
/* 570 */     } else if (traversableOnce instanceof IndexedSeq) {
/* 570 */       IndexedSeq indexedSeq = (IndexedSeq)traversableOnce;
/* 571 */       ensureTempSize(_tempLength() + xs.size());
/* 572 */       xs.copyToArray(_temp(), _tempLength());
/* 573 */       _tempLength_$eq(_tempLength() + indexedSeq.length());
/* 574 */       _length_$eq(_length() + indexedSeq.length());
/* 574 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 576 */       growable = Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     } 
/* 578 */     return this;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putByteArrayUnsafe(byte[] xs) {
/* 582 */     clearTemp();
/* 583 */     _builder().$plus$eq(ByteString.ByteString1$.MODULE$.apply(xs));
/* 584 */     _length_$eq(_length() + xs.length);
/* 585 */     return this;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder append(ByteString bs) {
/* 591 */     return $plus$plus$eq((TraversableOnce<Object>)bs);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putByte(byte x) {
/* 596 */     return $plus$eq(x);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putShort(int x, ByteOrder byteOrder) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
/*     */     //   4: astore_3
/*     */     //   5: dup
/*     */     //   6: ifnonnull -> 17
/*     */     //   9: pop
/*     */     //   10: aload_3
/*     */     //   11: ifnull -> 24
/*     */     //   14: goto -> 45
/*     */     //   17: aload_3
/*     */     //   18: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   21: ifeq -> 45
/*     */     //   24: aload_0
/*     */     //   25: iload_1
/*     */     //   26: bipush #8
/*     */     //   28: iushr
/*     */     //   29: i2b
/*     */     //   30: invokevirtual $plus$eq : (B)Lakka/util/ByteStringBuilder;
/*     */     //   33: pop
/*     */     //   34: aload_0
/*     */     //   35: iload_1
/*     */     //   36: iconst_0
/*     */     //   37: iushr
/*     */     //   38: i2b
/*     */     //   39: invokevirtual $plus$eq : (B)Lakka/util/ByteStringBuilder;
/*     */     //   42: goto -> 90
/*     */     //   45: aload_2
/*     */     //   46: getstatic java/nio/ByteOrder.LITTLE_ENDIAN : Ljava/nio/ByteOrder;
/*     */     //   49: astore #4
/*     */     //   51: dup
/*     */     //   52: ifnonnull -> 64
/*     */     //   55: pop
/*     */     //   56: aload #4
/*     */     //   58: ifnull -> 72
/*     */     //   61: goto -> 91
/*     */     //   64: aload #4
/*     */     //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 91
/*     */     //   72: aload_0
/*     */     //   73: iload_1
/*     */     //   74: iconst_0
/*     */     //   75: iushr
/*     */     //   76: i2b
/*     */     //   77: invokevirtual $plus$eq : (B)Lakka/util/ByteStringBuilder;
/*     */     //   80: pop
/*     */     //   81: aload_0
/*     */     //   82: iload_1
/*     */     //   83: bipush #8
/*     */     //   85: iushr
/*     */     //   86: i2b
/*     */     //   87: invokevirtual $plus$eq : (B)Lakka/util/ByteStringBuilder;
/*     */     //   90: areturn
/*     */     //   91: new java/lang/IllegalArgumentException
/*     */     //   94: dup
/*     */     //   95: new scala/collection/mutable/StringBuilder
/*     */     //   98: dup
/*     */     //   99: invokespecial <init> : ()V
/*     */     //   102: ldc_w 'Unknown byte order '
/*     */     //   105: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   108: aload_2
/*     */     //   109: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   112: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   115: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   118: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #602	-> 0
/*     */     //   #603	-> 24
/*     */     //   #604	-> 34
/*     */     //   #605	-> 45
/*     */     //   #606	-> 72
/*     */     //   #607	-> 81
/*     */     //   #602	-> 90
/*     */     //   #608	-> 91
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	119	0	this	Lakka/util/ByteStringBuilder;
/*     */     //   0	119	1	x	I
/*     */     //   0	119	2	byteOrder	Ljava/nio/ByteOrder;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putInt(int x, ByteOrder byteOrder) {
/* 615 */     fillArray(4, (Function2<byte[], Object, BoxedUnit>)new ByteStringBuilder$$anonfun$putInt$1(this, x, byteOrder));
/* 628 */     return this;
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putInt$1 extends AbstractFunction2<byte[], Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int x$27;
/*     */     
/*     */     private final ByteOrder byteOrder$2;
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putInt$1(ByteStringBuilder $outer, int x$27, ByteOrder byteOrder$2) {}
/*     */     
/*     */     public final void apply(byte[] target, int offset) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield byteOrder$2 : Ljava/nio/ByteOrder;
/*     */       //   4: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   7: astore_3
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 20
/*     */       //   12: pop
/*     */       //   13: aload_3
/*     */       //   14: ifnull -> 27
/*     */       //   17: goto -> 81
/*     */       //   20: aload_3
/*     */       //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   24: ifeq -> 81
/*     */       //   27: aload_1
/*     */       //   28: iload_2
/*     */       //   29: iconst_0
/*     */       //   30: iadd
/*     */       //   31: aload_0
/*     */       //   32: getfield x$27 : I
/*     */       //   35: bipush #24
/*     */       //   37: iushr
/*     */       //   38: i2b
/*     */       //   39: bastore
/*     */       //   40: aload_1
/*     */       //   41: iload_2
/*     */       //   42: iconst_1
/*     */       //   43: iadd
/*     */       //   44: aload_0
/*     */       //   45: getfield x$27 : I
/*     */       //   48: bipush #16
/*     */       //   50: iushr
/*     */       //   51: i2b
/*     */       //   52: bastore
/*     */       //   53: aload_1
/*     */       //   54: iload_2
/*     */       //   55: iconst_2
/*     */       //   56: iadd
/*     */       //   57: aload_0
/*     */       //   58: getfield x$27 : I
/*     */       //   61: bipush #8
/*     */       //   63: iushr
/*     */       //   64: i2b
/*     */       //   65: bastore
/*     */       //   66: aload_1
/*     */       //   67: iload_2
/*     */       //   68: iconst_3
/*     */       //   69: iadd
/*     */       //   70: aload_0
/*     */       //   71: getfield x$27 : I
/*     */       //   74: iconst_0
/*     */       //   75: iushr
/*     */       //   76: i2b
/*     */       //   77: bastore
/*     */       //   78: goto -> 162
/*     */       //   81: aload_0
/*     */       //   82: getfield byteOrder$2 : Ljava/nio/ByteOrder;
/*     */       //   85: getstatic java/nio/ByteOrder.LITTLE_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   88: astore #4
/*     */       //   90: dup
/*     */       //   91: ifnonnull -> 103
/*     */       //   94: pop
/*     */       //   95: aload #4
/*     */       //   97: ifnull -> 111
/*     */       //   100: goto -> 163
/*     */       //   103: aload #4
/*     */       //   105: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   108: ifeq -> 163
/*     */       //   111: aload_1
/*     */       //   112: iload_2
/*     */       //   113: iconst_0
/*     */       //   114: iadd
/*     */       //   115: aload_0
/*     */       //   116: getfield x$27 : I
/*     */       //   119: iconst_0
/*     */       //   120: iushr
/*     */       //   121: i2b
/*     */       //   122: bastore
/*     */       //   123: aload_1
/*     */       //   124: iload_2
/*     */       //   125: iconst_1
/*     */       //   126: iadd
/*     */       //   127: aload_0
/*     */       //   128: getfield x$27 : I
/*     */       //   131: bipush #8
/*     */       //   133: iushr
/*     */       //   134: i2b
/*     */       //   135: bastore
/*     */       //   136: aload_1
/*     */       //   137: iload_2
/*     */       //   138: iconst_2
/*     */       //   139: iadd
/*     */       //   140: aload_0
/*     */       //   141: getfield x$27 : I
/*     */       //   144: bipush #16
/*     */       //   146: iushr
/*     */       //   147: i2b
/*     */       //   148: bastore
/*     */       //   149: aload_1
/*     */       //   150: iload_2
/*     */       //   151: iconst_3
/*     */       //   152: iadd
/*     */       //   153: aload_0
/*     */       //   154: getfield x$27 : I
/*     */       //   157: bipush #24
/*     */       //   159: iushr
/*     */       //   160: i2b
/*     */       //   161: bastore
/*     */       //   162: return
/*     */       //   163: new java/lang/IllegalArgumentException
/*     */       //   166: dup
/*     */       //   167: new scala/collection/mutable/StringBuilder
/*     */       //   170: dup
/*     */       //   171: invokespecial <init> : ()V
/*     */       //   174: ldc 'Unknown byte order '
/*     */       //   176: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   179: aload_0
/*     */       //   180: getfield byteOrder$2 : Ljava/nio/ByteOrder;
/*     */       //   183: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   186: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   189: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   192: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #616	-> 0
/*     */       //   #617	-> 27
/*     */       //   #618	-> 40
/*     */       //   #619	-> 53
/*     */       //   #620	-> 66
/*     */       //   #621	-> 81
/*     */       //   #622	-> 111
/*     */       //   #623	-> 123
/*     */       //   #624	-> 136
/*     */       //   #625	-> 149
/*     */       //   #616	-> 162
/*     */       //   #626	-> 163
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	193	0	this	Lakka/util/ByteStringBuilder$$anonfun$putInt$1;
/*     */       //   0	193	1	target	[B
/*     */       //   0	193	2	offset	I
/*     */     }
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putLong(long x, ByteOrder byteOrder) {
/* 635 */     fillArray(8, (Function2<byte[], Object, BoxedUnit>)new ByteStringBuilder$$anonfun$putLong$1(this, x, byteOrder));
/* 656 */     return this;
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putLong$1 extends AbstractFunction2<byte[], Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final long x$28;
/*     */     
/*     */     private final ByteOrder byteOrder$3;
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putLong$1(ByteStringBuilder $outer, long x$28, ByteOrder byteOrder$3) {}
/*     */     
/*     */     public final void apply(byte[] target, int offset) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield byteOrder$3 : Ljava/nio/ByteOrder;
/*     */       //   4: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   7: astore_3
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 20
/*     */       //   12: pop
/*     */       //   13: aload_3
/*     */       //   14: ifnull -> 27
/*     */       //   17: goto -> 143
/*     */       //   20: aload_3
/*     */       //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   24: ifeq -> 143
/*     */       //   27: aload_1
/*     */       //   28: iload_2
/*     */       //   29: iconst_0
/*     */       //   30: iadd
/*     */       //   31: aload_0
/*     */       //   32: getfield x$28 : J
/*     */       //   35: bipush #56
/*     */       //   37: lushr
/*     */       //   38: l2i
/*     */       //   39: i2b
/*     */       //   40: bastore
/*     */       //   41: aload_1
/*     */       //   42: iload_2
/*     */       //   43: iconst_1
/*     */       //   44: iadd
/*     */       //   45: aload_0
/*     */       //   46: getfield x$28 : J
/*     */       //   49: bipush #48
/*     */       //   51: lushr
/*     */       //   52: l2i
/*     */       //   53: i2b
/*     */       //   54: bastore
/*     */       //   55: aload_1
/*     */       //   56: iload_2
/*     */       //   57: iconst_2
/*     */       //   58: iadd
/*     */       //   59: aload_0
/*     */       //   60: getfield x$28 : J
/*     */       //   63: bipush #40
/*     */       //   65: lushr
/*     */       //   66: l2i
/*     */       //   67: i2b
/*     */       //   68: bastore
/*     */       //   69: aload_1
/*     */       //   70: iload_2
/*     */       //   71: iconst_3
/*     */       //   72: iadd
/*     */       //   73: aload_0
/*     */       //   74: getfield x$28 : J
/*     */       //   77: bipush #32
/*     */       //   79: lushr
/*     */       //   80: l2i
/*     */       //   81: i2b
/*     */       //   82: bastore
/*     */       //   83: aload_1
/*     */       //   84: iload_2
/*     */       //   85: iconst_4
/*     */       //   86: iadd
/*     */       //   87: aload_0
/*     */       //   88: getfield x$28 : J
/*     */       //   91: bipush #24
/*     */       //   93: lushr
/*     */       //   94: l2i
/*     */       //   95: i2b
/*     */       //   96: bastore
/*     */       //   97: aload_1
/*     */       //   98: iload_2
/*     */       //   99: iconst_5
/*     */       //   100: iadd
/*     */       //   101: aload_0
/*     */       //   102: getfield x$28 : J
/*     */       //   105: bipush #16
/*     */       //   107: lushr
/*     */       //   108: l2i
/*     */       //   109: i2b
/*     */       //   110: bastore
/*     */       //   111: aload_1
/*     */       //   112: iload_2
/*     */       //   113: bipush #6
/*     */       //   115: iadd
/*     */       //   116: aload_0
/*     */       //   117: getfield x$28 : J
/*     */       //   120: bipush #8
/*     */       //   122: lushr
/*     */       //   123: l2i
/*     */       //   124: i2b
/*     */       //   125: bastore
/*     */       //   126: aload_1
/*     */       //   127: iload_2
/*     */       //   128: bipush #7
/*     */       //   130: iadd
/*     */       //   131: aload_0
/*     */       //   132: getfield x$28 : J
/*     */       //   135: iconst_0
/*     */       //   136: lushr
/*     */       //   137: l2i
/*     */       //   138: i2b
/*     */       //   139: bastore
/*     */       //   140: goto -> 286
/*     */       //   143: aload_0
/*     */       //   144: getfield byteOrder$3 : Ljava/nio/ByteOrder;
/*     */       //   147: getstatic java/nio/ByteOrder.LITTLE_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   150: astore #4
/*     */       //   152: dup
/*     */       //   153: ifnonnull -> 165
/*     */       //   156: pop
/*     */       //   157: aload #4
/*     */       //   159: ifnull -> 173
/*     */       //   162: goto -> 287
/*     */       //   165: aload #4
/*     */       //   167: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   170: ifeq -> 287
/*     */       //   173: aload_1
/*     */       //   174: iload_2
/*     */       //   175: iconst_0
/*     */       //   176: iadd
/*     */       //   177: aload_0
/*     */       //   178: getfield x$28 : J
/*     */       //   181: iconst_0
/*     */       //   182: lushr
/*     */       //   183: l2i
/*     */       //   184: i2b
/*     */       //   185: bastore
/*     */       //   186: aload_1
/*     */       //   187: iload_2
/*     */       //   188: iconst_1
/*     */       //   189: iadd
/*     */       //   190: aload_0
/*     */       //   191: getfield x$28 : J
/*     */       //   194: bipush #8
/*     */       //   196: lushr
/*     */       //   197: l2i
/*     */       //   198: i2b
/*     */       //   199: bastore
/*     */       //   200: aload_1
/*     */       //   201: iload_2
/*     */       //   202: iconst_2
/*     */       //   203: iadd
/*     */       //   204: aload_0
/*     */       //   205: getfield x$28 : J
/*     */       //   208: bipush #16
/*     */       //   210: lushr
/*     */       //   211: l2i
/*     */       //   212: i2b
/*     */       //   213: bastore
/*     */       //   214: aload_1
/*     */       //   215: iload_2
/*     */       //   216: iconst_3
/*     */       //   217: iadd
/*     */       //   218: aload_0
/*     */       //   219: getfield x$28 : J
/*     */       //   222: bipush #24
/*     */       //   224: lushr
/*     */       //   225: l2i
/*     */       //   226: i2b
/*     */       //   227: bastore
/*     */       //   228: aload_1
/*     */       //   229: iload_2
/*     */       //   230: iconst_4
/*     */       //   231: iadd
/*     */       //   232: aload_0
/*     */       //   233: getfield x$28 : J
/*     */       //   236: bipush #32
/*     */       //   238: lushr
/*     */       //   239: l2i
/*     */       //   240: i2b
/*     */       //   241: bastore
/*     */       //   242: aload_1
/*     */       //   243: iload_2
/*     */       //   244: iconst_5
/*     */       //   245: iadd
/*     */       //   246: aload_0
/*     */       //   247: getfield x$28 : J
/*     */       //   250: bipush #40
/*     */       //   252: lushr
/*     */       //   253: l2i
/*     */       //   254: i2b
/*     */       //   255: bastore
/*     */       //   256: aload_1
/*     */       //   257: iload_2
/*     */       //   258: bipush #6
/*     */       //   260: iadd
/*     */       //   261: aload_0
/*     */       //   262: getfield x$28 : J
/*     */       //   265: bipush #48
/*     */       //   267: lushr
/*     */       //   268: l2i
/*     */       //   269: i2b
/*     */       //   270: bastore
/*     */       //   271: aload_1
/*     */       //   272: iload_2
/*     */       //   273: bipush #7
/*     */       //   275: iadd
/*     */       //   276: aload_0
/*     */       //   277: getfield x$28 : J
/*     */       //   280: bipush #56
/*     */       //   282: lushr
/*     */       //   283: l2i
/*     */       //   284: i2b
/*     */       //   285: bastore
/*     */       //   286: return
/*     */       //   287: new java/lang/IllegalArgumentException
/*     */       //   290: dup
/*     */       //   291: new scala/collection/mutable/StringBuilder
/*     */       //   294: dup
/*     */       //   295: invokespecial <init> : ()V
/*     */       //   298: ldc 'Unknown byte order '
/*     */       //   300: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   303: aload_0
/*     */       //   304: getfield byteOrder$3 : Ljava/nio/ByteOrder;
/*     */       //   307: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   310: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   313: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   316: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #636	-> 0
/*     */       //   #637	-> 27
/*     */       //   #638	-> 41
/*     */       //   #639	-> 55
/*     */       //   #640	-> 69
/*     */       //   #641	-> 83
/*     */       //   #642	-> 97
/*     */       //   #643	-> 111
/*     */       //   #644	-> 126
/*     */       //   #645	-> 143
/*     */       //   #646	-> 173
/*     */       //   #647	-> 186
/*     */       //   #648	-> 200
/*     */       //   #649	-> 214
/*     */       //   #650	-> 228
/*     */       //   #651	-> 242
/*     */       //   #652	-> 256
/*     */       //   #653	-> 271
/*     */       //   #636	-> 286
/*     */       //   #654	-> 287
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	317	0	this	Lakka/util/ByteStringBuilder$$anonfun$putLong$1;
/*     */       //   0	317	1	target	[B
/*     */       //   0	317	2	offset	I
/*     */     }
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putLongPart(long x, int n, ByteOrder byteOrder) {
/* 663 */     return fillArray(n, (Function2<byte[], Object, BoxedUnit>)new ByteStringBuilder$$anonfun$putLongPart$1(this, x, n, byteOrder));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putLongPart$1 extends AbstractFunction2<byte[], Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long x$29;
/*     */     
/*     */     private final int n$1;
/*     */     
/*     */     private final ByteOrder byteOrder$4;
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putLongPart$1(ByteStringBuilder $outer, long x$29, int n$1, ByteOrder byteOrder$4) {}
/*     */     
/*     */     public final void apply(byte[] target, int offset) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield byteOrder$4 : Ljava/nio/ByteOrder;
/*     */       //   4: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   7: astore_3
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 20
/*     */       //   12: pop
/*     */       //   13: aload_3
/*     */       //   14: ifnull -> 27
/*     */       //   17: goto -> 74
/*     */       //   20: aload_3
/*     */       //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   24: ifeq -> 74
/*     */       //   27: aload_0
/*     */       //   28: getfield n$1 : I
/*     */       //   31: bipush #8
/*     */       //   33: imul
/*     */       //   34: bipush #8
/*     */       //   36: isub
/*     */       //   37: istore #4
/*     */       //   39: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */       //   42: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   45: iconst_0
/*     */       //   46: invokevirtual intWrapper : (I)I
/*     */       //   49: aload_0
/*     */       //   50: getfield n$1 : I
/*     */       //   53: invokevirtual until$extension0 : (II)Lscala/collection/immutable/Range;
/*     */       //   56: new akka/util/ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$1
/*     */       //   59: dup
/*     */       //   60: aload_0
/*     */       //   61: aload_1
/*     */       //   62: iload_2
/*     */       //   63: iload #4
/*     */       //   65: invokespecial <init> : (Lakka/util/ByteStringBuilder$$anonfun$putLongPart$1;[BII)V
/*     */       //   68: invokevirtual foreach$mVc$sp : (Lscala/Function1;)V
/*     */       //   71: goto -> 134
/*     */       //   74: aload_0
/*     */       //   75: getfield byteOrder$4 : Ljava/nio/ByteOrder;
/*     */       //   78: getstatic java/nio/ByteOrder.LITTLE_ENDIAN : Ljava/nio/ByteOrder;
/*     */       //   81: astore #5
/*     */       //   83: dup
/*     */       //   84: ifnonnull -> 96
/*     */       //   87: pop
/*     */       //   88: aload #5
/*     */       //   90: ifnull -> 104
/*     */       //   93: goto -> 135
/*     */       //   96: aload #5
/*     */       //   98: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   101: ifeq -> 135
/*     */       //   104: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */       //   107: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   110: iconst_0
/*     */       //   111: invokevirtual intWrapper : (I)I
/*     */       //   114: aload_0
/*     */       //   115: getfield n$1 : I
/*     */       //   118: invokevirtual until$extension0 : (II)Lscala/collection/immutable/Range;
/*     */       //   121: new akka/util/ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$2
/*     */       //   124: dup
/*     */       //   125: aload_0
/*     */       //   126: aload_1
/*     */       //   127: iload_2
/*     */       //   128: invokespecial <init> : (Lakka/util/ByteStringBuilder$$anonfun$putLongPart$1;[BI)V
/*     */       //   131: invokevirtual foreach$mVc$sp : (Lscala/Function1;)V
/*     */       //   134: return
/*     */       //   135: new java/lang/IllegalArgumentException
/*     */       //   138: dup
/*     */       //   139: new scala/collection/mutable/StringBuilder
/*     */       //   142: dup
/*     */       //   143: invokespecial <init> : ()V
/*     */       //   146: ldc 'Unknown byte order '
/*     */       //   148: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   151: aload_0
/*     */       //   152: getfield byteOrder$4 : Ljava/nio/ByteOrder;
/*     */       //   155: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   158: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   161: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   164: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #664	-> 0
/*     */       //   #665	-> 27
/*     */       //   #666	-> 42
/*     */       //   #667	-> 74
/*     */       //   #668	-> 107
/*     */       //   #664	-> 134
/*     */       //   #669	-> 135
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	165	0	this	Lakka/util/ByteStringBuilder$$anonfun$putLongPart$1;
/*     */       //   0	165	1	target	[B
/*     */       //   0	165	2	offset	I
/*     */       //   39	32	4	start	I
/*     */     }
/*     */     
/*     */     public class ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final byte[] target$1;
/*     */       
/*     */       private final int offset$1;
/*     */       
/*     */       private final int start$1;
/*     */       
/*     */       public final void apply(int i) {
/* 666 */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/* 666 */         this.target$1[this.offset$1 + i] = (byte)(int)(this.$outer.x$29 >>> this.start$1 - 8 * i);
/*     */       }
/*     */       
/*     */       public ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$1(ByteStringBuilder$$anonfun$putLongPart$1 $outer, byte[] target$1, int offset$1, int start$1) {}
/*     */     }
/*     */     
/*     */     public class ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$2 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final byte[] target$1;
/*     */       
/*     */       private final int offset$1;
/*     */       
/*     */       public final void apply(int i) {
/* 668 */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/* 668 */         this.target$1[this.offset$1 + i] = (byte)(int)(this.$outer.x$29 >>> 8 * i);
/*     */       }
/*     */       
/*     */       public ByteStringBuilder$$anonfun$putLongPart$1$$anonfun$apply$2(ByteStringBuilder$$anonfun$putLongPart$1 $outer, byte[] target$1, int offset$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putFloat(float x, ByteOrder byteOrder) {
/* 677 */     return putInt(Float.floatToRawIntBits(x), byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putDouble(double x, ByteOrder byteOrder) {
/* 683 */     return putLong(Double.doubleToRawLongBits(x), byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putBytes(byte[] array) {
/* 689 */     return putBytes(array, 0, array.length);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putBytes(byte[] array, int start, int len) {
/* 695 */     return fillArray(len, (Function2<byte[], Object, BoxedUnit>)new ByteStringBuilder$$anonfun$putBytes$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putBytes$1 extends AbstractFunction2<byte[], Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] array$1;
/*     */     
/*     */     private final int start$2;
/*     */     
/*     */     private final int len$2;
/*     */     
/*     */     public final void apply(byte[] x0$2, int x1$2) {
/* 695 */       Tuple2 tuple2 = new Tuple2(x0$2, BoxesRunTime.boxToInteger(x1$2));
/* 695 */       if (tuple2 != null) {
/* 695 */         byte[] target = (byte[])tuple2._1();
/* 695 */         int targetOffset = tuple2._2$mcI$sp();
/* 695 */         Array$.MODULE$.copy(this.array$1, this.start$2, target, targetOffset, this.len$2);
/* 695 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/* 695 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putBytes$1(ByteStringBuilder $outer, byte[] array$1, int start$2, int len$2) {}
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putShorts(short[] array, ByteOrder byteOrder) {
/* 701 */     return putShorts(array, 0, array.length, byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putShorts(short[] array, int start, int len, ByteOrder byteOrder) {
/* 707 */     return fillByteBuffer(len * 2, byteOrder, (Function1<ByteBuffer, BoxedUnit>)new ByteStringBuilder$$anonfun$putShorts$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putShorts$1 extends AbstractFunction1<ByteBuffer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final short[] array$2;
/*     */     
/*     */     private final int start$3;
/*     */     
/*     */     private final int len$3;
/*     */     
/*     */     public final void apply(ByteBuffer x$9) {
/* 707 */       x$9.asShortBuffer().put(this.array$2, this.start$3, this.len$3);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putShorts$1(ByteStringBuilder $outer, short[] array$2, int start$3, int len$3) {}
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putInts(int[] array, ByteOrder byteOrder) {
/* 713 */     return putInts(array, 0, array.length, byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putInts(int[] array, int start, int len, ByteOrder byteOrder) {
/* 719 */     return fillByteBuffer(len * 4, byteOrder, (Function1<ByteBuffer, BoxedUnit>)new ByteStringBuilder$$anonfun$putInts$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putInts$1 extends AbstractFunction1<ByteBuffer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int[] array$3;
/*     */     
/*     */     private final int start$4;
/*     */     
/*     */     private final int len$4;
/*     */     
/*     */     public final void apply(ByteBuffer x$10) {
/* 719 */       x$10.asIntBuffer().put(this.array$3, this.start$4, this.len$4);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putInts$1(ByteStringBuilder $outer, int[] array$3, int start$4, int len$4) {}
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putLongs(long[] array, ByteOrder byteOrder) {
/* 725 */     return putLongs(array, 0, array.length, byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putLongs(long[] array, int start, int len, ByteOrder byteOrder) {
/* 731 */     return fillByteBuffer(len * 8, byteOrder, (Function1<ByteBuffer, BoxedUnit>)new ByteStringBuilder$$anonfun$putLongs$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putLongs$1 extends AbstractFunction1<ByteBuffer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final long[] array$4;
/*     */     
/*     */     private final int start$5;
/*     */     
/*     */     private final int len$5;
/*     */     
/*     */     public final void apply(ByteBuffer x$11) {
/* 731 */       x$11.asLongBuffer().put(this.array$4, this.start$5, this.len$5);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putLongs$1(ByteStringBuilder $outer, long[] array$4, int start$5, int len$5) {}
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putFloats(float[] array, ByteOrder byteOrder) {
/* 737 */     return putFloats(array, 0, array.length, byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putFloats(float[] array, int start, int len, ByteOrder byteOrder) {
/* 743 */     return fillByteBuffer(len * 4, byteOrder, (Function1<ByteBuffer, BoxedUnit>)new ByteStringBuilder$$anonfun$putFloats$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putFloats$1 extends AbstractFunction1<ByteBuffer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final float[] array$5;
/*     */     
/*     */     private final int start$6;
/*     */     
/*     */     private final int len$6;
/*     */     
/*     */     public final void apply(ByteBuffer x$12) {
/* 743 */       x$12.asFloatBuffer().put(this.array$5, this.start$6, this.len$6);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putFloats$1(ByteStringBuilder $outer, float[] array$5, int start$6, int len$6) {}
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putDoubles(double[] array, ByteOrder byteOrder) {
/* 749 */     return putDoubles(array, 0, array.length, byteOrder);
/*     */   }
/*     */   
/*     */   public ByteStringBuilder putDoubles(double[] array, int start, int len, ByteOrder byteOrder) {
/* 755 */     return fillByteBuffer(len * 8, byteOrder, (Function1<ByteBuffer, BoxedUnit>)new ByteStringBuilder$$anonfun$putDoubles$1(this, array, start, len));
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anonfun$putDoubles$1 extends AbstractFunction1<ByteBuffer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final double[] array$6;
/*     */     
/*     */     private final int start$7;
/*     */     
/*     */     private final int len$7;
/*     */     
/*     */     public final void apply(ByteBuffer x$13) {
/* 755 */       x$13.asDoubleBuffer().put(this.array$6, this.start$7, this.len$7);
/*     */     }
/*     */     
/*     */     public ByteStringBuilder$$anonfun$putDoubles$1(ByteStringBuilder $outer, double[] array$6, int start$7, int len$7) {}
/*     */   }
/*     */   
/*     */   public void clear() {
/* 758 */     _builder().clear();
/* 759 */     _length_$eq(0);
/* 760 */     _tempLength_$eq(0);
/*     */   }
/*     */   
/*     */   public ByteString result() {
/* 766 */     clearTemp();
/* 767 */     Vector<ByteString.ByteString1> bytestrings = _builder().result();
/* 768 */     return (_length() == 0) ? ByteString$.MODULE$.empty() : ((bytestrings.size() == 1) ? 
/* 769 */       (ByteString)bytestrings.head() : 
/*     */       
/* 771 */       ByteString.ByteStrings$.MODULE$.apply(bytestrings, _length()));
/*     */   }
/*     */   
/*     */   public OutputStream asOutputStream() {
/* 778 */     return new ByteStringBuilder$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class ByteStringBuilder$$anon$1 extends OutputStream {
/*     */     public ByteStringBuilder$$anon$1(ByteStringBuilder $outer) {}
/*     */     
/*     */     public void write(int b) {
/* 779 */       this.$outer.$plus$eq((byte)b);
/*     */     }
/*     */     
/*     */     public void write(byte[] b, int off, int len) {
/* 781 */       this.$outer.putBytes(b, off, len);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ByteStringBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */