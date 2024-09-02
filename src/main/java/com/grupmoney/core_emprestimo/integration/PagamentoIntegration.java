package com.grupmoney.core_emprestimo.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${url.pagamento}", value = "${name.core.pagamento}")
public interface PagamentoIntegration {

    @PostMapping("/pagamento")
    PagamentoResponse pagamentoresponse(@RequestBody PagamentoRequest pagamentoRequest);
}
