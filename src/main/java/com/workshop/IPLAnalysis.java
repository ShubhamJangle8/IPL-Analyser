package com.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

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
	
	public <E> List getBestBattingAverage() throws IPLAnalyserException{
		Comparator<BattingStats> statComparator = Comparator.comparing(stat -> (stat.average) );
		return this.sort(batCsvList, statComparator.reversed());
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
