import java.awt.GradientPaint;

public class HohmannCalculator
{
    /*Variable*/
    public static double StarMass = 1.9891*Math.pow(10,30); /*Used the mass of the sun here*/
    public static double AvgFromSunDest = 227920000;
    public static double AvgPeriodDest = 686.6812*86400;
    public static double AvgFromSunDept = 149600000;
    public static double AvgPeriodDept = 365.25636*86400;
    //We can setup for whatever planet
    /*Constant*/
    static double GravConst = 6.67408*Math.pow(10,-11)/Math.pow(10,9); /*Converting this bad guy to Km³/kg*s2*/
    static double GravParameter = StarMass*GravConst;/*1.327 * Math.pow(10,11);*/
    static double Pi = 3.1415;

    public static void main(String[] args)
    {
        CalculateHohmann();
    }
    
    public static void CalculateHohmann()
    {
        System.out.println("Gravitational Parameter = " + GravParameter);
        double SemiMajor = CalculateSemiMajor(AvgFromSunDest, AvgFromSunDept);
        double TransferPeriod = CalculateTransferPeriod(SemiMajor);
        double VelDept = CalculateVel(AvgFromSunDept, AvgPeriodDept);
        double VelDest = CalculateVel(AvgFromSunDest, AvgPeriodDest);  
        double PerihellionVelTransfer = CalculatePerihellionVel(SemiMajor, TransferPeriod, AvgFromSunDept);
        double AphellionVelTransfer = CalculateAphellionVel(SemiMajor, TransferPeriod, AvgFromSunDest);
        System.out.print("Transfer Burn DeltaV needed: ");
        double TransferDeltaV = CalculateDeltaV(VelDept, PerihellionVelTransfer);
        System.out.println(TransferDeltaV + "Km/s");
        System.out.print("Capture Burn DeltaV needed: ");
        double CaptureDeltaV = CalculateDeltaV(VelDest, AphellionVelTransfer);
        System.out.println(CaptureDeltaV + "Km/s");
        double TimeOfFlight = TransferPeriod/2;
        System.out.println("Time of flight = " + TimeOfFlight + " Seconds, or " + TimeOfFlight/86400 + " Days");
        double PhaseAngle = CalculatePhaseAngle(AvgPeriodDest, TimeOfFlight);
    }

    public static double CalculateSemiMajor(double Dest, double Dept)
    {
        double SemiMajor = (Dest+Dept)/2;
        System.out.println("Semi major axis distance = " + SemiMajor + "Km");
        return SemiMajor;

    }

    public static double CalculateTransferPeriod(double TransferSemiMajor)
    {
        double TransferPeriod = Math.sqrt(((4*Math.pow(Pi,2)*Math.pow(TransferSemiMajor,3))/GravParameter));
        System.out.println("Transfer orbital period = " + TransferPeriod + " Seconds");
        return TransferPeriod;
    }
    
    public static double CalculateVel(double Dist, double Period)
    {
        double Vel = (2*Pi*Dist)/Period;
        System.out.println(Vel);
        return Vel;
    }

    public static double CalculatePerihellionVel(double SemiMajor, double Period, double R)
    {
        double PerihellionVel = (2*Pi*SemiMajor/Period)*Math.sqrt(((2*SemiMajor)/R) - 1);
        System.out.println("Perihellion Velocity = " + PerihellionVel + "Km/s");
        return PerihellionVel;
    }

    public static double CalculateAphellionVel(double SemiMajor, double Period, double Rdest)
    {
        double AphellionVel = (2*Pi*SemiMajor/Period)*Math.sqrt(((2*SemiMajor)/Rdest) - 1);
        System.out.println("Aphellion Velocity = " + AphellionVel + "Km/s");
        return AphellionVel;
    }

    public static double CalculateDeltaV(double V1, double V2)
    {
        double DeltaV = V2 - V1;
        return DeltaV;
    }

    public static double CalculatePhaseAngle(double PeriodDest, double TimeOfFlight)
    {
        double PhaseAngle = 180 - ((360/PeriodDest) * TimeOfFlight);
        System.out.println("Phase Angle = " + PhaseAngle + " degrees");
        return PhaseAngle;
    }

}