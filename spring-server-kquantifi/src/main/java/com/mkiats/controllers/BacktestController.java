package com.mkiats.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mkiats.dataTransferObjects.backtest.BacktestRequest;
import com.mkiats.dataTransferObjects.backtest.BacktestResponse;
import com.mkiats.service.BacktestService;
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
	public ResponseEntity<?> getBacktestResult(
		@RequestBody BacktestRequest backtestRequest
	) {
		try {
			BacktestResponse theResponse = backtestService.doExecute(
				backtestRequest
			);
			return ResponseEntity.ok(theResponse);
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				"JsonProcessing"
			);
		}
	}
}
