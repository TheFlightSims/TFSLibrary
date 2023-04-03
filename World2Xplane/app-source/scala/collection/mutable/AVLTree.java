package scala.collection.mutable;

import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005Ub\001C\001\003!\003\r\tC\001\005\003\017\0053F\n\026:fK*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\013\003\0239\0322\001\001\006\017!\tYA\"D\001\007\023\tiaA\001\004B]f\024VM\032\t\003\027=I!\001\005\004\003\031M+'/[1mSj\f'\r\\3\t\013I\001A\021\001\013\002\r\021Jg.\033;%\007\001!\022!\006\t\003\027YI!a\006\004\003\tUs\027\016\036\005\0063\0011\tAG\001\bE\006d\027M\\2f+\005Y\002CA\006\035\023\tibAA\002J]RDQa\b\001\007\002i\tQ\001Z3qi\"DQ!\t\001\005\002\t\n\001\"\033;fe\006$xN]\013\003G)*\022\001\n\t\004K\031BS\"\001\003\n\005\035\"!\001C%uKJ\fGo\034:\021\005%RC\002\001\003\006W\001\022\r\001\f\002\002\005F\021Q\006\016\t\003S9\"aa\f\001\005\006\004\001$!A!\022\005E\"\004CA\0063\023\t\031dAA\004O_RD\027N\\4\021\005-)\024B\001\034\007\005\r\te.\037\005\006q\001!\t!O\001\tG>tG/Y5ogV\021!(\021\013\004wy\022\005CA\006=\023\tidAA\004C_>dW-\0318\t\013}:\004\031\001!\002\013Y\fG.^3\021\005%\nE!B\0268\005\004a\003\"B\"8\001\004!\025\001C8sI\026\024\030N\\4\021\007\025C\005I\004\002\f\r&\021qIB\001\ba\006\0347.Y4f\023\tI%J\001\005Pe\022,'/\0338h\025\t9e\001C\003M\001\021\005Q*\001\004j]N,'\017^\013\003\035J#2aT*U!\r\001\006!U\007\002\005A\021\021F\025\003\006W-\023\r\001\f\005\006-\003\r!\025\005\006\007.\003\r!\026\t\004\013\"\013\006\"B,\001\t\003A\026A\002:f[>4X-\006\002Z;R\031!l\0270\021\007A\003Q\006C\003@-\002\007A\f\005\002*;\022)1F\026b\001Y!)1I\026a\001?B\031Q\t\023/\t\013\005\004A\021\0012\002\023I,Wn\034<f\033&tWCA2i+\005!\007\003B\006fO&L!A\032\004\003\rQ+\b\017\\33!\tI\003\016B\003,A\n\007A\006E\002Q\001\035DQa\033\001\005\0021\f\021B]3n_Z,W*\031=\026\0055\004X#\0018\021\t-)w.\035\t\003SA$Qa\0136C\0021\0022\001\025\001p\021\025\031\b\001\"\001u\003%\021XMY1mC:\034W-\006\002vqV\ta\017E\002Q\001]\004\"!\013=\005\013-\022(\031\001\027\t\013i\004A\021A>\002\0311,g\r\036*pi\006$\030n\0348\026\007q\f\031!F\001~!\021\001f0!\001\n\005}\024!\001\002(pI\026\0042!KA\002\t\025Y\023P1\001-\021\035\t9\001\001C\001\003\023\tQB]5hQR\024v\016^1uS>tW\003BA\006\003#)\"!!\004\021\tAs\030q\002\t\004S\005EAAB\026\002\006\t\007A\006C\004\002\026\001!\t!a\006\002%\021|WO\0317f\031\0264GOU8uCRLwN\\\013\005\0033\ty\"\006\002\002\034A!\001K`A\017!\rI\023q\004\003\007W\005M!\031\001\027\t\017\005\r\002\001\"\001\002&\005\031Bm\\;cY\026\024\026n\0325u%>$\030\r^5p]V!\021qEA\027+\t\tI\003\005\003Q}\006-\002cA\025\002.\02111&!\tC\0021JC\001AA\031}*\031\0211\007\002\002\t1+\027M\032")
public interface AVLTree<A> extends Serializable {
  int balance();
  
  int depth();
  
  <B> Iterator<B> iterator();
  
  <B> boolean contains(B paramB, Ordering<B> paramOrdering);
  
  <B> AVLTree<B> insert(B paramB, Ordering<B> paramOrdering);
  
  <B> AVLTree<A> remove(B paramB, Ordering<B> paramOrdering);
  
  <B> Tuple2<B, AVLTree<B>> removeMin();
  
  <B> Tuple2<B, AVLTree<B>> removeMax();
  
  <B> AVLTree<B> rebalance();
  
  <B> Node<B> leftRotation();
  
  <B> Node<B> rightRotation();
  
  <B> Node<B> doubleLeftRotation();
  
  <B> Node<B> doubleRightRotation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AVLTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */