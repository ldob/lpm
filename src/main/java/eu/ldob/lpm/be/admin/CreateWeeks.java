package eu.ldob.lpm.be.admin;

import eu.ldob.lpm.be.model.WeekModel;
import eu.ldob.lpm.be.repository.WeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Calendar;

@SpringBootApplication
public class CreateWeeks {

	@Autowired
	WeekRepository weekRepository;

	public static void main(String[] args) {
		CreateWeeks script = new CreateWeeks();
		script.run();
	}

	private void run() {
		Integer year = 2020;

		LocalDate day = LocalDate.of(year, 1, 1);
		Calendar c = Calendar.getInstance();

		do {
			WeekModel week = new WeekModel();
			c.set(day.getDayOfYear(), day.getMonthValue(), day.getDayOfMonth());
			week.setWeek();
			week.setFromDate();
			weekRepository.save(week);

			day.plusDays(7);
		}
		while (day.isBefore(LocalDate.of(year, 12, 31)));
	}
}
