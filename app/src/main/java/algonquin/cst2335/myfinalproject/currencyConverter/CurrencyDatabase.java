/**
 * CST2335_010_012_Final Project_Currency Converter
 * Changhong Li (041071022)
 */

package algonquin.cst2335.myfinalproject.currencyConverter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * A RoomDatabase class that serves as the main access point
 * to the SQLite database for the CurrencyConverter app.
 * This class defines the database configuration and provides access
 * to the CurrencyDAO for database operations.
 *
 * @author Changhong Li
 */
@Database(entities={Currency.class}, version = 1)
public abstract class CurrencyDatabase extends RoomDatabase {

    /**
     * Returns the CurrencyDAO instance that provides access to the database operations.
     *
     * @return The CurrencyDAO instance associated with this database.
     */
    public abstract CurrencyDAO cDAO();
}
