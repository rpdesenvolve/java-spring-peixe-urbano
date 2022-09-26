package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;
import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.modelo.Type;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class DealForm {

    @NotNull @NotEmpty  @Length(min = 5)
    private String title;
    @NotNull @NotEmpty @Length(min = 10)
    private String text;
    @NotNull
    private Type type;
    @NotNull
    private Date publishDate;
    @NotNull
    private Date endDate;
    private List<BuyOption> buyOptionList;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public List<BuyOption> getBuyOptionList() {
        return buyOptionList;
    }

    public void setBuyOptionList(List<BuyOption> buyOptionList) {
        this.buyOptionList = buyOptionList;
    }

    public Deal convert() {
        return new Deal(title, text, type, publishDate, endDate, buyOptionList);
    }
}
