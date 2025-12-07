package Assignment2.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalesDataAnalyzerTest {

	private SalesDataAnalyzer analyzer;
	private List<SalesRecord> records;

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new SalesDataAnalyzer();
		try (InputStream in = getClass().getClassLoader()
				.getResourceAsStream("sales-data.csv")) {
			assertNotNull(in, "sales-data.csv should be on the classpath");
			records = analyzer.loadFromCsv(in);
		}
	}

	@Test
	void totalRevenue_isCorrect() {
		double total = analyzer.totalRevenue(records);
		assertEquals(970.0, total, 1e-6);
	}

	@Test
	void revenueByRegion_isCorrect() {
		Map<String, Double> byRegion = analyzer.revenueByRegion(records);
		assertEquals(350.0, byRegion.get("North"), 1e-6);
		assertEquals(260.0, byRegion.get("South"), 1e-6);
		assertEquals(230.0, byRegion.get("East"), 1e-6);
		assertEquals(130.0, byRegion.get("West"), 1e-6);
	}

	@Test
	void topSalesPersonByRevenue_isAlice() {
		assertEquals("Alice", analyzer.topSalesPersonByRevenue(records).orElse(null));
	}
}