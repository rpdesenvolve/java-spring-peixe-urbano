package br.com.rpdesenvolve.peixeurbano.controller.dto;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;

import java.util.Date;

public class DetailBuyOptionDTO {

    private Long id;
    private String title;
    private Double normalPrice;
    private Double salePrice;
    private Double percentageDiscount;
    private Long quantityCupom;
    private Date startDate;
    private Date endDate;

    public DetailBuyOptionDTO(BuyOption buyOption) {
        this.id = buyOption.getId();
        this.title = buyOption.getTitle();
        this.normalPrice = buyOption.getNormalPrice();
        this.salePrice = buyOption.getSalePrice();
        this.percentageDiscount = buyOption.getPercentageDiscount();
        this.quantityCupom = buyOption.getQuantityCupom();
        this.startDate = buyOption.getStartDate();
        this.endDate = buyOption.getEndDate();
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public Double getNormalPrice() { return normalPrice; }

    public Double getSalePrice() { return salePrice; }

    public Double getPercentageDiscount() { return percentageDiscount; }

    public Long getQuantityCupom() { return quantityCupom; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }
}
