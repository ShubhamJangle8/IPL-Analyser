package com.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import IPLAnalysing.CSVBuilderException;
import IPLAnalysing.CSVBuilderFactory;
import IPLAnalysing.ICSVBuilder;

public class IPLAnalysis {
	List<BattingStats> batCsvList = null;
	List<BowlingStats> bowlCsvList = null;

	public int loadBattingStatsData(String csvFile)  throws IOException, CSVBuilderException{		
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<BattingStats> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			batCsvList =  csvBuilder.getCSVFileList(reader, BattingStats.class);
			return batCsvList.size();
	}
	public int loadBowlingStatsData(String csvFile)  throws IOException, CSVBuilderException{		
		Reader reader = Files.newBufferedReader(Paths.get(csvFile));
		@SuppressWarnings("unchecked")
		ICSVBuilder<BowlingStats> csvBuilder = CSVBuilderFactory.createCSVBuilder();
		bowlCsvList =  csvBuilder.getCSVFileList(reader, BowlingStats.class);
		return bowlCsvList.size();
	}
	
	/**
	 * UC1 Best Average
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getBestBattingAverage() throws IPLAnalyserException{
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.average) );
		return this.sort(batCsvList, statComparator.reversed());
	}
	
	/**
	 * UC2 Best Strike Rate
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E> List getBestStrikeRate() throws IPLAnalyserException {
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		return this.sort(batCsvList, statComparator.reversed());
	}
	
	/**
	 * UC3 Get Most Boundary Players
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getMostBoundaries() throws IPLAnalyserException {
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.fours + stat.sixes) );
		return this.sort(batCsvList, statComparator.reversed());
	}
	
	/**
	 * UC4 Get Most Boundary Hitting Players With High Strike Rates
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestStrikeRateWithBoundaries() throws IPLAnalyserException {
		batCsvList.removeIf(s->(s.fours+s.sixes)==0);
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.bf / (stat.fours + stat.sixes)));
		return this.sort(batCsvList,  statComparator);
	}
	
	/**
	 * UC5 Get High Strike Rate With High Average Batsmen
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getBestAverageAndStrikeRate() throws IPLAnalyserException {
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		List<BattingStats> strikeRate = getBestBattingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), statComparator.reversed());
	}
	
	/**
	 * UC6 Get Highest Run Scoring Batsmen With High Averages
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getMaximumRunsWithBestAverage() throws IPLAnalyserException {
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.runs) );
		Comparator<BattingStats> statAvgComparator = Comparator.comparing(stat -> (stat.average) );
		List<BattingStats> maximumRuns = this.sort(batCsvList, statComparator.reversed());
		return this.sort(maximumRuns.stream().limit(20).collect(Collectors.toList()), statAvgComparator.reversed());
	}
	
	/**
	 * UC7 Get Highest Bowling Average Bowler
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getBestBowlingAverage() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.average) );
		bowlCsvList.removeIf(stat -> stat.average == 0);
		return this.sort(bowlCsvList, statComparator);
	}
	
	/**
	 * UC8 Get Highest Bowler with Highest Strike Rate
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingStrikeRate() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		bowlCsvList.removeIf(stat -> stat.strikeRate == 0);
		return this.sort(bowlCsvList, statComparator);
	}
	
	/**
	 * UC9 Get Bowler with Best Economy Rate
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingEconomy() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.economy) );
		return this.sort(bowlCsvList, statComparator);
	}
	
	private <E> List sort(List<E> statList, Comparator<E> statComparator) throws IPLAnalyserException {
		if(statList == null || statList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_STATISTICS_DATA);
		}
		for (int i = 0; i < statList.size(); i++) {
			for (int j = 0; j < statList.size() - i - 1; j++) {
				E stat1 =  statList.get(j);
				E stat2 =  statList.get(j + 1);
				if (statComparator.compare(stat1, stat2) > 0) {
					statList.set(j, stat2);
					statList.set(j + 1, stat1);
				}
			}
		}
		return statList;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the IPL Analyser Program");
	}
}
