public class BloodStock {
    private int stockId;
    private String bloodGroup;
    private int unitsAvailable;

    public BloodStock() {
    }

    public BloodStock(String bloodGroup, int unitsAvailable) {
        this.bloodGroup = bloodGroup;
        this.unitsAvailable = unitsAvailable;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(int unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }
}