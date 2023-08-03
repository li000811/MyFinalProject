package algonquin.cst2335.myfinalproject.currencyConverter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CurrencyDAO {

    @Insert
    void insertCurrency(Currency c);

    @Query("Select * from Currency")
    List<Currency> getAllCurrencys();

    @Delete
    void deleteCurrency(Currency c);
}
