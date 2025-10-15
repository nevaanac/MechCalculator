import java.util.Scanner;

public class MechCalculator {

    public MechCalculator(){
        inputTaker();
    }
    public void inputTaker(){
        System.out.println("Choose the type of project:"+
        "\n1. Projectile Motion \n2. Collision \n3. Sliding down from a ramp");    
        Scanner keyboard=new Scanner(System.in);
        int choice=keyboard.nextInt();

        while(choice!=1 && choice!=2 && choice!=3){
            System.out.println("Please enter a valid choice.");
            choice=keyboard.nextInt();
        }
        if(choice==1){
            Projectile proj=new Projectile();
        }
        else if(choice==2){
            Collision coll=new Collision();
        }
        else if (choice==3){ 
            Ramp ramp=new Ramp();
        }
    }

}
