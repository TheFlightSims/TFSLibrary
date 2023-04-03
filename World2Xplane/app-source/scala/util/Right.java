/*     */ package scala.util;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%c\001B\001\003\005\036\021QAU5hQRT!a\001\003\002\tU$\030\016\034\006\002\013\005)1oY1mC\016\001Qc\001\005\0205M!\001!\003\017 !\021Q1\"D\r\016\003\tI!\001\004\002\003\r\025KG\017[3s!\tqq\002\004\001\005\rA\001AQ1\001\022\005\005\t\025C\001\n\027!\t\031B#D\001\005\023\t)BAA\004O_RD\027N\\4\021\005M9\022B\001\r\005\005\r\te.\037\t\003\035i!aa\007\001\005\006\004\t\"!\001\"\021\005Mi\022B\001\020\005\005\035\001&o\0343vGR\004\"a\005\021\n\005\005\"!\001D*fe&\fG.\033>bE2,\007\002C\022\001\005+\007I\021\001\023\002\003\t,\022!\007\005\tM\001\021\t\022)A\0053\005\021!\r\t\005\006Q\001!\t!K\001\007y%t\027\016\036 \025\005)Z\003\003\002\006\001\033eAQaI\024A\002eAQ!\f\001\005\0029\na![:MK\032$X#A\030\021\005M\001\024BA\031\005\005\035\021un\0347fC:DQa\r\001\005\0029\nq![:SS\036DG\017C\0046\001\005\005I\021\001\034\002\t\r|\007/_\013\004oibDC\001\035>!\021Q\001!O\036\021\0059QD!\002\t5\005\004\t\002C\001\b=\t\025YBG1\001\022\021\035\031C\007%AA\002mBqa\020\001\022\002\023\005\001)\001\bd_BLH\005Z3gCVdG\017J\031\026\007\005cU*F\001CU\tI2iK\001E!\t)%*D\001G\025\t9\005*A\005v]\016DWmY6fI*\021\021\nB\001\013C:tw\016^1uS>t\027BA&G\005E)hn\0315fG.,GMV1sS\006t7-\032\003\006!y\022\r!\005\003\0067y\022\r!\005\005\b\037\002\t\t\021\"\021Q\0035\001(o\0343vGR\004&/\0324jqV\t\021\013\005\002S/6\t1K\003\002U+\006!A.\0318h\025\0051\026\001\0026bm\006L!\001W*\003\rM#(/\0338h\021\035Q\006!!A\005\002m\013A\002\035:pIV\034G/\021:jif,\022\001\030\t\003'uK!A\030\003\003\007%sG\017C\004a\001\005\005I\021A1\002\035A\024x\016Z;di\026cW-\\3oiR\021aC\031\005\bG~\013\t\0211\001]\003\rAH%\r\005\bK\002\t\t\021\"\021g\003=\001(o\0343vGRLE/\032:bi>\024X#A4\021\007!\\g#D\001j\025\tQG!\001\006d_2dWm\031;j_:L!\001\\5\003\021%#XM]1u_JDqA\034\001\002\002\023\005q.\001\005dC:,\025/^1m)\ty\003\017C\004d[\006\005\t\031\001\f\t\017I\004\021\021!C!g\006A\001.Y:i\007>$W\rF\001]\021\035)\b!!A\005BY\f\001\002^8TiJLgn\032\013\002#\"9\001\020AA\001\n\003J\030AB3rk\006d7\017\006\0020u\"91m^A\001\002\0041ra\002?\003\003\003E\t!`\001\006%&<\007\016\036\t\003\025y4q!\001\002\002\002#\005qp\005\003\003\003y\002cA\n\002\004%\031\021Q\001\003\003\r\005s\027PU3g\021\031Ac\020\"\001\002\nQ\tQ\020C\004v}\006\005IQ\t<\t\023\005=a0!A\005\002\006E\021!B1qa2LXCBA\n\0033\ti\002\006\003\002\026\005}\001C\002\006\001\003/\tY\002E\002\017\0033!a\001EA\007\005\004\t\002c\001\b\002\036\02111$!\004C\002EAqaIA\007\001\004\tY\002C\005\002$y\f\t\021\"!\002&\0059QO\\1qa2LXCBA\024\003w\t\t\004\006\003\002*\005M\002#B\n\002,\005=\022bAA\027\t\t1q\n\035;j_:\0042ADA\031\t\031Y\022\021\005b\001#!Q\021QGA\021\003\003\005\r!a\016\002\007a$\003\007\005\004\013\001\005e\022q\006\t\004\035\005mBA\002\t\002\"\t\007\021\003C\005\002@y\f\t\021\"\003\002B\005Y!/Z1e%\026\034x\016\034<f)\t\t\031\005E\002S\003\013J1!a\022T\005\031y%M[3di\002")
/*     */ public final class Right<A, B> extends Either<A, B> implements Product, Serializable {
/*     */   private final B b;
/*     */   
/*     */   public B b() {
/* 200 */     return this.b;
/*     */   }
/*     */   
/*     */   public <A, B> Right<A, B> copy(Object b) {
/* 200 */     return new Right((B)b);
/*     */   }
/*     */   
/*     */   public <A, B> B copy$default$1() {
/* 200 */     return b();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 200 */     return "Right";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 200 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 200 */     switch (x$1) {
/*     */       default:
/* 200 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 200 */     return b();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 200 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 200 */     return x$1 instanceof Right;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 200 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 200 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 200 */     if (this != x$1) {
/*     */       boolean bool;
/* 200 */       if (x$1 instanceof Right) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Right right = (Right)x$1;
/*     */         Object object = right.b();
/*     */         B b;
/*     */         if ((((b = b()) == object) ? true : ((b == null) ? false : ((b instanceof Number) ? BoxesRunTime.equalsNumObject((Number)b, object) : ((b instanceof Character) ? BoxesRunTime.equalsCharObject((Character)b, object) : b.equals(object))))));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Right(Object b) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isLeft() {
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRight() {
/*     */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Right.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */