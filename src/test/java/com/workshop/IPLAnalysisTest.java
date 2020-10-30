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
	 * UC1
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
	 * UC2
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
	 * UC3
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
	
	@Test
	public void givenBattingStatistics_WhenSortedByStrikeRate_ShouldReturnSortedResult()
			throws IOException, IPLAnalyserException, CSVBuilderException {
		IPLAnalysis analyser = new IPLAnalysis();
		analyser.loadBattingStatsData(BAT_STATS_CSV_FILE_PATH);
		List<BattingStats> sortedByStrikeRate = analyser.getBestStrikeRate();
		assertEquals("Ishant Sharma", sortedByStrikeRate.get(0).player);
	}

}
