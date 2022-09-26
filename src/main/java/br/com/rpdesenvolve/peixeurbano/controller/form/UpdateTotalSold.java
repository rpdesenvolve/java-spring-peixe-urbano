package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.repository.DealRepository;

import javax.validation.constraints.NotNull;

public class UpdateTotalSold {

    @NotNull
    private Long totalSold;

    public Long getTotalSold() { return totalSold; }

    public void setTotalSold(Long totalSold) { this.totalSold = totalSold; }

    public Deal update(Long id, DealRepository dealRepository) {
        Deal deal = dealRepository.getReferenceById(id);

        deal.setTotalSold(this.totalSold);

        return deal;
    }
}
