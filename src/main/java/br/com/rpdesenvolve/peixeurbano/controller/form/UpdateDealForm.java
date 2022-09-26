package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.modelo.Type;
import br.com.rpdesenvolve.peixeurbano.repository.DealRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateDealForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String title;
    @NotNull @NotEmpty @Length(min = 10)
    private String text;

    @NotNull
    private Long totalSold;
    @NotNull
    private Type type;
    @NotNull
    private Date endDate;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Long getTotalSold() { return totalSold; }

    public void setTotalSold(Long totalSold) { this.totalSold = totalSold; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Deal update(Long id, DealRepository dealRepository) {
        Deal deal = dealRepository.getReferenceById(id);

        deal.setTitle(this.title);
        deal.setText(this.text);
        deal.setTotalSold(this.totalSold);
        deal.setType(this.type);
        deal.setEndDate(this.endDate);

        return deal;
    }
}
