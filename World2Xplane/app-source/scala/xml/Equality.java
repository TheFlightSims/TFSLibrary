package scala.xml;

import scala.Equals;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]<Q!\001\002\t\002\035\t\001\"R9vC2LG/\037\006\003\007\021\t1\001_7m\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\t\013F,\030\r\\5usN\021\021\002\004\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007\"B\t\n\t\003\021\022A\002\037j]&$h\bF\001\b\021\025!\022\002\"\001\026\003\025\t7OU3g)\taa\003C\003\030'\001\007\001$A\001y!\ti\021$\003\002\033\t\t\031\021I\\=\t\013qIA\021A\017\002\037\r|W\016]1sK\nc\027\016\0365fYf$2AH\021$!\tiq$\003\002!\t\t9!i\\8mK\006t\007\"\002\022\034\001\004a\021A\001=2\021\025!3\0041\001&\003\tA(\007\005\002'S9\021QbJ\005\003Q\021\ta\001\025:fI\0264\027B\001\026,\005\031\031FO]5oO*\021\001\006\002\005\0069%!\t!\f\013\004=9z\003\"\002\022-\001\004a\001\"\002\023-\001\004\001\004C\001\0052\023\t\021$A\001\003O_\022,\007\"\002\017\n\t\003!Dc\001\0206m!)!e\ra\001\031!)Ae\ra\001\031\0319!B\001I\001\004\003A4cA\034\rsA\021QBO\005\003w\021\021a!R9vC2\034\b\"B\0378\t\003q\024A\002\023j]&$H\005F\001@!\ti\001)\003\002B\t\t!QK\\5u\021\025\031uG\"\005E\003A\021\027m]5t\r>\024\b*Y:i\007>$W-F\001F!\r1e\n\007\b\003\0172s!\001S&\016\003%S!A\023\004\002\rq\022xn\034;?\023\005)\021BA'\005\003\035\001\030mY6bO\026L!a\024)\003\007M+\027O\003\002N\t!)!k\016D\001'\006i1\017\036:jGR|F%Z9%KF$\"A\b+\t\013U\013\006\031\001,\002\013=$\b.\032:\021\005!9\004\"\002-8\t\003I\026aD:ue&\034Go\030\023cC:<G%Z9\025\005yQ\006\"B+X\001\0041\006\"\002/8\t\003j\026\001C2b]\026\013X/\0317\025\005yq\006\"B+\\\001\004A\002\"\00218\t\003\n\027\001\0035bg\"\034u\016Z3\025\003\t\004\"!D2\n\005\021$!aA%oi\")am\016C!O\0061Q-];bYN$\"A\b5\t\013U+\007\031\001\r\t\013)<DQA6\002\025alGn\030\023fc\022*\027\017\006\002\037Y\")Q+\033a\0011!)an\016C\003_\006a\0010\0347`I\t\fgn\032\023fcR\021a\004\035\005\006+6\004\r\001\007\005\006e^\"Ia]\001\rI>\034u.\0349be&\034xN\034\013\004=Q,\b\"B+r\001\004A\002\"\002<r\001\004q\022A\0022mSRDW\r")
public interface Equality extends Equals {
  Seq<Object> basisForHashCode();
  
  boolean strict_$eq$eq(Equality paramEquality);
  
  boolean strict_$bang$eq(Equality paramEquality);
  
  boolean canEqual(Object paramObject);
  
  int hashCode();
  
  boolean equals(Object paramObject);
  
  boolean xml_$eq$eq(Object paramObject);
  
  boolean xml_$bang$eq(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Equality.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */