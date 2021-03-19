package com.litil.currencyconverter.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
        private val currencyCharCodeText: TextView = this.itemView.findViewById(R.id.currency_charcode_text)
        private val currencyValueEdit: EditText = this.itemView.findViewById(R.id.currency_value_edit)
        private val currencyNameText: TextView = this.itemView.findViewById(R.id.currency_name_text)

        fun bind(currency: Currency) {
            this.currencyCharCodeText.text = this.itemView.context.getString(
                R.string.currency_charcode_format,
                currency.charCode
            )
            this.currencyValueEdit.setText(this.itemView.context.getString(
                R.string.currency_value_edit_format,
                "%.3f".format(1.0 / currency.value)
//                String.format("%3f", 1.0 / currency.value)
//            1.0 / currency.value
            ))
            this.currencyNameText.text = this.itemView.context.getString(
                R.string.currency_name_format,
                currency.name
            )
            this.itemView.setOnClickListener { this.onClicked(currency) }
        }
    }
}