
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface StockMapper {

	@Insert("insert into ${table} (dot,date) values (#{dot},#{date})")
	public void addStockRecord(@Param("dot") Double dot, @Param("table") String table, @Param("date") String date);

}
