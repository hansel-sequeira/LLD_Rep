package BookMyShow.Entitites;

public class PriceConfigurator {
    int standardPrice;
    int reclinerPrice;
    public PriceConfigurator(int standardPrice, int reclinerPrice) {
        this.standardPrice = standardPrice;
        this.reclinerPrice = reclinerPrice;
    }

    public int getStandardPrice() {
        return standardPrice;
    }

    public int getReclinerPrice() {
        return reclinerPrice;
    }

}
