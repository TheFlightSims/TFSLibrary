package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.collection.parallel.ParIterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\tUbaB\001\003!\003\r\na\002\002\023\017\026tGK]1wKJ\034\030M\0317f\031&\\WM\003\002\004\t\005Q1m\0347mK\016$\030n\0348\013\003\025\tQa]2bY\006\034\001!F\002\t'\031\032B\001A\005\0163A\021!bC\007\002\t%\021A\002\002\002\004\003:L\bc\001\b\020#5\t!!\003\002\021\005\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\n!\tQq#\003\002\031\t\t9aj\034;iS:<\007\003\002\b\033#qI!a\007\002\003\035A\013'/\0317mK2L'0\0312mKB\031Q\004I\t\016\003yQ!a\b\002\002\021A\f'/\0317mK2L!!\t\020\003\027A\013'/\023;fe\006\024G.\032\005\006G\0011\t\001J\001\005e\026\004(/F\001&!\t\021b\005\002\004(\001\021\025\r!\006\002\005%\026\004(\017C\003*\001\031\005!&\001\003tSj,W#A\026\021\005)a\023BA\027\005\005\rIe\016\036\005\006_\0011\t\001M\001\005Q\026\fG-F\001\022\021\025\021\004A\"\0014\003)AW-\0313PaRLwN\\\013\002iA\031!\"N\t\n\005Y\"!AB(qi&|g\016C\0039\001\031\005\021(\001\njgR\023\030M^3sg\006\024G.Z!hC&tW#\001\036\021\005)Y\024B\001\037\005\005\035\021un\0347fC:DQA\020\001\007\002\021\nA\001^1jY\")\001\t\001D\001a\005!A.Y:u\021\025\021\005A\"\0014\003)a\027m\035;PaRLwN\034\005\006\t\0021\t\001J\001\005S:LG\017C\003G\001\031\005q)\001\003tG\006tWc\001%X\031R\021\021j\030\013\003\025j#\"a\023(\021\005IaE!B'F\005\004)\"\001\002+iCRDQaT#A\004A\0131a\0312g!\025\tF+\n,L\033\005\021&BA*\003\003\0359WM\\3sS\016L!!\026*\003\031\r\013gNQ;jY\0224%o\\7\021\005I9F!\002-F\005\004I&!\001\"\022\005EI\001\"B.F\001\004a\026AA8q!\025QQL\026,W\023\tqFAA\005Gk:\034G/[8oe!)\001-\022a\001-\006\t!\020C\003c\001\031\0051-\001\005tG\006tG*\0324u+\r!W\016\033\013\003KB$\"A\0328\025\005\035L\007C\001\ni\t\025i\025M1\001\026\021\025Q\027\rq\001l\003\t\021g\rE\003R)\026bw\r\005\002\023[\022)\001,\031b\001+!)1,\031a\001_B)!\"\0307\022Y\")\001-\031a\001Y\")!\017\001D\001g\006I1oY1o%&<\007\016^\013\004irDHCA;\000)\t1X\020\006\002xsB\021!\003\037\003\006\033F\024\r!\006\005\006UF\004\035A\037\t\006#R+3p\036\t\003%q$Q\001W9C\002UAQaW9A\002y\004RAC/\022wnDQ\001Y9A\002mDs!]A\002\003\037\t\031\002\005\003\002\006\005-QBAA\004\025\r\tI\001B\001\013C:tw\016^1uS>t\027\002BA\007\003\017\021\021\"\\5he\006$\030n\0348\"\005\005E\021\001\033+iK\002\022W\r[1wS>\024\be\0344!AN\034\027M\034*jO\"$\b\r\t5bg\002\032\007.\0318hK\022t\003\005\0265fAA\024XM^5pkN\004#-\0325bm&|'\017I2b]\002\022W\r\t:faJ|G-^2fI\002:\030\016\0365!g\016\fgNU5hQRt#/\032<feN,g&\t\002\002\026\005)!GL\035/a!9\021\021\004\001\007\002\005m\021a\0024pe\026\f7\r[\013\005\003;\t\t\004\006\003\002 \005\025\002c\001\006\002\"%\031\0211\005\003\003\tUs\027\016\036\005\t\003O\t9\0021\001\002*\005\ta\r\005\004\013\003W\t\022qF\005\004\003[!!!\003$v]\016$\030n\03482!\r\021\022\021\007\003\b\003g\t9B1\001\026\005\005)\006bBA\034\001\031\005\021\021H\001\004[\006\004XCBA\036\003\023\n\t\005\006\003\002>\005-C\003BA \003\007\0022AEA!\t\031i\025Q\007b\001+!9!.!\016A\004\005\025\003cB)UK\005\035\023q\b\t\004%\005%CA\002-\0026\t\007Q\003\003\005\002(\005U\002\031AA'!\031Q\0211F\t\002H!9\021\021\013\001\007\002\005M\023aB2pY2,7\r^\013\007\003+\n\031'a\027\025\t\005]\023Q\r\013\005\0033\ni\006E\002\023\0037\"a!TA(\005\004)\002b\0026\002P\001\017\021q\f\t\b#R+\023\021MA-!\r\021\0221\r\003\0071\006=#\031A\013\t\021\005\035\024q\na\001\003S\n!\001\0354\021\r)\tY'EA1\023\r\ti\007\002\002\020!\006\024H/[1m\rVt7\r^5p]\"9\021\021\017\001\007\002\005M\024a\0024mCRl\025\r]\013\007\003k\n\031)a\037\025\t\005]\024Q\021\013\005\003s\ni\bE\002\023\003w\"a!TA8\005\004)\002b\0026\002p\001\017\021q\020\t\b#R+\023\021QA=!\r\021\0221\021\003\0071\006=$\031A\013\t\021\005\035\022q\016a\001\003\017\003bACA\026#\005%\005\003\002\b\020\003\003Cq!!$\001\r\003\ty)\001\006%a2,8\017\n9mkN,b!!%\002 \006]E\003BAJ\003C#B!!&\002\032B\031!#a&\005\r5\013YI1\001\026\021\035Q\0271\022a\002\0037\003r!\025+&\003;\013)\nE\002\023\003?#a\001WAF\005\004I\006\002CAR\003\027\003\r!!*\002\tQD\027\r\036\t\005\035=\ti\nC\004\002*\0021\t!a+\002\r\031LG\016^3s)\r)\023Q\026\005\t\003_\0139\0131\001\0022\006!\001O]3e!\025Q\0211F\t;\021\035\t)\f\001D\001\003o\013\021BZ5mi\026\024hj\034;\025\007\025\nI\f\003\005\0020\006M\006\031AAY\021\035\ti\f\001D\001\003\013\021\002]1si&$\030n\0348\025\t\005\005\027q\031\t\006\025\005\rW%J\005\004\003\013$!A\002+va2,'\007\003\005\0020\006m\006\031AAY\021\035\tY\r\001D\001\003\033\fqa\032:pkB\024\0250\006\003\002P\006eG\003BAi\003;\004bADAj\003/,\023bAAk\005\t1q)\0328NCB\0042AEAm\t\035\tY.!3C\002U\021\021a\023\005\t\003O\tI\r1\001\002`B1!\"a\013\022\003/Dq!a9\001\r\003\t)/\001\003uC.,GcA\023\002h\"9\021\021^Aq\001\004Y\023!\0018\t\017\0055\bA\"\001\002p\006!AM]8q)\r)\023\021\037\005\b\003S\fY\0171\001,\021\035\t)\020\001D\001\003o\fQa\0357jG\026$R!JA}\003{Dq!a?\002t\002\0071&\001\005v]\016|fM]8n\021\035\ty0a=A\002-\n\021\"\0368d?VtG/\0337\t\017\t\r\001A\"\001\003\006\00591\017\0357ji\006#H\003BAa\005\017Aq!!;\003\002\001\0071\006C\004\003\f\0011\tA!\004\002\023Q\f7.Z,iS2,GcA\023\003\020!A\021q\026B\005\001\004\t\t\fC\004\003\024\0011\tA!\006\002\tM\004\030M\034\013\005\003\003\0249\002\003\005\0020\nE\001\031AAY\021\035\021Y\002\001D\001\005;\t\021\002\032:pa^C\027\016\\3\025\007\025\022y\002\003\005\0020\ne\001\031AAY\021\035\021\031\003\001D\001\005K\tAb\035;sS:<\007K]3gSb,\"Aa\n\021\t\t%\"q\006\b\004\025\t-\022b\001B\027\t\0051\001K]3eK\032LAA!\r\0034\t11\013\036:j]\036T1A!\f\005\001")
public interface GenTraversableLike<A, Repr> extends GenTraversableOnce<A>, Parallelizable<A, ParIterable<A>> {
  Repr repr();
  
  int size();
  
  A head();
  
  Option<A> headOption();
  
  boolean isTraversableAgain();
  
  Repr tail();
  
  A last();
  
  Option<A> lastOption();
  
  Repr init();
  
  <B, That> That scan(B paramB, Function2<B, B, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That scanLeft(B paramB, Function2<B, A, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That scanRight(B paramB, Function2<A, B, B> paramFunction2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <U> void foreach(Function1<A, U> paramFunction1);
  
  <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That collect(PartialFunction<A, B> paramPartialFunction, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That $plus$plus(GenTraversableOnce<B> paramGenTraversableOnce, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  Repr filter(Function1<A, Object> paramFunction1);
  
  Repr filterNot(Function1<A, Object> paramFunction1);
  
  Tuple2<Repr, Repr> partition(Function1<A, Object> paramFunction1);
  
  <K> GenMap<K, Repr> groupBy(Function1<A, K> paramFunction1);
  
  Repr take(int paramInt);
  
  Repr drop(int paramInt);
  
  Repr slice(int paramInt1, int paramInt2);
  
  Tuple2<Repr, Repr> splitAt(int paramInt);
  
  Repr takeWhile(Function1<A, Object> paramFunction1);
  
  Tuple2<Repr, Repr> span(Function1<A, Object> paramFunction1);
  
  Repr dropWhile(Function1<A, Object> paramFunction1);
  
  String stringPrefix();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenTraversableLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */