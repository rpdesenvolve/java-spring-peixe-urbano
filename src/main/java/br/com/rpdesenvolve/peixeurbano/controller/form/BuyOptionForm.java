package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;

import java.util.Date;

public class BuyOptionForm {

    private String title;
    private Double normalPrice;
    private Double salePrice;
    private Double percentageDiscount;
    private Long quantityCupom;
    private Date startDate;
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

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public BuyOption convert() {
        return new BuyOption(title, normalPrice, salePrice, percentageDiscount, quantityCupom, startDate, endDate);
    }
}
