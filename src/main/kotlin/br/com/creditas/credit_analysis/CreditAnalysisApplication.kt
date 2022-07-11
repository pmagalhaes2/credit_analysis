package br.com.creditas.credit_analysis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CreditAnalysisApplication

fun main(args: Array<String>) {
	runApplication<CreditAnalysisApplication>(*args)
}
