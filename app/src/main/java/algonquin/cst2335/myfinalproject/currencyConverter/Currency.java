package algonquin.cst2335.myfinalproject.currencyConverter;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
/**
 * CST2335_010_012_Final Project_Currency Converter
 * Changhong Li (041071022)
 */

import androidx.room.PrimaryKey;

/**
 * The Currency class represents a currency conversion entity
 * that is stored in the database.
 * Each Currency object contains information about the original currency,
 * target currency, and the corresponding amounts for the conversion.
 *
 * @Author Changhong Li
 */
@Entity(tableName = "currency")
public class Currency {

    /**
     * The unique identifier for the currency conversion record.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;

    /**
     * The original currency code (e.g., USD, EUR) for the conversion.
     */
    @ColumnInfo(name="CurrencyFrom")
    String CurrencyFrom;

    /**
     * The target currency code (e.g., USD, EUR) for the conversion.
     */
    @ColumnInfo(name="CurrencyTo")
    String CurrencyTo;

    /**
     * The amount of the original currency to be converted.
     */
    @ColumnInfo(name="AmountFrom")
    double AmountFrom;

    /**
     * The amount of the target currency after the conversion.
     */
    @ColumnInfo(name="AmountTo")
    double AmountTo;

    /**
     * Retrieves the original currency code for the conversion.
     *
     * @return The original currency code as a String.
     */
    public String getCurrencyFrom() {
        return CurrencyFrom;
    }

    /**
     * Sets the original currency code for the conversion.
     *
     * @param currencyFrom The original currency code to be set.
     */
    public void setCurrencyFrom(String currencyFrom) {
        CurrencyFrom = currencyFrom;
    }

    /**
     * Retrieves the target currency code for the conversion.
     *
     * @return The target currency code as a String.
     */
    public String getCurrencyTo() {
        return CurrencyTo;
    }

    /**
     * Sets the target currency code for the conversion.
     *
     * @param currencyTo The target currency code to be set.
     */
    public void setCurrencyTo(String currencyTo) {
        CurrencyTo = currencyTo;
    }

    /**
     * Retrieves the amount of the original currency to be converted.
     *
     * @return The amount of the original currency as a double.
     */
    public double getAmountFrom() {
        return AmountFrom;
    }

    /**
     * Sets the amount of the original currency to be converted.
     *
     * @param amountFrom The amount of the original currency to be set.
     */
    public void setAmountFrom(double amountFrom) {
        AmountFrom = amountFrom;
    }

    /**
     * Retrieves the amount of the target currency after the conversion.
     *
     * @return The amount of the target currency as a double.
     */
    public double getAmountTo() {
        return AmountTo;
    }

    /**
     * Sets the amount of the target currency after the conversion.
     *
     * @param amountTo amountTo The amount of the target currency to be set.
     */
    public void setAmountTo(double amountTo) {
        AmountTo = amountTo;
    }
}
