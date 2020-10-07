package sizhe.chen.mogodb.converter;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @ClassName : MoneyReadConverter
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 10:52
 */
public class MoneyReadConverter implements Converter<Document, Money> {
    @Override
    public Money convert(Document source) {
        Document money = (Document) source.get("money");
        Double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document)money.get("currency")).getString("code");

        return Money.of(CurrencyUnit.of(currency),amount);
    }
}
