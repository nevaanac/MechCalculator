import java.util.Scanner;

public class Projectile{
    Scanner keyboard=new Scanner(System.in);
    double vyo=1; //1=true, needed
    double vyf=1;
    double disp=1;
    double acc=-9.81;
    double t=1;

    public Projectile(){
        System.out.println("Projectile! \n");
        int chosenVariable = findVariable();
       neededVariables(chosenVariable);
       takeInputs();
       printResults(chosenVariable);
       energyChange();
    }
    
    public int findVariable(){
        System.out.println("The value for which variable would you like to find out?"+ 
        "\n1. Initial vertical velocity(vyo)"+
        "\n2. Final vertical velocity (vyf)"+
        "\n3. Vertical displacement (Δy)"+
        "\n4. Acceleration (a)"+
        "\n5. Time (t)");
        int variable=keyboard.nextInt();
        return variable;
    }
    public void neededVariables(int var){
        if (var==1){vyo=0;} //0 means that I am searching for the variable and I cannot take it as an input
        else if (var==2){vyf=0;}
        else if (var==3){disp=0;}
        //else if (var==4){acc=0;}
        else if (var==5){t=0;}
        else{System.out.println("Not a valid answer. Start over!");}
    }
    public void takeInputs() {
        System.out.println("Info: Acceleration is -9.8m/s^2 in vertical motion");
        if (vyo==1) { //if it is zero, I won't take it as an input
            System.out.println("What is the initial vertical velocity of the object?");
            vyo=keyboard.nextInt();
        }
        if (vyf==1) {
            System.out.println("What is the final vertical velocity of the object?");
            vyf=keyboard.nextInt();
        }
        if (disp==1) {
            System.out.println("What is the distance travelled by the object?");
            disp=keyboard.nextInt();
        }
        /*if (acc==1) {
            System.out.println("Info: Acceleration is -9.8m/s^2 in vertical motion"); //OR ASSUME -G
            acc=-9.8;
        }*/
        if (t==1&&vyo==1) { //if vyo is asked, t is not necessary
            System.out.println("What is time of flight?");
            t=keyboard.nextInt();
        }
    }
    public void printResults(int var){ //don't forget the units!
        if (var==1){
            System.out.println("The initial vertical velocity (vyo) of the object is: "+find_vyo()+" m/s");}
        else if (var==2){
            System.out.println("The final vertical velocity (vyf) of the object is: "+find_vyf()+" m/s");}
        else if (var==3){
            System.out.println("The vertical displacement (Δy) of the object is: "+find_disp()+" m");}
        /*else if (var==4){
            System.out.println("The acceleration (a) of the object is: "+" m/s^2");}*/
        else if (var==5){
            System.out.println("The time of flight (t) is: "+find_t()+" s");}
    }
    
    private double find_vyo(){
        //vyo^2=vf^2-2ax
        vyo=Math.sqrt(vyf*vyf-2*acc*disp);
        return Math.round(vyo*100.0)/100d;
    }
    private double find_vyf(){
        //vyf^2=vyo^2+2ax
        vyf=Math.sqrt(vyo*vyo+2*acc*disp);
        return Math.round(vyf*100.0)/100d;
    }
    private double find_disp(){
        //y=vyot+0.5*a*t^2
        disp=vyo*t+0.5*acc*t*t;
        return Math.round(disp*100.0)/100d;
    }
    private double find_t(){
        //vyf=vyo+at
        //t=(vyf-vyo)/a
        t=(vyf-vyo)/acc;
        return Math.round(t*100.0)/100d;
    }
    private void energyChange(){
        System.out.println("If you would like to learn the change in energy, write the mass of the object. If not, write -1.");
        double mass=keyboard.nextDouble();
        if(mass>=0){ // !=-1 demiştim ama değiştirdim
            double KE= 0.5*mass*(vyf*vyf-vyo*vyo); //0.5m(vf^2-vo^2)
            double KE_round=Math.round(KE*100.0)/100d;
            
            double PE=mass*9.81*disp; //mgh
            double PE_round=Math.round(PE*100.0)/100d;

            System.out.println("The change in kinetic energy is: "+KE_round+" Joules");
            System.out.println("The change in gravitational potential energy is:"+PE_round+" Joules");
        }
    }
}