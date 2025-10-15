import java.util.Scanner;

public class Collision {
    Scanner keyboard=new Scanner(System.in);
    double voa=1; //1=true, needed
    double vob=1;
    double vfa=1;
    double vfb=1;
    double mass_a=1;
    double mass_b=1;
    double kinetic=1;
    boolean elastic;
    double mass_total;
    boolean sticks;

    public Collision(){
        System.out.println("Collision! \n");
        elastic=isElastic();
        if (!elastic){
            sticks=sticks();
        }
        int chosenVariable = findVariable();
        neededVariables(chosenVariable);
        takeInputs();
        printResults(chosenVariable);

        //NOTE: IF it is elastic, don't ask for the masses!
    }

    public boolean isElastic(){
        System.out.println("Is the collision elastic? (Yes/No)");
        String variable=keyboard.nextLine();
        if (variable.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }
    public boolean sticks(){
        System.out.println("Do the particles stick together after the collision? (Yes/No)");
        String variable=keyboard.nextLine();
        if (variable.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }
    public int findVariable(){
        System.out.println("The value for which variable would you like to find out?"+ 
        "\n1. Initial velocity of particle a"+ //voa
        "\n2. Initial velocity of particle b"+ //vob
        "\n3. Final velocity of particle a"+ //vfa
        "\n4. Final velocity of particle b"+ //vfb
        "\n5. Change in kinetic energy"); //BUNU BİR DÜŞÜN? başta mı soracaksın sonda mı?
        //"\n6. Mass of particle a"+ //mass_a
        //"\n7. Mass of particle b"); //mass_b
        int variable=keyboard.nextInt();
        return variable;
    }
    public void neededVariables(int var){ //ELASTIC DEĞİLSE VFB'Yİ DE SORMA
        if (var==1){voa=0;//0 means that I am searching for the variable and I cannot take it as an input
            if(!elastic){
                vfa=0;}} //if it is inelastic, don't ask for vfa
        else if (var==2){vob=0;
            if(!elastic){
                vfb=0;}}
        else if (var==3){vfa=0;
            if (!elastic){vfb=0;} //if it is inelastic, don't ask for vfb
            if (sticks){vfb=0;} //if they stick, don't ask for vfb
            } 
        else if (var==4){vfb=0;
           if (!elastic){vfa=0;} //if it is inelastic, don't ask for vfa
            if (sticks){vfa=0;} //if they stick, don't ask for vfa
            } 
        else if (var==5){kinetic=0;}
        //else if (var==6){mass_a=0;}
        //else if (var==7){mass_b=0;}
        else{System.out.println("Not a valid answer. Start over!");}
    }

    public void takeInputs() {
        if (voa==1) { //if it is zero, I won't take it as an input
            System.out.println("What is the initial velocity of particle a?");
            voa=keyboard.nextInt();
        }
        if (vob==1) {
            System.out.println("What is the initial velocity of particle b?");
            vob=keyboard.nextInt();
        }
        if (vfa==1) {
            System.out.println("What is the final velocity of particle a?");
            vfa=keyboard.nextInt();
            if(sticks){
                vfb=vfa;
            }
        }
        if (vfb==1) {
            System.out.println("What is the final velocity of particle b?");
            vfb=keyboard.nextInt();
            if(sticks){
                vfa=vfb;
            }
        }
        if (!elastic) { //if it's elastic, the masses are not needed
            System.out.println("What is the mass of particle a?");
            mass_a=keyboard.nextInt();
        }
        if (!elastic) {
            System.out.println("What is the mass of particle b?");
            mass_b=keyboard.nextInt();
        }
  
        mass_total=mass_a+mass_b;
        
    }

    public void printResults(int var){ //don't forget the units!
        if (var==1){
            System.out.println("The initial velocity of particle a is: "+find_voa()+" m/s");}
        else if (var==2){
            System.out.println("The initial velocity of particle b is: "+find_vob()+" m/s");}
        else if (var==3){
            System.out.println("The final velocity of particle a is: "+find_vfa()+" m/s");}
        else if (var==4){
            System.out.println("The final velocity of particle b is: "+find_vfb()+" m/s");}
        else if (var==5){
            System.out.println("The mass of particle a is: "+find_voa()+" kg");} //FIND MASS
        else if (var==6){
            System.out.println("The mass of particle b is: "+find_voa()+" kg");}
    }

    private double find_voa(){
        if (elastic){
            return vfb-vfa+vob;
        }
        if (sticks){
            return (mass_total*vfa-mass_b*vob)/mass_a;
        }
        //double ans= (vfa-(2*mass_b/mass_total*vob))*mass_total/(mass_a-mass_b); //no need for vfb
        //parametreleri güzel değil, vfa ve vob çok mantıklı bi ikili değil
        double ans=(vfb-(mass_b-mass_a)/mass_total*vob)*mass_total/2*mass_a; //no need for vfa
        return ans;
        
    }

    private double find_vob(){
        if (elastic){
            return vfa-vfb+voa;
        }
        if (sticks){
            return (mass_total*vfb-mass_a*vfa)/mass_b;
        }
        double ans=(vfa-(mass_a-mass_b)/mass_total*voa)*mass_total/2*mass_b; //no need for vfb
        //double ans=(2*mass_a/mass_total*voa-vfb)*mass_total/(mass_a-mass_b);
        return ans;
        
    }

    private double find_vfa(){
        if (elastic){
            return vfb+vob-voa;
        }
        if (sticks){
            return (voa*mass_a+vob*mass_b)/mass_total;
        }
        double ans= (mass_a-mass_b)*voa/(mass_total) + (2*mass_b*vob)/(mass_total);
        return ans;
    }

    private double find_vfb(){
        if (elastic){
            return vfa+voa-vob;
        }
        if (sticks){
            return (voa*mass_a+vob*mass_b)/mass_total;
        }
        double ans= (2*mass_a)*voa/(mass_total) - (mass_a-mass_b)*vob/(mass_total);
        return ans;
    }



}