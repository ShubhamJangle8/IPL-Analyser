package com.workshop;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import IPLAnalysing.CSVBuilderException;

public class IPLAnalysisTest {
	
	private static final String BAT_STATS_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//IPLAnalysisProblem//MostRuns.csv";
	private static final String BOWL_STATS_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//IPLAnalysisProblem//MostWickets.csv";
	
	/**
	 * start
	 * Test 1 for a correct count for Bat Stats CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenBatStatsCSVFile_ReturnsCorrectCount()  {
		IPLAnalysis iplAnalyser = new IPLAnalysis();
		try {
			assertEquals(101, iplAnalyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * start
	 * Test for a proper count of records for Bowl Stats CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenBowlStatsCSVFile_ReturnsCorrectCount()  {
		IPLAnalysis iplAnalyser = new IPLAnalysis();
		try {
			assertEquals(99, iplAnalyser.loadBowlingStatsData(BOWL_STATS_CSV_FILE_PATH));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * UC1
	 * Test for getting the highest average player
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByAverage_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedCensusData = analyser.getBestBattingAverage();
		assertEquals("MS Dhoni", sortedCensusData.get(0).player);
	}
	
	/**
	 * UC2
	 * Test for getting the highest strikeRate player
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByStrikeRate_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByStrikeRate = analyser.getBestStrikeRate();
		assertEquals("Ishant Sharma", sortedByStrikeRate.get(0).player);
	}
	
	/**
	 * UC3
	 * Test for getting the highest boundaries player
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByMost4sAnd6s_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByBoundaries = analyser.getMostBoundaries();
		assertEquals("Andre Russell", sortedByBoundaries.get(0).player);
	}
	
	/**
	 * UC4
	 * Test for Most 4s and 6s with high strike rates
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByBestStrikeRatesWithBoundaries_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByStrikeRateAndBoundaries = analyser.getBestStrikeRateWithBoundaries();
		for(BattingStats s: sortedByStrikeRateAndBoundaries)
		{
			System.out.println(s.player);
		}
		assertEquals("Ishant Sharma", sortedByStrikeRateAndBoundaries.get(0).player);
	}

	/**
	 * UC5
	 * Test for high averages with high strike rate batsmen
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByBestBattingAverageWithBestBattingStrikeRate_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByStrikeRateAndBestAverage = analyser.getBestAverageAndStrikeRate();
		assertEquals("Andre Russell", sortedByStrikeRateAndBestAverage.get(0).player);
	}
	
	/**
	 * UC6
	 * Test for high scoring batsmen with high averages
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBattingStatistics_WhenSortedByMaximumRunsAndBestBattingAverage_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByMaximumRunsAndBestAverage = analyser.getMaximumRunsWithBestAverage();
		sortedByMaximumRunsAndBestAverage.forEach(s->System.out.println(s.player));
		assertEquals("MS Dhoni", sortedByMaximumRunsAndBestAverage.get(0).player);
	}
	
	/**
	 * UC7
	 * Test for getting high average bowlers
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBowlingStatistics_WhenSortedByAverage_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBowlingStatsData(BOWL_STATS_CSV_FILE_PATH);
		List<BowlingStats> sortedByAverage = analyser.getBestBowlingAverage();
		assertEquals("Anukul Roy", sortedByAverage.get(0).player);
	}
	
	/**
	 * UC8
	 * Test for getting high strike rate bowlers
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBowlingStatistics_WhenSortedByStrikeRate_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBowlingStatsData(BOWL_STATS_CSV_FILE_PATH);
		List<BowlingStats> sortedByStrikeRate = analyser.getBestBowlingStrikeRate();
		assertEquals("Alzarri Joseph", sortedByStrikeRate.get(0).player);
	}
	
	/**
	 * UC9
	 * Test for getting best economy bowlers
	 * @throws IOException
	 * @throws StatisticsAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBowlingStatistics_WhenSortedByEconomy_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBowlingStatsData(BOWL_STATS_CSV_FILE_PATH);
		List<BowlingStats> sortedByEconomy = analyser.getBestBowlingEconomy();
		assertEquals("Shivam Dube", sortedByEconomy.get(0).player);
	}
	
	/**
	 * UC10
	 * Test for getting high strike rate bowlers with 4s and 6s
	 * @throws IOException
	 * @throws IPLAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenBowlingStatistics_WhenSortedByStrikeRateAndHauls_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBowlingStatsData(BOWL_STATS_CSV_FILE_PATH);
		List<BowlingStats> sortedByStrikeRateAndHauls = analyser.getBestBowlingStrikeRateWithMore4WAnd5W();
		assertEquals("Alzarri Joseph", sortedByStrikeRateAndHauls.get(0).player);
	}
	
}
