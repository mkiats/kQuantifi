package com.mkiats.backtest;

import com.mkiats.backtest.dto.service.BacktestRequest;
import com.mkiats.backtest.dto.service.BacktestResponse;
import com.mkiats.backtest.service.BacktestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BacktestController {

	// Constructor injection via @RequiredArgsConstructor
	private final BacktestService backtestService;

	@PostMapping("/backtests")
	public ResponseEntity<BacktestResponse> getBacktestResult(
		@RequestBody BacktestRequest backtestRequest
	) {
		System.out.println("\n\nRequest received...");
		BacktestResponse theResponse = backtestService.doExecute(
			backtestRequest
		);
		System.out.println("Response created...\n\n");
		return ResponseEntity.ok(theResponse);
	}
}
