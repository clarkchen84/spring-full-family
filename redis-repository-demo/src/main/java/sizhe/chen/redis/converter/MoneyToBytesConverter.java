package sizhe.chen.redis.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName : MoneyToBytesConverter
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 14:39
 */
@WritingConverter
public class MoneyToBytesConverter implements Converter<Money,byte[]> {


    @Override
    public byte[] convert(Money source) {
        String value = Long.toString( source.getAmountMinorLong());
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
