package com.workshop;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
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
	
	
	

}
