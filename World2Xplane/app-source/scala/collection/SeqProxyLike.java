/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Serializable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\rEbaB\001\003!\003\r\ta\002\002\r'\026\f\bK]8ys2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\004\021Mi2\003\002\001\n\033\031\002\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\021qq\"\005\017\016\003\tI!\001\005\002\003\017M+\027\017T5lKB\021!c\005\007\001\t\031!\002\001\"b\001+\t\t\021)\005\002\0273A\021!bF\005\0031\021\021qAT8uQ&tw\r\005\002\0135%\0211\004\002\002\004\003:L\bC\001\n\036\t\031q\002\001\"b\001?\t!!+\0329s#\t1\002EE\002\"\033\r2AA\t\001\001A\taAH]3gS:,W.\0328u}A\031a\002J\t\n\005\025\022!aA*fcB!abJ\t\035\023\tA#AA\tJi\026\024\030M\0317f!J|\0070\037'jW\026DQA\013\001\005\002-\na\001J5oSR$C#\001\027\021\005)i\023B\001\030\005\005\021)f.\033;\t\013A\002A\021I\031\002\tML'0Z\013\002eA\021!bM\005\003i\021\0211!\0238u\021\0251\004\001\"\0218\003\025!xnU3r+\005\031\003\"B\035\001\t\003\n\024A\0027f]\036$\b\016C\003<\001\021\005C(A\003baBd\027\020\006\002\022{!)aH\017a\001e\005\031\021\016\032=\t\013\001\003A\021I!\002\0331,gn\032;i\007>l\007/\031:f)\t\021$\tC\003D\001\007!'A\002mK:DQ!\022\001\005B\031\0131\"[:EK\032Lg.\0323BiR\021qI\023\t\003\025!K!!\023\003\003\017\t{w\016\\3b]\")1\n\022a\001e\005\t\001\020C\003N\001\021\005c*A\007tK\036lWM\034;MK:<G\017\033\013\004e=#\006\"\002)M\001\004\t\026!\0019\021\t)\021\026cR\005\003'\022\021\021BR;oGRLwN\\\031\t\013Uc\005\031\001\032\002\t\031\024x.\034\005\006/\002!\t\005W\001\raJ,g-\033=MK:<G\017\033\013\003eeCQ\001\025,A\002ECQa\027\001\005Bq\013!\"\0338eKb<\006.\032:f)\t\021T\fC\003Q5\002\007\021\013C\003\\\001\021\005s\fF\0023A\006DQ\001\0250A\002ECQ!\0260A\002IBQa\031\001\005B\021\fq!\0338eKb|e-\006\002fSR\021!G\032\005\006O\n\004\r\001[\001\005K2,W\016\005\002\023S\022)!N\031b\001W\n\t!)\005\002\0223!)1\r\001C![V\021a.\035\013\004e=\024\b\"B4m\001\004\001\bC\001\nr\t\025QGN1\001l\021\025)F\0161\0013\021\025!\b\001\"\021v\003-a\027m\035;J]\022,\007p\0244\026\005YLHC\001\032x\021\02597\0171\001y!\t\021\022\020B\003kg\n\0071\016C\003u\001\021\00530\006\002}R!!'`A\001\021\0259'\0201\001!\t\021r\020B\003ku\n\0071\016\003\004\002\004i\004\rAM\001\004K:$\007bBA\004\001\021\005\023\021B\001\017Y\006\034H/\0238eKb<\006.\032:f)\r\021\0241\002\005\007!\006\025\001\031A)\t\017\005\035\001\001\"\021\002\020Q)!'!\005\002\024!1\001+!\004A\002ECq!a\001\002\016\001\007!\007C\004\002\030\001!\t%!\007\002\017I,g/\032:tKV\tA\004C\004\002\036\001!\t%a\b\002\025I,g/\032:tK6\013\007/\006\004\002\"\005u\022q\005\013\005\003G\ty\004\006\003\002&\005-\002c\001\n\002(\0219\021\021FA\016\005\004)\"\001\002+iCRD\001\"!\f\002\034\001\017\021qF\001\003E\032\004\022\"!\r\0028q\tY$!\n\016\005\005M\"bAA\033\005\0059q-\0328fe&\034\027\002BA\035\003g\021AbQ1o\005VLG\016\032$s_6\0042AEA\037\t\031Q\0271\004b\001+!A\021\021IA\016\001\004\t\031%A\001g!\025Q!+EA\036\021\035\t9\005\001C!\003\023\nqB]3wKJ\034X-\023;fe\006$xN]\013\003\003\027\002BADA'#%\031\021q\n\002\003\021%#XM]1u_JDq!a\025\001\t\003\n)&\001\006ti\006\024Ho],ji\",B!a\026\002fQ)q)!\027\002h!A\0211LA)\001\004\ti&\001\003uQ\006$\b#\002\b\002`\005\r\024bAA1\005\t1q)\0328TKF\0042AEA3\t\031Q\027\021\013b\001+!9\021\021NA)\001\004\021\024AB8gMN,G\017C\004\002T\001!\t%!\034\026\t\005=\024q\017\013\004\017\006E\004\002CA.\003W\002\r!a\035\021\0139\ty&!\036\021\007I\t9\b\002\004k\003W\022\r!\006\005\b\003w\002A\021IA?\003!)g\016Z:XSRDW\003BA@\003\017#2aRAA\021!\tY&!\037A\002\005\r\005#\002\b\002`\005\025\005c\001\n\002\b\0221!.!\037C\002UAq!a#\001\t\003\ni)\001\007j]\022,\007p\0244TY&\034W-\006\003\002\020\006]Ec\001\032\002\022\"A\0211LAE\001\004\t\031\nE\003\017\003?\n)\nE\002\023\003/#aA[AE\005\004Y\007bBAF\001\021\005\0231T\013\005\003;\013)\013F\0033\003?\0139\013\003\005\002\\\005e\005\031AAQ!\025q\021qLAR!\r\021\022Q\025\003\007U\006e%\031A6\t\rU\013I\n1\0013\021\035\tY\013\001C!\003[\013\001\003\\1ti&sG-\032=PMNc\027nY3\026\t\005=\026q\027\013\004e\005E\006\002CA.\003S\003\r!a-\021\0139\ty&!.\021\007I\t9\f\002\004k\003S\023\ra\033\005\b\003W\003A\021IA^+\021\ti,!2\025\013I\ny,a2\t\021\005m\023\021\030a\001\003\003\004RADA0\003\007\0042AEAc\t\031Q\027\021\030b\001W\"9\0211AA]\001\004\021\004bBAf\001\021\005\023QZ\001\016G>tG/Y5ogNc\027nY3\026\t\005=\027q\033\013\004\017\006E\007\002CA.\003\023\004\r!a5\021\0139\ty&!6\021\007I\t9\016\002\004k\003\023\024\r!\006\005\b\0037\004A\021IAo\003!\031wN\034;bS:\034HcA$\002`\"1q-!7A\002eAq!a9\001\t\003\n)/A\003v]&|g.\006\004\002h\006U\030Q\036\013\005\003S\f9\020\006\003\002l\006=\bc\001\n\002n\0229\021\021FAq\005\004)\002\002CA\027\003C\004\035!!=\021\023\005E\022q\007\017\002t\006-\bc\001\n\002v\0221!.!9C\002-D\001\"a\027\002b\002\007\021\021 \t\006\035\005}\0231\037\005\b\003{\004A\021IA\000\003\021!\027N\0324\026\t\t\005!\021\002\013\0049\t\r\001\002CA.\003w\004\rA!\002\021\0139\tyFa\002\021\007I\021I\001\002\004k\003w\024\ra\033\005\b\005\033\001A\021\tB\b\003%Ig\016^3sg\026\034G/\006\003\003\022\teAc\001\017\003\024!A\0211\fB\006\001\004\021)\002E\003\017\003?\0229\002E\002\023\0053!aA\033B\006\005\004Y\007b\002B\017\001\021\005\023\021D\001\tI&\034H/\0338di\"9!\021\005\001\005B\t\r\022!\0029bi\016DWC\002B\023\005g\021Y\003\006\005\003(\tU\"q\007B\036)\021\021IC!\f\021\007I\021Y\003B\004\002*\t}!\031A\013\t\021\0055\"q\004a\002\005_\001\022\"!\r\0028q\021\tD!\013\021\007I\021\031\004\002\004k\005?\021\ra\033\005\007+\n}\001\031\001\032\t\021\t\005\"q\004a\001\005s\001RADA0\005cAqA!\020\003 \001\007!'\001\005sKBd\027mY3e\021\035\021\t\005\001C!\005\007\nq!\0369eCR,G-\006\004\003F\tM#1\n\013\007\005\017\022)F!\027\025\t\t%#Q\n\t\004%\t-CaBA\025\005\021\r!\006\005\t\003[\021y\004q\001\003PAI\021\021GA\0349\tE#\021\n\t\004%\tMCA\0026\003@\t\0071\016C\004\003X\t}\002\031\001\032\002\013%tG-\032=\t\017\035\024y\0041\001\003R!9!Q\f\001\005B\t}\023a\003\023qYV\034HeY8m_:,bA!\031\003p\t\035D\003\002B2\005c\"BA!\032\003jA\031!Ca\032\005\017\005%\"1\fb\001+!A\021Q\006B.\001\b\021Y\007E\005\0022\005]BD!\034\003fA\031!Ca\034\005\r)\024YF1\001l\021\0359'1\fa\001\005[BqA!\036\001\t\003\0229(A\006%G>dwN\034\023qYV\034XC\002B=\005\017\023y\b\006\003\003|\t%E\003\002B?\005\003\0032A\005B@\t\035\tICa\035C\002UA\001\"!\f\003t\001\017!1\021\t\n\003c\t9\004\bBC\005{\0022A\005BD\t\031Q'1\017b\001W\"9qMa\035A\002\t\025\005b\002BG\001\021\005#qR\001\006a\006$Gk\\\013\007\005#\023yJa&\025\r\tM%\021\025BR)\021\021)J!'\021\007I\0219\nB\004\002*\t-%\031A\013\t\021\0055\"1\022a\002\0057\003\022\"!\r\0028q\021iJ!&\021\007I\021y\n\002\004k\005\027\023\ra\033\005\007\007\n-\005\031\001\032\t\017\035\024Y\t1\001\003\036\"9!q\025\001\005B\t%\026aC2peJ,7\017]8oIN,BAa+\003:R!!Q\026B^)\r9%q\026\005\b!\n\025\006\031\001BY!\035Q!1W\t\0038\036K1A!.\005\005%1UO\\2uS>t'\007E\002\023\005s#aA\033BS\005\004)\002\002CA.\005K\003\rA!0\021\0139\tyFa.\t\017\t\005\007\001\"\021\003D\006A1o\034:u/&$\b\016F\002\035\005\013D\001Ba2\003@\002\007!\021Z\001\003YR\004bA\003BZ#E9\005b\002Bg\001\021\005#qZ\001\007g>\024HOQ=\026\t\tE'1\037\013\005\005'\024)\020F\002\035\005+D\001Ba6\003L\002\017!\021\\\001\004_J$\007C\002Bn\005W\024\tP\004\003\003^\n\035h\002\002Bp\005Kl!A!9\013\007\t\rh!\001\004=e>|GOP\005\002\013%\031!\021\036\003\002\017A\f7m[1hK&!!Q\036Bx\005!y%\017Z3sS:<'b\001Bu\tA\031!Ca=\005\r)\024YM1\001\026\021!\t\tEa3A\002\t]\b#\002\006S#\tE\bb\002B~\001\021\005#Q`\001\007g>\024H/\0323\026\t\t}8q\001\013\0049\r\005\001\002\003Bl\005s\004\035aa\001\021\r\tm'1^B\003!\r\0212q\001\003\007U\ne(\031A6\t\017\r-\001\001\"\021\004\016\0059\021N\0343jG\026\034XCAB\b!\021\021Yn!\005\n\t\rM!q\036\002\006%\006tw-\032\005\b\007/\001A\021IB\r\003\0211\030.Z<\026\005\rm!#BB\017\023\r\005b!\002\022\001\001\rm\021bAB\f\037A)aba\t\0229%\0311Q\005\002\003\017M+\027OV5fo\"91q\003\001\005B\r%BCBB\021\007W\031i\003\003\004V\007O\001\rA\r\005\b\007_\0319\0031\0013\003\025)h\016^5m\001")
/*    */ public interface SeqProxyLike<A, Repr extends SeqLike<A, Repr> & Seq<A>> extends SeqLike<A, Repr>, IterableProxyLike<A, Repr> {
/*    */   int size();
/*    */   
/*    */   Seq<A> toSeq();
/*    */   
/*    */   int length();
/*    */   
/*    */   A apply(int paramInt);
/*    */   
/*    */   int lengthCompare(int paramInt);
/*    */   
/*    */   boolean isDefinedAt(int paramInt);
/*    */   
/*    */   int segmentLength(Function1<A, Object> paramFunction1, int paramInt);
/*    */   
/*    */   int prefixLength(Function1<A, Object> paramFunction1);
/*    */   
/*    */   int indexWhere(Function1<A, Object> paramFunction1);
/*    */   
/*    */   int indexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*    */   
/*    */   <B> int indexOf(B paramB);
/*    */   
/*    */   <B> int indexOf(B paramB, int paramInt);
/*    */   
/*    */   <B> int lastIndexOf(B paramB);
/*    */   
/*    */   <B> int lastIndexOf(B paramB, int paramInt);
/*    */   
/*    */   int lastIndexWhere(Function1<A, Object> paramFunction1);
/*    */   
/*    */   int lastIndexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*    */   
/*    */   Repr reverse();
/*    */   
/*    */   <B, That> That reverseMap(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   Iterator<A> reverseIterator();
/*    */   
/*    */   <B> boolean startsWith(GenSeq<B> paramGenSeq, int paramInt);
/*    */   
/*    */   <B> boolean startsWith(GenSeq<B> paramGenSeq);
/*    */   
/*    */   <B> boolean endsWith(GenSeq<B> paramGenSeq);
/*    */   
/*    */   <B> int indexOfSlice(GenSeq<B> paramGenSeq);
/*    */   
/*    */   <B> int indexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
/*    */   
/*    */   <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq);
/*    */   
/*    */   <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
/*    */   
/*    */   <B> boolean containsSlice(GenSeq<B> paramGenSeq);
/*    */   
/*    */   boolean contains(Object paramObject);
/*    */   
/*    */   <B, That> That union(GenSeq<B> paramGenSeq, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B> Repr diff(GenSeq<B> paramGenSeq);
/*    */   
/*    */   <B> Repr intersect(GenSeq<B> paramGenSeq);
/*    */   
/*    */   Repr distinct();
/*    */   
/*    */   <B, That> That patch(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B, That> That updated(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B, That> That $plus$colon(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B, That> That $colon$plus(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B, That> That padTo(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<A, B, Object> paramFunction2);
/*    */   
/*    */   Repr sortWith(Function2<A, A, Object> paramFunction2);
/*    */   
/*    */   <B> Repr sortBy(Function1<A, B> paramFunction1, Ordering<B> paramOrdering);
/*    */   
/*    */   <B> Repr sorted(Ordering<B> paramOrdering);
/*    */   
/*    */   Range indices();
/*    */   
/*    */   Object view();
/*    */   
/*    */   SeqView<A, Repr> view(int paramInt1, int paramInt2);
/*    */   
/*    */   public class SeqProxyLike$$anonfun$lastIndexOf$1 extends AbstractFunction1<A, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object elem$1;
/*    */     
/*    */     public final boolean apply(Object x$1) {
/*    */       Object object;
/* 39 */       return (((object = this.elem$1) == x$1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, x$1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, x$1) : object.equals(x$1)))));
/*    */     }
/*    */     
/*    */     public SeqProxyLike$$anonfun$lastIndexOf$1(SeqProxyLike $outer, Object elem$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqProxyLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */