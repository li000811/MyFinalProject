/**
 * CST2335_010_012_Final Project_Currency Converter
 * Changhong Li (041071022)
 */

package algonquin.cst2335.myfinalproject.currencyConverter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface CurrencyDAO is a Data Access Object (DAO) interface for Currency entity.
 * This interface defines the database operations related to the Currency entity.
 *
 * @author Changhong Li
 */
@Dao
public interface CurrencyDAO {

    /**
     * Inserts a new currency conversion record into the database.
     *
     * @param c The Currency object representing the conversion record to be inserted.
     */
    @Insert
    void insertCurrency(Currency c);

    /**
     * Retrieves all currency conversion records from the database.
     *
     * @return A list of Currency objects representing all conversion records in the database.
     */
    @Query("Select * from Currency")
    List<Currency> getAllCurrencys();

    /**
     * Deletes a currency conversion record from the database.
     *
     * @param c The Currency object representing the conversion record to be deleted.
     */
    @Delete
    void deleteCurrency(Currency c);
}
