package employee;

import java.time.LocalDate;

public class Period {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Period(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    public LocalDate getDateTo() {
        return this.dateTo;
    }
}