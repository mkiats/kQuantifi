package com.mkiats.backtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.backtest.dto.BacktestRequest;
import com.mkiats.backtest.dto.BacktestResponse;
import com.mkiats.backtest.service.BacktestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BacktestController {

	private final BacktestService backtestService;

	@PostMapping("/backtests")
	public ResponseEntity<BacktestResponse> getBacktestResult(
		@RequestBody BacktestRequest backtestRequest
	) {
			BacktestResponse theResponse = backtestService.doExecute(
				backtestRequest
			);
			return ResponseEntity.ok(theResponse);

	}
}
