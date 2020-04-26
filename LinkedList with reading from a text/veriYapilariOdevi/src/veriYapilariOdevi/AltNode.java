/**
* @file AltNode.java
* @description listenin düğümlerinin altDüğümlerini tutmak icin olusturulan AltNode objesi 
* @assignment Odev 1
* @date 16.04.2020
* @author Mert İshak Kılıç mertishak.kilic@stu.fsm.edu.tr 
*/
package veriYapilariOdevi;

/**
 *
 * @author Mert
 */
public class AltNode {
    
    char data;
    AltNode nextNode;
    int adet;
    
    public AltNode(char data){
        this.data = data;
    }
    
}
