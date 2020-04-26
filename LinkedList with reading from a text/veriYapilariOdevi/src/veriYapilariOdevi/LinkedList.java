/**
* @file LinkedList.java
* @description Linkedlistin kendisi
* @assignment Odev 1
* @date 16.04.2020
* @author Mert İshak Kılıç mertishak.kilic@stu.fsm.edu.tr 
*/
package veriYapilariOdevi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mert
 */
public class LinkedList {

    Node head;

    public void anaNode_olustur() throws IOException {

        File file = new File("veri.txt");
        if (!file.exists()) { // dosyanın var olup olmadığını kontrol etmek için
            System.out.println("Dosya yok!");
        } else {

            FileReader inputStream = new FileReader(file); // dosyayı karakter karakter okumak için
            int i;
            while ((i = inputStream.read()) != -1) { // dosyanın sonuna gelene kadar dönücek
                anaListe((char) i); // dosyadan okunan her karakter ile ana liste metotunu çalıştırıcak 
            }
        }
        altListe(); // ana listeye alt liste ekliyecek
    }

    public void anaListe(char harf) {

        if (harf >= 65 && harf <= 90) { // dosyadan okunan karakter büyük harf ise küçük harfe dönüştürüyor
            harf += 32;
        }
        if (harf >= 97 && harf <= 122) {//içeri sadece küçük harfler gireiblir
            Node newNode = new Node(harf); // listeye eklenicek karakterin Node unu oluşturuyor

            if (isEmpty()) { // eğer liste boşsa direk head e ekliyor
                head = newNode;
            } else {
                Node temp = head; // liste boş değilse bir temp ile head tutularak liste içinde dönüp son elemanın sonrasına ekleme işlemi yapılacak
                if (head.data != newNode.data) {
                    while (temp.nextNode != null && temp.nextNode.data != newNode.data) { // tempin bir sonraki nodu kontrol edilerek null veya eklenicek node a eşit olana kadar dönücek
                        temp = temp.nextNode;                                               // böylelikle aynı elemanın listede olmamasını da sağlıyorum
                    }
                    if (temp.nextNode == null) { // listenin sonuna gelindiğinde yeni nodu ekler
                        temp.nextNode = newNode;
                    }
                }
            }
        }
    }

    public void altListe() throws IOException {
        File file = new File("veri.txt");
        if (!file.exists()) { // dosyanın var olup olmadığını kontrol etmek için
            System.out.println("Dosya yok!");
        } else {

            FileReader inputStream = new FileReader(file); // dosyayı karakter karakter okumak için
            int i;
            boolean harfAynı = false;  
            char karakter = ' ';
            char dizi[];
            while ((i = inputStream.read()) != -1) { // dosyanın sonuna gelene kadar dönücek
                char harf = (char) i;
                if (harfMi(harf)) { // gelen karakterin harf olup olmadığını kontrol ediyor
                    if (harf >= 65 && harf <= 90) { // dosyadan okunan karakter büyük harf ise küçük harfe dönüştürüyor
                        harf += 32;
                    }
                    Node temp = head;
                    while (temp != null) { // analisteyi dönüyor
                        if (harfAynı && karakter == temp.data) { 
                            if (temp.altNode == null) { // eğer hiç alt node yok ise
                                AltNode newNode = new AltNode(harf); 
                                temp.altNode = newNode; // direk ilk altNode a ekleme yapılır
                                temp.altNode.adet = 1; // ve default 0 olan adeti 1 olur
                            } else { // eğer altNode varsa 
                                AltNode gecici = temp.altNode;
                                while (gecici.nextNode != null) { // altNode lar dönülür
                                    if (gecici.data == harf) { // eğer eklenicek altNode zaten varsa sadece adedi artılır
                                        gecici.adet++; 
                                    } else { 
                                        gecici = gecici.nextNode;
                                    }
                                }
                                if (gecici.data == harf) { // eğer var olan altNode ilk node ise buradan sadece adedi artılır
                                    gecici.adet++;
                                } else { // eğer eklenicek altNode yoksa buradan eklenir
                                    AltNode newNode = new AltNode(harf);
                                    gecici.nextNode = newNode;
                                    gecici.nextNode.adet += 1;
                                }
                            }
                            harfAynı = false; // en sonunda sıfırlamalar yapılır
                            karakter = ' ';
                        }
                        temp = temp.nextNode;
                    }
                    if (karakter == ' ') { // eğer herhangi bir Node a altNode eklendiyse buraya girer 
                        Node temp2 = head; 
                        while (temp2 != null) { 
                            if (harf == temp2.data) {
                                harfAynı = true; // bunu yaparak bir sonraki dönüşte altnode eklemesi yapılması sağlanıyor
                                karakter = temp2.data; // karakter node u tutuyo bir sonraki dönüşte önceki node u kontrol etmeye yarıyo
                                break;
                            }
                            temp2 = temp2.nextNode;
                        }
                    }

                } else { // eğer okunan karakter harf değilse sıfırlamalar yapılır
                    harfAynı = false;
                    karakter = ' ';
                }
            }
        }

    }

    public void ardisikKarakterler(char k) {

        if (isEmpty()) { // liste boş ise
            System.out.println("boş");
        } else {
            Node temp = head;
            while (temp != null && temp.data != k) { // listeyi dönüp k daki harfi arıyor
                temp = temp.nextNode;
            }
            if (temp != null) { // k yı buldu mu diye kontrol
                AltNode temp2 = temp.altNode; // temp in altnode unu tutmak için bir başka temp değişken
                if (temp2 == null) { // eğer null ise 
                    System.out.println("sonra gelen harf yok");
                } else { 
                    System.out.print(k + " 'dan sonra gelen harfler : ");
                    while (temp2 != null) { // altNode lar dönülerek yazdırılır
                        System.out.print(temp2.data + " ");
                        temp2 = temp2.nextNode;
                    }
                    System.out.println("");
                }
            }
        }

    }

    public void enCokArdisik() {
        int sayac = 0; 
        AltNode temp3 = new AltNode(' '); // en cok ardışık olan altNode
        Node temp4 = new Node(' '); // en çok ardışık olan Node
        if (isEmpty()) {
            System.out.println("boş");
        } else {
            Node temp = head;
            while (temp.nextNode != null) { // Liste dönülür
                AltNode temp2 = temp.altNode; 
                if (temp2 != null) { // en çoğu aradığımız için altNode un var olması gerekir
                    while (temp2 != null) { // altNode ların sonuna kadar döner
                        if (sayac < temp2.adet) { // altNode un adedi eğer sayactan daha fazla ise sayac o sayı olur
                            sayac = temp2.adet;
                            temp3 = temp2; // en cok ardışık olan altNode
                            temp4 = temp; // en çok ardışık olan Node
                        }
                        temp2 = temp2.nextNode;
                    }
                }
                temp = temp.nextNode;
            }
        }
        System.out.println("en çok peş peşe gelen karakter ikilisi : " + temp4.data + " ve " + temp3.data);
    }

    public void enCokArdisik(char k) {
        AltNode temp3 = new AltNode(' '); // en cok ardışık olan altNode
        int sayac = 0;
        if (isEmpty()) {
            System.out.println("boş");
        } else {
            Node temp = head;
            while (temp != null && temp.data != k) { // listede k değiskenindeki harf aranıyor
                temp = temp.nextNode;
            }
            if (temp != null) { // eğer harf bulunursa
                AltNode temp2 = temp.altNode; 
                while (temp2 != null) { // altNode lar dönülür
                    if (sayac < temp2.adet) { // altNode un adedi eğer sayactan daha fazla ise sayac o sayı olur 
                        temp3 = temp2; // en cok ardışık olan altNode
                        sayac = temp3.adet;
                    }
                    temp2 = temp2.nextNode;
                }
            }
        }
        System.out.println(k + " karakterinin peşi sıra en çok gelen karakter : " + temp3.data);

    }

    public void enAzArdisik() {
        int sayac = 10;
        AltNode temp3 = new AltNode(' '); // en az ardışık olan altNode
        Node temp4 = new Node(' '); // en az ardışık olan Node
        if (isEmpty()) {
            System.out.println("boş");
        } else {
            Node temp = head;
            while (temp.nextNode != null) { // altNode un var olması gerekir
                AltNode temp2 = temp.altNode;
                if (temp2 != null) { // altNode ların sonuna kadar döner
                    while (temp2 != null) {
                        if (sayac > temp2.adet) { // altNode un adedi eğer sayactan daha az ise sayac o sayı olur
                            sayac = temp2.adet;
                            temp3 = temp2; // en az ardışık olan altNode
                            temp4 = temp; // en az ardışık olan Node
                        }
                        temp2 = temp2.nextNode;
                    }
                }
                temp = temp.nextNode;
            }
        }
        System.out.println("en az peş peşe gelen karakter ikilisi : " + temp4.data + " ve " + temp3.data);
    }

    public void enAzArdisik(char k) {
        AltNode temp3 = new AltNode(' '); // en az ardışık olan altNode
        int sayac = 10;
        if (isEmpty()) {
            System.out.println("boş");
        } else {
            Node temp = head;
            while (temp != null && temp.data != k) { // listede k değiskenindeki harf aranıyor
                temp = temp.nextNode;
            }
            if (temp != null) { // altNode lar dönülür
                AltNode temp2 = temp.altNode;
                while (temp2 != null) {
                    if (sayac > temp2.adet) { // altNode un adedi eğer sayactan daha az ise sayac o sayı olur 
                        temp3 = temp2; // en cok ardışık olan altNode
                        sayac = temp3.adet;
                    }
                    temp2 = temp2.nextNode;
                }
            }
        }
        System.out.println(k + " karakterinin peşi sıra en az gelen karakter : " + temp3.data);
    }

    public boolean harfMi(char harf) { 
        if (harf >= 65 && harf <= 90 || harf >= 97 && harf <= 122) { // ascii koduna göre büyük veya küçük harf olup olmadığına bakar
            return true;
        } else {
            return false;
        }
    }

    public void print() { // listeyi ve altNode ları basar

        if (isEmpty()) {
            System.out.println("Liste boş");
        } else {
            Node tmpAnaNode = head;
            while (tmpAnaNode != null) {//ana dugumleri dolasiyorum
                System.out.print(tmpAnaNode.data + "(");//ana dugumun harfini basiyorum simdi sira liste dugumunde

                AltNode tmpListNode = tmpAnaNode.altNode;

                while (tmpListNode != null) {//liste dugumunu dolasiyorum harfini ve adetini bastiriyorum
                    System.out.print(tmpListNode.data + "," + tmpListNode.adet + " ->");

                    tmpListNode = tmpListNode.nextNode;
                }
                System.out.print("null) -> ");

                tmpAnaNode = tmpAnaNode.nextNode;
            }
            System.out.println(" null");
        }

    }

    boolean isEmpty() {
        return head == null;
    }
}
