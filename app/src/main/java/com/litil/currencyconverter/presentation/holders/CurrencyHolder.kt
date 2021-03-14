package com.litil.currencyconverter.presentation.holders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.litil.currencyconverter.R
import com.litil.currencyconverter.domain.models.Currency

class CurrencyHolder(itemView: View, private val onClicked: (Currency) -> Unit): RecyclerView.ViewHolder(itemView) {
    private val currencyInfoText: TextView = this.itemView.findViewById(R.id.currency_info_text)

    fun bind(currency: Currency) {
        this.currencyInfoText.text = this.itemView.context.getString(
            R.string.currencies_info_format,
            "1.0", "RUB", currency.value.toString(), currency.charCode
        )
        this.itemView.setOnClickListener { this.onClicked(currency) }
    }
}
