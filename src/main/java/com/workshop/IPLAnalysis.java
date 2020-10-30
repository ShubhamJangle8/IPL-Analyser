package com.workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import IPLAnalysing.CSVBuilderException;
import IPLAnalysing.CSVBuilderFactory;
import IPLAnalysing.ICSVBuilder;

public class IPLAnalysis {
	List<BowlingStats> bowlCsvList = null;
	List<BattingStats> batCsvList = null;

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
	
	public static void main(String[] args) {
		System.out.println("Welcome to the IPL Analyser Program");
	}
}
