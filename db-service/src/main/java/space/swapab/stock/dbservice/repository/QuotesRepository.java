package space.swapab.stock.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.swapab.stock.dbservice.model.Quote;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUserName(String username);

    List<Quote> findByUserNameAndQuote(String username, String quote);
}
