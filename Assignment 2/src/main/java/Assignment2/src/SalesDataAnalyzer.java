package Assignment2.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesDataAnalyzer {

    public List<SalesRecord> loadFromCsv(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().skip(1).map(this::parseLine).collect(Collectors.toList());
        }
    }

    private SalesRecord parseLine(String line) {
        String[] parts = line.split(",", -1);
        String region = parts[0].trim();
        String product = parts[1].trim();
        String salesPerson = parts[2].trim();
        LocalDate date = LocalDate.parse(parts[3].trim()); // yyyy-MM-dd
        int unitsSold = Integer.parseInt(parts[4].trim());
        double unitPrice = Double.parseDouble(parts[5].trim());
        return new SalesRecord(region, product, salesPerson, date, unitsSold, unitPrice);
    }

    public double totalRevenue(List<SalesRecord> records) {
        return records.stream().mapToDouble(SalesRecord::getRevenue).sum();
    }

    public Map<String, Double> revenueByRegion(List<SalesRecord> records) {
        return records.stream().collect(Collectors.groupingBy(SalesRecord::getRegion, Collectors.summingDouble(SalesRecord::getRevenue)));
    }

    public Map<String, Double> revenueByProduct(List<SalesRecord> records) {
        return records.stream() .collect(Collectors.groupingBy(SalesRecord::getProduct, Collectors.summingDouble(SalesRecord::getRevenue)));
    }

    public Map<String, Double> revenueBySalesPerson(List<SalesRecord> records) {
        return records.stream().collect(Collectors.groupingBy(SalesRecord::getSalesPerson, Collectors.summingDouble(SalesRecord::getRevenue)));
    }

    public Optional<String> topSalesPersonByRevenue(List<SalesRecord> records) {
        return revenueBySalesPerson(records).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
    }
}
