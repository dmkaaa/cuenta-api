package com.github.dmkaaa.cuenta.report

import com.github.dmkaaa.cuenta.report.balance.BalanceSheet
import com.github.dmkaaa.cuenta.report.balance.BalanceSheetService
import com.github.dmkaaa.cuenta.report.ledger.Ledger
import com.github.dmkaaa.cuenta.report.ledger.LedgerService
import com.github.dmkaaa.cuenta.util.Period
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/reports")
class ReportController(
    private val ledgerService: LedgerService,
    private val balanceSheetService: BalanceSheetService
) {

    @GetMapping("/ledger")
    fun getLedger(period: Period): Ledger {
        return ledgerService.get(period)
    }

    @GetMapping("/balance-sheet")
    fun getBalanceSheet(@RequestParam date: LocalDate): BalanceSheet {
        return balanceSheetService.get(date)
    }
}