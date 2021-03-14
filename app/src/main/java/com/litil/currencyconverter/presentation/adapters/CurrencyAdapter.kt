package com.litil.currencyconverter.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.litil.currencyconverter.R
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.presentation.holders.CurrencyHolder

class CurrencyAdapter(private val onClicked: (Currency) -> Unit): RecyclerView.Adapter<CurrencyHolder>() {
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

}