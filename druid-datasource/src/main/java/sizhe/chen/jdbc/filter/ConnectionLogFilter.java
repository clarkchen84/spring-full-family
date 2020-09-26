package sizhe.chen.jdbc.filter;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @ClassName : ConnectionLogFilter
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 17:06
 */

@Component
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("connect before");
      //  super.connection_connectBefore(chain, info);
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("connect after ");
       // super.connection_connectAfter(connection);
    }
}
