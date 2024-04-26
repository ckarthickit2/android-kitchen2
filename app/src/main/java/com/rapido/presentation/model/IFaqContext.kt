package com.rapido.presentation.model

/**
 * Created by Abhishek Raj on 09/06/23.
 */

sealed interface IFaqContext {
    val context: String
    val title: String
    data class FaqContext(override val context: String, override val title: String) : IFaqContext
}
