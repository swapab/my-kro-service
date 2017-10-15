package space.swapab.stock.dbservice.resource;

import org.springframework.web.bind.annotation.*;
import space.swapab.stock.dbservice.model.Quote;
import space.swapab.stock.dbservice.model.Quotes;
import space.swapab.stock.dbservice.repository.QuotesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/db")
public class DbServiceResource {

    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {

        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));

        return getQuotesByUserName(quotes.getUserName());
    }

    @DeleteMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {
        quotesRepository.delete(quotesRepository.findByUserName(username));

        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

}
