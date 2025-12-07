package Assignment2.src;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		SalesDataAnalyzer analyzer = new SalesDataAnalyzer();

		List<SalesRecord> records;

		if (args.length > 0) {
			// read CSV from path provided as first argument
			Path path = Path.of(args[0]);
			try (InputStream in = Files.newInputStream(path)) {
				records = analyzer.loadFromCsv(in);
			}
		} else {
			// default: read bundled resource sales-data.csv
			try (InputStream in = Main.class.getClassLoader().getResourceAsStream("sales-data.csv")) {

				if (in == null) {
					throw new IllegalStateException("sales-data.csv not found on classpath");
				}
				records = analyzer.loadFromCsv(in);
			}
		}

		double totalRevenue = analyzer.totalRevenue(records);
		Map<String, Double> revenueByRegion = analyzer.revenueByRegion(records);
		Map<String, Double> revenueByProduct = analyzer.revenueByProduct(records);

		System.out.println("Total revenue: " + totalRevenue);
		System.out.println("Revenue by region: " + revenueByRegion);
		System.out.println("Revenue by product: " + revenueByProduct);

		analyzer.topSalesPersonByRevenue(records).ifPresent(sp -> System.out.println("Top salesperson by revenue: " + sp));
	}
}
