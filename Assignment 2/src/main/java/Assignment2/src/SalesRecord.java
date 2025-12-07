package Assignment2.src;

import java.time.LocalDate;

public class SalesRecord {
    private final String region;
    private final String product;
    private final String salesPerson;
    private final LocalDate date;
    private final int unitsSold;
    private final double unitPrice;

    public SalesRecord(String region, String product, String salesPerson, LocalDate date, int unitsSold, double unitPrice) {
        this.region = region;
        this.product = product;
        this.salesPerson = salesPerson;
        this.date = date;
        this.unitsSold = unitsSold;
        this.unitPrice = unitPrice;
    }

    public String getRegion() {
        return region;
    }

    public String getProduct() {
        return product;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getRevenue() {
        return unitsSold * unitPrice;
    }

    @Override
    public String toString() {
        return "SalesRecord{" + "region='" + region + '\'' + ", product='" + product + '\'' + ", salesPerson='" + salesPerson + '\'' + ", date=" + date + ", unitsSold=" + unitsSold + ", unitPrice=" + unitPrice + '}';
    }
}
