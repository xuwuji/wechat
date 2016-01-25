
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.xuwuji.wechat.common.util.TimeUtil;

@Service
public class StockService {

	public static void addRecord(Double dot, String type) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		StockMapper mapper = session.getMapper(StockMapper.class);
		String date = TimeUtil.currentTimewithoutMinutes();
		mapper.addStockRecord(dot, "shanghai_stock", date);
		session.commit();
		session.close();
	}
}
