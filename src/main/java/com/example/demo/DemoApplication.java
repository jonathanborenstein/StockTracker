package com.example.demo;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Order(1)
	CommandLineRunner command1(SymbolRepository symbolRepository) {
		return args -> {

			Arrays.asList("AAPL", "ABT", "ABBV", "ACN", "ACE", "ADBE", "ADT", "AAP", "AES", "AET", "AFL", 
					"AMG", "A", "GAS", "ARE", "APD", "AKAM", "AA", "AGN", "ALXN", "ALLE", "ADS", "ALL", 
					"ALTR", "MO", "AMZN", "AEE", "AAL", "AEP", "AXP", "AIG", "AMT", "AMP", "ABC", "AME", 
					"AMGN", "APH", "APC", "ADI", "AON", "APA", "AIV", "AMAT", "ADM", "AIZ", "T", "ADSK", 
					"ADP", "AN", "AZO", "AVGO", "AVB", "AVY", "BHI", "BLL", "BAC", "BK", "BCR", "BXLT", 
					"BAX", "BBT", "BDX", "BBBY", "BRK.B", "BBY", "BLX", "HRB", "BA", "BWA", "BXP", "BSX",
					"BMY", "BRCM", "BF.B", "CHRW", "CA", "CVC", "COG", "CAM", 
					"CPB", "COF", "CAH", "HSIC", "KMX", "CCL", "CAT", "CBG", "CBS", "CELG", "CNP", "CTL",
					"CERN", "CF", "SCHW", "CHK", "CVX", "CMG", "CB", "CI", "XEC", "CINF", "CTAS", "CSCO",
					"C", "CTXS", "CLX", "CME", "CMS", "COH", "KO", "CCE", "CTSH", "CL", "CMCSA", "CMA", "CSC", 
					"CAG", "COP", "CNX", "ED", "STZ", "GLW", "COST", "CCI", "CSX", "CMI", "CVS", "DHI", "DHR",
					"DRI", "DVA", "DE", "DLPH", "DAL", "XRAY", "DVN", "DO", "DTV", "DFS", "DISCA", "DISCK", "DG",
					"DLTR", "D", "DOV", "DOW", "DPS", "DTE", "DD", "DUK", "DNB", "ETFC", "EMN", "ETN", "EBAY", "ECL",
					"EIX", "EW", "EA", "EMC", "EMR", "ENDP", "ESV", "ETR", "EOG", "EQT", "EFX", "EQIX", "EQR", "ESS", 
					"EL", "ES", "EXC", "EXPE", "EXPD", "ESRX", "XOM", "FFIV", "FB", "FAST", "FDX", "FIS", "FITB", "FSLR",
					"FE", "FISV", "FLIR", "FLS", "FLR", "FMC", "FTI", "F", "FOSL", "BEN", "FCX", "FTR", "GME", "GPS", 
					"GRMN", "GD", "GE", "GGP", "GIS", "GM", "GPC", "GNW", "GILD", "GS", "GT", "GOOGL", "GOOG", "GWW",
					"HAL", "HBI", "HOG", "HAR", "HRS", "HIG", "HAS", "HCA", "HCP", "HCN", "HP", "HES", "HPQ", "HD", "HON", "HRL",
					"HSP", "HST", "HCBK", "HUM", "HBAN", "ITW", "IR", "INTC", "ICE", "IBM", "IP", "IPG", "IFF", "INTU",
					"ISRG", "IVZ", "IRM", "JEC", "JBHT", "JNJ", "JCI", "JOY", "JPM", "JNPR", "KSU", "K", "KEY", "GMCR", 
					"KMB", "KIM", "KMI", "KLAC", "KSS", "KRFT", "KR", "LB", "LLL", "LH", "LRCX", "LM", "LEG", "LEN", "LVLT", 
					"LUK", "LLY", "LNC", "LLTC", "LMT", "L", "LOW", "LYB", "MTB", "MAC", "M", "MNK", "MRO", "MPC", "MAR", "MMC", 
					"MLM", "MAS", "MA", "MAT", "MKC", "MCD", "MCK", "MJN", "MMV", "MDT", "MRK", "MET", "KORS", "MCHP", "MU", "MSFT", 
					"MHK", "TAP", "MDLZ", "MON", "MNST", "MCO", "MS", "MOS", "MSI", "MUR", "MYL", "NDAQ", "NOV", "NAVI", "NTAP", "NFLX",
					"NWL", "NFX", "NEM", "NWSA", "NEE", "NLSN", "NKE", "NI", "NE", "NBL", "JWN", "NSC", "NTRS", "NOC", "NRG", "NUE", 
					"NVDA", "ORLY", "OXY", "OMC", "OKE", "ORCL", "OI", "PCAR", "PLL", "PH", "PDCO", "PAYX", "PNR", "PBCT", "POM", "PEP", 
					"PKI", "PRGO", "PFE", "PCG", "PM", "PSX", "PNW", "PXD", "PBI", "PCL", "PNC", "RL", "PPG", "PPL", "PX", "PCP", "PCLN", "PFG", 
					"PG", "PGR", "PLD", "PRU", "PEG", "PSA", "PHM", "PVH", "QRVO", "PWR", "QCOM", "DGX", "RRC", "RTN", "O", "RHT",
					"REGN", "RF", "RSG", "RAI", "RHI", "ROK", "COL", "ROP", "ROST", "RLD", "R",
					"CRM", "SNDK", "SCG", "SLB", "SNI", "STX", "SEE", "SRE", "SHW", "SPG",
					"SWKS", "SLG", "SJM", "SNA", "SO", "LUV", "SWN", "SE", "STJ", "SWK", 
					"SPLS", "SBUX", "HOT", "STT", "SRCL", "SYK", "STI", "SYMC", "SYY",
					"TROW", "TGT", "TEL", "TE", "TGNA", "THC", "TDC", "TSO", "TXN", "TXT", "HSY", 
					"TRV", "TMO", "TIF", "TWX", "TWC", "TJX", "TMK", "TSS", "TSCO", "RIG", "TRIP", "FOXA", 
					"TSN", "TYC", "UA", "UNP", "UNH", "UPS", "URI", "UTX", "UHS", "UNM", 
					"URBN", "VFC", "VLO", "VAR", "VTR", "VRSN", "VZ", "VRTX", "VIAB", "V", 
					"VNO", "VMC", "WMT", "WBA", "DIS", "WM", "WAT", "ANTM", "WFC", "WDC", "WU", 
					"WY", "WHR", "WFM", "WMB", "WEC", "WYN", "WYNN", "XEL", "XRX", "XLNX", "XL", 
					"XYL", "YHOO", "YUM", "ZBH", "ZION", "ZTS", "TWTR", "FB").forEach(symbol -> symbolRepository.save(new Symbol(symbol)));






		};
	}

	@Bean
	CommandLineRunner command(StockTransactionRepository stockRepo, StockService stockService) {
		return args -> {



			StockTransaction transaction1 = new StockTransaction(100, 100, new BigDecimal(100.25), "GOOG");
			StockTransaction transaction2 = new StockTransaction(100, 100, new BigDecimal(100.25), "AAPL");
			StockTransaction transaction5 = new StockTransaction(100, 100, new BigDecimal(100.33), "AAPL");
			StockTransaction transaction7 = new StockTransaction(100, 100, new BigDecimal(10.25), "AAPL");



			StockTransaction transaction3 = new StockTransaction(100, 100, new BigDecimal(100.777), "FB");

			StockTransaction transaction4 = new StockTransaction(100, 100, new BigDecimal(100.22), "MSFT");
			StockTransaction transaction6 = new StockTransaction(50, 50, new BigDecimal(120.22), "MSFT");


			stockRepo.save(transaction1);
			stockRepo.save(transaction2);
			stockRepo.save(transaction3);
			stockRepo.save(transaction4);
			stockRepo.save(transaction5);
			stockRepo.save(transaction6);
			stockRepo.save(transaction7);



		};
	}

}



