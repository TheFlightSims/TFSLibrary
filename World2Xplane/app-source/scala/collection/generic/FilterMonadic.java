package scala.collection.generic;

import scala.Function1;
import scala.collection.GenTraversableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]3q!\001\002\021\002G\005\021BA\007GS2$XM]'p]\006$\027n\031\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013]\r\032\"\001A\006\021\0051iQ\"\001\004\n\00591!aA!os\")\001\003\001D\001#\005\031Q.\0319\026\007I1c\003\006\002\024QQ\021A\003\b\t\003+Ya\001\001B\003\030\037\t\007\001D\001\003UQ\006$\030CA\r\f!\ta!$\003\002\034\r\t9aj\034;iS:<\007\"B\017\020\001\bq\022A\0012g!\025y\002EI\023\025\033\005\021\021BA\021\003\0051\031\025M\034\"vS2$gI]8n!\t)2\005\002\004%\001\021\025\r\001\007\002\005%\026\004(\017\005\002\026M\021)qe\004b\0011\t\t!\tC\003*\037\001\007!&A\001g!\021a1&L\023\n\00512!!\003$v]\016$\030n\03482!\t)b\006\002\0040\001\021\025\r\001\007\002\002\003\")\021\007\001D\001e\0059a\r\\1u\033\006\004XcA\032;mQ\021Ag\017\013\003k]\002\"!\006\034\005\013]\001$\031\001\r\t\013u\001\0049\001\035\021\013}\001#%O\033\021\005UQD!B\0241\005\004A\002\"B\0251\001\004a\004\003\002\007,[u\0022AP :\033\005!\021B\001!\005\005I9UM\034+sCZ,'o]1cY\026|enY3\t\013\t\003a\021A\"\002\017\031|'/Z1dQV\021Ai\023\013\003\013\"\003\"\001\004$\n\005\0353!\001B+oSRDQ!K!A\002%\003B\001D\026.\025B\021Qc\023\003\006\031\006\023\r\001\007\002\002+\")a\n\001D\001\037\006Qq/\033;i\r&dG/\032:\025\005A\013\006\003B\020\001[\tBQAU'A\002M\013\021\001\035\t\005\031-jC\013\005\002\r+&\021aK\002\002\b\005>|G.Z1o\001")
public interface FilterMonadic<A, Repr> {
  <B, That> That map(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <B, That> That flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
  
  <U> void foreach(Function1<A, U> paramFunction1);
  
  FilterMonadic<A, Repr> withFilter(Function1<A, Object> paramFunction1);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\FilterMonadic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */