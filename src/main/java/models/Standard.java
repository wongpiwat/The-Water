package models;

public class Standard {
    private double pH;
    private double bod;
    private double sulfide;
    private double settleableSolids;
    private double totalDissolvedSolid;
    private double suspendedSoilds;
    private double fatOilGrease;
    private double totalKjeldahlNitrogen;

    public Standard(double pH, double bod, double sulfide, double settleableSolids, double totalDissolvedSolid, double suspendedSoilds, double fatOilGrease, double totalKjeldahlNitrogen) {
        this.pH = pH;
        this.bod = bod;
        this.sulfide = sulfide;
        this.settleableSolids = settleableSolids;
        this.totalDissolvedSolid = totalDissolvedSolid;
        this.suspendedSoilds = suspendedSoilds;
        this.fatOilGrease = fatOilGrease;
        this.totalKjeldahlNitrogen = totalKjeldahlNitrogen;
    }

    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    public double getBod() {
        return bod;
    }

    public void setBod(double bod) {
        this.bod = bod;
    }

    public double getSulfide() {
        return sulfide;
    }

    public void setSulfide(double sulfide) {
        this.sulfide = sulfide;
    }

    public double getSettleableSolids() {
        return settleableSolids;
    }

    public void setSettleableSolids(double settleableSolids) {
        this.settleableSolids = settleableSolids;
    }

    public double getTotalDissolvedSolid() {
        return totalDissolvedSolid;
    }

    public void setTotalDissolvedSolid(double totalDissolvedSolid) {
        this.totalDissolvedSolid = totalDissolvedSolid;
    }

    public double getSuspendedSoilds() {
        return suspendedSoilds;
    }

    public void setSuspendedSoilds(double suspendedSoilds) {
        this.suspendedSoilds = suspendedSoilds;
    }

    public double getFatOilGrease() {
        return fatOilGrease;
    }

    public void setFatOilGrease(double fatOilGrease) {
        this.fatOilGrease = fatOilGrease;
    }

    public double getTotalKjeldahlNitrogen() {
        return totalKjeldahlNitrogen;
    }

    public void setTotalKjeldahlNitrogen(double totalKjeldahlNitrogen) {
        this.totalKjeldahlNitrogen = totalKjeldahlNitrogen;
    }
}
