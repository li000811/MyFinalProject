package algonquin.cst2335.myfinalproject.currencyConverter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Currency.class}, version = 1)
public abstract class CurrencyDatabase extends RoomDatabase {
    public abstract CurrencyDAO cDAO();
}
