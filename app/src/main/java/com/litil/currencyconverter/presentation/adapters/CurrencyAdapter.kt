package com.litil.currencyconverter.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.litil.currencyconverter.R
import com.litil.currencyconverter.domain.models.Currency

class CurrencyAdapter(private val onClicked: (Currency) -> Unit): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    var currenciesList: List<Currency> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
            this.onClicked
        )
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.bind(this.currenciesList[position])
    }

    override fun getItemCount(): Int {
        return this.currenciesList.count()
    }

    inner class CurrencyHolder(itemView: View, private val onClicked: (Currency) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val currencyInfoText: TextView = this.itemView.findViewById(R.id.currency_info_text)

        fun bind(currency: Currency) {
            this.currencyInfoText.text = this.itemView.context.getString(
                R.string.currencies_info_format,
                "1.0", currency.charCode, currency.value.toString(), "RUB"
            )
            this.itemView.setOnClickListener { this.onClicked(currency) }
        }
    }
}