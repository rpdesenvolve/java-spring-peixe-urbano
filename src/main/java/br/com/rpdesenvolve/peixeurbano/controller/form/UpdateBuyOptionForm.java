package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;
import br.com.rpdesenvolve.peixeurbano.repository.BuyOptionRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateBuyOptionForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String title;
    @NotNull
    private Double normalPrice;
    @NotNull
    private Double salePrice;
    @NotNull
    private Double percentageDiscount;
    @NotNull
    private Long quantityCupom;
    @NotNull
    private Date endDate;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Double getNormalPrice() { return normalPrice; }

    public void setNormalPrice(Double normalPrice) { this.normalPrice = normalPrice; }

    public Double getSalePrice() { return salePrice; }

    public void setSalePrice(Double salePrice) { this.salePrice = salePrice; }

    public Double getPercentageDiscount() { return percentageDiscount; }

    public void setPercentageDiscount(Double percentageDiscount) { this.percentageDiscount = percentageDiscount; }

    public Long getQuantityCupom() { return quantityCupom; }

    public void setQuantityCupom(Long quantityCupom) { this.quantityCupom = quantityCupom; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public BuyOption update(Long id, BuyOptionRepository buyOptionRepository) {
        BuyOption buyOption = buyOptionRepository.getReferenceById(id);

        buyOption.setTitle(this.title);
        buyOption.setNormalPrice(this.normalPrice);
        buyOption.setSalePrice(this.salePrice);
        buyOption.setPercentageDiscount(this.percentageDiscount);
        buyOption.setQuantityCupom(this.quantityCupom);
        buyOption.setEndDate(this.endDate);

        return buyOption;
    }
}
