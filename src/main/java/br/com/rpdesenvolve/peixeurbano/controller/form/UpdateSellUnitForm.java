package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;
import br.com.rpdesenvolve.peixeurbano.repository.BuyOptionRepository;

import javax.validation.constraints.NotNull;

public class UpdateSellUnitForm {

    @NotNull
    private Long quantityCupom;

    public Long getQuantityCupom() { return quantityCupom; }

    public void setQuantityCupom(Long quantityCupom) { this.quantityCupom = quantityCupom; }

    public BuyOption update(Long id, BuyOptionRepository buyOptionRepository) {
        BuyOption buyOption = buyOptionRepository.getReferenceById(id);

        buyOption.setQuantityCupom(this.quantityCupom);

        return buyOption;
    }
}
