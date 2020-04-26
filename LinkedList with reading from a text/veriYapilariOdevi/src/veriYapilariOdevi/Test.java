/**
* @file Test.java
* @description Test sınıfı
* @assignment Odev 1
* @date 16.04.2020
* @author Mert İshak Kılıç mertishak.kilic@stu.fsm.edu.tr 
*/
package veriYapilariOdevi;

import java.io.IOException;

/**
 *
 * @author Mert
 */
public class Test {

    public static void main(String[] args) throws IOException {

        LinkedList liste = new LinkedList();

        liste.anaNode_olustur();
        liste.print();

        liste.ardisikKarakterler('v');
        liste.enCokArdisik();
        liste.enCokArdisik('l');
        liste.enAzArdisik();
        liste.enAzArdisik('i');

    }

}
