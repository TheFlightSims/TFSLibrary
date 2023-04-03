package scala.collection.mutable;

import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes = "\006\001q;Q!\001\002\t\n&\tA\001T3bM*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001##\016\005\021aU-\0314\024\013-q!\003G\016\021\005=\001R\"\001\004\n\005E1!AB!osJ+g\rE\002\013'UI!\001\006\002\003\017\0053F\n\026:fKB\021qBF\005\003/\031\021qAT8uQ&tw\r\005\002\0203%\021!D\002\002\b!J|G-^2u!\tyA$\003\002\036\r\ta1+\032:jC2L'0\0312mK\")qd\003C\001A\0051A(\0338jiz\"\022!\003\005\bE-\021\r\021\"\021$\003\035\021\027\r\\1oG\026,\022\001\n\t\003\037\025J!A\n\004\003\007%sG\017\003\004)\027\001\006I\001J\001\tE\006d\027M\\2fA!9!f\003b\001\n\003\032\023!\0023faRD\007B\002\027\fA\003%A%\001\004eKB$\b\016\t\005\b]-\t\t\021\"\0210\0035\001(o\0343vGR\004&/\0324jqV\t\001\007\005\0022m5\t!G\003\0024i\005!A.\0318h\025\005)\024\001\0026bm\006L!a\016\032\003\rM#(/\0338h\021\035I4\"!A\005\002\r\nA\002\035:pIV\034G/\021:jifDqaO\006\002\002\023\005A(\001\bqe>$Wo\031;FY\026lWM\034;\025\005u\002\005CA\b?\023\tydAA\002B]fDq!\021\036\002\002\003\007A%A\002yIEBqaQ\006\002\002\023\005C)A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005)\005c\001$H{5\tA!\003\002I\t\tA\021\n^3sCR|'\017C\004K\027\005\005I\021A&\002\021\r\fg.R9vC2$\"\001T(\021\005=i\025B\001(\007\005\035\021un\0347fC:Dq!Q%\002\002\003\007Q\bC\004R\027\005\005I\021\t*\002\021!\f7\017[\"pI\026$\022\001\n\005\b).\t\t\021\"\021V\003!!xn\025;sS:<G#\001\031\t\017][\021\021!C\0051\006Y!/Z1e%\026\034x\016\034<f)\005I\006CA\031[\023\tY&G\001\004PE*,7\r\036")
public final class Leaf {
  public static <B> Node<B> doubleRightRotation() {
    return Leaf$.MODULE$.doubleRightRotation();
  }
  
  public static <B> Node<B> doubleLeftRotation() {
    return Leaf$.MODULE$.doubleLeftRotation();
  }
  
  public static <B> Node<B> rightRotation() {
    return Leaf$.MODULE$.rightRotation();
  }
  
  public static <B> Node<B> leftRotation() {
    return Leaf$.MODULE$.leftRotation();
  }
  
  public static <B> AVLTree<B> rebalance() {
    return Leaf$.MODULE$.rebalance();
  }
  
  public static <B> Tuple2<B, AVLTree<B>> removeMax() {
    return Leaf$.MODULE$.removeMax();
  }
  
  public static <B> Tuple2<B, AVLTree<B>> removeMin() {
    return Leaf$.MODULE$.removeMin();
  }
  
  public static <B> AVLTree<Nothing$> remove(B paramB, Ordering<B> paramOrdering) {
    return Leaf$.MODULE$.remove(paramB, paramOrdering);
  }
  
  public static <B> AVLTree<B> insert(B paramB, Ordering<B> paramOrdering) {
    return Leaf$.MODULE$.insert(paramB, paramOrdering);
  }
  
  public static <B> boolean contains(B paramB, Ordering<B> paramOrdering) {
    return Leaf$.MODULE$.contains(paramB, paramOrdering);
  }
  
  public static <B> Iterator<B> iterator() {
    return Leaf$.MODULE$.iterator();
  }
  
  public static String toString() {
    return Leaf$.MODULE$.toString();
  }
  
  public static int hashCode() {
    return Leaf$.MODULE$.hashCode();
  }
  
  public static boolean canEqual(Object paramObject) {
    return Leaf$.MODULE$.canEqual(paramObject);
  }
  
  public static Iterator<Object> productIterator() {
    return Leaf$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return Leaf$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return Leaf$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return Leaf$.MODULE$.productPrefix();
  }
  
  public static int depth() {
    return Leaf$.MODULE$.depth();
  }
  
  public static int balance() {
    return Leaf$.MODULE$.balance();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Leaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */