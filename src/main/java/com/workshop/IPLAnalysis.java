package com.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	public <E> List getBestBowlingStrikeRate() throws IPLAnalyserException {
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
	public <E> List getBestBowlingEconomy() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.economy) );
		return this.sort(bowlCsvList, statComparator);
	}
	
	/**
	 * UC10 Get Bowler with Best Strike Rate of 4 Wickets and 5 Wickets
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E> List getBestBowlingStrikeRateWithMore4WAnd5W() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		bowlCsvList.removeIf(stat -> (stat.strikeRate == 0 || (stat.fourWickets == 0 && stat.fiveWickets == 0)));
		return this.sort(bowlCsvList, statComparator);
	}
	
	/**
	 * UC11 Get Bowler with Best Strike Rate of 4 Wickets and 5 Wickets with best averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E> List getBestBowlingAverageAndStrikeRate() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		List<BowlingStats> strikeRate = getBestBowlingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), statComparator);
	}
	
	/**
	 * UC12 Get Bowler with High Wicket Takers with best averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E> List getMaximumWicketsWithBestAverage() throws IPLAnalyserException {
		Comparator<BowlingStats> statComparator = Comparator.comparing(stat -> (stat.wickets) );
		Comparator<BowlingStats> statAvgComparator = Comparator.comparing(stat -> (stat.average) );
		List<BowlingStats> maximumWickets = this.sort(bowlCsvList, statComparator.reversed());
		return this.sort(maximumWickets.stream().limit(20).collect(Collectors.toList()), statAvgComparator);
	}
	
	/**
	 * UC13 Test for getting best average players with bowling and batting
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E>List getBestBattingAndBowlingAverage() throws IPLAnalyserException {
		List<String> stat = new ArrayList<String>();
		List<BattingStats> battingAverage = getBestBattingAverage();
		battingAverage = battingAverage.stream().limit(50).collect(Collectors.toList());
		List<BowlingStats> bowlingAverage = getBestBowlingAverage();
		bowlingAverage = bowlingAverage.stream().limit(50).collect(Collectors.toList());
		for(BattingStats b: battingAverage ) {
			for(int i = 0;i < bowlingAverage.size(); i++) {
				if(b.player.equals(bowlingAverage.get(i).player)) {
					stat.add(b.player);
				}
			}
		}
		System.out.println(stat);
		return stat;
	}
	
	/**
	 * UC14 Test for getting best all rounders
	 * @param <E>
	 * @return
	 * @throws IPLAnalyserException
	 */
	public <E>List getAllRounder() throws IPLAnalyserException {
		
		List<String> stats = new ArrayList<String>();
		Comparator<BattingStats> statBatComparator = Comparator.comparing(stat -> (stat.runs));
		Comparator<BowlingStats> statBowlComparator = Comparator.comparing(stat -> (stat.wickets));
		List<BattingStats> battingAverage = this.sort(batCsvList, statBatComparator);
		battingAverage = battingAverage.stream().limit(50).collect(Collectors.toList());
		List<BowlingStats> bowlingAverage = this.sort(bowlCsvList, statBowlComparator);
		bowlingAverage = bowlingAverage.stream().limit(50).collect(Collectors.toList());
		for(BattingStats b: battingAverage ) {
			for(int i = 0;i < bowlingAverage.size(); i++) {
				if(b.player.equals(bowlingAverage.get(i).player)) {
					stats.add(b.player);
				}
			}
		}
		System.out.println(stats);
		return stats;
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
