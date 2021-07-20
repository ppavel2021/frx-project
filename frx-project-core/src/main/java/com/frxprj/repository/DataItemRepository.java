package com.frxprj.repository;

import java.util.List;

import com.frxprj.model.DataItem;
import com.frxprj.model.DataItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataItemRepository extends JpaRepository<DataItem, DataItemId> {
    
    @Query(value = "select distinct fd.ticker, fd.period, fd.date, fd.time, fd.open, fd.low, fd.close, fd.high, fd.vol " +
            "from frx_data fd order by fd.date, fd.time",
            nativeQuery = true  )
    List<DataItem> findAll();

    @Query(value = "select * from (select distinct fd.ticker, fd.period, fd.date, fd.time, \n" +
            "row_number() over (partition by fd.date order by fd.date, fd.time) as cnt, \n" +
            "avg(fd.open) over (partition by fd.date order by fd.date, fd.time rows between 4 preceding and current row) as open,\n" +
            "avg(fd.close) over (partition by fd.date order by fd.date, fd.time rows between 4 preceding and current row) as close,\n" +
            "avg(fd.low) over (partition by fd.date order by fd.date, fd.time rows between 4 preceding and current row) as low,\n" +
            "avg(fd.high) over (partition by fd.date order by fd.date, fd.time rows between 4 preceding and current row) as high,\n" +
            "avg(fd.vol) over (partition by fd.date order by fd.date, fd.time rows between 4 preceding and current row) as vol\n" +
            "from frx_data fd order by date, time) as t where \n" +
            "t.cnt % 5 = 0 \n" +
            "order by t.date, t.time",
            nativeQuery = true  )
    List<DataItem> findAll5m();


    @Query(value = "select * from (select distinct fd.ticker, fd.period, fd.date, fd.time, \n" +
            "row_number() over (partition by fd.date order by fd.date, fd.time) as cnt, \n" +
            "avg(fd.open) over (partition by fd.date order by fd.date, fd.time rows between 59 preceding and current row) as open,\n" +
            "avg(fd.close) over (partition by fd.date order by fd.date, fd.time rows between 59 preceding and current row) as close,\n" +
            "avg(fd.low) over (partition by fd.date order by fd.date, fd.time rows between 59 preceding and current row) as low,\n" +
            "avg(fd.high) over (partition by fd.date order by fd.date, fd.time rows between 59 preceding and current row) as high,\n" +
            "avg(fd.vol) over (partition by fd.date order by fd.date, fd.time rows between 59 preceding and current row) as vol\n" +
            "from frx_data fd order by date, time) as t where \n" +
            "t.cnt % 60 = 0\n" +
            "order by t.date, t.time",
            nativeQuery = true  )
    List<DataItem> findAll1h();

    @Query(value = "select distinct fd.ticker, fd.\"period\", fd.date, '235900' as time, \n" +
            "avg(fd.open) as open,\n" +
            "avg(fd.close) as close,\n" +
            "avg(fd.low) as low,\n" +
            "avg(fd.high) as high,\n" +
            "avg(fd.vol) as vol\n" +
            "from frx_data fd \n" +
            "group by fd.date, fd.ticker, fd.\"period\"\n" +
            "order by fd.date",
            nativeQuery = true  )
    List<DataItem> findAll1d();

    @Query(value = "with t as (\n" +
            "select distinct fd.ticker, fd.\"period\", fd.date, '235900' as time, \n" +
            "row_number() over (order by fd.date) as cnt,\n" +
            "avg(fd.open) as open,\n" +
            "avg(fd.close) as close,\n" +
            "avg(fd.low) as low,\n" +
            "avg(fd.high) as high,\n" +
            "avg(fd.vol) as vol\n" +
            "from frx_data fd \n" +
            "group by fd.date, fd.ticker, fd.\"period\"\n" +
            "order by fd.date)\n" +
            "select tt.ticker, tt.period, tt.date, tt.time, tt.open, tt.close, tt.high, tt.low, tt.vol from (\n" +
            "select t.ticker, t.period, t.date, t.time, t.cnt,\n" +
            "avg(t.open) over (order by t.date rows between 6 preceding and current row) as open,\n" +
            "avg(t.close) over (order by t.date rows between 6 preceding and current row) as close,\n" +
            "avg(t.low) over (order by t.date rows between 6 preceding and current row) as low,\n" +
            "avg(t.high) over (order by t.date rows between 6 preceding and current row) as high,\n" +
            "avg(t.vol) over (order by t.date rows between 6 preceding and current row) as vol\n" +
            "from t) as tt\n" +
            "where tt.cnt % 7 = 0\n" +
            "order by tt.date",
            nativeQuery = true  )
    List<DataItem> findAll1w();
}
