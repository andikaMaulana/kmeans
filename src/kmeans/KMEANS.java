package kmeans;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dika
 */
public class KMEANS {

    //centroid awal 
    static List<Double> cluster = new ArrayList<>();
    //kelas data umur
    static List<k_means> cDataUmur=new ArrayList<>();
    static double totalA=0,totalB=0;
    static int jumA=0,jumB=0;
    static boolean status=false;
    static double rataA,rataB;
     static int[] dataUmur ={15, 15, 16, 19, 19, 20, 20, 21, 22, 28, 35, 40, 41, 42, 43, 44, 60, 61, 65};

    //menentukan centroid awal dengan bilangan random
    static void centroidAwal(int k) {
        Random ran = new Random();
        for (int i = 0; i < k; i++) {
            int x = ran.nextInt(99) + 1;
            double y=x+0;
            cluster.add(y);
        }
    }
    static void hitung(int[] data){
        jumA=0;
        jumB=0;
        totalA=0;
        totalB=0;
        for (int i = 0; i < data.length; i++) {
            if(cDataUmur.size()!=dataUmur.length)
                cDataUmur.add(new k_means(data[i], cluster.get(0), cluster.get(1)));
            else
                cDataUmur.set(i, new k_means(data[i], cluster.get(0), cluster.get(1)));
            if(cDataUmur.get(i).C=="A"){
                totalA+=cDataUmur.get(i).x1; jumA++;}
            else{
                totalB+=cDataUmur.get(i).x1; jumB++;}
        }
        rataA=totalA/jumA;
        rataB=totalB/jumB;
    }
    static void cari(){
        double rA=cluster.get(0);
        double rB=cluster.get(1);
        hitung(dataUmur);
        while(rA!=rataA && rB!=rataB){
            rA=rataA;
            rB=rataB;
            changeCluster(rataA, rataB);
            hitung(dataUmur);    
        }
    }
    static void changeCluster(double a, double b){
        cluster.clear();
        cluster.add(a);
        cluster.add(b);
    }
    static void tampil(){
        for (int i = 0; i < cDataUmur.size(); i++) {
            System.out.println(cDataUmur.get(i).x1+" : "+cDataUmur.get(i).cA+" : "+
                               cDataUmur.get(i).cB+" : "+cDataUmur.get(i).dxcA+" : "+
                               cDataUmur.get(i).dxcB+" : "+cDataUmur.get(i).C
                    );
        }
            System.out.println(" rata-rata dari cluster A : "+rataA+" cluster B : "+rataB);
    }
    public static void main(String[] args) {
        rataA=0;
        rataB=0;
        int k = 2; //jumlah cluster
        //data umur
        centroidAwal(k);
//        cluster.add(16.0);
//        cluster.add(22.0);
        cari();
        tampil();
        // TODO code application logic here       
    }
}
class k_means{
    int x1;
    double cA, cB, dxcA, dxcB;
    String C;

    public k_means(int x1, double cA, double cB) {
        this.x1=x1;
        this.cA=cA;
        this.cB=cB;
        this.dxcA=euclidean(x1, cA);
        this.dxcB=euclidean(x1, cB);
        this.C=setCluster();
    }
    double euclidean(int xi, double ci){
        return Math.sqrt(Math.pow(xi-ci, 2));
    } 
    String setCluster(){
        return dxcA<=dxcB?"A":"B";
    }
    void setJarakA(double a){
        this.dxcA=a;
    }
    void setJarakB(double b){
        this.dxcB=b;
    }  
}
