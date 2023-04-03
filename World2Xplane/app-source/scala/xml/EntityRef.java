/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ub\001B\001\003\001\036\021\021\"\0228uSRL(+\0324\013\005\r!\021a\001=nY*\tQ!A\003tG\006d\027m\001\001\024\t\001AA\002\005\t\003\023)i\021AA\005\003\027\t\0211b\0259fG&\fGNT8eKB\021QBD\007\002\t%\021q\002\002\002\b!J|G-^2u!\ti\021#\003\002\023\t\ta1+\032:jC2L'0\0312mK\"AA\003\001BK\002\023\005Q#\001\006f]RLG/\037(b[\026,\022A\006\t\003/iq!!\004\r\n\005e!\021A\002)sK\022,g-\003\002\0349\t11\013\036:j]\036T!!\007\003\t\021y\001!\021#Q\001\nY\t1\"\0328uSRLh*Y7fA!)\001\005\001C\001C\0051A(\0338jiz\"\"AI\022\021\005%\001\001\"\002\013 \001\0041\002\"B\023\001\t\0132\023a\0053p\007>dG.Z2u\035\006lWm\0359bG\026\034X#A\024\021\0055A\023BA\025\005\005\035\021un\0347fC:DQa\013\001\005F\031\n1\002Z8Ue\006t7OZ8s[\")Q\006\001C\001]\005)A.\0312fYV\tq\006\005\0021k5\t\021G\003\0023g\005!A.\0318h\025\005!\024\001\0026bm\006L!aG\031\t\013]\002A\021I\013\002\tQ,\007\020\036\005\006s\001!\tEO\001\fEVLG\016Z*ue&tw\r\006\002<\007B\021A(Q\007\002{)\021ahP\001\b[V$\030M\0317f\025\t\001E!\001\006d_2dWm\031;j_:L!AQ\037\003\033M#(/\0338h\005VLG\016Z3s\021\025!\005\b1\001F\003\t\031(\r\005\002G\035:\021q\t\024\b\003\021.k\021!\023\006\003\025\032\ta\001\020:p_Rt\024\"A\003\n\0055#\021a\0029bG.\fw-Z\005\003\005>S!!\024\003\t\017E\003\021\021!C\001%\006!1m\0349z)\t\0213\013C\004\025!B\005\t\031\001\f\t\017U\003\021\023!C\001-\006q1m\0349zI\021,g-Y;mi\022\nT#A,+\005YA6&A-\021\005i{V\"A.\013\005qk\026!C;oG\",7m[3e\025\tqF!\001\006b]:|G/\031;j_:L!\001Y.\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004c\001\005\005I\021\t\030\002\033A\024x\016Z;diB\023XMZ5y\021\035!\007!!A\005\002\025\fA\002\035:pIV\034G/\021:jif,\022A\032\t\003\033\035L!\001\033\003\003\007%sG\017C\004k\001\005\005I\021A6\002\035A\024x\016Z;di\026cW-\\3oiR\021An\034\t\003\0335L!A\034\003\003\007\005s\027\020C\004qS\006\005\t\031\0014\002\007a$\023\007C\004s\001\005\005I\021I:\002\037A\024x\016Z;di&#XM]1u_J,\022\001\036\t\004kZdW\"A \n\005]|$\001C%uKJ\fGo\034:\b\017e\024\021\021!E\001u\006IQI\034;jif\024VM\032\t\003\023m4q!\001\002\002\002#\005ApE\002|{B\001RA`A\002-\tj\021a \006\004\003\003!\021a\002:v]RLW.Z\005\004\003\013y(!E!cgR\024\030m\031;Gk:\034G/[8oc!1\001e\037C\001\003\023!\022A\037\005\n\003\033Y\030\021!C#\003\037\t\001\002^8TiJLgn\032\013\002_!I\0211C>\002\002\023\005\025QC\001\006CB\004H.\037\013\004E\005]\001B\002\013\002\022\001\007a\003C\005\002\034m\f\t\021\"!\002\036\0059QO\\1qa2LH\003BA\020\003K\001B!DA\021-%\031\0211\005\003\003\r=\003H/[8o\021%\t9#!\007\002\002\003\007!%A\002yIAB\021\"a\013|\003\003%I!!\f\002\027I,\027\r\032*fg>dg/\032\013\003\003_\0012\001MA\031\023\r\t\031$\r\002\007\037\nTWm\031;")
/*    */ public class EntityRef extends SpecialNode implements Product, Serializable {
/*    */   private final String entityName;
/*    */   
/*    */   public String entityName() {
/* 17 */     return this.entityName;
/*    */   }
/*    */   
/*    */   public EntityRef copy(String entityName) {
/* 17 */     return new EntityRef(entityName);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 17 */     return entityName();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 17 */     return "EntityRef";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 17 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 17 */     switch (x$1) {
/*    */       default:
/* 17 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 17 */     return entityName();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 17 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public EntityRef(String entityName) {
/* 17 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public final boolean doCollectNamespaces() {
/* 18 */     return false;
/*    */   }
/*    */   
/*    */   public final boolean doTransform() {
/* 19 */     return false;
/*    */   }
/*    */   
/*    */   public String label() {
/* 20 */     return "#ENTITY";
/*    */   }
/*    */   
/*    */   public String text() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokevirtual entityName : ()Ljava/lang/String;
/*    */     //   4: astore_1
/*    */     //   5: ldc 'lt'
/*    */     //   7: dup
/*    */     //   8: ifnonnull -> 19
/*    */     //   11: pop
/*    */     //   12: aload_1
/*    */     //   13: ifnull -> 26
/*    */     //   16: goto -> 32
/*    */     //   19: aload_1
/*    */     //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   23: ifeq -> 32
/*    */     //   26: ldc '<'
/*    */     //   28: astore_2
/*    */     //   29: goto -> 155
/*    */     //   32: ldc 'gt'
/*    */     //   34: dup
/*    */     //   35: ifnonnull -> 46
/*    */     //   38: pop
/*    */     //   39: aload_1
/*    */     //   40: ifnull -> 53
/*    */     //   43: goto -> 59
/*    */     //   46: aload_1
/*    */     //   47: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   50: ifeq -> 59
/*    */     //   53: ldc '>'
/*    */     //   55: astore_2
/*    */     //   56: goto -> 155
/*    */     //   59: ldc 'amp'
/*    */     //   61: dup
/*    */     //   62: ifnonnull -> 73
/*    */     //   65: pop
/*    */     //   66: aload_1
/*    */     //   67: ifnull -> 80
/*    */     //   70: goto -> 86
/*    */     //   73: aload_1
/*    */     //   74: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   77: ifeq -> 86
/*    */     //   80: ldc '&'
/*    */     //   82: astore_2
/*    */     //   83: goto -> 155
/*    */     //   86: ldc 'apos'
/*    */     //   88: dup
/*    */     //   89: ifnonnull -> 100
/*    */     //   92: pop
/*    */     //   93: aload_1
/*    */     //   94: ifnull -> 107
/*    */     //   97: goto -> 113
/*    */     //   100: aload_1
/*    */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   104: ifeq -> 113
/*    */     //   107: ldc '''
/*    */     //   109: astore_2
/*    */     //   110: goto -> 155
/*    */     //   113: ldc 'quot'
/*    */     //   115: dup
/*    */     //   116: ifnonnull -> 127
/*    */     //   119: pop
/*    */     //   120: aload_1
/*    */     //   121: ifnull -> 134
/*    */     //   124: goto -> 140
/*    */     //   127: aload_1
/*    */     //   128: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   131: ifeq -> 140
/*    */     //   134: ldc '"'
/*    */     //   136: astore_2
/*    */     //   137: goto -> 155
/*    */     //   140: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*    */     //   143: new scala/xml/EntityRef$$anonfun$text$1
/*    */     //   146: dup
/*    */     //   147: aload_0
/*    */     //   148: invokespecial <init> : (Lscala/xml/EntityRef;)V
/*    */     //   151: invokevirtual sbToString : (Lscala/Function1;)Ljava/lang/String;
/*    */     //   154: astore_2
/*    */     //   155: aload_2
/*    */     //   156: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #22	-> 0
/*    */     //   #23	-> 5
/*    */     //   #24	-> 32
/*    */     //   #25	-> 59
/*    */     //   #26	-> 86
/*    */     //   #27	-> 113
/*    */     //   #28	-> 140
/*    */     //   #22	-> 155
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	157	0	this	Lscala/xml/EntityRef;
/*    */   }
/*    */   
/*    */   public class EntityRef$$anonfun$text$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 28 */       this.$outer.buildString(sb);
/*    */     }
/*    */     
/*    */     public EntityRef$$anonfun$text$1(EntityRef $outer) {}
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 37 */     return sb.append("&").append(entityName()).append(";");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\EntityRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */