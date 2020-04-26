/**
* @file Node.java
* @description listenin düğümlerini tutmak icin olusturulan Node objesi
* @assignment Odev 1
* @date 16.04.2020
* @author Mert İshak Kılıç mertishak.kilic@stu.fsm.edu.tr 
*/
package veriYapilariOdevi;

/**
 *
 * @author Mert
 */
public class Node {
    
    char data;
    Node nextNode;
    AltNode altNode;
    
    public Node(char data){
        this.data = data;
    }
    
}
